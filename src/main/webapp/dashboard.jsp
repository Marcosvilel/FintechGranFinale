
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Resource/CSS/dashboard.css"> <!-- continue usando seu CSS -->
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
                    <h4><i class="bi bi-coin"></i> Fintech</h4>
                </div>
                <ul class="nav nav-pills flex-column mb-auto">
                    <li class="nav-item">
                        <a href="dashboard.jsp" class="nav-link active">
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

        <!-- Conteúdo principal -->
        <main class="col-md-9 col-lg-10 ms-sm-auto px-md-4 py-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Dashboard</h1>

            </div>

            <!-- Cards de Resumo -->
            <div class="row mb-4">
                <div class="col-md-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">Limite Disponível</h5>
                            <h2 class="card-text">R$ 5.000,00</h2>
                            <p class="card-text text-muted">80% do limite total</p>
                            <div class="progress mt-3">
                                <div class="progress-bar bg-success" role="progressbar" style="width: 80%" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">Meta Financeira</h5>
                            <h2 class="card-text">65%</h2>
                            <p class="card-text text-muted">R$ 3.250,00 de R$ 5.000,00</p>
                            <div class="progress mt-3">
                                <div class="progress-bar bg-info" role="progressbar" style="width: 65%" aria-valuenow="65" aria-valuemin="0" aria-valuemax="100"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Últimas Transações -->
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">Últimas Transações</h5>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>Descrição</th>
                                <th>Valor</th>
                                <th>Data</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Supermercado</td>
                                <td class="text-danger">- R$ 120,90</td>
                                <td>10/05/2023</td>
                                <td><span class="badge bg-success">Concluído</span></td>
                            </tr>
                            <tr>
                                <td>Salário</td>
                                <td class="text-success">+ R$ 3.500,00</td>
                                <td>05/05/2023</td>
                                <td><span class="badge bg-success">Concluído</span></td>
                            </tr>
                            <tr>
                                <td>Academia</td>
                                <td class="text-danger">- R$ 89,90</td>
                                <td>03/05/2023</td>
                                <td><span class="badge bg-warning text-dark">Pendente</span></td>
                            </tr>
                            <tr>
                                <td>Internet</td>
                                <td class="text-danger">- R$ 100,00</td>
                                <td>03/05/2023</td>
                                <td><span class="badge bg-success">Concluído</span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

