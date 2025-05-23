package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "cadastro", value = "/cadastro")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DAOFactory.getUsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String nome = req.getParameter("nomeCompleto");
        String genero = req.getParameter("genero");
        String dataNascimento = req.getParameter("dataNascimento");
        String cpf = req.getParameter("cpf");
        String telefone = req.getParameter("telefone");
        String password = req.getParameter("senha");

        Usuario usuario = new Usuario(email, username, password, nome, genero, cpf, telefone, dataNascimento);

        try {
            dao.cadastrar(usuario);
            req.setAttribute("mensagem", "Usuario cadastrado com sucesso");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar usuario");
        }

        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}
