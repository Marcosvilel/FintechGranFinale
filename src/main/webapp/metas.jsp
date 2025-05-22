
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
                <div id="goals-container"></div>
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
                <form id="goalForm">
                    <input type="hidden" id="goalId">
                    <div class="mb-3"><label class="form-label">Nome</label><input type="text" id="goalName" class="form-control" required></div>
                    <div class="mb-3"><label class="form-label">Valor Alvo</label><input type="number" id="goalTarget" class="form-control" min="0.01" step="0.01" required></div>
                    <div class="mb-3"><label class="form-label">Valor Atual</label><input type="number" id="goalCurrent" class="form-control" min="0" step="0.01" required></div>
                    <div class="mb-3"><label class="form-label">Data Limite</label><input type="date" id="goalDeadline" class="form-control" required></div>
                    <div class="mb-3">
                        <label class="form-label">Prioridade</label>
                        <select id="goalPriority" class="form-select" required>
                            <option value="baixa">Baixa</option>
                            <option value="media" selected>Média</option>
                            <option value="alta">Alta</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button class="btn btn-primary" id="saveGoalBtn">Salvar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="Resource/JS/metas.js"></script>
</body>
</html>
