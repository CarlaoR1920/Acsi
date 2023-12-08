package org.example.Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TipoDePagamento extends JFrame {
    private String origem;
    private String destino;
    private String tipo;
    private JComboBox cbMeioPagamento;
    private JLabel lbValor;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JPanel panelTipoDePagamento;

    public static String obterHoraAtualMaisUmDia() {
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaMaisUmDia = horaAtual.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        return horaMaisUmDia.format(formatter);
    }

    public static String obterDataAtualMaisUmDia() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataMaisUmDia = dataAtual.plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return dataMaisUmDia.format(formatter);
    }

    public static String gerarReferenciaBancaria() {
        // Tamanho da referência bancária desejada
        int tamanhoReferencia = 10;

        // Geração de números aleatórios
        Random random = new Random();

        StringBuilder referencia = new StringBuilder();

        for (int i = 0; i < tamanhoReferencia; i++) {
            int digito = random.nextInt(10); // Gera um número aleatório entre 0 e 9
            referencia.append(digito);
        }

        return referencia.toString();
    }

    public TipoDePagamento(String origem, String destino, String tipo) {

        this.origem = origem;
        this.destino = destino;
        this.tipo = tipo;
        setContentPane(panelTipoDePagamento);
        setTitle("TubMobile");
        setSize(650, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        lbValor.setText("50.49");
        String s1[] = {"Multibanco", "MBWay", "PayPal"};
        cbMeioPagamento.setModel(new javax.swing.DefaultComboBoxModel(s1));


        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pagamento = cbMeioPagamento.getSelectedItem().toString();
                JFrame jFrame = new JFrame();
                if(pagamento.equals("MBWay")) {
                    String getMessage = JOptionPane.showInputDialog(jFrame, "Insira o seu número de telemóvel:");
                    prodValCompra.prod("5.49");
                    hora_atual = obterhora();
                    data = CurrentDateExample();
                    pay.put("hora_compra", hora_atual);
                    pay.put("data", data);
                    jsonString = pay.toString();
                    prodPagamento.producerPagamento(jsonString);
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
