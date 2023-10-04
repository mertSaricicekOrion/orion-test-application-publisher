package org.orion.avaya.servicetwo.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.orion.avaya.servicetwo.model.CallCancelEvent;
import org.orion.avaya.servicetwo.model.CallEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("org.orion.avaya.servicetwo")
public class KafkaConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.properties.schema.registry.url}")
    private String registryUrl;

    private Map<String, Object> producerConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        config.put("schema.registry.url",registryUrl);
        // Add other common producer properties here
        return config;
    }


    @Bean("callEventProducer")
    @Lazy(value = false)
    public KafkaProducer<String, CallEvent> callEventKafkaProducer() {

        return new KafkaProducer<>(producerConfig());
    }


    @Bean("callCancelEventProducer")
    public KafkaProducer<String, CallCancelEvent> callCancelEventKafkaProducer() {
        return new KafkaProducer<>(producerConfig());
    }

}
