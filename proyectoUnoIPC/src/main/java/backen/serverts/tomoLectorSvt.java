package backen.serverts;

import Revistas.archivoPdf;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author xavi
 */
@WebServlet(name = "tomoLectorSvt", urlPatterns = {"/tomoLectorSvt"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class tomoLectorSvt extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            // Obtener lista de tomos (acción por defecto si no hay 'action')
            int noRevista = Integer.parseInt(request.getParameter("codigo"));
            archivoPdf tomo = new archivoPdf();
            try {
                List<archivoPdf> tomos = tomo.obtenerTomos(noRevista);
                // Verificar si hay tomos
                if (tomos.isEmpty()) {
                    // Si no hay tomos, redirigir a una página de error o notificación
                    response.sendRedirect(request.getContextPath() + "/lectorJsp");
                } else {
                    // Si hay tomos, enviarlos a la JSP para mostrarlos
                    request.setAttribute("tomos", tomos);
                    request.getRequestDispatcher("/lectorJsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Error al obtener los tomos.");
            }
        } else if ("verPdf".equals(action)) {
            // Visualizar archivo PDF
            String nombreArchivo = request.getParameter("nombre");
            int noRevista = Integer.parseInt(request.getParameter("codigo"));
            archivoPdf tomo = new archivoPdf();
            try {
                List<archivoPdf> tomos = tomo.obtenerTomos(noRevista);
                archivoPdf pdfTomo = null;
                // Buscar el archivo por nombre
                for (archivoPdf t : tomos) {
                    if (t.getNombre().equals(nombreArchivo)) {
                        pdfTomo = t;
                        break;
                    }
                }
                if (pdfTomo != null) {
                    // Mostrar el archivo PDF en el navegador
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "inline; filename=\"" + nombreArchivo + "\"");
                    response.getOutputStream().write(pdfTomo.getArchivo());
                } else {
                    // Si no se encuentra el archivo, mostrar un error 404
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Archivo no encontrado");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener el archivo");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
