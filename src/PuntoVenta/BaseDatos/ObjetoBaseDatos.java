/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tparalate file, choose Tools | Tparalates
 * and open the tparalate in the editor.
 */
package PuntoVenta.BaseDatos;

import ClasesExtendidas.Numeros.XBigDecimal;
import Utilidades.ArticuloDescontar;
import Utilidades.Cripto;
import Utilidades.GuardarReporte;
import Utilidades.currentLoger;
import Utilidades.ValorPagos;
import java.io.File;
import java.io.IOException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase para el manejo de la base de datos del stpv.
 */
public class ObjetoBaseDatos {

    private DecimalFormat redondeo = new DecimalFormat("0.00");
    final String url;
    PostgreSQL postgreSQL;
    HashMap<String, String> mapTabla = new HashMap<String, String>();
    HashMap<String, String> mapSchema = new HashMap<String, String>();

    public ObjetoBaseDatos(String url, String login, String clave) {
        this.url = url;
        postgreSQL = new PostgreSQL();
        crearMaps();
    }

    public static void main(String[] args){
        ObjetoBaseDatos obd = new ObjetoBaseDatos("jdbc:postgresql://localhost:5432/stpv", "inverdata", "C1234567c");
        System.out.println(obd);
        //LISTOobd.crearPersona("Administrador", "", 'V', "0", "", "", "", "admin", 1);
        //LISTO//obd.modificarPersona("ernesto", "rincon", 'E', "20944806", "zulia", "7654321", "erincongil@gmail.com", "1234567");
        //LISTO//obd.eliminarPersona("25491458");
        //LISTO//obd.crearempleado("luis", "rincon", 'V', "25491458", "san jose", "04167662633", "luisjuanito@gmail.com", "admin", 1);
        //LISTO//obd.eliminarPersona('V',"25491458");
        //LISTO//obd.eliminarempleado('V',"25491458");
        //LISTO//obd.crearCierreCaja(5,4);
        //obd.crearCorteCaja(100.00, 0.00, 0.00, 1, 2);
        //LISTO//obd.seleccionarCargo(1);
        //LISTO//obd.personaExiste('V', "25491458");
        //LISTO//obd.empleadoExiste('V', "254914581");
        //LISTO//empleado empleado = obd.getDatosempleadoId(1);
        //LISTO//empleado empleado = obd.getDatosempleadoCedula("admin");
        //System.out.println(empleado.getNombre());
        //System.out.println(empleado.getApellido());
        //System.out.println(empleado.getNacionalidad());
        //System.out.println(empleado.getCedula());
        //LISTO//obd.getIdEstadoCaja(1);
        //LISTO//obd.getMapPersona('V',"25491458");
        //obd.crearVenta(1, 1);
        //..........obd.crearPago(100, 1, 1);
        //LISTO//obd.getMapCargos();
        //LISTO//obd.getMapCaja(1);
        //LISTO//obd.getMapCajas();
        //LISTO//obd.getMapProducto("1234");
        //LISTO//obd.getMapempleado(1);
        //LISTO//obd.crearCaja("caja 1");
        //LISTO//obd.eliminarProductoEnVenta(1, "1234");
        //LISTO//obd.agregarProductoEnVenta(1, "4321", 6);
        //LISTO//obd.getArrayListempleado();
        //REALIZAR PRUEBAS//obd.getArrayListEstadoCaja(1);
        //LISTO//obd.getArrayListFactura();
        //LISTO//obd.getArrayListProductos();
        //obd.getArrayListProductosEnVenta(1);
        //LISTO//obd.crearPago(100.504, 1, 1);
        //LISTO//obd.getTotalPagadoVenta(2);
        //LISTO//obd.getUltimaFechaVentaProducto('V', "0", "4321");
        //LISTO//System.out.println(obd.getTotalExentoVenta(1));
        //LISTO//System.out.println(obd.getTotalNoExentoVenta(1));
        //LISTO//System.out.println(obd.getTotalImpuestoVenta(44));
        //LISTO//System.out.println(obd.getTotalBaseImponibleVenta(1));
        //LISTO//System.out.println(obd.getSubtotalVenta(44));
        //LISTO//System.out.println(obd.getArrayListNacionalidad());
        //System.out.println(obd.crearProducto("Chocolate", "0000", '', "Chocolate savoy", 25, 0, 0, "13-04-2018", 30, 12, 24, 224, 200, 0, 6));
    }
    
    /**
     * Mapas para no tener que cambiar los nombres de los schemas y tablas si se
     * cambian en la base de datos.
     */
    private void crearMaps() {
        mapSchema.put("spve", "spve");
        mapSchema.put("inventario", "inventario");
        mapTabla.put("almacen", "almacen");
        mapTabla.put("unidad", "unidad");
        mapTabla.put("unidad_inventario", "unidad_inventario");
        mapTabla.put("precio_unidad_inventario", "precio_unidad_inventario");
        mapTabla.put("lote_produccion", "lote_produccion");
        mapTabla.put("caja", "caja");
        mapTabla.put("cierre_caja", "cierre_caja");
        mapTabla.put("corte_caja", "corte_caja");
        mapTabla.put("desglose_caja", "desglose_caja");
        mapTabla.put("estado_caja", "estado_caja");
        mapTabla.put("cargo", "cargo");
        mapTabla.put("empleado", "empleado");
        mapTabla.put("persona", "persona");
        mapTabla.put("venta", "venta");
        mapTabla.put("venta_producto", "venta_producto");
        mapTabla.put("pago", "pago");
        mapTabla.put("tipo_pago", "tipo_pago");
        mapTabla.put("producto", "producto");
        mapTabla.put("ajuste", "ajuste");
        mapTabla.put("produccion", "produccion");
        mapTabla.put("compra", "compra");
        mapTabla.put("producto_componente", "producto_componente");
        mapTabla.put("precio_producto", "precio_producto");
        mapTabla.put("periodo_venta_producto", "periodo_venta_producto");

    }
    //Valida que una persona existe
    public int personaExiste(String numero_identificacion_persona, String tipo_persona, String numero_identificacion_persona_viejo, String tipo_persona_viejo){
//        StringBuilder sqlQuery = new StringBuilder();
        StringBuilder sqlPersonaExiste = new StringBuilder();
        ResultSet rs;
        int resultado = -1;
        
        //Cuando se modifica: evita que el sistema diga que el documento existe si el individuo no cambia su cedula
        if(!( numero_identificacion_persona.equals(numero_identificacion_persona_viejo) ) || !( tipo_persona.equals(tipo_persona_viejo) )) {
            sqlPersonaExiste.append("SELECT id_persona FROM spve.persona WHERE tipo_persona = '")
                   .append(tipo_persona)
                   .append("'").append(" AND numero_identificacion_persona = '")
                   .append(numero_identificacion_persona).append("' AND activo_persona = 1");

           try {
               postgreSQL.conectar();
               rs = postgreSQL.ejecutarSelect(sqlPersonaExiste.toString());
               if(rs.next()){
                   resultado = rs.getInt("id_persona");
               }
               else {
                   resultado = -1;
               }
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               postgreSQL.desconectar();
           }
        }
        return resultado;
    }
    
