<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Investimentos</title>
  <link rel="stylesheet" href="Resource/CSS/relatorios.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <!-- Barra lateral -->
    <div class="col-md-3 col-lg-2 sidebar p-0">
      <div class="d-flex flex-column p-3">
        <div class="logo-area mb-4 text-center">
          <div class="split-word">
            <i class="bi bi-coin"></i>
            <span class="top">FINANCE</span>
            <span class="bottom">EASY</span>
          </div>
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
            <a href="metas.jsp" class="nav-link">
              <i class="bi bi-piggy-bank"></i> Metas
            </a>
          </li>
          <li class="nav-item">
            <a href="relatorios.jsp" class="nav-link active">
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

    <!-- Conteúdo principal -->
    <div class="col-md-9 col-lg-10 ms-sm-auto px-md-4">
      <div class="container">
        <header class="header">
          <h1>Relatório Personalizado</h1>
          <p class="subtitle">Selecione os dados que deseja exportar</p>
        </header>

        <!-- Card de Seleção -->
        <div class="selection-card">
          <div class="row g-3">
            <div class="col-md-6">
              <label for="reportType" class="form-label">Tipo de Relatório</label>
              <select class="form-select" id="reportType">
                <option value="">Selecione o tipo...</option>
                <option value="transacoes">Transações</option>
                <option value="investimentos">Investimentos</option>
                <option value="metas">Metas Financeiras</option>
                <option value="cartoes">Cartões de Crédito</option>
              </select>
            </div>
            <div class="col-md-6">
              <label for="period" class="form-label">Período</label>
              <select class="form-select" id="period">
                <option value="30">Últimos 30 dias</option>
                <option value="90">Últimos 3 meses</option>
                <option value="180">Últimos 6 meses</option>
                <option value="365">Último ano</option>
                <option value="custom">Personalizado</option>
              </select>
            </div>
          </div>

          <!-- Filtros personalizados (aparece apenas quando selecionado) -->
          <div id="customFilters" class="row g-3 mt-3" style="display: none;">
            <div class="col-md-6">
              <label for="startDate" class="form-label">Data Início</label>
              <input type="date" class="form-control" id="startDate">
            </div>
            <div class="col-md-6">
              <label for="endDate" class="form-label">Data Fim</label>
              <input type="date" class="form-control" id="endDate">
            </div>
          </div>

          <!-- Filtros adicionais dinâmicos -->
          <div id="additionalFilters" class="mt-3">
            <!-- Filtros serão inseridos aqui via JS -->
          </div>
        </div>

        <!-- Visualização Prévia -->
        <div class="preview-card mt-4" id="dataPreview" style="display: none;">
          <div class="d-flex justify-content-between align-items-center mb-3">
            <h3>Pré-visualização</h3>
            <small class="text-muted" id="rowCount">0 registros encontrados</small>
          </div>
          <div class="table-responsive">
            <table class="table table-sm" id="previewTable">
              <thead>
              <tr>
                <!-- Cabeçalhos serão preenchidos via JS -->
              </tr>
              </thead>
              <tbody>
              <!-- Dados serão preenchidos via JS -->
              </tbody>
            </table>
          </div>
        </div>

        <!-- Opções de Exportação -->
        <div class="export-options mt-4">
          <button class="btn btn-primary me-2" id="exportExcel">
            <i class="fas fa-file-excel"></i> Exportar para Excel
          </button>
          <button class="btn btn-secondary me-2" id="exportPDF">
            <i class="fas fa-file-pdf"></i> Exportar para PDF
          </button>
          <button class="btn btn-outline-secondary" id="exportPrint">
            <i class="fas fa-print"></i> Imprimir Relatório
          </button>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/xlsx@0.18.5/dist/xlsx.full.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
    <script src="Resource/JS/relatorio.js"></script>