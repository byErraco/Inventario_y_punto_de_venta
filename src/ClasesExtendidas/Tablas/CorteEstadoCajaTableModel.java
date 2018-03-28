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
public class CorteEstadoCajaTableModel extends ArrayListTableModel {
    enum Columnas {

        Monto("Monto", "monto_corte"),
        Fecha("Fecha", "fecha_corte");
        
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

    public CorteEstadoCajaTableModel(ArrayList<HashMap<String, String>> contenido) {
        super(contenido,
                new String[]{Columnas.Monto.getHeader(), Columnas.Fecha.getHeader()},
                new String[]{Columnas.Monto.getColumna(), Columnas.Fecha.getColumna()});
    }

}
    

