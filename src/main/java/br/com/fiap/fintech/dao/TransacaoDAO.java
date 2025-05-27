package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Transacao;
import br.com.fiap.fintech.model.Usuario;

import java.util.List;

public interface TransacaoDAO {

    void cadastrarTransacao(Usuario usuario, Transacao transacao) throws DBException;

    void atualizarTransacao(Usuario usuario, Transacao transacao) throws DBException;

    void removerTransacao(Usuario usuario, Transacao transacao) throws DBException;

    Transacao buscarTransacao(Usuario usuario, int id);

    List<Transacao> listarTransacao(Usuario usuario);

    double totalIncome(Usuario usuario) throws DBException;

    double totalExpense(Usuario usuario) throws DBException;

    List<Transacao> listarTransacaoMes(Usuario usuario);
}