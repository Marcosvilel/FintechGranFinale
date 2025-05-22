package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.DadosPessoais;
import br.com.fiap.fintech.model.Usuario;

import java.util.List;

public interface DadosPessoaisDAO {

    void cadastrar(Usuario usuario, DadosPessoais dadosPessoais) throws DBException;
    void atualizar(Usuario usuario, DadosPessoais dadosPessoais) throws DBException;
    DadosPessoais buscar(int id);

}
