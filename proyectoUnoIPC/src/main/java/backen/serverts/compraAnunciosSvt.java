/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backen.serverts;

import backen.DataBase.conexionDB;
import backen.anuncios.Anuncio;
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
import java.time.format.DateTimeFormatter;

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
        // Establecer el tipo de contenido de respuesta como HTML
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();

// Verificar que el usuario esté autenticado
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

        conexionDB conexion = new conexionDB();
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Primero contar el número de anuncios activos para el usuario
        int count = 0;

        try {
            String countQuery = "SELECT COUNT(*) FROM COMPRAR WHERE usuario = ? AND estado = true";
            ps = conexion.getConnection().prepareStatement(countQuery);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
            Anuncio[] anuncios = new Anuncio[count];
            rs.close(); // Cerrar el ResultSet anterior
            ps.close(); // Cerrar el PreparedStatement anterior
            
            
            // Consulta para obtener todas las compras donde el estado sea verdadero (1)
            String query = "SELECT anuncio, fecha_pago, estado, fecha_limite FROM COMPRAR WHERE usuario = ? AND estado = true";
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, userName);

            rs = ps.executeQuery();

            int i = 0;
            while (rs.next()) {
                String anuncio = rs.getString("anuncio");
                String fechaPago = rs.getString("fecha_pago");
                String estado = rs.getString("estado");
                String fechaLimite = rs.getString("fecha_limite");
                anuncios[i++] = new Anuncio(anuncio, fechaPago, estado, fechaLimite);
            }
            
            request.setAttribute("anuncios", anuncios);
            request.getRequestDispatcher("publicista/publicistaJsp.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error en la base de datos: " + e.getMessage());
        } finally {
            conexion.cerrarConnection(conexion.getConnection());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String userName = "";
        if (session != null) {
            userName = (String) session.getAttribute("userName");
            if (userName != null) {
                response.getWriter().println("Bienvenido, " + userName);
            } else {
                response.getWriter().println("No estás logueado.");
            }
        } else {
            response.getWriter().println("No estás logueado.");
        }

        String tipoAnuncio = request.getParameter("tipoAnuncio");
        String tiempoVigente = request.getParameter("tiempoAnuncio");

        // Cambia la fecha de String a LocalDate usando un formateador
        LocalDate fechaPago = LocalDate.parse(request.getParameter("fecha"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fechaLimite = null;

        BigDecimal precio = BigDecimal.ZERO;
        conexionDB conexion = new conexionDB();

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
                        fechaLimite = fechaPago.plusDays(1); // Sumar 1 día a la fecha de pago
                        break;
                    case "3_dias":
                        precio = rs.getBigDecimal("precio_tres_dias");
                        fechaLimite = fechaPago.plusDays(3); // Sumar 3 días
                        break;
                    case "1_semana":
                        precio = rs.getBigDecimal("precio_una_semana");
                        fechaLimite = fechaPago.plusWeeks(1); // Sumar 1 semana
                        break;
                    case "2_semanas":
                        precio = rs.getBigDecimal("precio_dos_semanas");
                        fechaLimite = fechaPago.plusWeeks(2); // Sumar 2 semanas
                        break;
                    default:
                    // Manejar caso por defecto
                }
            }

            // Convertir LocalDate a java.sql.Date para almacenar en la base de datos
            Date sqlFechaPago = Date.valueOf(fechaPago);
            Date sqlFechaLimite = Date.valueOf(fechaLimite);

            // Insertar la compra en la tabla COMPRAR
            String insertCompra = "INSERT INTO COMPRAR (usuario, anuncio, fecha_pago, precio, estado, fecha_limite) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psInsert = conexion.getConnection().prepareStatement(insertCompra);
            psInsert.setString(1, userName);
            psInsert.setString(2, tipoAnuncio);
            psInsert.setDate(3, sqlFechaPago); // Usar la fecha de pago convertida
            psInsert.setBigDecimal(4, precio);
            psInsert.setBoolean(5, true);
            psInsert.setDate(6, sqlFechaLimite); // Usar la fecha límite convertida

            int rowsAffected = psInsert.executeUpdate();

            if (rowsAffected > 0) {
                response.getWriter().println("Compra realizada con éxito.");
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
