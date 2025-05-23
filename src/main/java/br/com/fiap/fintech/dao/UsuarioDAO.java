package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Usuario;

public interface UsuarioDAO {

    Usuario cadastrar(Usuario usuario) throws DBException;
    void atualizar(Usuario usuario) throws DBException;
    Usuario login(Usuario usuario) throws DBException;

}
