document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("loginForm");
    const senhaInput = document.getElementById("senha");

    form.addEventListener("submit", function (e) {
        const senha = senhaInput.value;
        const temMaiuscula = /
