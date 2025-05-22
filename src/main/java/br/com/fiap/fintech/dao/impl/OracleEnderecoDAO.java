package br.com.fiap.fintech.dao.impl;

import br.com.fiap.fintech.dao.EnderecoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.OracleConnectionManager;
import br.com.fiap.fintech.model.Endereco;
import br.com.fiap.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleEnderecoDAO implements EnderecoDAO {
    
    private Connection conexao;

    @Override
    public void cadastrar(Usuario usuario, Endereco endereco) throws DBException {

        PreparedStatement ps = null;
        
        try {

            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO t_endereco (ID_USUARIO, CEP, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO)  VALUES (?,?,?,?,?,?,?,?)";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, usuario.getId());
            ps.setString(2, endereco.getCep());
            ps.setString(3, endereco.getLogradouro());
            ps.setInt(4, endereco.getNumero());
            ps.setString(5, endereco.getComplemento());
            ps.setString(6, endereco.getBairro());
            ps.setString(7, endereco.getCidade());
            ps.setString(8, endereco.getEstado());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar Endereço", e);
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
    public void atualizar(Usuario usuario, Endereco endereco) throws DBException {

        PreparedStatement ps = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "UPDATE t_endereco SET CEP = ?, LOGRADOURO = ?, NUMERO = ?, COMPLEMENTO = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ? WHERE ID_USUARIO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setString(1, endereco.getCep());
            ps.setString(2, endereco.getLogradouro());
            ps.setInt(3, endereco.getNumero());
            ps.setString(4, endereco.getComplemento());
            ps.setString(5, endereco.getBairro());
            ps.setString(6, endereco.getCidade());
            ps.setString(7, endereco.getEstado());
            ps.setInt(8, usuario.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao atualizar Endereco", e);
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
    public Endereco buscar(int id) {

        Endereco endereco = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = OracleConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM t_endereco WHERE ID_USUARIO = ?";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);

            rs =  ps.executeQuery();

            if (rs.next()){
                String cep = rs.getString("CEP");
                String rua = rs.getString("LOGRADOURO");
                int numero = rs.getInt("NUMERO");
                String complemento = rs.getString("COMPLEMENTO");
                String bairro = rs.getString("BAIRRO");
                String cidade = rs.getString("CIDADE");
                String estado = rs.getString("ESTADO");

                endereco = new Endereco(cep,rua,numero,complemento,bairro,cidade,estado);
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
        return endereco;
        
    }
}
