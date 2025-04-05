/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.repositorios;

import datos.models.Administrador;
import datos.models.Estudiante;
import datos.models.Persona;
import datos.models.Propietario;
import datos.persistencia.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rossimar
 */
public class PersonaRepositorio {

    public PersonaRepositorio() {
    }
    public boolean savePersona(Persona persona) throws ClassNotFoundException{
        try {
            String consultarPersonaSQL = "INSERT INTO personas (nombre, apellido, fechanacimiento, email, contrasena, tipo) "
                    + "VALUES (?, ?, ?, ?, ?, ?) RETURNING idpersona";
            try (Connection conexion = Conexion.getConexion()) {
                conexion.setAutoCommit(false);
                PreparedStatement statementPersona = conexion.prepareStatement(consultarPersonaSQL);
                statementPersona.setString(1, persona.getNombre());
                statementPersona.setString(2, persona.getApellido());
                statementPersona.setDate(3, new Date(
                        persona.getFechaNacimiento().getYear(),
                        persona.getFechaNacimiento().getMonth(),
                        persona.getFechaNacimiento().getYear()
                ));
                
                statementPersona.setString(4, persona.getEmail());
                statementPersona.setString(5, persona.getContrasena());
                statementPersona.setString(6, persona.getTipo());
                ResultSet result = statementPersona.executeQuery();
                
                if (result.next()) {
                    int idPersona = result.getInt("idpersona"); // tomar el id de la persona para ponerselo al cliente
                    
                    String tipo;
                    switch (persona.getTipo()) {
                        case "propietario":
                            tipo = "propietarios";
                            break;
                        case "estudiante":
                            tipo = "estudiantes";
                            break;
                        default:
                            tipo = "administradores";
                            break;
                    }
                    
                    String consultaSQL = "INSERT INTO " + tipo + " VALUES (?);";
                    PreparedStatement statementCliente = conexion.prepareStatement(consultaSQL);
                    statementCliente.setInt(1, idPersona);
                    
                    
                    if (statementCliente.executeUpdate() > 0) {
                        conexion.commit();
                        conexion.close();
                        return true;
                    }
                }
                // por si no se guardó bien
                conexion.rollback();
            }
            return false;
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
            return false;
        }
    }
    
    public Persona login(String email, String password, String tipoPersona) throws ClassNotFoundException {
        Persona persona = null;

        if (!validarLogin(email, password, tipoPersona)) {
            return persona;
        }
        String tipo;
        switch (tipoPersona) {
            case "propietario":
                tipo = "propietarios";
                break;
            case "estudiante":
                tipo = "estudiantes";
                break;
            default:
                tipo = "administradores";
                break;
        }
        
        String consultaSQL = "SELECT idpersona, nombre, apellido, email, fechanacimiento "
                + "FROM personas "
                + "WHERE email = ?;";
        try {
            Connection conexion = Conexion.getConexion();
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, email);
            
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int idPersona = result.getInt("idpersona");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                int diaNacimiento = 0;
                int mesNacimiento = 0;
                int anioNacimiento = 0;
                Date fecha = new Date(anioNacimiento, mesNacimiento, diaNacimiento);
                if ("administrador".equals(tipoPersona))
                    persona = new Administrador(idPersona, nombre, apellido, fecha, email, "", "administrador");
                else if ("estudiante".equals(tipoPersona))
                    persona = new Estudiante(idPersona, nombre, apellido, fecha, email, "", "estudiante");
                else
                    persona = new Propietario(idPersona, nombre, apellido, fecha, email, "", "propietario");
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e.toString());
        }
        return persona;
    }
    
    public boolean validarLogin(String email, String password, String tipo) throws ClassNotFoundException {
        String consultaQL = "SELECT email, contrasena, tipo FROM personas WHERE (email = ?) "
                + "AND (tipo = ?) " +
                " AND (contrasena = ?);";
        try {
            Connection conexion = Conexion.getConexion();
            
            PreparedStatement statement = conexion.prepareStatement(consultaQL);
            statement.setString(1, email);
            statement.setString(2, tipo);
            statement.setString(3, password);
            
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return false;
    }
}
