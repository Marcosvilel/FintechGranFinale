<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Metas Financeiras</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="Resource/CSS/metas.css">
</head>
<body>
<%@ include file="Sidebar.jsp" %>

        <!-- Conteúdo Principal -->
        <div class="col-md-9 col-lg-10 ms-sm-auto main-content">
            <div class="page-header d-flex justify-content-between align-items-center">
                <h1 class="page-title m-0">
                    <i class="bi bi-piggy-bank me-2"></i>Metas Financeiras
                </h1>
                <div class="d-flex">
                    <button class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#filterModal">
                        <i class="bi bi-funnel"></i> Filtrar
                    </button>
                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#newGoalModal">
                        <i class="bi bi-plus-circle"></i> Nova Meta
                    </button>
                </div>
            </div>

            <br>

            <!-- Cards de Resumo Financeiro -->
            <div class="row mb-4">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Total em Metas</h5>
                            <p class="card-text">R$ <fmt:formatNumber value="${totalMetas}" minFractionDigits="2" maxFractionDigits="2"/></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Alcançado</h5>
                            <p class="card-text">R$ <fmt:formatNumber value="${totalAlcancado}" minFractionDigits="2" maxFractionDigits="2"/></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Faltam</h5>
                            <p class="card-text">R$ <fmt:formatNumber value="${totalFaltam}" minFractionDigits="2" maxFractionDigits="2"/></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card goal-card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <span>Minhas Metas</span>
                </div>
                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover mb-0">
                            <thead>
                            <tr>
                                <th>Meta</th>
                                <th>Valor Alvo</th>
                                <th>Data Limite</th>
                                <th>Prioridade</th>
                                <th class="text-center">Ações</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="meta" items="${metas}">
                                <tr>
                                    <td>${meta.nome}</td>
                                    <td>R$ <fmt:formatNumber value="${meta.valor}" minFractionDigits="2" maxFractionDigits="2"/></td>
                                    <td>${meta.data != null ? meta.data.format(DateTimeFormatter.ofPattern('dd/MM/yyyy')) : ''}</td>
                                    <td>
                                <span class="badge bg-${meta.prioridade == 'alta' ? 'danger' : meta.prioridade == 'media' ? 'warning' : 'success'}">
                                        ${meta.prioridade}
                                </span>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group btn-group-sm">
                                            <button class="btn btn-outline-primary" onclick="editarMeta(${meta.id})">
                                                <i class="bi bi-pencil"></i>
                                            </button>
                                            <button class="btn btn-outline-danger" onclick="confirmarExclusao(${meta.id})">
                                                <i class="bi bi-trash"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>

                            <c:if test="${empty metas}">
                                <tr>
                                    <td colspan="7" class="text-center text-muted py-4">
                                        Nenhuma meta cadastrada ainda
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

<!-- Modal (mantido igual) -->
<div class="modal fade" id="newGoalModal" tabindex="-1" aria-labelledby="newGoalModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="newGoalModalLabel">Nova Meta Financeira</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="meta" method="post" id="goalForm">
                    <input type="hidden" name="acao" value="cadastrar">
                    <div class="mb-3">
                        <label for="goalDescription" class="form-label">Descrição da Meta</label>
                        <input type="text" class="form-control" id="goalDescription" name="nome" required>
                    </div>
                    <div class="mb-3">
                        <label for="goalTargetValue" class="form-label">Valor Alvo (R$)</label>
                        <input type="number" class="form-control" id="goalTargetValue" name="valor" step="0.01" min="0.01" required>
                    </div>
                    <div class="mb-3">
                        <label for="goalDueDate" class="form-label">Data Limite</label>
                        <input type="date" class="form-control" id="goalDueDate" name="data" required>
                    </div>
                    <div class="mb-3">
                        <label for="goalPriority" class="form-label">Prioridade</label>
                        <select class="form-select" id="goalPriority" name="prioridade" required>
                            <option value="baixa">Baixa</option>
                            <option value="media" selected>Média</option>
                            <option value="alta">Alta</option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="submit" class="btn btn-primary">Salvar Meta</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="Resource/JS/metas.js"></script>
</body>
</html>