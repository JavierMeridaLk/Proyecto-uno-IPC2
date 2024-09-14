<%-- 
    Document   : vistaAdmin
    Created on : 14 sept 2024, 2:59:15
    Author     : xavi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion</title>

       <style>
            /* Estilos generales */
            body, html {
                margin: 0;
                padding: 0;
                height: 100%;
                overflow-x: hidden; /* Evita el scroll horizontal */
            }

            .container {
                display: flex;
                justify-content: center; /* Centrar el contenido principal */
                margin: 0 auto;
                padding: 10px;
                height: calc(100vh - 120px); /* Ajustar al tamaño de la pantalla, menos el espacio para header y footer */
            }

            /* Estilos para las barras laterales */
            .barra-izquierda, .barra-derecha {
                position: fixed;
                top: 60px; /* Espacio para el header */
                height: 70vh; /* 70% de la altura de la pantalla */
                width: 20%;
                padding: 10px;
            }

            .barra-izquierda {
                left: 0;
                background-color: lightblue; /* Color de ejemplo para identificar */
            }

            .barra-derecha {
                right: 0;
                background-color: lightgreen; /* Color de ejemplo para identificar */
            }

            /* Estilos para el contenido principal */
            .contenido-principal {
                width: 55%; /* Ajusta el ancho del contenido principal */
                padding: 10px;
                background-color: lightgray;
                margin-left: 20%; /* Dejar espacio para la barra izquierda */
                margin-right: 20%; /* Dejar espacio para la barra derecha */
                height: calc(100vh - 120px); /* Ajustar al tamaño de la pantalla menos el header y footer */
            }

            /* Ajustes para header y footer */
            header {
                height: 60px; /* Altura fija para el header */
            }

            footer {
                height: 60px; /* Altura fija para el footer */
            }
        </style>

    </head>
    <body>
 <!-- Header -->
        <jsp:include page="/includes/Headers.jsp"/>

        <!-- Contenedor principal con flexbox -->
        <div class="container">
            <!-- Contenido principal en el centro -->
            <div class="contenido-principal">
                <h1>Vista admin</h1>
                <p>Este es el contenido de la vista de administración.</p>
                <p>El contenido puede desplazarse mientras las barras laterales permanecen fijas.</p>
            </div>
        </div>

        <!-- Barra lateral izquierda fija -->
        <div class="barra-izquierda">
            <jsp:include page="/includes/barraAI.jsp"/>
        </div>

        <!-- Barra lateral derecha fija -->
        <div class="barra-derecha">
            <jsp:include page="/includes/barraAD.jsp"/>
        </div>

        <!-- Footer -->
        <jsp:include page="/includes/Footer.jsp"/>
    </body>

</html>
