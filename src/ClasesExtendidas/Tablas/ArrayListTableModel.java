/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesExtendidas.Tablas;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gustavo
 */
public class ArrayListTableModel extends DefaultTableModel {

//    private DecimalFormat redondeo = new DecimalFormat("0.00");
    private int cantidadColumnas = 0;
    private final boolean isEditable = false;

    /**
     *
     * @param contenido Contenido de la tabla, se recorre con un for.
     * @param headers Nombres para las columnnas de la tabla
     * @param columnas Nombres para sacar los datos del arraylist
     */
    public ArrayListTableModel(ArrayList<HashMap<String, String>> contenido, String[] headers, String[] columnas) {
        this.cantidadColumnas = headers.length;
        this.setColumnIdentifiers(headers);
        if (contenido != null) {
            for (HashMap<String, String> row : contenido) {
                String[] newRow = new String[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    newRow[i] = row.get(columnas[i].toLowerCase());
                }
                this.addRow(newRow);
            }
        }
    }

    public ArrayListTableModel(String[] headers) {
        this.cantidadColumnas = headers.length;
        this.setColumnIdentifiers(headers);
    }

    //FALTA MEJORAR isCellEditable 
    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 3;
    }

}
