<%-- 
    Document   : Login
    Created on : 10 sept 2024, 1:50:46
    Author     : xavi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Iniciar sesion</h1>
        <form method="POST" action="inicioDeSesion">
            <table>
            <!-- Fila para el campo de nombre de usuario -->
            <tr>
                <td><label for="user">Usuario:</label></td>
                <td><input id="user" name="User" type="text" maxlength="100" placeholder="Ingrese tu usuario" required /></td>
            </tr>

            <!-- Fila para el campo de contraseña -->
            <tr>
                <td><label for="password">Contraseña:</label></td>
                <td><input id="password" name="Password" type="password" maxlength="100" minlength="8" placeholder="Ingresa tu contraseña" required /></td>
              <!-- Fila para el botón de enviar -->
            <tr>
                <td colspan="2" style="text-align: center;">
                    <button type="submit">Iniciar sesion</button>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <a href="registrarCuenta.jsp">Crea una cuenta nueva</a>
                </td>
                
            </tr>
        </table>
            
    </form>
    </body>
</html>
