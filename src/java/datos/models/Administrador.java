/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.models;

import java.util.Date;

/**
 *
 * @author Rossimar
 */
public class Administrador extends Persona {
    private int idAdministrador;

    public Administrador(int idPersona, String nombre, String apellido, Date fechaNacimiento, String email, String contrasena, String tipo) {
        super(idPersona, nombre, apellido, fechaNacimiento, email, contrasena, tipo);
        idAdministrador = idPersona;
    }
    
    

    /**
     * @return the idAdministrador
     */
    public int getIdAdministrador() {
        return idAdministrador;
    }

    /**
     * @param idAdministrador the idAdministrador to set
     */
    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    @Override
    public String toString() {
        return super.toString() + "Administrador{" + "idAdministrador=" + idAdministrador + '}';
    }
    
}
