package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.OracleConnectionManager;
import br.com.fiap.fintech.model.*;
import br.com.fiap.fintech.model.Receita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleTransacaoDAO implements TransacaoDAO {

    Connection conexao;


    @Override
    public void cadastrarDespesa(Usuario usuario, Despesa despesa) throws DBException {

        PreparedStatement ps = null;

        try {

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_despesa (ID_USUARIO, NOME_DESPESA,VALOR_DESPESA,DATA_DESPESA)  VALUES (?,?,?,?)";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, despesa.getDescricao());
            ps.setDouble(3, despesa.getValor());
            ps.setString(4, despesa.getData());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar a despesa", e);
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
    public void atualizarDespesa(Usuario usuario, Despesa despesa) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE t_despesa SET NOME_DESPESA = ?, VALOR_DESPESA = ?, DATA_DESPESA = ? WHERE ID_USUARIO = ? and ID_DESPESA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setString(1, despesa.getDescricao());
            ps.setDouble(2, despesa.getValor());
            ps.setString(3, despesa.getData());
            ps.setInt(4, usuario.getId());
            ps.setInt(5, despesa.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar de despesa", e);
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
    public void removerDespesa(Usuario usuario, Despesa despesa) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM t_despesa WHERE ID_USUARIO = ? and ID_DESPESA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(2, despesa.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao remover despesa", e);
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
    public Despesa buscarDespesa(Usuario usuario, int id) {

        Despesa despesa = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_despesa WHERE ID_USUARIO = ? and ID_DESPESA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(1, id);

            rs =  ps.executeQuery();

            if (rs.next()){
                String nome = rs.getString("NOME_DESPESA");
                double valor = rs.getDouble("VALOR_DESPESA");
                String data = rs.getString("DATA_DESPESA");

                despesa = new Despesa(valor,data,nome);
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
        return despesa;
    }

    @Override
    public List<Despesa> listarDespesas() {

        List<Despesa> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_despesa";
            ps = conexao.prepareStatement(sql);
            rs =  ps.executeQuery();

            while (rs.next()){
                String nome = rs.getString("NOME_DESPESA");
                double valor = rs.getDouble("VALOR_DESPESA");
                String data = rs.getString("DATA_DESPESA");
                Despesa despesa = new Despesa(valor,data,nome);
                lista.add(despesa);
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



    @Override
    public void cadastrarReceita(Usuario usuario, Receita receita) throws DBException {

        PreparedStatement ps = null;

        try {

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_receita (ID_USUARIO, NOME_RECEITA,VALOR_RECEITA,DATA_RECEITA)  VALUES (?,?,?,?)";
            
            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, receita.getDescricao());
            ps.setDouble(3, receita.getValor());
            ps.setString(4, receita.getData());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar a receita", e);
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
    public void atualizarReceita(Usuario usuario, Receita receita) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE t_receita SET NOME_RECEITA = ?, VALOR_RECEITA = ?, DATA_RECEITA = ? WHERE ID_USUARIO = ? and ID_RECEITA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setString(1, receita.getDescricao());
            ps.setDouble(2, receita.getValor());
            ps.setString(3, receita.getData());
            ps.setInt(4, usuario.getId());
            ps.setInt(5, receita.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar de receita", e);
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
    public void removerReceita(Usuario usuario, Receita receita) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM t_receita WHERE ID_USUARIO = ? and ID_RECEITA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(2, receita.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao remover receita", e);
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
    public Receita buscarReceita(Usuario usuario, int id) {

        Receita receita = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_receita WHERE ID_USUARIO = ? and ID_RECEITA = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(1, id);

            rs =  ps.executeQuery();

            if (rs.next()){
                String nome = rs.getString("NOME_RECEITA");
                double valor = rs.getDouble("VALOR_RECEITA");
                String data = rs.getString("DATA_RECEITA");

                receita = new Receita(valor,data,nome);
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
        return receita;
    }

    @Override
    public List<Receita> listarReceitas() {

        List<Receita> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_receita";
            ps = conexao.prepareStatement(sql);
            rs =  ps.executeQuery();

            while (rs.next()){
                String nome = rs.getString("NOME_RECEITA");
                double valor = rs.getDouble("VALOR_RECEITA");
                String data = rs.getString("DATA_RECEITA");
                Receita receita = new Receita(valor,data,nome);
                lista.add(receita);
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
