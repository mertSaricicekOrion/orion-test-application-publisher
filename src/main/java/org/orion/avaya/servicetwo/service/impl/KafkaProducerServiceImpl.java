package org.orion.avaya.servicetwo.service.impl;

import lombok.RequiredArgsConstructor;
import org.orion.avaya.servicetwo.model.Call;
import org.orion.avaya.servicetwo.service.KafkaProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Async
    public CompletableFuture<SendResult<String, String>> send(Call call) {
        return kafkaTemplate.send("call", String.valueOf(call.getId()));

    }
}
