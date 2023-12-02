package org.example.Backend;

import java.util.HashMap;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer1 {

    public void prod1() {

        // Configurações do produtor
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Criar o produtor
        Producer<String, String> producer = new KafkaProducer<>(properties);

        // Tópico para o qual enviar a mensagem
        String topic = "meu-topico";

        try {
            // Enviar algumas mensagens de exemplo
            for (int i = 0; i < 10; i++) {
                String key = "chave-" + i;
                String value = "mensagem-" + i;

                // Criar um registro de produtor
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

                // Enviar o registro
                producer.send(record);

                System.out.println("Mensagem enviada com sucesso: Key = " + key + ", Value = " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechar o produtor ao finalizar
            producer.close();
        }
    }
}
