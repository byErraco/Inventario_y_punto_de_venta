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
public class EstadoCajaTableModel extends ArrayListTableModel{
    
    enum Columnas {

        EmpleadoApertura("Empleado", "empleado"),
        FechaApertura("Fecha apertura", "fecha_apertura"),
        CajaAbierta("Estado", "caja_abierta"),
        Codigo("Código", "id_estado_caja");
//        FechaCierre("Fecha corte", "fecha_corte");
        
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

    public EstadoCajaTableModel(ArrayList<HashMap<String, String>> contenido) {
//        super(contenido,
//                new String[]{Columnas.EmpleadoApertura.getHeader(), Columnas.CodCorte.getHeader(), Columnas.FechaApertura.getHeader(), 
//                    Columnas.FechaCierre.getHeader()},
//                new String[]{Columnas.EmpleadoApertura.getColumna(), Columnas.CodCorte.getHeader(), Columnas.FechaApertura.getColumna(), 
//                    Columnas.FechaCierre.getColumna()});
        // para cambiar las columnas
        super(contenido,
                new String[]{Columnas.Codigo.getHeader(), Columnas.EmpleadoApertura.getHeader(), 
                    Columnas.FechaApertura.getHeader()/*, Columnas.FechaCierre.getHeader(),*/, Columnas.CajaAbierta.getHeader()},
                new String[]{Columnas.Codigo.getColumna(), Columnas.EmpleadoApertura.getColumna(), 
                    Columnas.FechaApertura.getColumna()/*, Columnas.FechaCierre.getColumna() */, Columnas.CajaAbierta.getColumna()});
    }

}