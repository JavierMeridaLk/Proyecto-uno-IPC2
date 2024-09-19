/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Revistas;

import backen.DataBase.conexionDB;
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
public class archivoPdf {
    private String nombre;
    private int noRevista;
    private byte[] archivo;

    public archivoPdf() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNoRevista() {
        return noRevista;
    }

    public void setNoRevista(int noRevista) {
        this.noRevista = noRevista;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }
    
     public void guardarTomo(archivoPdf tomo) throws SQLException {
        String sql = "INSERT INTO TOMO (no_revista, nombre_archivo, archivo) VALUES (?, ?, ?)";
         conexionDB conexion = new conexionDB();
         try {
             PreparedStatement stmt = conexion.getConnection().prepareStatement(sql);
             stmt.setInt(1, tomo.getNoRevista());
            stmt.setString(2, tomo.getNombre());
            stmt.setBytes(3, tomo.getArchivo());
            
            stmt.executeUpdate();
         } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexion.cerrarConnection(conexion.getConnection());
            }
 
        
    }
     public List<archivoPdf> obtenerTomos(int noRevista) throws SQLException {
         
        List<archivoPdf> tomos = new ArrayList<>();
        String sql = "SELECT nombre_archivo, archivo FROM TOMO WHERE no_revista = ?";
        
        conexionDB conexion = new conexionDB();
        try (Connection conn = conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, noRevista);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    archivoPdf tomo = new archivoPdf();
                    tomo.setNombre(rs.getString("nombre_archivo"));
                    tomo.setArchivo(rs.getBytes("archivo"));
                    tomos.add(tomo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            conexion.cerrarConnection(conexion.getConnection());
        }
        return tomos;
     }
        
    
}
