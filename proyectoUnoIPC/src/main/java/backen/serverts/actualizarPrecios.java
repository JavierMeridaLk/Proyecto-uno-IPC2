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
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author xavi
 */
@WebServlet(name = "actualizarPrecios", urlPatterns = {"/actualizarPrecios"})
public class actualizarPrecios extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
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
        
        String tipoAnuncio = request.getParameter("tipoAnuncio");
        String tiempoAnuncio = request.getParameter("tiempoAnuncio");
        BigDecimal nuevoPrecio = new BigDecimal(request.getParameter("precioNuevo"));
        
        // Define el campo que se actualizará basado en el período seleccionado
        String columnaPrecio = "";
        switch (tiempoAnuncio) {
            case "precio_un_dia":
                columnaPrecio = "precio_un_dia";
                break;
            case "precio_tres_dias":
                columnaPrecio = "precio_tres_dias";
                break;
            case "precio_una_semana":
                columnaPrecio = "precio_una_semana";
                break;
            case "precio_dos_semanas":
                columnaPrecio = "precio_dos_semanas";
                break;
            default:
                throw new ServletException("Período de anuncio no válido.");
        }

        String updateQuery = "UPDATE ANUNCIO SET " + columnaPrecio + " = ? WHERE tipo_anuncio = ?";
conexionDB conexion = new conexionDB();
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {
            
            ps.setBigDecimal(1, nuevoPrecio);
            ps.setString(2, tipoAnuncio);
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                response.sendRedirect("admin/adminJsp.jsp"); // Redirige a una página de éxito
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
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
