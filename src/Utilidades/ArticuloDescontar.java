/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author inverdata
 */
public class ArticuloDescontar {

    String Codigo_barra;
    String Cantidad;

    public ArticuloDescontar() {
    }

    public ArticuloDescontar(String Codigo_barra, String Cantidad) {
        this.Codigo_barra = Codigo_barra;
        this.Cantidad = Cantidad;
    }

    public String getCodigo_barra() {
        return Codigo_barra;
    }

    public void setCodigo_barra(String Codigo_barra) {
        this.Codigo_barra = Codigo_barra;
    }

    public int getCantidad() {
        return Integer.parseInt(Cantidad);
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

}
