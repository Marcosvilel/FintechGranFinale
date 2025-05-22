package br.com.fiap.fintech.model;

public class DadosPessoais {
    private String name;
    private String cpf;
    private String dataNascimento;
    private Endereco endereco;

    public DadosPessoais() {
    }

    public DadosPessoais(String name, String cpf, String dataNascimento) {
        this.name = name;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public DadosPessoais(String name, String cpf, String dataNascimento, Endereco endereco) {
        this.name = name;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
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
