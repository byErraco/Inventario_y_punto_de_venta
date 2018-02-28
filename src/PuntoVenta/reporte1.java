/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta;

/**
 *
 * @author david
 *
 */
public class reporte1 {

    private String canti;
    private String descri;
    private String puni;
    private String total;
    private String gtotal;
    private String nombre;
    private String cedula;
    private String direccion;
    private String factura;
    private String pagado;
    private String tipo;
    private String rifE;
    private String nombreE;
    private String direccionE;
    private String telefonoE;
    private String moneda;
    private String totalexento;
    private String totalnoexento;
    private String impuesto;
    private String vuelto;
    private String nombreemp;

    public reporte1(String canti, String descri, String puni, String total, String gtotal, String nombre, String cedula, String direccion, String factura, String pagado, String tipo, String rifE, String nombreE, String direccionE, String telefonoE, String moneda, String totalexento, String totalnoexento, String impuesto, String vuelto, String nombreemp) {
        this.canti = canti;
        this.descri = descri;
        this.puni = puni;
        this.total = total;
        this.gtotal = gtotal;
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
        this.factura = factura;
        this.pagado = pagado;
        this.tipo = tipo;
        this.rifE = rifE;
        this.nombreE = nombreE;
        this.direccionE = direccionE;
        this.telefonoE = telefonoE;
        this.moneda = moneda;
        this.totalexento = totalexento + "0";
        this.totalnoexento = totalnoexento + "0";
        this.impuesto = impuesto;
        this.vuelto = vuelto;
        this.nombreemp = nombreemp;
    }

    public String getCanti() {
        return canti;
    }

    public void setCanti(String canti) {
        this.canti = canti;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getPuni() {
        return puni;
    }

    public void setPuni(String puni) {
        this.puni = puni;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getgtotal() {
        return gtotal;
    }

    public void setGtotal(String gtotal) {
        this.gtotal = gtotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRifE() {
        return rifE;
    }

    public void setRifE(String rifE) {
        this.rifE = rifE;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getDireccionE() {
        return direccionE;
    }

    public void setDireccionE(String direccionE) {
        this.direccionE = direccionE;
    }

    public String getTelefonoE() {
        return telefonoE;
    }

    public void setTelefonoE(String telefonoE) {
        this.telefonoE = telefonoE;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTotalexento() {
        return totalexento;
    }

    public void setTotalexento(String totalexento) {
        this.totalexento = totalexento;
    }

    public String getTotalnoexento() {
        return totalnoexento;
    }

    public void setTotalnoexento(String totalnoexento) {
        this.totalnoexento = totalnoexento;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getVuelto() {
        return vuelto;
    }

    public void setVuelto(String vuelto) {
        this.vuelto = vuelto;
    }

    public String getNombreemp() {
        return nombreemp;
    }

    public void setNombreemp(String nombreemp) {
        this.nombreemp = nombreemp;
    }

}
