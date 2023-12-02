package org.example.Backend;

import java.sql.Date;
import java.sql.Time;

public class Bilhete extends TituloDeTransporte {
    private Date data_utilizacao;
    private Time hora_utilizacao;

    public Bilhete() {
    }

    public Date getData_utulizacao() {
        return data_utilizacao;
    }

    public void setData_utulizacao(Date data_utilizacao) {
        this.data_utilizacao = data_utilizacao;
    }

    public Time getHora_utulizacao() {
        return hora_utilizacao;
    }

    public void setHora_utulizacao(Time hora_utilizacao) {
        this.hora_utilizacao = hora_utilizacao;
    }
}
