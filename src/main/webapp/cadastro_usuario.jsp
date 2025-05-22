<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link
            href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="Resource/CSS/cadastro_usuario.css">
</head>
<body class="body">
<div class="cadastro-container">
    <div class="cadastro-box">
        <div class="cadastro-header">
            <h2><i class="fas fa-user-plus"></i> Criar Conta</h2>
            <p>Preencha seus dados para se cadastrar</p>
        </div>

        <% if (request.getParameter("error") != null) { %>
        <div class="alert-error">
            <i class="fas fa-exclamation-circle"></i> ${param.error}
        </div>
        <% } %>

        <form id="formCadastro" action="${pageContext.request.contextPath}/processaCadastro" method="post">
            <!-- Dados Pessoais -->
            <div class="form-section">
                <h3><i class="fas fa-id-card"></i> Dados Pessoais</h3>
                <div class="form-row">
                    <div class="input-group">
                        <label for="nomeCompleto">Nome Completo*</label>
                        <input type="text" id="nomeCompleto" name="nomeCompleto" required>
                        <i class="fas fa-user"></i>
                    </div>

                    <div class="input-group">
                        <label for="cpf">CPF*</label>
                        <input type="text" id="cpf" name="cpf" placeholder="000.000.000-00" required>
                        <i class="fas fa-id-card"></i>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="genero">Gênero*</label>
                        <select id="genero" name="genero" required>
                            <option value="">Selecione...</option>
                            <option value="M">Masculino</option>
                            <option value="F">Feminino</option>
                            <option value="O">Outro</option>
                            <option value="N">Prefiro não informar</option>
                        </select>
                        <i class="fas fa-venus-mars"></i>
                    </div>

                    <div class="input-group">
                        <label for="dataNascimento">Data de Nascimento*</label>
                        <input type="date" id="dataNascimento" name="dataNascimento" required>
                        <i class="fas fa-calendar-alt"></i>
                    </div>
                </div>
            </div>

            <!-- Contato -->
            <div class="form-section">
                <h3><i class="fas fa-phone-alt"></i> Contato</h3>
                <div class="form-row">
                    <div class="input-group">
                        <label for="celular">Celular*</label>
                        <input type="tel" id="celular" name="celular" placeholder="(00) 00000-0000" required>
                        <i class="fas fa-mobile-alt"></i>
                    </div>

                    <div class="input-group">
                        <label for="email">E-mail*</label>
                        <input type="email" id="email" name="email" placeholder="seu@email.com" required>
                        <i class="fas fa-envelope"></i>
                    </div>
                </div>
            </div>

            <!-- Segurança -->
            <div class="form-section">
                <h3><i class="fas fa-lock"></i> Segurança</h3>
                <div class="form-row">
                    <div class="input-group">
                        <label for="senha">Senha*</label>
                        <input type="password" id="senha" name="senha" required>
                        <i class="fas fa-key"></i>
                        <div class="password-strength"></div>
                    </div>

                    <div class="input-group">
                        <label for="confirmarSenha">Confirmar Senha*</label>
                        <input type="password" id="confirmarSenha" name="confirmarSenha" required>
                        <i class="fas fa-key"></i>
                    </div>
                </div>
                <div class="password-requirements">
                    <p>A senha deve conter:</p>
                    <ul>
                        <li class="req-length">Mínimo 8 caracteres</li>
                        <li class="req-upper">Letra maiúscula</li>
                        <li class="req-number">Número</li>
                        <li class="req-special">Caractere especial</li>
                    </ul>
                </div>
            </div>

            <div class="form-footer">
                <a href="Dashboard.jsp">
                    <button type="submit" class="btn-cadastrar">
                        <i class="fas fa-user-plus"></i> Cadastrar
                    </button>
                </a>
                <p class="login-link">
                    Já possui conta? <a href="index.jsp">Faça login</a>
                </p>
            </div>
        </form>
    </div>
</div>
<%--<style>--%>
<%--    body {--%>
<%--        background: #2ecc71;--%>
<%--    }--%>
<%--</style>--%>
</body>
</html>