<%-- 
    Document   : registrarCuenta
    Created on : 9 sept 2024, 1:41:40
    Author     : xavi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo usuario</title>
    </head>
    <body>
        <%-- 
 method="POST" action="${pageContext.servletContext.contextPath}/mvc/solicitudes/solicitudes-servlet"
--%>
        
        <form method="POST" action="crearUsuarioSvt">
        <table>
        <!-- Fila para el campo de nombre de usuario -->
        <tr>
            <td><label for="user">Nuevo usuario:</label></td>
            <td><input id="user" name="newUser" type="text" maxlength="100" placeholder="Ingresa el nombre de usuario" required /></td>
        </tr>

        <!-- Fila para el campo de contraseña -->
        <tr>
            <td><label for="password">Contraseña:</label></td>
            <td><input id="password" name="newPassword" type="password" maxlength="100" minlength="8" placeholder="Ingresa tu contraseña" required /></td>
        </tr>

        <!-- Fila para el campo de confirmación de contraseña -->
        <tr>
            <td><label for="ConPassword">Confirmar contraseña:</label></td>
            <td><input id="ConPassword" name="ConfirmarPassword" type="password" maxlength="100" minlength="8" placeholder="Repite tu contraseña" required /></td>
        </tr>

        <!-- Fila para el tipo de usuario -->
        <tr>
            <td><label for="tipo">Tipo de usuario:</label></td>
            <td>
                <select id="tipo" name="tipo" required>
                    <option value="" disabled selected>Selecciona el tipo de usuario</option>
                    <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                    <option value="AUTOR">AUTOR</option>
                    <option value="LECTOR">LECTOR</option>
                    <option value="PUBLICISTA">PUBLICISTA</option>
                </select>
            </td>
        </tr>

        <!-- Fila para el campo de billetera digital -->
        <tr>
            <td><label for="billetera">Billetera Digital (monto inicial):</label></td>
            <td><input id="billetera" name="billetera" type="number" min="1" step="0.01" placeholder="Monto inicial" required /></td>
        </tr>

        <!-- Fila para el botón de enviar -->
        <tr>
            <td colspan="2" style="text-align: center;">
                <button type="submit">Crear Usuario</button>
            </td>
        </tr>
    </table>
</form>
            
    </body>
</html>
