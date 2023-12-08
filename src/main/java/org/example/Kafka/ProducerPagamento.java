package org.example.Kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;



    public class ProducerPagamento {
        public ProducerPagamento(){}
        public void producerPagamento(String dados) {

            Properties properties = new Properties();
            properties.put("bootstrap.servers", "localhost:9092");
            properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            Producer<String, String> producer = new KafkaProducer<>(properties);

            String topic = "topicoPagamento";

            String key =  Integer.toString(1);

            // Criar um registro de produtor
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, dados);

            // Enviar o registro
            producer.send(record);

            System.out.println("Mensagem enviada com sucesso: Key = " + key + ", Value = " + dados);
            // Fechar o produtor ao finalizar
            producer.close();
        }
    }

