<%-- 
    Document   : autorJSP
    Created on : 17 sept 2024, 16:12:04
    Author     : xavi
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autor</title>
        <style>
            /* Esconde el formulario por defecto */
            #formNuevaRevista {
                display: none;
            }
            /* Estilo para ocultar los formularios inicialmente */
            .formulario {
                display: none;
                margin-top: 20px;
            }
            .formulario1 {
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
                    <h1 class="display-5 fw-bold">Publicar revista</h1>
                    <button class="btn btn-primary btn-lg" type="button" id="btnNuevaRevista">Nueva revista</button>
                    <form id="formNuevaRevista" method="POST" action="${pageContext.servletContext.contextPath}/nuevaRevistasSvt">
                        <h2>Nueva Revista</h2>
                        <table>
                            <tr>
                                <th><label for="titulo">Nombre de la revista:</label></th>
                                <th><input type="text" id="nombre" name="nombre" required></th>
                            </tr>
                            <tr>
                                <th><label for="fecha">Fecha de publicación:</label></th>
                                <th><input type="date" id="fecha" name="fecha" required></th>
                            </tr>
                            <tr>
                                <th><label for="descripcion">Descripción:</label></th>
                                <th><textarea id="descripcion" name="descripcion" required></textarea></th>
                            </tr>
                            <tr>
                                <th><label for="categoria">Categoría:</label></th>
                                <th><input type="text" id="categoria" name="categoria" required></th>
                            </tr>
                            <tr>
                                <th><label><input type="checkbox" id="meGustas" name="meGustas" value="false"> Deshabilitar Me gustas</label></th>
                            </tr>
                            <tr>
                                <th><label><input type="checkbox" id="comentarios" name="comentarios" value="false"> Deshabilitar Comentarios</label></th>
                            </tr>
                            <tr>
                                <th><label><input type="checkbox" id="suscripciones" name="suscripciones" value="false"> Deshabilitar Suscripciones</label></th>
                            </tr>
                        </table>
                        <br>
                        <button type="submit" class="btn btn-success">Guardar</button>
                    </form>

                    <script>
                        // Selecciona el botón y el formulario
                        const btnNuevaRevista = document.getElementById("btnNuevaRevista");
                        const formNuevaRevista = document.getElementById("formNuevaRevista");
                        // Agrega un evento click al botón
                        btnNuevaRevista.addEventListener("click", function () {
                            // Oculta el botón
                            btnNuevaRevista.style.display = "none";
                            // Muestra el formulario
                            formNuevaRevista.style.display = "block";
                        });
                    </script>
                </div>
            </div>
            <br><!-- comment -->

            <div class="p-5 mb-4 bg-body-terciary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="card-title">Listado de Revistas</h1>            
                    <hr>
                    <c:forEach items="${revistas}" var="revista" varStatus="status">
                        <div class="card-body">
                            <h2 class="card-title">Nombre: ${revista.nombreRevista}</h2>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Descripción: ${revista.descripcion}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Categoría: ${revista.categoria}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Fecha de publicación: ${revista.fechaPublicacion}</h4>
                            <input type="hidden" name="codigo" value="${revista.codigo}">
                        </div>
                        <form method="GET" action="${pageContext.servletContext.contextPath}/archivoSvt" enctype="multipart/form-data">     
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
                        <br>
                        <table>
                            <tr>
                                <!-- Botón Nuevo tomo (siempre visible) -->
                                <th><button onclick="mostrarFormulario('formulario${status.index}')" class="btn btn-primary btn-lg">Nuevo tomo</button></th>
                                <!-- Botones de Likes -->
                                <th>
                                    <c:choose>
                                        <c:when test="${revista.estadoMeGustas}">
                                            <button class="btn btn-primary btn-lg" type="button">Deshabilitar Me gustas</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-primary btn-lg" type="button">Habilitar Me gustas</button>
                                        </c:otherwise>
                                    </c:choose>
                                </th>
                                <!-- Botones de Comentarios -->
                                <th>
                                    <c:choose>
                                        <c:when test="${revista.estadoComentarios}">
                                            <button class="btn btn-primary btn-lg" type="button">Deshabilitar Comentarios</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-primary btn-lg" type="button">Habilitar Comentarios</button>
                                        </c:otherwise>
                                    </c:choose>
                                </th>
                                <!-- Botones de Suscripciones -->
                                <th>
                                    <c:choose>
                                        <c:when test="${revista.estadoSuscripciones}">
                                            <button class="btn btn-primary btn-lg" type="button">Deshabilitar Suscripciones</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-primary btn-lg" type="button">Habilitar Suscripciones</button>
                                        </c:otherwise>
                                    </c:choose>
                                </th>
                            </tr>
                        </table>
                        <br>
                        <div id="formulario${status.index}" class="formulario">
                            <form method="POST" action="${pageContext.servletContext.contextPath}/archivoSvt" enctype="multipart/form-data">     
                                <label for="nombre">Nombre del archivo:</label>
                                <input type="text" id="nombre" name="nombre" required>
                                <br><br>
                                <label for="archivo">Seleccionar archivo PDF:</label>
                                <input type="file" id="archivo" name="archivo" accept=".pdf" required>
                                <br>
                                <input type="hidden" name="codigo" value="${revista.codigo}">
                                <!-- Mostrar el código para depuración -->
                                <button type="submit" class="btn btn-success">Subir tomo</button>
                                <br>
                            </form>
                        </div>
                        <hr>
                    </c:forEach>
                </div>
            </div>

            <br>
            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Reportes</h1>
                    <p class="col-md-8 fs-4">Seleccione el reporete que desea ver:</p>
                    <br><!-- comment -->
                    <table>
                        <tr>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de comentarios</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de suscripciones</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de las 5 revistas más gustadas</button> </th>
                        </tr>
                    </table>
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
