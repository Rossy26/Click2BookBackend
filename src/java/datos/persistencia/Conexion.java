/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.persistencia;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rossimar
 */
public class Conexion {
    
    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Dotenv env = Dotenv.load();
        return DriverManager.getConnection(env.get("URL"), env.get("USER"), env.get("PASSWORD"));
    }
}
