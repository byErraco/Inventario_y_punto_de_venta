/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesExtendidas.Tablas;

import java.util.ArrayList;
import java.util.HashMap;

        
public class ProductoTableModel extends  ArrayListTableModel {

    enum Columnas{
             
        CodigoVenta("Código","codigo_venta_producto"),
        Descripcion("Descripción","descripcion_producto"),
        Unidad("Unidad","unidad"),
        Balanza("Balanza","balanza"),
        Seguimiento("Seguimiento","seguimiento");
              
        String header;
        String columna;
              
        Columnas(String header, String columna){
            this.header = header;
            this.columna = columna;
        }
        
        private static String[] getHeaders() {
            ArrayList<String> headers = new ArrayList();
            for (Columnas col : Columnas.values()) {
                headers.add(col.header);
            }
            return headers.toArray(new String[headers.size()]);
        }
        
        private static String[] getColumnas() {
            ArrayList<String> columnas = new ArrayList();
            for (Columnas col : Columnas.values()) {
                columnas.add(col.columna);
            }
            return columnas.toArray(new String[columnas.size()]);
        }
    }
          
    public ProductoTableModel(ArrayList<HashMap<String, String>> Producto) {
       super(Producto, Columnas.getHeaders(), Columnas.getColumnas());
    }
}
