/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backen.serverts;

import Revistas.Revista;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author xavi
 */
@WebServlet(name = "actualizarRevista", urlPatterns = {"/actualizarRevista"})
public class actualizarRevista extends HttpServlet {

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

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener parámetros del formulario
        int codRevista = Integer.parseInt(request.getParameter("codigo"));
        String accion = request.getParameter("accion");

        // Determinar qué acción realizar
        try {
            Revista revista = new Revista();  // Asumiendo que tienes un modelo "Revista"
            switch (accion) {
                case "meGustas":
                    revista.actualizarEstadoMeGustas(codRevista);
                    break;
                case "comentarios":
                    revista.actualizarEstadoComentario(codRevista);
                    break;
                case "suscripciones":
                    revista.actualizarEstadoSuscripciones(codRevista);
                    break;
            }

            // Redirigir a la página original o mostrar un mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/nuevaRevistasSvt");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al actualizar los estados.");
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
