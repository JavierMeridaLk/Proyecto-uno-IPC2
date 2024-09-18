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
    </head>
    <body>
        <jsp:include page="/includes/Headers.jsp"/>

        <div class="barra-izquierda">
            <jsp:include page="/includes/barraAI.jsp"/>
        </div>

        <div class="container">

            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Revistas Disponibles 📖 </h1>
                    <p class="col-md-8 fs-4">¡Bienvenido a nuestra plataforma! Nos alegra que estés aquí. 
                        Explora, disfruta y encuentra contenido creado especialmente para ti. Si necesitas 
                        algo, estamos para ayudarte en cada paso del camino. ¡Que disfrutes tu experiencia! 🌟</p>
                    <br><!-- comment -->

                    <c:forEach items="${revistas}" var="revista">
                        <!-- Aquí puedes usar la variable 'revista' para acceder a las propiedades -->
                        <div class="card-body">
                            <h2 class="card-title">Revista: ${revista.nombreRevista}</h2>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Descripción: ${revista.descripcion}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Categoría: ${revista.categoria}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Autor: ${revista.nombreAutor}</h4>
                            <h4 class="card-subtitle mb-2 text-body-secondary">Me gustas: </h4>
                        </div>
                        <table>
                            <tr>
                                <!-- Botones de Me gustas -->
                                <th>
                                    <c:choose>
                                        <c:when test="${revista.estadoMeGustas}">
                                            <button class="btn btn-primary btn-lg" type="button">Me gusta</button>
                                        </c:when>
                                    </c:choose>
                                </th>
                                <!-- Botones de Comentarios -->
                                <th>
                                    <c:choose>
                                        <c:when test="${revista.estadoComentarios}">
                                            <button class="btn btn-primary btn-lg" type="button">Comentar</button>
                                        </c:when>
                                    </c:choose>
                                </th>
                                <!-- Botones de Suscripciones -->
                                <th>
                                    <c:choose>
                                        <c:when test="${revista.estadoSuscripciones}">
                                            <button class="btn btn-primary btn-lg" type="button">Suscribirse</button>
                                        </c:when>
                                    </c:choose>
                                </th>
                            </tr>
                        </table>
                        <hr>
                    </c:forEach>

                </div>
            </div>
            <br>
            <div class="p-5 mb-4 bg-body-tertiary rounded shadow-lg">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">Tus revistas suscritas 📚 </h1>
                    <p class="col-md-8 fs-4">¡Aquí tienes acceso a todas las revistas a las que te has suscrito! 
                        Disfruta de tu contenido favorito y mantente al día con las últimas publicaciones. 
                        ¡Gracias por ser parte de nuestra comunidad! 😊</p>
                    <br><!-- comment -->

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
