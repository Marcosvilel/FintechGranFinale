package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.MetaFinanceiraDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.MetaFinanceira;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/metaFinanceira")
public class MetaFinanceiraServlet extends HttpServlet {

    private MetaFinanceiraDAO dao;
    private Usuario usuario;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usuario = new Usuario(1, "Admin"); // Exemplo - você deve pegar do usuário logado
        dao = DAOFactory.getMetaFinanceiraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                try {
                    cadastrar(req, resp);
                    System.out.println("Cadastrado com sucesso! switch");
                } catch (DBException e) {
                    System.out.println("erro ao cadastrar switch" );
                    throw new RuntimeException(e);
                }
                break;
            case "alterar":
                try {
                    editar(req, resp);
                } catch (DBException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "excluir":
                try {
                    excluir(req, resp);
                } catch (DBException e) {
                    throw new RuntimeException(e);
                }
                break;
        }


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "pagina-meta-financeira":
                try {
                    paginaMeta(req, resp);
                } catch (DBException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "buscar-meta-financeira":
                buscarMeta(req, resp);
                break;
        }
    }






    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        try {
            String nome = req.getParameter("nome");
            double valor = Double.parseDouble(req.getParameter("valor"));
            LocalDate data = LocalDate.parse(req.getParameter("data"));
            String prioridade = req.getParameter("prioridade");

            MetaFinanceira metaFinanceira = new MetaFinanceira(nome, valor, data, prioridade);
            dao.cadastrar(usuario, metaFinanceira);
            req.setAttribute("mensagem", "Meta cadastrada com sucesso!");
            System.out.println("Meta cadastrada com sucesso!");

        } catch (NumberFormatException e) {
            req.setAttribute("erro", "Valor inválido: " + e.getMessage());
            System.out.println("erro: " + e.getMessage());
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao cadastrar meta: " + e.getMessage());
            System.out.println("Erro ao cadastrar meta: " + e.getMessage());
        }
        paginaMeta(req, resp);
    }



    private void editar(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, DBException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String nome = req.getParameter("nome");
            double valor = Double.parseDouble(req.getParameter("valor"));
            LocalDate data = LocalDate.parse(req.getParameter("data"));
            String prioridade = req.getParameter("prioridade");

            MetaFinanceira metaFinanceira = new MetaFinanceira(id, nome, valor, data, prioridade);
            dao.atualizar(usuario, metaFinanceira);
            req.setAttribute("mensagem", "Meta alterada com sucesso!");

        } catch (NumberFormatException e) {
            req.setAttribute("erro", "Valor inválido: " + e.getMessage());
        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao editar meta: " + e.getMessage());
        }
        paginaMeta(req, resp);
    }


    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));


            MetaFinanceira metaFinanceira = new MetaFinanceira(id);
            dao.remover(usuario, metaFinanceira);
            req.setAttribute("mensagem", "Meta excluida com sucesso!");



        } catch (NumberFormatException e) {
            req.setAttribute("erro", "Valor inválido: " + e.getMessage());
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao excluir meta: " + e.getMessage());
        }
        paginaMeta(req, resp);
    }



    private void buscarMeta(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        MetaFinanceira metaFinanceira = dao.buscar(usuario, id);
        req.setAttribute("meta", metaFinanceira);
        req.getRequestDispatcher("editar-meta.jsp").forward(req, resp);
    }

    private void paginaMeta(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, DBException {
        List<MetaFinanceira> lista = dao.listar(usuario);
        req.setAttribute("metas", lista);

        double totalMetas = 0;
        try {
            totalMetas = dao.totalMeta(usuario);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("totalMetas", totalMetas);


        req.getRequestDispatcher("metas.jsp").forward(req, resp);
    }
}
