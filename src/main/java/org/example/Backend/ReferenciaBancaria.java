package org.example.Backend;

import java.sql.Date;
import java.sql.Time;

public class ReferenciaBancaria extends Pagamento {
    private int entidade;
    private int referencia;
    private Date data_validade;
    private Time hora_validade;

    public ReferenciaBancaria() {
    }

    public int getEntidade() {
        return entidade;
    }

    public void setEntidade(int entidade) {
        this.entidade = entidade;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public Date getData_validade() {
        return data_validade;
    }

    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }

    public Time getHora_validade() {
        return hora_validade;
    }

    public void setHora_validade(Time hora_validade) {
        this.hora_validade = hora_validade;
    }
}
