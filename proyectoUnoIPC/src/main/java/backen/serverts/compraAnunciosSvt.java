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
import static java.lang.System.out;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author xavi
 */
@WebServlet(name = "compraAnunciosSvt", urlPatterns = {"/compraAnunciosSvt"})
public class compraAnunciosSvt extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        String  userName="";
        if (session != null) {
                userName = (String) session.getAttribute("userName");
                if (userName != null) {
                    out.println("Bienvenido, " + userName);
                } else {
                    out.println("No estás logueado.");
                }
            } else {
                out.println("No estás logueado.");
            }
        
        
        String tipoAnuncio = request.getParameter("tipoAnuncio");
       
        String tiempoVigente = request.getParameter("tiempoAnuncio");
        Date fechaPago = Date.valueOf(request.getParameter("fecha"));
        
        BigDecimal precio = BigDecimal.ZERO;
        conexionDB conexion = new conexionDB();
        
        // Obtener conexión a la base de datos
        try {
            // Consultar el precio del anuncio basado en el tiempo vigente
            
            
            String query = "SELECT precio_un_dia, precio_tres_dias, precio_una_semana, precio_dos_semanas FROM ANUNCIO WHERE tipo_anuncio = ?";
            PreparedStatement ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, tipoAnuncio);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                switch (tiempoVigente) {
                    case "1_dia":
                        precio = rs.getBigDecimal("precio_un_dia");
                        break;
                    case "3_dias":
                        precio = rs.getBigDecimal("precio_tres_dias");
                        break;
                    case "1_semana":
                        precio = rs.getBigDecimal("precio_una_semana");
                        break;
                    case "2_semanas":
                        precio = rs.getBigDecimal("precio_dos_semanas");
                        break;
                    default:
                       
                }
            }

            // Insertar la compra en la tabla COMPRAR
            String insertCompra = "INSERT INTO COMPRAR (usuario, anuncio, fecha_pago, precio) "
                                + "VALUES (?, ?, ?, ?)";
            PreparedStatement psInsert = conexion.getConnection().prepareStatement(insertCompra);
            psInsert.setString(1, userName);
            psInsert.setString(2, tipoAnuncio);
            psInsert.setDate(3, fechaPago);
            psInsert.setBigDecimal(4, precio);

            int rowsAffected = psInsert.executeUpdate();

            if (rowsAffected > 0) {
                out.println("Compra realizada con éxito.");
            } else {
                response.getWriter().println("Error al realizar la compra.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
        } finally {
                conexion.cerrarConnection(conexion.getConnection());
            }
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
