<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <link rel="stylesheet" href="Resource/CSS/perfil.css">
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
                    <h1>Meu Perfil</h1>
                </header>

                <div class="profile-card">
                    <div class="profile-icon">
                        <i class="bi bi-person-circle"></i>
                    </div>
                    <h3>Bem-vindo, <span>${usuario.name}</span></h3>

                    <div class="profile-email">
                        <i class="bi bi-envelope-fill me-2"></i>
                        <span>${usuario.email}</span>
                    </div>

                    <form action="LogoutServlet" method="get">
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