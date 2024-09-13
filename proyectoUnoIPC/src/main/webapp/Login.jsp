<%-- 
    Document   : Login
    Created on : 10 sept 2024, 1:50:46
    Author     : xavi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">
        <title>Inicar sesion</title>
        <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">
        <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="signin.css" rel="stylesheet">
        <style>
              .form-signin {
                max-width: 330px;
                padding: 15px;
                margin: auto;
              }
             .form-signin .form-floating:focus-within {
                z-index: 2;
              }
        </style>
    </head>
    <body class="text-center">

        <form class="form-signin" method="POST" action="inicioDeSesion">
            <div>   
                <img class="mb-4" src="${pageContext.request.contextPath}/resources/img/logo.jpg" alt="" width="72" height="72">
                <h1 class="h3 mb-3 font-weight-normal">Iniciar sesion</h1>
            </div> 
            <br>
            <div>    
                <label for="inputEmail" class="sr-only">Usuario</label>
                <input type="text" id="user" name="User" class="form-control" placeholder="Usuario" required autofocus="" maxlength="20">
            </div>  
            <br>
            <div>          
                <label for="inputPassword" class="sr-only">Contraseña</label>
                <input type="password" id="password" name="Password" class="form-control" placeholder="Contraseña" maxlength="100" minlength="8" required>
            </div>
            <br>
            <div>   
                <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar Sesion</button>
            </div>   
            <div>   
                <br>
                <a href="registrarCuenta.jsp">Crea una cuenta nueva</a>
            </div>   
            <p class="mt-5 mb-3 text-muted">© 2017-2018</p>
        </form>
    </body>
</html>
