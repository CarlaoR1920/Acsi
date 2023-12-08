package org.example.Frontend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPasswordField tfPass;
    private JTextField tfUser;
    private JPanel loginPanel;
    private JButton registerBtn;
    private JButton loginButton;
    private JButton registarBtn;

    public Login() {
        setContentPane(loginPanel);
        setTitle("TubMobile");
        setSize(500, 450);
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
                String pass = tfPass.getText();
                JSONObject json = new JSONObject();
                json.put("nome", user);
                json.put("password", pass);
                String jsonString = json.toString();
                ProducerLogin pl = new ProducerLogin();
                pl.producerLogin(jsonString);
                ConsumerResultadoLogin crl = new ConsumerResultadoLogin();
                if (crl.coonsumerResultadoLogin()) {
                    JOptionPane.showMessageDialog(loginPanel,
                            "Login efetuado com sucesso!",
                            "Bem-Vindo",
                            JOptionPane.INFORMATION_MESSAGE);
                    EscolherRotas rotas = new EscolherRotas(user);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(loginPanel,
                            "Login efetuado com sucesso!",
                            "Bem-Vindo",
                            JOptionPane.ERROR_MESSAGE);
                    dispose();
                }
            }
        });
    }
}


