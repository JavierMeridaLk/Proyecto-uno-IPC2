<%-- 
    Document   : Headers
    Created on : 14 sept 2024, 1:23:28
    Author     : xavi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div class="col-md-3 mb-2 mb-md-0">
            <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
                <img src="${pageContext.request.contextPath}/resources/img/logo.jpg" alt="" width="72" height="72">
            </a>
        </div>
        <h1  class="nav-link px-2 link-secondary">Revistas actuales</h1>
        <div class="col-md-3 text-end">
            <button type="button" class="btn btn-outline-primary me-2" 
                    onclick="window.location.href = '${pageContext.request.contextPath}/Perfil.jsp';">
                Perfil
            </button>
            <button type="button" class="btn btn-primary" 
                    onclick="cerrarSesion();">
                Salir
            </button>
        </div>
    </header>
</div>
<script>
    function cerrarSesion() {
        window.location.href = '${pageContext.request.contextPath}/Login.jsp';
        session.invalidate();
    }
</script>