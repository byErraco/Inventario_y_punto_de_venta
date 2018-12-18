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
public class Producto {
     String id;
     String codigo;
     String descripcionProducto;
     int limiteVenta;
     String descripcionEmpaque;
     

    public Producto(String id ,String codigo, String descripcionProducto, int limiteVenta, String descripcionEmpaque) {
        this.id = id;
        this.codigo = codigo;
        this.descripcionProducto = descripcionProducto;
        this.limiteVenta = limiteVenta;
        this.descripcionEmpaque = descripcionEmpaque;

    }

   public  Producto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getLimiteVenta() {
        return limiteVenta;
    }

    public void setLimiteVenta(int limiteVenta) {
        this.limiteVenta = limiteVenta;
    }

   public String getDescripcionEmpaque() {
        return descripcionEmpaque;
    }

    public void setDescripcionEmpaque(String descripcionEmpaque) {
        this.descripcionEmpaque = descripcionEmpaque;
    }
    
    
}
