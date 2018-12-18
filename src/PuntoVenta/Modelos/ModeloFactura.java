/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Modelos;

/**
 *
 * @author Programador01
 */
public class ModeloFactura {

    private final String codigoFactura;

    public ModeloFactura() {
        this.codigoFactura = this.gerarCodigoFactura();
    }
    public ModeloFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    private String gerarCodigoFactura() {
        String cod = "FACT-";
        for (int i = 1; i <= 5; i++) {
            int num = (int) ((Math.random() * (90 - 65)) + 65);
            cod = cod + (char) num;
        }
        return cod;
    }
}
