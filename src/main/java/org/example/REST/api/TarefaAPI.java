package org.example.REST.api;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;


@RestController
@RequestMapping(value = "/tarefas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TarefaAPI {
    @GetMapping
    @ResponseBody
    public Double teste() {
        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "meu-grupo");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Criação do consumidor
        try (Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProperties)) {

            String topicName = "topicoValorCompra";
            kafkaConsumer.subscribe(Collections.singletonList(topicName));
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    return Double.valueOf(record.value());
                }
            }
        }
    }


    @PostMapping("/poste")
    @ResponseBody
    public void endpointComBody(@RequestBody String dados) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "topicoTokens";

        String key =  Integer.toString(1);
        String minha_string_sem_igual = dados.replace("=", "");
        float flaot = Float.parseFloat(minha_string_sem_igual);
        int i = (int) flaot;
        String dado = String.valueOf(i);
        // Criar um registro de produtor
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, dado);
        // Enviar o registro
        producer.send(record);

        System.out.println("Mensagem enviada com sucesso: Key = " + key + ", Value = " + dado);
        // Fechar o produtor ao finalizar
        producer.close();
    }
}



