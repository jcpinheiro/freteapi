package edu.ifma.lpweb.freteapi.repository.filter;

import edu.ifma.lpweb.freteapi.model.Cidade;
import edu.ifma.lpweb.freteapi.model.Cliente;

import java.math.BigDecimal;

public class FreteFiltro {

    private Integer clienteId;
    private Integer cidadeId;

    private String descricao;

    private Float pesoDe;
    private Float pesoAte;

    private BigDecimal valorDe;
    private BigDecimal valorAte;

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPesoDe() {
        return pesoDe;
    }

    public void setPesoDe(Float pesoDe) {
        this.pesoDe = pesoDe;
    }

    public BigDecimal getValorDe() {
        return valorDe;
    }

    public void setValorDe(BigDecimal valorDe) {
        this.valorDe = valorDe;
    }

    public BigDecimal getValorAte() {
        return valorAte;
    }

    public void setValorAte(BigDecimal valorAte) {
        this.valorAte = valorAte;
    }

    public Float getPesoAte() {
        return pesoAte;
    }

    public void setPesoAte(Float pesoAte) {
        this.pesoAte = pesoAte;
    }
}