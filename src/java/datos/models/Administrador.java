/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.models;

/**
 *
 * @author Rossimar
 */
public class Administrador {
    private int idAdministrador;

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

    public Administrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    @Override
    public String toString() {
        return super.toString() + "Administrador{" + "idAdministrador=" + idAdministrador + '}';
    }
    
}
