package org.example.Kafka;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Topico {

    public static void main(String[] args) {

        // Configurações do administrador do Kafka
        Properties adminProperties = new Properties();
        adminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        try (AdminClient adminClient = AdminClient.create(adminProperties)) {
            String topico1 = "topico1";
            String topico2="topicoFatura";
            String topico3="topicoValorCompra";
            String topico4="topicoTokens";
            String topico5 = "topicoPagamento";
            String topico7="topicoDadosLogin";
            String topico8="topicoResultadoLogin";


            // Número de partições e fator de replicação para o novo tópico
            int numPartitions = 1;
            short replicationFactor = 1;

            // Criação do objeto NewTopic
            //NewTopic newTopic1 = new NewTopic(topico1, numPartitions, replicationFactor);
            //NewTopic newTopic2 = new NewTopic(topico2, numPartitions, replicationFactor);
            //NewTopic newTopic3 = new NewTopic(topico3, numPartitions, replicationFactor);
            //NewTopic newTopic4 = new NewTopic(topico4, numPartitions, replicationFactor);
            //NewTopic newTopic5 = new NewTopic(topico5, numPartitions, replicationFactor);
            NewTopic newTopic7 = new NewTopic(topico7, numPartitions, replicationFactor);
            NewTopic newTopic8 = new NewTopic(topico8, numPartitions, replicationFactor);
            // Criação do tópico
            /*adminClient.createTopics(Collections.singletonList(newTopic1))
                    .all()
                    .get(); // Espera a operação ser concluída*/
            /*adminClient.createTopics(Collections.singletonList(newTopic2))
                    .all()
                    .get(); // Espera a operação ser concluída*/
            /*adminClient.createTopics(Collections.singletonList(newTopic3))
                    .all()
                    .get(); // Espera a operação ser concluída*/
            /*adminClient.createTopics(Collections.singletonList(newTopic4))
                    .all()
                    .get();*/
            /*adminClient.createTopics(Collections.singletonList(newTopic5))
                    .all()
                    .get();*/
            adminClient.createTopics(Collections.singletonList(newTopic7))
                    .all()
                    .get();
            adminClient.createTopics(Collections.singletonList(newTopic8))
                    .all()
                    .get();

            //System.out.println("Tópico criado com sucesso: " + topico1);
            //System.out.println("Tópico criado com sucesso: " + topico2);
            //System.out.println("Tópico criado com sucesso: " + topico3);
            //System.out.println("Tópico criado com sucesso: " + topico4);
            //System.out.println("Tópico criado com sucesso: " + topico5);
            System.out.println("Tópico criado com sucesso: " + topico7);
            System.out.println("Tópico criado com sucesso: " + topico8);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Erro ao criar o tópico: " + e.getMessage());
        }
    }
}
