package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class Transacao {

    private int id;
    private String tipo;
    private String descricao;
    private String categoria;
    private double valor;
    private LocalDate data;


    public Transacao() {
    }

    public Transacao(int id) {
        this.id = id;
    }

    public Transacao(String tipo, String descricao, String categoria, double valor, LocalDate data) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
    }

    public Transacao(int id, String tipo, String descricao, String categoria, double valor, LocalDate data) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
