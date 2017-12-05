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
public class Reporte3 {

    private String rifE;
    private String nombreE;
    private String direccionE;
    private String telefonoE;
    private String nombreEmp;
    private String cedulaEmp;
    private String numCaja;
    private String total;
    private String gtotal;
    private String moneda;
    private String sefe;
    private String stdd;
    private String stdc;
    private String sctk;
    private String cefe;
    private String ctdd;
    private String ctdc;
    private String cctk;
    private String dif;
    private String saldo;

    public Reporte3(Empresa e, String nombreEmp, String cedulaEmp, String numCaja, String total, String gtotal, String sefe, String stdd, String stdc, String sctk, String cefe, String ctdd, String ctdc, String cctk, String saldo) {
        this.rifE = e.getRif();
        this.nombreE = e.getNombre();
        this.direccionE = e.getDireccion();
        this.telefonoE = e.getTelefono();
        this.nombreEmp = nombreEmp;
        this.cedulaEmp = cedulaEmp;
        this.numCaja = numCaja;
        this.total = total;
        this.gtotal = gtotal;
        this.moneda = e.getMoneda();
        this.sefe = decimales(sefe);
        this.stdd = decimales(stdd);
        this.stdc = decimales(stdc);
        this.sctk = decimales(sctk);
        this.cefe = decimales(cefe);
        this.ctdd = decimales(ctdd);
        this.ctdc = decimales(ctdc);
        this.cctk = decimales(cctk);
        this.dif = suma(total, gtotal);
        this.saldo = saldo;
    }

    private String suma(String stotal, String ctotal) {
        double dife = Double.parseDouble(ctotal) - Double.parseDouble(stotal);
        String valor = "" + dife + "0";
        return valor;
    }

    private String decimales(String valor) {
        if (valor.contains(".")) {
            return valor;
        } else {
            valor = valor.concat(".00");
            return valor;
        }
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

    public String getSefe() {
        return sefe;
    }

    public void setSefe(String sefe) {
        this.sefe = sefe;
    }

    public String getStdd() {
        return stdd;
    }

    public void setStdd(String stdd) {
        this.stdd = stdd;
    }

    public String getStdc() {
        return stdc;
    }

    public void setStdc(String stdc) {
        this.stdc = stdc;
    }

    public String getSctk() {
        return sctk;
    }

    public void setSctk(String sctk) {
        this.sctk = sctk;
    }

    public String getCefe() {
        return cefe;
    }

    public void setCefe(String cefe) {
        this.cefe = cefe;
    }

    public String getCtdd() {
        return ctdd;
    }

    public void setCtdd(String ctdd) {
        this.ctdd = ctdd;
    }

    public String getCtdc() {
        return ctdc;
    }

    public void setCtdc(String ctdc) {
        this.ctdc = ctdc;
    }

    public String getCctk() {
        return cctk;
    }

    public void setCctk(String cctk) {
        this.cctk = cctk;
    }

    public String getDif() {
        return dif;
    }

    public void setDif(String dif) {
        this.dif = dif;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

}
