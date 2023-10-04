package org.orion.avaya.servicetwo.service.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.orion.avaya.servicetwo.model.CallEvent;
import org.orion.avaya.servicetwo.service.kafka.callback.CallEventProducerCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class CallEventProducerService implements KafkaProducerService<CallEvent> {

    //Topic can be configurable
    public static final String CALL_EVENT_TOPIC = "call-event";
    @Qualifier("callEventProducer")
    private final KafkaProducer<String, CallEvent> kafkaProducer;

    @Autowired
    protected CallEventProducerService(KafkaProducer<String, CallEvent> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public Future<RecordMetadata> send(CallEvent call) {
        ProducerRecord<String, CallEvent> callProducerRecord = new ProducerRecord<>(CALL_EVENT_TOPIC, call);
        return kafkaProducer.send(callProducerRecord, new CallEventProducerCallBack());


    }
}