    /**
     * Inserta un cliente en la tabla spve.persona.
     *
     * @param nombre
     * @param apellido
     * @param tipo_persona
     * @param numero_identificacion_persona
     * @param direccion
     * @param telefono
     * @param correo
     * @param vengoDeEmpleado verifica si el método se llamó desde crearempleado para que no envíe mensaje de error de que la persona existe
     * @return id del cliente resultado del INSERT o -1 en caso de fallar.
     */
    public int crearPersona(String nombre, String apellido, String tipo_persona, String numero_identificacion_persona, 
            String direccion, String telefono, String correo, boolean vengoDeEmpleado) {
        StringBuilder sqlQuery = new StringBuilder();
        PostgreSQL d = new PostgreSQL();
        
        int resultado = personaExiste(numero_identificacion_persona, tipo_persona, "", "");
        
        //Si la persona no existe activa, insertala
        if(resultado < 0) {
            sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("persona"))
                .append("(nombre_persona, apellido_persona, direccion_persona, tipo_persona, numero_identificacion_persona, email_persona, telefono_persona) VALUES (")
                .append("'").append(nombre).append("', ")
                .append("'").append(apellido).append("', ")
                .append("'").append(direccion).append("', ")
                .append("'").append(tipo_persona).append("', ")
                .append("'").append(numero_identificacion_persona).append("', ")
                .append("'").append(correo).append("', ")
                .append("'").append(telefono).append("');");
        
            resultado = ejecutarCreate(sqlQuery.toString(), "persona");
        }
        //Si vengo desde la creacion de un empleado muestrame este mensaje
        else if(vengoDeEmpleado) {
            JOptionPane.showMessageDialog(null, "Su empleado existe en la tabla clientes, se migraran los datos a la tabla empleados", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        //Si la persona existe activa, envia un mensaje
        else if(resultado > 0) {
            JOptionPane.showMessageDialog(null, "La persona que intenta registrar ya esta registrada", "Error", JOptionPane.INFORMATION_MESSAGE);
            resultado = -1;
        }
        
        return resultado;
    }

    /**
     * Modifica un cliente en la tabla spve.persona.
     *
     * @param nombre_persona
     * @param apellido_persona
     * @param tipo_persona
     * @param direccion_persona
     * @param telefono_persona
     * @param email_persona
     * @param numero_identificacion_persona
     * @param numero_identificacion_persona_viejo
     * @return id del cliente resultado del INSERT o -1 en caso de fallar.
     */
    public boolean modificarPersona(String nombre_persona, String apellido_persona, String tipo_persona, 
            String numero_identificacion_persona, String direccion_persona, String telefono_persona, String email_persona, 
            String numero_identificacion_persona_viejo, String tipo_persona_viejo) {
        StringBuilder sqlQuery = new StringBuilder();
        boolean resultado;
        
        sqlQuery.append("UPDATE ")
            .append(mapSchema.get("spve")).append(".")
            .append(mapTabla.get("persona"))
            .append(" SET nombre_persona='").append(nombre_persona)
            .append("', apellido_persona='").append(apellido_persona)
            .append("', tipo_persona='").append(tipo_persona)
            .append("', numero_identificacion_persona='").append(numero_identificacion_persona)
            .append("', direccion_persona='").append(direccion_persona)
            .append("', telefono_persona='").append(telefono_persona)
            .append("', email_persona='").append(email_persona)
            .append("' WHERE numero_identificacion_persona='")
            .append(numero_identificacion_persona_viejo)
            .append("' AND ").append("tipo_persona='")
            .append(tipo_persona_viejo).append("' AND activo_persona = 1")
            .append(";");
        
        resultado = ejecutarQuerySinResultado(sqlQuery.toString());
        
        return resultado;
    }

    /**
     * Elimina un cliente, dado un idCliente y la cedula del cliente. La cedula
     * es utilizada para sacar el id del cliente ya que en la tabla de empleados
     * no se guarda su id, sino la cedula.
     *
     *
     * @param tipo_persona
     * @param numero_identificacion_persona Cedula del empleado que se desee eliminar.
     * @return 
     */
    public boolean eliminarPersona(String tipo_persona, String numero_identificacion_persona) {
        boolean result = false;
        PostgreSQL d = new PostgreSQL();
        
        result = d.ejecutar("UPDATE spve.persona SET activo_persona = 0"
            + " WHERE numero_identificacion_persona='"+numero_identificacion_persona+"' AND tipo_persona='"+tipo_persona+"'");
        
        return result;
    }

    
    public boolean modificarProducto(String codigo, String descripcionProducto, String descripcionparaaque, int limiteVenta, String codigoViejo){
        StringBuilder sqlQuery = new StringBuilder();
        boolean resultado = false;
        int resultadoActivo, resultadoInactivo;
        
        resultadoActivo = productoExiste(codigo, "1", "AND codigo_venta_producto<>'"+codigoViejo+"'");

        if(resultadoActivo > 0) {
            JOptionPane.showMessageDialog(null,"El codigo que coloco ya existe", "Exito", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            sqlQuery.append("UPDATE ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" SET codigo_venta_producto ='").append(codigo)
                .append("', descripcion_producto ='").append(descripcionProducto)
                .append("', limite_venta_persona ='").append(limiteVenta)
                .append("', descripcion_paraaque ='").append(descripcionparaaque)
                .append("' WHERE codigo_venta_producto ='")
                .append(codigoViejo).append("'")
                .append(";");
        
            resultado = ejecutarQuerySinResultado(sqlQuery.toString());
        }
        
        
        return resultado;
    }
    
    //VALIDA QUE EL PRODUCTO EXISTE
    public int productoExiste(String codigo, String estado, String sqlCodigoViejo) {
        // el Parametro sqlCodigoViejo se utiliza para la parte de modificar, cuando queremos verificar que un codigo existe en la tabla
        // sin contarse a si mismo. En caso de agregar solo se envia un ";" para terminar con la sentencia sql.
        int resultado = -1;
        PostgreSQL d = new PostgreSQL();
        try{
            d.buscar("SELECT * FROM spve.producto "
                    + "WHERE codigo_venta_producto = '"+codigo+"' AND activo_producto = '"+estado+"' " + sqlCodigoViejo);
            while(d.rs.next()){
                resultado = d.rs.getInt("id_producto");
            }
        }catch(Exception e){
        }
        
        return resultado;
    }
    
    //VALIDA QUE EL empleado EXISTE
    public int empleadoExiste(String numero_identificacion_persona, String tipo_persona, String estado, 
            String numero_identificacion_persona_viejo, String tipo_persona_viejo) {
        int resultado = -1;
        PostgreSQL d = new PostgreSQL();
        //Validacion para modificar un empleado y que no te deje colocar una cedula ya existente
        if(!( numero_identificacion_persona.equals(numero_identificacion_persona_viejo) ) || !( tipo_persona.equals(tipo_persona_viejo) )) {
            try{
            d.buscar("SELECT * FROM spve.empleado INNER JOIN spve.persona ON empleado.id_persona = persona.id_persona "
                    + "WHERE numero_identificacion_persona = '"+numero_identificacion_persona+"' AND tipo_persona = '"+tipo_persona
                    + "' AND activo_empleado = '"+estado+"'");
            while(d.rs.next()){
                resultado = d.rs.getInt("id_persona");
            }
            }catch(Exception e){
            }
        }
//        System.out.println("Resultado = "+resultado);
        
        return resultado;
    }
    
    /**
     * Inserta un empleado en la tabla spve.empleado.
     *
     * @param nombre
     * @param apellido
     * @param tipo_persona
     * @param numero_identificacion_persona
     * @param direccion_persona
     * @param telefono_persona
     * @param email_persona
     * @param clave
     * @param id_cargo
     * @return id del empleado resultado del INSERT o -1 en caso de fallar.
     */
    public int crearEmpleado(String nombre, String apellido, String tipo_persona, String numero_identificacion_persona, 
            String direccion_persona, String telefono_persona, String email_persona, char[] clave, int id_cargo) {
        PostgreSQL d = new PostgreSQL();
        String passString = new String(clave);
        
        int resultado = -1;        
        int resultadoActivo = empleadoExiste(numero_identificacion_persona, tipo_persona, "1", "", "");
                
        //si el empleado no existe activo
        if(resultadoActivo < 0) {
            resultado = crearPersona(nombre, apellido, tipo_persona, numero_identificacion_persona, direccion_persona, telefono_persona, email_persona, true);
            try{
                d.buscar("SELECT * FROM spve.persona WHERE numero_identificacion_persona = '"+numero_identificacion_persona+"' AND tipo_persona ='"+tipo_persona+"'");
                while(d.rs.next()){
                    resultado = d.rs.getInt("id_persona");
                }
            }catch(Exception e){
            }
            d.ejecutar("INSERT INTO spve.empleado (clave, id_persona, id_cargo) VALUES ('"+passString+"', '"+resultado+"', '"+id_cargo+"')");
        }
        // si existe
        else {
            JOptionPane.showMessageDialog(null, "El empleado que intenta registrar ya existe", "Fallo", JOptionPane.INFORMATION_MESSAGE);
        }
        return resultado;
    }
    
    //Metodo para seleccionar el tipo de cargo que tendrá un empleado
    public Cargo seleccionarCargo(int id_cargo) {
        ResultSet rs;
        Cargo cargo = new Cargo();

        String query = "SELECT nombre_cargo FROM spve.cargo WHERE id_cargo=" +id_cargo+"";
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(query);
            while (rs.next()) {
                cargo.setCargo(rs.getString("nombre_cargo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        return cargo;
    }
    
    /**
     * Ernesto: 
     * método para crear un producto en el sistema
     * 
     */
    public int crearProducto(String descripcion_producto, String codigo_venta_producto, int limite_venta_persona, String descripcion_empaque, double cantidad_disponible, 
                                int balanza, int producto_pre_fabricado, Date fecha_registro_precio, double margen_ganancia, double porcentaje_impuesto_producto, double impuesto_producto,
                                double precio_venta_publico, double base_imponible, boolean producto_exento, int id_producto){
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        
        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append("(INSERT INTO spve.producto \n" +
                        "(descripcion_producto, codigo_venta_producto, limite_venta_persona, descripcion_empaque, \n" +
                        "cantidad_disponible, balanza, producto_pre_fabricado) \n" +
                        "VALUES (")
                .append("'").append(descripcion_producto).append("', ")
                .append("'").append(codigo_venta_producto).append("', ")
                .append("'").append(limite_venta_persona).append("', ")
                .append("'").append(descripcion_empaque).append("', ")
                .append("'").append(cantidad_disponible).append("', ")
                .append("'").append(balanza).append("', ")
                .append("'").append(producto_pre_fabricado)
                .append("');")
                .append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("precio_producto"))
                .append(" (fecha_registro_precio, margen_ganancia, porcentaje_impuesto_producto, impuesto_producto, \n" +
                        "precio_venta_publico, base_imponible, producto_exento, activo_precio_producto, id_producto) \n" +
                        "VALUES (")
                .append("current_date").append(fecha_registro_precio).append(", ")
                .append(margen_ganancia).append(", ")
                .append(porcentaje_impuesto_producto).append(", ")
                .append(impuesto_producto).append(", ")
                .append(precio_venta_publico).append(", ")
                .append(base_imponible).append(", ")
                .append(producto_exento).append(", ")
                .append(id_producto).append(");");

        resultado = ejecutarCreate(sqlQuery.toString(), "producto");
        resultado = ejecutarCreate(sqlQuery.toString(), "precio_producto");

        return resultado;
    }
    
    /**
     * Ernesto:
     * Método para obtener los tipos de personas existentes en un HashMap
     * 
     * @return tipo_persona
     */
    public ArrayList<HashMap<String, String>> getMapNacionalidad(){
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        HashMap<String, String> map = null;
        
        String[] columnaNacionalidad = {"tipo_persona AS nacionalidad"};
        sqlQuery.append("SELECT DISTINCT ");
        sqlQuery = addColumnasAlQuery(columnaNacionalidad, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(" FROM ")
                .append("spve.persona")
                .append(" WHERE activo_persona = 1;");
         
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();

                for (String columna : columnaNacionalidad) {
                    map.put(columna, rs.getString(columna));

                }
                
                resultado.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        
        
        return resultado;
    }
    
    /**
     * Ernesto: método para obtener una lista de los tipos de personas existentes en
     * la base de datos
     * @return resultado
     */
    
        public ArrayList getArrayListNacionalidad(){
        ArrayList resultado = new ArrayList();
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        
        String[] columnaNacionalidad = {"tipo_persona AS nacionalidad"};
        sqlQuery.append("SELECT DISTINCT ");
        sqlQuery = addColumnasAlQuery(columnaNacionalidad, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(" FROM ")
                .append("spve.persona")
                .append(" WHERE activo_persona = 1;");
        try {
            postgreSQL.conectar();
            
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                ArrayList map = new ArrayList();
                for(String columna : columnaNacionalidad){
                    map.add(rs.getString(columna));
                    for (int i = 0; i<map.lastIndexOf(columnaNacionalidad); i++) {
                        map.add(i, columnaNacionalidad);
                    }
                System.out.println("map "+map);
                }
                
                resultado.add(map);
                System.out.println("resultado "+resultado);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        
        
        return resultado;
    }

    /**
     * Consulta los datos de la pararesa en la tabla stpv.pararesa.
     *
     * @return pararesa resultado de la consulta o null en caso de fallar.
     */
    /*public pararesa datospararesas() {
        ResultSet rs;
        pararesa para = new pararesa();

        String query = "SELECT * FROM spve.parametros";
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(query);
            while (rs.next()) {
                para.setNombre(rs.getString("nombre_persona"));
                para.setNombre(rs.getString("apellido_persona"));
                para.setTipopararesa(rs.getString("tipo_persona"));
                para.setRif(rs.getString("numero_identificacion_persona"));
                //para.setTelefono(rs.getString("telefono_persona"));
                para.setDireccion(rs.getString("direccion_persona"));
                //para.setMoneda(rs.getString("moneda_utilizada"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        if (para != null) {
            return para;
        }
        return null;
    }*/

    /**
     * Consulta los datos del empleado en la tabla spve.empleado.
     *
     * @param idempleado
     * @return empleado resultado de la consulta o null en caso de fallar.
     */
    public Empleado getDatosEmpleadoId(int idempleado) {
        ResultSet rs;
        Empleado emp = new Empleado();

        
        String query = "SELECT persona.nombre_persona, persona.apellido_persona, persona.tipo_persona, persona.numero_identificacion_persona, " +
                        "persona.telefono_persona, persona.email_persona, " +
                        "persona.direccion_persona, empleado.id_empleado, cargo.nombre_cargo, cargo.id_cargo " +
                        "FROM spve.persona INNER JOIN spve.empleado ON persona.id_persona = empleado.id_persona " +
                        "INNER JOIN spve.cargo ON empleado.id_cargo = cargo.id_cargo WHERE id_empleado = '" + idempleado + "'";
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(query);
            while (rs.next()) {
                emp.setId(rs.getString("id_empleado"));
                emp.setNombre(rs.getString("nombre_persona"));
                emp.setApellido(rs.getString("apellido_persona"));
                emp.setNacionalidad(rs.getString("tipo_persona"));
                emp.setCedula(rs.getString("numero_identificacion_persona"));
                emp.setCorreo(rs.getString("email_persona"));
                emp.setTelefono(rs.getString("telefono_persona"));
                emp.setDireccion(rs.getString("direccion_persona"));
                emp.setCargo_id(rs.getString("id_cargo"));
                //emp.setCargo_id(rs.getString("nombre_cargo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        
        
        return emp;
    }
    
     /**
     * Consulta los datos del empleado en la tabla spve.empleado.
     *
     * @param tipo_persona
     * @param numero_identificacion_persona
     * @return empleado resultado de la consulta o null en caso de fallar.
     */
    public Empleado getDatosEmpleadoIdentificacion(String tipo_persona, String numero_identificacion_persona) {
        ResultSet rs;
        Empleado emple = new Empleado();

        String query = "SELECT persona.nombre_persona, persona.apellido_persona, persona.tipo_persona, persona.numero_identificacion_persona, \n" +
                        "persona.telefono_persona, persona.email_persona, \n" +
                        "persona.direccion_persona, empleado.id_empleado, cargo.nombre_cargo, cargo.id_cargo \n" +
                        "FROM spve.persona INNER JOIN spve.empleado ON persona.id_persona = empleado.id_persona \n" +
                        "INNER JOIN spve.cargo ON empleado.id_cargo = cargo.id_cargo WHERE numero_identificacion_persona = '" + numero_identificacion_persona +"' AND tipo_persona='"+tipo_persona+"'"
                      + " AND activo_empleado = 1";
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(query);
            while (rs.next()) {
                emple.setId(rs.getString("id_empleado"));
                emple.setNombre(rs.getString("nombre_persona"));
                emple.setApellido(rs.getString("apellido_persona"));
                emple.setNacionalidad(rs.getString("tipo_persona"));
                emple.setCedula(rs.getString("numero_identificacion_persona"));
                emple.setCorreo(rs.getString("email_persona"));
                emple.setTelefono(rs.getString("telefono_persona"));
                emple.setDireccion(rs.getString("direccion_persona"));
                emple.setCargo_id(rs.getString("id_cargo"));
                //parale.setPassword(rs.getString("clave"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        
        return emple;
    }
    
    public Producto getDatosProductoCodigo(String codigo) {
        ResultSet rs;
        Producto produc = new Producto();

        String query = "SELECT id_producto, descripcion_producto, codigo_venta_producto, limite_venta_persona" +
                       " FROM spve.producto WHERE codigo_venta_producto = '"+codigo+"'";
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(query);
            while (rs.next()) {
                produc.setId(rs.getString("id_producto"));
                produc.setCodigo(rs.getString("codigo_venta_producto"));
                produc.setDescripcionProducto(rs.getString("descripcion_producto"));
                produc.setLimiteVenta(rs.getInt("limite_venta_persona"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        
        return produc;
    }
    
    // Valida que existan usuarios creados
    public static int verificarUsuarios() {
        PostgreSQL d = new PostgreSQL();
        int resultado = -1;
        try{
            d.buscar("SELECT count(id_empleado) AS cantidad FROM spve.empleado");
            while(d.rs.next()){
                resultado = d.rs.getInt("cantidad");
            }
            }catch(Exception e){
            }

        return resultado;
    }
    
//    // Valida que los parametros esten creados
    public static int verificarParametros() {
        PostgreSQL d = new PostgreSQL();
        int resultado = -1;
        try{
            d.buscar("SELECT count(nombre) AS cantidad FROM spve.parametros");
            while(d.rs.next()){
                resultado = d.rs.getInt("cantidad");
            }
            }catch(Exception e){
            }

        return resultado;
    }
    
    // Crea los parametros iniciales
    public void crearParametros(HashMap<String, String> map) {
        PostgreSQL d = new PostgreSQL();
        for ( HashMap.Entry<String, String> entry : map.entrySet() ) {   
            d.ejecutar("INSERT INTO spve.parametros (nombre, valor) VALUES"
                        + " ('"+entry.getKey()+"', '"+entry.getValue()+"')");
        }
    }
    
    public Parametros getDatosParametros() {
        PostgreSQL d = new PostgreSQL();
        Parametros para = new Parametros();
        
        String[] claveFila = {"pais", "nombre", "identificacion", "direccion", "telefono", "moneda", "logo"};
        
        for (String claveFila1 : claveFila) {
            try {
                d.buscar("SELECT * FROM spve.parametros WHERE nombre = '" + claveFila1 + "'");
                while (d.rs.next()) {
                    para.setParameter(claveFila1, d.rs.getString("valor"));
                }
            }catch(Exception e){
            }
        }
        
        return para;
    }
    
    public void activarPais(String pais) {
        PostgreSQL d = new PostgreSQL();
        d.ejecutar("UPDATE spve.pais set activo = true WHERE nombre_pais = '"+pais+"'");
    }
    
    public Pais getDatosPais(String sqlActivo) {
        PostgreSQL d = new PostgreSQL();
        Pais p = new Pais();
        
        try{
            d.buscar("SELECT * FROM spve.pais" + sqlActivo);
            while(d.rs.next()){
                p.setNombre(d.rs.getString("nombre_pais"));
                p.setIdZonaHoraria(d.rs.getString("id_zona_horaria"));
                p.setMoneda(d.rs.getString("moneda"));
                p.setNacionalidad(d.rs.getString("nacionalidad"));
                p.setGmtZona(d.rs.getString("gmt_zona"));
                p.setSimbolo(d.rs.getString("simbolo"));
                p.setFormatoId(d.rs.getString("formato_id"));
                p.setActivo(d.rs.getString("activo"));
                p.setIdentificacion(d.rs.getString("identificacion"));
            }
        }catch(Exception e){
        }
        
        return p;
    }
    
    public Empleado getDatosEmpleadoClave(char[] clave) {
        PostgreSQL d = new PostgreSQL();
        Empleado emple = new Empleado();
        String passString = new String(clave);
        
        try{
            d.buscar("SELECT * FROM spve.empleado WHERE clave = '"+passString+"' AND activo_empleado = 1 AND id_cargo = 1");
            while(d.rs.next()){
                emple.setId(d.rs.getString("id_empleado"));
//                parale.setNombre(d.rs.getString("nombre_persona"));
//                parale.setApellido(d.rs.getString("apellido_persona"));
//                parale.setNacionalidad(d.rs.getString("tipo_persona"));
//                parale.setCedula(d.rs.getString("numero_identificacion_persona"));
//                parale.setCorreo(d.rs.getString("email_persona"));
//                parale.setTelefono(d.rs.getString("telefono_persona"));
//                parale.setDireccion(d.rs.getString("direccion_persona"));
                emple.setCargo_id(d.rs.getString("id_cargo"));
            }
        }catch(Exception e){
        }
        
        return emple;
    }
    
    /**
     * Inserta los datos de la pararesa en la tabla stpv.pararesa.
     *
     * @param nombre
     * @param rif
     * @param telefono
     * @param moneda
     * @param direccion
     */
    /*public void ingresopara(String nombre, String rif, String telefono, String direccion, String moneda) {
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String sql = "SELECT * FROM stpv.pararesa";
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sql);
            while (rs.next()) {
                sql = "UPDATE stpv.pararesa SET nombre='" + nombre + "',rif='" + rif + "',direccion='" + direccion + "',moneda_utilizada='" + moneda + "'";
                try {
                    postgreSQL.conectar();
                    postgreSQL.getSentencia().executeQuery(sql);
                } catch (Exception ex) {
                } finally {
                    postgreSQL.desconectar();
                }
                return;
            }
            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("stpv")).append(".")
                    .append("pararesa")
                    .append(" (nombre, rif, telefono, direccion, moneda_utilizada)")
                    .append(" VALUES (")
                    .append("'").append(nombre).append("', ")
                    .append("'").append(rif).append("', ")
                    .append("'").append(telefono).append("', ")
                    .append("'").append(direccion).append("', ")
                    .append("'").append(moneda).append("')");
            try {
                postgreSQL.conectar();
                postgreSQL.getSentencia().executeQuery(sqlQuery.toString());
            } catch (Exception ex) {
            } finally {
                postgreSQL.desconectar();
            }
        } catch (Exception ex) {
            Logger.getLogger(ObjetoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/

    /**
     * Modifica los datos del empleado en la tabla spve.empleado.
     *
     * @param id_cargo
     * @param nombre_persona
     * @param apellido_persona
     * @param tipo_persona
     * @param numero_identificacion_persona
     * @param clave
     * @param email_persona
     * @param telefono_persona
     * @param departamento
     * @param direccion_persona
     * @param numero_identificacion_persona_viejo
     * @param tipo_persona_viejo
     * @return
     */
    public boolean modificarempleado(int id_cargo, String nombre_persona, String apellido_persona, 
            String tipo_persona, String numero_identificacion_persona, String direccion_persona, String telefono_persona, 
            String email_persona, String clave, String numero_identificacion_persona_viejo, String tipo_persona_viejo) {
        PostgreSQL d = new PostgreSQL();
        int idempleadoAdministrador = -1;
        boolean resultado = false;
        
        Empleado emple = getDatosEmpleadoIdentificacion(tipo_persona_viejo, numero_identificacion_persona_viejo);

        // valida que no se quede sin usuario administrador el programa
        if(id_cargo != 1) {
            try{
                d.buscar("SELECT id_empleado FROM spve.empleado WHERE id_cargo = 1 AND id_empleado <> '"+emple.getId()+"' AND activo_empleado = 1");
                while(d.rs.next()){
                    idempleadoAdministrador = d.rs.getInt("id_empleado");
                }
            }catch(Exception e){}
            
            if(idempleadoAdministrador < 0) {
                JOptionPane.showMessageDialog(null, "No puede modificar su cargo porque no existen mas administradores", "Fallo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                resultado = d.ejecutar("UPDATE spve.empleado SET id_cargo = '"+id_cargo+"', clave = '"+clave+"' WHERE id_persona IN (SELECT id_persona FROM spve.persona "
                    + "WHERE numero_identificacion_persona = '"+numero_identificacion_persona+"' AND tipo_persona= '"+tipo_persona+"') AND activo_empleado = 1");  
            }
        } else {
            resultado = d.ejecutar("UPDATE spve.empleado SET id_cargo = '"+id_cargo+"', clave = '"+clave+"' WHERE id_persona IN (SELECT id_persona FROM spve.persona "
                    + "WHERE numero_identificacion_persona ='"+numero_identificacion_persona+"' AND tipo_persona='"+tipo_persona+"') AND activo_empleado = 1");  
        }
        
        return resultado;
    }
    
    /**
     * Elimina un empleado, dado un idempleado y la cedula del empleado. La
     * cedula es utilizada para sacar el id del empleado ya que en la tabla de
     * empleados no se guarda su id, sino la cedula.
     *
     *
     * @param tipo_persona
     * @param cedula Cedula del empleado que se desee eliminar.
     * @return
     */ 
    public boolean eliminarEmpleado(String tipo_persona, String cedula) {
        boolean result = false;

        PostgreSQL d = new PostgreSQL();
        currentLoger c = new currentLoger();
        
        Empleado empLogueado = c.getDatosEmpleadoLogueado();
        Empleado empSeleccionado = getDatosEmpleadoIdentificacion(tipo_persona, cedula);
        int idempleadoAdministrador = -1;
//        empleado parale = getDatosempleadoIdentificacion(tipo_persona_viejo, numero_identificacion_persona_viejo);
                
        if( !( empLogueado.getId().equals(empSeleccionado.getId()) ) ) {
            if( empSeleccionado.getCargo_id().equals("1") ) {
                try{
                    d.buscar("SELECT id_empleado FROM spve.empleado WHERE id_cargo = 1 AND id_empleado <> '"
                            +empSeleccionado.getId()+"' AND activo_empleado = 1");
                    while(d.rs.next()){
                        idempleadoAdministrador = d.rs.getInt("id_empleado");
                    }
                }catch(Exception e){}

                if(idempleadoAdministrador < 0) {
                    JOptionPane.showMessageDialog(null, "No puede eliminar a este empleado porque no existen mas administradores", "Fallo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //tabla empleado
                    result = d.ejecutar("UPDATE spve.empleado SET activo_empleado = 0"
                    + " WHERE id_persona IN"
                    + " (SELECT id_persona FROM spve.persona"
                    + " WHERE numero_identificacion_persona='"+cedula+"' AND tipo_persona='"+tipo_persona+"')");
                    //tabla personas
                    eliminarPersona(tipo_persona, cedula);
                }
            } else {
                //tabla empleado
                result = d.ejecutar("UPDATE spve.empleado SET activo_empleado = 0"
                + " WHERE id_persona IN"
                + " (SELECT id_persona FROM spve.persona"
                + " WHERE numero_identificacion_persona='"+cedula+"' AND tipo_persona='"+tipo_persona+"')");
                //tabla personas
                eliminarPersona(tipo_persona, cedula);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede eliminar a si mismo", "Fallo", JOptionPane.INFORMATION_MESSAGE);
        }
        
        return result;
    }

    
    /**
     * Verifica la existencia de un usuario en la base de datos. -Utiliza el
     * schema "stpv" del mapSchema y la tabla "usuario" del mapTabla.
     *
     * @param cedula usuario a validar.
     * @param clave del usuario.
     * @return Id del usuario. Si no existe retorna -1
     */
    public int autenticarUsuario(String cedula, char[] clave) {
        ResultSet result;
        int id = -1;

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT id_empleado AS id, clave FROM ")
                .append(mapSchema.get("stpv"))
                .append(".").append(mapTabla.get("empleado"))
                .append(" WHERE numero_identificacion_persona='")
                .append(cedula)
                .append("';");

        try {
            postgreSQL.conectar();
            result = postgreSQL.ejecutarSelect(sqlQuery.toString());
            String p = null;

            if (result.next()) {
                id = result.getInt("id");
                p = result.getString("clave");
            }
            Cripto c = new Cripto(p);
            boolean autenticado = c.validate(new String(clave));
            if (autenticado) {
                return id;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            id = -1;
        } finally {
            postgreSQL.desconectar();
        }

        return id;
    }

    /**
     * Verifica la existencia de un empleado en la base de datos. -Utiliza el
     * schema "stpv" del mapSchema y la tabla "empleado" del mapTabla. Este
     * método funciona con la encriptacion sha1 del clave.
     *
     * @param cedula empleado a validar.
     * @param clave del empleado.
     * @return Id del empleado. Si no existe retorna -1
     */
    public int autenticarEmpleado(String cedula, char[] clave) {
        ResultSet result;
        int id = -1;

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT numero_identificacion_persona AS id, clave FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("empleado"))
                .append(" WHERE numero_identificacion_persona='")
                .append(cedula)
                .append("';");

        try {
            postgreSQL.conectar();
            result = postgreSQL.ejecutarSelect(sqlQuery.toString());
            String p = null;

            if (result.next()) {
                id = result.getInt("id");
                p = result.getString("clave");
            }
            char[] pass = PuntoVenta.Ventanas.Bloqueo1.jpwClave.getPassword();

            String passString = new String(pass);

            if (passString.equals(p)) {

                PuntoVenta.Inicio.MenuPrincipal.btnCaja.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnFacturas.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnVentas.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnAyuda.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnAcerca.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnAdmin.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnProductos.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnInventario.setEnabled(true);
                PuntoVenta.Ventanas.Bloqueo1.btnIngresar.setEnabled(true);
                PuntoVenta.Ventanas.Bloqueo1.btnIngresar.requestFocus();

            } else {
                return -1;
            }

        } catch (Exception e) {
          //  e.printStackTrace();
            id = -1;
        } finally {
            postgreSQL.desconectar();
        }

        return id;
    }

    public int autenticarEmpleado2(String cedula, char[] clave, String tipo) {
        int id = -1;
        PostgreSQL d = new PostgreSQL();
        //char[] pass = PuntoVenta.Ventanas.LogIn.jpwClave.getPassword();

        //String passString = new String(pass);
        StringBuilder sqlQuery = new StringBuilder();
        String passString = new String(clave);
 
        try{
            d.buscar("SELECT id_empleado, clave, tipo_persona FROM spve.empleado as e INNER JOIN spve.persona as p ON e.id_persona = p.id_persona "
                + " WHERE numero_identificacion_persona ='"+cedula+"'"
                + " AND tipo_persona='"+tipo+"'"
                + " AND clave='"+passString+"'"
                + " AND activo_empleado = '1'");
            while(d.rs.next()){
                    id = d.rs.getInt("id_empleado");
            }
        }catch(Exception e) {
        }
        
        return id;
    }

    public int autsupervisor(String clave) {
        ResultSet result;
        int id1 = 1;
        int id = -1;
//        System.out.println(clave + "1111");
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT clave FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("empleado"))
                .append(" WHERE id_cargo='")
                .append(id1)
                .append("'AND clave='")
                .append(clave)
                .append("';");

        try {
            postgreSQL.conectar();
            result = postgreSQL.ejecutarSelect(sqlQuery.toString());
            String j = null;
            if (result.next()) {
                j = result.getString("clave");
            }
//            System.out.println(j + " este es el bd");
            if (clave.equals(j)) {
//                System.out.println("lol");
                id = 6;
            } else {
//                System.out.println(clave + "no lo es");
                id = -1;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            postgreSQL.desconectar();
        }

        return id;
    }

    public boolean actualizarEmpleado(String nombre_persona, String apellido_persona, String tipo_persona, String numero_identificacion_persona, 
            String telefono_persona, String email_persona, String direccion_persona, int id_cargo, String clave, String numero_identificacion_persona_viejo, 
            String tipo_persona_viejo) {

        boolean resultado = true;
        int resultadoActivo;
        //Verifica si el documento existe
        resultadoActivo = personaExiste(numero_identificacion_persona, tipo_persona, numero_identificacion_persona_viejo, tipo_persona_viejo);
        
        if(resultadoActivo > 0) {
            JOptionPane.showMessageDialog(null, "El documento de identificacion que intenta registrar ya existe", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            modificarPersona(nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, 
                    direccion_persona, telefono_persona, email_persona, numero_identificacion_persona_viejo, tipo_persona_viejo);
            resultado =  modificarempleado(id_cargo, nombre_persona, apellido_persona, tipo_persona, 
                    numero_identificacion_persona, telefono_persona, email_persona, direccion_persona, clave, 
                    numero_identificacion_persona_viejo, tipo_persona_viejo);
        }
       
        return resultado;
    }

    public boolean actualizarCliente(String nombre_persona, String apellido_persona, 
            String tipo_persona, String numero_identificacion_persona, String telefono_persona, String email_persona, 
            String direccion_persona, String numero_identificacion_persona_viejo, String tipo_persona_viejo) {

        boolean resultado = false;
        //verifica si el documento existe
        int resultadoActivo = personaExiste(numero_identificacion_persona, tipo_persona, numero_identificacion_persona_viejo, tipo_persona_viejo);
        
        if(resultadoActivo > 0) {
            JOptionPane.showMessageDialog(null, "El documento de identificacion que intenta registrar ya existe", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            resultado = modificarPersona(nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, 
                direccion_persona, telefono_persona, email_persona, numero_identificacion_persona_viejo, tipo_persona_viejo);
        }

        return resultado;
    }

    /**
     * Actualiza la informacion de un empleado basandose en el Modeloempleado.
     *
     * @param cliente
     * @return
     */
    //public int actualizarClienteAdmin(ModeloCliente cliente) {
        /*StringBuilder sqlQuery = new StringBuilder(ModeloCliente cliente, String nombre_persona, String apellido_persona, String tipo_persona, String numero_identificacion_persona, String telefono_persona, String email_persona, String direccion_persona){
        int resultado;
        modificarPersona(nombre_persona, apellido_persona, direccion_persona, telefono_persona, email_persona, numero_identificacion_persona);
        resultado = ejecutarCreate(sqlQuery.toString(), "persona");
        return resultado;

    }*/

    public boolean actualizarProducto(String codigo, String descripcionProducto, String descripcionEmpaque, int limiteVenta, String codigoViejo){

        boolean resultado;
        resultado = modificarProducto(codigo, descripcionProducto, descripcionEmpaque, limiteVenta, codigoViejo);

        return resultado;
    }
    
    /**
     * Verifica en la base de datos si una caja específica está abierta o
     * cerrada.
     *
     * @param idCaja
     * @return Estado de la caja. Abierto o cerrado.
     */
    public boolean isCajaAbierta(int idCaja, boolean primeraVez) {
        boolean cajaAbierta = false;
        int caja_abierta = 0;
        ResultSet rs;
        PostgreSQL d = new PostgreSQL();
        StringBuilder sqlQuery = new StringBuilder();
        
        // Busca el cierre de caja que este relacionado al ultimo id_caja de la tabla 
        // estado_caja, de no haber ninguno envia false
//        sqlQuery.append("SELECT EXISTS (SELECT id_cierre_caja, fecha_corte FROM ")
//                .append(mapSchema.get("spve"))
//                .append(".").append(mapTabla.get("cierre_caja"))
//                .append(" as cic LEFT JOIN ")
//                .append(mapSchema.get("spve"))
//                .append(".").append(mapTabla.get("corte_caja"))
//                .append(" AS c ")
//                .append("ON cic.id_corte_caja = c.id_corte_caja ")
//                .append("WHERE cic.id_corte_caja = (SELECT max(id_corte_caja) FROM ")
//                .append(mapSchema.get("spve"))
//                .append(".").append(mapTabla.get("corte_caja"))
//                .append(" AS cc WHERE cc.id_estado_caja = (SELECT max(id_estado_caja) FROM ")
//                .append(mapSchema.get("spve"))
//                .append(".").append(mapTabla.get("estado_caja"))
//                .append(" AS ec WHERE ec.id_caja = ")
//                .append(idCaja)
//                .append(")) AND fecha_corte < current_date);");
        
        sqlQuery.append("SELECT EXISTS (SELECT id_estado_caja, fecha_cierre FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("cierre_caja"))
                .append(" AS cc WHERE cc.id_estado_caja = (SELECT max(id_estado_caja) FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("estado_caja"))
                .append(" AS ec WHERE ec.id_caja = ")
                .append(idCaja)
                .append(") AND fecha_cierre < current_date);");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            
            if(rs.next()){
                cajaAbierta = !rs.getBoolean("exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        
        //Busca el smallint "caja_abierta" de la tabla estado_caja
        try{
            d.buscar("SELECT caja_abierta FROM spve.estado_caja WHERE id_caja = '"+idCaja+"'");
            while(d.rs.next()) {
                caja_abierta = d.rs.getInt("caja_abierta");
            }
        }catch(Exception e){}
        
//        if(cajaAbierta == true && caja_abierta == 1 && primeraVez) {
//            JOptionPane.showMessageDialog(null, "Hemos detectado un mal cierre del sistema", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//        }

        return (cajaAbierta && caja_abierta == 1);
    }
   
    /**
     * Verifica en la base de datos si una caja específica está abierta o
     * cerrada.
     *
     * @param idCaja
     * @return id de la caja si está abierta o -1 si está cerrada.
     */
    public int getIdEstadoCaja(int idCaja) {
        int idEstadoCaja = -1;
        ResultSet rs;
 
        if(isCajaAbierta(idCaja, false)){
            StringBuilder sqlQuery = new StringBuilder();
            sqlQuery.append("SELECT MAX(id_estado_caja) FROM ")
                    .append(mapSchema.get("spve"))
                    .append(".").append(mapTabla.get("estado_caja"))
                    .append(" WHERE ").append("id_caja = '"+idCaja)
                    .append("' AND caja_abierta = 1")
                    .append(";");
            try {
                postgreSQL.conectar();
                rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
                if (rs.next()) {
                    idEstadoCaja = rs.getInt("max");
                    System.out.println(idEstadoCaja);
                }
            } catch (Exception e) {
                e.printStackTrace();
                idEstadoCaja = -1;
            } finally {
                postgreSQL.desconectar();
            }   
        }
        return idEstadoCaja;
    }

    public ArrayList<HashMap<String, String>> getArrayListEmpleado() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        HashMap<String, String> map;
        
        String[] columnasempleado = {"tipo_persona||'-'||numero_identificacion_persona AS identificacion_persona", "nombre_persona||' '||apellido_persona AS nombre_persona", "nombre_cargo"};
        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnasempleado, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(" FROM ")
                .append("spve.persona AS p INNER JOIN spve.empleado AS e ON p.id_persona = e.id_persona")
                .append(" LEFT JOIN spve.cargo AS c ON e.id_cargo = c.id_cargo WHERE activo_empleado = 1");
         
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnasempleado) {
                    map.put(columna, rs.getString(columna));
                }
                resultado.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        
        return resultado;
    }

    /**
     * Método que busca los registros de apertura y cierre de una caja
     * particular y devuelve el tipo y número de identificación del empleado que abrió la caja,
     * la fecha de apertura, el tipo y número de identificación del empleado que cerró la caja
     * y la fecha de cierre
     * 
     * @param idCaja
     * @return Arraylist de HashMap
     */
    public ArrayList<HashMap<String, String>> getArrayListEstadoCaja(int idCaja, String sql) {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        // agregado "id_corte_caja" a "columasEstadoCaja"
//        String[] columnasEstadoCaja = {"p.tipo_persona||'-'||p.numero_identificacion_persona AS empleado", "fecha_apertura",/* "fecha_corte", "id_corte_caja"*/};
//        sqlQuery.append("SELECT ");
//        sqlQuery = addColumnasAlQuery(columnasEstadoCaja, "", sqlQuery);
//        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
//        sqlQuery.append(" FROM spve.estado_caja as ec INNER JOIN spve.caja as c ON ec.id_caja = c.id_caja")
//                .append(" LEFT JOIN spve.corte_caja as cc ON ec.id_estado_caja = cc.id_estado_caja")
//                //.append(" LEFT JOIN spve.cierre_caja as cic ON cc.id_corte_caja = cic.id_corte_caja")
//                .append(" INNER JOIN spve.empleado as em ON ec.id_empleado = em.id_empleado")
//                .append(" INNER JOIN spve.persona as p ON em.id_persona = p.id_persona")
//                //.append(" LEFT JOIN spve.empleado as em1 ON cc.id_empleado = em1.id_empleado")
//                //.append(" LEFT JOIN spve.persona as p1 ON em1.id_persona = p1.id_persona")
//                .append(" WHERE ec.id_caja = ")
//                .append(idCaja)
//                .append(" AND activo_caja = 1")
//                //.append(" GROUP BY ec.id_estado_caja, descripcion_caja, empleado_apertura, fecha_apertura, fecha_cierre")
//                .append(" ORDER BY fecha_apertura DESC;");
        
        String[] columnasEstadoCaja = {"p.tipo_persona||'-'||p.numero_identificacion_persona AS empleado", "fecha_apertura", "caja_abierta", "id_estado_caja"};
        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnasEstadoCaja, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(" FROM spve.estado_caja as ec INNER JOIN spve.caja as c ON ec.id_caja = c.id_caja")
                .append(" INNER JOIN spve.empleado as em ON ec.id_empleado = em.id_empleado")
                .append(" INNER JOIN spve.persona as p ON em.id_persona = p.id_persona")
                .append(" WHERE ec.id_caja = ")
                .append(idCaja)
                .append(" AND activo_caja = 1").append(sql)
                .append(" ORDER BY fecha_apertura DESC;");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (String columna : columnasEstadoCaja) {
                    if(columna.equals("caja_abierta")) {
                        switch(rs.getString(columna)) {
                            case "1": row.put(columna, "Abierta"); break;
                            case "0": row.put(columna, "Cerrada"); break;
                        }
                    } else {
                        row.put(columna, rs.getString(columna));
                    }
                }
                resultado.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }
/** Ernesto:
 * Método para obtener una lista de las facturas relacionadas a ventas activas
 * 
 * Konstanza: función no terminada, falta devolver el total de la venta y el código agregando sus '0'
 * @return 
 */
    public ArrayList<HashMap<String, String>> getArrayListFactura() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        ResultSet rs;
        
        StringBuilder sqlQuery = new StringBuilder();
        
        String[] columnas = {"codigo_factura", "CONCAT (nombre_persona,' ', apellido_persona) AS identificacion_persona", "fecha_venta", "estado_venta"};
        
        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnas, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        
        sqlQuery.append(" FROM spve.persona p INNER JOIN spve.venta v ON v.id_persona= p.id_persona WHERE activo_venta = 1 ORDER BY v.codigo_factura DESC;");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            /*
            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (int i = 1; i < meta.getColumnCount() + 1; i++) {
                    String codigo;
                    if ("codigo_factura".equals(meta.getColumnName(i))) {
                        codigo = rs.getString(i);
                        while (codigo.length() < 10) {
                            codigo = "0" + codigo;
                        }
                        row.put(meta.getColumnName(i), codigo);
                    } else {
                        row.put(meta.getColumnName(i), rs.getString(i));
                    }
                }

                resultado.add(row);
            }*/
            
            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (String columna : columnas) {
                    // ingresa el string correspondiente para cada número
                    if(columna.equals("estado_venta")) {
                        switch(rs.getString(columna)) {
                            case "1": row.put(columna, "En proceso"); break;
                            case "2": row.put(columna, "Finalizada"); break;
                            case "3": row.put(columna, "Pausada");    break;
                            case "4": row.put(columna, "Cancelada");  break;
                        }
                    } else {
                        row.put(columna, rs.getString(columna));
                    }
                }
                resultado.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        System.out.println(resultado);
        return resultado;
    }

    /**
     * Método que busca los desgloses de moneda y devuelve el id, el valor y la
     * descripcion.
     *
     *
     * @return Arraylist de HashMap
     */
    public ArrayList<HashMap<String, String>> getArrayListDesgloseMoneda() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String[] columnas = {"id", "valor", "descripcion"};

        sqlQuery.append("SELECT m.id, m.valor, m.descripcion ")
                .append("FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("moneda")).append(" AS m ")
                .append("ORDER BY m.id ASC;");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (String columna : columnas) {
                    row.put(columna, rs.getString(columna));
                }
                resultado.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    /**
     * Obtiene una lista de los productos que fueron asociados a una venta
     * particular
     *
     * @param idVenta
     * @return ArrayList<HashMap<String, String>>
     */
    public ArrayList<HashMap<String, String>> getArrayListProductosEnVenta(int idVenta) {
        if (idVenta <= 0) {
            return null;
        }
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        HashMap<String, String> map;
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String[] columnaProductos = {"codigo_venta_producto", "descripcion_producto", "unidad_inventario.id AS id_unidad_inventario", "CONCAT(almacen.nombre, '. ', almacen.direccion) AS almacen", 
                                        "lote_produccion.codigo AS codigo_lote", "venta_producto.cantidad_producto AS cantidad_producto", "(CASE\n" +
                                        "    WHEN exento = TRUE THEN precio_venta_publico\n" +
                                        "    ELSE COALESCE(base_imponible, 0)\n" +
                                        "END) AS precio_base_unitario", "(CASE\n" +
                                        "    WHEN exento = TRUE THEN 0\n" +
                                        "    ELSE COALESCE(impuesto, 0)\n" +
                                        "END) AS impuesto", "precio_venta_publico", "(venta_producto.cantidad_producto*precio_venta_publico) AS total"};

        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnaProductos, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" ON unidad_inventario.producto_id = producto.id_producto")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("almacen"))
                .append(" ON unidad_inventario.almacen_id = almacen.id") 
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("lote_produccion"))
                .append(" ON unidad_inventario.lote_produccion_id = lote_produccion.id")
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("precio_unidad_inventario"))
                .append(" ON unidad_inventario.id = precio_unidad_inventario.unidad_inventario_id ")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta_producto"))
                .append(" ON venta_producto.id_unidad_inventario = unidad_inventario.id")
                .append(" WHERE precio_unidad_inventario.activo = 1 AND activo_venta_producto = 1 AND id_venta=").append(idVenta).append(";");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnaProductos) {
                    if (("precio_base_unitario".equals(columna) || "impuesto".equals(columna) || "precio_venta_publico".equals(columna) || "total".equals(columna))) {
                        map.put(columna, redondeo.format(Double.parseDouble(rs.getString(columna))).replace(",", "."));
                    } else {
                        map.put(columna, rs.getString(columna));
                        ;
                    }
                }
                System.out.println("MAP "+map);
                resultado.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    /**
     * Método que busca los productos existentes en la tabla
     * inventario.productos y los almacena en un ArrayList
     *
     * @return Arraylist de HashMap
     */
    public ArrayList<HashMap<String, String>> getArrayListProductos() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        HashMap<String, String> map;
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String[] columnaProducto = {"codigo_venta_producto", "descripcion_producto", "unidad.descripcion AS unidad", "balanza", "seguimiento"};

        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnaProducto, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad"))
                .append(" ON producto.id_unidad = unidad.id")
                .append(" WHERE activo_producto = 1;");

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnaProducto) {
                    map.put(columna, rs.getString(columna));
                }
                resultado.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        
        return resultado;
    }
    
    /**
     * Devuelve la lista de tipos de pago activos
     * 
     * @return 
     */
    public ArrayList<HashMap<String, String>> getArrayListTipoPago() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        HashMap<String, String> map;
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String[] columnasTipoPago = {"id_tipo_pago", "descripcion_pago"};

        sqlQuery.append("SELECT ");
        for (String columna : columnasTipoPago) {
            sqlQuery.append(columna).append(",");
        }
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM spve.tipo_pago WHERE activo_tipo_pago = 1;");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnasTipoPago) {
                    map.put(columna, rs.getString(columna));
                }
                resultado.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    /**
     * Devuelve la lista de personas activas que no existen en la tabla de empleados
     * 
     * @return 
     */
    public ArrayList<HashMap<String, String>> getArrayListClientes() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        HashMap<String, String> map;
        
        String[] columnasCliente = {"tipo_persona||'-'||numero_identificacion_persona AS identificacion_persona", "nombre_persona||' '||apellido_persona AS nombre_persona"};
        
        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnasCliente, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(" FROM ")
                .append("spve.persona WHERE NOT id_persona IN (SELECT id_persona FROM spve.empleado WHERE activo_empleado=1) AND activo_persona = 1;");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnasCliente) {
                    map.put(columna, rs.getString(columna));
                }
                resultado.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        

        return resultado;
    }

    /**
     * Método para obtener la lista de cortes que se le han realizado a un
     * estado_caja.
     *
     * @param idEstadoCaja
     * @return
     */
    public ArrayList<HashMap<String, String>> getArrayListCortesCaja(int idEstadoCaja) {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        HashMap<String, String> map;
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String[] columnasCorteCaja = {"fecha_corte", "monto_corte", "id_corte_caja", "tipo_persona||'-'||numero_identificacion_persona AS empleado"};

        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnasCorteCaja, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM spve.corte_caja")
                .append(" INNER JOIN spve.empleado ON corte_caja.id_empleado = empleado.id_empleado")
                .append(" INNER JOIN spve.persona ON persona.id_persona = empleado.id_persona")
                .append(" WHERE id_estado_caja = ").append(idEstadoCaja)
                .append(";");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnasCorteCaja) {
                    if(columna.equals("monto_corte")) {
                        map.put(columna, String.format(Locale.US, "%.2f",rs.getDouble(columna)));
                    } else {
                        map.put(columna, rs.getString(columna));
                    }
                }
                resultado.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    /**
     * Devuelve todos los cargos activos en un HashMap
     * donde el String es el nombre del cargo e Integer el id
     * 
     * @return 
     */
    public HashMap<String, Integer> getMapCargos(){
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, Integer> cargos = new HashMap<>();
        ResultSet rs;
        
        sqlQuery.append("SELECT id_cargo, nombre_cargo FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("cargo"))
                .append(" WHERE activo_cargo = 1;");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            
            while (rs.next()) {
                cargos.put(rs.getString("nombre_cargo"), rs.getInt("id_cargo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            cargos = null;
        } finally {
            postgreSQL.desconectar();
        }
        System.out.println(cargos);
        return cargos;
    }
    
    /**
     * Método para agregar una caja al sistema
     * @param descripcion_caja
     * @return
     */
    public int crearCaja(String descripcion_caja){
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        
        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("caja"))
                .append("(descripcion_caja)")
                .append(" VALUES ")
                .append("('").append(descripcion_caja)
                .append("');");
        
        resultado = ejecutarCreate(sqlQuery.toString(), "caja");

        return resultado;
        
    }
    
    /**
     * Método para crear un producto nuevo con su precio
     * 
     * 
     */
    
    /**
     * Método para obtener un mapa <k,v> dado un id_caja
     * 
     * @param id_caja
     * @return 
     */
    
    public HashMap<String, String> getMapCaja(int id_caja){
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> cajas = new HashMap<>();
        ResultSet rs;
        
        sqlQuery.append("SELECT id_caja, descripcion_caja FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("caja"))
                .append(" WHERE id_caja = "+id_caja+" AND activo_caja = 1;");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            
            while (rs.next()) {
                cajas.put("id_caja", rs.getString("id_caja"));
                cajas.put("descripcion_caja", rs.getString("descripcion_caja"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            cajas = null;
        } finally {
            postgreSQL.desconectar();
        }
        System.out.println(cajas);
        return cajas;
    }
    
    /**
     * Busca en la base de datos y crea un
     * map<k, v> de las cajas activas
     *
     *
     * @return
     */
    public HashMap<String, Integer> getMapCajas(){
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, Integer> cajas = new HashMap<>();
        ResultSet rs;
        
        sqlQuery.append("SELECT id_caja, descripcion_caja FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("caja"))
                .append(" WHERE activo_caja = 1;");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            
            while (rs.next()) {
                cajas.put(rs.getString("descripcion_caja"), rs.getInt("id_caja"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            cajas = null;
        } finally {
            postgreSQL.desconectar();
        }
        System.out.println(cajas);
        return cajas;
    }

    /**
     * Utiliza el id de un cliente para buscar en la base de datos y crear un
     * map<k, v> de la tabla cliente
     *
     * @param tipo_persona
     * @param numero_identificacion_persona
     * @return
     */
    public HashMap<String, String> getMapPersona(String tipo_persona, String numero_identificacion_persona) {
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        ResultSet rs = null;
        String[] columnaCliente = new String[]{"id_persona", "nombre_persona", "apellido_persona", 
            "tipo_persona", "numero_identificacion_persona", "direccion_persona", "telefono_persona", 
            "email_persona"};
        sqlQuery.append("SELECT ");
        for (String columna : columnaCliente) {
            sqlQuery.append("c.").append(columna).append(",");
        }
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("persona")).append(" AS c ")
                .append(" WHERE c.tipo_persona='").append(tipo_persona).append("'")
                .append(" AND c.numero_identificacion_persona='").append(numero_identificacion_persona)
                .append("' AND activo_persona = 1")
                .append(";");

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                for (String columna : columnaCliente) {
                    map.put(columna, rs.getString(columna));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map = null;
        } finally {
            postgreSQL.desconectar();
        }
        System.out.println(map);
        return map;
    }

    /**
     * Utiliza el id de un cliente para buscar en la base de datos y crear un
     * map<k, v> de la tabla cliente
     *
     * @param idCliente
     * @return
     */
    public HashMap<String, String> getMapCliente(final int idCliente) {
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        ResultSet rs = null;
        String[] columnaCliente = new String[]{"id", "nombre", "apellido", "nacionalidad", "cedula", "correo", "facebook", "twitter"};

        sqlQuery.append("SELECT ");
        addColumnasAlQuery(columnaCliente, "c.", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("cliente")).append(" AS c ")
                .append(" WHERE c.id=").append(idCliente).append("")
                .append(" LIMIT 1;");

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                for (String columna : columnaCliente) {
                    map.put(columna, rs.getString(columna));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map = null;
        } finally {
            postgreSQL.desconectar();
        }
        return map;
    }

    /**
     * Utiliza el id de un empleado para buscar en la base de datos y crear un
     * map<k, v> de la tabla empleado
     *
     * @param id
     * @return
     */
    public HashMap<String, String> getMapEmpleado(int id) {
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        ResultSet rs;

        String[] columnaempleado = new String[]{"id_empleado", "clave", "nombre_persona", "apellido_persona", "tipo_persona", "numero_identificacion_persona", "email_persona", "telefono_persona"};
        String[] columnaCargo = new String[]{"id_cargo", "nombre_cargo"};

        sqlQuery.append("SELECT ");
        addColumnasAlQuery(columnaempleado, "", sqlQuery);
        addColumnasAlQuery(columnaCargo, "c.", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("empleado")).append(" AS e")
                .append(" INNER JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("persona")).append(" AS p")
                .append(" ON e.id_persona = p.id_persona")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("cargo"))
                .append(" AS c")
                .append(" ON e.id_cargo=c.id_cargo")
                .append(" WHERE id_empleado=").append(id)
                .append(";");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                for (String columna : columnaempleado) {
                    map.put(columna, rs.getString(columna));
                }
                for (String columna : columnaCargo) {
                    map.put(columna, rs.getString(columna));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map = null;
        } finally {
            postgreSQL.desconectar();
        }
        return map;
    }

    public HashMap<String, String> getMapUsuarioSistema(int idempleado) {
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        ResultSet rs;

        String[] columnaUsuarioSistema = new String[]{"usuario"};

        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnaUsuarioSistema, "u.", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("empleado")).append(" AS e")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("usuario")).append(" AS u")
                .append(" ON e.id=u.empleado_id")
                .append(" WHERE e.id=").append(idempleado)
                .append(" LIMIT 1;");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                for (String columna : columnaUsuarioSistema) {
                    map.put(columna, rs.getString(columna));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map = null;
        } finally {
            postgreSQL.desconectar();
        }
        return map;
    }

    /**
     * Utiliza el codigo de barras de un producto para 
     * crear un map<k, v> de la tabla producto, incluyendo
     * los datos de la primera unidad de inventario que se encuentre 
     * de dicho producto
     *
     * @param codigo_venta_producto
     * @return
     */
    public HashMap<String, String> getMapProducto(String codigo_venta_producto) {
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        ResultSet rs;
        String[] columnaProducto = {"producto.id_producto AS id_producto","descripcion_producto", "unidad_inventario.id AS id_unidad_inventario", "codigo_venta_producto", "precio_venta_publico", "limite_venta_persona", "balanza", "almacen_id", "lote_produccion.codigo AS codigo_lote", "seguimiento", "producto_pre_fabricado", "id_periodo_venta_producto"};
        sqlQuery.append("SELECT ");
        addColumnasAlQuery(columnaProducto, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" ON producto.id_producto = unidad_inventario.producto_id")
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("precio_unidad_inventario"))
                .append(" ON precio_unidad_inventario.unidad_inventario_id = unidad_inventario.id")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("lote_produccion"))
                .append(" ON unidad_inventario.lote_produccion_id = lote_produccion.id")
                .append(" WHERE ")
                .append("codigo_venta_producto='").append(codigo_venta_producto).append("'")
                .append(" AND activo_producto = 1 AND unidad_inventario.activo = 1 AND precio_unidad_inventario.activo = 1 LIMIT 1;");
       
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                for (String columna : columnaProducto) {
                    
                    map.put(columna, rs.getString(columna));
                }System.out.println(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map = null;
        } finally {
            postgreSQL.desconectar();
        }
        return map;
    }
    
    /**
     * Utiliza un id de unidad de inventario para 
     * crear un map<k, v> con los datos necesarios del producto para la venta
     *
     * @param id_unidad_inventario
     * @return
     */
    public HashMap<String, String> getMapProducto(int id_unidad_inventario) {
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        ResultSet rs;
        String[] columnaProducto = {"producto.id_producto AS id_producto","descripcion_producto", "unidad_inventario.id AS id_unidad_inventario", "codigo_venta_producto", "precio_venta_publico", "limite_venta_persona", "balanza", "almacen_id", "lote_produccion.codigo AS codigo_lote", "seguimiento", "producto_pre_fabricado", "id_periodo_venta_producto"};
        sqlQuery.append("SELECT ");
        addColumnasAlQuery(columnaProducto, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" ON producto.id_producto = unidad_inventario.producto_id")
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("precio_unidad_inventario"))
                .append(" ON precio_unidad_inventario.unidad_inventario_id = unidad_inventario.id")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("lote_produccion"))
                .append(" ON unidad_inventario.lote_produccion_id = lote_produccion.id")
                .append(" WHERE ")
                .append(" unidad_inventario.id =").append(id_unidad_inventario)
                .append(" AND activo_producto = 1 AND unidad_inventario.activo = 1 AND precio_unidad_inventario.activo = 1 LIMIT 1;");
       
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                for (String columna : columnaProducto) {
                    
                    map.put(columna, rs.getString(columna));
                }System.out.println(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map = null;
        } finally {
            postgreSQL.desconectar();
        }
        return map;
    }

    /**
     * Método para obtener el total de venta de un EstadoCaja basado en la
     * sumatoria de todos los montos asociados a pagos de alguna venta
     * (facturas).
     *
     * @param idEstadoCaja
     * @return XBigDecimal con el total.
     */
    public XBigDecimal getTotalEstadoCajaPorPagoCliente(int idEstadoCaja) {
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        XBigDecimal resultado;

        sqlQuery.append("SELECT SUM(p.monto) as total");

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("venta")).append(" AS v")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("estado_venta")).append(" AS ev")
                .append(" ON v.estado_venta_id=ev.id")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("venta_pago")).append(" AS vp")
                .append(" ON v.id=vp.venta_id")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("pago")).append(" AS p")
                .append(" ON vp.pago_id=p.id")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("estado_caja")).append(" AS ec")
                .append(" ON v.estado_caja_id=ec.id")
                .append(" WHERE v.estado_caja_id=").append(idEstadoCaja)
                .append(" AND ev.descripcion='finalizada';");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                resultado = new XBigDecimal(rs.getString("total"));
            } else {
                resultado = new XBigDecimal(0);
            }
        } catch (Exception e) {
            resultado = new XBigDecimal(0);
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    /**
     * Método para obtener el total de venta de un EstadoCaja basado en la
     * sumatoria del pvp de todos los productos vendidos. Si la caja no tiene
     * ventas, regresa 0.
     *
     * NOTA. Este query ha sido cambiado por getTotalEstadoCaja, ya que al
     * cambiar el pvp de algun producto no iba a cuadrar la caja en el momento
     * de cierre.
     *
     * @param idEstadoCaja
     * @return XBigDecimal con el valor del total de la venta.
     */
    public XBigDecimal getTotalEstadoCajaPorProductoVendido(int idEstadoCaja) {
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        XBigDecimal resultado;

        sqlQuery.append("SELECT SUM(p.pvp*vp.cantidad_producto) as total");

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("venta")).append(" AS v")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("estado_venta")).append(" AS ev")
                .append(" ON v.estado_venta_id=ev.id")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("venta__producto")).append(" AS vp")
                .append(" ON v.id=vp.venta_id")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("producto")).append(" AS p")
                .append(" ON p.id=vp.producto_id")
                .append(" WHERE v.estado_caja_id=").append(idEstadoCaja)
                .append(" AND ev.descripcion='finalizada';");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                resultado = new XBigDecimal(rs.getString("total"));
            } else {
                resultado = new XBigDecimal(0);
            }
        } catch (Exception e) {
            resultado = new XBigDecimal(0);
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    /**
     * Método para obtener el total de venta de un EstadoCaja basado en el valor
     * de la columna total en la tabla venta. Si la caja no tiene ventas,
     * regresa 0.
     *
     * NOTA. Este query se ha creado en reparalazo de
     * getTotalEstadoCajaPorProductoVendido;
     *
     * @param idEstadoCaja
     * @param pagos_por_corte filtra los resultados que se obtienen por corte realizado
     * @return XBigDecimal con el valor del total de la venta.
     */
    public XBigDecimal[] getTotalEstadoCaja(int idEstadoCaja, String pagos_por_corte) {
        ResultSet rs;
        XBigDecimal[] resultado = new XBigDecimal[5];
        XBigDecimal[] acum = new XBigDecimal[5];
//        String sql = "SELECT v.total AS total FROM stpv.venta AS v LEFT JOIN stpv.estado_venta AS ev "
//                + "ON v.estado_venta_id=ev.id WHERE v.estado_caja_id=" + idEstadoCaja + " AND v.corte_caja is null";
        String sql1;

        for(int i = 0; i < 5; i++) {
            if(i < 4) {
                sql1 = "SELECT sum(monto_pago) FROM "
                    + "(spve.pago AS p INNER JOIN spve.venta AS v "
                    + "ON p.id_venta = v.id_venta "
                    + "INNER JOIN spve.estado_caja AS ec "
                    + "ON v.id_estado_caja = ec.id_estado_caja) "
                    + "WHERE p.id_tipo_pago = '"+(i+1)+"' AND ec.id_estado_caja = '"+idEstadoCaja+"'"+pagos_por_corte;
            } else {
                sql1 = "SELECT sum(monto_pago) FROM "
                    + "(spve.pago AS p INNER JOIN spve.venta AS v "
                    + "ON p.id_venta = v.id_venta "
                    + " INNER JOIN spve.estado_caja AS ec "
                    + "ON v.id_estado_caja = ec.id_estado_caja) "
                    + "WHERE ec.id_estado_caja = '"+idEstadoCaja+"'"+pagos_por_corte;
            }
            
            try {
                postgreSQL.conectar();
                rs = postgreSQL.ejecutarSelect(sql1);
                while (rs.next()) {
                    acum[i] = new XBigDecimal(rs.getDouble("sum"));
                    resultado[i] = new XBigDecimal(acum[i].negate().toString());
                }

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                postgreSQL.desconectar();
            }
//            resultado[i] = new XBigDecimal(resultado[i].toString().replaceAll("-", ""));
        }

//        System.out.println("REsultado valor xbd" + resultado);
        return resultado;
    }
    
    // Verifica en la tabla pago si hay algún registro sin corte realizado
    public boolean verificarCorteEnPagos() {
        PostgreSQL d = new PostgreSQL();
        boolean resultado = false;
        try{
            d.buscar("SELECT EXISTS (SELECT id_pago from spve.pago WHERE corte_realizado = 0)");
            while(d.rs.next()) {
                resultado = d.rs.getBoolean("exists");
            }
        }catch(Exception e){}
        return resultado;
    }
    
    public XBigDecimal getTotalEstadoCajaCierre(int idEstadoCaja) {
        ResultSet rs;
        XBigDecimal resultado = new XBigDecimal(0);
        XBigDecimal acum;
        String sql = "SELECT v.total AS total FROM stpv.venta AS v LEFT JOIN stpv.estado_venta AS ev ON v.estado_venta_id=ev.id WHERE v.estado_caja_id=" + idEstadoCaja + " AND v.cierre_caja is null";

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sql);

            while (rs.next()) {
                acum = new XBigDecimal(rs.getString("total"));
                resultado = new XBigDecimal(resultado.add(acum.negate()).toString());
//                System.out.println("Valor del XBIGDECIMAL:" + resultado);
            }
//                resultado = new XBigDecimal(0);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            postgreSQL.desconectar();
        }
//        resultado = new XBigDecimal(resultado.toString().replaceAll("-", ""));
//        System.out.println("Resultado valor xbd" + resultado);
        return resultado;
    }

    public List<ValorPagos> getTotalPagoCierre(int idEstadoCaja) {
        ResultSet rs;
        List<ValorPagos> resultado = new ArrayList();
        double ef = 0;
        double tdd = 0;
        double tdc = 0;
        double ctk = 0;
        //Ernesto:
        //Anotacion: no esta especificado el alias para "p" y "v" en el siguiente Query
        String sql = "SELECT p.tipopago,p.monto FROM stpv.pago p INNER JOIN stpv.venta v ON p.id_venta=v.id "
                + "LEFT JOIN stpv.estado_venta AS ev ON v.estado_venta_id=ev.id WHERE v.estado_caja_id=" + idEstadoCaja + " AND v.cierre_caja is null";

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sql);

            while (rs.next()) {
                if ("Efectivo".equals(rs.getString("tipopago"))) {
                    ef = ef + Double.parseDouble(rs.getString("monto"));
                }
                if ("Debito".equals(rs.getString("tipopago"))) {
                    tdd = tdd + Double.parseDouble(rs.getString("monto"));
                }
                if ("Credito".equals(rs.getString("tipopago"))) {
                    tdc = tdc + Double.parseDouble(rs.getString("monto"));
                }
                if ("Cestaticket".equals(rs.getString("tipopago"))) {
                    ctk = ctk + Double.parseDouble(rs.getString("monto"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            postgreSQL.desconectar();
        }
        resultado.add(new ValorPagos(1, ef));
        resultado.add(new ValorPagos(2, tdd));
        resultado.add(new ValorPagos(3, tdc));
        resultado.add(new ValorPagos(4, ctk));
        return resultado;
    }

    public List<ValorPagos> montoscorte(int idCorteCaja) {
        ResultSet rs;
        List<ValorPagos> resultado = new ArrayList();
        List<ValorPagos> valores = new ArrayList();
        double ef = 0;
        double tdd = 0;
        double tdc = 0;
        double ctk = 0;
            
            String sql = "SELECT monto_desglose_caja, id_tipo_pago FROM spve.desglose_caja "
                       + "WHERE id_corte_caja = '"+idCorteCaja+"'";
            try {
                postgreSQL.conectar();
                rs = postgreSQL.ejecutarSelect(sql);
                while (rs.next()) {
                    valores.add(new ValorPagos(rs.getInt("id_tipo_pago"), rs.getDouble("monto_desglose_caja")));
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                postgreSQL.desconectar();
            }
        
        
        for (ValorPagos valor : valores) {
            if (1 == valor.getTipo()) {
                ef = ef + valor.getMontoD();
            }
            if (2 == valor.getTipo()) {
                tdd = tdd + valor.getMontoD();
            }
            if (3 == valor.getTipo()) {
                tdc = tdc + valor.getMontoD();
            }
            if (4 == valor.getTipo()) {
                ctk = ctk + valor.getMontoD();          
            }
        }
        resultado.add(new ValorPagos(1, ef));
        resultado.add(new ValorPagos(2, tdd));
        resultado.add(new ValorPagos(3, tdc));
        resultado.add(new ValorPagos(4, ctk));
         
        return resultado;
    }

    // Obtiene los datos de los cortes realizados
    // para mostrarlos en el cierre de caja
    public XBigDecimal[] getTotalCortesCierre(int idEstadoCaja) {
        ResultSet rs;
        XBigDecimal[] resultado = new XBigDecimal[5];

        String sql;
        
        for(int i = 0; i < 5; i++) {
            if(i < 4) {
                sql = "SELECT sum(monto_desglose_caja) FROM spve.desglose_caja "
                    + "INNER JOIN spve.corte_caja ON desglose_caja.id_corte_caja = corte_caja.id_corte_caja "
                    + "INNER JOIN spve.estado_caja ON corte_caja.id_estado_caja = estado_caja.id_estado_caja "
                    + "WHERE corte_caja.id_estado_caja = '"+idEstadoCaja+"' AND desglose_caja.id_tipo_pago = '"+(i+1)+"'";
            } else {
                sql = "SELECT sum(monto_desglose_caja) FROM spve.desglose_caja "
                    + "INNER JOIN spve.corte_caja ON desglose_caja.id_corte_caja = corte_caja.id_corte_caja "
                    + "INNER JOIN spve.estado_caja ON corte_caja.id_estado_caja = estado_caja.id_estado_caja "
                    + "WHERE corte_caja.id_estado_caja = '"+idEstadoCaja+"'";
            }
            
            try {
                postgreSQL.conectar();
                rs = postgreSQL.ejecutarSelect(sql);
                while (rs.next()) {
                    resultado[i] = new XBigDecimal(rs.getDouble("sum"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }
    
    public XBigDecimal getExcedenteCortesCaja(int idEstadoCaja) {
        PostgreSQL d = new PostgreSQL();
        XBigDecimal resultado = new XBigDecimal(0); 
        try{
            d.buscar("SELECT sum(excedente_caja) FROM spve.corte_caja "
                    + "WHERE id_estado_caja = '"+idEstadoCaja+"'");
            while(d.rs.next()) {
                resultado = new XBigDecimal(d.rs.getString("sum"));
            }
        }catch(Exception e){}
        return resultado;
    }
    
    public XBigDecimal getRestanteCortesCaja(int idEstadoCaja) {
        PostgreSQL d = new PostgreSQL();
        XBigDecimal resultado = new XBigDecimal(0); 
        try{
            d.buscar("SELECT sum(restante_caja) FROM spve.corte_caja "
                    + "WHERE id_estado_caja = '"+idEstadoCaja+"'");
            while(d.rs.next()) {
                resultado = new XBigDecimal(d.rs.getString("sum"));
            }
        }catch(Exception e){}
        return resultado;
    }

    public List<Integer> getListIDVentas(int idEstadoCaja) {
        ResultSet rs;
        List<Integer> resultado = new ArrayList();
//        String sql = "SELECT v.id FROM stpv.venta AS v LEFT JOIN stpv.estado_venta AS ev ON v.estado_venta_id=ev.id WHERE v.estado_caja_id=" + idEstadoCaja + " AND v.corte_caja is null";
        String sql = "SELECT id_venta FROM spve.venta "
                   + "WHERE id_estado_caja = '" + idEstadoCaja + "'";
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sql);
            while (rs.next()) {
                resultado.add(rs.getInt("id_venta"));
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    /**
     * Obtiene el limite de cantidad maxima de un producto.
     *
     * @param codigoBarra
     * @return XBigDecimal con el limite maximo del producto.
     */
    public int getLimiteMaximoProducto(String codigoBarra) {
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        int resultado = -1;

        sqlQuery.append("SELECT periodo_venta_producto.id_periodo_venta_producto FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("periodo_venta_producto"))
                .append(" INNER JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" ON periodo_venta_producto.id_periodo_venta_producto = producto.id_periodo_venta_producto")
                .append(" WHERE codigo_venta_producto = '").append(codigoBarra).append("'");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                resultado = rs.getInt(1);
            }
        } catch (Exception e) {
            resultado = -1;
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    /**
     * Método para obtener el total de dinero sacado por cortes de caja.
     *
     * @param idEstadoCaja
     * @return XBigDecimal con el total.
     */
    public XBigDecimal getTotalCorteCaja(int idEstadoCaja) {
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        XBigDecimal resultado;
        String sql = "selec ";
        sqlQuery.append("SELECT SUM(cc.monto_corte) AS total");

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("corte_caja")).append(" AS cc")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("estado_caja")).append(" AS ec")
                .append(" ON cc.id_estado_caja=ec.id_estado_caja")
                .append(" WHERE ec.id_estado_caja=").append(idEstadoCaja).append(";");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                resultado = new XBigDecimal(rs.getString("total"));
            } else {
                resultado = new XBigDecimal(0);
            }
        } catch (Exception e) {
            resultado = new XBigDecimal(0);
        } finally {
            postgreSQL.desconectar();
        }
//        System.out.println("Total corte bddd: " + resultado);
        return resultado;
    }
    
    

    /**
     * Inserta un cliente en la tabla stpv.cliente.
     *
     * 
     * @param nombre_persona
     * @param apellido_persona
     * @param tipo_persona
     * @param numero_identificacion_persona
     * @param direccion_persona
     * @param email_persona
     * @param telefono_persona
     * @return id del cliente resultado del INSERT o -1 en caso de fallar.
     */
    public int crearCliente(String nombre_persona, String apellido_persona, String tipo_persona, String numero_identificacion_persona, String direccion_persona, String telefono_persona, String email_persona) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        
        crearPersona(nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, telefono_persona, email_persona, direccion_persona, false);

        resultado = ejecutarCreate(sqlQuery.toString(), "persona");

        return resultado;
    }

    
    //Este metodo ya no es necesario, la tabla "telefono" fue eliminada del nuevo modelo de BBDD
    /*public int[] crearTelefonosCliente(int idCliente, List<String> listaTelefonos) {
        StringBuilder sqlQuery = new StringBuilder();
        int[] resultado = new int[listaTelefonos.size()];

        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("telefono"))
                .append("(numero) VALUES ");

        for (String numero : listaTelefonos) {
            sqlQuery.append("('").append(numero).append("'),");
        }
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        int cantidadTelefonos = ejecutarCreate(sqlQuery.toString());
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = cantidadTelefonos + i;
            sqlQuery = new StringBuilder();
            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("stpv")).append(".")
                    .append(mapTabla.get("cliente__telefono"))
                    .append("(cliente_id,telefono_id) VALUES ")
                    .append("('").append(idCliente).append("',")
                    .append("'").append(resultado[i]).append("');");
            int idTabla = ejecutarCreate(sqlQuery.toString());
        }
        return resultado;
    }*/

    /**
     * Crea una venta asociada a una persona según su id 
     * si no tiene ventas en proceso o pausadas, en caso contrario
     * se obtiene el id de su última venta no finalizada y se actualiza 
     * con el nuevo estado de venta
     * 
     *
     * @param idPersona
     * @param idEstadoCaja
     * @param idEstadoVenta
     * @return id de la última venta al cliente o -1 si no se pudo crear ni actualizar
     */
    public int crearVenta(int idPersona, int idEstadoCaja, int idEstadoVenta) {
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        int idVenta = -1;
        boolean ventaActualizada = false;

        sqlQuery.append("SELECT id_venta, estado_venta FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" WHERE id_persona = ")
                .append(idPersona)
                .append(" AND estado_venta IN (").append(EstadoVenta.EnProceso)
                .append(",").append(EstadoVenta.Pausada)
                .append(") AND activo_venta = 1;");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                idVenta = rs.getInt("id_venta");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }

        sqlQuery = new StringBuilder();
        
        LocalDateTime fechaActual = LocalDateTime.now();
        
        if (idVenta > -1) {
            sqlQuery.append("UPDATE ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("venta"))
                    .append(" SET estado_venta=").append(idEstadoVenta)
                    .append(", fecha_ultima_modificacion='")
                    .append(fechaActual)
                    .append("' WHERE id_venta=").append(idVenta)
                    .append(";");
            
            try {
                postgreSQL.conectar();
                ventaActualizada = ejecutarQuerySinResultado(sqlQuery.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                postgreSQL.desconectar();
            }
            
            if(!ventaActualizada) resultado = -1;
            else resultado = idVenta;
        } else {
            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("venta"))
                    .append("(id_persona, estado_venta, fecha_venta, fecha_ultima_modificacion, id_estado_caja) VALUES (")
                    .append(idPersona).append(", ")
                    .append(EstadoVenta.EnProceso).append(", ")
                    .append("'").append(fechaActual)
                    .append("', '").append(fechaActual)
                    .append("', ")
                    .append(idEstadoCaja)
                    .append(");");
            
            resultado = ejecutarCreate(sqlQuery.toString(), "venta");
        }
        
        return resultado;
    }

    /**
     * Obtiene el código y fecha de una factura según un id de venta
     * 
     * @param idVenta
     * @return 
     */
    public HashMap<String, String> getMapVenta(int idVenta) {
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String codigoFactura = "";
        int codigo_factura;
        
        HashMap<String, String> venta = new HashMap();
        
        sqlQuery.append("SELECT codigo_factura, fecha_venta FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" WHERE id_venta=").append(idVenta)
                .append(";");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            
            if (rs.next()) {
                venta.put("fecha_venta", rs.getString("fecha_venta"));
                codigo_factura = rs.getInt("codigo_factura");
                codigoFactura += codigo_factura;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        
        while (codigoFactura.length() < 10) {
            codigoFactura = "0" + codigoFactura;
        }
        
        venta.put("codigo_factura", codigoFactura);
        
        return venta;
    }

    
    /**
     * Realiza un UPDATE a la tabla producto-
     *
     * @param codigo
     * @param cantidad
     * @return
     * @throws java.sql.SQLException
     *//*
    public int descontarCantidad(String codigo, double cantidad) throws SQLException {

        ResultSet rs;
        int resultado = -1;
        double can = Double.parseDouble((String) PuntoVenta.Ventanas.Venta.getTxtCantidad().getText());

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("UPDATE ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" SET cantidad_disponible = " + cantidad + " - ")
                .append(can)
                .append(" WHERE codigo_venta_producto = '").append(codigo).append("'")
                .append(";");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getInt("codigo_venta_producto");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
//        System.out.println(can + "cantidad2");
        //PreparedStatement us1 = con.conexion().prepareStatement(sql1);
        //int res1 = us1.executeUpdate();
//        System.out.println(sqlQuery);
        return resultado;
    }*/

    /**
     * Realiza un UPDATE a la tabla producto-
     *
     * @param codigo
     * @param cantidad
     * @return
     * @throws java.sql.SQLException
     */
    //Ernesto:
    //Este metodo es igual al anterior con la diferencia que este Query no paralea
    //la palabra reservada SUM para sumar, solo UPDATE
    public int contarCantidad(String codigo, double cantidad) throws SQLException {
        ResultSet rs;
        int resultado = -1;
        double can = PuntoVenta.Ventanas.Venta.jtbVenta.getModel().getRowCount();

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("UPDATE ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" SET cantidad_disponible =" + cantidad + " + ")
                .append(can)
                .append(" WHERE codigo_venta_producto = '").append(codigo).append("'")
                .append(";");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getInt("codigo_venta_producto");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
//        System.out.println(can + "cantidad2");
        //PreparedStatement us1 = con.conexion().prepareStatement(sql1);
        //int res1 = us1.executeUpdate();
//        System.out.println(sqlQuery);
        return resultado;
    }

    /**
     * Método para crear un corte de caja dado un id y un monto.
     *
     * @param idCorteCaja
     * @param fechaCorte
     * @param idEstadoCaja EstadoCaja a la que se aplicará el corte.
     * @param monto Monto total del corte
     * @param excedente
     * @param restante
     * @param idempleado
     * @return
     */
    public int crearCorteCaja(double monto, double excedente, double restante, int idEstadoCaja, int idempleado) {
        Date date = new java.util.Date();
        StringBuilder sqlQuery = new StringBuilder();
        PostgreSQL d = new PostgreSQL();
        int resultado;
        
        // inserta los datos del corte en la tabla corte_caja
        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("corte_caja"))
                .append("(fecha_corte, monto_corte, excedente_caja, restante_caja, id_estado_caja, id_empleado) VALUES (")
                //.append(idCorteCaja).append(", ")
                .append("'").append(new Timestamp(date.getTime())).append("', ")
                .append(monto)/*.setScale(2, RoundingMode.UNNECESSARY).toString())*/.append(", ")
                .append(excedente)/*.setScale(2, RoundingMode.UNNECESSARY).toString())*/.append(", ")
                .append(restante)/*.setScale(2, RoundingMode.UNNECESSARY).toString())*/.append(", ")
                .append(idEstadoCaja).append(", ")
                .append(idempleado)
                .append(");");
        
        // actualiza la tabla de pago para no tomar en cuenta esos pagos en los próximos cortes
        d.ejecutar("UPDATE spve.pago SET corte_realizado = 1 WHERE corte_realizado = 0"); 

        resultado = ejecutarCreate(sqlQuery.toString(), "corte_caja");
        return resultado;
    }
    
    // inserta en la tabla pago_corte que define el tipo de pago
    // en cada corte.
    public void insertarTipoPagoPorCorte(int idCorteCaja, int tipoPago, XBigDecimal monto) {
        PostgreSQL d = new PostgreSQL();


        
        d.ejecutar("INSERT INTO spve.pago_corte (id_tipo_pago, monto, id_corte_caja) VALUES ()");
    }
    
    /**
     * Método para crear un desglose de un corte de caja dado un id y un monto.
     *
     * @param idCorteCaja
     * @param tipo
     * @param monto
     * @return
     */
    public int crearDesgloseCaja(int idCorteCaja, String tipo, XBigDecimal monto) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        int tipoInt = 0;
        
        switch(tipo) {
            case "Efectivo": tipoInt = 1; break;
            case "Débito": tipoInt = 2; break;
            case "Crédito": tipoInt = 3; break;
            case "CestaTicket": tipoInt = 4; break;
        }
        
        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("desglose_caja"))
                .append("(id_corte_caja, monto_desglose_caja,id_tipo_pago) VALUES (")
                .append(idCorteCaja).append(", ")
                .append(monto).append(", '")
                .append(tipoInt).append("');");
        resultado = ejecutarCreate(sqlQuery.toString(), "desglose_caja");
  
        return resultado;
    }

    public void guardarMontosCierreCaja(int idCierreCaja, String[][] montos) {
        PostgreSQL d = new PostgreSQL();
        
        for(int i=0; i < 3; i++) {
            for(int j=0; j < 4; j++) {

                String sql = "INSERT INTO spve.montos_cierre_caja (monto, id_tipo_pago, id_tipo_monto_cierre, id_cierre_caja) "
                        + "VALUES ('"+montos[i][j]+"', '"+(j+1)+"', '"+(i+1)+"', '"+idCierreCaja+"')";
                d.ejecutar(sql);
//                try {
//                    postgreSQL.conectar();
//                    postgreSQL.ejecutarSelect(sql);
//                } catch (Exception e) {
//                } finally {
//                    postgreSQL.desconectar();
//                }
            }
        }
    }

    public void ActualizarCierreEnEstadoCaja(int idEstadoCaja) {
        PostgreSQL d = new PostgreSQL();
        String sql = "UPDATE spve.estado_caja SET caja_abierta = 0 WHERE id_estado_caja=" + idEstadoCaja;
        d.ejecutar(sql);
//        try {
//            postgreSQL.conectar();
//            postgreSQL.ejecutarSelect(sql);
//        } catch (Exception e) {
//        } finally {
//            postgreSQL.desconectar();
//        }
    }

    public String montoInicial(int idEstadoCaja) {
        ResultSet rs;
        String monto = "";
        //Ernesto:
        //Agregar alias para "ec"
        String sql = "SELECT ec.monto_apertura FROM spve.estado_caja ec WHERE ec.id_estado_caja=" + idEstadoCaja;
//        System.out.println(sql);
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sql);
            while (rs.next()) {
                monto = rs.getString("monto_apertura");
            }
        } catch (Exception e) {
        } finally {
            postgreSQL.desconectar();
        }
        return monto;
    }

    /**
     * Método para crear un desglose de un cierre de caja dado un id y un monto.
     *
     * @param idCaja
     * @param idMoneda
     * @param cantidadMoneda
     * @param monto
     * @return
     */
    public int crearCierreCaja(int idCierre, int idCaja) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;

        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("cierre_caja"))
                .append("(id_cierre_caja, id_corte_caja) VALUES (")
                .append(idCierre).append(", ")
                .append(idCaja).append("); ");
                //.append(idMoneda).append(", ")
                //.append(cantidadMoneda).append(", ")
                //.append(monto).append(");");
        resultado = ejecutarCreate(sqlQuery.toString(), "cierre_caja");
       
        return resultado;
    }

    /**
     * Registra el pago de una venta en la base de datos
     *
     * @param monto
     * @param idTipoPago
     * @param idVenta
     * @return El id del pago creado.
     */
    public int crearPago(double monto, int idTipoPago, int idVenta) {
        Date date = new java.util.Date();
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;

        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("pago"))
                .append(" (monto_pago, fecha_pago, id_tipo_pago, id_venta) VALUES (")
                .append(monto).append(", '")
                .append(new Timestamp(date.getTime())).append("', ")
                .append(idTipoPago).append(", ")
                .append(idVenta).append(");");
                
        resultado = ejecutarCreate(sqlQuery.toString(), "pago");
        
        return resultado;
    }

    
    /**
     * Método para calcular el total pagado de una venta
     * @param id_venta
     * @return 
     */
    public double getTotalPagadoVenta(int id_venta){
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        double resultado = 0;
        
        sqlQuery.append("SELECT SUM(monto_pago) FROM spve.pago WHERE id_venta ="+id_venta+";");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sqlQuery.toString());
            while (rs.next()) {
                resultado = rs.getDouble("sum");
                System.out.println(resultado);
            }
        } catch (Exception e) {
        } finally {
            postgreSQL.desconectar();
        }
        
        return resultado;
    }
    
    /**
     * Método para obtener el total exento de una venta
     * 
     * @param id_venta
     * @return
     */
    public double getTotalExentoVenta(int id_venta){
        StringBuilder sqlQuery = new StringBuilder();
        double resultado = 0;
        ResultSet rs;
        
        sqlQuery.append("SELECT SUM(precio_venta_publico*cantidad_producto) AS total_exento FROM spve.precio_producto AS pp\n" +
                        "INNER JOIN spve.producto AS p ON p.id_precio_producto = pp.id_precio_producto\n" +
                        "INNER JOIN spve.venta_producto AS vp ON vp.id_producto = p.id_producto\n" +
                        "WHERE producto_exento = 1 AND id_venta = "+id_venta+";");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getDouble("total_exento");
                
            }
        } catch (Exception e) {
        } finally {
            postgreSQL.desconectar();
        }
        
