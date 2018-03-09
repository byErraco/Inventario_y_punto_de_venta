/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesExtendidas.Tablas;

/**
 * on
 *
 * @author inverdata
 */
public class TipoPagoTableModel extends ArrayListTableModel {

    enum Colunmas {

        TipoPago("Tipo pago"),
        Monto("Monto");

        String header;

        Colunmas(String header) {
            this.header = header;
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

    public TipoPagoTableModel() {
        super(new String[]{Colunmas.TipoPago.getHeader(), Colunmas.Monto.getHeader()});
    }

}
