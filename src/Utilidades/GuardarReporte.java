/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import PuntoVenta.BaseDatos.PostgreSQL;
import PuntoVenta.Inicio.MenuPrincipal;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author leandro
 */
public class GuardarReporte {
    
    public static MenuPrincipal menuPrincipal;
    
    public static byte[] FileToByteArray(File file) throws IOException{
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }
    
    public static String ByteArrayToFile(byte[] myByteArray) throws IOException{
        String url=System.getProperty("user.dir")+System.getProperty("file.separator");
        FileUtils.writeByteArrayToFile(new File(url), myByteArray);
        return url;
    }
    
    public static void GuardarPDF(JasperPrint print, String name) throws JRException, IOException {
        String realPath = System.getProperty("user.dir") + System.getProperty("file.separator");

        JasperExportManager.exportReportToPdfFile(print, realPath + name);
    }
    
    // Guarda en la base de datos el reporte en forma de bytea
    public static void GuardarBaseDatos(int id, String name) throws IOException {
        String realPath = System.getProperty("user.dir") + System.getProperty("file.separator");
        File reporte = new File(realPath + name);
        byte[] reporteBytea =  FileToByteArray(reporte);
        
        if(name.equals("reporteCaja.PDF")) {
            reporteCorteCaja(reporteBytea, id);
        } else if (name.equals("reporteVenta.PDF")) {
            reporteVenta(reporteBytea, id);
        }
    }
    
    // Guarda el reporte generado del corte de caja en la base de datos
    public static boolean reporteCorteCaja(byte[] reporte, int idCorte) {
        PostgreSQL d = new PostgreSQL();
        return d.ejecutar("UPDATE spve.corte_caja SET reporte_corte = '"+reporte+"' WHERE id_corte_caja = '"+idCorte+"'");
    }
    
    // Guarda el reporte generado del corte de caja en la base de datos
    public static boolean reporteVenta(byte[] reporte, int idVenta) {
        PostgreSQL d = new PostgreSQL();
        return d.ejecutar("UPDATE spve.venta SET reporte_venta = '"+reporte+"' WHERE id_venta = '"+idVenta+"'");
    }
}
