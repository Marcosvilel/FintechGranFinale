package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Receita;
import br.com.fiap.fintech.model.Despesa;
import br.com.fiap.fintech.model.Usuario;

import java.util.List;

public interface TransacaoDAO {

    void cadastrarDespesa(Usuario usuario, Despesa despesa) throws DBException;
    void atualizarDespesa(Usuario usuario, Despesa despesa) throws DBException;
    void removerDespesa(Usuario usuario, Despesa despesa) throws DBException;
    Despesa buscarDespesa(Usuario usuario, int id);
    List<Despesa> listarDespesas();


    void cadastrarReceita(Usuario usuario, Receita receita) throws DBException;
    void atualizarReceita(Usuario usuario, Receita receita) throws DBException;
    void removerReceita(Usuario usuario, Receita receita) throws DBException;
    Receita buscarReceita(Usuario usuario, int id);
    List<Receita> listarReceitas();
    
}
