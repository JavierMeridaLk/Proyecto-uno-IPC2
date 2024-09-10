/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backen.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author xavi
 */
public class conexionDB {


    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/revistas_actuales";
    private static final String USER = "root";
    private static final String PASSWORD = "1012";

    private Connection connection;

    public conexionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            System.out.println("Esquema: " + connection.getSchema());
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al connectar a la DB");
            e.printStackTrace();
        }
    }
    
    public static void cerrarConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
}
