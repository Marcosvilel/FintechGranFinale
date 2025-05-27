package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {


    public static UsuarioDAO getUsuarioDAO() {
        return new OracleUsuarioDAO();
    }

    public static br.com.fiap.fintech.dao.MetaFinanceiraDAO getMetaFinanceiraDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleMetaFinanceiraDAO();
    }

    public static br.com.fiap.fintech.dao.TransacaoDAO getTransacaoDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleTransacaoDAO();
    }

}
