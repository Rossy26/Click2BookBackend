/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package datos.controllers;

import datos.models.Administrador;
import datos.models.Estudiante;
import datos.models.Persona;
import datos.models.Propietario;
import datos.repositorios.PersonaRepositorio;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rossimar
 */
@WebServlet(name = "PersonaControl", urlPatterns = {"/PersonaControl"})
public class PersonaServlet extends HttpServlet {
    
    private PersonaRepositorio personaRepositorio;
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession(false); // No crear una sesión nueva si no existe
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        personaRepositorio = new PersonaRepositorio();
        String metodo = request.getParameter("metodo");
        
        if ("registrar".equals(metodo)) {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String tipo = request.getParameter("tipo");
            
            // parsear la fecha
            String fechaStr = request.getParameter("fecha");
            String[] fechaParts = fechaStr.split("-");
            int anio = Integer.parseInt(fechaParts[0]);
            int mes = Integer.parseInt(fechaParts[1]);
            int dia = Integer.parseInt(fechaParts[2]);
            
            // TODO no solo se registran clientes
            
            String password = request.getParameter("password");
            String passwordConfirm = request.getParameter("passwordConfirm");
            if (!password.equals(passwordConfirm)) {
                request.setAttribute("mensaje", "El usuario NO ha sido creado correctamente.");
                request.getRequestDispatcher("/").forward(request, response);
                return;
            }
            Date fecha = new Date(anio, mes, dia);
            Persona persona;
            if ("administrador".equals(tipo))
                    persona = new Administrador(0, nombre, apellido, fecha, email, password, "administrador");
            else if ("estudiante".equals(tipo))
                persona = new Estudiante(0, nombre, apellido, fecha, email, password, "estudiante");
            else
                persona = new Propietario(0, nombre, apellido, fecha, email, password, "propietario");
            
            try {
                personaRepositorio.savePersona(persona);
                request.setAttribute("mensaje", "El usuario con email " + persona.getEmail() + "  ha sido creado correctamente.");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
                request.setAttribute("mensaje", "El usuario con email " + persona.getEmail() + "  ha sido creado correctamente.");
            }
            response.sendRedirect("http://localhost:5500/html/pensiones.html");
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        personaRepositorio = new PersonaRepositorio();
        
        String metodo = request.getParameter("metodo");
        if ("login".equals(metodo)) {
            String correo = request.getParameter("email");
            String password = request.getParameter("password");
            String tipo = request.getParameter("tipo");
            Persona cliente = null;
            try {
                cliente = personaRepositorio.login(correo, password, tipo);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
                Logger.getLogger(PersonaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (cliente != null ) {
                request.setAttribute("mensaje", "Login exitoso");
                HttpSession sesion = request.getSession();
                sesion.setAttribute("cliente", cliente);
                response.sendRedirect("http://localhost:5500/html/pensiones.html");
            } else {
                request.setAttribute("mensaje", "Hubo un error");    
                response.sendRedirect("http://localhost:5500/html/login.html");
            }
        
        }
        
    }
}
