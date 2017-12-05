package PuntoVenta.Modelos;

import java.util.HashMap;

public class ModeloProducto {

    private int id;
    private String codigoBarra;
    private String descripcion;
    private String baseImponible;
    private String pvp;
    private String isva;
    private int limiteVentaPorPersona;

    public ModeloProducto(String id, String codigoBarra, String descripcion, String idGalpon,
            String descripcionGalpon, String cantidad, String precio, String iva, String limite) {
      //  this.id = Integer.parseInt(id);
       // this.codigoBarra = codigoBarra;
       // this.descripcion = descripcion;
       // this.baseImponible = descripcionGalpon;
       // this.pvp = precio;
       // this.isva = iva;
       // this.limiteVentaPorPersona = Integer.parseInt(limite);
    }

    /**
     * Utiliza el HashMap para asignar los valores al objeto producto. Los campos
     * integer utilizan el método replace para eliminar el ".00" de los double.
     * @param map 
     */
    public ModeloProducto(HashMap<String, String> map) {
        try {
            this.id = Integer.parseInt(map.get("id"));
            this.limiteVentaPorPersona = Integer.parseInt(map.get("limitedeventaporpersona").replace(".00", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
      this.codigoBarra = map.get("codigo_barra");
       this.descripcion = map.get("descripcion");
       this.baseImponible = map.get("descripcion_g");
        this.pvp = map.get("pvp");
       this.isva = map.get("iva");
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the codigoBarra
     */
    public String getCodigoBarra() {
        return codigoBarra;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return the baseImponible
     */
    public String getBaseImponible() {
        return baseImponible;
    }

    /**
     * @return the pvp
     */
    public String getPvp() {
        return pvp;
    }

    /**
     * @return the isva
     */
    public String getIsva() {
        return isva;
    }

    /**
     * @return the limiteVentaPorPersona
     */
    public int getLimiteVentaPorPersona() {
        return limiteVentaPorPersona;
    }

}
