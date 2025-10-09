package com.hrapp.hr_payroll.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ApplicationRunner runner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Beans loaded:");
            for (String name : ctx.getBeanDefinitionNames()) {
                if (name.toLowerCase().contains("listener")) {
                    System.out.println(" - " + name);
                }
            }
        };
    }

}
