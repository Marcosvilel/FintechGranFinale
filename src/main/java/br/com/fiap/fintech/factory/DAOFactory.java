package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

    public static br.com.fiap.fintech.dao.ContaDAO getContaDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleContaDAO();
    }

    public static UsuarioDAO getUsuarioDAO() {
        return new OracleUsuarioDAO();
    }

    public static br.com.fiap.fintech.dao.MetaFinanceiraDAO getMetaFinanceiraDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleMetaFinanceiraDAO();
    }

    public static br.com.fiap.fintech.dao.TransacaoDAO getTransacaoDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleTransacaoDAO();
    }

    public static br.com.fiap.fintech.dao.InvestimentoDAO getInvestimentoDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleInvestimentoDAO();
    }
}
