package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;


public interface ContaDAO {

    void cadastro(Usuario usuario, Conta conta) throws DBException;
    void atualizarDados(Usuario usuario, Conta conta) throws DBException;
    void atualizarSaldo(Usuario usuario, Conta conta) throws DBException;
    Conta buscar(int id) throws DBException;

}