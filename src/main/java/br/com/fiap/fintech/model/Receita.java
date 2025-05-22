package br.com.fiap.fintech.model;

public class Receita extends Transacao {

    private int id;
    private String descricao;

    //Constructors

    public Receita() {
    }
    public Receita(double valor, String data, String descricao) {
        super(valor, data);
        this.descricao = descricao;
    }
    public Receita(double valor, String data, int id, String descricao) {
        super(valor, data);
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
