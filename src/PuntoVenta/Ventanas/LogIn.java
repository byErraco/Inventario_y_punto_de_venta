package PuntoVenta.Ventanas;

import PuntoVenta.Inicio.MenuPrincipal;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class LogIn extends javax.swing.JFrame {
    
    public MenuPrincipal menuPrincipal;
    
    public LogIn(MenuPrincipal menuPrincipal) {
        initComponents();
   
        this.menuPrincipal = menuPrincipal;

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/PuntoVenta/Iconos/acerca.png")));
        
        entrarProgramador();
        
        this.setVisible(true);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup14 = new javax.swing.ButtonGroup();
        jpnPrincipal = new javax.swing.JPanel();
        lblCabecera = new javax.swing.JLabel();
        jpnCamposLogin = new javax.swing.JPanel();
        jpwClave = new javax.swing.JPasswordField();
        lblUsuario = new javax.swing.JLabel();
        lblContrasena = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        btnEntrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Saphiro - Punto de Venta\n"); // NOI18N
        setResizable(false);

        jpnPrincipal.setBackground(new java.awt.Color(32, 182, 155));
        jpnPrincipal.setName("jpnPrincipal"); // NOI18N

        lblCabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCabecera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/Banner_Saphiro_Punto_de_Venta_Fondo_Blanco.jpg"))); // NOI18N
        lblCabecera.setName("lblCabecera"); // NOI18N

        jpnCamposLogin.setBackground(new java.awt.Color(32, 182, 155));
        jpnCamposLogin.setName("jpnCamposLogin"); // NOI18N

        jpwClave.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jpwClave.setForeground(new java.awt.Color(204, 0, 51));
        jpwClave.setName("jpwClave"); // NOI18N
        jpwClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpwClaveActionPerformed(evt);
            }
        });

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("CEDULA:");
        lblUsuario.setAutoscrolls(true);
        lblUsuario.setFocusable(false);
        lblUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblUsuario.setName("lblUsuario"); // NOI18N

        lblContrasena.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(255, 255, 255));
        lblContrasena.setText("CONTRASEÑA: ");
        lblContrasena.setAutoscrolls(true);
        lblContrasena.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lblContrasena.setName("lblContrasena"); // NOI18N

        txtCedula.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        txtCedula.setName("txtCedula"); // NOI18N
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCamposLoginLayout = new javax.swing.GroupLayout(jpnCamposLogin);
        jpnCamposLogin.setLayout(jpnCamposLoginLayout);
        jpnCamposLoginLayout.setHorizontalGroup(
            jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrasena))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpwClave)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCamposLoginLayout.setVerticalGroup(
            jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpwClave, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnEntrar.setBackground(new java.awt.Color(204, 204, 204));
        btnEntrar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(0, 0, 0));
        btnEntrar.setText("ENTRAR");
        btnEntrar.setContentAreaFilled(true);
        btnEntrar.setName("btnEntrar"); // NOI18N
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(204, 204, 204));
        btnSalir.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 0, 0));
        btnSalir.setText("SALIR");
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnPrincipalLayout = new javax.swing.GroupLayout(jpnPrincipal);
        jpnPrincipal.setLayout(jpnPrincipalLayout);
        jpnPrincipalLayout.setHorizontalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipalLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jpnCamposLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addComponent(lblCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnPrincipalLayout.setVerticalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jpnCamposLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jpnPrincipal, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(487, 348));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // Konstanza: ¿Qué es 'SINAMC'?
        int dialogo = JOptionPane.showConfirmDialog(this, "¿Desea salir del sistema ahora?", "Salir - SINAMC", JOptionPane.YES_NO_OPTION);

        if (dialogo == JOptionPane.YES_OPTION) {
            System.exit(0);
        } 
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        ingresarSistema();
    }//GEN-LAST:event_btnEntrarActionPerformed
        
    private void jpwClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpwClaveActionPerformed
        ingresarSistema();
    }//GEN-LAST:event_jpwClaveActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        jpwClave.requestFocus();
    }//GEN-LAST:event_txtCedulaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.JPanel jpnCamposLogin;
    private javax.swing.JPanel jpnPrincipal;
    public static javax.swing.JPasswordField jpwClave;
    private javax.swing.JLabel lblCabecera;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblUsuario;
    private static javax.swing.JTextField txtCedula;
    // End of variables declaration//GEN-END:variables

    /* Konstanza: nueva función para entrar al sistema. 
       La primera función 'entrarProgramador' no servía, los valores que asignaba a
       las variables no están en la base de datos.
    */
    private void entrarProgramador() {
        txtCedula.setText("admin");
        jpwClave.setText("admin");
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
        
        if (cedula.isEmpty()) {
            Utilidades.Sonidos.beep();
            txtCedula.requestFocus();
            return;
        }
  
        if (arrayPassword.length <= 0) {
            Utilidades.Sonidos.beep();
            jpwClave.requestFocus();
            return;
        }
        
        int idEmpleado = menuPrincipal.getOBD().autenticarEmpleado2(cedula, arrayPassword);
        
        if (idEmpleado != -1) {
            HashMap<String, String> mapEmpleado = menuPrincipal.getOBD().getMapEmpleado(idEmpleado);
            menuPrincipal.setEmpleado(new PuntoVenta.Modelos.ModeloEmpleado(mapEmpleado));
            menuPrincipal.setExtendedState(MAXIMIZED_BOTH);
            menuPrincipal.setVisible(true);
            this.dispose();
        } else {
            Utilidades.Sonidos.beep();
            txtCedula.setText("");
            jpwClave.setText("");
            txtCedula.requestFocus();
        }
    }
}
