/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.deprecated;

public class Configuracion {

    Licencia Licencia = new Licencia();
    public String id_caja;
    public String desc_caja;
    public String id_empresa;
    public String rif;
    public String empresa;
    public String direccion;
    public String telefono;
    public String correo;
    public String num_copia;
    public String redondeo;
    public String MAC;
    public double ivsa;

    public double getIvsa() {
        return ivsa;
    }

    public Configuracion(String id_caja, String desc_caja, String id_empresa, String rif, String empresa, String direccion, String telefono, String correo, String num_copia,
            String redondeo, double ivsa) {
        //Licencia.getDatosLicencia();
        //System.out.println(Licencia.getMAC());

        this.id_caja = id_caja;
        this.desc_caja = desc_caja;
        this.id_empresa = id_empresa;
        this.rif = rif;
        this.empresa = empresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.num_copia = num_copia;
        this.redondeo = redondeo;
        this.MAC = Licencia.getMAC();
        this.ivsa = ivsa;

    }

    public String getId_caja() {
        return id_caja;
    }

    public void setId_caja(String id_caja) {
        this.id_caja = id_caja;
    }

    public String getDesc_caja() {
        return desc_caja;
    }

    public void setDesc_caja(String desc_caja) {
        this.desc_caja = desc_caja;
    }

    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNum_copia() {
        return num_copia;
    }

    public void setNum_copia(String num_copia) {
        this.num_copia = num_copia;
    }

    public String getRedondeo() {
        return redondeo;
    }

    public void setRedondeo(String redondeo) {
        this.redondeo = redondeo;
    }

}
