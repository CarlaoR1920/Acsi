package org.example.Frontend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JTextField tfPass;
    private JTextField tfUser;
    private JPanel loginPanel;
    private JButton registerBtn;
    private JButton loginButton;
    private JButton registarBtn;

    public Login() {
    setContentPane(loginPanel);
    setTitle("TubMobile");
    setSize(500,450);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    setVisible(true);
        registarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Registar registar = new Registar();
                registar.setVisible(true);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = tfUser.getText();
                String pass= tfPass.getText();
                verificarUsuario(user,pass);
            }
        });
    }


    public static void verificarUsuario( String username, String password) {
        try {
            String url = "jdbc:mysql://192.168.56.10:3306/TubMobile";
            String usuario = "user";
            String senha = "pass";

            // Carregar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão com o banco de dados
            try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
                // Consulta SQL para verificar o usuário com o nome de usuário e senha fornecidos
                String sql = "SELECT * FROM Utilizadores WHERE username = ? AND password = ?";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Atribuir valores aos parâmetros da consulta
                    statement.setString(1, username);
                    statement.setString(2, password);

                    // Executar a consulta
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            // Usuário encontrado, login bem-sucedido
                            JOptionPane.showConfirmDialog(this,
                                    "Bem-vindo!",
                                    "Login realizado com sucesso!",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Nenhum usuário correspondente encontrado
                            System.out.println("Usuário não encontrado ou senha incorreta.");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
