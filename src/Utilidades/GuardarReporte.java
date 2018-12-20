/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import PuntoVenta.BaseDatos.PostgreSQL;
import PuntoVenta.Inicio.MenuPrincipal;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author leandro
 */
public class GuardarReporte {
    
    // Manera de transformar #1 File ro Byte[] (FALLIDA)
    public static byte[] FileToByteArray(File file) throws IOException{
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }
    // manera de transformar de byte[] a File #2 (FALLIDA)
    public static File ByteArrayToFile(byte[] myByteArray, String nombre) throws IOException{
        String url=System.getProperty("user.dir")+System.getProperty("file.separator");
        File reporteFile = new File(url + nombre);
        FileUtils.writeByteArrayToFile(reporteFile, myByteArray);
        return reporteFile;
    }
   
    public static void GuardarPDF(JasperPrint print, String name) throws JRException, IOException {
        String realPath = System.getProperty("user.dir") + System.getProperty("file.separator");

        JasperExportManager.exportReportToPdfFile(print, realPath + name);
    }
    
    // Guarda en la base de datos el reporte en forma de bytea
    public static void GuardarBaseDatos(int id, String name) throws IOException, SQLException {
        String realPath = System.getProperty("user.dir") + System.getProperty("file.separator");
        File reporte = new File(realPath + name);
        byte[] reporteBytea =  FileToByteArray(reporte);
        
        if(name.equals("reporteCaja.PDF")) {
            reporteCorteCaja(reporteBytea, id);
        } else if (name.equals("reporteVenta.PDF")) {
            reporteVenta(reporteBytea, id, reporte);
        }
    }
    
    // Guarda el reporte generado del corte de caja en la base de datos
    public static boolean reporteCorteCaja(byte[] reporte, int idCorte) {
        PostgreSQL d = new PostgreSQL();
        return d.ejecutar("UPDATE spve.corte_caja SET reporte_corte = '"+reporte+"' WHERE id_corte_caja = '"+idCorte+"'");
    }
    
    // Guarda el reporte generado del corte de caja en la base de datos
    public static boolean reporteVenta(byte[] reporte, int idVenta, File reporteFile) throws IOException, SQLException {
        PostgreSQL d = new PostgreSQL();
        
        FileInputStream fis = new FileInputStream(reporteFile);
        
        String sql = "UPDATE spve.venta SET reporte_venta = ? WHERE id_venta = '"+idVenta+"'";
        PreparedStatement ps = d.getConexion().prepareStatement(sql);
        ps.setBinaryStream(1, fis, (int)reporteFile.length());
        
        return ps.execute();
    }
}
