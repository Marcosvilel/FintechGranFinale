package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.MetaFinanceiraDAO;
import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.MetaFinanceira;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/meta")
public class MetaFinanceiraServlet extends HttpServlet {
    private MetaFinanceiraDAO dao;
    private TransacaoDAO transacaoDAO;
    private Usuario usuario;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = DAOFactory.getMetaFinanceiraDAO();
        transacaoDAO =  DAOFactory.getTransacaoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        usuario = (Usuario) session.getAttribute("usuarioLogado");


        String acao = req.getParameter("acao");

        switch (acao) {
            case "listar":
                listarMetas(req, resp);
                break;
            case "abrir-edicao":
                abrirEdicao(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        usuario = (Usuario) session.getAttribute("usuarioLogado");


        String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
                break;
        }
    }

    private void listarMetas(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MetaFinanceira> metas = null;
        try {
            metas = dao.listar(usuario);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("metas", metas);


        double totalMetas = 0;
        try {
            totalMetas = dao.totalMeta(usuario);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("totalMetas", totalMetas);


        double income = 0;
        try {
            income = transacaoDAO.totalIncome(usuario);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        double expense = 0;
        try {
            expense = transacaoDAO.totalExpense(usuario);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        double totalAlcancado = income - expense;

        req.setAttribute("totalAlcancado", totalAlcancado);


        double totalFaltam = totalMetas - totalAlcancado;
        if(totalFaltam < 0) {
            totalFaltam = 0;
        }

        req.setAttribute("totalFaltam", totalFaltam);

        req.getRequestDispatcher("metas.jsp").forward(req, resp);
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nome = req.getParameter("nome");
            double valor = Double.parseDouble(req.getParameter("valor"));
            LocalDate data = LocalDate.parse(req.getParameter("data"));
            String prioridade = req.getParameter("prioridade");

            MetaFinanceira metaFinanceira = new MetaFinanceira(nome,valor,data,prioridade);
            dao.cadastrar(usuario, metaFinanceira);

            req.setAttribute("mensagem", "Meta cadastrada com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar meta");
        } catch (NumberFormatException e) {
            req.setAttribute("erro", "Valor inválido");
        }

        listarMetas(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nome = req.getParameter("nome");
            double valor = Double.parseDouble(req.getParameter("valor"));
            LocalDate data = LocalDate.parse(req.getParameter("data"));
            String prioridade = req.getParameter("prioridade");

            MetaFinanceira metaFinanceira = new MetaFinanceira(id,nome,valor,data,prioridade);
            dao.atualizar(usuario, metaFinanceira);

            req.setAttribute("mensagem", "Meta atualizada com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar meta");
        } catch (NumberFormatException e) {
            req.setAttribute("erro", "Valor inválido");
        }

        listarMetas(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.remover(usuario, id);

            req.setAttribute("mensagem", "Meta removida com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao remover meta");
        }

        listarMetas(req, resp);
    }



    private void abrirEdicao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        MetaFinanceira meta = dao.buscar(usuario, id);
        req.setAttribute("meta", meta);
        req.getRequestDispatcher("editar-meta.jsp").forward(req, resp);
    }
}