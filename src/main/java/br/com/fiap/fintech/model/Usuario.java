package br.com.fiap.fintech.model;

public class Usuario {
    private br.com.fiap.fintech.model.Conta conta;
    private br.com.fiap.fintech.model.MetaFinanceira metaFinanceira;

    private int id;
    private String email;
    private String username;
    private String password;
    private String name;
    private String cpf;
    private String dataNascimento;
    private Endereco endereco;

    //Constructors
    public Usuario() {
    }

    public Usuario(Conta conta, MetaFinanceira metaFinanceira, int id, String email, String username, String password, String name, String cpf, String dataNascimento, Endereco endereco) {
        this.conta = conta;
        this.metaFinanceira = metaFinanceira;
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public Usuario(String email, String username, String password, br.com.fiap.fintech.model.Conta conta, br.com.fiap.fintech.model.MetaFinanceira metaFinanceira) {
        super();
        this.conta = conta;
        this.metaFinanceira = metaFinanceira;
    }

    //Getters and Setters
    public br.com.fiap.fintech.model.Conta getConta() {
        return conta;
    }

    public void setConta(br.com.fiap.fintech.model.Conta conta) {
        this.conta = conta;
    }

    public br.com.fiap.fintech.model.MetaFinanceira getMetaFinanceira() {
        return metaFinanceira;
    }

    public void setMetaFinanceira(br.com.fiap.fintech.model.MetaFinanceira metaFinanceira) {
        this.metaFinanceira = metaFinanceira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}