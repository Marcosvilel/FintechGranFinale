
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Resource/CSS/dashboard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>

<body>
<%@ include file="Sidebar.jsp" %>

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
                            <h5 class="card-title">Balanço Total</h5>
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
                                <th>Tipo</th>
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

