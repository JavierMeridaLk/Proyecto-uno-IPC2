/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backen.serverts;
import Revistas.Revista;
import backen.DataBase.conexionDB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author xavi
 */
@WebServlet(name = "nuevaRevistasSvt", urlPatterns = {"/nuevaRevistasSvt"})
public class nuevaRevistasSvt extends HttpServlet {
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
        }
        if (userName == null || userName.isEmpty()) {
            response.getWriter().println("No estás logueado.");
            return;
        }
        try {
            Revista revista = new Revista();
            List<Revista> revistas = revista.obtenerTodasLasRevistasDelAutor(userName);
            // Establece las revistas en el request
            request.setAttribute("revistas", revistas);
            
            // Redirige a la JSP
            request.getRequestDispatcher("/autor/autorJsp.jsp").forward(request, response);
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
            if (userName == null) {
                out.println("No estás logueado.");
                return;
            }
        } else {
            out.println("No estás logueado.");
            return;
        }
        Revista revista = new Revista();
        // Obtener valores del formulario
        String nombreRevista = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");
        // Los checkboxes devuelven "on" si están seleccionados, por lo que se evalúa si no están seleccionados
        boolean meGustas = request.getParameter("meGustas") == null; // Si no está marcado, es null, por lo que es true para deshabilitar
        boolean comentarios = request.getParameter("comentarios") == null;
        boolean suscripciones = request.getParameter("suscripciones") == null;
        // Configurar los valores en la instancia de Revista
        revista.setNombreRevista(nombreRevista);
        revista.setNombreAutor(userName);
        revista.setFechaPublicacion(LocalDate.parse(fecha)); // Convertir la fecha a LocalDate
        revista.setDescripcion(descripcion);
        revista.setCategoria(categoria);
        revista.setEstadoMeGustas(meGustas); // Si el checkbox no está marcado, es true (deshabilitado)
        revista.setEstadoComentarios(comentarios); // Lo mismo para comentarios
        revista.setEstadoSuscripciones(suscripciones); // Y para suscripciones
        revista.setCosto(10.00);
        conexionDB conexion = new conexionDB();

        try {
            String query = "INSERT INTO REVISTA (user_autor, nombre_revista, categoria, descripcion, "
                    + "fecha_publicacion, costo,estado_comentario,estado_me_gustas,estado_suscripciones) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psInsert = conexion.getConnection().prepareStatement(query);
            psInsert.setString(1, revista.getNombreAutor());
            psInsert.setString(2, revista.getNombreRevista());
            psInsert.setString(3, revista.getCategoria());
            psInsert.setString(4, revista.getDescripcion());
            psInsert.setDate(5, java.sql.Date.valueOf(revista.getFechaPublicacion()));
            psInsert.setDouble(6, revista.getCosto());
            psInsert.setBoolean(7, revista.isEstadoComentarios());
            psInsert.setBoolean(8, revista.isEstadoMeGustas());
            psInsert.setBoolean(9, revista.isEstadoSuscripciones());

            int rowsAffected = psInsert.executeUpdate();
            if (rowsAffected > 0) {
                response.getWriter().println("Compra realizada con éxito.");

                request.getRequestDispatcher("autor/autorJsp.jsp").forward(request, response);

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
    }

}
