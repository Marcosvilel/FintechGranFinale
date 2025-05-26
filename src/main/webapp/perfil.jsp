
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <link rel="stylesheet" href="Resource/CSS/perfil.css">
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
                        <a href="relatorios.jsp" class="nav-link">
                            <i class="bi bi-file-earmark-text"></i> Relatórios
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="relatorios.jsp" class="nav-link active">
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
                    <h1>Meu Perfil</h1>
                </header>

                <div class="profile-card">
                    <div class="profile-icon">
                        <i class="bi bi-person-circle"></i>
                    </div>
                    <h3>Bem-vindo, <span id="user-name">Usuário</span></h3>

                    <div class="profile-email">
                        <i class="bi bi-envelope-fill me-2"></i>
                        <span id="user-email">${usuario.email}</span>
                    </div>

                    <form action="LogoutServlet" method="post">
                        <button type="submit" class="btn btn-danger logout-btn">
                            <i class="bi bi-box-arrow-right me-2"></i> Sair da Conta
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="Resource/JS/perfil.js"></script>
</body>
</html>