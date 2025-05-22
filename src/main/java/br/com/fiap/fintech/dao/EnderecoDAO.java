package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Endereco;
import br.com.fiap.fintech.model.Usuario;

import java.util.List;

public interface EnderecoDAO {
    void cadastrar(Usuario usuario, Endereco endereco) throws DBException;
    void atualizar(Usuario usuario, Endereco endereco) throws DBException;
    Endereco buscar(int id);
}
