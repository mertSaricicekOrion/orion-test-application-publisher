package org.orion.avaya.servicetwo.service.kafka;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

public interface KafkaProducerService<T extends SpecificRecord> {

    Future<RecordMetadata> send(T specificRecord);
}
