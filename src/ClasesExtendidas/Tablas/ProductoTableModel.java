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
             
              Codigo("Codigo","id"),
              //CodigoBarra("Codigo Barra","codigo_barra"),
              Descripcion("Descripcion","descripcion"),
              //Costo("Costo","precio"),
              //CostoUnidad("Costo x Unidad","costoxunidad"),
             // CostoPromedio("Costo Promedio","costo_promedio"),
             // Margen("Margen","margendeganacia"),
             // BaseImponible("Base Imponible","baseimponible"),
           //   Excento("Excento","excento"),
            //  SubTotal("Sub-Total","subtotal"),
            //  PrecioCompra("Precio Compra","precio_compra"),
            //  Impuesto("Impuesto","impuesto"),
              Tipo("Tipo","tipo"),
              Cantidad("Cantidad","canidad"),
              Fecha("Fecha","fecha_actualizacion"),
              Precio("Precio","pvp");
              
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
        new String[]{Columnas.Codigo.getHeader(),
                   //  Columnas.CodigoBarra.getHeader(),
                     Columnas.Descripcion.getHeader(),
                   //  Columnas.Costo.getHeader(),
                  //   Columnas.CostoUnidad.getHeader(),
                   /*  Columnas.CostoPromedio.getHeader(),
                     Columnas.Margen.getHeader(),
                     Columnas.BaseImponible.getHeader(),
                     Columnas.Excento.getHeader(),
                     Columnas.SubTotal.getHeader(),
                     Columnas.PrecioCompra.getHeader(),
                     Columnas.Impuesto.getHeader(),  */
                     Columnas.Tipo.getHeader(),
                     Columnas.Cantidad.getHeader(),
                     Columnas.Fecha.getHeader(),
                     Columnas.Precio.getHeader()},//columna
        new String[]{Columnas.Codigo.getColumna(),
                   //  Columnas.CodigoBarra.getColumna(),
                     Columnas.Descripcion.getColumna(),
                 /*    Columnas.Costo.getColumna(),
                     Columnas.CostoUnidad.getColumna(),
                     Columnas.CostoPromedio.getColumna(),
                     Columnas.Margen.getColumna(),
                     Columnas.BaseImponible.getColumna(),
                     Columnas.Excento.getColumna(),
                     Columnas.SubTotal.getColumna(),
                     Columnas.PrecioCompra.getColumna(),
                     Columnas.Impuesto.getColumna(), */
                     Columnas.Tipo.getColumna(),
                     Columnas.Cantidad.getColumna(),
                     Columnas.Fecha.getColumna(),
                     Columnas.Precio.getColumna()});
          }
    
    

       
}
