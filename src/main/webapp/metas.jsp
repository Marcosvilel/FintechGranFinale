
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fintech | Metas Financeiras</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="Resource/CSS/metas.css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Barra lateral -->
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
                        <a href="transacao.jsp" class="nav-link">
                            <i class="bi bi-cash-stack"></i> Transações
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="investimento.jsp" class="nav-link">
                            <i class="bi bi-graph-up"></i> Investimentos
                        </a>
                    </li>

                    <li class="nav-item">
                        <a href="metas.jsp" class="nav-link active">
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

        <!-- Conteúdo Principal -->
        <div class="col-md-9 col-lg-10 main-content">
            <header class="header">
                <h1>Metas Financeiras</h1>
                <button class="btn btn-primary" id="addGoalBtn"><i class="bi bi-plus-circle"></i> Nova Meta</button>
            </header>

            <div class="summary-cards">
                <div class="summary-card"><h3>Total em Metas</h3><p class="amount">R$ <span id="total-goals">0,00</span></p></div>
                <div class="summary-card"><h3>Alcançado</h3><p class="amount">R$ <span id="total-saved">0,00</span></p></div>
                <div class="summary-card"><h3>Faltam</h3><p class="amount">R$ <span id="remaining">0,00</span></p></div>
            </div>

            <div class="goals-list">
                <h2>Suas Metas</h2>
                <div class="card transaction-card">
                    <div class="card-body p-0" id="transactionList">
                        <div class="table-responsive">
                            <table class="table table-hover mb-0">
                                <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Prioridade</th>
                                    <th>Nome</th>
                                    <th class="text-end">Valor</th>
                                    <th class="text-center">Ações</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="meta" items="${metas}">
                                    <tr>
                                        <td>${meta.data != null ? meta.data.format(DateTimeFormatter.ofPattern('dd/MM/yyyy')) : ''}</td>
                                        <td>${meta.prioridade}</td>
                                        <td>${meta.nome}</td>
                                        <td class="text-end fw-bold">R$ <fmt:formatNumber value="${meta.valor}" minFractionDigits="2" maxFractionDigits="2"/></td>
                                        <td class="text-center">
                                            <div class="btn-group btn-group-sm">
                                                <button class="btn btn-outline-primary"
                                                        onclick="editarMeta(${meta.id})">
                                                    <i class="bi bi-pencil"></i>
                                                </button>
                                                <form action="${pageContext.request.contextPath}/meta" method="post" style="display: inline;">
                                                    <input type="hidden" name="acao" value="excluir">
                                                    <input type="hidden" name="id" value="${meta.id}">
                                                    <button type="submit" class="btn btn-outline-danger"
                                                            onclick="return confirm('Tem certeza que deseja excluir esta transação?')">
                                                        <i class="bi bi-trash"></i>
                                                    </button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <c:if test="${empty metas}">
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
</div>

<!-- Modal -->
<div class="modal fade" id="goalModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitle">Adicionar Nova Meta</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form action="metaFinanceira" method="post" id="goalForm">
                    <input type="hidden" name="acao" value="cadastrar">
                    <div class="mb-3">
                        <label for="goalName" class="form-label">Nome</label>
                        <input type="text" name="nome" id="goalName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="goalTarget" class="form-label">Valor Alvo</label>
                        <input type="number" name="valor" id="goalTarget" class="form-control" min="0.01" step="0.01" required>
                    </div>
                    <div class="mb-3">
                        <label for="goalDeadline" class="form-label">Data Limite</label>
                        <input type="date" name="data" id="goalDeadline" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="goalPriority" class="form-label">Prioridade</label>
                        <select id="goalPriority" class="form-select" name="prioridade" required>
                            <option value="baixa">Baixa</option>
                            <option value="media" selected>Média</option>
                            <option value="alta">Alta</option>
                        </select>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-primary" id="saveGoalBtn">Salvar</button>
                        </div>
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
