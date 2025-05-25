<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>LOGIN</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="Resource/CSS/login.css">
</head>
<body>
<div class="login-container">
    <div class="login-box">
        <h2>Login</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert" style="margin: 10px 0; padding: 10px; border-left: 4px solid #dc3545; background-color: #f8d7da; color: #721c24;">
                    ${error}
            </div>
        </c:if>

        <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
            <div class="input-group">
                <label for="usuario">Usuário:</label>
                <input type="text" id="usuario" name="usuario" required>
            </div>

            <div class="input-group">
                <label for="senha">Senha:</label>
                <input type="password" id="senha" name="senha" required>
            </div>

            <div class="login_usuario">
                <button type="submit" class="btn-login">Entrar</button>
            </div>
        </form>

        <div class="register-link">
            Não tem conta? <a href="cadastro_usuario.jsp">Cadastre-se</a>
        </div>
    </div>
</div>

<script src="Resource/JS/login.js"></script>
</body>
</html>