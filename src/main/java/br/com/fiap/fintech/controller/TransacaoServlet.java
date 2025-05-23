package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.Transacao;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/transacao")
public class TransacaoServlet extends HttpServlet {
    private TransacaoDAO dao;
    private Usuario usuario;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usuario = new Usuario(2, "JOAO_DIAS"); // Exemplo - você deve pegar do usuário logado
        dao = DAOFactory.getTransacaoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "alterar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
                break;
        }


    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "pagina-transacao":
                paginaTransacao(req, resp);
                break;
            case "buscar-transacao":
                buscarTransacao(req, resp);
                break;
        }
    }






    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String tipo = req.getParameter("tipo");
            double valor = Double.parseDouble(req.getParameter("valor"));
            String descricao = req.getParameter("descricao");
            String categoria = req.getParameter("categoria");
            String data = req.getParameter("data");


            Transacao transacao = new Transacao(tipo, descricao, categoria, valor, data);
            dao.cadastrarTransacao(usuario, transacao);
            req.setAttribute("mensagem", "Entrada cadastrada com sucesso!");



        } catch (NumberFormatException e) {
            req.setAttribute("erro", "Valor inválido: " + e.getMessage());
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar transação: " + e.getMessage());
        }
        paginaTransacao(req, resp);
    }



    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String tipo = req.getParameter("tipo");
            double valor = Double.parseDouble(req.getParameter("valor"));
            String descricao = req.getParameter("descricao");
            String categoria = req.getParameter("categoria");
            String data = req.getParameter("data");


            Transacao transacao = new Transacao(id, tipo, descricao, categoria, valor, data);
            dao.atualizarTransacao(usuario, transacao);
            req.setAttribute("mensagem", "Entrada alterada com sucesso!");



        } catch (NumberFormatException e) {
            req.setAttribute("erro", "Valor inválido: " + e.getMessage());
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar transação: " + e.getMessage());
        }
        paginaTransacao(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String tipo = req.getParameter("tipo");
            double valor = Double.parseDouble(req.getParameter("valor"));
            String descricao = req.getParameter("descricao");
            String categoria = req.getParameter("categoria");
            String data = req.getParameter("data");


            Transacao transacao = new Transacao(id, tipo, descricao, categoria, valor, data);
            dao.removerTransacao(usuario, transacao);
            req.setAttribute("mensagem", "Entrada excluida com sucesso!");



        } catch (NumberFormatException e) {
            req.setAttribute("erro", "Valor inválido: " + e.getMessage());
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar transação: " + e.getMessage());
        }
        paginaTransacao(req, resp);
    }

    private void buscarTransacao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Transacao transacao = dao.buscarTransacao(usuario, id);
        req.setAttribute("transacao", transacao);
        req.getRequestDispatcher("editar-transacao.jsp").forward(req, resp);
    }

    private void paginaTransacao(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transacao> lista = dao.listarTransacao(usuario);
        req.setAttribute("transacoes", lista);

        double income = 0;
        try {
            income = dao.totalIncome(usuario);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("income", income);

        double expense = 0;
        try {
            expense = dao.totalExpense(usuario);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("expense", expense);

        req.getRequestDispatcher("transacao.jsp").forward(req, resp);
    }
}
