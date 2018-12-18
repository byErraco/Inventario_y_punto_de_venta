/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesExtendidas.Tablas;

import java.util.ArrayList;
import java.util.HashMap;

        
public class ProductoTableModel extends  ArrayListTableModel {

   /* public ProductoTableModel(ArrayList<HashMap<String, String>> Producto, String[] headers, String[] columnas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
          
         VARIABLES DE LAS TABLAS (INVENTARIO) : PRODUCTO, PRODUCTO_HISTORIAL,ALMACEN_PRODUCTO,COMPRA
*/
   

          enum Columnas{
              
              DescripcionProducto("Descripcion P","descripcion_producto"),
              Codigo("Codigo","codigo_venta_producto"),
              Limite("Limite","limite_venta_persona"),
              DescripcionEmpaque("Descripcion E","descripcion_empaque");
//              CantidadDisponible("Cantidad D","cantidad_disponible"),
//              Balanza("Balanza","balanza"),
//              ProductoPreFabricado("PPF","producto_pre_fabricado"),
//              PeriodoVentaProducto("periodo venta","id_periodo_venta_producto"),
//              Fecha("Fecha","fecha_registro_precio"),
//              Ganancia("Ganancia","margen_ganancia"),
//              Impuesto("Impuesto","impuesto_producto"),
//              Precio("Precio","precio_venta_publico"),
//              BaseImponible("Base I","base_imponible"),
//              ProductoExento("Exento","producto_exento");
              
//              Codigo("Codigo","id_producto"),
//              Descripcion("Descripcion","descripcion_producto"),
//              Tipo("Tipo","tipo"),
//              Cantidad("Cantidad","canidad_compra"),
//              Fecha("Fecha","fecha_compra"),
//              Precio("Precio","costo_unidad_compra");
              
              String header;
              String columna;
              
              Columnas(String header, String columna){
                  this.header = header;
                  this.columna = columna;
               
              }
              private String getHeader(){
                     return this.header;
               }
              private String getColumna(){
                  return this.columna;
               }
          }
          
          
          
      public ProductoTableModel(ArrayList<HashMap<String, String>> Producto) {
       super(Producto,//header
        new String[]{Columnas.DescripcionProducto.getHeader(),
                     Columnas.Codigo.getHeader(),
                     Columnas.Limite.getHeader(),
                     Columnas.DescripcionEmpaque.getHeader()},
//                     Columnas.CantidadDisponible.getHeader(),
//                     Columnas.Balanza.getHeader(),
//                     Columnas.ProductoPreFabricado.getHeader(),
//                     Columnas.PeriodoVentaProducto.getHeader(),
//                     Columnas.Fecha.getHeader(),
//                     Columnas.Ganancia.getHeader(),
//                     Columnas.Impuesto.getHeader(),
//                     Columnas.Precio.getHeader(),
//                     Columnas.BaseImponible.getHeader(),
//                     Columnas.ProductoExento.getHeader()},
                     
//columna
        new String[]{Columnas.DescripcionProducto.getColumna(),
                     Columnas.Codigo.getColumna(),
                     Columnas.Limite.getColumna(),
                     Columnas.DescripcionEmpaque.getColumna()});
//                     Columnas.CantidadDisponible.getColumna(),
//                     Columnas.Balanza.getColumna(),
//                     Columnas.ProductoPreFabricado.getColumna(),
//                     Columnas.PeriodoVentaProducto.getColumna(),
//                     Columnas.Fecha.getColumna(),
//                     Columnas.Ganancia.getColumna(),
//                     Columnas.Impuesto.getColumna(),
//                     Columnas.Precio.getColumna(),
//                     Columnas.BaseImponible.getColumna(),
//                     Columnas.ProductoExento.getColumna()});
          }
    
    

       
}
