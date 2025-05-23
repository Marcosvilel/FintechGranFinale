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

        req.getRequestDispatcher("transacao.jsp").forward(req, resp);
    }
}
