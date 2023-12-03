package org.example.Kafka;

import java.util.HashMap;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer1 {
    public Producer1(){}
    public void prod1() {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "topico1";

        try {
            for (int i = 0; i < 10; i++) {
                String key =  Integer.toString(i);
                String value =  Integer.toString(i);

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
