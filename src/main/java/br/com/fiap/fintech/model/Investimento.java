package br.com.fiap.fintech.model;

public class Investimento {

    private int id;
    private String tipo;
    private String nome;
    private double valor;
    private String data;

    public Investimento() {
    }

    public Investimento(String tipo, String nome, double valor, String data) {
        this.tipo = tipo;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
    }

    public Investimento(int id, String tipo, String nome, double valor, String data) {
        this.id = id;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
