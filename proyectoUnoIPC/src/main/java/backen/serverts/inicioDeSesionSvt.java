/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backen.serverts;

import backen.DataBase.conexionDB;
import backen.usuarios.Usuario;
import enums.tipoUser;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author xavi
 */
@WebServlet(name = "inicioDeSesion", urlPatterns = {"/inicioDeSesion"})
public class inicioDeSesionSvt extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        processRequest(request, response);
        Usuario user = new Usuario();
        user.setUserName(request.getParameter("User"));
        user.setPassword(request.getParameter("Password"));
        String tipoUsuarioStr = "";

        if (user.iniciarSesion(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", user.getUserName());
            conexionDB conexion = new conexionDB();
            String query = "SELECT tipo_usuario FROM USUARIO WHERE BINARY user_name = ?";
            try {
                preparedStatement = conexion.getConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName()); // Asegúrate de obtener el nombre de usuario de `user`
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    tipoUsuarioStr = resultSet.getString("tipo_usuario");
                }
                
                if (tipoUsuarioStr.equals(tipoUser.ADMINISTRADOR.name())) {
                    response.sendRedirect("admin/adminJsp.jsp");
                } else if (tipoUsuarioStr.equals(tipoUser.AUTOR.name())) {
                    response.sendRedirect(request.getContextPath()+"/nuevaRevistasSvt");
                } else if (tipoUsuarioStr.equals(tipoUser.LECTOR.name())) {
                    response.sendRedirect(request.getContextPath()+"/lectorJsp");
                } else if (tipoUsuarioStr.equals(tipoUser.PUBLICISTA.name())) {
                    response.sendRedirect("publicista/publicistaJsp.jsp");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexion.cerrarConnection(conexion.getConnection());
            }
            System.out.println(user.getTipo());
        } else {
            response.sendRedirect("Login.jsp?error=true");
            System.out.println("no inicar sesion");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
