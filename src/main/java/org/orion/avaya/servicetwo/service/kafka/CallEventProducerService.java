package org.orion.avaya.servicetwo.service.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.orion.avaya.servicetwo.model.CallEvent;
import org.orion.avaya.servicetwo.service.kafka.callback.CallEventProducerCallBack;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public abstract class CallEventProducerService implements KafkaProducerService<CallEvent> {

    //Topic can be configurable
    public static final String CALL_EVEN_TOPIC = "call";
    private final KafkaProducer<String, CallEvent> kafkaProducer;

    @Override
    public Future<RecordMetadata> send(CallEvent call) {
        ProducerRecord<String, CallEvent> callProducerRecord = new ProducerRecord<>(CALL_EVEN_TOPIC, call);
        return kafkaProducer.send(callProducerRecord, new CallEventProducerCallBack());


    }
}
