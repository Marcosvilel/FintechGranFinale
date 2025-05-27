package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.OracleConnectionManager;
import br.com.fiap.fintech.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OracleTransacaoDAO implements TransacaoDAO {

    Connection conexao;


    @Override
    public void cadastrarTransacao(Usuario usuario, Transacao transacao) throws DBException {

        PreparedStatement ps = null;

        try {

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_transacao (ID_USUARIO,TIPO_TRANSACAO,DESCRICAO_TRANSACAO,CATEGORIA_TRANSACAO,VALOR_TRANSACAO,DATA_TRANSACAO)  VALUES (?,?,?,?,?,TO_DATE(?,'yyyy-MM-dd'))";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, transacao.getTipo());
            ps.setString(3, transacao.getDescricao());
            ps.setString(4, transacao.getCategoria());
            ps.setDouble(5, transacao.getValor());
            ps.setDate(6, Date.valueOf(transacao.getData()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar a transacao", e);
        } finally {
            try {
                ps.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void atualizarTransacao(Usuario usuario, Transacao transacao) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE t_transacao SET TIPO_TRANSACAO = ?,DESCRICAO_TRANSACAO = ?,CATEGORIA_TRANSACAO = ?,VALOR_TRANSACAO = ?, DATA_TRANSACAO = TO_DATE(?,'yyyy-MM-dd') WHERE ID_USUARIO = ? and ID_TRANSACAO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setString(1, transacao.getTipo());
            ps.setString(2, transacao.getDescricao());
            ps.setString(3, transacao.getCategoria());
            ps.setDouble(4, transacao.getValor());
            ps.setDate(5, Date.valueOf(transacao.getData()));
            ps.setInt(6, usuario.getId());
            ps.setInt(7, transacao.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar de transacao", e);
        } finally {
            try {
                ps.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removerTransacao(Usuario usuario, Transacao transacao) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM t_transacao WHERE ID_USUARIO = ? and ID_TRANSACAO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(2, transacao.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao remover transacao", e);
        } finally {
            try {
                ps.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Transacao buscarTransacao(Usuario usuario, int id) {

        Transacao transacao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT TIPO_TRANSACAO, DESCRICAO_TRANSACAO, CATEGORIA_TRANSACAO, VALOR_TRANSACAO, to_date(DATA_TRANSACAO, 'yy-MM-dd') as DATA_TRANSACAO FROM t_transacao WHERE ID_USUARIO = ? and ID_TRANSACAO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(2, id);

            rs =  ps.executeQuery();

            if (rs.next()){
                String tipo = rs.getString("TIPO_TRANSACAO");
                String descricao = rs.getString("DESCRICAO_TRANSACAO");
                String categoria = rs.getString("CATEGORIA_TRANSACAO");
                double valor = rs.getDouble("VALOR_TRANSACAO");
                LocalDate data = rs.getDate("DATA_TRANSACAO").toLocalDate();

                transacao = new Transacao(id, tipo, descricao, categoria, valor, data);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return transacao;
    }

    @Override
    public List<Transacao> listarTransacao(Usuario usuario) {

        List<Transacao> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT ID_TRANSACAO, TIPO_TRANSACAO, DESCRICAO_TRANSACAO, CATEGORIA_TRANSACAO, VALOR_TRANSACAO, to_date(DATA_TRANSACAO, 'yy-mm-dd') as DATA_TRANSACAO FROM t_transacao WHERE ID_USUARIO = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            rs =  ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt("ID_TRANSACAO");
                String tipo = rs.getString("TIPO_TRANSACAO");
                String descricao = rs.getString("DESCRICAO_TRANSACAO");
                String categoria = rs.getString("CATEGORIA_TRANSACAO");
                double valor = rs.getDouble("VALOR_TRANSACAO");
                LocalDate data = rs.getDate("DATA_TRANSACAO").toLocalDate();
                Transacao transacao = new Transacao(id, tipo, descricao, categoria, valor, data);
                lista.add(transacao);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }




    public List<Transacao> listarTransacaoMes(Usuario usuario) {

        List<Transacao> listaMes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT ID_TRANSACAO, TIPO_TRANSACAO, DESCRICAO_TRANSACAO, CATEGORIA_TRANSACAO, VALOR_TRANSACAO, to_date(DATA_TRANSACAO, 'yy-mm-dd') as DATA_TRANSACAO, to_char(TO_DATE(DATA_TRANSACAO, 'yy-mm-dd'),'MM-YYYY'), to_char(SYSDATE,'MM YYYY') " +
                    "FROM t_transacao " +
                    "WHERE ID_USUARIO = ? " +
                    "AND to_char(TO_DATE(DATA_TRANSACAO, 'yy-mm-dd'),'MM YYYY') = to_char(SYSDATE,'MM YYYY') " +
                    "order by DATA_TRANSACAO desc, id_transacao desc";

            ps = conexao.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            rs =  ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt("ID_TRANSACAO");
                String tipo = rs.getString("TIPO_TRANSACAO");
                String descricao = rs.getString("DESCRICAO_TRANSACAO");
                String categoria = rs.getString("CATEGORIA_TRANSACAO");
                double valor = rs.getDouble("VALOR_TRANSACAO");
                LocalDate data = rs.getDate("DATA_TRANSACAO").toLocalDate();
                Transacao transacao = new Transacao(id, tipo, descricao, categoria, valor, data);
                listaMes.add(transacao);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaMes;
    }







    public double totalIncome(Usuario usuario) throws DBException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String income = "income";
        double valor = 0;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "select sum(valor_transacao) as total_income from t_transacao where id_usuario = ? and tipo_transacao = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, income);

            rs = ps.executeQuery();

            if (rs.next()) {
                valor = rs.getDouble("TOTAL_INCOME");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return valor;
    }


    public double totalExpense(Usuario usuario) throws DBException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String expense = "expense";
        double valor = 0;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "select sum(valor_transacao) as total_expense from t_transacao where id_usuario = ? and tipo_transacao = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, expense);

            rs = ps.executeQuery();

            if (rs.next()) {
                valor = rs.getDouble("TOTAL_EXPENSE");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return valor;
    }


}
