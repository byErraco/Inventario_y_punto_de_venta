/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta;

import PuntoVenta.BaseDatos.Empresa;

/**
 *
 * @author inverdata
 */
public class Reporte2 {

    private String rifE;
    private String nombreE;
    private String direccionE;
    private String telefonoE;
    private String nombreEmp;
    private String cedulaEmp;
    private String numCaja;
    private String tipoP;
    private String total;
    private String gtotal;
    private String moneda;
    private String ef;
    private String tdd;
    private String tdc;
    private String ctk;

    public Reporte2(Empresa e, String nombreEmp, String cedulaEmp, String numCaja, String tipoP, String total, String gtotal, String ef, String tdd, String tdc, String ctk) {
        this.rifE = e.getRif();
        this.nombreE = e.getNombre();
        this.direccionE = e.getDireccion();
        this.telefonoE = e.getTelefono();
        this.nombreEmp = nombreEmp;
        this.cedulaEmp = cedulaEmp;
        this.numCaja = numCaja;
        this.tipoP = tipoP;
        this.total = total;
        this.gtotal = gtotal;
        this.ef = ef;
        this.tdd = tdd;
        this.tdc = tdc;
        this.ctk = ctk;
        this.moneda = e.getMoneda();
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

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getCedulaEmp() {
        return cedulaEmp;
    }

    public void setCedulaEmp(String cedulaEmp) {
        this.cedulaEmp = cedulaEmp;
    }

    public String getNumCaja() {
        return numCaja;
    }

    public void setNumCaja(String numCaja) {
        this.numCaja = numCaja;
    }

    public String getTipoP() {
        return tipoP;
    }

    public void setTipoP(String tipoP) {
        this.tipoP = tipoP;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getGtotal() {
        return gtotal;
    }

    public void setGtotal(String gtotal) {
        this.gtotal = gtotal;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getEf() {
        return ef;
    }

    public void setEf(String ef) {
        this.ef = ef;
    }

    public String getTdd() {
        return tdd;
    }

    public void setTdd(String tdd) {
        this.tdd = tdd;
    }

    public String getTdc() {
        return tdc;
    }

    public void setTdc(String tdc) {
        this.tdc = tdc;
    }

    public String getCtk() {
        return ctk;
    }

    public void setCtk(String ctk) {
        this.ctk = ctk;
    }

}
