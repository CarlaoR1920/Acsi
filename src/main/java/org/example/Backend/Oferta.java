package org.example.Backend;

public class Oferta {
    private int id_oferta;
    private int n_tokens;
    private String descricao;
    private boolean estado;

    public Oferta() {
    }

    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public int getN_tokens() {
        return n_tokens;
    }

    public void setN_tokens(int n_tokens) {
        this.n_tokens = n_tokens;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}