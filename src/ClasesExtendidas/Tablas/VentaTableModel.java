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
public class VentaTableModel extends ArrayListTableModel {

    enum Columnas {

        Codigo("Código", "codigo_barra"),
        Descripcion("Descripción", "descripcion"),
        Cantidad("Cantidad", "cantidad_producto"),
        PrecioBase("Precio base", "pvp"),
        Impuesto("Impuesto", "impuesto"),
        Precio("Precio final", "total");

        String header;
        String columna;

        Columnas(String header, String columna) {
            this.header = header;
            this.columna = columna;

        }

        /**
         * Obtiene el nombre de la columna dentro del resultset para obtener su
         * valor.
         *
         * @return
         */
        private String getColumna() {
            return this.columna;
        }

        /**
         * Obtiene el nombre de la columna para asignar en el header de la
         * tabla.
         *
         * @return
         */
        private String getHeader() {
            return this.header;
        }
    }

    public VentaTableModel(ArrayList<HashMap<String, String>> contenido) {
        super(contenido,
                new String[]{Columnas.Codigo.getHeader(), Columnas.Descripcion.getHeader(), Columnas.PrecioBase.getHeader(),
                    Columnas.Cantidad.getHeader(), Columnas.Impuesto.getHeader(), Columnas.Precio.getHeader()},
                new String[]{Columnas.Codigo.getColumna(), Columnas.Descripcion.getColumna(), Columnas.PrecioBase.getColumna(),
                    Columnas.Cantidad.getColumna(), Columnas.Impuesto.getColumna(), Columnas.Precio.getColumna()});
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
