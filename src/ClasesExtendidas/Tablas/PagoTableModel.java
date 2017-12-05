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
 * @author david
 */
public class PagoTableModel extends ArrayListTableModel {

    enum Columnas {

        Tipo("Tipo", "tipo"),
        Monto("Monto", "monto");

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

    public PagoTableModel(ArrayList<HashMap<String, String>> contenido) {
        super(contenido,
                new String[]{Columnas.Tipo.getHeader(), Columnas.Monto.getHeader()},
                new String[]{Columnas.Tipo.getColumna(), Columnas.Monto.getColumna()});
    }

//    @Override
//    public boolean isCellEditable(int row, int column) {
//        return column == Columnas.Cantidad.ordinal();
//    }

}
