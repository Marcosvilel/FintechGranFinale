<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Usuário</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="Resource/CSS/cadastro_usuario.css">
</head>
<body>
<div class="cadastro-container">
    <div class="cadastro-box">
        <div class="cadastro-header">
            <h2><i class="fas fa-user-plus"></i> Criar Nova Conta</h2>
            <p>Preencha seus dados para começar</p>
        </div>

        <c:if test="${not empty error}">
            <div class="alert-error">
                <i class="fas fa-exclamation-circle"></i> ${error}
            </div>
        </c:if>

        <form id="formCadastro" action="${pageContext.request.contextPath}/cadastro" method="post" novalidate>
            <!-- Dados Pessoais -->
            <div class="form-section">
                <h3><i class="fas fa-id-card"></i> Dados Pessoais</h3>

                <div class="form-row">
                    <div class="input-group">
                        <label for="nomeCompleto">Nome Completo*</label>
                        <input type="text" id="nomeCompleto" name="nomeCompleto" required
                               pattern="[A-Za-zÀ-ú\s]{5,}"
                               title="Mínimo 5 caracteres (apenas letras)">
                        <i class="fas fa-user"></i>
                    </div>

                    <div class="input-group">
                        <label for="usuario">Nome de Usuário*</label>
                        <input type="text" id="usuario" name="usuario" required
                               pattern="[A-Za-z0-9_]{4,20}"
                               title="4-20 caracteres (letras, números ou _)">
                        <i class="fas fa-user-tag"></i>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="cpf">CPF*</label>
                        <input type="text" id="cpf" name="cpf" placeholder="000.000.000-00" required
                               pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"
                               title="Formato: 000.000.000-00">
                        <i class="fas fa-id-card"></i>
                    </div>

                    <div class="input-group">
                        <label for="dataNascimento">Data de Nascimento*</label>
                        <input type="date" id="dataNascimento" name="dataNascimento" required
                               max="<%= java.time.LocalDate.now().minusYears(18) %>"
                               min="<%= java.time.LocalDate.now().minusYears(120) %>">
                        <i class="fas fa-calendar-alt"></i>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="genero">Gênero</label>
                        <select id="genero" name="genero">
                            <option value="">Selecione...</option>
                            <option value="M">Masculino</option>
                            <option value="F">Feminino</option>
                            <option value="O">Outro</option>
                            <option value="N">Prefiro não informar</option>
                            <i class="fas fa-venus-mars"></i>
                        </select>
                    </div>
                </div>
            </div>

            <!-- Contato -->
            <div class="form-section">
                <h3><i class="fas fa-envelope"></i> Informações de Contato</h3>

                <div class="form-row">
                    <div class="input-group">
                        <label for="email">E-mail*</label>
                        <input type="email" id="email" name="email" placeholder="seu@email.com" required
                               pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
                        <i class="fas fa-envelope"></i>
                    </div>

                    <div class="input-group">
                        <label for="celular">Celular*</label>
                        <input type="tel" id="celular" name="celular" placeholder="(00) 00000-0000" required
                               pattern="\(\d{2}\)\s\d{4,5}-\d{4}"
                               title="Formato: (00) 00000-0000">
                        <i class="fas fa-mobile-alt"></i>
                    </div>
                </div>
            </div>

            <!-- Segurança -->
            <div class="form-section">
                <h3><i class="fas fa-lock"></i> Segurança</h3>

                <div class="form-row">
                    <div class="input-group">
                        <label for="senha">Senha*</label>
                        <input type="password" id="senha" name="senha" required
                               pattern="^(?=.[a-z])(?=.[A-Z])(?=.\d)(?=.[@$!%?&])[A-Za-z\d@$!%?&]{8,}$"
                               title="Mínimo 8 caracteres com letras maiúsculas, minúsculas, números e símbolos">
                        <i class="fas fa-lock"></i> <!-- Ícone de cadeado simples -->
                    </div>

                    <div class="input-group">
                        <label for="confirmarSenha">Confirmar Senha*</label>
                        <input type="password" id="confirmarSenha" name="confirmarSenha" required>
                        <i class="fas fa-lock"></i> <!-- Ícone de cadeado simples -->
                    </div>
                </div>


                <div class="password-requirements">
                    <p>A senha deve conter:</p>
                    <ul>
                        <li class="req-length">Mínimo 8 caracteres</li>
                        <li class="req-upper">Letra maiúscula</li>
                        <li class="req-lower">Letra minúscula</li>
                        <li class="req-number">Número</li>
                        <li class="req-special">Caractere especial (@$!%*?&)</li>
                    </ul>
                </div>
            </div>

            <div class="form-footer">
                <button type="submit" class="btn-cadastrar">
                    <i class="fas fa-user-plus"></i> Criar Conta
                </button>
                <p class="login-link">
                    Já possui uma conta? <a href="index.jsp">Faça login</a>
                </p>
            </div>


        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="Resource/JS/cadastro.js"></script>

</body>
</html>