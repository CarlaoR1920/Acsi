package org.example.Backend;

import java.sql.Date;

public class PasseMensal extends TituloDeTransporte {
    private Date data_validade;

    public PasseMensal() {
    }

    public Date getData_validade() {
        return data_validade;
    }

    public void setData_validade(Date data_validade) {
        this.data_validade = data_validade;
    }
}
