<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Transações</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
  <link rel="stylesheet" href="Resource/CSS/transacao.css">
</head>
<body>




<div class="container-fluid">
  <div class="row">
    <!-- Sidebar -->
    <div class="col-md-3 col-lg-2 sidebar p-0">
      <div class="d-flex flex-column p-3">
        <div class="logo-area mb-4 text-center">
          <h4><i class="bi bi-coin"></i> Fintech</h4>
        </div>
        <ul class="nav nav-pills flex-column mb-auto">
          <li class="nav-item">
            <a href="dashboard.jsp" class="nav-link">
              <i class="bi bi-house-door"></i> Dashboard
            </a>
          </li>
          <li class="nav-item">
            <a href="transacao.jsp" class="nav-link active">
              <i class="bi bi-cash-stack"></i> Transações
            </a>
          </li>
          <li class="nav-item">
            <a href="investimento.jsp" class="nav-link">
              <i class="bi bi-graph-up"></i> Investimentos
            </a>
          </li>
          <li class="nav-item">
            <a href="metas.jsp" class="nav-link">
              <i class="bi bi-piggy-bank"></i> Metas
            </a>
          </li>
          <li class="nav-item">
            <a href="relatorios.jsp" class="nav-link">
              <i class="bi bi-file-earmark-text"></i> Relatórios
            </a>
          </li>
          <li class="nav-item">
            <a href="perfil.jsp" class="nav-link">
              <i class="bi bi-person-circle"></i> Perfil
            </a>
          </li>
        </ul>
      </div>
    </div>

    <!-- Main content -->
    <div class="col-md-9 col-lg-10 ms-sm-auto main-content">
      <div class="page-header">
        <h1 class="page-title">
          <i class="bi bi-cash-stack me-2"></i>Transações
        </h1>
        <div>
          <button class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#filterModal">
            <i class="bi bi-funnel"></i> Filtrar
          </button>
          <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#newTransactionModal">
            <i class="bi bi-plus-circle"></i> Nova Transação
          </button>
        </div>
      </div>

      <div class="row">
        <div class="col-md-6 mb-4">
          <div class="card transaction-card income-card">
            <div class="card-header">
              <i class="bi bi-arrow-down-circle me-2"></i>Entradas
            </div>
            <div class="card-body">
              <h3 class="card-title text-success">
                R$ <fmt:formatNumber value="${income}" minFractionDigits="2" maxFractionDigits="2"/>
              </h3>
              <p class="card-text text-muted">Total recebido este mês</p>
            </div>
          </div>
        </div>
        <div class="col-md-6 mb-4">
          <div class="card transaction-card expense-card">
            <div class="card-header">
              <i class="bi bi-arrow-up-circle me-2"></i>Saídas
            </div>
            <div class="card-body">
              <h3 class="card-title text-danger">
                R$ <fmt:formatNumber value="${expense}" minFractionDigits="2" maxFractionDigits="2"/>
              </h3>
              <p class="card-text text-muted">Total gasto este mês</p>
            </div>
          </div>
        </div>
      </div>

      <div class="card transaction-card">
        <div class="card-header d-flex justify-content-between align-items-center">
          <span>Histórico de Transações</span>
          <div>
            <ul class="nav nav-tabs card-header-tabs">
              <li class="nav-item">
                <a class="nav-link active" href="#">Todas</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Entradas</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Saídas</a>
              </li>
            </ul>
          </div>
        </div>
        <div class="card-body p-0" id="transactionList">
          <div class="table-responsive">
            <table class="table table-hover mb-0">
              <thead>
              <tr>
                <th>Data</th>
                <th>Descrição</th>
                <th>Categoria</th>
                <th class="text-end">Valor</th>
                <th class="text-center">Ações</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="transacao" items="${transacoes}">
                <tr class="${transacao.tipo == 'income' ? 'table-success' : 'table-danger'}">
                  <td>${transacao.data != null ? transacao.data.format(DateTimeFormatter.ofPattern('dd/MM/yyyy')) : ''}</td>
                  <td>${transacao.descricao}</td>
                  <td>${transacao.categoria}</td>
                  <td class="text-end fw-bold">
                    <c:if test="${transacao.tipo == 'income'}">+</c:if>
                    <c:if test="${transacao.tipo == 'expense'}">-</c:if>
                    R$ <fmt:formatNumber value="${transacao.valor}" minFractionDigits="2" maxFractionDigits="2"/>
                  </td>
                  <td class="text-center">
                    <div class="btn-group btn-group-sm">
                      <button class="btn btn-outline-primary"
                              onclick="editarTransacao(${transacao.id})">
                        <i class="bi bi-pencil"></i>
                      </button>
                      <form action="${pageContext.request.contextPath}/transacao" method="post" style="display: inline;">
                        <input type="hidden" name="acao" value="excluir">
                        <input type="hidden" name="id" value="${transacao.id}">
                        <button type="submit" class="btn btn-outline-danger"
                                onclick="return confirm('Tem certeza que deseja excluir esta transação?')">
                          <i class="bi bi-trash"></i>
                        </button>
                      </form>
                    </div>
                  </td>
                </tr>
              </c:forEach>

              <c:if test="${empty transacoes}">
                <tr>
                  <td colspan="5" class="text-center text-muted py-4">
                    Nenhuma transação cadastrada ainda
                  </td>
                </tr>
              </c:if>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<!-- Modals -->
