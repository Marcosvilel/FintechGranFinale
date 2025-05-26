package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.MetaFinanceiraDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.OracleConnectionManager;
import br.com.fiap.fintech.model.MetaFinanceira;
import br.com.fiap.fintech.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleMetaFinanceiraDAO implements MetaFinanceiraDAO {

    Connection conexao;


    @Override
    public void cadastrar(Usuario usuario, MetaFinanceira metaFinanceira) throws DBException {

        PreparedStatement ps = null;

        try {

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_meta_financeira (ID_USUARIO, NOME_META, VALOR_META, DATA_META, PRIORIDADE_META)  VALUES (?,?,?,TO_DATE(?, 'YYYY-MM-DD'),?)";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, metaFinanceira.getNome());
            ps.setDouble(3, metaFinanceira.getValor());
            ps.setDate(4, Date.valueOf(metaFinanceira.getData()));
            ps.setString(5, metaFinanceira.getPrioridade());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar a Meta", e);
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
    public void atualizar(Usuario usuario, MetaFinanceira metaFinanceira) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE t_meta_financeira SET NOME_META = ?, VALOR_META = ?, DATA_META = to_date(?, 'yyyy-mm-dd'), PRIORIDADE_META = ? WHERE ID_USUARIO = ? and ID_META_FINANCEIRA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setString(1, metaFinanceira.getNome());
            ps.setDouble(2, metaFinanceira.getValor());
            ps.setDate(3, Date.valueOf(metaFinanceira.getData()));
            ps.setString(4, metaFinanceira.getPrioridade());
            ps.setInt(4, usuario.getId());
            ps.setInt(5, metaFinanceira.getId());
            ps.setString(6, metaFinanceira.getPrioridade());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar de Meta", e);
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
    public void remover(Usuario usuario, int id) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM t_meta_financeira WHERE ID_USUARIO = ? and ID_META_FINANCEIRA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao remover Meta", e);
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
    public MetaFinanceira buscar(Usuario usuario, int id) {

        MetaFinanceira metaFinanceira = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT ID_META_FINANCEIRA, ID_USUARIO, NOME_META, VALOR_META, TO_DATE(DATA_META, 'yy-MM-dd') AS DATA_META, PRIORIDADE_META FROM t_meta_financeira WHERE ID_USUARIO = ? and ID_META_FINANCEIRA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(2, id);

            rs =  ps.executeQuery();

            if (rs.next()){
                int idMeta = rs.getInt("ID_META_FINANCEIRA");
                String nome = rs.getString("NOME_META");
                double valor = rs.getDouble("VALOR_META");
                LocalDate data = rs.getDate("DATA_META").toLocalDate();
                String prioridade = rs.getString("PRIORIDADE_META");
                metaFinanceira = new MetaFinanceira(idMeta, nome, valor, data, prioridade);
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
        return metaFinanceira;
    }

    @Override
    public List<MetaFinanceira> listar(Usuario usuario) {

        List<MetaFinanceira> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT ID_META_FINANCEIRA, ID_USUARIO, NOME_META, VALOR_META, TO_DATE(DATA_META, 'yy-MM-dd') AS DATA_META, PRIORIDADE_META FROM t_meta_financeira where  ID_USUARIO = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            rs =  ps.executeQuery();

            while (rs.next()){
                int idMetaFinanceira = rs.getInt("ID_META_FINANCEIRA");
                String nome = rs.getString("NOME_META");
                double valor = rs.getDouble("VALOR_META");
                LocalDate data = rs.getDate("DATA_META").toLocalDate();
                String prioridade = rs.getString("PRIORIDADE_META");
                MetaFinanceira metaFinanceira = new MetaFinanceira(idMetaFinanceira, nome, valor, data, prioridade);
                lista.add(metaFinanceira);
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

    public double totalMeta(Usuario usuario) throws DBException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        double valor = 0;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "select sum(VALOR_META) as total_meta from t_meta_financeira where id_usuario = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());

            rs = ps.executeQuery();

            if (rs.next()) {
                valor = rs.getDouble("total_meta");
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
