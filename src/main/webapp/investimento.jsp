
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Investimentos</title>
    <link rel="stylesheet" href="Resource/CSS/Tela_investimento.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>
<body>
<%@ include file="Sidebar.jsp" %>
        <!-- Conteúdo principal -->
        <div class="col-md-9 col-lg-10 ms-sm-auto px-md-4">
            <div class="container">
                <header class="header">
                    <h1>Investimentos</h1>
                </header>

                <div class="summary">
                    <div class="summary-card">
                        <h3>Total Investido</h3>
                        <p class="amount">R$ <span id="total-investido">25.430,75</span></p>
                    </div>
                    <div class="summary-card">
                        <h3>Rendimento Mensal</h3>
                        <p class="amount positive">+ R$ <span id="rendimento-mensal">1.245,32</span></p>
                    </div>
                </div>

                <div class="investments-list">
                    <h2>Seus Investimentos</h2>
                    <div id="investments-container">
                        <!-- Os investimentos serão carregados aqui via JavaScript -->
                    </div>
                </div>

                <div class="actions">
                    <button class="action-button add-button" id="add-investment">
                        <i class="fas fa-plus"></i> Adicionar Investimento
                    </button>
                    <button class="action-button view-button" id="view-charts">
                        <i class="fas fa-chart-line"></i> Visualizar Gráficos
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal para adicionar investimento -->
<div class="modal fade" id="investmentModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Adicionar Investimento</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="investmentForm">
                    <div class="mb-3">
                        <label for="investmentType" class="form-label">Tipo de Investimento</label>
                        <select class="form-select" id="investmentType" required>
                            <option value="">Selecione...</option>
                            <option value="tesouro">Tesouro Direto</option>
                            <option value="acoes">Ações</option>
                            <option value="fii">Fundo Imobiliário</option>
                            <option value="cdb">CDB</option>
                            <option value="lc">LCI/LCA</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="investmentName" class="form-label">Nome/Descrição</label>
                        <input type="text" class="form-control" id="investmentName" required>
                    </div>
                    <div class="mb-3">
                        <label for="investmentAmount" class="form-label">Valor Investido (R$)</label>
                        <input type="number" class="form-control" id="investmentAmount" step="0.01" required>
                    </div>
                    <div class="mb-3">
                        <label for="investmentDate" class="form-label">Data do Investimento</label>
                        <input type="date" class="form-control" id="investmentDate" required>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary" id="saveInvestment">Salvar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="Resource/JS/Tela_Investimento.js"></script>
</body>
</html>