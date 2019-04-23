package PuntoVenta.Ventanas;

import PuntoVenta.BaseDatos.Pais;
import PuntoVenta.Inicio.MenuPrincipal;
//import static PuntoVenta.Ventanas.LogIn.jpwClave;
//import static PuntoVenta.Ventanas.bloqueo2.pass;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Bloqueo extends javax.swing.JFrame {
//    static Object txtNombreUsuario;
    
    public MenuPrincipal menuPrincipal;

//    String contrasena = "";
//    int i =0;
    
    public Bloqueo(MenuPrincipal menuPrincipal) {
        initComponents();
        this.menuPrincipal = menuPrincipal;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/PuntoVenta/Iconos/acerca.png")));
//        txtCedula.setText("");
        this.setTitle("Saphiro - Bloqueo de caja");
        this.setVisible(true);
        identificarPais();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnPrincipal1 = new javax.swing.JPanel();
        cmbTipoIdentificacion = new javax.swing.JComboBox<>();
        lblDescripcion = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        lblContrasena = new javax.swing.JLabel();
        jpwContrasena = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblLogoInverdata = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setTitle("Saphiro - Punto de Venta\n"); // NOI18N

        jpnPrincipal1.setBackground(new java.awt.Color(255, 255, 255));
        jpnPrincipal1.setName("jpnPrincipal1"); // NOI18N
        jpnPrincipal1.setPreferredSize(new java.awt.Dimension(500, 424));

        cmbTipoIdentificacion.setBackground(new java.awt.Color(32, 182, 155));
        cmbTipoIdentificacion.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cmbTipoIdentificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "E" }));
        cmbTipoIdentificacion.setBorder(null);
        cmbTipoIdentificacion.setName("cmbTipoIdentificacion"); // NOI18N
        cmbTipoIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoIdentificacionActionPerformed(evt);
            }
        });

        lblDescripcion.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(28, 90, 125));
        lblDescripcion.setText("    Por favor ingresa tu cédula y contraseña");
        lblDescripcion.setName("lblDescripcion"); // NOI18N

        txtCedula.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        txtCedula.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtCedula.setName("txtCedula"); // NOI18N
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });

        lblContrasena.setBackground(new java.awt.Color(32, 182, 155));
        lblContrasena.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(255, 255, 255));
        lblContrasena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/10-bloqueo.png"))); // NOI18N
        lblContrasena.setAutoscrolls(true);
        lblContrasena.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblContrasena.setName("lblContrasena"); // NOI18N

        jpwContrasena.setForeground(new java.awt.Color(32, 182, 155));
        jpwContrasena.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        jpwContrasena.setName("jpwContrasena"); // NOI18N
        jpwContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpwContrasenaActionPerformed(evt);
            }
        });

        btnEntrar.setBackground(new java.awt.Color(32, 182, 155));
        btnEntrar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnEntrar.setContentAreaFilled(true);
        btnEntrar.setName("btnEntrar"); // NOI18N
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(32, 182, 155));
        btnSalir.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblLogoInverdata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/logo_verde_p_v.png"))); // NOI18N
        lblLogoInverdata.setName("lblLogoInverdata"); // NOI18N

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/logo_saphiro-verde-pequeño.png"))); // NOI18N
        lblLogo.setName("lblLogo"); // NOI18N

        lblFondo.setBackground(new java.awt.Color(255, 255, 255));
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/login 500x424.jpg"))); // NOI18N
        lblFondo.setName("lblFondo"); // NOI18N

        javax.swing.GroupLayout jpnPrincipal1Layout = new javax.swing.GroupLayout(jpnPrincipal1);
        jpnPrincipal1.setLayout(jpnPrincipal1Layout);
        jpnPrincipal1Layout.setHorizontalGroup(
            jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipal1Layout.createSequentialGroup()
                .addGroup(jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnPrincipal1Layout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(lblLogoInverdata, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnPrincipal1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipal1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnPrincipal1Layout.createSequentialGroup()
                            .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipal1Layout.createSequentialGroup()
                                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(152, 152, 152))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipal1Layout.createSequentialGroup()
                                .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jpwContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipal1Layout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173))))
            .addGroup(jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnPrincipal1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblFondo)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jpnPrincipal1Layout.setVerticalGroup(
            jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipal1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpwContrasena, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(lblLogoInverdata, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jpnPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnPrincipal1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblFondo)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        getContentPane().add(jpnPrincipal1, java.awt.BorderLayout.PAGE_START);

        setSize(new java.awt.Dimension(517, 461));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoIdentificacionActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        jpwContrasena.requestFocus();
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void jpwContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpwContrasenaActionPerformed
        ingresarSistema();
    }//GEN-LAST:event_jpwContrasenaActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        ingresarSistema();
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        javax.swing.JOptionPane mensajedeerror = new javax.swing.JOptionPane();
        int dialogo_salir = JOptionPane.showConfirmDialog(this, "Desea salir del Sistema ahora", "Saphiro - Salir", JOptionPane.YES_NO_OPTION);

        if (dialogo_salir == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            this.setVisible(true);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSalir;
    public static javax.swing.JComboBox<String> cmbTipoIdentificacion;
    private javax.swing.JPanel jpnPrincipal1;
    private javax.swing.JPasswordField jpwContrasena;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoInverdata;
    public static javax.swing.JTextField txtCedula;
    // End of variables declaration//GEN-END:variables

    public void identificarPais(){
        Pais p = menuPrincipal.getOBD().getDatosPais(" WHERE activo = true");

        //asignando identificacion al combo.
        cmbTipoIdentificacion.insertItemAt(p.getNacionalidad(), 0);
        cmbTipoIdentificacion.setSelectedIndex(0);
    }    
    
    /**
     * Método sustituto para entrar al sistema, en vez de utilizar las variables
     * conf y ctrl utiliza las variable del menuPrincipal llamadas configuracion
     * y obd.
     *
     * 1.- No se deben hacer validaciones de licencia. Esta clase solo manipula
     * el acceso de un determinado usuario al sistema.
     *
     */
    
    public void cerrarbloq(){
        this.dispose();
    }
  
    public void ingresarSistema() {
        String cedula = txtCedula.getText();
        char[] arrayPassword = jpwContrasena.getPassword();
        String tipo = cmbTipoIdentificacion.getSelectedItem().toString();
        
        if (cedula.isEmpty()) {
            Utilidades.Sonidos.beep();
            JOptionPane.showMessageDialog(this, " Para DESBLOQUEAR, debe ingresar su cedula/usuario");
            txtCedula.requestFocus();
            return;
        }
  
        if (arrayPassword.length <= 0) {
            this.menuPrincipal.habilitar();
            Utilidades.Sonidos.beep();
            jpwContrasena.requestFocus();
            JOptionPane.showMessageDialog(this, "Para DESBLOQUEAR, ingrese su clave");
            return;
        }
        
        int idEmpleado = menuPrincipal.getOBD().autenticarEmpleado2(cedula, arrayPassword, tipo);

        if (idEmpleado != -1) {
            HashMap<String, String> mapEmpleado = menuPrincipal.getOBD().getMapEmpleado(idEmpleado);
            menuPrincipal.setEmpleado(new PuntoVenta.Modelos.ModeloEmpleado(mapEmpleado));
            menuPrincipal.setExtendedState(MAXIMIZED_BOTH);
            menuPrincipal.setVisible(true);
            menuPrincipal.deshabilitar();
            this.dispose();
        } else {
            this.menuPrincipal.habilitar();
            JOptionPane.showMessageDialog(this, "Ingrese su IDENTIFICACION y Clave correctamente para DESBLOQUEAR");
            Utilidades.Sonidos.beep();
            txtCedula.setText("");
            jpwContrasena.setText("");
            txtCedula.requestFocus();
        }
    }
    
    public void setMaximum(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

  
    
    

