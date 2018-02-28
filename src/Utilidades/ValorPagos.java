/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author inverdata
 */
public class ValorPagos {

    String tipo;
    String monto;
    String fecha;

    public ValorPagos(String tipo, Double monto) {
        this.tipo = tipo;
        this.monto = "" + monto;
        SimpleDateFormat sdf = new SimpleDateFormat("Y-MM-dd hh:mm a");
        fecha = sdf.format(new Date());
    }

    public ValorPagos() {
        SimpleDateFormat sdf = new SimpleDateFormat("Y-MM-dd hh:mm a");
        fecha = sdf.format(new Date());
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMonto() {
        return monto;
    }

    public Double getMontoD() {
        return Double.parseDouble(monto);
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
