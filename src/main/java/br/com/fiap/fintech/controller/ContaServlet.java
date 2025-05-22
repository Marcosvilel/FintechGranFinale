package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/conta")
public class ContaServlet extends HttpServlet {

    private ContaDAO dao;
    private Usuario usuario;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usuario = new Usuario();
        dao = DAOFactory.getContaDAO();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        String userName = req.getParameter("userName");

        int numero = Integer.valueOf(req.getParameter("numero"));
        int  agencia = Integer.valueOf(req.getParameter("agencia"));
        int banco = Integer.valueOf(req.getParameter("banco"));
        String tipo = req.getParameter("tipo");
        double saldo = Double.valueOf(req.getParameter("saldo"));

        Usuario usuario = new Usuario(idUsuario, userName);

        Conta conta = new Conta(numero, agencia, banco, tipo, saldo);

        try {
            dao.cadastro(usuario, conta);
            req.setAttribute("exito", "Conta cadastrada com sucesso!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar Conta: " + e.getMessage());
        }

        req.getRequestDispatcher("cadastro-conta.jsp").forward(req, resp);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao) {
            case "infos-conta":
                int idUsuario = Integer.parseInt(req.getParameter("id-usuario"));

                try {
                    Conta conta = dao.buscar(idUsuario);

                } catch (DBException e) {
                    req.setAttribute("erro", "Erro ao retornar informações: " + e.getMessage());
                }

                break;
            case "editar-conta":
                break;
        }


    }
}
