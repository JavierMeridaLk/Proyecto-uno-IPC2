/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Revistas;

import backen.DataBase.conexionDB;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author xavi
 */
public class Revista {

    private String nombreRevista;
    private String nombreAutor;
    private LocalDate fechaPublicacion;
    private String descripcion;
    private String categoria;
    private boolean estadoMeGustas;
    private boolean estadoComentarios;
    private boolean estadoSuscripciones;
    private Double costo;

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Revista() {
        estadoMeGustas = true;
        estadoComentarios = true;
        estadoSuscripciones = true;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nobreRevista) {
        this.nombreRevista = nobreRevista;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isEstadoMeGustas() {
        return estadoMeGustas;
    }

    public void setEstadoMeGustas(boolean estadoMeGustas) {
        this.estadoMeGustas = estadoMeGustas;
    }

    public boolean isEstadoComentarios() {
        return estadoComentarios;
    }

    public void setEstadoComentarios(boolean estadoComentarios) {
        this.estadoComentarios = estadoComentarios;
    }

    public boolean isEstadoSuscripciones() {
        return estadoSuscripciones;
    }

    public void setEstadoSuscripciones(boolean estadoSuscripciones) {
        this.estadoSuscripciones = estadoSuscripciones;
    }

    public List<Revista> obtenerTodasLasRevistas(String user) throws SQLException {
        List<Revista> revistas = new ArrayList<>();
        conexionDB conexion = new conexionDB();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM REVISTA WHERE user_autor = ?";
            ps = conexion.getConnection().prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                Revista revista = new Revista();
                revista.setNombreRevista(rs.getString("nombre_revista"));
                revista.setFechaPublicacion(rs.getDate("fecha_publicacion").toLocalDate());
                revista.setDescripcion(rs.getString("descripcion"));
                revista.setCategoria(rs.getString("categoria"));
                revista.setEstadoComentarios(rs.getBoolean("estado_comentario"));
                revista.setEstadoMeGustas(rs.getBoolean("estado_me_gustas"));
                revista.setEstadoSuscripciones(rs.getBoolean("estado_suscripciones"));
                revistas.add(revista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error en la base de datos: " + e.getMessage());
        } finally {
            conexion.cerrarConnection(conexion.getConnection());
        }
        return revistas;
    }
}
