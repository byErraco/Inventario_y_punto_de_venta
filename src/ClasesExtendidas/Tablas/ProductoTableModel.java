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
             
              Codigo("Codigo","id_producto"),
              Descripcion("Descripcion","descripcion_producto"),
              Tipo("Tipo","tipo"),
              Cantidad("Cantidad","canidad_compra"),
              Fecha("Fecha","fecha_compra"),
              Precio("Precio","costo_unidad_compra");
              
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
                     Columnas.Tipo.getHeader(),
                     Columnas.Cantidad.getHeader(),
                     Columnas.Fecha.getHeader(),
                     Columnas.Precio.getHeader()},//columna
        new String[]{Columnas.Codigo.getColumna(),
                     Columnas.Descripcion.getColumna(),
                     Columnas.Tipo.getColumna(),
                     Columnas.Cantidad.getColumna(),
                     Columnas.Fecha.getColumna(),
                     Columnas.Precio.getColumna()});
          }
    
    

       
}
