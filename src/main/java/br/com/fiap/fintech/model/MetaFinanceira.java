package br.com.fiap.fintech.model;

import java.time.LocalDate;

public class MetaFinanceira {
    private int id;
    private String nome;
    private double valor;
    private LocalDate data;
    private String prioridade;

    public MetaFinanceira() {
    }

    public MetaFinanceira(int id) {
        this.id = id;
    }

    public MetaFinanceira(String nome, double valor, LocalDate data, String prioridade) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.prioridade = prioridade;
    }

    public MetaFinanceira(int id, String nome, double valor, LocalDate data, String prioridade) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.prioridade = prioridade;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}