package config.kafka.topic;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class OwnerTopicConfiguration {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaAdmin kafkaOwnerAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic createOwnerTopic() {
        return new NewTopic("create_owner", 1, (short) 1);
    }

    @Bean
    public NewTopic updateOwnerTopic() {
        return new NewTopic("update_owner", 1, (short) 1);
    }

    @Bean
    public NewTopic deleteOwnerTopic() {
        return new NewTopic("delete_owner", 1, (short) 1);
    }
}
