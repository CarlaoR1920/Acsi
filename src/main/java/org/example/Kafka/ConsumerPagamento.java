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

public class ConsumerPagamento {
    public static void main(String[] args) {

        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "meu-grupo");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(Level.WARN);

        // Criação do consumidor
        try (Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProperties)) {

            String topicName = "topicoPagamento";
            kafkaConsumer.subscribe(Collections.singletonList(topicName));

            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    String pedido = record.value();
                    JSONObject pagamento = new JSONObject(pedido);

                    String url = "jdbc:mysql://192.168.217.132:3306/TubMobile";
                    String usuario = "user";
                    String senha = "pass";

                    // Dados para inserção
                    float valor = pagamento.getFloat("valor");
                    int estado = pagamento.getInt("estado");
                    String meio_pagamento = pagamento.getString("meio_pagamento");
                    String data_compra = pagamento.getString("data");
                    String hora_compra = pagamento.getString("hora_compra");
                    String username = pagamento.getString("username");
                    int id_passageiro=0;
                    String nif="";
                    int id_gerado=0;

                    try {
                        // Carregar o driver JDBC
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Estabelecer a conexão com o banco de dados
                        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
                            // Consulta SQL para o INSERT
                            String sql = "INSERT INTO Pagamentos (valor, estado, meio_pagamento) VALUES (?, ?, ?)";
                            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                                // Atribuir valores aos parâmetros da consulta
                                statement.setFloat(1, valor);
                                statement.setInt(2, estado);
                                statement.setString(3, meio_pagamento);

                                // Executar a consulta
                                int linhasAfetadas = statement.executeUpdate();
                                if (linhasAfetadas > 0) {
                                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                                        if (generatedKeys.next()) {
                                            id_gerado = generatedKeys.getInt(1);

                                        }
                                    }
                                } else {
                                    System.out.println("Falha na inserção.");
                                }
                            }
                            String sql2 = "SELECT * FROM Utilizadores WHERE username = ?";
                            try (PreparedStatement statement2 = connection.prepareStatement(sql2)) {
                                statement2.setString(1, username);
                                try (ResultSet resultSet = statement2.executeQuery()) {
                                    if (resultSet.next()) {
                                        nif = resultSet.getString("nif");
                                        id_passageiro  = resultSet.getInt("id");


                                    } else {
                                        System.out.println("Usuário não encontrado no banco de dados.");
                                    }
                                }
                            }
                            String sql3 = "INSERT INTO Faturas (id_passageiro, nif, id_pagamento, data_compra, hora_compra) VALUES (?, ?, ?, ?, ?)";
                            try (PreparedStatement statement3 = connection.prepareStatement(sql3)) {
                                    // Atribuir valores aos parâmetros da consulta
                                    statement3.setInt(1, id_passageiro);
                                    statement3.setString(2, nif);
                                    statement3.setInt(3, id_gerado);
                                    statement3.setString(4, data_compra);
                                    statement3.setString(5, hora_compra);
                                    // Executar a consulta
                                    int linhasAfetadas = statement3.executeUpdate();
                                    if (linhasAfetadas > 0) {
                                        System.out.println("Inserção bem-sucedida!");
                                    } else {
                                        System.out.println("Falha na inserção.");
                                    }
                            }
                        }

                        // Consulta SQL para selecionar dados do usuário
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            System.err.println("Erro no consumidor: " + e.getMessage());
        }
    }
}
