package org.example.Backend;

import java.sql.Time;

public class Horario {
    private Time hora_partida;
    private int duracao;

    public Horario() {
    }

    public Time getHora_partida() {
        return hora_partida;
    }

    public void setHora_partida(Time hora_partida) {
        this.hora_partida = hora_partida;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
