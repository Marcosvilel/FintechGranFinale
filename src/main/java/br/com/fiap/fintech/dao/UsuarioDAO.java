package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Usuario;

public interface UsuarioDAO {

    Usuario cadastrar(Usuario usuario) throws DBException;
    void atualizar(Usuario usuario) throws DBException;
    public Usuario buscarUsuario(String username) throws DBException;
    boolean validarUsuario(Usuario usuario) throws DBException;

}
