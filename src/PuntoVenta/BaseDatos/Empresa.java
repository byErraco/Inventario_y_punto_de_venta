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
public class Empresa {

    String nombre;
    String direccion;
    String tipoEmpresa;
    String rif;
    String telefono;
    String moneda;

    public Empresa(String nombre, String direccion, String tipoEmpresa, String rif, String telefono, String moneda) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.tipoEmpresa = tipoEmpresa;
        this.rif = rif;
        this.telefono = telefono;
        this.moneda = moneda;
    }

    public Empresa() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoEmpresa(){
        return tipoEmpresa;
    }
    
    public void setTipoEmpresa(String tipoEmpresa){
        this.tipoEmpresa = tipoEmpresa;
    }
    
    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

}
