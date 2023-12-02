package org.example.Backend;

import java.sql.Date;

public class RegistoOferta {
    private int id_oferta;
    private Date data_aquisicao;
    private int tokens_pagos;
    private boolean estado;
    private String codigo_oferta;
    private Date validade;

    public RegistoOferta() {
    }

    public int getId_oferta() {
        return id_oferta;
    }

    public void setId_oferta(int id_oferta) {
        this.id_oferta = id_oferta;
    }

    public Date getData_aquisicao() {
        return data_aquisicao;
    }

    public void setData_aquisicao(Date data_aquisicao) {
        this.data_aquisicao = data_aquisicao;
    }

    public int getTokens_pagos() {
        return tokens_pagos;
    }

    public void setTokens_pagos(int tokens_pagos) {
        this.tokens_pagos = tokens_pagos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCodigo_oferta() {
        return codigo_oferta;
    }

    public void setCodigo_oferta(String codigo_oferta) {
        this.codigo_oferta = codigo_oferta;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }
}
