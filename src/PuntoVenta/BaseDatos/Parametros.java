/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.BaseDatos;

import Utilidades.ruta;



/**
 *
 * @author inverdata
 */
public class Parametros {

    String pais;
    String nombre;
    String identificacion;
    String direccion;
    String telefono;
    String moneda;
    String logo;

    public Parametros(String pais, String nombre, String identificacion, String direccion, String telefono, String moneda, String logo) {
        this.pais = pais;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.moneda = moneda;
        this.logo = logo;
    }

    public Parametros() {
    }

    public void setParameter(String nombre, String valor) {
        ruta r = new ruta();
        switch(nombre) {
            case "pais":
                this.pais = valor;
            break;
            case "nombre":
                this.nombre = valor;
            break;
            case "identificacion":
                this.identificacion = valor;
            break;
            case "direccion":
                this.direccion = valor;
            break;
            case "telefono":
                this.telefono = valor;
            break;
            case "moneda":
                this.moneda = valor;
            break;
            case "logo":
                this.logo = r.rutaImagen + valor + r.separador;
            break;
        }
    }
    
    public String getPais() {
        return pais;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getLogo() {
        return logo;
    }
    
}
