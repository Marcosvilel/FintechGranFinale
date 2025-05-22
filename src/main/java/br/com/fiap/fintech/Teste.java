package br.com.fiap.fintech;

import br.com.fiap.fintech.dao.LoginDAO;
import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;
import br.com.fiap.fintech.model.Receita;
import br.com.fiap.fintech.model.Usuario;


public class Teste {
    public static void main(String[] args) {


        //cadastrod e usuário


        LoginDAO login = DAOFactory.getLoginDAO();
        TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();

        Usuario usuario = new Usuario(1,"luigi.bros@nintendo.com", "luigiBros", "nova Senha 2");
        Receita receita = new Receita(1230.42,"21/05/2025","transacao teste");

        try {
            transacaoDAO.cadastrarReceita(usuario,receita);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }


        System.out.println(receita.getDescricao());

    }
}
