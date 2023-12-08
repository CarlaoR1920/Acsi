package org.example.Kafka;

import ch.qos.logback.classic.Level;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerResultadoTokens {
        public ConsumerResultadoTokens() {
        }

        public int coonsumerResultadoTokens() {

            Properties consumerProperties = new Properties();
            consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "meu-grupo");
            consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
            rootLogger.setLevel(Level.WARN);

            // Criação do consumidor
            try (Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProperties)) {
                System.out.println("ola");
                String topicName = "topicoTokens";
                kafkaConsumer.subscribe(Collections.singletonList(topicName));


                while (true) {
                    System.out.println("ol2");
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        String tokens = record.value();
                        System.out.println("ola3");
                        return Integer.parseInt(tokens);
                    }
                }
            }
        }
    }

