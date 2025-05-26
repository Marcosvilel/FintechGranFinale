

<%
    String currentPage = request.getServletPath();
    currentPage = currentPage.substring(currentPage.lastIndexOf("/") + 1);
    pageContext.setAttribute("currentPage", currentPage);
%>


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
                        <a href="dashboard.jsp" class="nav-link ${currentPage eq 'dashboard.jsp' ? 'active' : ''}">
                            <i class="bi bi-house-door"></i> Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="transacao.jsp" class="nav-link ${currentPage eq 'transacao.jsp' ? 'active' : ''}">
                            <i class="bi bi-cash-stack"></i> transacao
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="investimento.jsp" class="nav-link ${currentPage eq 'investimento.jsp' ? 'active' : ''}">
                            <i class="bi bi-graph-up"></i> Investimentos
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="metas.jsp" class="nav-link ${currentPage eq 'metas.jsp' ? 'active' : ''}">
                            <i class="bi bi-piggy-bank"></i> Metas
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="relatorios.jsp" class="nav-link ${currentPage eq 'relatorios.jsp' ? 'active' : ''}">
                            <i class="bi bi-file-earmark-text"></i> Relatorio
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="perfil.jsp" class="nav-link ${currentPage eq 'perfil.jsp' ? 'active' : ''}">
                            <i class="bi bi-person-circle"></i> Perfil
                        </a>
                    </li>
                </ul>
            </div>
        </div>
