document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    const usuarioInput = document.getElementById('usuario');
    const senhaInput = document.getElementById('senha');

    // Função para verificar se há pelo menos uma letra maiúscula
    function hasUpperCase(str) {
        return /[A-Z]/.test(str);
    }

    // Função para verificar se há pelo menos um caractere especial
    function hasSpecialChar(str) {
        return /[!@#$%^&*(),.?":{}|<>]/.test(str);
    }

    // Função para validar as credenciais
    function validateCredentials(usuario, senha) {
        let isValid = true;
        let errorMessage = '';

        // Validar usuário
        // if (!hasUpperCase(usuario)) {
        //     isValid = false;
        //     errorMessage += 'O nome de usuário deve conter pelo menos uma letra maiúscula.\n';
        // }

        // Validar senha
        // if (!hasUpperCase(senha)) {
        //     isValid = false;
        //     errorMessage += 'A senha deve conter pelo menos uma letra maiúscula.\n';
        // }
        //
        // if (!hasSpecialChar(senha)) {
        //     isValid = false;
        //     errorMessage += 'A senha deve conter pelo menos um caractere especial.\n';
        // }
        //
        // if (senha.length < 6) {
        //     isValid = false;
        //     errorMessage += 'A senha deve ter pelo menos 6 caracteres.\n';
        // }
        //
        // if (!isValid) {
        //     alert(errorMessage);
        // }

        return isValid;
    }

    // Evento de submit do formulário
    loginForm.addEventListener('submit', function(e) {
        e.preventDefault(); // Impede o envio padrão do formulário

        const usuario = usuarioInput.value.trim();
        const senha = senhaInput.value.trim();

        // Validar as credenciais
        if (validateCredentials(usuario, senha)) {
            // Se as credenciais forem válidas, envia o formulário
            this.submit();

            // Ou, se preferir redirecionar diretamente:
        }
    });

    // Validação em tempo real (opcional)
    // usuarioInput.addEventListener('input', function() {
    //     if (this.value && !hasUpperCase(this.value)) {
    //         this.setCustomValidity('O nome de usuário deve conter pelo menos uma letra maiúscula.');
    //     } else {
    //         this.setCustomValidity('');
    //     }
    // });

    // senhaInput.addEventListener('input', function() {
    //     if (this.value && (!hasUpperCase(this.value) || !hasSpecialChar(this.value))) {
    //         this.setCustomValidity('A senha deve conter pelo menos uma letra maiúscula e um caractere especial.');
    //     } else {
    //         this.setCustomValidity('');
    //     }
    // });
});