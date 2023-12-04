package org.example.Frontend;

import javax.swing.*;

public class TipoDePagamento extends JFrame{
    private String origem;
    private String destino;
    private String tipo;
    private JComboBox comboBox1;
    private JLabel lbValor;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel panelTipoDePagamento;

    public TipoDePagamento(String origem, String destino, String tipo){
        this.origem=origem;
        this.destino=destino;
        this.tipo=tipo;
        setContentPane(panelTipoDePagamento);
        setTitle("TubMobile");
        setSize(650, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        lbValor.setText("50.49€");
        String s1[] = { "Multibanco", "MBWay", "PayPal"};
        comboBox1.setModel(new javax.swing.DefaultComboBoxModel(s1));
    }
}
