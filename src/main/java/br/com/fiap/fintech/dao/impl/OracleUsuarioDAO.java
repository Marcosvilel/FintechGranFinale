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
    public Usuario cadastrar(Usuario usuario) throws DBException {

        PreparedStatement ps = null;

        try {

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_usuario (NOME, E_MAIL, USER_NAME, SENHA, GENERO, CPF, TELEFONE, DATA_NASCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, to_date( ? , 'YYYY-MM-DD'))";

            ps = conexao.prepareStatement(sql, new String[]{"ID_USUARIO"});

            ps.setString(1, usuario.getName());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getUsername());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, usuario.getGenero());
            ps.setString(6, usuario.getCpf());
            ps.setString(7, usuario.getTelefone());
            ps.setString(8, usuario.getDataNascimento());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    usuario.setId(userId);
                } else {
                    throw new DBException("Failed to retrieve userId");
                }
            }

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
        return usuario;
    }

    @Override
    public void atualizar(Usuario usuario) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE T_USUARIO SET NOME = ?, CPF = ?, DATA_NASCIMENTO = ? WHERE ID_USUARIO = ?";

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
    public boolean validarUsuario(Usuario usuario) throws DBException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_usuario WHERE USER_NAME = ? AND SENHA = ?";

            ps = conexao.prepareStatement(sql, new String[]{"ID_USUARIO"});
            ps = conexao.prepareStatement(sql);

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());


            rs = ps.executeQuery();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    usuario.setId(userId);
                } else {
                    throw new DBException("Failed to retrieve userId");
                }
            }

            if (rs != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                conexao.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
