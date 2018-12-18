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
public class Pais {
     String id;
     String nombre;
     String idZonaHoraria;
     String moneda;
     String nacionalidad;
     String gmtZona;
     String simbolo;
     String formatoId;
     String activo;
     String identificacion;
     
    public Pais(String id, String nombre, String idZonaHoraria, String moneda, String nacionalidad, String gmtZona, String simbolo, String formatoId, String activo, String identificacion) {
        this.id = id;
        this.nombre = nombre;
        this.idZonaHoraria = idZonaHoraria;
        this.moneda = moneda;
        this.nacionalidad = nacionalidad;
        this.gmtZona = gmtZona;
        this.simbolo = simbolo;
        this.formatoId = formatoId;
        this.activo = activo;
        this.identificacion = identificacion;
    }

   public  Pais() {
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

    public String getIdZonaHoraria() {
        return idZonaHoraria;
    }

    public void setIdZonaHoraria(String idZonaHoraria) {
        this.idZonaHoraria = idZonaHoraria;
    }
    
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getGmtZona() {
        return gmtZona;
    }

    public void setGmtZona(String gmtZona) {
        this.gmtZona = gmtZona;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    
    public String getFormatoId() {
        return formatoId;
    }

    public void setFormatoId(String formatoId) {
        this.formatoId = formatoId;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}
