package org.example.Frontend;

import javax.swing.*;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EscolherRotas extends JFrame {
    private String username;
    private JComboBox cbTipo;
    private JComboBox cbOrigem;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JPanel escolherRotas;
    private JComboBox cbDestino;

    public EscolherRotas(String username) {
        this.username = username;
        String s1[] = {"Bilhete simples", "Pack 5 Bilhetes", "Passe Mensal"};
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(s1));
        String s2[] = {"Braga", "Guimarâes", "Barcelos", "Famalicão"};
        cbOrigem.setModel(new javax.swing.DefaultComboBoxModel(s2));
        cbDestino.setModel(new javax.swing.DefaultComboBoxModel(s2));
        setContentPane(escolherRotas);
        setTitle("TubMobile");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String origem = cbTipo.getSelectedItem().toString();
                String destino = cbTipo.getSelectedItem().toString();
                String tipo = cbTipo.getSelectedItem().toString();
                dispose();
                TipoDePagamento tipoPag = new TipoDePagamento(origem, destino, tipo, username);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

