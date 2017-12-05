/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta;

import PuntoVenta.Beans.FacturaBean;
import java.util.Collection;
import java.util.Vector;


/**
 *
 * @author inverdata
 */
public class Reporte {

    public static Collection generarFactura() {
        Vector collection = new Vector();

        collection.add(new FacturaBean("Inverdata", "Valle Frio", "J-000-0"));

        return collection;
    }
    

}
