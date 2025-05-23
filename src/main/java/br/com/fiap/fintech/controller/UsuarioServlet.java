package br.com.fiap.fintech.controller;

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

    private Usuario dao;

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String nome = req.getParameter("nomeCompleto");
        String password = req.getParameter("senha");


    }
}
