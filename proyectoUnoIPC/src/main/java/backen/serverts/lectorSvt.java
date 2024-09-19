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
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author xavi
 */
@WebServlet(name = "lectorJsp", urlPatterns = {"/lectorJsp"})
public class lectorSvt extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
HttpSession session = request.getSession(false);
        String userName = "";
        if (session != null) {
            userName = (String) session.getAttribute("userName");
            if (userName == null) {
                out.println("No estás logueado.");
                return;
            }
        } else {
            out.println("No estás logueado.");
            return;
        }
        try {
            Revista revista = new Revista();
            List<Revista> revistas = revista.obtenerTodasLasRevistas();
            // Establece las revistas en el request
            request.setAttribute("revistas", revistas);
            // Redirige a la JSP
            //request.getRequestDispatcher("/lector/lectorJsp.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al obtener las revistas.");
        }
        try {
            Revista revista = new Revista();
            List<Revista> revistas1 = revista.obtenerRevistasSuscritas(userName);
            // Establece las revistas en el request
            request.setAttribute("revistas1", revistas1);
            // Redirige a la JSP
            request.getRequestDispatcher("/lector/lectorJsp.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error al obtener las revistas.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String userName = "";
        if (session != null) {
            userName = (String) session.getAttribute("userName");
        }
        if (userName == null || userName.isEmpty()) {
            response.getWriter().println("No estás logueado.");
            return;
        }

        String fecha = request.getParameter("fecha");

        // Obtener el nombre del autor desde el campo oculto
        String codigo = request.getParameter("codigo");
        conexionDB conexion = new conexionDB();
        try {
            String query = "INSERT INTO SUSCRIPCION (user_lector, codigo_revista, fecha_suscripcion) "
                    + "VALUES (?, ?, ?)";
            PreparedStatement psInsert = conexion.getConnection().prepareStatement(query);
            psInsert.setString(1, userName);
            psInsert.setString(2, codigo);
            psInsert.setDate(3, java.sql.Date.valueOf(fecha));

            int rowsAffected = psInsert.executeUpdate();
            if (rowsAffected > 0) {
                response.getWriter().println("Suscripcion realizada con éxito.");

                response.sendRedirect(request.getContextPath() + "/lectorJsp");

            } else {
                response.getWriter().println("Error al realizar la suscripcion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
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
