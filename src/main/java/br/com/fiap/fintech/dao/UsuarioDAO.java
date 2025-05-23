package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Usuario;

public interface UsuarioDAO {

    void cadastrar(Usuario usuario) throws DBException;
    void atualizar(Usuario usuario) throws DBException;
    DadosPessoais buscar(int id);

}
