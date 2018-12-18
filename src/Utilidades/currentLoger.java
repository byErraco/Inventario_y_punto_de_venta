package Utilidades;


import PuntoVenta.BaseDatos.Empleado;
import PuntoVenta.BaseDatos.PostgreSQL;
import PuntoVenta.Inicio.MenuPrincipal;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import PuntoVenta.BaseDatos.ObjetoBaseDatos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leandro
 */
public class currentLoger {
    public MenuPrincipal menuPrincipal;
    public int id = -1;

    public currentLoger() {

    }   
         
         
    public Empleado getDatosEmpleadoLogueado() {
        Empleado emple;
        
        if(PuntoVenta.Ventanas.LogIn.contador > 0) {
            id = PuntoVenta.Ventanas.LogIn.idEmpleado;
            emple = getDatosEmpleadoId(id);
        } else {
            id = PuntoVenta.Inicio.RegistroAdministrador.idEmpleado;
            emple = getDatosEmpleadoId(id);
        }

        return emple;
    }
    
    public Empleado getDatosEmpleadoId(int idEmpleado) {
        
        PostgreSQL d = new PostgreSQL();
        Empleado emple = new Empleado();
        
        String query = "SELECT persona.nombre_persona, persona.apellido_persona, persona.tipo_persona, persona.numero_identificacion_persona, " +
                        "persona.telefono_persona, persona.email_persona, " +
                        "persona.direccion_persona, empleado.id_empleado, cargo.nombre_cargo, cargo.id_cargo " +
                        "FROM spve.persona INNER JOIN spve.empleado ON persona.id_persona = empleado.id_persona " +
                        "INNER JOIN spve.cargo ON empleado.id_cargo = cargo.id_cargo WHERE id_empleado = '" + idEmpleado + "'";
        try{
            d.buscar(query);
            while(d.rs.next()){
                emple.setId(d.rs.getString("id_empleado"));
                emple.setNombre(d.rs.getString("nombre_persona"));
                emple.setApellido(d.rs.getString("apellido_persona"));
                emple.setNacionalidad(d.rs.getString("tipo_persona"));
                emple.setCedula(d.rs.getString("numero_identificacion_persona"));
                emple.setCorreo(d.rs.getString("email_persona"));
                emple.setTelefono(d.rs.getString("telefono_persona"));
                emple.setDireccion(d.rs.getString("direccion_persona"));
                emple.setCargo_id(d.rs.getString("id_cargo"));
            }
        }catch(Exception e) {    
        }
    
        return emple;
    }
}
