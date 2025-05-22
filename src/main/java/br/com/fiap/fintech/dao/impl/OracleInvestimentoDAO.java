package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.OracleConnectionManager;
import br.com.fiap.fintech.model.Investimento;
import br.com.fiap.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleInvestimentoDAO implements InvestimentoDAO {

    Connection conexao;


    @Override
    public void cadastrar(Usuario usuario, Investimento investimento) throws DBException {

        PreparedStatement ps = null;

        try {

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_investimento (ID_USUARIO, TIPO_INVESTIMENTO, NOME_INVESTIMENTO, VALOR_INVESTIMENTO, DATA_INVESTIMENTO)  VALUES (?,?,?,?,?)";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, investimento.getTipo());
            ps.setString(3, investimento.getNome());
            ps.setDouble(4, investimento.getValor());
            ps.setString(5, investimento.getData());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar o Investimento", e);
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
    public void atualizar(Usuario usuario, Investimento investimento) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE t_investimento SET TIPO_INVESTIMENTO = ?, NOME_INVESTIMENTO = ?, VALOR_INVESTIMENTO = ?, DATA_INVESTIMENTO = ? WHERE ID_USUARIO = ? and ID_INVESTIMENTO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setString(1, investimento.getNome());
            ps.setString(2, investimento.getTipo());
            ps.setDouble(3, investimento.getValor());
            ps.setString(4, investimento.getData());
            ps.setInt(5, usuario.getId());
            ps.setInt(6, investimento.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar Investimento", e);
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
    public void remover(Usuario usuario, Investimento investimento) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM t_investimento WHERE ID_USUARIO = ? and ID_INVESTIMENTO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(2, investimento.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao remover Investimento", e);
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
    public Investimento buscar(Usuario usuario, int id) {

        Investimento investimento = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_investimento WHERE ID_USUARIO = ? and ID_INVESTIMENTO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(1, id);

            rs =  ps.executeQuery();

            if (rs.next()){
                String tipo = rs.getString("TIPO_INVESTIMENTO");
                String nome = rs.getString("NOME_INVESTIMENTO");
                double valor = rs.getDouble("VALOR_INVESTIMENTO");
                String data = rs.getString("DATA_INVESTIMENTO");

                investimento = new Investimento(tipo,nome,valor,data);
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
        return investimento;
    }

    @Override
    public List<Investimento> listar() {

        List<Investimento> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_investimento";
            ps = conexao.prepareStatement(sql);
            rs =  ps.executeQuery();

            while (rs.next()){
                String tipo = rs.getString("TIPO_INVESTIMENTO");
                String nome = rs.getString("NOME_INVESTIMENTO");
                double valor = rs.getDouble("VALOR_INVESTIMENTO");
                String data = rs.getString("DATA_INVESTIMENTO");
                Investimento investimento = new Investimento(tipo,nome,valor,data);
                lista.add(investimento);
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
