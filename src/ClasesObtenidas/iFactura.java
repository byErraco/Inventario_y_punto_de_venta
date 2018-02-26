package ClasesObtenidas;

//import PuntoVenta.BaseDatos.Sql;
import PuntoVenta.deprecated.Configuracion;
import java.security.CodeSource;
import java.sql.Connection;
import java.text.NumberFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 */
public class iFactura {

    //se estable una conexion con la base de datos
    //private conectate con = new conectate("");
    public Connection conn = null;
    private Configuracion conf;
//    Sql PgSql;
    String BARRA = System.getProperty("file.separator");
    String DIR_APP = System.getProperty("user.dir");
    CodeSource codeSource;
    String directorioReporte;
    String Directorio1 = BARRA + "build";
    String Directorio2 = BARRA + "dist";
    //---------------------------------------------------------------------------//
    NumberFormat dispFormat = NumberFormat.getCurrencyInstance();
// Formato de edición: inglés (separador decimal: el punto)
    NumberFormat editFormat = NumberFormat.getNumberInstance();
// Para la edición, no queremos separadores de millares

// Creamos los formateadores de números
    NumberFormatter dnFormat = new NumberFormatter(dispFormat);
    NumberFormatter enFormat = new NumberFormatter(editFormat);
// Creamos la factoría de formateadores especificando los
// formateadores por defecto, de visualización y de edición
    DefaultFormatterFactory currFactory = new DefaultFormatterFactory(dnFormat, dnFormat, enFormat);

    public iFactura(Configuracion conf /*, Sql PgSql*/) {
        this.conf = conf;
        this.conn = null;
        //PgSql.

    }

    public void ver_Factura(String NumFactura, String Total, String exento, String noexento, String iva, String SumaTotal) {

        try {
            String archivo = directorioReportes() + BARRA + "reporte" + BARRA + "FacturaVenta.jasper";
//            System.out.println(archivo);
            HashMap parametros = new HashMap();
            parametros.clear();
            parametros.put("nom_empresa", conf.getEmpresa());
            parametros.put("sucursal", "");
            parametros.put("direccion", conf.getDireccion());
            parametros.put("rif", conf.getRif());
            parametros.put("NumFactura", NumFactura);
            parametros.put("totalexento", exento);
            parametros.put("totalnoexento", noexento);
            parametros.put("iva", iva);
            parametros.put("Total", SumaTotal);
            JasperPrint jasperPrint = JasperFillManager.fillReport(archivo, parametros, conn);
            if (!jasperPrint.getPages().isEmpty()) {
                JasperViewer leap = new JasperViewer(jasperPrint, false);
                leap.setTitle("Lista de Reportes");
                leap.setVisible(true);
                conn = null;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "" + e.getMessage());
        }

    }

    public void ver_FacturaCaja(int id) {
        try {

            String archivo = directorioReportes() + BARRA + "reportecaja" + BARRA + "reportedecaja.jasper";
            HashMap parameters = new HashMap();
            parameters.put("nom_empresa", conf.getEmpresa());
            parameters.put("direccion", conf.getDireccion());
            parameters.put("rif", conf.getRif());
            parameters.put("idsesion", id);
            JasperPrint jasperPrint = JasperFillManager.fillReport(archivo, parameters, conn);

            if (!jasperPrint.getPages().isEmpty()) {
                JasperViewer leap = new JasperViewer(jasperPrint, false);
                leap.setTitle("Lista de Reportes");
                leap.setVisible(true);
                conn = null;
            } else {
                conn = null;
                JOptionPane.showMessageDialog(null, "No hay Registros en ese rango de Fechas",
                        "!!!ATENCION!!!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "" + e.getMessage());

        }

    }

    public void ver_ReporteDevolucion(String codFactura) {
        try {

            String archivo = directorioReportes() + BARRA + "reportedevolucion" + BARRA + "devolucionreporte.jasper";
            HashMap parameters = new HashMap();
            parameters.put("direccion", conf.getDireccion());
            parameters.put("nom_empresa", conf.getEmpresa());

            parameters.put("rif", conf.getRif());
            parameters.put("cod_devolucion", codFactura);
            JasperPrint jasperPrint = JasperFillManager.fillReport(archivo, parameters, conn);

            if (!jasperPrint.getPages().isEmpty()) {
                JasperViewer leap = new JasperViewer(jasperPrint, false);
                leap.setTitle("Lista de Reportes");
                leap.setVisible(true);
                conn = null;
            } else {
                conn = null;
                JOptionPane.showMessageDialog(null, "No hay Registros en ese rango de Fechas",
                        "!!!ATENCION!!!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "" + e.getMessage());
        }
    }

    public String directorioReportes() {
        return System.getProperty("user.dir");
    }

    public boolean verificarPalabras(String cadena, String cadenaBuscar) {
        String[] palabras = cadena.split(BARRA);
        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].toString().equals(cadenaBuscar) == true) {
                return true;
            }
        }
        return false;
    }

}
