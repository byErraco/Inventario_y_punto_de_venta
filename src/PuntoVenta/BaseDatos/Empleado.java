/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.BaseDatos;

/**
 *
 * @author inverdata
 */
public class Empleado {
     String id;
     String nombre;
     String apellido;
     String nacionalidad;
     String cedula;
     String correo;
     String direccion;
     String cargo_id;
     String telefono;
     String password;

    public Empleado(String id, String nombre, String apellido, String nacionalidad, String cedula, String correo, String direccion, String cargo_id, String telefono, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.cedula = cedula;
        this.correo = correo;
        this.direccion = direccion;
        this.cargo_id = cargo_id;
        this.telefono = telefono;
        this.password = password;
    }

   public  Empleado() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String correo) {
        this.direccion = direccion;
    }

    public String getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(String cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
