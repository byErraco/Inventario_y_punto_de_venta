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
*/
   

          enum Columnas{
          
              Codigo("Codigo","codigo"),
              Descripcion("Descripcion","descripcion"),
              Tipo("Tipo","tipo"),
              Cantidad("Cantidad","canidad"),
              Precio("Precio","precio");
              
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
       super(Producto,
        new String[]{Columnas.Codigo.getHeader(),Columnas.Descripcion.getHeader(),
        Columnas.Tipo.getHeader(),Columnas.Cantidad.getHeader(),Columnas.Precio.getHeader()},
        new String[]{Columnas.Codigo.getColumna(),Columnas.Descripcion.getColumna(),
        Columnas.Tipo.getColumna(),Columnas.Cantidad.getColumna(),Columnas.Precio.getColumna()});
          }
    
    

       
}
