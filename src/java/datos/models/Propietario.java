/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.models;

import java.util.Date;

/**
 *
 * @author xlancet
 */
public class Propietario extends Persona {

    private int idPropietario;

    public Propietario(int idPersona, String nombre, String apellido, Date fechaNacimiento, String email, String contrasena, String tipo) {
        super(idPersona, nombre, apellido, fechaNacimiento, email, contrasena, tipo);
        this.idPropietario = idPersona;
    }

    @Override
    public String toString() {
        return super.toString() + " Propietario{" + "idPropietario=" + idPropietario + '}';
    }

    /**
     * @return the idPropietario
     */
    public int getIdPropietario() {
        return idPropietario;
    }

    /**
     * @param idPropietario the idPropietario to set
     */
    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }
}
