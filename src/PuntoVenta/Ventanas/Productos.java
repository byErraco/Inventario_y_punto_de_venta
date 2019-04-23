/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;
import ClasesExtendidas.Tablas.ProductoTableModel;
import PuntoVenta.Inicio.MenuPrincipal;
import PuntoVenta.Modelos.ModeloProducto;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author konstanza
 */

public class Productos extends Lista {
     
    public ModeloProducto productos;
 
    /**
     *
     * @param menuPrincipal
     */
    public Productos(MenuPrincipal menuPrincipal) {
        super(menuPrincipal, "Consulta de productos");
        actualizarTabla();
    }
    
    @Override
    protected void actualizarTabla() {
       ArrayList<HashMap<String, String>> Producto = menuPrincipal.getOBD().getArrayListProductos();
       ProductoTableModel model = new ProductoTableModel(Producto);
       jtbTabla.setModel(model); 
    }  
}