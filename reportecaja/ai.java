/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte;

/**
 *
 * @author jose
 */
import java.net.URL;
import net.sf.jasperreports.engine.JRException; 
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport; 

import net.sf.jasperreports.engine.design.JasperDesign; 

import net.sf.jasperreports.engine.util.JRLoader; 

import net.sf.jasperreports.engine.xml.JRXmlWriter; 

public class  ai { 

    public static String sourcePath, destinationPath, xml; 
public static URL  in;
    public static JasperDesign jd = new JasperDesign(); 
JasperReport reporte;
     JasperPrint reporte_view;
    public static void main(String[] args) { 
        sourcePath = "/home/jose/NetBeansProjects/factura/jcFactura/src/reporte/FacturaVentahijo.jasper"; 

        destinationPath = "/home/jose/NetBeansProjects/factura/jcFactura/src/reporte/FacturaVentahijo.jrxml"; 

        try 
        { 
          JasperReport report = (JasperReport) JRLoader.loadObject(sourcePath); 
          JRXmlWriter.writeReport(report, destinationPath, "UTF-8"); 
        } catch (JRException e) { 

        e.printStackTrace(); 

      } 

  }

}  