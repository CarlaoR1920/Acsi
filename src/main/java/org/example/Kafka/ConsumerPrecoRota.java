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

public class ConsumerPrecoRota {
    public static void main(String[] args) {

        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "meu-grupo");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.WARN);
        try (Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProperties)) {

            String topicName = "topicoPrecoRota";
            kafkaConsumer.subscribe(Collections.singletonList(topicName));

            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    String pedido = record.value();
                    JSONObject json = new JSONObject(pedido);
                    String origem = json.getString("origem");
                    String destino = json.getString("destino");
                    String tipo = json.getString("tipo");
                    String url = "jdbc:mysql://192.168.217.132:3306/TubMobile";
                    String usuario = "user";
                    String senha = "pass";

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
                            String sql = "SELECT * FROM Rota WHERE origem = ? AND destino = ?";
                            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                                statement.setString(1, origem);
                                statement.setString(2, destino);
                                try (ResultSet resultSet = statement.executeQuery()) {
                                    if (resultSet.next()) {
                                        String preco = null;
                                        if (tipo.equals("Bilhete simples")) {
                                            preco = resultSet.getString("valor_bilhete");
                                        } else if (tipo.equals("Pack 5 Bilhetes")) {
                                            preco = resultSet.getString("valor_pack5");
                                        } else {
                                            preco = resultSet.getString("valor_passe");
                                        }

                                        ProducerValorPagamento pvp = new ProducerValorPagamento();
                                        pvp.producerValorPagamento(preco);
                                        // Exemplo de como usar os dados recuperados
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
