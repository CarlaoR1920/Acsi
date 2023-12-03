package org.example.Frontend;

import org.example.Backend.*;

import javax.swing.*;


public class Registar extends JFrame {
    private JTextField textField3;
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
