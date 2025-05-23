package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.OracleConnectionManager;
import br.com.fiap.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleUsuarioDAO implements UsuarioDAO {

    private Connection conexao;

    @Override
    public void cadastrar(Usuario usuario) throws DBException {

        PreparedStatement ps = null;

        try {

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_usuario (ID_USUARIO, NOME, E_MAIL, USER_NAME, SENHA, CPF, DATA_NASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, to_date( ? , 'dd-mm-yyyy'))";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getName());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getUsername());
            ps.setString(5, usuario.getCpf());
            ps.setString(6, usuario.getDataNascimento());
            ps.setString(7, usuario.getCpf());
            ps.setString(3, usuario.getDataNascimento());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar Dados Pessoais", e);
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
    public void atualizar(Usuario usuario, DadosPessoais dadosPessoais) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE t_dados_pessoais SET NOME = ?, CPF = ?, DATA_NASCIMENTO = ? WHERE ID_USUARIO = ?";

            ps = conexao.prepareStatement(sql);



            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar Dados Pessoais", e);
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
    public void atualizar(Usuario usuario) throws DBException {

    }

    @Override
    public DadosPessoais buscar(int id) {
        DadosPessoais dadosPessoais = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_dados_pessoais WHERE ID_USUARIO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NOME");
                String cpf = rs.getString("CPF");
                String dtNasc = rs.getString("DATA_NASCIMENTO");

                dadosPessoais = new DadosPessoais(nome, cpf, dtNasc);
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
        return dadosPessoais;
    }
}
