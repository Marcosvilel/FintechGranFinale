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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "cadastro", value = "/cadastro")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO dao;
    private Usuario usuario;


    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DAOFactory.getUsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("usuario");
        String nome = req.getParameter("nomeCompleto");
        String genero = req.getParameter("genero");
        String dataNascimento = req.getParameter("dataNascimento");
        String cpf = req.getParameter("cpf");
        String telefone = req.getParameter("celular");
        String password = req.getParameter("senha");

        Usuario usuario = new Usuario(email, username, password, nome, genero, cpf, telefone, dataNascimento);

        try {
            dao.cadastrar(usuario);
            req.setAttribute("mensagem", "Usuario cadastrado com sucesso");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("error", "Erro ao cadastrar usuario");
            req.getRequestDispatcher("/cadastro_usuario.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        usuario = (Usuario) session.getAttribute("usuarioLogado");

        try {
            usuario = dao.buscarUsuarioName(usuario.getId());
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("usuario", usuario);

        req.getRequestDispatcher("perfil.jsp").forward(req, resp);

    }

}
