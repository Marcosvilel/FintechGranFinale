package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Investimento;
import br.com.fiap.fintech.model.Usuario;

import java.util.List;

public interface InvestimentoDAO {
        void cadastrar(Usuario usuario, Investimento investimento) throws DBException;
        void atualizar(Usuario usuario, Investimento investimento) throws DBException;
        void remover(Usuario usuario, Investimento investimento) throws DBException;
        Investimento buscar(Usuario usuario, int id);
        List<Investimento> listar();
}
