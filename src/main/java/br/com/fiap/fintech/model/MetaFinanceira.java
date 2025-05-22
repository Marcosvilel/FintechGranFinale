package br.com.fiap.fintech.model;

public class MetaFinanceira {
    private int id;
    private String nome;
    private double valor;
    private String data;

    public MetaFinanceira() {
    }

    public MetaFinanceira(String nome, double valor, String data) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    public MetaFinanceira(int id, String nome, double valor, String data) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}