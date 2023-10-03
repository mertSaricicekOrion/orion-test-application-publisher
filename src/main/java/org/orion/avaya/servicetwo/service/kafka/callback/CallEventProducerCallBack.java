package org.orion.avaya.servicetwo.service.kafka.callback;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

@Slf4j
public class CallEventProducerCallBack implements Callback {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            log.info("{} offset, {} topic is sent successfully", metadata.offset(), metadata.topic());
        } else {
            exception.printStackTrace();
        }
    }
}
