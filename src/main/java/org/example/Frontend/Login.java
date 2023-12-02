package org.example.Frontend;

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
    }

}
