/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PuntoVenta.Modelos;

import java.util.HashMap;

/**
 *
 * @author gustavo
 */
public class ModeloEmpleado {
    private final int id;
    private String nombre;
    private String apellido;
    private char nacionalidad;
    private String cedula;
    private String correo;
    private String cargo;
    private int id_Cargo;
    private String usuario;
    private String password;
    private String telefono;
    
    public ModeloEmpleado(HashMap<String, String> map){
        this.id = Integer.parseInt(map.get("id_empleado"));
        this.nombre = map.get("nombre_persona");
        this.apellido = map.get("apellido_persona");
        this.nacionalidad =  map.get("tipo_persona").charAt(0);
        this.cedula = map.get("numero_identificacion_persona");
        this.correo = map.get("email_persona");
        this.cargo = map.get("nombre_cargo");
        this.id_Cargo = Integer.parseInt(map.get("id_cargo"));
        this.telefono = map.get("telefono_persona");
        this.password = map.get("clave");
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    
        public String getTelefono() {
        return telefono;
    }
     
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return the nacionalidad
     */
    public char getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @return the idCargo
     */
    public int getIdCargo() {
        return id_Cargo;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

        /**
     * @return the password
     */
    public String getpassword() {
        return password;
    }
    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(char nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

        public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    /**
     * @param idCargo the idCargo to set
     */
    public void setIdCargo(int idCargo) {
        this.id_Cargo = idCargo;
    }
    public void setIdpassword(String password) {
        this.password = password;
    }
    
}
