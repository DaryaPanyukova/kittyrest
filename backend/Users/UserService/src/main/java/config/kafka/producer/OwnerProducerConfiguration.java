package config.kafka.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class OwnerProducerConfiguration {

    private Map<String, Object> ConfigProps() {
        Map<String, Object> configProps = new HashMap<>();
        String bootstrapServers = "localhost:9092";
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return configProps;
    }

    @Bean(name = "OwnerProducerFactory")
    public ProducerFactory<String, Object> ownerProducerFactory() {
        return new DefaultKafkaProducerFactory<>(ConfigProps());
    }

    @Bean(name = "OwnerKafkaTemplate")
    public KafkaTemplate<String, Object> ownerKafkaTemplate(
            @Qualifier("OwnerProducerFactory") ProducerFactory<String, Object> ownerProducerFactory) {
        return new KafkaTemplate<>(ownerProducerFactory);
    }
}
