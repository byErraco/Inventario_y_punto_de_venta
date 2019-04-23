/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;
import ClasesExtendidas.Tablas.UnidadInventarioTableModel;
import PuntoVenta.Inicio.MenuPrincipal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author konstanza
 */
public class Inventario extends Lista {
 
    /**
     *
     * @param menuPrincipal
     */
    public Inventario(MenuPrincipal menuPrincipal) {
        super(menuPrincipal, "Inventario");
        actualizarTabla();
    }                      

    @Override
    protected void actualizarTabla() {
       ArrayList<HashMap<String, String>> unidadesInventario = menuPrincipal.getOBD().getArrayListUnidadesInventario();
       UnidadInventarioTableModel model = new UnidadInventarioTableModel(unidadesInventario);
       jtbTabla.setModel(model); 
    }         
}