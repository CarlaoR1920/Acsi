package org.example.Backend;

public class TituloDeTransporte {
    private int id_titulo;
    private Rota rota;

    public TituloDeTransporte() {
    }

    public int getId_titulo() {
        return id_titulo;
    }

    public void setId_titulo(int id_titulo) {
        this.id_titulo = id_titulo;
    }

    public Rota getRota() {
        return rota;
    }

    public void setRota(Rota rota) {
        this.rota = rota;
    }
}
