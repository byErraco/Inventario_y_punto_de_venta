/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesExtendidas.Tablas;

import java.util.ArrayList;
import java.util.HashMap;

public class MovimientoTableModel extends ArrayListTableModel {
 
    enum Columnas {

        Producto("Producto", "producto"),                           //nombre del producto
        CantidadRegistrada("Cantidad", "cantidad_registrada"),      // cantidad registrada
        CantidadMovida("Cantidad", "cantidad_movida"),              // catidad movida
        Tipo("Tipo", "tipo"),                                       // tipo deproducto
        FechaMovimiento("Fecha","fecha"),                           // fecha de movimiento del producto
        Usuario("Usuario","usuario");                               //  usuario 

        String header;
        String columna;

        Columnas(String header, String columna) {
            this.header = header;
            this.columna = columna;
        }
        private String getHeader() {
            return this.header;
        }

        private String getColumna() {
            return this.columna;
        }
    }
   
    public MovimientoTableModel(ArrayList<HashMap<String, String>> Movimiento) {
        super(Movimiento,
        new String[]{Columnas.Producto.getHeader(),Columnas.CantidadRegistrada.getHeader(),
        Columnas.CantidadMovida.getHeader(),Columnas.Tipo.getHeader(),Columnas.FechaMovimiento.getHeader(),
        Columnas.Usuario.getHeader()},
        new String[]{Columnas.Producto.getColumna(),Columnas.CantidadRegistrada.getColumna(),Columnas.CantidadMovida.getColumna(),
        Columnas.Tipo.getColumna(),Columnas.FechaMovimiento.getColumna(),Columnas.Usuario.getColumna()});
    }
    
    
}
