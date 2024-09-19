<%-- 
    Document   : lectorJsp
    Created on : 15 sept 2024, 16:47:01
    Author     : xavi
--%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page import="backen.anuncios.Anuncio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Publicista</title>
        <style>

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
                    <form class="compra-anuncio" method="POST" action="${pageContext.servletContext.contextPath}/compraAnunciosSvt">
                        <h1 class="display-5 fw-bold">Compra de Anuncios</h1>
                        
                            
                            <p class="col-md-8 fs-4">Tabla de precios:</p>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Tipo de anuncio</th>
                                        <th scope="col">1 Día</th>
                                        <th scope="col">3 Días</th>
                                        <th scope="col">1 Semana</th>
                                        <th scope="col">2 Semanas</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="anuncios" items="${anunciosPre}">
                                    <tr>
                                        <th scope="row">${anuncios.tipoAnuncio}</th>
                                        <td>Q.${anuncios.precioUnDia}</td> 
                                        <td>Q.${anuncios.precioTresDias}</td> 
                                        <td>Q.${anuncios.precioUnaSemana}</td> 
                                        <td>Q.${anuncios.precioDosSemanas}</td> 
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            
                            </form>
                            
                            <p class="col-md-8 fs-4">Seleccione el tipo de anuncio y el tiempo que estará vigente:</p>
                            <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" id="tipoAnuncio" name="tipoAnuncio" required >
                                <option selected>Seleccione un tipo de Anuncio</option>
                                <option value="TEXTO">TEXTO</option>
                                <option value="IMGTXT">TEXTO E IMAGEN</option>
                                <option value="VIDEO">VIDEO</option>
                            </select>
                            <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" id="tiempoAnuncio" name="tiempoAnuncio" required >
                                <option selected>Seleccione el tiempo vigente</option>
                                <option value="1_dia">1 día</option>
                                <option value="3_dias">3 días</option>
                                <option value="1_semana">1 semana</option>
                                <option value="2_semanas">2 semanas</option>
                            </select>
                            <br>
                            <label for="fecha">Fecha de compra:</label>
                            <input class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" required type="date" id="fecha" name="fecha"/>
                            <br>
                            <button class="btn btn-primary btn-lg" type="submit">Comprar anuncio</button>
                    
                </div>
            </div>

            <br><!-- comment -->

            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <form >
                        <h1 class="display-5 fw-bold">Anuncios activos</h1>
                        <p class="col-md-8 fs-4">Listado de anuncios activos. Puede desactivarlos cuando lo desee.</p>


                        <br>
                        <br>
                        <%
                            Anuncio[] anuncios = (Anuncio[]) request.getAttribute("anuncios");
                            if (anuncios != null && anuncios.length > 0) {
                        %>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">Tipo de anuncio</th>
                                    <th scope="col">Fecha de compra</th>
                                    <th scope="col">Fecha limite</th>
                                    <th scope="col">Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Anuncio anuncio : anuncios) {%>
                                <tr>
                                    <td><%= anuncio.getAnuncio()%></td>
                                    <td><%= anuncio.getFechaPago()%></td>
                                    <td><%= anuncio.getFechaLimite()%></td>
                                    <td>Activo</td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <%
                        } else {
                        %>
                        <p>No se encontraron anuncios activos.</p>
                        <%
                            }
                        %>
                    </form>
                </div>
            </div>
        </div>
        <!-- Barra lateral derecha fija -->
        <div class="barra-derecha">
            <jsp:include page="/includes/barraAD.jsp"/>
        </div>
        <!-- Footer -->
        <jsp:include page="/includes/Footer.jsp"/>
    </body>
</html>
