package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Login;
import br.com.fiap.fintech.model.Usuario;

import java.util.List;

public interface LoginDAO {
    
    Login cadastrar(Usuario usuario) throws DBException;
    void atualizar(Usuario usuario) throws DBException;
    List<Login> listar();
    
}
