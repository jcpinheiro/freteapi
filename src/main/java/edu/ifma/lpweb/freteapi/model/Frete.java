package edu.ifma.lpweb.freteapi.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

     private String descricao;
    private Float peso;
    private BigDecimal valor;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Cidade cidade;

    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getDescricao() {
        return this.descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getPeso() {
        return this.peso;
    }


    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public BigDecimal calcularFrete() {
        // R$10,00 é o valor fixo para o cálculo
        this.valor = new BigDecimal(this.peso * 10).add(this.cidade.getTaxa() );
        return this.getValor();

    }

}