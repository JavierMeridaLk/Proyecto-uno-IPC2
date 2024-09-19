/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package backen.serverts;

import Revistas.archivoPdf;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import jakarta.servlet.annotation.MultipartConfig;
import java.util.List;

@WebServlet(name = "archivoSvt", urlPatterns = {"/archivoSvt"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class archivoSvt extends HttpServlet {

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
                response.sendRedirect(request.getContextPath() + "/nuevaRevistasSvt");
            } else {
                // Si hay tomos, enviarlos a la JSP para mostrarlos
                request.setAttribute("tomos", tomos);
                request.getRequestDispatcher("/nuevaRevistasSvt").forward(request, response);

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
        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        int noRevista = Integer.parseInt(request.getParameter("codigo"));

        // Obtener el archivo desde el input tipo "file"
        Part filePart = request.getPart("archivo");
        InputStream inputStream = null;

        if (filePart != null) {
            // Obtener el contenido del archivo en formato InputStream
            inputStream = filePart.getInputStream();
        }

        // Convertir el InputStream en un arreglo de bytes (byte[])
        byte[] archivoBytes = inputStream.readAllBytes();

        // Crear una instancia de archivoPdf
        archivoPdf nuevoTomo = new archivoPdf();
        nuevoTomo.setNombre(nombre);
        nuevoTomo.setNoRevista(noRevista);
        nuevoTomo.setArchivo(archivoBytes);

        // Guardar el nuevo tomo utilizando el DAO
        archivoPdf tomo = new archivoPdf();
        try {
            tomo.guardarTomo(nuevoTomo);
            // Redirigir o mostrar un mensaje de éxito
            response.sendRedirect(request.getContextPath()+"/nuevaRevistasSvt");
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
            
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
