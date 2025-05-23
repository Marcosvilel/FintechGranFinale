package br.com.fiap.fintech.model;

public class Usuario {

    private int id;
    private String email;
    private String username;
    private String password;
    private String name;
    private String genero;
    private String cpf;
    private String telefone;
    private String dataNascimento;


    //Constructors
    public Usuario() {
    }

    public Usuario(int id, String nome) {
        this.email = nome;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Usuario(String email, String username, String password, String name, String genero, String cpf, String telefone, String dataNascimento) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.genero = genero;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;

    }

    public Usuario (int id, String email, String username, String password, String name, String genero, String cpf, String telefone, String dataNascimento) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.genero = genero;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }




    //Getters and Setters
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setId(int id) {};

    public int getId() {
        return id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}