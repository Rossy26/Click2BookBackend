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
public class Estudiante extends Persona {
    private int idEstudiante;

    /**
     * @return the idEstudiante
     */
    public int getIdEstudiante() {
        return idEstudiante;
    }

    /**
     * @param idEstudiante the idEstudiante to set
     */
    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Estudiante(int idEstudiante, int idPersona, String nombre, String apellido, Date fechaNacimiento, String email, String contrasena, String tipo) {
        super(idPersona, nombre, apellido, fechaNacimiento, email, contrasena, tipo);
        this.idEstudiante = idEstudiante;
    }

    @Override
    public String toString() {
        return super.toString() + "Estudiante{" + "idEstudiante=" + idEstudiante + '}';
    }
    
}
