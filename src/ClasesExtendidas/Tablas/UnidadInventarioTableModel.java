/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesExtendidas.Tablas;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author konstanza
 */
public class UnidadInventarioTableModel extends ArrayListTableModel{
    
    enum Columnas{
        
        AlmacenId("Id almacén", "almacen_id"),
        Almacen("Almacén","almacen"),
        Producto("Producto","codigo_venta_producto"),
        Lote("Lote/Serie","codigo_lote"),
        Cantidad("Cantidad","cantidad"),
        Precio("Precio","precio_venta_publico"),
        BaseImponible("B.Imp","base_imponible"),
        Ganancia("Ganancia %","margen_ganancia"),
        Impuesto("Impuesto %","porcentaje_impuesto"),
        Exento("Exento","exento");
        
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
          
    public UnidadInventarioTableModel(ArrayList<HashMap<String, String>> UnidadInventario) {
        super(UnidadInventario, Columnas.getHeaders(), Columnas.getColumnas());
    }
}
