package br.com.fiap.fintech.controller;


import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO dao;
    public LoginServlet() {
        dao = DAOFactory.getUsuarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("usuario");
        String senha = req.getParameter("senha");
        Usuario usuario = new Usuario(username, senha);

        try {
            if (dao.validarUsuario(usuario)) {
                Usuario sessaoUsuario = dao.buscarUsuario(username);

                HttpSession oldSession = req.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                HttpSession newSession = req.getSession(true);
                newSession.setAttribute("usuarioLogado", sessaoUsuario);
                newSession.setMaxInactiveInterval(30 * 60);
                resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");


//                HttpSession session = req.getSession();
//                session.setAttribute("usuario", sessaoUsuario);
//                req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Usuario ou senha invalidos");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } catch (DBException e) {
            req.setAttribute("error", "Erro no sistema. Tente novamente.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
