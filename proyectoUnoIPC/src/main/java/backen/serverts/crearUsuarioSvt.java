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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xavi
 */
@WebServlet(name = "crearUsuarioSvt", urlPatterns = {"/crearUsuarioSvt"})
public class crearUsuarioSvt extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario nuevoUser = new Usuario();
        String user = request.getParameter("newUser");
        String password = request.getParameter("newPassword");
        String passwordConfirm = request.getParameter("ConfirmarPassword");
        String tipo = request.getParameter("tipo");
        double billetera = Double.parseDouble(request.getParameter("billetera"));
        
        if (password.equals(passwordConfirm)) {
            
            System.out.println("coincide");
            if (nuevoUser.existeUsuario(user)) {
                response.sendRedirect("registrarCuenta.jsp");
                System.out.println("existente");
            }else{
                try {
                    nuevoUser.crearUsuario(user, password, passwordConfirm, tipo, billetera);
                    response.sendRedirect("Login.jsp");
                } catch (SQLException ex) {
                    Logger.getLogger(crearUsuarioSvt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }else{
            response.sendRedirect("registrarCuenta.jsp");
            System.out.println("no coincide");
        }
        
        
        
           
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
