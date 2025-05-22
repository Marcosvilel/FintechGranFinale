package br.com.fiap.fintech.model;

public abstract class Transacao {

    protected double valor;
    protected String data;

    //Constructors
    public Transacao() {
    }

    public Transacao(double valor, String data) {
        this.valor = valor;
        this.data = data;
    }

    //Getters and Setters

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
