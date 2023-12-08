package org.example.Kafka;

import ch.qos.logback.classic.Level;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MainConsumer {
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

            String topicName = "topico1";
            kafkaConsumer.subscribe(Collections.singletonList(topicName));


            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    String pedido = record.value();
                    JSONObject jsonFromString = new JSONObject(pedido);
                    JSONObject utilizador = jsonFromString.getJSONObject("utilizador");

                    //String url = "jdbc:mysql://192.168.56.10:3306/TubMobile";
                    String url = "jdbc:mysql://192.168.217.132:3306/TubMobile";
                    String usuario = "user";
                    String senha = "pass";

                    // Dados para inserção
                    String username = utilizador.getString("username");
                    String email = utilizador.getString("email");
                    String password = utilizador.getString("password");
                    String nome = utilizador.getString("nome");
                    String nif = utilizador.getString("nif");
                    String tipo = utilizador.getString("tipo");

                    try {
                        // Carregar o driver JDBC
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // Estabelecer a conexão com o banco de dados
                        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
                            // Consulta SQL para o INSERT
                            String sql = "INSERT INTO Utilizadores (username, email, password, nome,nif, tipo) VALUES (?, ?, ?, ?,?,?)";

                            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                                // Atribuir valores aos parâmetros da consulta
                                statement.setString(1, username);
                                statement.setString(2, email);
                                statement.setString(3, password);
                                statement.setString(4, nome);
                                statement.setString(5, nif);
                                statement.setString(6, tipo);


                                // Executar a consulta
                                int linhasAfetadas = statement.executeUpdate();

                                if (linhasAfetadas > 0) {
                                    System.out.println("Inserção bem-sucedida!");
                                } else {
                                    System.out.println("Falha na inserção.");
                                }
                            }
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            System.err.println("Erro no consumidor: " + e.getMessage());
        }
    }
}