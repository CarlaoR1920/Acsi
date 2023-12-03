package org.example.Kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MainConsumer {
    public static void main(String[] args) {
        /*
        // Configurações do administrador do Kafka
        Properties adminProperties = new Properties();
        adminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        try (AdminClient adminClient = AdminClient.create(adminProperties)) {
            String topicName = "topico1";

            // Número de partições e fator de replicação para o novo tópico
            int numPartitions = 1;
            short replicationFactor = 1;

            // Criação do objeto NewTopic
            NewTopic newTopic = new NewTopic(topicName, numPartitions, replicationFactor);

            // Criação do tópico
            adminClient.createTopics(Collections.singletonList(newTopic))
                    .all()
                    .get(); // Espera a operação ser concluída

            System.out.println("Tópico criado com sucesso: " + topicName);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Erro ao criar o tópico: " + e.getMessage());
        }
        */
        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "meu-grupo");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Criação do consumidor
        try (Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProperties)) {
            // Assinatura do tópico a ser consumido
            String topicName = "topico1";
            kafkaConsumer.subscribe(Collections.singletonList(topicName));

            // Loop de leitura de mensagens
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    System.out.println("Recebida mensagem:");
                    System.out.println("Tópico: " + record.topic());
                    System.out.println("Partição: " + record.partition());
                    System.out.println("Offset: " + record.offset());
                    System.out.println("Chave: " + record.key());
                    System.out.println("Valor: " + record.value());
                    System.out.println();
                });
            }
        } catch (Exception e) {
            System.err.println("Erro no consumidor: " + e.getMessage());
        }
    }
}