/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backen.serverts;

import backen.usuarios.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        processRequest(request, response);
        Usuario user = new Usuario();
        user.setUserName(request.getParameter("User"));
        user.setPassword(request.getParameter("Password"));
        if (user.iniciarSesion(user)) {
            System.out.println("inicio sesion");
            response.sendRedirect("index.jsp");
        }else{
            response.sendRedirect("Login.jsp?error=true");
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
