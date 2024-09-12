/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backen.usuarios;

import backen.DataBase.conexionDB;
import enums.tipoUser;
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
    private tipoUser tipo;
    private String tipoSt;
    private double billetera;
    PreparedStatement preparedStatement;
    
    public Usuario(){
        
    }
    
    public void crearUsuario(String user, String password, String passwordConfirm,String tipo, double billetera) throws SQLException{
        this.userName=user;
        this.password= password;
        this.passwordConfirm= passwordConfirm;
        this.tipoSt=tipo;
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
        preparedStatement.setString(3, tipoSt); 
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
        }
        try {
                String querryPerfil = "INSERT INTO PERFIL (user) VALUES (?)";
                preparedStatement = conexion.getConnection().prepareStatement(querryPerfil);
                preparedStatement.setString(1, userName);
                int result1 = preparedStatement.executeUpdate();
                if (result1 > 0) {
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
    
    public boolean iniciarSesion(Usuario user) {
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    conexionDB conexion = new conexionDB();
    String query = "SELECT password, tipo_usuario FROM USUARIO WHERE BINARY user_name = ?";

    try {
        // Obtén la conexión
        

        // Prepara la consulta
        preparedStatement = conexion.getConnection().prepareStatement(query);
        preparedStatement.setString(1, user.getUserName()); // Asegúrate de obtener el nombre de usuario de `user`

        // Ejecuta la consulta
        resultSet = preparedStatement.executeQuery();

        // Verifica las credenciales
        if (resultSet.next()) {
            String storedPassword = resultSet.getString("password");
            String tipoUsuarioStr = resultSet.getString("tipo_usuario");

            // Compara la contraseña proporcionada con la almacenada (usando BCrypt)
            if (BCrypt.checkpw(user.getPassword(), storedPassword)) {
                // Convierte el tipo de usuario (String) al valor correspondiente del enum
                tipoUser tipo1 = tipoUser.valueOf(tipoUsuarioStr.toUpperCase());

                // Asigna el tipo al usuario
                user.setTipo(tipo1);

                return true; // Login exitoso
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Cerrar recursos
       conexion.cerrarConnection(conexion.getConnection());
    }

    return false;
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

    public tipoUser getTipo() {
        return tipo;
    }

    public void setTipo(tipoUser tipo) {
        this.tipo = tipo;
    }

    public double getBilletera() {
        return billetera;
    }

    public void setBilletera(double billetera) {
        this.billetera = billetera;
    }
    
}
