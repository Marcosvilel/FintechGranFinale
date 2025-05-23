package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.OracleConnectionManager;
import br.com.fiap.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleLoginDAO implements LoginDAO {

    private Connection conexao;

    @Override
    public Login cadastrar(Usuario usuario) throws DBException {
        PreparedStatement ps = null;

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_usuario (e_mail, user_name, senha) VALUES (?, ?, ?)";

        try {
            ps = conexao.prepareStatement(sql, new String[]{"ID_USUARIO"});

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPassword());

            ps.executeUpdate();

            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    int userId = generatedKeys.getInt(1);
                    usuario.setId(userId);
                } else {
                    throw new DBException("Failed to retrieve userId");
                }
            }

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar Login", e);
        }
        finally {
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

            String sql = "UPDATE t_usuario SET e_mail = ?, user_name = ?, senha = ? WHERE ID_USUARIO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPassword());
            ps.setInt(4, usuario.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar Login", e);
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
    public List<Login> listar() {

        List<Login> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_login";
            ps = conexao.prepareStatement(sql);
            rs =  ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id_usuario");
                String email = rs.getString("e_mail");
                String userName = rs.getString("user_name");
                Usuario usuario = new Usuario(id,email,userName);
                lista.add(usuario);
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
