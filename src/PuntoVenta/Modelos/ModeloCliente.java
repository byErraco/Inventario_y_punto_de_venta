/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Modelos;

import java.util.HashMap;
import java.util.List;

public class ModeloCliente {

    private final int id;
    private String nombre;
    private String direccion;
    private String apellido;
    private String fechaNacimiento;
    private String genero;
    private String correo;
    private String twitter;
    private String facebook;
    private List<String> listaTelefonos;
    private String telefono;

    public ModeloCliente(HashMap<String, String> map) {
        if (map == null) {
            try {
                this.finalize();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        this.id = Integer.parseInt(map.get("id"));
        this.nombre = map.get("nombre");
        this.apellido = map.get("apellido");
        this.genero = map.get("sexo");
        this.fechaNacimiento = map.get("fechaNacimiento");
        this.direccion = map.get("direccion");
        this.correo = map.get("correo");
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
     * @return the sexo
     */
    public String getSexo() {
        return getGenero();
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
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
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * @param telefono the telefono to get
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
    
    /**
     * @return the listaTelefonos
     */
    public List<String> getListaTelefonos() {
        return listaTelefonos;
    }

    /**
     * @param listaTelefonos the listaTelefonos to set
     */
    public void setListaTelefonos(List<String> listaTelefonos) {
        this.listaTelefonos = listaTelefonos;
    }

    /**
     * @return the twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * @param twitter the twitter to set
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebok the facebook to set
     */
    public void setFacebook(String facebok) {
        this.facebook = facebok;
    }

}
