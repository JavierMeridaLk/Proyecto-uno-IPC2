<%-- 
    Document   : lectorJsp
    Created on : 18 sept 2024, 4:06:10
    Author     : xavi
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lector</title>
        <style>
            /* Estilo para ocultar los formularios inicialmente */
            .formulario {
                display: none;
                margin-top: 20px;
            }
        </style>
        <script>
            // Función para mostrar o ocultar el formulario específico
            function mostrarFormulario(idFormulario) {
                var formulario = document.getElementById(idFormulario);
                var display = window.getComputedStyle(formulario).display;
                if (display === "none") {
                    formulario.style.display = "block";
                } else {
                    formulario.style.display = "none";
                }
            }
        </script>

    </head>
    <body>
        <jsp:include page="/includes/Headers.jsp"/>

        <div class="barra-izquierda">
            <jsp:include page="/includes/barraAI.jsp"/>
        </div>

        <div class="container">
            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Revistas Disponibles 📖</h1>
                    <p class="col-md-8 fs-4">¡Bienvenido a nuestra plataforma! Nos alegra que estés aquí. 
                        Explora, disfruta y encuentra contenido creado especialmente para ti. Si necesitas 
                        algo, estamos para ayudarte en cada paso del camino. ¡Que disfrutes tu experiencia! 🌟</p>
                    <br>
                    <c:forEach items="${revistas}" var="revista" varStatus="status">
                        <div class="card-body">
                            <h2 class="card-title">Revista: ${revista.nombreRevista}</h2>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Descripción: ${revista.descripcion}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Categoría: ${revista.categoria}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Autor: ${revista.nombreAutor}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Me gustas:</h4>
                        </div>

                        <table>
                            <tr>
                                <!-- Botón Me gusta -->
                                <th>
                                    <c:choose>
                                        <c:when test="${revista.estadoMeGustas}">
                                            <form action="${pageContext.servletContext.contextPath}/registrarLikeSvt" method="post">
                                                <input type="hidden" name="cod_revista" value="${revista.codigo}">

                                                <button class="btn btn-primary btn-lg" type="submit">Me gusta</button>
                                            </form>
                                        </c:when>
                                    </c:choose>
                                </th>
                                <!-- Botón Comentar -->
                                <th>
                                    <c:choose>
                                        <c:when test="${revista.estadoComentarios}">
                                            <button class="btn btn-primary btn-lg" type="button" data-bs-toggle="modal" data-bs-target="#comentarioModal">Comentar</button>
                                        </c:when>
                                    </c:choose>
                                </th>

                                <!-- Modal para escribir el comentario -->
                            <div class="modal fade" id="comentarioModal" tabindex="-1" aria-labelledby="comentarioModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="comentarioModalLabel">Agregar Comentario</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="${pageContext.servletContext.contextPath}/RegistrarComentarioSvt" method="post">
    <div class="mb-3">
        <label for="contenido" class="form-label">Escribe tu comentario:</label>
        <textarea id="contenido" name="contenido" class="form-control" rows="3" required></textarea>
    </div>
    <input type="hidden" name="no_revista_com" value="${revista.codigo}" />
    <button type="submit" class="btn btn-primary">Enviar comentario</button>
</form>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            </tr>
                            <!-- Botón Suscribirse -->
                            <tr>
                                <th>
                                    <c:choose>
                                        <c:when test="${revista.estadoSuscripciones}">
                                            <!-- ID único para el botón -->
                                            <button onclick="mostrarFormulario('formulario${status.index}')" class="btn btn-success">Suscribirse</button>

                                        </c:when>
                                    </c:choose>
                                </th>
                            </tr>
                        </table>
                        <!-- Formulario oculto que se muestra al hacer clic en el botón Suscribirse -->
                        <div id="formulario${status.index}" class="formulario">
                            <form action="${pageContext.servletContext.contextPath}/lectorJsp" method="POST">
                                <label for="fecha">Fecha de suscripción:</label>
                                <input type="date" id="fecha" name="fecha" required>
                                <input type="hidden" name="codigo" value="${revista.codigo}">
                                <!-- Mostrar el código para depuración -->
                                <button type="submit" class="btn btn-success">Confirmar suscripción</button>
                            </form>
                        </div>
                        <hr>
                        <!-- Script para manejar la visibilidad del formulario -->
                    </c:forEach>
                </div>
            </div>

            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Tus revistas suscritas 📚</h1>
                    <p class="col-md-8 fs-4">¡Aquí tienes acceso a todas las revistas a las que te has suscrito! 
                        Disfruta de tu contenido favorito y mantente al día con las últimas publicaciones. 
                        ¡Gracias por ser parte de nuestra comunidad! 😊</p>
                    <br>
                    <c:forEach items="${revistas1}" var="revista" varStatus="status">
                        <div class="card-body">
                            <h2 class="card-title">Revista: ${revista.nombreRevista}</h2>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Descripción: ${revista.descripcion}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Categoría: ${revista.categoria}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Autor: ${revista.nombreAutor}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Me gustas:</h4>
                        </div>
                        <form method="GET" action="${pageContext.servletContext.contextPath}/tomoLectorSvt" enctype="multipart/form-data">     
                            <input type="hidden" name="codigo" value="${revista.codigo}">
                            <table >
                                <thead>
                                    <tr>
                                        <th>Nombre del Archivo</th>
                                        <th>Ver PDF</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="tomo" items="${tomos}">
                                        <tr>
                                            <td>${tomo.nombre}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/archivoSvt?codigo=${revista.codigo}&nombre=${tomo.nombre}&action=verPdf" target="_blank">
                                                    Ver PDF
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <button type="submit" class="btn btn-success">Ver tomos</button>
                            <br>
                        </form>
                        <hr>

                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="barra-derecha">
            <jsp:include page="/includes/barraAD.jsp"/>
        </div>
        <!-- Footer -->
        <jsp:include page="/includes/Footer.jsp"/>

    </body>
</html>
