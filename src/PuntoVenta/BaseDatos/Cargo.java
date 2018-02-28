/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.BaseDatos;

/**
 *
 * @author inverdata
 */
public class Cargo {
    
    //String id_cargo;
    String nombre_cargo;
    
    public Cargo(String nombre_cargo){
        this.nombre_cargo = nombre_cargo;
    }
    
    public Cargo(){
        
    }
    
    
    public String getCargo() {
        return nombre_cargo;
    }

    public void setCargo(String nombre_cargo) {
        this.nombre_cargo = nombre_cargo;
    }
    
}
