package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.MetaFinanceira;
import br.com.fiap.fintech.model.Usuario;

import java.util.List;

public interface MetaFinanceiraDAO {

    void cadastrar(Usuario usuario, MetaFinanceira metaFinanceira) throws DBException;
    void atualizar(Usuario usuario, MetaFinanceira metaFinanceira) throws DBException;
    void remover(Usuario usuario, int id) throws DBException;
    MetaFinanceira buscar(Usuario usuario, int id);
    List<MetaFinanceira> listar(Usuario usuario) throws DBException;
    double totalMeta(Usuario usuario) throws DBException;
}
