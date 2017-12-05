/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Beans;

import java.io.Serializable;

/**
 *
 * @author inverdata
 */
public class FacturaBean implements Serializable {

    public String nombreEmpresa;
    public String direccionEmpresa;
    public String rifEmpresa;

    public String nombreCliente;
    public String direccionCliente;
    public String rifCliente;

    public String fechaFactura;
    public String codigoFactura;

    public String numeroCaja;
    public String cajeroCaja;

    public String codigoProducto;
    public String descripcionProducto;
    public String precioProducto;
    public String cantidadProducto;
    public String ivaProducto;
    public String totalProducto;

    public String subtotalFactura;
    public String ivaFactura;
    public String totalFactura;

    public String formaPago;
    public String montoPago;

    public FacturaBean() {

    }

    public FacturaBean(String nombre, String direccion, String rif) {
        this.nombreEmpresa = nombre;
        this.direccionEmpresa = direccion;
        this.rifEmpresa = rif;
    }

    public void setNombreEmpresa(String nombre) {
        this.nombreEmpresa = nombre;
    }

    public void setDireccionEmpresa(String direccion) {
        this.direccionEmpresa = direccion;
    }

    public void setRifEmpresa(String rif) {
        this.rifEmpresa = rif;
    }

    public String getNombreEmpresa() {
        return this.nombreEmpresa;
    }

    public String getDireccionEmpresa() {
        return this.direccionEmpresa;
    }

    public String getRifEmpresa() {
        return this.rifEmpresa;
    }

}
