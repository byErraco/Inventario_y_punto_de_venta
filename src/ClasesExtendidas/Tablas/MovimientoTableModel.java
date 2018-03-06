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

        Producto("Producto", "producto"),  //nombre del producto
        Cantidad("Cantidad", "cant"),      // cantidad registrada
        Cantidad2("Cantidad", "cant2"),    // catidad movida
        Tipo("Tipo", "tipo"),              // tipo deproducto
        FechaReg("Fecha","fecha"),         // fecha registrado en el inventario
        FechaMov("Fecha","fecha1"),        // fecha de movimiento del producto
        Usuario("Usuario","usuario");      //  usuario 

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
   
    public MovimientoTableModel(ArrayList<HashMap<String, String>> contenido) {
        super(contenido,
        new String[]{Columnas.Producto.getHeader(),Columnas.Cantidad.getHeader(),
        Columnas.Cantidad2.getHeader(),Columnas.Tipo.getHeader(),Columnas.FechaReg.getHeader(),Columnas.FechaMov.getHeader(),
        Columnas.Usuario.getHeader()},
        new String[]{Columnas.Producto.getColumna(),Columnas.Cantidad.getColumna(),Columnas.Cantidad2.getColumna(),
        Columnas.Tipo.getColumna(),Columnas.FechaReg.getColumna(),Columnas.FechaMov.getColumna(),Columnas.Usuario.getColumna()});
    }
    
    
}
