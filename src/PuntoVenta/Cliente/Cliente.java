/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Cliente;

import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author konstanza
 */
public class Cliente {

    private final ObjetoBaseDatos OBD;
    private final String SERVIDOR;
    private final String API_KEY;

    public Cliente(ObjetoBaseDatos obd, String servidor, String apiKey) {
        this.OBD = obd;
        this.SERVIDOR = servidor;
        this.API_KEY = apiKey;
    }

    public static void main(String[] args) {
        ObjetoBaseDatos obd = new ObjetoBaseDatos("jdbc:postgresql://localhost:5432/stpv", "inverdata", "C1234567c");
       
        Cliente cliente = new Cliente(obd, "http://127.0.0.1:8000/api", "7d263dd4882e03ab15faa9a212918ebffd4e97ad");
        cliente.sincronizar();
    }

    public void sincronizar() {
        boolean sinc = obtenerInventario();
        
        if(sinc){
            System.out.println();
            sinc = enviarVentas();
        }
        
        if(sinc) JOptionPane.showMessageDialog(null, "Sincronización con sistema de inventario exitosa", "Sincronización", JOptionPane.PLAIN_MESSAGE);
        else JOptionPane.showMessageDialog(null, "No se pudo sincronizar la aplicación con el sistema de inventario", "Error de sincronización", JOptionPane.ERROR_MESSAGE);
    }

    private boolean obtenerInventario() {
        try {
            JsonArray unidades = new Gson().fromJson(get("/unidades_inventario"), JsonArray.class);
            
            if(unidades.size() > 0){
                JsonArray almacenes = new Gson().fromJson(get("/unidades_inventario/almacenes"), JsonArray.class);
                JsonArray lotes = new Gson().fromJson(get("/unidades_inventario/lotes"), JsonArray.class);
                JsonArray productos = new Gson().fromJson(get("/unidades_inventario/productos"), JsonArray.class);
                
                if(productos.size() > 0){
                    JsonArray unidadesProductos = new Gson().fromJson(get("/unidades_inventario/unidades"), JsonArray.class);
                    this.OBD.actualizarUnidadesProductos(unidadesProductos);
                }

                this.OBD.actualizarAlmacenes(almacenes);
                this.OBD.actualizarProductos(productos);
                this.OBD.actualizarLotes(lotes);
                this.OBD.actualizarUnidadesInventario(unidades);

                return post("/unidades_inventario", "") != null;
            }
            return true;
        } catch (NullPointerException e){
            //e.printStackTrace();
            return false;
        }
    }

    private boolean enviarVentas() {
        ArrayList ventas = this.OBD.getArrayListVentasParaSincronizar();
        ArrayList ventasProductos = this.OBD.getArrayListVentasProductosParaSincronizar();
        boolean sinc = false;
        
        if(ventas.size() > 0){
            String ventasJson = new Gson().toJson(ventas);
            
            if(post("/ventas", ventasJson) == null) return false;
            sinc = true;
        }
        
        if(ventasProductos.size() > 0){
            String ventasProductosJson = new Gson().toJson(ventasProductos);
            
            if(post("/ventas/productos", ventasProductosJson) == null) return false;
            sinc = true;
        }
        
        if(sinc) this.OBD.actualizarFechaSincronizacionVentas();
        return true;
    }
    
    public String get(String pagina) {

        try {
            URL url = new URL(this.SERVIDOR + pagina);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "api-key " + this.API_KEY);

            int responseCode = conn.getResponseCode();
            System.out.println("\nEnviando request 'GET' a URL: " + url);
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            String responseString = response.toString();
            
            System.out.println(responseString);
            return responseString;

        } catch (MalformedURLException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        
        return null;
    }
    
    public String post(String pagina, String data) {
        try {
            URL url = new URL(this.SERVIDOR+pagina);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "api-key " + this.API_KEY);

            conn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();

            int responseCode = conn.getResponseCode();
            System.out.println("\nEnviando request 'POST' a URL: " + url);
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String responseString = response.toString();
            
            System.out.println(responseString);
            return responseString;

        } catch (MalformedURLException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        
        return null;
    }
}
