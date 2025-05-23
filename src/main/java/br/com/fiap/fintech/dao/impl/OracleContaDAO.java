package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.OracleConnectionManager;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleContaDAO implements ContaDAO {

    private Connection conexao;

    @Override
    public void cadastro(Usuario usuario, Conta conta) throws DBException {

        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO t_conta (ID_USUARIO, NUMERO_CONTA, AGENCIA, BANCO, TIPO_CONTA, SALDO_CONTA)  VALUES (?, ?, ?, ?, ?, ?)";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setInt(2, conta.getNumber());
            ps.setInt(3, conta.getAgency());
            ps.setInt(4, conta.getBank());
            ps.setString(5, conta.getType());
            ps.setDouble(6, conta.getBalance());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar conta", e);
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
    public void atualizarDados(Usuario usuario, Conta conta) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE t_conta SET NUMERO_CONTA = ?, AGENCIA = ?, BANCO = ?, TIPO_CONTA = ? WHERE ID_USUARIO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, conta.getNumber());
            ps.setInt(2, conta.getAgency());
            ps.setInt(3, conta.getBank());
            ps.setString(4, conta.getType());
            ps.setInt(5, usuario.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar conta", e);
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
    public void atualizarSaldo(Usuario usuario, Conta conta) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE t_conta SET SALDO_CONTA = ? WHERE ID_USUARIO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setDouble(1, conta.getBalance());
            ps.setInt(2, usuario.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar saldo", e);
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
    public Conta buscar(int id) {

        Conta conta = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_conta WHERE ID_USUARIO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);

            rs =  ps.executeQuery();

            if (rs.next()){
                int numConta = rs.getInt("NUMERO_CONTA");
                int agencia = rs.getInt("AGENCIA");
                int banco = rs.getInt("BANCO");
                String tipoConta = rs.getString("TIPO_CONTA");
                float saldo = rs.getFloat("SALDO_CONTA");

                conta = new Conta(numConta,agencia,banco,tipoConta,saldo);
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
        return conta;
    }

}
