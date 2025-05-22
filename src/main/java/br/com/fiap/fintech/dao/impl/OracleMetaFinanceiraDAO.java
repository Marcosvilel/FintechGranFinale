package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.MetaFinanceiraDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.OracleConnectionManager;
import br.com.fiap.fintech.model.MetaFinanceira;
import br.com.fiap.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleMetaFinanceiraDAO implements MetaFinanceiraDAO {

    Connection conexao;


    @Override
    public void cadastrar(Usuario usuario, MetaFinanceira metaFinanceira) throws DBException {

        PreparedStatement ps = null;

        try {

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_meta_financeira (ID_USUARIO, NOME_META, VALOR_META, DATA_META)  VALUES (?,?,?,?)";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, metaFinanceira.getNome());
            ps.setDouble(3, metaFinanceira.getValor());
            ps.setString(4, metaFinanceira.getData());

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

            String sql = "UPDATE t_meta_financeira SET NOME_META = ?, VALOR_META = ?, DATA_META = ? WHERE ID_USUARIO = ? and ID_META_FINANCEIRA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setString(1, metaFinanceira.getNome());
            ps.setDouble(2, metaFinanceira.getValor());
            ps.setString(3, metaFinanceira.getData());
            ps.setInt(4, usuario.getId());
            ps.setInt(5, metaFinanceira.getId());

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
    public void remover(Usuario usuario, MetaFinanceira metaFinanceira) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM t_meta_financeira WHERE ID_USUARIO = ? and ID_META_FINANCEIRA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(2, metaFinanceira.getId());

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

            String sql = "SELECT * FROM t_meta_financeira WHERE ID_USUARIO = ? and ID_META_FINANCEIRA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(1, id);

            rs =  ps.executeQuery();

            if (rs.next()){
                String nome = rs.getString("NOME_META");
                double valor = rs.getDouble("VALOR_META");
                String data = rs.getString("DATA_META");

                metaFinanceira = new MetaFinanceira(nome,valor,data);
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
    public List<MetaFinanceira> listar() {

        List<MetaFinanceira> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_meta_financeira";
            ps = conexao.prepareStatement(sql);
            rs =  ps.executeQuery();

            while (rs.next()){
                String nome = rs.getString("NOME_META");
                double valor = rs.getDouble("VALOR_META");
                String data = rs.getString("DATA_META");
                MetaFinanceira metaFinanceira = new MetaFinanceira(nome,valor,data);
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
}
