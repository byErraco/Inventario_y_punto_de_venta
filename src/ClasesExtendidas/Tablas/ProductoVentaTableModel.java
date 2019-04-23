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
 * @author Programador01
 */
public class ProductoVentaTableModel extends ArrayListTableModel {

    enum Columnas {

        Codigo("Código", "codigo_venta_producto"),
        Descripcion("Descripción", "descripcion_producto"),
        IdUnidadInventario("Id de unidad de inventario", "id_unidad_inventario"),
        Almacen("Almacén","almacen"),
        Lote("Lote/Serie","codigo_lote"),
        PrecioBase("Precio base unitario", "precio_base_unitario"),
        Impuesto("Impuesto unitario", "impuesto"),
        Precio("Total unitario", "precio_venta_publico"),
        Cantidad("Cantidad", "cantidad_producto"),
        Total("Total", "total");

        String header;
        String columna;

        Columnas(String header, String columna) {
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

    public ProductoVentaTableModel(ArrayList<HashMap<String, String>> contenido) {
        super(contenido, Columnas.getHeaders(), Columnas.getColumnas());
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
}
