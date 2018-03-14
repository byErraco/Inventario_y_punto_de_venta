/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Modelos;

import java.util.HashMap;

public class ModeloCliente {

    private final int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String correo;

    public ModeloCliente(HashMap<String, String> map) {
        if (map == null) {
            try {
                this.finalize();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        this.id = Integer.parseInt(map.get("id_persona"));
        this.nombre = map.get("nombre_persona");
        this.apellido = map.get("apellido_persona");
        this.direccion = map.get("direccion_persona");
        this.telefono = map.get("telefono_persona");
        this.correo = map.get("email_persona");
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * @return 
     */
    public String getTelefono() {
        return telefono;
    }
    
    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
