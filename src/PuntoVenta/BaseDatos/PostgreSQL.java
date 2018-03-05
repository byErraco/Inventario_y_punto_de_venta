/*
 * Clase utilizada para manjar la base de datos. 
 */
package PuntoVenta.BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQL {

    private Connection conexion;
    private Statement sentencia;
    private final String path;
    private final String login;
    private final String password;
    private boolean estado;

    public PostgreSQL(String path, String login, String password) {
        this.path = path;
        this.login = login;
        this.password = password;
        this.estado = false;
    }

    /**
     * Método para conectar a la base de datos PostgreSQL.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void conectar() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        this.conexion = DriverManager.getConnection(this.path, login, password);
        this.sentencia = this.conexion.createStatement();
        this.setEstado(true);
    }

    /**
     * Método para desconectar la base de datos PostgreSQL.
     *
     * @return Valor equivalente al éxito en cerrar la base de datos. true =
     * cerrado, false = abierta.
     */
    public boolean desconectar() {
        try {
            if (this.conexion == null || this.conexion.isClosed()) {
                this.setEstado(false);
                return true;
            } else {
                this.sentencia.close();
                this.conexion.close();
                this.setEstado(false);
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Método para ejecutar un query en la base de datos. Solo debe utilizarse
     * para ejectuar SELECT.
     *
     * @param sql
     * @return ResulSet con el resultado del SELECon
     * @throws PuntoVenta.BaseDatos.PostgreSQL.XSQLException
     */
    public ResultSet ejecutarSelect(String sql) {
        /* Konstanza:
            - Revisar para que funciona XSQLException
            - Eliminar el if: si no hay conexión debería ir a catch no retornar null
            - El error que se muestra no es el correcto, siempre dice que 'La base de datos no está conectada',
              cuando puede dar otro error como que no haya un campo o una tabla
        */
        System.out.println(sql);
        try {
            if (this.conexion == null || this.conexion.isClosed()) {
                return null; 
            }
            return sentencia.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            this.desconectar();
        }
        
        return null;
    }

    /**
     * Método para ejecutar INSERT, UPDATE o DELETE. Solo se debe pasar una (1)
     * instruccion SQL al método. No se garantiza el funcionamiento si se pasa
     * más de una (1) instruccion SQL. Es synchronized.
     *
     * @param sql
     * @return id del row resultante del INSERT o UPDATE
     * @throws PuntoVenta.BaseDatos.PostgreSQL.XSQLException
     */
    public synchronized int ejecutarManipulacionDeDatosSimple(String sql, String tabla) throws XSQLException {
        int resultado = -1;
        try {
            if (this.conexion == null || this.conexion.isClosed()) {
                throw new XSQLException("La base de datos no está conectada.");
            }
            this.sentencia.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (this.sentencia.getGeneratedKeys().next()) {
                resultado = this.sentencia.getGeneratedKeys().getInt("id_"+tabla);
            }
            this.sentencia.close();
        } catch (SQLException e) {
            e.printStackTrace();
            this.desconectar();
            resultado = -1;
            //throw new XSQLException("La base de datos no está conectada.");
        }
        return resultado;
    }

    /**
     * Método que construye el SELECT para consultar una tabla.
     *
     * @param columnas Nombres de las columnas a consultar. No se debe incluir
     * el id ya que está implicito en la función.
     * @param schema Nombre del scheme en la base de datos. (Solo para
     * postgreSQL)
     * @param tabla Nombre de la tabla en la base de datos.
     * @param where Lista de condiciones a aplicar en el WHERE separadas por
     * coma. Solo deben venir las condiciones, no se debe incluir la palabra
     * WHERE.
     *
     * @return ResulSet con información de la tabla.
     */
    public ResultSet getTabla(String schema, final String tabla, final String columnas[], final String where) {
        if (schema.isEmpty()) {
            schema = "public";
        }
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT ").append(tabla).append(".id");

        for (String columna : columnas) {
            sqlQuery.append(", ");
            sqlQuery.append(tabla).append(".").append(columna);
        }
        sqlQuery.append(" FROM ").append(schema).append(".").append(tabla);
        if (where.isEmpty()) {
            sqlQuery.append(";");
        } else {
            sqlQuery.append(" WHERE ").append(where).append(";");
        }

        try {
            return this.ejecutarSelect(sqlQuery.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the conexion
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Exception para la clase PostgreSQL.
     * 
     * Konstanza: no le veo el uso a esta excepción
     */
    private static class XSQLException extends Exception {

        public XSQLException(String razon) {
            System.out.println("Error "+razon);
        }
    }

    public Statement getSentencia() {
        return sentencia;
    }

    public void setSentencia(Statement sentencia) {
        this.sentencia = sentencia;
    }
    
    
}
