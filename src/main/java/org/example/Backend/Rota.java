package org.example.Backend;

import java.util.ArrayList;

public class Rota {
    private ArrayList<Horario> hora_partida;
    private String origem;
    private String destino;
    private int id_rota;
    private boolean estado;
    private float valor_bilhete;
    private float valor_pack5;
    private float valor_pass;

    public Rota() {
    }

    public ArrayList<Horario> getHora_partida() {
        return hora_partida;
    }

    public void setHora_partida(ArrayList<Horario> hora_partida) {
        this.hora_partida = hora_partida;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getId_rota() {
        return id_rota;
    }

    public void setId_rota(int id_rota) {
        this.id_rota = id_rota;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public float getValor_bilhete() {
        return valor_bilhete;
    }

    public void setValor_bilhete(float valor_bilhete) {
        this.valor_bilhete = valor_bilhete;
    }

    public float getValor_pack5() {
        return valor_pack5;
    }

    public void setValor_pack5(float valor_pack5) {
        this.valor_pack5 = valor_pack5;
    }

    public float getValor_pass() {
        return valor_pass;
    }

    public void setValor_pass(float valor_pass) {
        this.valor_pass = valor_pass;
    }
}
