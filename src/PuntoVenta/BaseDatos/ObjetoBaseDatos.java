/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.BaseDatos;

import ClasesExtendidas.Numeros.XBigDecimal;
import PuntoVenta.Modelos.ModeloCliente;
import PuntoVenta.Modelos.ModeloEmpleado;
import PuntoVenta.reporte1;
import Utilidades.ArticuloDescontar;
import Utilidades.Cripto;
import Utilidades.ValorPagos;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        postgreSQL = new PostgreSQL(url, login, clave);
        crearMaps();
    }

    public static void main(String[] args){
        ObjetoBaseDatos obd = new ObjetoBaseDatos("jdbc:postgresql://localhost:5432/stpv", "inverdata", "C1234567c");
        System.out.println(obd);
        //LISTO//obd.crearPersona("Jose", "Gonzalez", 'V', "1234567", "mcbo", "7654321", "asdf@gmail.com");
        //LISTO//obd.modificarPersona("ernesto", "rincon", 'E', "20944806", "zulia", "7654321", "erincongil@gmail.com", "1234567");
        //LISTO//obd.eliminarPersona("20944806");
        //LISTO//obd.crearEmpleado("luis", "rincon", 'V', "25491458", "san jose", "04167662633", "luisjuanito@gmail.com", "asdf", 1);
        obd.eliminarEmpleado("25491458");
        //obd.seleccionarCargo();
        //Empleado empleado = obd.getDatosEmpleado(1);
        //System.out.println(empleado.getNombre());
    }
    
    /**
     * Mapas para no tener que cambiar los nombres de los schemas y tablas si se
     * cambian en la base de datos.
     */
    private void crearMaps() {
        mapSchema.put("spve", "spve");
        mapTabla.put("caja", "caja");
        mapTabla.put("cierre_caja", "cierre_caja");
        mapTabla.put("corte_caja", "corte_caja");
        mapTabla.put("desglose_caja", "desglose_caja");
        mapTabla.put("estado_caja", "estado_caja");
        mapTabla.put("cargo", "cargo");
        //mapTabla.put("cliente", "cliente");
        //mapTabla.put("cliente__telefono", "cliente__telefono");
        mapTabla.put("empleado", "empleado");
        mapTabla.put("persona", "persona");
        //mapTabla.put("estado_venta", "estado_venta");
        //mapTabla.put("moneda", "moneda");
        mapTabla.put("venta", "venta");
        mapTabla.put("venta_producto", "venta_producto");
        mapTabla.put("pago", "pago");
        //mapTabla.put("telefono", "telefono");
        mapTabla.put("tipo_pago", "tipo_pago");
        mapTabla.put("producto", "producto");
        mapTabla.put("ajuste", "ajuste");
        mapTabla.put("produccion", "produccion");
        mapTabla.put("compra", "compra");
        mapTabla.put("producto_componente", "producto_componente");
        //mapTabla.put("usuario", "usuario_sistema");
        
        //mapTabla.put("venta__pago", "venta__pago");
        
        //mapSchema.put("inventario", "inventario");
        //mapTabla.put("producto", "producto");

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
     * @return id del cliente resultado del INSERT o -1 en caso de fallar.
     */
    public int crearPersona(String nombre, String apellido, char tipo_persona, String numero_identificacion_persona, String direccion, String telefono, String correo) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        
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

        resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "persona");

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
    public int modificarPersona(String nombre_persona, String apellido_persona, char tipo_persona, String numero_identificacion_persona, String direccion_persona, String telefono_persona, String email_persona, String numero_identificacion_persona_viejo) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        
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
                .append("';");
        
        resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "persona");
        
        return resultado;
    }

    /**
     * Elimina un cliente, dado un idCliente y la cedula del cliente. La cedula
     * es utilizada para sacar el id del cliente ya que en la tabla de empleados
     * no se guarda su id, sino la cedula.
     *
     *
     * @param numero_identificacion_persona Cedula del empleado que se desee eliminar.
     */
    public void eliminarPersona(String numero_identificacion_persona) {
        ResultSet result;

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("DELETE FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("persona"))
                .append(" WHERE numero_identificacion_persona='")
                .append(numero_identificacion_persona)
                .append("';");

        try {
            postgreSQL.conectar();
            result = postgreSQL.ejecutarSelect(sqlQuery.toString());
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }

      //return result;
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
    public int crearEmpleado(String nombre, String apellido, char tipo_persona, String numero_identificacion_persona, String direccion_persona, String telefono_persona, String email_persona, String clave, int id_cargo) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
            resultado = crearPersona(nombre, apellido, tipo_persona, numero_identificacion_persona, telefono_persona, email_persona, direccion_persona);
            if(resultado > 0){
                sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("empleado")) 
                .append("(clave, id_persona, id_cargo) VALUES(")
                .append("'").append(clave).append("', ")
                .append("'").append(resultado).append("', ")
                .append("'").append(id_cargo)
                .append("');");
                resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "empleado");
            }
                
        return resultado;
    }
    
    //Ernesto:
    //Metodo para seleccionar el tipo de cargo que tendrá un empleado
    public Cargo seleccionarCargo() {
        ResultSet rs;
        Cargo cargo = new Cargo();

        String query = "SELECT nombre_cargo FROM spve.cargo";
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
        if (cargo != null) {
            return cargo;
        }
        return null;
    }

    /**
     * Consulta los datos de la empresa en la tabla stpv.empresa.
     *
     * @return Empresa resultado de la consulta o null en caso de fallar.
     */
    public Empresa datosEmpresas() {
        ResultSet rs;
        Empresa emp = new Empresa();

        String query = "SELECT * FROM stpv.empresa";
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(query);
            while (rs.next()) {
                emp.setNombre(rs.getString("nombre"));
                emp.setRif(rs.getString("rif"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setDireccion(rs.getString("direccion"));
                emp.setMoneda(rs.getString("moneda_utilizada"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        if (emp != null) {
            return emp;
        }
        return null;
    }

    /**
     * Consulta los datos del Empleado en la tabla spve.empleado.
     *
     * @param idEmpleado
     * @return Empleado resultado de la consulta o null en caso de fallar.
     */
    public Empleado getDatosEmpleado(int idEmpleado) {
        ResultSet rs;
        Empleado emple = new Empleado();

        String query = "SELECT persona.nombre_persona, persona.apellido_persona, persona.tipo_persona, persona.numero_identificacion_persona, \n" +
                        "persona.telefono_persona, persona.email_persona, \n" +
                        "persona.direccion_persona, empleado.id_empleado, cargo.nombre_cargo \n" +
                        "FROM spve.persona INNER JOIN spve.empleado ON persona.id_persona = empleado.id_persona \n" +
                        "INNER JOIN spve.cargo ON empleado.id_cargo = cargo.id_cargo WHERE id_empleado =" + idEmpleado;
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
                //emple.setPassword(rs.getString("clave"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        
        return emple;
    }

    /**
     * Inserta los datos de la empresa en la tabla stpv.empresa.
     *
     * @param nombre
     * @param rif
     * @param telefono
     * @param moneda
     * @param direccion
     */
    /*public void ingresoEmp(String nombre, String rif, String telefono, String direccion, String moneda) {
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String sql = "SELECT * FROM stpv.empresa";
        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sql);
            while (rs.next()) {
                sql = "UPDATE stpv.empresa SET nombre='" + nombre + "',rif='" + rif + "',direccion='" + direccion + "',moneda_utilizada='" + moneda + "'";
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
                    .append("empresa")
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
     * Modifica los datos del empleado en la tabla stpv.empleado.
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
     * @return
     */
    public int modificarEmpleado(String id_cargo, String nombre_persona, String apellido_persona, char tipo_persona, String numero_identificacion_persona, String telefono_persona, String email_persona, String direccion_persona, String clave, String departamento, String numero_identificacion_persona_viejo) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        
                resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "empleado");
                if(resultado > 0){
                modificarPersona(nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, direccion_persona, telefono_persona, email_persona, numero_identificacion_persona_viejo);
                sqlQuery.append("UPDATE ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("empleado"))
                .append(" SET id_cargo ='").append(id_cargo)
                .append("', clave='").append(clave)
                .append("' WHERE numero_identificacion_persona='")
                .append(numero_identificacion_persona_viejo)
                .append("';");
                
                
                }
                return resultado;
    }

    /**
     * Elimina un empleado, dado un idEmpleado y la cedula del empleado. La
     * cedula es utilizada para sacar el id del empleado ya que en la tabla de
     * empleados no se guarda su id, sino la cedula.
     *
     *
     * @param cedula Cedula del empleado que se desee eliminar.
     * @return
     */
    public int eliminarEmpleado(String cedula) {
        ResultSet result;
        int id = -1;

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("DELETE FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("empleado"))
                .append(" WHERE numero_identificacion_persona='")
                .append(cedula)
                .append("';");

        try {
            postgreSQL.conectar();
            result = postgreSQL.ejecutarSelect(sqlQuery.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }

        return id;
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
            char[] pass = PuntoVenta.Ventanas.bloqueo2.pass.getPassword();

            String passString = new String(pass);

            if (passString.equals(p)) {

                PuntoVenta.Inicio.MenuPrincipal.btnCaja.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnVentas.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnAyuda.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.jButton5.setEnabled(true);
                PuntoVenta.Inicio.MenuPrincipal.btnAdmin.setEnabled(true);
                PuntoVenta.Ventanas.bloqueo2.jButton2.setEnabled(true);
                PuntoVenta.Ventanas.bloqueo2.jButton2.requestFocus();

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

    public int autenticarEmpleado2(String cedula, char[] clave) {
        ResultSet result;
        int id = -1;

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT id_empleado, clave FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("empleado")).append(" as e ")
                .append("INNER JOIN ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("persona")).append(" as p ")
                .append("ON e.id_persona = p.id_persona ")
                .append("WHERE numero_identificacion_persona='")
                .append(cedula)
                .append("';"); 
        try {
            postgreSQL.conectar();
            result = postgreSQL.ejecutarSelect(sqlQuery.toString());
            String p = null;

            if (result.next()) {
                id = result.getInt("id_empleado");
                p = result.getString("clave");
            }
            
            char[] pass = PuntoVenta.Ventanas.LogIn.jpwClave.getPassword();

            String passString = new String(pass);
            
            if (!passString.equals(p)) {
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

    public int actualizarEmpleado(ModeloEmpleado empleado, String nombre_persona, String apellido_persona, char tipo_persona, String numero_identificacion_persona, String telefono_persona, String email_persona, String direccion_persona, String id_cargo, String clave, String departamento, String numero_identificacion_persona_viejo) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
            modificarPersona(nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, direccion_persona, telefono_persona, email_persona, numero_identificacion_persona_viejo);
            modificarEmpleado(id_cargo, nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, telefono_persona, email_persona, direccion_persona, clave, departamento, numero_identificacion_persona_viejo);
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "empleado");
       
        return resultado;
    }

    public int actualizarCliente(ModeloCliente cliente, String nombre_persona, String apellido_persona, char tipo_persona, String numero_identificacion_persona, String telefono_persona, String email_persona, String direccion_persona, String numero_identificacion_persona_viejo) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        modificarPersona(nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, direccion_persona, telefono_persona, email_persona, numero_identificacion_persona_viejo);
        resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "persona");
        return resultado;
    }

    /**
     * Actualiza la informacion de un empleado basandose en el ModeloEmpleado.
     *
     * @param cliente
     * @return
     */
    //public int actualizarClienteAdmin(ModeloCliente cliente) {
        /* Konstanza:
            - ¡¿Qué es un 'ClienteAdmin'?!
            - El nombre dice que actualiza cliente pero el query es para empleados
            - El argumento de la función debería ser el modelo de empleado
        */
        /*StringBuilder sqlQuery = new StringBuilder(ModeloCliente cliente, String nombre_persona, String apellido_persona, String tipo_persona, String numero_identificacion_persona, String telefono_persona, String email_persona, String direccion_persona){
        int resultado;
        modificarPersona(nombre_persona, apellido_persona, direccion_persona, telefono_persona, email_persona, numero_identificacion_persona);
        resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "persona");
        return resultado;

    }*/

    /**
     * Verifica en la base de datos si una caja específica está abierta o
     * cerrada.
     *
     * @param idCaja
     * @return Estado de la caja. Abierto o cerrado.
     */
    public boolean isCajaAbierta(int idCaja) {
        boolean cajaAbierta = false;
        ResultSet rs;

        StringBuilder sqlQuery = new StringBuilder();
        
        sqlQuery.append("SELECT EXISTS (SELECT id_cierre_caja FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("cierre_caja"))
                .append(" as cic WHERE cic.id_corte_caja = (SELECT max(id_corte_caja) FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("corte_caja"))
                .append(" as cc WHERE cc.id_estado_caja = (SELECT max(id_estado_caja) FROM ")
                .append(mapSchema.get("spve"))
                .append(".").append(mapTabla.get("estado_caja"))
                .append(" as ec WHERE ec.id_caja = ") 
                .append(idCaja)
                .append(")));");
        
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
        return cajaAbierta;
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
        String fecha_cierre = "";

        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT id ,fecha_apertura, fecha_cierre FROM ")
                .append(mapSchema.get("stpv"))
                .append(".").append(mapTabla.get("estado_caja"))
                .append(" WHERE caja_id=") 
                .append(idCaja)
                .append(" ORDER BY id DESC LIMIT 1;");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                idEstadoCaja = rs.getInt("id");
                fecha_cierre = rs.getString("fecha_cierre");
            }
        } catch (Exception e) {
            e.printStackTrace();
            idEstadoCaja = -1;
        } finally {
            postgreSQL.desconectar();
        }
        //Este if verifica que la caja no tenga fecha de cierre.
        if (fecha_cierre != null) {
            idEstadoCaja = -1;
        }
        return idEstadoCaja;
    }

    public ArrayList<HashMap<String, String>> getArrayListEmpleado() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        HashMap<String, String> map;
        String[] columnasEmpleado = {"e.cedula AS cedula", "e.nombre||' '||e.apellido AS nombre"};
        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnasEmpleado, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(" FROM ")
                .append("stpv.empleado AS e;");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnasEmpleado) {
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
     * particular y devuelve el id, la cedula del empleado que la abrió/cerró, y
     * los datetime de apertura y cierre.
     *
     * NOTA: Probablemente se deba filtrar la busqueda por usuario tambien.
     *
     * @param idCaja
     * @return Arraylist de HashMap
     */
    public ArrayList<HashMap<String, String>> getArrayListEstadoCaja(int idCaja) {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String[] columnas = {"id", "empleado", "apertura", "cierre"};

        sqlQuery.append("SELECT ec.id AS id, CAST(e.nacionalidad AS text)||'-'||e.cedula AS empleado, ")
                .append("to_char(ec.fecha_apertura, 'DD Mon YYYY HH12:MI:SS AM') AS apertura, to_char(ec.fecha_cierre, 'DD Mon YYYY HH12:MI:SS AM') AS cierre ")
                .append("FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("empleado")).append(" AS e ")
                .append("LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("estado_caja")).append(" AS ec ")
                .append("ON e.id=ec.empleado_id ")
                .append("WHERE ec.caja_id=")
                .append(idCaja)
                .append(" ORDER BY id DESC;");
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

    public ArrayList<HashMap<String, String>> getArrayListFactura() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        ResultSet rs;
//        StringBuilder sqlQuery = new StringBuilder();
//        String[] columnas = {"codigo_factura", "cliente", "fecha_hora", "monto"};
        String sql1 = "SELECT v.codigo_factura,CONCAT(c.nombre,' ',c.apellido) AS nombre, v.fecha_hora,v.total FROM stpv.cliente c INNER JOIN stpv.venta v on v.cliente_id= c.id ORDER BY v.codigo_factura DESC";

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sql1);
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    /**
     * Método que busca los desglose de moneda y devulve el id, el valor y la
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
        String[] columnaProducto = {"codigo_barra", "descripcion", "pvp-(pvp*impuesto) AS pvp", "pvp*cantidad_producto AS total", "pvp*impuesto AS impuesto"};
        String[] columnaProductoVenta = {"cantidad_producto"};

        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnaProducto, "p.", sqlQuery);
        sqlQuery = addColumnasAlQuery(columnaProductoVenta, "vp.", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("producto")).append(" AS p")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("venta__producto")).append(" AS vp")
                .append(" ON p.id=vp.producto_id")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("venta")).append(" AS v")
                .append(" ON vp.venta_id=v.id")
                .append(" WHERE v.id=").append(idVenta).append(";");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnaProducto) {
                    if (("total".equals(columna) || "impuesto".equals(columna) || "pvp".equals(columna))) {
                        map.put(columna, redondeo.format(Double.parseDouble(rs.getString(columna))).replace(",", "."));
                    } else {
                        map.put(columna, rs.getString(columna));
                    }
                }
                for (String columna : columnaProductoVenta) {
                    if (rs.getString(columna).endsWith(".00")) {
                        map.put(columna, rs.getString(columna).substring(0, rs.getString(columna).length() - 3));
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
        String[] columnaProducto = {"id", "codigo_barra", "descripcion", "stockminimo", "stockmaximo", "limitedeventaporpersona", "puntodepedido", "costoxunidad", "margendeganancia", "baseimponible", "fecha_actualizacion", "impuesto", "pvp"};

        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnaProducto, "p.", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("producto")).append(" AS p;");

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
        //Ernesto:
        //devuelve una lista de los tipos de moneda existentes en un arraylist?
    public ArrayList<HashMap<String, String>> getArrayListTipoMoneda() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        HashMap<String, String> map;
        ResultSet rs;
        StringBuilder sqlQuery = new StringBuilder();
        String[] columnaTipoMoneda = {"id", "descripcion"};

        sqlQuery.append("SELECT ");
        for (String columna : columnaTipoMoneda) {
            sqlQuery.append(columna).append(",");
        }
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("tipo_moneda")).append(";");
                //Tabla "tipo_moneda" actualizada a "tipo_pago"
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnaTipoMoneda) {
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

    public ArrayList<HashMap<String, String>> getArrayListClientes() {
        ArrayList<HashMap<String, String>> resultado = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        HashMap<String, String> map;
        String[] columnasCliente = {"c.cedula AS cedula", "c.nombre||' '||c.apellido AS nombre"};
        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnasCliente, "", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);
        sqlQuery.append(" FROM ")
                .append("stpv.cliente AS c;");
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
     * Método para obtener la lista de cortes que se le han ralizado a un
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
        String[] columnasCorteCaja = {"id AS nombre", "monto_corte"};

        sqlQuery.append("SELECT ");
        sqlQuery = addColumnasAlQuery(columnasCorteCaja, "cc.", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("corte_caja")).append(" AS cc")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("estado_caja")).append(" AS ec")
                .append(" ON cc.estado_caja_id=ec.id ")
                .append(" WHERE ec.id=").append(idEstadoCaja)
                .append(";");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            while (rs.next()) {
                map = new HashMap<>();
                for (String columna : columnasCorteCaja) {
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
     * Utiliza el id de la caja para buscar en la base de datos y crear un
     * map<k, v> de la tabla caja
     *
     * @param id
     * @return
     */
    public HashMap<String, String> getMapCaja(int id) {
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        ResultSet rs;

        sqlQuery.append("SELECT id_caja, descripcion_caja FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("caja"))
                .append(" WHERE id_caja=").append(id)
                .append(" LIMIT 1;");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            String descripcion;
            if (rs.next()) {
                descripcion = rs.getString("descripcion_caja");
                map.put("id_caja", String.valueOf(id));
                map.put("descripcion_caja", descripcion);
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
     * Utiliza el id de un cliente para buscar en la base de datos y crear un
     * map<k, v> de la tabla cliente
     *
     * @param identificador
     * @param cedula
     * @return
     */
    public HashMap<String, String> getMapCliente(char identificador, String cedula) {
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        ResultSet rs = null;
        String[] columnaCliente = new String[]{"id", "nombre", "apellido", "direccion", "nacionalidad", "cedula", "correo", "facebook", "twitter"};

        sqlQuery.append("SELECT ");
        for (String columna : columnaCliente) {
            sqlQuery.append("c.").append(columna).append(",");
        }
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("cliente")).append(" AS c ")
                .append(" WHERE c.nacionalidad='").append(identificador).append("'")
                .append(" AND c.cedula='").append(cedula).append("'")
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

        String[] columnaEmpleado = new String[]{"id_empleado", "clave", "nombre_persona", "apellido_persona", "tipo_persona", "numero_identificacion_persona", "email_persona", "telefono_persona"};
        String[] columnaCargo = new String[]{"id_cargo", "nombre_cargo"};

        sqlQuery.append("SELECT ");
        for (String columna : columnaEmpleado) {
            sqlQuery.append(columna).append(",");
        }
        for (String columna : columnaCargo) {
            sqlQuery.append(columna).append(",");
        }
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
                .append(" ON id_cargo_empleado=id_cargo")
                .append(" WHERE id_empleado=").append(id)
                .append(";");
        
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                for (String columna : columnaEmpleado) {
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

    public HashMap<String, String> getMapUsuarioSistema(int idEmpleado) {
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
                .append(" WHERE e.id=").append(idEmpleado)
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
     * Utiliza el id o el codigo de barras de un prodcuto para buscar en el
     * schema inventario y crear un map<k, v> de la tabla producto
     *
     * @param codigoBarra
     * @return
     */
    public HashMap<String, String> getMapProducto(String codigoBarra) {
        StringBuilder sqlQuery = new StringBuilder();
        HashMap<String, String> map = new HashMap<>();
        ResultSet rs;
        String[] columnaProducto = {"id", "codigo_barra", "descripcion", "stockminimo", "stockmaximo", "limitedeventaporpersona", "puntodepedido", "costoxunidad", "margendeganancia", "baseimponible", "fecha_actualizacion", "impuesto", "pvp"};

        sqlQuery.append("SELECT ");
        addColumnasAlQuery(columnaProducto, "p.", sqlQuery);
        sqlQuery.deleteCharAt(sqlQuery.length() - 1);

        sqlQuery.append(" FROM ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("producto")).append(" AS p ")
                .append(" WHERE ");
        try {
//            int id = Integer.parseInt(codigoBarra);
            //sqlQuery.append("p.id=").append(id);
            sqlQuery.append("p.codigo_barra='").append(codigoBarra).append("'");
//        } catch (NumberFormatException e) {
//            sqlQuery.append("p.codigo_barra='").append(codigoBarra).append("'");
        } finally {
            sqlQuery.append(" LIMIT 1;");
        }
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                for (String columna : columnaProducto) {
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
     * NOTA. Este query se ha creado en reemplazo de
     * getTotalEstadoCajaPorProductoVendido;
     *
     * @param idEstadoCaja
     * @return XBigDecimal con el valor del total de la venta.
     */
    public XBigDecimal getTotalEstadoCaja(int idEstadoCaja) {
        ResultSet rs;
        XBigDecimal resultado = new XBigDecimal(0);
        XBigDecimal acum;
        String sql = "SELECT v.total AS total FROM stpv.venta AS v LEFT JOIN stpv.estado_venta AS ev ON v.estado_venta_id=ev.id WHERE v.estado_caja_id=" + idEstadoCaja + " AND v.corte_caja is null";

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
        resultado = new XBigDecimal(resultado.toString().replaceAll("-", ""));
//        System.out.println("REsultado valor xbd" + resultado);
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
        resultado = new XBigDecimal(resultado.toString().replaceAll("-", ""));
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
        String sql = "SELECT p.tipopago,p.monto FROM stpv.pago p INNER JOIN stpv.venta v ON p.id_venta=v.id LEFT JOIN stpv.estado_venta AS ev ON v.estado_venta_id=ev.id WHERE v.estado_caja_id=" + idEstadoCaja + " AND v.cierre_caja is null";

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
        resultado.add(new ValorPagos("Efectivo", ef));
        resultado.add(new ValorPagos("Debito", tdd));
        resultado.add(new ValorPagos("Credito", tdc));
        resultado.add(new ValorPagos("Cestaticket", ctk));
        return resultado;
    }

    public List<ValorPagos> montoscorte(List<Integer> lista) {
        ResultSet rs;
        List<ValorPagos> resultado = new ArrayList();
        List<ValorPagos> valores = new ArrayList();
        double ef = 0;
        double tdd = 0;
        double tdc = 0;
        double ctk = 0;
        for (Integer id : lista) {
            //Ernesto:
            //Anotacion: no esta especificado el alias para "p" y "v" en el siguiente Query
            String sql = "SELECT p.tipopago,p.monto FROM stpv.venta v INNER JOIN stpv.pago p on p.id_venta =v.id WHERE v.id='" + id + "';";
//            System.out.println(sql);
            try {
                postgreSQL.conectar();
                rs = postgreSQL.ejecutarSelect(sql);
                while (rs.next()) {
//                    System.out.println(rs.getString("tipopago")+":"+rs.getDouble("monto"));
                    valores.add(new ValorPagos(rs.getString("tipopago"), rs.getDouble("monto")));
                }
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                postgreSQL.desconectar();
            }
        }
        for (ValorPagos valor : valores) {
            if ("Efectivo".equals(valor.getTipo())) {
                ef = ef + valor.getMontoD();
            }
            if ("Debito".equals(valor.getTipo())) {
                tdd = tdd + valor.getMontoD();
            }
            if ("Credito".equals(valor.getTipo())) {
                tdc = tdc + valor.getMontoD();
            }
            if ("Cestaticket".equals(valor.getTipo())) {
                ctk = ctk + valor.getMontoD();
            }
        }
        resultado.add(new ValorPagos("Efectivo", ef));
        resultado.add(new ValorPagos("Debito", tdd));
        resultado.add(new ValorPagos("Credito", tdc));
        resultado.add(new ValorPagos("Cestaticket", ctk));
        return resultado;
    }

    public ValorPagos getTotalCortesCierre(int idEstadoCaja) {
        ResultSet rs;
//        List<ValorPagos> resultado = new ArrayList();
        double monto = 0;
        //Ernesto:
        //Anotacion: no esta especificado el alias para "cc" en el siguiente Query
        String sql = "SELECT cc.monto_corte FROM stpv.corte_caja cc WHERE cc.estado_caja_id=" + idEstadoCaja + ";";

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sql);
            while (rs.next()) {
                monto = monto + Double.parseDouble(rs.getString("monto_corte"));
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            postgreSQL.desconectar();
        }
        ValorPagos resultado = new ValorPagos("monto", monto);
        return resultado;
    }

    public List<Integer> getListIDVentas(int idEstadoCaja) {
        ResultSet rs;
        List<Integer> resultado = new ArrayList();
        String sql = "SELECT v.id FROM stpv.venta AS v LEFT JOIN stpv.estado_venta AS ev ON v.estado_venta_id=ev.id WHERE v.estado_caja_id=" + idEstadoCaja + " AND v.corte_caja is null";
//        System.out.println(sql);
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sql);
            while (rs.next()) {
//                System.out.println(rs.getInt("id"));
                resultado.add(rs.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            postgreSQL.desconectar();
        }
//        System.out.println(resultado);
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

        sqlQuery.append("SELECT limitedeventaporpersona FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("producto"))
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
    public int crearCliente(String nombre_persona, String apellido_persona, char tipo_persona, String numero_identificacion_persona, String direccion_persona, String telefono_persona, String email_persona) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        
        crearPersona(nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, telefono_persona, email_persona, direccion_persona);

        resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "persona");

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

        int cantidadTelefonos = ejecutarManipulacionDeDatosSimple(sqlQuery.toString());
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = cantidadTelefonos + i;
            sqlQuery = new StringBuilder();
            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("stpv")).append(".")
                    .append(mapTabla.get("cliente__telefono"))
                    .append("(cliente_id,telefono_id) VALUES ")
                    .append("('").append(idCliente).append("',")
                    .append("'").append(resultado[i]).append("');");
            int idTabla = ejecutarManipulacionDeDatosSimple(sqlQuery.toString());
        }
        return resultado;
    }*/

    /**
     * Realiza un INSERT a la tabla venta-
     *
     * @param idCliente
     * @param idEstadoCaja
     * @return
     */
    public int crearVenta(int idCliente, int idEstadoCaja) {
        ResultSet rs;
        Date date = new java.util.Date();
        StringBuilder sqlQuery = new StringBuilder();
        int resultado = -1;

        sqlQuery.append("SELECT id_venta FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" WHERE id_cliente=").append(idCliente)
                .append(" AND id_estado_venta in (").append(EstadoVenta.EnProceso)
                .append(",").append(EstadoVenta.Pausada).append(");");
        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                resultado = rs.getInt("id_venta");
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
                    .append(mapTabla.get("venta"))
                    .append(" SET estado_venta=").append(idEstadoCaja)
                    .append(" WHERE id=").append(resultado)
                    .append(";");
        } else {
            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("venta"))
                    .append("(id_cliente, estado_venta, fecha_venta) VALUES (")
                    .append(idCliente).append(", ")
                    .append(idEstadoCaja).append(", ")
                    .append(EstadoVenta.EnProceso).append(", ")
                    .append("'").append(new Timestamp(date.getTime()))
                    .append("');");
        }
        try {
            postgreSQL.conectar();
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "venta");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        return resultado;
    }

    public String numeroFactura(int idCliente, int idEstadoCaja) {
        ResultSet res;
        StringBuilder sqlQuery = new StringBuilder();
        String resultado = "";
        sqlQuery.append("SELECT * FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta"))
                .append(" WHERE id_cliente=").append(idCliente)
                .append(" AND (estado_venta = ").append(EstadoVenta.EnProceso)
                .append(" OR estado_venta = ").append(EstadoVenta.Pausada).append(");");
        try {
            //System.out.println(sqlQuery.toString());
            postgreSQL.conectar();
            res = postgreSQL.getSentencia().executeQuery(sqlQuery.toString());
            if (res.next()) {
                resultado = res.getString("codigo_factura");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        while (resultado.length() < 10) {
            resultado = "0" + resultado;
        }
        return resultado;
    }

    /**
     * Realiza un UPDATE a la tabla producto-
     *
     * @param codigo
     * @param cantidad
     * @return
     * @throws java.sql.SQLException
     */
    public int descontarCantidad(String codigo, double cantidad) throws SQLException {

        ResultSet rs;
        int resultado = -1;
        double can = Double.parseDouble((String) PuntoVenta.Ventanas.Venta.txtCantidad.getText());

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
    }

    /**
     * Realiza un UPDATE a la tabla producto-
     *
     * @param codigo
     * @param cantidad
     * @return
     * @throws java.sql.SQLException
     */
    //Ernesto:
    //Este metodo es igual al anterior con la diferencia que este Query no emplea
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
     * @param idEstadoCaja EstadoCaja a la que se aplicará el corte.
     * @param monto Monto total del corte
     * @param excedente
     * @param restante
     * @return
     */
    public int crearCorteCaja(int idEstadoCaja, XBigDecimal monto, XBigDecimal excedente, XBigDecimal restante) {
        Date date = new java.util.Date();
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;

        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("corte_caja"))
                .append("(id_estado_caja, monto_corte, excedente_caja, restante_caja, fecha_corte) VALUES (")
                .append(idEstadoCaja).append(", ")
                .append(monto.setScale(2, RoundingMode.UNNECESSARY).toString()).append(", ")
                .append(excedente.setScale(2, RoundingMode.UNNECESSARY).toString()).append(", ")
                .append(restante.setScale(2, RoundingMode.UNNECESSARY).toString()).append(", ")
                .append("'").append(new Timestamp(date.getTime()))
                .append("');");

        resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "corte_caja");
        return resultado;
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

        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("desglose_caja"))
                .append("(id_corte_caja, monto_desglose_caja,tipo_pago_desglose) VALUES (")
                .append(idCorteCaja).append(", ")
                .append(monto).append(",' ")
                .append(tipo).append("');");
        resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "desglose_caja");
  
        return resultado;
    }

    public void ActualizarCorteEnVenta(int idEstadoCaja) {
        //Ernesto:
        //Agregar alias para "v"
        String sql = "UPDATE spve.venta v SET corte_caja = true WHERE v.id_estado_caja=" + idEstadoCaja;
//        System.out.println(sql);
        try {
            postgreSQL.conectar();
            postgreSQL.ejecutarSelect(sql);
//            System.out.println(sql);
        } catch (Exception e) {
//            System.out.println("hola");
        } finally {
            postgreSQL.desconectar();
        }
    }

    public void ActualizarCierreEnVenta(int idEstadoCaja) {
        String sql = "UPDATE spve.venta v SET cierre_caja = true WHERE v.id_estado_caja=" + idEstadoCaja;
//        System.out.println(sql);
        try {
            postgreSQL.conectar();
            postgreSQL.ejecutarSelect(sql);
//            System.out.println(sql);
        } catch (Exception e) {
//            System.out.println("hola");
        } finally {
            postgreSQL.desconectar();
        }
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
    public int crearCierreCaja(int idCaja, int idMoneda, int cantidadMoneda, XBigDecimal monto) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;

        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("cierre_caja"))//Esta tabla no existe
                .append("(id_corte_caja, monto_corte, fecha_corte) VALUES (")
                .append(idCaja).append(", ")
                .append(idMoneda).append(", ")
                .append(cantidadMoneda).append(", ")
                .append(monto).append(");");
        resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "cierre_caja");
       
        return resultado;
    }

    /**
     * Registra un pago en la base de datos. ATENCION: Este pago no se asocia a
     * ninguna otra tabla, debe hacerse manualmente. Ver crearPagoVenta();
     *
     * @param monto
     * @param idTipoPago
     * @return El id del pago creado.
     */
    public int crearPago(XBigDecimal monto, int idTipoPago) {
        Date date = new java.util.Date();
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;

        sqlQuery.append("INSERT INTO ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("pago"))
                .append("(monto_pago, id_tipo_pago, fecha_pago) VALUES (")
                .append(monto.toString()).append(", ")
                .append(idTipoPago).append(", '")
                .append(new Timestamp(date.getTime())).append("');");
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "pago");
        return resultado;
    }

    /**
     * Método para asociar un pago a una venta.
     *
     * @param idPago
     * @param idVenta
     * @return
     */
    public int asociarPagoVenta(int idPago, int idVenta) {
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
        resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "venta_producto");
        return resultado;
    }

    /**
     * Método para crear un
     *
     * @param idVenta
     * @param idProducto
     * @param cantidadProducto
     * @deprecated
     * @return
     */
    public int incluirProductoEnVenta(int idVenta, int idProducto, int cantidadProducto) {
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
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(),"venta_producto");
        
    }
        return resultado;
    }

    /**
     * Método para incluir un producto en una venta. Si existe un producto del
     * mismo idProducto en la la venta, se hace un UPDATE de la tabla. En caso
     * contrario se hace un INSERT
     *
     * @param idVenta
     * @param codigoProducto
     * @param cantidadProducto
     * @return
     */
    public int incluirProductoEnVenta(int idVenta, String codigoProducto, double cantidadProducto) {
        StringBuilder sqlQuery = new StringBuilder();
        ResultSet rs;
        double cantidadAnterior = 0.00;
        int resultado;
        int idProducto = -1;

        sqlQuery.append("SELECT vp.producto_id AS idProducto, vp.cantidad_producto AS cantidad FROM ")
                .append(mapSchema.get("stpv")).append(".")
                .append(mapTabla.get("venta__producto")).append(" AS vp ")
                .append(" LEFT JOIN ")
                .append(mapSchema.get("inventario")).append(".")
                .append(mapTabla.get("producto")).append(" AS p ")
                .append(" ON vp.producto_id=p.id")
                .append(" WHERE venta_id=").append(idVenta)
                .append(" AND ");
        try {
//            int id = Integer.parseInt(codigoProducto);
            //sqlQuery.append("(p.id='").append(codigoProducto).append("'");
            sqlQuery.append("p.codigo_barra='").append(codigoProducto).append("'");
//        } catch (NumberFormatException e) {
//            sqlQuery.append("p.codigo_barra='").append(codigoProducto).append("'");
        } finally {
            sqlQuery.append(";");
        }

        try {
            postgreSQL.conectar();
            rs = postgreSQL.ejecutarSelect(sqlQuery.toString());
            if (rs.next()) {
                idProducto = rs.getInt("idProducto");
                cantidadAnterior = rs.getDouble("cantidad");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }

        sqlQuery = new StringBuilder();
        if (idProducto > 0) {
            sqlQuery.append("UPDATE ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("venta_producto"))
                    .append(" SET cantidad_producto=").append(cantidadProducto + cantidadAnterior)
                    .append(" WHERE venta_id=").append(idVenta)
                    .append(" AND producto_id=").append(idProducto)
                    .append(";");
        } else {
            StringBuilder queryID = new StringBuilder();
            queryID.append("SELECT id_producto FROM ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("producto"))
                    .append(" WHERE codigo_venta_producto='").append(codigoProducto).append("'");

            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("spve")).append(".")
                    .append(mapTabla.get("venta_producto"))
                    .append("(id_producto, id_venta, cantidad_producto) VALUES ((")
                    .append(queryID).append("), ")
                    .append(idVenta).append(", ")
                    .append(cantidadProducto).append(");");
        }
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "venta_producto");
      
        return resultado;
    }
        

    /**
     * Elimina un producto incluido en una venta, dado un idVenta y el código de
     * barra del producto. El código de barra es utilizado para sacar el id del
     * producto ya que en la tabla de productos no se guarda su id, sino el
     * codigo de barra.
     *
     * @param idVenta Id de la venta que esté abierta.
     * @param codigoBarra Codigo del producto que se desee eliminar.
     * @return
     */
    public int eliminarProductoEnVenta(int idVenta, String codigoBarra) {
        StringBuilder sqlQuery = new StringBuilder();
        int resultado;
        String idProducto = "(SELECT id_producto FROM spve.producto WHERE codigo_venta_producto='" + codigoBarra + "' LIMIT 1)";
        sqlQuery.append("DELETE FROM ")
                .append(mapSchema.get("spve")).append(".")
                .append(mapTabla.get("venta_producto"))
                .append(" WHERE id_venta=").append(idVenta)
                .append(" AND id_producto=").append(idProducto).append(";");
    
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "venta_producto");
        return resultado;
    }

    /**
     * Hace el INSERT o el UPDATE de la tabla estado_caja.
     *
     * @param idEstadoCaja Id de la tabla estado_caja, en caso de ser 0 se asume
     * que la tabla no existe y realiza un insert
     * @param idCaja Id de la caja
     * @param idEmpleado Id de tabla del empleado.
     * @param monto Si es un INSERT, se toma como monto inicial. Si es un
     * UPDATE, se toma como monto final
     * @return id del estado_caja resultado del INSERT o del UPDATE.
     */
    public int setEstadoCaja(int idEstadoCaja, int idCaja, int idEmpleado, String monto) {
        Date date = new java.util.Date();
        int resultado = -1;
        StringBuilder sqlQuery = new StringBuilder();
        if (idEstadoCaja <= 0) {
            sqlQuery.append("INSERT INTO ")
                    .append(mapSchema.get("stpv")).append(".")
                    .append(mapTabla.get("estado_caja"))
                    .append("(caja_id, empleado_id, fecha_apertura, monto_apertura) VALUES (")
                    .append(idCaja).append(", ")
                    .append(idEmpleado).append(", '")
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
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "estado_caja");
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

      
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "corte_caja");
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

       
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "venta");
        return resultado;
    }

    public void descontarcantidad(List<ArticuloDescontar> ad) {
        ResultSet result;
        int canti = 0;
        for (ArticuloDescontar articulo : ad) {
            String sql1 = "SELECT * FROM  inventario.producto WHERE codigo_barra = '" + articulo.getCodigo_barra() + "';";
            try {
                postgreSQL.conectar();
                result = postgreSQL.getSentencia().executeQuery(sql1);
                if (result.next()) {
                    canti = result.getInt("cantidad");
                }
                String sql2 = "UPDATE inventario.producto set cantidad=" + (canti - articulo.getCantidad()) + " WHERE codigo_barra='" + articulo.getCodigo_barra() + "';";
                postgreSQL.getSentencia().executeQuery(sql2);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ObjetoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                postgreSQL.desconectar();
            }
        }
    }

//    public String GetNombreEmp(int id){
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
    public boolean consultastock(String codp, String cantcomp) {
        ResultSet result;
        int cantidad = 0;
        int stock = 0;
        String sql1 = "SELECT * FROM  inventario.producto WHERE codigo_barra = '" + codp + "';";

        try {
            postgreSQL.conectar();
            System.out.println(sql1);
            result = postgreSQL.getSentencia().executeQuery(sql1);

            if (result.next()) {
                stock = result.getInt("stockminimo");
                cantidad = result.getInt("cantidad");
            }
            if ((cantidad - stock) < Integer.parseInt(cantcomp)) {
                return false;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ObjetoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    //Ernesto:
    //Campo "iva" fue reemplazado por "impuesto"
    public void actualizaiva(int id, String iva, XBigDecimal monto, double pagoconinva, double pagosiniva, String totalpag, String cambio, int idemp) {
        SimpleDateFormat sdf = new SimpleDateFormat("Y-MM-dd hh:mm a");
        String fecha = sdf.format(new Date());
        String sql = "UPDATE stpv.venta SET iva ='" + iva + "',total='" + monto + "',fecha_hora='" + fecha + "',total_no_exento='" + pagoconinva + "',total_exento='" + pagosiniva + "',totalpag='" + totalpag + "',cambio='" + cambio + "',empleado_id='" + idemp + "' WHERE id=" + id + ";";
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
        for (ValorPagos vp : valor) {
            sql = "INSERT into stpv.pago (id_venta,tipopago,monto,fecha_hora) VALUES(" + idventa + ",'" + vp.getTipo() + "','" + vp.getMonto() + "','" + vp.getFecha() + "');";
            System.out.println(sql);
            try {
                postgreSQL.conectar();
                postgreSQL.getSentencia().executeQuery(sql);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                postgreSQL.desconectar();
            }
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
      
            resultado = ejecutarManipulacionDeDatosSimple(sqlQuery.toString(), "venta");
        return resultado;
    }

    /**
     * Muestra el nombre del empleado en la caja
     *
     * @param cedula cedula del empleado que se mostrara.
     * @param clave 
     * @return
     */
    public int setEmpleadoCaja(String cedula, char[] clave) {
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
            char[] pass = PuntoVenta.Ventanas.bloqueo2.pass.getPassword();
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
     * cada valor y añade el prefijo a cada uno. Ejemplo: <code> culumnas = {"nombre", "apellido"}; prefijo = "p."; query += "p.nombre, p.apellido,";
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

    private int ejecutarManipulacionDeDatosSimple(String query, String tabla) {
        /* Konstanza: el -1 está de más, 
        ya que en caso de error la función 'postgreSQL.ejecutarManipulacionDeDatosSimple(query)' retorna -1*/
        int resultado = -1;
        
        try {
            postgreSQL.conectar();
            resultado = postgreSQL.ejecutarManipulacionDeDatosSimple(query, tabla);
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
            String sql = "SELECT * FROM stpv.cliente WHERE cedula='" + cedula + "';";
            result = postgreSQL.ejecutarSelect(sql);
            if (result.next()) {
                direccion = result.getString("direccion");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postgreSQL.desconectar();
        }
        return direccion;
    }

    public List<reporte1> reimprimirfac(String codigo) {
        List lista = new ArrayList();
        Empresa emp;
        ResultSet rs;
        emp = datosEmpresas();//Ernesto: /*REVISAR QUERY Faltan asignaciones de alias*/
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

        try {
            postgreSQL.conectar();
            rs = postgreSQL.getSentencia().executeQuery(sql);
            while (rs.next()) {
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
                    PuntoVenta.reporte1 rp = new PuntoVenta.reporte1(rs.getString("cantidad_producto"), descrip + " (E)", rs.getString("pvp"), pagado, rs.getString("total"), rs.getString("nombre"), rs.getString("cedula"), rs.getString("direccion"), codigofac, rs.getString("totalpag"), rs.getString("tipopago"), emp.getRif(), emp.getNombre(), emp.getDireccion(), emp.getTelefono(), emp.getMoneda(), rs.getString("total_exento"), rs.getString("total_no_exento"), rs.getString("iva"), rs.getString("cambio"), rs.getString("nombE"));
                    lista.add(rp);
                } else {
                    PuntoVenta.reporte1 rp = new PuntoVenta.reporte1(rs.getString("cantidad_producto"), descrip, rs.getString("pvp"), pagado, rs.getString("total"), rs.getString("nombre"), rs.getString("cedula"), rs.getString("direccion"), codigofac, rs.getString("totalpag"), rs.getString("tipopago"), emp.getRif(), emp.getNombre(), emp.getDireccion(), emp.getTelefono(), emp.getMoneda(), rs.getString("total_exento"), rs.getString("total_no_exento"), rs.getString("iva"), rs.getString("cambio"), rs.getString("nombE"));
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

    public void crearCierre(String monto_fisico, String monto_sistema, String empleado, String fecha) {
        String sql = "INSERT INTO stpv.cierre_caja(monto_fisico,monto_sistema,empleado,fecha) "
                + "VALUES('" + monto_fisico + "','" + monto_sistema + "','" + empleado + "','" + fecha + "')";
        System.out.println(sql);
        try {
            postgreSQL.conectar();
            postgreSQL.getSentencia().execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ObjetoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            postgreSQL.desconectar();
        }

    }

}
