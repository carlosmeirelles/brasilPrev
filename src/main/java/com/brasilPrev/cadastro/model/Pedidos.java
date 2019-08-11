package com.brasilPrev.cadastro.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   idPedido;
    private String dtaPedido;
    private Long   idCliente;
    private String status;
    private String sessao;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getDtaPedido() {
        return dtaPedido;
    }

    public void setDtaPedido(String dtaPedido) {
        this.dtaPedido = dtaPedido;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

}
