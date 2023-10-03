package org.orion.avaya.servicetwo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.orion.avaya.servicetwo.api.dto.CallDTO;
import org.orion.avaya.servicetwo.mapper.CallMapper;
import org.orion.avaya.servicetwo.model.CallEvent;
import org.orion.avaya.servicetwo.model.CallStatus;
import org.orion.avaya.servicetwo.service.CallService;
import org.orion.avaya.servicetwo.service.RedisService;
import org.orion.avaya.servicetwo.service.kafka.KafkaProducerService;
import org.springframework.stereotype.Service;

//TODO: fix the logging problem, you already tried logback with lombok
@Slf4j
@Service
@RequiredArgsConstructor
public class CallServiceImpl implements CallService {


    private final RedisService redisService;
    private final KafkaProducerService kafkaProducerService;
    private final CallMapper callMapper;

    public Long recieveCall(String telephoneNumber, CallDTO callDTO) {
        log.debug("Call is received for telephone number: {}", telephoneNumber);
        CallEvent call = new CallEvent();
        call.setLocale(callDTO.getLocale());
        call.setTimestamp(callDTO.getTimestamp());
        call.setTrunkName(callDTO.getTrunkName());
        call.setTelephoneNumber(telephoneNumber);
        call.setCallStatus(CallStatus.INVITED);
        log.debug("The call id for {} is: {}", telephoneNumber, call.getId());
        try {

            redisService.save(call);
        } catch (Exception exception) {
            log.error("{} Couldn't be saved to Redis error: {}", call.getId(), exception.getLocalizedMessage());
        }
        log.debug("Call successfully saved to redis callId: {}", call.getId());
        /*
        TODO: add callback method in there
        ListenableFuture<SendResult<String, String>> kafkaRequest = kafkaProducerService.send(call);
        kafkaRequest.completable.((stringStringSendResult, throwable) -> {
            if (throwable == null) {
                log.info("{} call is sent to Kafka", call.getId());
                log.debug("Request result: {}", stringStringSendResult);
                redisService.update(String.valueOf(call.getId()), org.orion.avaya.servicetwo.model.CallStatus.ACTIVE);
                System.out.println(stringStringSendResult);
            } else {
                log.error("{} call couldn't be sent to Kafka error message: {}", call.getId(), throwable.getLocalizedMessage());
                System.out.println(throwable.getMessage());
            }
        });*/

        return call.getId();
    }

}
