package org.example.Frontend;

import org.example.Backend.*;
import org.example.Kafka.*;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Registar extends JFrame {
    private JTextField textField3;
    private JPanel registoPanel;
    private JButton cancelarButton;
    private JButton confirmarButton;

    private JTextField tfEmail;
    private JTextField tfUser;
    private JTextField tfNome;
    private JPasswordField pfConfPass;
    private JPasswordField pfPass;

    public Registar() {
        setContentPane(registoPanel);
        setTitle("Regista-te");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   registarUser();
                   Login login= new Login();
                   dispose();

               }
               catch (Exception x)
               {
                   x.printStackTrace();
                   JOptionPane.showConfirmDialog(registoPanel,
                           "Não foi possível realizar o registo! Tente novamente!",
                           "Registo falhado!",
                           JOptionPane.ERROR_MESSAGE);
               }
            }
        });
    }

    private void registarUser() {
        Utilizador user = new Utilizador();
        String username = tfUser.getText();
        String nome = tfNome.getText();
        String email = tfEmail.getText();
        char[] passChars = pfPass.getPassword();
        String pass = new String(passChars);
        char[] confPassChars = pfConfPass.getPassword();
        String confPass = new String(confPassChars);
        System.out.println(pass);
        if (username.isEmpty() || nome.isEmpty() || email.isEmpty() || pass.isEmpty() || confPass.isEmpty()) {
            JOptionPane.showConfirmDialog(this,
                    "Preencha todos os campos por favor!",
                    "Tenta novamente!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!pass.equals(confPass)) {
            JOptionPane.showConfirmDialog(this,
                    "Confirme a palavra-passe, por favor!",
                    "Tenta novamente!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Producer1 prod1 = new Producer1();

        JSONObject utilizador = new JSONObject();
        utilizador.put("nome", nome);
        utilizador.put("username", username);
        utilizador.put("email", email);
        utilizador.put("password", pass);
        utilizador.put("tipo", "Passageiro");

        JSONObject json = new JSONObject();
        json.put("tipo", "Insert");
        json.put("utilizador", utilizador);

        String jsonString = json.toString();

        prod1.prod1(jsonString);
        JOptionPane.showConfirmDialog(this,
                "Registo efetuado com sucesso!",
                "Registo bem sucedido",
                JOptionPane.ERROR_MESSAGE);
        return;
    }
}
