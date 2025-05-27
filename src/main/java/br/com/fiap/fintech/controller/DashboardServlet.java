package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.Transacao;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;


@WebServlet("/dash")
public class DashboardServlet extends HttpServlet {

    private TransacaoDAO dao;
    private Usuario usuario;


    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = DAOFactory.getTransacaoDAO();
    }



    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        usuario = (Usuario) session.getAttribute("usuarioLogado");

        listar(req, resp);
    }


    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transacao> listaMes = dao.listarTransacaoMes(usuario);
        req.setAttribute("transacoesMes", listaMes);
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}
