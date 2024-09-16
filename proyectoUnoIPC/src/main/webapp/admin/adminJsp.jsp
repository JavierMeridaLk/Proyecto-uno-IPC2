<%-- 
    Document   : adminJsp
    Created on : 15 sept 2024, 18:51:20
    Author     : xavi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
    </head>
    <body>
        <jsp:include page="/includes/Headers.jsp"/>

        <div class="barra-izquierda">
            <jsp:include page="/includes/barraAI.jsp"/>
        </div>

        <div class="container">
            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Precios de los Anuncios</h1>
                    <form>
                        <p class="col-md-8 fs-4">Seleccione el tipo de anuncio, el tiempo y su nuevo precio:</p>
                        <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" id="tipoAnuncio" name="tipoAnuncio" required >
                            <option selected>Seleccione un tipo de Anuncio</option>
                            <option value="TEXTO">TEXTO</option>
                            <option value="TEXTO E IMAGEN">TEXTO E IMAGEN</option>
                            <option value="VIDEO">VIDEO</option>
                        </select>
                        <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" id="tiempoAnuncio" name="tiempoAnuncio" required >
                            <option selected>Seleccione un tipo de Anuncio</option>
                            <option value="TEXTO">1 día</option>
                            <option value="TEXTO E IMAGEN">3 días</option>
                            <option value="VIDEO">1 semana</option>
                            <option value="VIDEO">2 semanas</option>
                        </select>

                        <label for="billetera" class="sr-only">Nuevo Precio:</label>
                        <input id="precioNuevo" name="precioNuevo" type="number" min="1" step="1" class="form-control" placeholder="Precio nuevo" required >
                        <br><!-- comment -->
                        <button class="btn btn-primary btn-lg" type="button">Actualizar precio</button>
                    </form>
                </div>
            </div>
            <br><!-- comment -->
            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Costo de Revista</h1>

                    <p class="col-md-8 fs-4">Seleccione la revista e ingrese su costo por día:</p>
                    <form>
                        <label for="revista" class="sr-only">Nombre de la revista</label>
                        <input id="nomRevista" name="nomRevista" type="text" class="form-control" placeholder="Ingrese el nombre" required >
                        <br><!-- comment -->
                        <button class="btn btn-primary btn-lg" type="button">Buscar</button>
                    </form>
                    <br><!-- comment -->
                    <form>
                        <label for="precio" class="sr-only">Precio de la revista por día</label>
                        <input id="precioRevista" name="precioRevista" type="number" min="1" step="1" class="form-control" placeholder="Precio de la revista" required >
                        <br><!-- comment -->
                        <button class="btn btn-primary btn-lg" type="button">Actualizar precio</button>
                    </form>

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
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de ganancias</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de anuncios comprados</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de las 5 revistas más populares</button> </th>
                            <th> <button class="btn btn-primary btn-lg" type="button">Reporte de las 5 revistas más comentadas</button> </th>
                        </tr>
                    </table>

                </div>
            </div>
            <br><!-- comment -->
        </div>

        <div class="barra-derecha">
            <jsp:include page="/includes/barraAD.jsp"/>
        </div>
        <!-- Footer -->
        <jsp:include page="/includes/Footer.jsp"/>
    </body>
</html>
