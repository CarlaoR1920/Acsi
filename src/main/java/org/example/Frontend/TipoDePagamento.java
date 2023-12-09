package org.example.Frontend;
import org.apache.kafka.clients.producer.Producer;
import org.example.Kafka.ProducerValorCompra;
import org.json.JSONObject;;
import org.example.Kafka.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.time.LocalTime;

public class TipoDePagamento extends JFrame {
    private String username;
    private String origem;
    private String destino;
    private String tipo;
    private float preco;
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
    private String  obterhora() {
        String hora_atual = String.valueOf(LocalTime.now());
        return hora_atual;
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

    public TipoDePagamento(String origem, String destino, String tipo, String username, float preco) {

        this.username = username;
        this.origem = origem;
        this.destino = destino;
        this.tipo = tipo;
        this.preco = preco;
        setContentPane(panelTipoDePagamento);
        setTitle("TubMobile");
        setSize(650, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        lbValor.setText(Float.toString(preco));
        String s1[] = {"Multibanco", "MBWay", "PayPal"};
        cbMeioPagamento.setModel(new javax.swing.DefaultComboBoxModel(s1));


        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pagamento = cbMeioPagamento.getSelectedItem().toString();
                String valor = lbValor.getText();
                String hora_atual;
                String data;
                Float valorCompra = Float.valueOf(valor);
                //ProducerCompra prodCompra = new ProducerCompra();
                ProducerPagamento prodPagamento = new ProducerPagamento();
                ProducerValorCompra prodValCompra = new ProducerValorCompra();
                JSONObject pay = new JSONObject();
                String jsonString;
                pay.put("valor", valor);
                pay.put("estado", "1");
                pay.put("meio_pagamento", pagamento);
                pay.put("username", username);
                JFrame jFrame = new JFrame();
                ConsumerResultadoTokens crt = new ConsumerResultadoTokens();
                int tokens;

                if(pagamento.equals("MBWay")) {
                    String getMessage = JOptionPane.showInputDialog(jFrame, "Insira o seu número de telemóvel:");
                    hora_atual = obterhora();
                    data = CurrentDateExample();
                    pay.put("hora_compra", hora_atual);
                    pay.put("data", data);
                    prodValCompra.prod(valor);
                    tokens = crt.coonsumerResultadoTokens();
                    pay.put("tokens", tokens);
                    jsonString = pay.toString();
                    prodPagamento.producerPagamento(jsonString);
                    JOptionPane.showMessageDialog(panelTipoDePagamento,
                            "Você Recebeu:"+ tokens + " tokens",
                            "Compra Bem-sucedida! ",
                            JOptionPane.INFORMATION_MESSAGE);

                } else if(pagamento.equals("Multibanco")){
                    JOptionPane.showMessageDialog(jFrame, "Entidade: 51033\nReferência: " + gerarReferenciaBancaria() + "\nValor: " + lbValor.getText()
                            + "\nPrazo de pagamento: " + obterDataAtualMaisUmDia() + " até às " + obterHoraAtualMaisUmDia());
                    //prodPagamento.producerCompra(username);
                    hora_atual = obterhora();
                    data = CurrentDateExample();
                    pay.put("hora_compra", hora_atual);
                    pay.put("data", data);
                    prodValCompra.prod(valor);
                    tokens = crt.coonsumerResultadoTokens();
                    pay.put("tokens", tokens);
                    jsonString = pay.toString();
                    prodPagamento.producerPagamento(jsonString);
                    JOptionPane.showMessageDialog(panelTipoDePagamento,
                            "Você Recebeu:"+ tokens + " tokens",
                            "Compra Bem-sucedida! ",
                            JOptionPane.INFORMATION_MESSAGE);

                }else{
                    String getMessage = JOptionPane.showInputDialog(jFrame, "Insira o seu email:");
                    String getMessage2 = JOptionPane.showInputDialog(jFrame, "Insira a sua palavra passe:");
                    //prodPagamento.producerCompra(username);
                    hora_atual = obterhora();
                    data = CurrentDateExample();
                    pay.put("hora_compra", hora_atual);
                    pay.put("data", data);
                    prodValCompra.prod(valor);
                    tokens = crt.coonsumerResultadoTokens();
                    pay.put("tokens", tokens);
                    jsonString = pay.toString();
                    prodPagamento.producerPagamento(jsonString);
                    JOptionPane.showMessageDialog(panelTipoDePagamento,
                            "Você Recebeu:"+ tokens + " tokens",
                            "Compra Bem-sucedida! ",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });
    }

    private String  CurrentDateExample() {
            LocalDate currentDate = LocalDate.now();

            // Formata a data para um formato específico (por exemplo, "dd/MM/yyyy")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = currentDate.format(formatter);

            return formattedDate;
        }


}
