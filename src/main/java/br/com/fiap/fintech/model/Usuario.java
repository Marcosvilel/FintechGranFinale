package br.com.fiap.fintech.model;

public class Usuario extends br.com.fiap.fintech.model.Login {
    private br.com.fiap.fintech.model.Conta conta;
    private br.com.fiap.fintech.model.DadosPessoais dadosPessoais;
    private br.com.fiap.fintech.model.MetaFinanceira metaFinanceira;

    //Constructors
    public Usuario() {}
    public Usuario(String email, String username, String password) {
        super(email, username, password);
    }
    public Usuario(int id, String username) {
        super(id, username);
    }
    public Usuario(int id, String email, String username) { super(id, email, username); }
    public Usuario(int id, String email, String username, String password) { super(id, email, username, password); }
    public Usuario(String email, String username, String password, br.com.fiap.fintech.model.Conta conta, br.com.fiap.fintech.model.DadosPessoais dadosPessoais, br.com.fiap.fintech.model.MetaFinanceira metaFinanceira) {
        super(email, username, password);
        this.conta = conta;
        this.dadosPessoais = dadosPessoais;
        this.metaFinanceira = metaFinanceira;
    }
    public Usuario(String email, String username, String password, br.com.fiap.fintech.model.DadosPessoais dadosPessoais) {
        super(email, username, password);
        this.dadosPessoais = dadosPessoais;
    }




    //Getters and Setters
    public br.com.fiap.fintech.model.Conta getConta() {
        return conta;
    }

    public void setConta(br.com.fiap.fintech.model.Conta conta) {
        this.conta = conta;
    }

    public br.com.fiap.fintech.model.DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(br.com.fiap.fintech.model.DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public br.com.fiap.fintech.model.MetaFinanceira getMetaFinanceira() {
        return metaFinanceira;
    }

    public void setMetaFinanceira(br.com.fiap.fintech.model.MetaFinanceira metaFinanceira) {
        this.metaFinanceira = metaFinanceira;
    }

    public br.com.fiap.fintech.model.Login getLogin(br.com.fiap.fintech.model.Login login) {return login;}


}