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
import java.io.FileNotFoundException;
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
    
    // Transforma File a byte
    public static byte[] FileToByteArray(File file) throws IOException{
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return fileContent;
    }
    // Transforma byte a file
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
    
    // Filtra el reporte verificando su nombre y enviandolo a la tabla correspondiente
    public static void GuardarBaseDatos(int id, String name) throws IOException, SQLException {
        String realPath = System.getProperty("user.dir") + System.getProperty("file.separator");
        File reporte = new File(realPath + name);
        
        switch (name) {
            case "reporteCorte.PDF":
                guardarReporte(id, reporte, "corte_caja", "reporte_corte", "id_corte_caja");
                break;
            case "reporteVenta.PDF":
                guardarReporte(id, reporte, "venta", "reporte_venta", "id_venta");
                break;
            case "reporteCierre.PDF":
                guardarReporte(id, reporte, "cierre_caja", "reporte_cierre", "id_cierre_caja");
                break;
            default:
                break;
        }
    }
    
    // Guarda el reporte en la base de datos
    public static boolean guardarReporte(int id, File reporteFile, String tabla, String campo, String filtro) throws FileNotFoundException, SQLException {
        PostgreSQL d = new PostgreSQL();
        FileInputStream fis = new FileInputStream(reporteFile);
        
        String sql = "UPDATE spve."+tabla+" SET "+campo+" = ? WHERE "+filtro+" = '"+id+"'";
        PreparedStatement ps = d.getConexion().prepareStatement(sql);
        ps.setBinaryStream(1, fis, (int)reporteFile.length());

        return ps.execute();
    }
}
