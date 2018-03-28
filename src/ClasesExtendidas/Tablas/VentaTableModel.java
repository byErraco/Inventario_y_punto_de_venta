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
 * @author inverdata
 */
public class VentaTableModel extends ArrayListTableModel {

    enum Columnas {

        Codigo("Código", "codigo_factura"),
        Cliente("Cliente", "identificacion_persona"),
        Fecha("Fecha", "fecha_venta"),
        Estado("Estado", "estado_venta"),
        Total("Total", "total");

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
                new String[]{Columnas.Codigo.getHeader(), Columnas.Cliente.getHeader(), Columnas.Fecha.getHeader(),
                    Columnas.Estado.getHeader(), Columnas.Total.getHeader()},
                new String[]{Columnas.Codigo.getColumna(), Columnas.Cliente.getColumna(), Columnas.Fecha.getColumna(),
                    Columnas.Estado.getColumna(), Columnas.Total.getColumna()});
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
