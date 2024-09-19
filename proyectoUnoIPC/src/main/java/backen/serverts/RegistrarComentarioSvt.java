/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backen.serverts;

import backen.DataBase.conexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author xavi
 */
@WebServlet(name = "RegistrarComentarioSvt", urlPatterns = {"/RegistrarComentarioSvt"})
public class RegistrarComentarioSvt extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String contenido = request.getParameter("contenido");
        int no_revista_com = Integer.parseInt(request.getParameter("no_revista_com"));
        HttpSession session = request.getSession(false);
        String userName = "";
        if (session != null) {
            userName = (String) session.getAttribute("userName");
        }
        if (userName == null || userName.isEmpty()) {
            response.getWriter().println("No estás logueado.");
            return;
        }
        

        PreparedStatement ps = null;
        conexionDB conexion = new conexionDB();

        try {
            
            String query = "INSERT INTO COMENTARIO (user_com, no_revista_com, contenido) VALUES (?, ?, ?)";
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, userName);
            ps.setInt(2, no_revista_com);
            ps.setString(3, contenido);

            ps.executeUpdate();
            response.sendRedirect(request.getContextPath()+"/lectorJsp");
             // Redirige a una página de éxito
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp");
        } finally {
            conexion.cerrarConnection(conexion.getConnection());
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
