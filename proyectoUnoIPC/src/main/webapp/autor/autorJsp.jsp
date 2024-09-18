<%-- 
    Document   : autorJSP
    Created on : 17 sept 2024, 16:12:04
    Author     : xavi
--%>

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
        </style>
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
            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Nombre revista</h1>
                    <table>
                        <tr>
                            <th> <button class="btn btn-primary btn-lg" type="button">Subir nuevo tomo</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Habilitar Likes</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Habilitar Comentarios</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Habilitar Suscripciones</button> </th>

                            <th> <button class="btn btn-primary btn-lg" type="button">Deshabilitar Me gustas</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Deshabilitar Comentarios</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Deshabilitar Suscripciones</button> </th>
                        </tr>
                    </table>
                </div>
            </div>
            <br><!-- comment -->
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
