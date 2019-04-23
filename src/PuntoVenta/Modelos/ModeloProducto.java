package PuntoVenta.Modelos;

import java.util.HashMap;

public class ModeloProducto {

    private int id;
    private String codigoBarra;
    private String descripcion;
    private String baseImponible;
    private String pvp;
    private String isva;
    private double limiteVentaPorPersona;
    private int idPeriodoLimiteVenta;


    /**
     * Utiliza el HashMap para asignar los valores al objeto producto. Los campos
     * integer utilizan el método replace para eliminar el ".00" de los double.
     * @param map 
     */
    public ModeloProducto(HashMap<String, String> map) {
        try {
            this.id = Integer.parseInt(map.get("id_producto"));
            
            String limiteVenta = map.get("limite_venta_persona");
            String periodoLimite = map.get("id_periodo_venta_producto");
                    
            if(limiteVenta != null && periodoLimite != null){
                this.limiteVentaPorPersona = Double.parseDouble(limiteVenta);
                this.idPeriodoLimiteVenta = Integer.parseInt(periodoLimite);
            }
            else {
                this.limiteVentaPorPersona = -1;
                this.idPeriodoLimiteVenta = -1;
            }
            
            this.codigoBarra = map.get("codigo_venta_producto");
            this.descripcion = map.get("descripcion_producto");
            this.baseImponible = map.get("base_imponible");
            this.pvp = map.get("precio_venta_publico");
            this.isva = map.get("impuesto_producto");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public double getLimiteVentaPorPersona() {
        return limiteVentaPorPersona;
    }
    
    /**
     * @return idPeriodoLimiteVenta
     */
    public int getIdPeriodoLimiteVenta() {
        return idPeriodoLimiteVenta;
    }
}
