package org.example.Backend;

public class Pagamento {
    private double valor;
    private boolean estado;

    public Pagamento(double valor, boolean estado) {
        this.valor = valor;
        this.estado = estado;
    }

    public Pagamento() {
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

