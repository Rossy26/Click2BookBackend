/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.repositorios;

import datos.models.Persona;
import datos.persistencia.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rossimar
 */
public class PersonaRepositorio {

    public PersonaRepositorio() {
    }
    public boolean savePersona (Persona persona){
        try {
            String consultarPersonaSQL = "INSERT INTO personas (nombre, apellido, fechanacimiento, email, contrasena)" + "values (?, ?, ?, ?, ?) RETURNING idpersona";
            Connection conexion = Conexion.getConexion();
            conexion.setAutoCommit(false);
            PreparedStatement statementPersona = conexion.prepareStatement(consultarPersonaSQL);
            statementPersona.setString(1, persona.getNombre());
            statementPersona.setString(2, persona.getApellido());
            statementPersona.setDate(3, new Date(
                    persona.getFechaNacimiento().getYear(),
                    persona.getFechaNacimiento().getMonth(),
                    persona.getFechaNacimiento().getYear()
            ));
            statementPersona.setString(1, persona.getNombre());
            statementPersona.setString(1, persona.getNombre());
        } catch (SQLException ex) {
            Logger.getLogger(PersonaRepositorio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
