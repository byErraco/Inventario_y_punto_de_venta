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
 * @author inverdata03
 */
public class ClienteTableModel extends ArrayListTableModel {

    enum Columnas {

        Tipo("Tipo", "tipo_persona"),
        Identificacion("Identificación", "numero_identificacion_persona"),
        Nombre("Nombre", "nombre_persona");

        String header;
        String columna;

        Columnas(String header, String columna) {
            this.header = header;
            this.columna = columna;
        }

        /**
         *
         */
        private String getHeader() {
            return this.header;
        }

        private String getColumna() {
            return this.columna;
        }
    }

    public ClienteTableModel(ArrayList<HashMap<String, String>> contenido) {
        super(contenido,
                new String[]{Columnas.Tipo.getHeader(), Columnas.Identificacion.getHeader(), Columnas.Nombre.getHeader()},
                new String[]{Columnas.Tipo.getColumna(), Columnas.Identificacion.getColumna(), Columnas.Nombre.getColumna()});
    }

}
