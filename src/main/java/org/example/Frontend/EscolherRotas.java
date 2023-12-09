package org.example.Frontend;

import org.example.Kafka.ConsumerValorPagamento;
import org.example.Kafka.ProducerPrecoRota;
import org.json.JSONObject;

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
        String s2[] = {"Braga", "Guimaraes", "Barcelos", "Famalicao"};
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
                String origem = cbOrigem.getSelectedItem().toString();
                String destino = cbDestino.getSelectedItem().toString();
                String tipo = cbTipo.getSelectedItem().toString();
                System.out.println(origem);
                System.out.println(destino);
                if(origem == destino){
                    JOptionPane.showMessageDialog(escolherRotas,
                            "Escolha um destino diferente da sua origem!",
                            "Erro de rota",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                ProducerPrecoRota ppr = new ProducerPrecoRota();
                ConsumerValorPagamento cvp = new ConsumerValorPagamento();
                JSONObject json = new JSONObject();
                json.put("origem", origem);
                json.put("destino", destino);
                json.put("tipo", tipo);
                String jsonString = json.toString();
                ppr.producerPrecoRota(jsonString);
                float preco = cvp.consumerValorPagamento();
                System.out.println(preco);
                dispose();
                TipoDePagamento tipoPag = new TipoDePagamento(origem, destino, tipo, username, preco);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

