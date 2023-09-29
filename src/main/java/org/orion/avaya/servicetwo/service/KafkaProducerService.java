package org.orion.avaya.servicetwo.service;

import org.orion.avaya.servicetwo.model.Call;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface KafkaProducerService {

     CompletableFuture<SendResult<String, String>> send(Call call);
}
