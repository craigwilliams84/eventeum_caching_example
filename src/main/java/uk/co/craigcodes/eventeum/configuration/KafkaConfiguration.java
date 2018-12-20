package uk.co.craigcodes.eventeum.configuration;

import net.consensys.eventeum.dto.message.EventeumMessage;
import net.consensys.eventeum.integration.KafkaSettings;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configures Kafka related beans.
 */
@EnableKafka
@Configuration
public class KafkaConfiguration {

    @Value("${kafka.bootstrap.addresses}")
    private String bootstrapAddresses;

    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddresses);
        return new KafkaAdmin(configs);
    }

    @Bean
    public ConsumerFactory<String, EventeumMessage> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddresses);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props, null, new JsonDeserializer<>(EventeumMessage.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EventeumMessage> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, EventeumMessage> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);
        return factory;
    }

    @Bean
    public NewTopic contractEventsTopic() {
        return new NewTopic("contract-events", 3, Short.parseShort("1"));
    }
}