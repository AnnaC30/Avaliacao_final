package com.restaurante.pedido.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pratoId;

    private Integer quantidade;

    private Double precoTotal;

    public Pedido() {
    }

    public Pedido(Long pratoId, Integer quantidade, Double precoTotal) {
        this.pratoId = pratoId;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }

    public Long getId() {
        return id;
    }

    public Long getPratoId() {
        return pratoId;
    }

    public void setPratoId(Long pratoId) {
        this.pratoId = pratoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }
}
