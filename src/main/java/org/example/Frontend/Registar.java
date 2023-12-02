package org.example.Frontend;

import Backend.*;

import javax.swing.*;


public class Registar extends JFrame {
    private JTextField fdNome;
    private JTextField fdUser;
    private JTextField textField3;
    private JTextField textField4;
    private JPanel registoPanel;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JTextField textField1;
    private JLabel tfEmail;
    private JLabel tfPass;
    private JLabel tfConfPass;
    private JLabel tfUser;
    private JLabel tfNome;

    public Registar() {
        setContentPane(registoPanel);
        setTitle("Regista-te");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
