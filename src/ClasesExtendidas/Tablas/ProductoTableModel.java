/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesExtendidas.Tablas;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductoTableModel extends ArrayListTableModel {

    public ProductoTableModel(ArrayList<HashMap<String, String>> contenido, String[] headers, String[] columnas) {
        super(contenido,
        new String[]{Columnas.Producto.getHeader(), Columnas.Producto2.getHeader(),Columnas.Cantidad.getHeader(),
        Columnas.Cantidad2.getHeader(),Columnas.Tipo.getHeader(),Columnas.FechaReg.getHeader(),Columnas.FechaMod.getHeader(),
        Columnas.Usuario.getHeader()},
        new String[]{Columnas.Producto.getColumna(), Columnas.Producto2.getColumna(),Columnas.Cantidad.getColumna(),Columnas.Cantidad2.getColumna(),
        Columnas.Tipo.getColumna(),Columnas.FechaReg.getColumna(),Columnas.FechaMod.getColumna(),Columnas.Usuario.getColumna()});
    }
    
    enum Columnas {

        Producto("Producto", "producto"),
        Producto2("Producto", "producto2"),
        Cantidad("Cantidad", "cant"),
        Cantidad2("Cantidad", "cant2"),
        Tipo("Tipo", "tipo"),
        FechaReg("Fecha","fecha"),
        FechaMod("Fecha","fecha1"),
        Usuario("Usuario","usuario");

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
    
    
    
}
