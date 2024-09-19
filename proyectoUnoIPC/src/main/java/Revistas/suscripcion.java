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
public class suscripcion {
    private LocalDate fecha;
    private String userLec;
    private String nomRevista;

    public suscripcion() {

    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUserLec() {
        return userLec;
    }

    public void setUserLec(String userLec) {
        this.userLec = userLec;
    }

    public String getNomRevista() {
        return nomRevista;
    }

    public void setNomRevista(String nomRevista) {
        this.nomRevista = nomRevista;
    }
    public List<suscripcion> obtenerSubs(){
        
        List<suscripcion> suscripciones = new ArrayList<>();
        conexionDB conexion = new conexionDB();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.user_lector, s.fecha_suscripcion, r.nombre_revista\n" +
"FROM SUSCRIPCION s\n" +
"JOIN REVISTA r ON s.codigo_revista = r.cod_revista;";
            
            
            ps = conexion.getConnection().prepareStatement(sql);
            
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                suscripcion suscripcion = new suscripcion();
                suscripcion.setUserLec(rs.getString("user_lector"));
                suscripcion.setFecha(rs.getDate("fecha_suscripcion").toLocalDate());
                suscripcion.setNomRevista(rs.getString("nombre_revista"));
                
                suscripciones.add(suscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error en la base de datos: " + e.getMessage());
        } finally {
            conexion.cerrarConnection(conexion.getConnection());
        }
        return suscripciones;
    }
}
