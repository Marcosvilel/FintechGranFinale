package br.com.fiap.fintech.factory;

public class DAOFactory {

    public static br.com.fiap.fintech.dao.ContaDAO getContaDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleContaDAO();
    }

    public static br.com.fiap.fintech.dao.DadosPessoaisDAO getDadosPessoaisDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleDadosPessoaisDAO();
    }

    public static br.com.fiap.fintech.dao.EnderecoDAO getEnderecoDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleEnderecoDAO();
    }

    public static br.com.fiap.fintech.dao.LoginDAO getLoginDAO() {
        return new br.com.fiap.fintech.dao.impl.OracleLoginDAO();
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
