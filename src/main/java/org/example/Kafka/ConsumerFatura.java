package org.example.Kafka;
import ch.qos.logback.classic.Level;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerFatura {
    public static void main(String[] args) {

        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "meu-grupo");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.WARN);
        try (Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProperties)) {

            String topicName = "topicoFatura";
            kafkaConsumer.subscribe(Collections.singletonList(topicName));

            while (true) {
                //System.out.println("1");
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    String username = record.value();
                    System.out.println("username");
                    String url = "jdbc:mysql://192.168.217.132:3306/TubMobile";
                    String usuario = "user";
                    String senha = "pass";

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");

                    try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
                        String sql = "SELECT * FROM Utilizadores WHERE username = ?";
                        try (PreparedStatement statement = connection.prepareStatement(sql)) {
                            statement.setString(1, username);
                            try (ResultSet resultSet = statement.executeQuery()) {
                                if (resultSet.next()) {

                                    String nif = resultSet.getString("nif");
                                    int id = resultSet.getInt("id");
                                    JSONObject utilizador = new JSONObject();
                                    ProducerDadosCompra prod = new ProducerDadosCompra();
                                    utilizador.put("nif", nif);
                                    JSONObject json = new JSONObject();
                                    json.put("utilizador", utilizador);
                                    String jsonString = json.toString();
                                    prod.producerDadosCompra(jsonString);
                                    // Exemplo de como usar os dados recuperados
                                    System.out.println("Nif: " + nif + ", Id: " + id);
                                } else {
                                    System.out.println("Usuário não encontrado no banco de dados.");
                                }
                            }
                        }


                    }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        }catch (Exception e) {
            System.err.println("Erro no consumidor: " + e.getMessage());
        }
    }
}
