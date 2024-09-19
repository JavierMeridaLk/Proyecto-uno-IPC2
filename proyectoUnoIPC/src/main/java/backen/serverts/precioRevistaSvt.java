/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backen.serverts;

import Revistas.Revista;
import backen.DataBase.conexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xavi
 */
@WebServlet(name = "precioRevistaSvt", urlPatterns = {"/precioRevistaSvt"})
public class precioRevistaSvt extends HttpServlet {

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

        try {
                        Revista revista = new Revista();

            List<Revista> revistas = revista.obtenerTodasLasRevistas();
            request.setAttribute("revistas", revistas);
            request.getRequestDispatcher("/admin/adminJsp.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
        }
    }
        // Configurar el tipo de contenido
       

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
        int codigoRevista = Integer.parseInt(request.getParameter("revista"));
        double nuevoPrecio = Double.parseDouble(request.getParameter("precioRevista"));

        try {
            actualizarPrecioRevista(codigoRevista, nuevoPrecio);
            response.sendRedirect(request.getContextPath() + "/precioRevistaSvt");


        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp"); // Redirige a una página de error
        }
    }

    private void actualizarPrecioRevista(int codigoRevista, double nuevoPrecio) throws SQLException {
        conexionDB conexion = new conexionDB();
        PreparedStatement ps = null;

        try {
            String query = "UPDATE REVISTA SET costo = ? WHERE cod_revista = ?";
            ps = conexion.getConnection().prepareStatement(query);
            ps.setDouble(1, nuevoPrecio);
            ps.setInt(2, codigoRevista);
            ps.executeUpdate();
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
