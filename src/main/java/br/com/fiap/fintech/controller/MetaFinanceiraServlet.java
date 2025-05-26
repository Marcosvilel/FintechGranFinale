package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.MetaFinanceiraDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.MetaFinanceira;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/meta")
public class MetaFinanceiraServlet extends HttpServlet {
    private MetaFinanceiraDAO dao;
    private Usuario usuario;

    @Override
    public void init() throws ServletException {
        super.init();
        usuario = new Usuario(2, "JOAO_DIAS"); // Deve vir da sessão
        dao = DAOFactory.getMetaFinanceiraDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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