<div class="modal fade" id="newTransactionModal" tabindex="-1" aria-labelledby="newTransactionModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="newTransactionModalLabel">Nova Transação</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="transacao" method="post" id="transactionForm" class="transaction-form">
          <input type="hidden" name="acao" value="cadastrar">
          <div class="mb-3">
            <label for="transactionType" class="form-label">Tipo</label>
            <select class="form-select" id="transactionType" name="tipo" required>
              <option value="income">Entrada</option>
              <option value="expense">Saída</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="transactionAmount" class="form-label">Valor</label>
            <div class="input-group">
              <span class="input-group-text">R$</span>
              <input type="number" name="valor" class="form-control" id="transactionAmount" placeholder="0,00" step="0.01" required>
            </div>
          </div>
          <div class="mb-3">
            <label for="transactionDescription" class="form-label">Descrição</label>
            <input type="text" name="descricao" class="form-control" id="transactionDescription" placeholder="Ex: Salário, Aluguel..." required>
          </div>
          <div class="mb-3">
            <label for="transactionCategory" class="form-label">Categoria</label>
            <select class="form-select" id="transactionCategory" name="categoria" required>
              <!-- Options will be loaded dynamically -->
            </select>
          </div>
          <div class="mb-3">
            <label for="transactionDate" class="form-label">Data</label>
            <input type="date" name="data" class="form-control" id="transactionDate" required>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            <button type="submit" class="btn btn-primary">Salvar Transação</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="filterModal" tabindex="-1" aria-labelledby="filterModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="filterModalLabel">Filtrar Transações</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="filterForm">
          <div class="mb-3">
            <label class="form-label">Tipo</label>
            <div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="filterType" id="filterAll" value="all" checked>
                <label class="form-check-label" for="filterAll">Todos</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="filterType" id="filterIncome" value="income">
                <label class="form-check-label" for="filterIncome">Entradas</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="filterType" id="filterExpense" value="expense">
                <label class="form-check-label" for="filterExpense">Saídas</label>
              </div>
            </div>
          </div>
          <div class="mb-3">
            <label for="filterCategory" class="form-label">Categoria</label>
            <select class="form-select" id="filterCategory">
              <option value="">Todas categorias</option>
            </select>
          </div>
          <div class="mb-3">
            <label for="filterDateFrom" class="form-label">Período</label>
            <div class="row">
              <div class="col-md-6">
                <input type="date" class="form-control" id="filterDateFrom">
              </div>
              <div class="col-md-6">
                <input type="date" class="form-control" id="filterDateTo">
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-primary" id="applyFilters">Aplicar Filtros</button>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="Resource/JS/transacao.js"></script>
</body>
</html>