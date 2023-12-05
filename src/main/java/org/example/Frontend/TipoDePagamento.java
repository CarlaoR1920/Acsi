package org.example.Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TipoDePagamento extends JFrame{
    private String origem;
    private String destino;
    private String tipo;
    private JComboBox cbMeioPagamento;
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
        String s1[] = {"Multibanco", "MBWay", "PayPal"};
        cbMeioPagamento.setModel(new javax.swing.DefaultComboBoxModel(s1));


        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pagamento = cbMeioPagamento.getSelectedItem().toString();
                JFrame jFrame = new JFrame();
                if(pagamento.equals("MBWay")) {
                    String getMessage = JOptionPane.showInputDialog(jFrame, "Insira o seu número de telemóvel:");
                    JOptionPane.showMessageDialog(jFrame, "Your message: " + getMessage);
                } else if(pagamento.equals("Multibanco")){
                    JOptionPane.showMessageDialog(jFrame, "Entidade: 51033\nReferência: " + gerarReferenciaBancaria() + "\nValor: " + lbValor.getText()
                            + "\nPrazo de pagamento: " + obterDataAtualMaisUmDia() + " até às " + obterHoraAtualMaisUmDia());
                }else{
                    String getMessage = JOptionPane.showInputDialog(jFrame, "Insira o seu email:");
                    String getMessage2 = JOptionPane.showInputDialog(jFrame, "Insira a sua palavra passe:");
                }
            }
        });
    }

}
