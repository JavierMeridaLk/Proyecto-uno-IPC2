/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backen.usuarios;

import backen.DataBase.conexionDB;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author xavi
 */
public class Usuario {
    private String userName;
    private String password;
    private String passwordConfirm;
    private String tipo;
    private double billetera;
    PreparedStatement preparedStatement;
    
    public Usuario(){
        
    }
    
    public void crearUsuario(String user, String password, String passwordConfirm,String tipo, double billetera) throws SQLException{
        this.userName=user;
        this.password= password;
        this.passwordConfirm= passwordConfirm;
        this.tipo=tipo;
        this.billetera= billetera;
        String contraseñaEncriptada = BCrypt.hashpw(password, BCrypt.gensalt());
        
        
        System.out.println(contraseñaEncriptada);
        System.out.println(password);
        conexionDB conexion = new conexionDB();
        try {
            
        
        String sql = "INSERT INTO USUARIO (user_name, credito,tipo_usuario,password) VALUES (?, ?,?,?)";
        preparedStatement = conexion.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setBigDecimal(2, BigDecimal.valueOf(billetera)); 
        preparedStatement.setString(3, tipo); 
        preparedStatement.setString(4, contraseñaEncriptada); 
        
        
        int result = preparedStatement.executeUpdate();

            if (result > 0) {
                // Redirigir a una página de éxito o mostrar un mensaje de éxito
                System.out.println("registro_exitoso");
            } else {
                // Manejar el fallo en la inserción
                System.out.println("error");
                
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conexion.cerrarConnection(conexion.getConnection());
        }
        
        
        
        
    }
    
    public boolean existeUsuario(String user){
        String query = "SELECT COUNT(*) FROM USUARIO WHERE user_name = ?";
    conexionDB conexion = new conexionDB();
    ResultSet resultSet = null;
    boolean existe = false;

    try {
 
        
        // Prepara la consulta
        preparedStatement = conexion.getConnection().prepareStatement(query);
        preparedStatement.setString(1, user);

        // Ejecuta la consulta
        resultSet = preparedStatement.executeQuery();

        // Verifica si el usuario existe
        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            existe = (count > 0);  // true si el conteo es mayor que 0
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
             conexion.cerrarConnection(conexion.getConnection());
        }
        return existe;
    }
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getBilletera() {
        return billetera;
    }

    public void setBilletera(double billetera) {
        this.billetera = billetera;
    }
    
}
