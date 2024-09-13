<%-- 
    Document   : registrarCuenta
    Created on : 9 sept 2024, 1:41:40
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
        <title>Nueva cuenta</title>
        <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">
        <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="signin.css" rel="stylesheet">
        <style>
            .form-register {
                max-width: 330px;
                padding: 15px;
                margin: auto;
            }
            .form-register .form-floating:focus-within {
                z-index: 2;
            }

        </style>

    </head>
    <body class="text-center">

        <form class="form-register" method="POST" action="crearUsuarioSvt">
            <div >   
                <img class="mb-4" src="${pageContext.request.contextPath}/resources/img/logo.jpg" alt="" width="72" height="72">
                <h1 class="h3 mb-3 font-weight-normal">Nueva Cuenta</h1>
            </div> 
            <br>
            <div>    
                <label for="user" class="sr-only">Usuario</label>
                <input type="text" id="user" name="newUser" class="form-control" placeholder="Nombre del usuario" required autofocus="" maxlength="20">
            </div>  
            <br>
            <div>          
                <label for="password" class="sr-only">Nueva contraseña</label>
                <input type="password" id="password" name="newPassword" class="form-control" placeholder="Contraseña" maxlength="100" minlength="8" required>
            </div>   
            <br>
            <div>          
                <label for="ConPassword" class="sr-only">Confirmar contraseña:</label>
                <input type="password" id="ConPassword" name="ConfirmarPassword" class="form-control" placeholder="Confirma contraseña" maxlength="100" minlength="8" required>
            </div>  
            <br>
            <div>          
                <label for="tipo" class="sr-only">Tipo de usuario</label>
                <select id="tipo" name="tipo" required class="form-control">
                    <option value="" disabled selected>Seleccione un tipo</option>
                    <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                    <option value="AUTOR">AUTOR</option>
                    <option value="LECTOR">LECTOR</option>
                    <option value="PUBLICISTA">PUBLICISTA</option>
                </select>
            </div>        
            <br>
            <div>    
                <label for="billetera" class="sr-only">Billetera Digital</label>
                <input id="billetera" name="billetera" type="number" min="1" step="1" class="form-control" placeholder="Monto inicial" required >
            </div>  
            <br>
            <div>   
                <button class="btn btn-lg btn-primary btn-block" type="submit">Nueva cuenta</button>
            </div>   
            <p class="mt-5 mb-3 text-muted">© 2017-2018</p>    
        </form>

    </body>
</html>
