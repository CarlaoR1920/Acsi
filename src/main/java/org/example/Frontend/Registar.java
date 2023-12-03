package org.example.Frontend;

import org.example.Backend.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Registar extends JFrame {
    private JPanel registoPanel;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JTextField tfEmail;
    private JTextField tfPass;
    private JTextField tfConfPass;
    private JTextField tfUser;
    private JTextField tfNome;

    public Registar() {
        setContentPane(registoPanel);
        setTitle("Regista-te");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registarUser();
            }
        });
    }

    private void registarUser()
    {
        Utilizador user = new Utilizador();
        String username = tfUser.getText();
        String nome = tfNome.getText();
        String email = tfEmail.getText();
        String pass = tfPass.getText();
        String confPass = tfConfPass.getText();

        if(username.isEmpty() || nome.isEmpty() || email.isEmpty() || pass.isEmpty() ||confPass.isEmpty())
        {
            JOptionPane.showConfirmDialog(this,
                    "Preencha todos os campos por favor!",
                    "Tenta novamente!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!pass.equals(confPass))
        {
            JOptionPane.showConfirmDialog(this,
                    "Confirme a palavra-passe, por favor!",
                    "Tenta novamente!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

}
