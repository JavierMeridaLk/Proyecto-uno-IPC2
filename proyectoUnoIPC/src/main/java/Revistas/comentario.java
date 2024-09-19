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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xavi
 */
public class comentario {

    private String contenido;
    private String userCom;
    private String nomRevista;

    public comentario() {

    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getUserCom() {
        return userCom;
    }

    public void setUserCom(String userCom) {
        this.userCom = userCom;
    }

    public String getNomRevista() {
        return nomRevista;
    }

    public void setNomRevista(String nomRevista) {
        this.nomRevista = nomRevista;
    }
    
    public List<comentario> obtenerComentarios(){
        List<comentario> comentarios = new ArrayList<>();
        conexionDB conexion = new conexionDB();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT c.contenido, c.user_com, r.nombre_revista FROM COMENTARIO c JOIN REVISTA r ON c.no_revista_com = r.cod_revista";
            
            
            ps = conexion.getConnection().prepareStatement(sql);
            
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                comentario comentario = new comentario();
                comentario.setContenido(rs.getString("contenido"));
                comentario.setUserCom(rs.getString("user_com"));
                comentario.setNomRevista(rs.getString("nombre_revista"));
                
                comentarios.add(comentario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error en la base de datos: " + e.getMessage());
        } finally {
            conexion.cerrarConnection(conexion.getConnection());
        }
        return comentarios;
    }

}
