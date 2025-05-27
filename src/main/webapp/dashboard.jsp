<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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


            <!-- Últimas Transações -->
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">
                        <i class="bi bi-clock-history"></i> Atividades do Mês
                    </h5>
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
                            <c:forEach var="transacao" items="${transacoesMes}">
                            <tr>
                                <td>${transacao.descricao}</td>
                                <td class="fw-bold text-nowrap">
                                    <c:if test="${transacao.tipo == 'income'}">+</c:if>
                                    <c:if test="${transacao.tipo == 'expense'}">-</c:if>
                                    R$ <fmt:formatNumber value="${transacao.valor}" minFractionDigits="2" maxFractionDigits="2"/>
                                </td>
                                <td>${transacao.data != null ? transacao.data.format(DateTimeFormatter.ofPattern('dd/MM/yyyy')) : ''}</td>
                                <td>
                                    <span class="${transacao.tipo == 'income' ? 'badge bg-success' : 'badge bg-warning text-dark'}">
                                            ${transacao.tipo}
                                    </span>
                                </td>
                            </tr>
                            </c:forEach>

                            <c:if test="${empty transacoesMes}">
                                <tr>
                                    <td colspan="5" class="text-center text-muted py-4">
                                        Nenhuma transação cadastrada ainda
                                    </td>
                                </tr>
                            </c:if>
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

