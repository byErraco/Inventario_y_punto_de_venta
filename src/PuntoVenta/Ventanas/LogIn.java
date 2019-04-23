package PuntoVenta.Ventanas;

import PuntoVenta.BaseDatos.Pais;
import PuntoVenta.Inicio.MenuPrincipal;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class LogIn extends javax.swing.JFrame {
    
    public MenuPrincipal menuPrincipal;
    public static int contador = 0;
    public static int idEmpleado;
    
    public LogIn(MenuPrincipal menuPrincipal) {
        initComponents();
        contador++;
        this.menuPrincipal = menuPrincipal;

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/PuntoVenta/Iconos/acerca.png")));
        
      //  entrarProgramador();
        identificarPais();
        this.setVisible(true);
        txtCedula.requestFocus();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup14 = new javax.swing.ButtonGroup();
        lblCabecera = new javax.swing.JLabel();
        jpnPrincipal = new javax.swing.JPanel();
        lblDescripcion = new javax.swing.JLabel();
        cmbTipoIdentificacion = new javax.swing.JComboBox<>();
        txtCedula = new javax.swing.JTextField();
        lblContrasena = new javax.swing.JLabel();
        jpwClave = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();
        lblLogoInverdata = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();

        lblCabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCabecera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/Banner_Saphiro_Punto_de_Venta_Fondo_Blanco.jpg"))); // NOI18N
        lblCabecera.setName("lblCabecera"); // NOI18N

        setTitle("Saphiro - Punto de Venta\n"); // NOI18N

        jpnPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        jpnPrincipal.setName("jpnPrincipal"); // NOI18N

        lblDescripcion.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(28, 90, 125));
        lblDescripcion.setText("    Por favor ingresa tu cédula y contraseña");
        lblDescripcion.setName("lblDescripcion"); // NOI18N

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

        jpwClave.setForeground(new java.awt.Color(32, 182, 155));
        jpwClave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        jpwClave.setName("jpwClave"); // NOI18N
        jpwClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpwClaveActionPerformed(evt);
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

        lblFondo.setBackground(new java.awt.Color(255, 255, 255));
        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/login 500x424.jpg"))); // NOI18N
        lblFondo.setName("lblFondo"); // NOI18N

        lblLogoInverdata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/logo_verde_p_v.png"))); // NOI18N
        lblLogoInverdata.setName("lblLogoInverdata"); // NOI18N

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/logo_saphiro-verde-pequeño.png"))); // NOI18N
        lblLogo.setName("lblLogo"); // NOI18N

        javax.swing.GroupLayout jpnPrincipalLayout = new javax.swing.GroupLayout(jpnPrincipal);
        jpnPrincipal.setLayout(jpnPrincipalLayout);
        jpnPrincipalLayout.setHorizontalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpwClave, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipalLayout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipalLayout.createSequentialGroup()
                        .addComponent(lblLogoInverdata, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70))))
            .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(lblFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
        );
        jpnPrincipalLayout.setVerticalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpwClave, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(lblLogoInverdata, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnPrincipalLayout.createSequentialGroup()
                    .addComponent(lblFondo)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(516, 462));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        javax.swing.JOptionPane mensajedeerror = new javax.swing.JOptionPane();
        int dialogo_salir = JOptionPane.showConfirmDialog(this, "¿Desea salir del sistema ahora?", "Saphiro - Salir", JOptionPane.YES_NO_OPTION);

        if (dialogo_salir == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            this.setVisible(true);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        ingresarSistema();
    }//GEN-LAST:event_btnEntrarActionPerformed
        
    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        jpwClave.requestFocus();
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void cmbTipoIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoIdentificacionActionPerformed

    private void jpwClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpwClaveActionPerformed
        ingresarSistema();
    }//GEN-LAST:event_jpwClaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup14;
    public static javax.swing.JComboBox<String> cmbTipoIdentificacion;
    private javax.swing.JPanel jpnPrincipal;
    private javax.swing.JPasswordField jpwClave;
    private javax.swing.JLabel lblCabecera;
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
    public void ingresarSistema() {
        
        String cedula = txtCedula.getText();
        char[] arrayPassword = jpwClave.getPassword();
        String tipo = cmbTipoIdentificacion.getSelectedItem().toString();
        
        if (cedula.isEmpty()) {
            Utilidades.Sonidos.beep();
             JOptionPane.showMessageDialog(this, " Para INICIAR, debe ingresar su IDENTIFICACIÓN");
            txtCedula.requestFocus();
            return;
        }
  
        if (arrayPassword.length <= 0) {
            Utilidades.Sonidos.beep();
            jpwClave.requestFocus();
            JOptionPane.showMessageDialog(this, "Ahora, ingrese su clave");
            return;
        }
        
        idEmpleado = menuPrincipal.getOBD().autenticarEmpleado2(cedula, arrayPassword, tipo);
        
        if (idEmpleado != -1) {
            HashMap<String, String> mapEmpleado = menuPrincipal.getOBD().getMapEmpleado(idEmpleado);
            menuPrincipal.setEmpleado(new PuntoVenta.Modelos.ModeloEmpleado(mapEmpleado));
            menuPrincipal.setExtendedState(MAXIMIZED_BOTH);
            menuPrincipal.setVisible(true);
            menuPrincipal.sincronizar();
            this.dispose();
        } else {
            Utilidades.Sonidos.beep();
            JOptionPane.showMessageDialog(this, "Para INICIAR, Ingrese su IDENTIFICACION y Clave correctamente");
            txtCedula.setText("");
            jpwClave.setText("");
            txtCedula.requestFocus();
        }
    }

}
