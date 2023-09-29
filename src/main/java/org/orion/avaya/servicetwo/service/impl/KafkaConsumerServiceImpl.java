package org.orion.avaya.servicetwo.service.impl;

import lombok.RequiredArgsConstructor;
import org.orion.avaya.servicetwo.model.CallStatus;
import org.orion.avaya.servicetwo.service.KafkaConsumerService;
import org.orion.avaya.servicetwo.service.RedisService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
    private final RedisService redisService;

    @Override
    @KafkaListener(topics = "call-processed",groupId = "foo")
    public void recieveMessage(String message) {
        redisService.update(message, CallStatus.PROCESSED);
    }
}
