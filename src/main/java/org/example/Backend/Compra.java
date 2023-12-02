package org.example.Backend;

import java.sql.Date;
import java.sql.Time;

public class Compra {
    private Pagamento pagamento;
    private Date data_compra;
    private Time hora_compra;
    private int titulo_transporte;

    public Compra() {
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public Time getHora_compra() {
        return hora_compra;
    }

    public void setHora_compra(Time hora_compra) {
        this.hora_compra = hora_compra;
    }

    public int getTitulo_transporte() {
        return titulo_transporte;
    }

    public void setTitulo_transporte(int titulo_transporte) {
        this.titulo_transporte = titulo_transporte;
    }

}
