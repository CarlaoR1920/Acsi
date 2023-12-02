package org.example.Backend;

import java.util.ArrayList;

public class Passageiro extends Utilizador {
    private int telemovel;
    private int nif;
    private String morada;
    private ArrayList<TituloDeTransporte> carteira_bilhetes;
    private ArrayList<RegistoOferta> ofertas_resgatadas;
    private ArrayList<Fatura> compras;
    private int tokens;

    public Passageiro() {
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public ArrayList<TituloDeTransporte> getCarteira_bilhetes() {
        return carteira_bilhetes;
    }

    public void setCarteira_bilhetes(ArrayList<TituloDeTransporte> carteira_bilhetes) {
        this.carteira_bilhetes = carteira_bilhetes;
    }

    public ArrayList<RegistoOferta> getOfertas_resgatadas() {
        return ofertas_resgatadas;
    }

    public void setOfertas_resgatadas(ArrayList<RegistoOferta> ofertas_resgatadas) {
        this.ofertas_resgatadas = ofertas_resgatadas;
    }

    public ArrayList<Fatura> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Fatura> compras) {
        this.compras = compras;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }
}