        return resultado;
    }
    
    /**
     * Método para obtener el total no exento de
     * una venta
     * 
     * @param id_venta
     * @return
     */
    public double getTotalNoExentoVenta(int id_venta){
        StringBuilder sqlQuery = new StringBuilder();
        double resultado = 0;
        ResultSet rs;
        
        sqlQuery.append("SELECT SUM(precio_venta_publico*cantidad_producto) AS total_no_exento FROM spve.precio_producto AS pp\n" +
                        "INNER JOIN spve.producto AS p ON p.id_precio_producto = pp.id_precio_producto\n" +
                        "INNER JOIN spve.venta_producto AS vp ON vp.id_producto = p.id_producto\n" +
                        "WHERE producto_exento = 0 AND id_venta = "+id_venta+";");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getDouble("total_no_exento");
                
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            postgreSQL.desconectar();
        }
        
        return resultado;
    }
    
    /**
     * Método para obtener la base imponible total
     * de una venta
     * 
     * @param id_venta
     * @return
     */
    public double getTotalBaseImponibleVenta(int id_venta){
        StringBuilder sqlQuery = new StringBuilder();
        double resultado = 0;
        ResultSet rs;
        
        sqlQuery.append("SELECT SUM(base_imponible*cantidad_producto) AS total_base_imponible FROM spve.precio_producto AS pp\n" +
                        "INNER JOIN spve.producto AS p ON p.id_producto = pp.id_producto\n" +
                        "INNER JOIN spve.venta_producto AS vp ON vp.id_producto = p.id_producto\n" +
                        "WHERE id_venta = "+id_venta+";");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getDouble("total_base_imponible");
                
            }
        } catch (Exception e) {
        } finally {
            postgreSQL.desconectar();
        }
        
        return resultado;
    }
    
    /**
     * Método para obtener el impuesto total de una venta
     * 
     * @param id_venta
     * @return
     */
    public double getTotalImpuestoVenta(int id_venta){
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        double resultado = 0;
        
        sqlQuery.append("SELECT COALESCE(SUM(impuesto*venta_producto.cantidad_producto), 0) AS total_impuesto")
                .append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta_producto"))
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" ON venta_producto.id_unidad_inventario = unidad_inventario.id")
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("precio_unidad_inventario"))
                .append(" ON unidad_inventario.id = precio_unidad_inventario.unidad_inventario_id ")
                .append(" WHERE exento = FALSE AND activo_venta_producto = 1 AND precio_unidad_inventario.activo = 1 AND id_venta = ")
                .append(id_venta).append(";");
                        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getDouble("total_impuesto");
                
            }
        } catch (Exception e) {
        } finally {
            postgreSQL.desconectar();
        }
        
        return resultado;
    }
    
    public XBigDecimal getValorDivisa() {
            
        String query = "SELECT DISTINCT ON (id_estado_caja)\n" +
                        "	monto_divisa\n" +
                        "FROM spve.estado_caja\n" +
                        "ORDER BY id_estado_caja DESC LIMIT 1;";
                    
        try {
            postgreSQL.conectar();
            ResultSet rs = postgreSQL.getSentencia().executeQuery(query);
            if (rs.next()) {
                double r = rs.getDouble(1);
                return new XBigDecimal(r);
            }
            System.out.println("No se encontro ningun registro");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            postgreSQL.desconectar();
        }
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Método para obtener el subtotal de una venta
     * 
     * @param id_venta
     * @return
     */
    public double getSubtotalVenta(int id_venta){
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        double resultado = 0;
        
        sqlQuery.append("SELECT (COALESCE(SUM(precio_venta_publico*venta_producto.cantidad_producto),0)-COALESCE(SUM(impuesto*venta_producto.cantidad_producto), 0)) AS subtotal")
                .append(" ")
                .append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta_producto"))
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" ON venta_producto.id_unidad_inventario = unidad_inventario.id")
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("precio_unidad_inventario"))
                .append(" ON unidad_inventario.id = precio_unidad_inventario.unidad_inventario_id ")
                .append(" WHERE activo_venta_producto = 1 AND precio_unidad_inventario.activo = 1 AND id_venta = ")
                .append(id_venta).append(";");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getDouble("subtotal");
                System.out.println("resultado "+resultado);
                
            }
        } catch (Exception e) {
        } finally {
            postgreSQL.desconectar();
        }
        
        return resultado;
    }
    
    /*
     * 
     * Método para asociar un pago a una venta.
     *
     * @param idPago
     * @param idVenta
     * @return
     */
    /*public int asociarPagoVenta(int idPago, int idVenta) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;

        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                //Ernesto:
                //Doble "__" en nombre de tabla "venta__pago"
                //esto se repite en los siguientes metodos donde se conecta 
                //con esta tabla
                .append(mapTabla.get("venta_producto"))
                .append("(id_pago, id_venta) VALUES (")
                .append(idPago).append(", ")
                .append(idVenta).append(");");
        resultado = ejecutarCreate(sqlQuery.toString(), "venta_producto");
        return resultado;
    }*/

    
    /*
     * Método para crear un
     *
     * @param idVenta
     * @param idProducto
     * @param cantidadProducto
     * @deprecated
     * @return
    */
    /*public int incluirProductoEnVenta(int idVenta, int idProducto, int cantidadProducto) {
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        int cantidadAnterior = 0;
        int resultado = -1;

        sqlQuery.append("SELECT id_venta_producto, cantidad_producto AS cantidad FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta_producto"))
                .append(" WHERE id_venta=").append(idVenta)
                .append(" AND id_producto=").append(idProducto).append(";");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getInt("id");
                cantidadAnterior = rs.getInt("cantidad");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }

        sqlQuery = new StringBuilder();
        if (resultado != -1) {
            sqlQuery.append("UPDATE ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("venta_producto"))
                    .append(" SET cantidad_producto=").append(cantidadProducto + cantidadAnterior)
                    .append(" WHERE id=").append(resultado)
                    .append(";");
        } else {
            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("stpv")).append(".")
                    .append(mapTabla.get("venta__producto"))
                    .append("(producto_id, venta_id, cantidad_producto) VALUES (")
                    .append(idProducto).append(", ")
                    .append(idVenta).append(", ")
                    .append(cantidadProducto).append(");");
            resultado = ejecutarCreate(sqlQuery.toString(),"venta_producto");
        
    }
        return resultado;
    }*/

    /**
     * Método para incluir un producto en una venta. Si existe un producto del
     * mismo idUnidadInventario en la venta, se hace un UPDATE de la tabla. En caso
     * contrario se hace un INSERT
     *
     * @param idVenta
     * @param idUnidadInventario
     * @param cantidadProducto
     * @return
     */
    public int agregarProductoEnVenta(int idVenta, int idUnidadInventario, double cantidadProducto) {
        StringBuilder sqlQuery = new StringBuilder();
        
        LocalDateTime fechaActual = LocalDateTime.now();
        
        sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("venta_producto"))
                    .append(" (id_unidad_inventario, id_venta, cantidad_producto, fecha_ultima_modificacion) VALUES (")
                    .append(idUnidadInventario).append(", ")
                    .append(idVenta).append(", ")
                    .append(cantidadProducto).append(", '")
                    .append(fechaActual)
                    .append("')")
                    .append(" ON CONFLICT ON CONSTRAINT venta_unidad_inventario DO UPDATE")
                    .append(" SET cantidad_producto = venta_producto.cantidad_producto+EXCLUDED.cantidad_producto, fecha_ultima_modificacion=EXCLUDED.fecha_ultima_modificacion;");
        
        return ejecutarCreate(sqlQuery.toString(), "venta_producto");
    }
        
    /**
     * Método para obtener la última fecha en la que una persona compró un 
     * producto dado
     * 
     * @param tipo_persona
     * @param numero_identificacion_persona
     * @param codigo_venta_producto
     * @return 
     */
    public Date getUltimaFechaVentaProducto(char tipo_persona, String numero_identificacion_persona, String codigo_venta_producto){
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        Date resultado = null;
        
        sqlQuery.append("SELECT MAX(fecha_venta)")
                .append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" INNER JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta_producto"))
                .append(" ON venta.id_venta = venta_producto.id_venta")
                .append(" INNER JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("persona"))
                .append(" ON venta.id_persona = persona.id_persona")
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" ON venta_producto.id_unidad_inventario = unidad_inventario.id")
                .append(" INNER JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" ON unidad_inventario.producto_id = producto.id_producto")
                .append(" WHERE ")
                .append("tipo_persona='")
                .append(tipo_persona).append("'")
                .append(" AND numero_identificacion_persona='")
                .append(numero_identificacion_persona).append("'")
                .append(" AND activo_persona = 1")
                .append(" AND codigo_venta_producto = '")
                .append(codigo_venta_producto).append("'")
                .append(" AND activo_producto = 1 AND unidad_inventario.activo = 1;");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            
            if(rs.next()){
                resultado = rs.getDate("max");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            postgreSQL.desconectar();
        }
        
        return resultado;
    }
    
    
    /**
     * Elimina un producto incluido en una venta volviendo su activo 0, dado un idVenta y un idUnidadInventario
     * y actualiza su fecha de última modificación. 
     *
     * @param idVenta Id de la venta que esté abierta.
     * @param idUnidadInventario
     * @return
     */
    public boolean eliminarProductoEnVenta(int idVenta, int idUnidadInventario) {
        LocalDateTime fechaActual = LocalDateTime.now();
        
        StringBuilder sqlQuery = new StringBuilder();
        
        sqlQuery.append("UPDATE ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("venta_producto"))
                    .append(" SET activo_venta_producto=0")
                    .append(", fecha_ultima_modificacion='")
                    .append(fechaActual)
                    .append("' WHERE id_unidad_inventario = ")
                    .append(idUnidadInventario)
                    .append(" AND id_venta = ")
                    .append(idVenta)  
                    .append(";");
        
        return ejecutarQuerySinResultado(sqlQuery.toString());
    }

    /**
     * Hace el INSERT o el UPDATE de la tabla estado_caja.
     *
     * @param idEstadoCaja Id de la tabla estado_caja, en caso de ser 0 se asume
     * que la tabla no existe y realiza un insert
     * @param idCaja Id de la caja
     * @param idempleado Id de tabla del empleado.
     * @param monto Si es un INSERT, se toma como monto inicial. Si es un
     * UPDATE, se toma como monto final
     * @return id del estado_caja resultado del INSERT o del UPDATE.
     */
    public int setEstadoCaja(int idEstadoCaja, int idCaja, int idempleado, String monto) {
        Date date = new java.util.Date();
        int resultado = -1;
        StringBuilder sqlQuery = new StringBuilder();
        if (idEstadoCaja <= 0) {
            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("stpv")).append(".")
                    .append(mapTabla.get("estado_caja"))
                    .append("(caja_id, empleado_id, fecha_apertura, monto_apertura) VALUES (")
                    .append(idCaja).append(", ")
                    .append(idempleado).append(", '")
                    .append(new Timestamp(date.getTime())).append("', ")
                    .append(monto).append(");");
        } else {
            sqlQuery.append("UPDATE ")
                    .append(mapSchema.get("stpv")).append(".")
                    .append(mapTabla.get("estado_caja"))
                    .append(" SET fecha_cierre='").append(new Timestamp(date.getTime())).append("' ,")
                    .append("monto_cierre=").append(monto)
                    .append(" WHERE id=").append(idEstadoCaja)
                    .append(";");
        }
            resultado = ejecutarCreate(sqlQuery.toString(), "estado_caja");
        return resultado;
    }
     
    /**
     * Abre una caja creando un estado de caja
     * 
     * 
     * @param id_caja
     * @param id_empleado
     * @param monto_apertura
     * @return 
     */
    public int abrirCaja(int id_caja, int id_empleado, String monto_apertura, String monto_divisa){
        Date date = new java.util.Date();
        Date fecha_apertura = new Timestamp(date.getTime());
        
        int resultado;
        StringBuilder sqlQuery = new StringBuilder();
        System.out.println("Abriendo Estado de caja");
        sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("estado_caja"))
                    .append(" (fecha_apertura, monto_apertura,monto_divisa, id_empleado, id_caja, caja_abierta) VALUES ('")
                    .append(fecha_apertura).append("', '")
                    .append(monto_apertura).append("', '")
                    .append(monto_divisa).append("', '")
                    .append(id_empleado).append("', '")
                    .append(id_caja).append("', '")
                    .append(1).append("');");
                  
        resultado = ejecutarCreate(sqlQuery.toString(), "estado_caja");
        
        return resultado;
    }

    /**
     * Hace el UPDATE de la columna excedente de la tabla ESTADO_CAJA
     *
     * @param idCorteCaja Id de la tabla estado_caja, en caso de ser 0 se asume
     * que la tabla no existe y realiza un insert
     * @param monto Si es positivo se entiende como excendente, de lo contrario
     * es faltante.
     * @return id del estado_caja resultado del INSERT o del UPDATE.
     */
    public int setExcendenteFaltanteCaja(int idCorteCaja, String monto) {
        Date date = new java.util.Date();
        int resultado;
        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append("UPDATE ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("corte_caja"))
                .append(" SET excedente=").append(monto)
                .append(" WHERE id_corte_caja=").append(idCorteCaja)
                .append(";");

      
            resultado = ejecutarCreate(sqlQuery.toString(), "corte_caja");
        return resultado;
    }

    /**
     * Cambia el estado de la venta.
     *
     * @param idVenta
     * @param estadoVenta
     * @return
     */
    public int setEstadoVenta(int idVenta, EstadoVenta estadoVenta) {
        int resultado;
        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append("UPDATE ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" SET estado_venta=").append(estadoVenta)
                .append(" WHERE id_venta=").append(idVenta)
                .append(";");

       
            resultado = ejecutarCreate(sqlQuery.toString(), "venta");
        return resultado;
    }
    //Problema con el articulo.getCantidad()
    public void descontarcantidad(List<ArticuloDescontar> ad) {
        ResultSet result;
        int canti = 0;
        for (ArticuloDescontar articulo : ad) {
            String sql1 = "SELECT cantidad_disponible FROM  spve.producto WHERE codigo_venta_producto = '" + articulo.getCodigo_barra() + "';";
            try {
                postgreSQL.conectar();
                result = postgreSQL.ejecutarSelect(sql1.toString());;
                if (result.next()) {
                    canti = result.getInt("cantidad_disponible");
                }
                String sql2 = "UPDATE spve.producto SET cantidad_disponible=" + (canti - articulo.getCantidad()) + " WHERE codigo_venta_producto='" + articulo.getCodigo_barra() + "';";
                ejecutarCreate(sql2, "producto");
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ObjetoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                postgreSQL.desconectar();
            }
        }
    }

//    public String GetNombrepara(int id){
//        String sql = "select concat(nombre,' ',apellido) as nombre from stpv.empleado,stpv.estado_caja,stpv.venta where stpv.empleado.id = stpv.estado_caja.empleado_id and stpv.estado_caja.id = stpv.venta.id and stpv.venta.id = 84";
//        String nombre = "";
//        ResultSet result;
//        return nombre;
//        try{
//            postgreSQL.conectar();
//            result = postgreSQL.getSentencia().executeQuery(sql);
//            if(result.next)
//        }catch(ClassNotFoundException | SQLException ex){
//            Logger.getLogger(ObjetoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    /**
     * Consulta si hay cantidad disponible para vender un producto.
     * 
     * @param idVenta
     * @param idUnidadInventario
     * @param cantidad_venta - Cantidad del producto a vender.
     * @return 
     */
    public boolean consultastock(int idVenta, int idUnidadInventario, double cantidad_venta) {
        ResultSet result;
        StringBuilder sqlQuery = new StringBuilder();
        
        sqlQuery.append("SELECT unidad_inventario.cantidad_producto - COALESCE(SUM(CASE WHEN venta_producto.id_venta = ")
                .append(idVenta).append(" OR estado_venta = ")
                .append(EstadoVenta.Cancelada)
                .append(" THEN 0 ELSE venta_producto.cantidad_producto END), 0) AS cantidad_disponible")
                .append(" FROM ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta_producto"))
                .append(" ON unidad_inventario.id = venta_producto.id_unidad_inventario")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" ON venta_producto.id_venta = venta.id_venta")
                .append(" WHERE unidad_inventario.activo = 1 AND unidad_inventario.id = ")
                .append(idUnidadInventario)
                .append(" AND (activo_venta_producto IS NULL OR activo_venta_producto = 1)")
                .append(" AND (activo_venta IS NULL OR activo_venta = 1)")
                .append(" GROUP BY unidad_inventario.id;");
        
        try {
            postgreSQL.conectar();
            System.out.println(sqlQuery.toString());
            result = postgreSQL.getSentencia().executeQuery(sqlQuery.toString());

            if (result.next()) {
                if (result.getDouble("cantidad_disponible") >= cantidad_venta) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ObjetoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //Ernesto:
    //Campo "iva" fue reparalazado por "impuesto"
    public void actualizaiva(int id, String iva, XBigDecimal monto, double pagoconinva, double pagosiniva, String totalpag, String cambio, int idEmp) {
        SimpleDateFormat sdf = new SimpleDateFormat("Y-MM-dd hh:mm a");
        String fecha = sdf.format(new Date());
        String sql = "UPDATE stpv.venta SET iva ='" + iva + "',total='" + monto + "',fecha_hora='" + fecha + "',total_no_exento='" + pagoconinva + "',total_exento='" + pagosiniva + "',totalpag='" + totalpag + "',cambio='" + cambio + "',empleado_id='" + idEmp + "' WHERE id=" + id + ";";
        try {
            postgreSQL.conectar();
            postgreSQL.getSentencia().executeQuery(sql);
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }

    }

    public void guardarPagos(int idventa, List<ValorPagos> valor) {
        String sql;
        PostgreSQL d = new PostgreSQL();
        for (ValorPagos vp : valor) {
            int tipoPago = vp.getTipo();

            sql = "INSERT into spve.pago (id_venta,id_tipo_pago,monto_pago,fecha_pago) "
                + "VALUES('" + idventa + "','" + tipoPago + "','" + vp.getMonto() + "','" + vp.getFecha() + "');";
            d.ejecutar(sql);

        }
    }

    /**
     * Actualiza un registro de venta con su correspondiente IVA y Total.
     *
     * @param idVenta
     * @param iva
     * @param total
     * @return
     */
    public int setMontoVenta(int idVenta, String iva, String total) {
        int resultado;
        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append("UPDATE ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" SET impuesto_venta=").append(iva)
                .append(", total_venta=").append(total)
                .append("WHERE id_venta=").append(idVenta)
                .append(";");
      
            resultado = ejecutarCreate(sqlQuery.toString(), "venta");
        return resultado;
    }

    /**
     * Muestra el nombre del empleado en la caja
     *
     * @param cedula cedula del empleado que se mostrara.
     * @param clave 
     * @return
     */
    public int setempleadoCaja(String cedula, char[] clave) {
        ResultSet result;
        int id = -1;

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT cedula AS id, clave FROM ")
                .append(mapSchema.get("stpv"))
                .append(".").append(mapTabla.get("empleado"))
                .append(" WHERE cedula='")
                .append(cedula)
                .append("';");

        try {
            postgreSQL.conectar();
            result = postgreSQL.ejecutarSelect(sqlQuery.toString());
            String p = null;

            if (result.next()) {
                id = result.getInt("id");
                p = result.getString("clave");
            }
            char[] pass = PuntoVenta.Ventanas.Bloqueo1.jpwClave.getPassword();
            String passString = new String(pass);

        } catch (Exception e) {
            e.printStackTrace();
            id = -1;
        } finally {
            postgreSQL.desconectar();
        }

        return id;
    }

    /**
     * Devuelve el id del último estado de caja 
     * para una caja específica, si no encuentra ningún estado
     * devuelve -1
     * 
     * @param idCaja
     * @return 
     */
    public int getIdUltimoEstadoCaja(int idCaja) {
        int idUltimoEstadoCaja = -1;
        ResultSet rs;

        StringBuilder sqlQuery = new StringBuilder();
        
        sqlQuery.append("SELECT max(id_estado_caja) FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("estado_caja"))
                .append(" WHERE id_caja = ") 
                .append(idCaja)
                .append(";");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            
            if(rs.next()){
                idUltimoEstadoCaja = rs.getInt("max");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        return idUltimoEstadoCaja;
    }
            
    public void actualizarUnidadesInventario(JsonArray unidades) {
        if (unidades.size() > 0) {
            StringBuilder sqlQuery = new StringBuilder();

            String values = "";
            for (JsonElement element : unidades) {
                JsonObject unidad = element.getAsJsonObject();
                int id = unidad.get("id").getAsInt();
                BigDecimal cantidad_producto = unidad.get("cantidad_actual_sin_ventas").getAsBigDecimal();
                int almacen_id = unidad.get("almacen").getAsInt();
                JsonElement lote_produccion_id = unidad.get("lote_produccion");
                String lote = (lote_produccion_id.isJsonNull())?"NULL":lote_produccion_id.getAsString();
                int producto_id = unidad.get("producto").getAsInt();
                int activo = unidad.get("activo").getAsBoolean()?1:0;
                values += "(" + id + "," + cantidad_producto + "," + almacen_id + "," + lote + ","
                        + producto_id + "," + activo + "),";
            }
            values = values.substring(0, values.length() - 1);

            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("inventario")).append(".")
                    .append(mapTabla.get("unidad_inventario"))
                    .append(" (id, cantidad_producto, almacen_id, lote_produccion_id, "
                            + "producto_id, activo) "
                            + "VALUES ")
                    .append(values)
                    .append(" ON CONFLICT (id) DO UPDATE")
                    .append(" SET cantidad_producto = EXCLUDED.cantidad_producto, activo = EXCLUDED.activo;");

            ejecutarQuerySinResultado(sqlQuery.toString());
            
            for (JsonElement element : unidades) {
                JsonObject unidad = element.getAsJsonObject();
                int id = unidad.get("id").getAsInt();
                
                JsonElement precio = unidad.get("precio_actual");
                if(!precio.isJsonNull()) {
                    desactivarPreciosUnidadInventario(id);
                    actualizarPrecioUnidadInventario(id, precio.getAsJsonObject());
                }
            }
        }
    }

    public void actualizarAlmacenes(JsonArray almacenes) {
        if(almacenes.size() > 0){
            StringBuilder sqlQuery = new StringBuilder();

            String values = "";
            for (JsonElement element : almacenes) {
                JsonObject almacen = element.getAsJsonObject();
                int id = almacen.get("id").getAsInt();
                String nombre = almacen.get("nombre").getAsString();
                String direccion = "";
                if(!almacen.get("direccion").isJsonNull()) direccion = almacen.get("direccion").getAsString();
                values += "(" + id + ",'" + nombre + "','" + direccion + "'),";
            }
            values = values.substring(0, values.length() - 1);

            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("inventario")).append(".")
                    .append(mapTabla.get("almacen"))
                    .append(" (id, nombre, direccion) VALUES ")
                    .append(values)
                    .append(" ON CONFLICT (id) DO UPDATE")
                    .append(" SET nombre = EXCLUDED.nombre, direccion = EXCLUDED.direccion;");

            ejecutarQuerySinResultado(sqlQuery.toString());
        }
    }

    public void actualizarUnidadesProductos(JsonArray unidades) {
        if (unidades.size() > 0) {
            StringBuilder sqlQuery = new StringBuilder();

            String values = "";
            for (JsonElement element : unidades) {
                JsonObject unidad = element.getAsJsonObject();
                int id = unidad.get("id").getAsInt();
                String nombre = unidad.get("nombre").getAsString();
                String descripcion = unidad.get("descripcion").getAsString();
                values += "(" + id + ",'" + nombre + "','" + descripcion + "'),";
            }
            values = values.substring(0, values.length() - 1);

            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("inventario")).append(".")
                    .append(mapTabla.get("unidad"))
                    .append(" (id, nombre, descripcion) VALUES ")
                    .append(values)
                    .append(" ON CONFLICT (id) DO UPDATE")
                    .append(" SET nombre = EXCLUDED.nombre, descripcion = EXCLUDED.descripcion;");

            ejecutarQuerySinResultado(sqlQuery.toString());
        }
    }
    
    public void desactivarPreciosUnidadInventario(int unidad_inventario_id) {
        StringBuilder sqlQuery = new StringBuilder();

        sqlQuery.append("UPDATE ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("precio_unidad_inventario"))
                .append(" SET activo = 0 WHERE unidad_inventario_id = ")
                .append(unidad_inventario_id)
                .append(" AND activo = 1;");

        ejecutarQuerySinResultado(sqlQuery.toString());
    }
    
    public void actualizarPrecioUnidadInventario(int unidadInventario, JsonObject precio) {
        StringBuilder sqlQuery = new StringBuilder();
        
        
        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("precio_unidad_inventario"))
                .append(" (id, base_imponible, margen_ganancia, porcentaje_impuesto, "
                        + "impuesto, precio_venta_publico, exento, unidad_inventario_id, activo) "
                        + "VALUES (")
                .append(precio.get("id").getAsInt()).append(",")
                .append(precio.get("base_imponible").isJsonNull()?"NULL":precio.get("base_imponible").getAsBigDecimal()).append(",")
                .append(precio.get("margen_ganancia").isJsonNull()?"NULL":precio.get("margen_ganancia").getAsBigDecimal()).append(",")
                .append(precio.get("porcentaje_impuesto").isJsonNull()?"NULL":precio.get("porcentaje_impuesto").getAsBigDecimal()).append(",")
                .append(precio.get("impuesto").isJsonNull()?"NULL":precio.get("impuesto").getAsBigDecimal()).append(",")
                .append(precio.get("precio_venta_publico").getAsBigDecimal()).append(",")
                .append(precio.get("exento").getAsBoolean()).append(",")
                .append(unidadInventario).append(",")
                .append(precio.get("activo").getAsBoolean()?1:0)
                .append(") ON CONFLICT (id) DO UPDATE")
                .append(" SET base_imponible = EXCLUDED.base_imponible, margen_ganancia = EXCLUDED.margen_ganancia,"
                        + "porcentaje_impuesto = EXCLUDED.porcentaje_impuesto, impuesto = EXCLUDED.impuesto,"
                        + "precio_venta_publico = EXCLUDED.precio_venta_publico, exento = EXCLUDED.exento, "
                        + "unidad_inventario_id = EXCLUDED.unidad_inventario_id,"
                        + "activo = EXCLUDED.activo;");

        ejecutarQuerySinResultado(sqlQuery.toString());
    }
    
    public void actualizarProductos(JsonArray productos) {
        if (productos.size() > 0) {
            StringBuilder sqlQuery = new StringBuilder();

            String values = "";
            for (JsonElement element : productos) {
                JsonObject producto = element.getAsJsonObject();
                int id = producto.get("id").getAsInt();
                String descripcion = producto.get("descripcion").getAsString();
                String codigo_venta = producto.get("codigo_venta").getAsString();
                
                values += "(" + id + ",'" + descripcion + "','" + codigo_venta + "',";
                
                JsonElement limite_venta_persona = producto.get("limite_venta_persona");
                if(!limite_venta_persona.isJsonNull()) values += limite_venta_persona.getAsBigDecimal()+",";
                else values += "NULL,";
                
                JsonElement periodo_venta_producto = producto.get("periodo_venta_producto");
                if(!periodo_venta_producto.isJsonNull()) values += periodo_venta_producto.getAsInt()+",";
                else values += "NULL,";
                
                JsonElement id_unidad = producto.get("unidad");
                if(!id_unidad.isJsonNull()) {
                    values += id_unidad.getAsInt()+",";
                } else {
                    values += "NULL,";
                }
     
                int balanza = producto.get("balanza").getAsBoolean()?1:0;
                values += balanza  + ",";
                        
                JsonElement seguimiento = producto.get("seguimiento");
                if (!seguimiento.isJsonNull()) {
                    values += seguimiento.getAsInt() + ",";
                } else {
                    values += "NULL,";
                }
                
                int producto_pre_fabricado = producto.get("producto_pre_fabricado").getAsBoolean()?1:0;
                int activo = producto.get("activo").getAsBoolean()?1:0;
                
                values += producto_pre_fabricado + "," + activo + "),";
                
            }
            values = values.substring(0, values.length() - 1);

            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("producto"))
                    .append(" (id_producto, descripcion_producto, codigo_venta_producto, limite_venta_persona, "
                            + "id_periodo_venta_producto, id_unidad, balanza, seguimiento, producto_pre_fabricado, activo_producto) "
                            + "VALUES ")
                    .append(values)
                    .append(" ON CONFLICT (id_producto) DO UPDATE")
                    .append(" SET descripcion_producto = EXCLUDED.descripcion_producto, codigo_venta_producto = EXCLUDED.codigo_venta_producto,"
                            + "limite_venta_persona = EXCLUDED.limite_venta_persona,"
                            + "id_periodo_venta_producto = EXCLUDED.id_periodo_venta_producto, id_unidad = EXCLUDED.id_unidad,"
                            + "balanza = EXCLUDED.balanza, seguimiento = EXCLUDED.seguimiento, producto_pre_fabricado = EXCLUDED.producto_pre_fabricado,"
                            + "activo_producto = EXCLUDED.activo_producto;");

            ejecutarQuerySinResultado(sqlQuery.toString());
        }
    }

    public void actualizarLotes(JsonArray lotes) {
        if (lotes.size() > 0) {
            StringBuilder sqlQuery = new StringBuilder();

            String values = "";
            for (JsonElement element : lotes) {
                JsonObject lote = element.getAsJsonObject();
                int id = lote.get("id").getAsInt();
                String codigo = lote.get("codigo").getAsString();
                int producto_id = lote.get("producto").getAsInt();
                values += "(" + id + ",'" + codigo + "'," + producto_id + "),";
            }
            values = values.substring(0, values.length() - 1);

            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("inventario")).append(".")
                    .append(mapTabla.get("lote_produccion"))
                    .append(" (id, codigo, producto_id) VALUES ")
                    .append(values)
                    .append(" ON CONFLICT (id) DO UPDATE")
                    .append(" SET codigo = EXCLUDED.codigo;");

            ejecutarQuerySinResultado(sqlQuery.toString());
        }
    }
    
    public ArrayList<HashMap<String, String>> getArrayListVentasParaSincronizar() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        HashMap<String, String> map;
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String[] columnas = {"id_venta AS id", "estado_venta AS estado", "activo_venta AS activo"};

        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnas, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" WHERE fecha_ultima_sincronizacion IS NULL OR fecha_ultima_modificacion > fecha_ultima_sincronizacion;");

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnas) {
                    map.put(columna, rs.getString(columna));
                }
                System.out.println("MAP " + map);
                resultado.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }
    
    public ArrayList<HashMap<String, String>> getArrayListVentasProductosParaSincronizar() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        HashMap<String, String> map;
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String[] columnas = {"id_venta_producto AS id", "cantidad_producto AS cantidad", 
            "id_unidad_inventario AS unidad_inventario", "v.id_venta AS venta", "activo_venta_producto AS activo"};

        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnas, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta_producto")).append(" AS vp")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta")).append(" AS v")
                .append(" ON vp.id_venta = v.id_venta")
                .append(" WHERE fecha_ultima_sincronizacion IS NULL OR vp.fecha_ultima_modificacion > fecha_ultima_sincronizacion OR v.fecha_ultima_modificacion > fecha_ultima_sincronizacion;");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnas) {
                    map.put(columna, rs.getString(columna));
                }
                System.out.println("MAP " + map);
                resultado.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    public void actualizarFechaSincronizacionVentas() {
        StringBuilder sqlQuery = new StringBuilder();
        
        LocalDateTime fechaActual = LocalDateTime.now();
        
        sqlQuery.append("UPDATE ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" SET fecha_ultima_sincronizacion = '")
                .append(fechaActual)
                .append("', fecha_ultima_modificacion = '")
                .append(fechaActual)
                .append("' WHERE fecha_ultima_sincronizacion IS NULL OR fecha_ultima_modificacion > fecha_ultima_sincronizacion;");

        ejecutarQuerySinResultado(sqlQuery.toString());
    }

    public ArrayList<HashMap<String, String>> getArrayListUnidadesInventario() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        HashMap<String, String> map;
        
        String[] columnasUnidades = {
            "almacen.id AS almacen_id", "CONCAT(almacen.nombre, '. ', almacen.direccion) AS almacen", "producto.codigo_venta_producto AS codigo_venta_producto", 
            "lote_produccion.codigo AS codigo_lote", "CONCAT(unidad_inventario.cantidad_producto - COALESCE(SUM(CASE WHEN estado_venta = "+EstadoVenta.Cancelada+" THEN 0 ELSE venta_producto.cantidad_producto END), 0), ' ', unidad.descripcion) AS cantidad",
            "precio_venta_publico", "base_imponible", "margen_ganancia", "porcentaje_impuesto", "exento"
        };
        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnasUnidades, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(" FROM ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta_producto"))
                .append(" ON unidad_inventario.id = venta_producto.id_unidad_inventario")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" ON venta_producto.id_venta = venta.id_venta")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("almacen"))
                .append(" ON unidad_inventario.almacen_id = almacen.id") 
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" ON unidad_inventario.producto_id = producto.id_producto")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad"))
                .append(" ON producto.id_unidad = unidad.id ")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("lote_produccion"))
                .append(" ON unidad_inventario.lote_produccion_id = lote_produccion.id")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("precio_unidad_inventario"))
                .append(" ON unidad_inventario.id = precio_unidad_inventario.unidad_inventario_id ")
                .append("WHERE unidad_inventario.activo = 1 AND precio_unidad_inventario.activo = 1 "
                        + "AND (activo_venta_producto IS NULL OR activo_venta_producto = 1) AND "
                        + "(activo_venta IS NULL OR activo_venta = 1) ")
                .append("GROUP BY unidad_inventario.id, almacen.id, almacen, codigo_venta_producto, codigo_lote, unidad.descripcion,")
                .append("precio_venta_publico, base_imponible, margen_ganancia, porcentaje_impuesto, exento;");
                
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnasUnidades) {
                    map.put(columna, rs.getString(columna));
                }
                resultado.add(map);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        System.out.println(resultado);
        return resultado;
    }

    public ArrayList<HashMap<String, String>> getArrayListProductosCajero() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        HashMap<String, String> map;

        String[] columnasUnidades = {
            "almacen.id AS almacen_id", "CONCAT(almacen.nombre, '. ', almacen.direccion) AS almacen", "codigo_venta_producto", "descripcion_producto",
            "unidad_inventario.id AS id_unidad_inventario", "lote_produccion.codigo AS codigo_lote", "precio_venta_publico"
        };
    
        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnasUnidades, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(" FROM ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" LEFT JOIN ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
                .append(" ON unidad_inventario.producto_id = producto.id_producto")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("almacen"))
                .append(" ON unidad_inventario.almacen_id = almacen.id") 
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("lote_produccion"))
                .append(" ON unidad_inventario.lote_produccion_id = lote_produccion.id")
                .append(" INNER JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("precio_unidad_inventario"))
                .append(" ON unidad_inventario.id = precio_unidad_inventario.unidad_inventario_id ")
                .append("WHERE unidad_inventario.activo = 1 AND precio_unidad_inventario.activo = 1;");

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnasUnidades) {
                    map.put(columna, rs.getString(columna));
                }
                resultado.add(map);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            resultado = null;
        } finally {
            postgreSQL.desconectar();
        }
        System.out.println(resultado);
        return resultado;
    }

    public int getIdUnidadInventario(int idProducto, int idAlmacen, String codigoLote) {
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        int resultado = -1;

        sqlQuery.append("SELECT unidad_inventario.id AS id_unidad_inventario")
                .append(" FROM ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("unidad_inventario"))
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("lote_produccion"))
                .append(" ON unidad_inventario.lote_produccion_id = lote_produccion.id")
                .append(" WHERE ")
                .append(" unidad_inventario.producto_id = ")
                .append(idProducto)
                .append(" AND almacen_id = ")
                .append(idAlmacen)
                .append(" AND (lote_produccion.codigo IS NULL OR lote_produccion.codigo = '")
                .append(codigoLote)
                .append("') AND activo = 1;");

        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getInt("id_unidad_inventario");
                System.out.println("resultado " + resultado);

            }
        } catch (Exception e) {
        } finally {
            postgreSQL.desconectar();
        }

        return resultado;
    }

    /**
     * Enum de los estados de una venta.
     */
    public enum EstadoVenta {

        EnProceso(1),
        Finalizada(2),
        Pausada(3),
        Cancelada(4);

        int id;

        EstadoVenta(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return String.valueOf(this.id);
        }
    }

    /**
     * Enum de los tipos de pago.
     */
    public enum TipoMoneda {

        Efectivo(1),
        Debito(2),
        Credito(3),
        Cheque(4),
        CestaTicket(5);

        int id;

        TipoMoneda(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return String.valueOf(this.id);
        }

//        public int getIdMoneda(XBigDecimal value) {
//            return 1;
//        }
        //Provisional
        public int getIdMoneda() {
            return this.id;
        }
    }

    /**
     * Agrega un String[] a un StringBuilder, utiliza una coma ',' despues de
     * cada valor y añade el prefijo a cada uno. Ejparalo: <code> culumnas = {"nombre", "apellido"}; prefijo = "p."; query += "p.nombre, p.apellido,";
     * </code>
     *
     * @param columnas
     * @param prefijo
     * @param query
     * @return
     */
    private StringBuilder addColumnasAlQuery(String[] columnas, final String prefijo, StringBuilder query) {
        String as = "AS";
        for (int i = 0; i < columnas.length; i++) {
            String columna = columnas[i];
            query.append(prefijo).append(columna).append(',');
            if (columna.contains(as)) {
                String[] splitColumna = columna.split(as);
                columnas[i] = splitColumna[splitColumna.length - 1].trim();
            }
        }
        return query;
    }

    /**
     * Ejecuta un query que no devuelve ningún resultado
     * 
     * @param query
     * @return true si se ejecutó el query 
     */
    private boolean ejecutarQuerySinResultado(String query){
        boolean resultado = false;
        
        try {
            postgreSQL.conectar();
            resultado = postgreSQL.ejecutarQuerySinResultado(query);
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }

      return resultado;
    }
    
    private int ejecutarCreate(String query, String tabla) {
        int resultado = -1;
        
        try {
            postgreSQL.conectar();
            resultado = postgreSQL.ejecutarCreate(query, tabla);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    public String direccionCliente(String cedula) {
        // Konstanza: mejorar query
        
        String direccion = "";
        try {
            postgreSQL.conectar();
            ResultSet result;
            String sql = "SELECT direccion_persona FROM spve.persona WHERE numero_identificacion_persona='" + cedula + "';";
            result = postgreSQL.ejecutarSelect(sql);
            if (result.next()) {
                direccion = result.getString("direccion_persona");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        return direccion;
    }

    // Método para traer los reportes pdf de la base de datos
    public byte[] reimprimirReporte(String codigo,  String campo, String tabla, String filtro, String fileName) throws IOException {
        GuardarReporte gr = new GuardarReporte();
        PostgreSQL d = new PostgreSQL();
        byte[] reporteBytea = {};
        System.out.println("codigo: " + codigo);
        String sql = "SELECT "+campo+" FROM spve."+tabla+" WHERE "+filtro+" = '"+codigo+"'";
        try{
            d.buscar(sql);
            while(d.rs.next()) {
                reporteBytea = d.rs.getBytes(campo);
            }
        }catch(Exception e){}
        
        System.out.println("\nlength del bytea: "+ reporteBytea.length + "\n");
//        return  gr.ByteArrayToFile(reporteBytea,  fileName+".PDF");
        return reporteBytea;
    }
    
    public File reimprimirReporteCierre(String codigo,  String campo, String tabla, String filtro, String fileName) throws IOException {
        GuardarReporte gr = new GuardarReporte();
        PostgreSQL d = new PostgreSQL();
        byte[] reporteBytea = {};
        System.out.println("codigo: " + codigo);
        String sql = "SELECT reporte_cierre FROM spve.reporte WHERE id_cierre_caja = '2'";
        try{
            d.buscar(sql);
            while(d.rs.next()) {
                reporteBytea = d.rs.getBytes("reporte_cierre");
            }
        }catch(Exception e){}
        
        System.out.println("\nlength del bytea: "+ reporteBytea.length + "\n");
        return  gr.ByteArrayToFile(reporteBytea,  fileName+".PDF");
    }
    
    /*
    public List<reporte1> reimprimirfac(String codigo) {
        List lista = new ArrayList();
        ResultSet rs;
        Pais p = getDatosPais("WHERE activo = true");
        Parametros para = getDatosParametros();//Ernesto: /*REVISAR QUERY*
        String sql = "SELECT DISTINCT CONCAT(c.nombre,' ',c.apellido) as nombre,c.cedula,c.direccion,"
                + "ven.codigo_factura,ven.total_exento,ven.total_no_exento,ven.iva,ven.total,ven.totalpag,ven.cambio,"
                + "pag.tipopago,pag.monto,vp.cantidad_producto, CONCAT (e.nombre,' ',e.apellido) as nombE,"
                + "p.descripcion,p.pvp,p.impuesto,(p.pvp*vp.cantidad_producto) as pt FROM stpv.venta ven "
                + "INNER JOIN stpv.venta__producto vp on ven.id=vp.venta_id "
                + "INNER JOIN inventario.producto p on p.id=vp.producto_id "
                + "INNER JOIN stpv.pago pag on pag.id_venta=ven.id "
                + "INNER JOIN stpv.cliente c on c.id=ven.cliente_id "
                + "INNER JOIN stpv.empleado e on e.id=ven.empleado_id "
                + "WHERE ven.codigo_factura='" + codigo + "';";

        System.out.println("RS antes try ");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sql);
            System.out.println("RS try "+rs);
            while (rs.next()) {
                System.out.println("RS "+rs);
                String pagado = rs.getString("pt");
                String codigofac = rs.getString("codigo_factura");
                String descrip = "";
                if (rs.getString("descripcion").length() > 13) {
                    for (int k = 0; k < rs.getString("descripcion").length(); k++) {
                        if (k % 13 == 0 && k != 0) {
                            descrip = descrip + rs.getString("descripcion").substring(0, k) + "<br/>" + rs.getString("descripcion").substring(k, rs.getString("descripcion").length());
                        }
                    }
                } else {
                    descrip = rs.getString("descripcion");
                }
                while (codigofac.length() < 10) {
                    codigofac = "0" + codigofac;
                }
                pagado = "" + redondeo.format(Double.parseDouble(pagado));
                if (rs.getString("impuesto").equals("0.00")) {
                    PuntoVenta.reporte1 rp = new PuntoVenta.reporte1(rs.getString("cantidad_producto"), descrip + " (E)",
                            rs.getString("pvp"), pagado, rs.getString("total"), rs.getString("nombre"), rs.getString("cedula"),
                            rs.getString("direccion"), codigofac, rs.getString("totalpag"), rs.getString("tipopago"), para.getIdentificacion(),
                            para.getNombre(), para.getDireccion(), para.getTelefono(), p.getSimbolo(), rs.getString("total_exento"),"0.", rs.getString("iva"),
                            rs.getString("cambio"), rs.getString("nombE"));
                    lista.add(rp);
                } else {
                   PuntoVenta.reporte1 rp = new PuntoVenta.reporte1(rs.getString("cantidad_producto"), descrip,
                           rs.getString("pvp"), pagado, rs.getString("total"), rs.getString("nombre"), rs.getString("cedula"),
                           rs.getString("direccion"), codigofac, rs.getString("totalpag"), rs.getString("tipopago"), para.getIdentificacion(),
                           para.getNombre(), para.getDireccion(), para.getTelefono(), p.getSimbolo(), rs.getString("total_exento"),"0.", rs.getString("iva"),
                           rs.getString("cambio"), rs.getString("nombE"));
                   lista.add(rp);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ObjetoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            postgreSQL.desconectar();
        }
        return lista;
    }
*/
    public int crearCierre(String monto_fisico, String monto_sistema, String monto_cortes, int id_estado_caja, String fecha) {
        
//        PostgreSQL d = new PostgreSQL();
        currentLoger cl = new currentLoger();
        Empleado emple = cl.getDatosEmpleadoLogueado();
        Date now = new Date();
        
        String sql = "INSERT INTO spve.cierre_caja(monto_fisico, monto_sistema, monto_cortes, id_empleado, id_estado_caja, fecha_cierre)"
                   + "VALUES ('"+monto_fisico+"', '"+monto_sistema+"', '"+monto_cortes+"', '"+emple.getId()+"', '"+id_estado_caja+"', '"+now+"'  )";
        
//        return d.ejecutar("INSERT INTO spve.cierre_caja(monto_fisico, monto_sistema, monto_cortes, id_empleado, id_estado_caja, fecha_cierre)"
//                   + "VALUES ('"+monto_fisico+"', '"+monto_sistema+"', '"+monto_cortes+"', '"+emple.getId()+"', '"+id_estado_caja+"', '"+fecha+"'  )");
        
        return ejecutarCreate(sql, "cierre_caja");
        
//        System.out.println(sql);
//        try {
//            postgreSQL.conectar();
//            postgreSQL.getSentencia().execute(sql);
//        } catch (Exception ex) {
//            Logger.getLogger(ObjetoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            postgreSQL.desconectar();
//        }

    }

}
