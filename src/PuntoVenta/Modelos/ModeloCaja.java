/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PuntoVenta.Modelos;

import java.util.HashMap;

/**
 *
 * @author Programador01
 */
public class ModeloCaja {
    private int id;
    private String descripcion;    
    
    public ModeloCaja(HashMap<String, String> map){
        this.id = Integer.parseInt(map.get("id"));
        this.descripcion = map.get("descripcion");
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
}
