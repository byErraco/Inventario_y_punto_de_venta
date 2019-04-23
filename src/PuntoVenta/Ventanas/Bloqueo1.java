package PuntoVenta.Ventanas;

import PuntoVenta.Inicio.MenuPrincipal;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Bloqueo1 extends javax.swing.JFrame {
    
    public MenuPrincipal menuPrincipal;
  
    public Bloqueo1(MenuPrincipal menuPrincipal) {
        initComponents();
   
        this.menuPrincipal = menuPrincipal;

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/PuntoVenta/Iconos/acerca.png")));
        
      //  entrarProgramador();
        
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
        jComboBox1 = new javax.swing.JComboBox<>();
        btnIngresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Saphiro - Punto de Venta\n"); // NOI18N

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
        lblUsuario.setText("IDENTIFICACIÓN:");
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "V", "J", "E", "P" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        javax.swing.GroupLayout jpnCamposLoginLayout = new javax.swing.GroupLayout(jpnCamposLogin);
        jpnCamposLogin.setLayout(jpnCamposLoginLayout);
        jpnCamposLoginLayout.setHorizontalGroup(
            jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblContrasena)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                    .addComponent(jpwClave)))
        );
        jpnCamposLoginLayout.setVerticalGroup(
            jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpwClave, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        btnIngresar.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnIngresar.setText("INGRESAR");
        btnIngresar.setContentAreaFilled(true);
        btnIngresar.setName("btnIngresar"); // NOI18N
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(204, 204, 204));
        btnSalir.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.setName("btnSalir"); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BLOQUEADO");
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jpnPrincipalLayout = new javax.swing.GroupLayout(jpnPrincipal);
        jpnPrincipal.setLayout(jpnPrincipalLayout);
        jpnPrincipalLayout.setHorizontalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addComponent(lblCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpnCamposLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnPrincipalLayout.createSequentialGroup()
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(153, 153, 153))
        );
        jpnPrincipalLayout.setVerticalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnCamposLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        getContentPane().add(jpnPrincipal, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(487, 407));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        javax.swing.JOptionPane mensajedeerror = new javax.swing.JOptionPane();
        int dialogo_salir = JOptionPane.showConfirmDialog(this, "Desea salir del Sistema ahora", "Saphiro - Salir", JOptionPane.YES_NO_OPTION);

        if (dialogo_salir == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            this.setVisible(true);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        ingresarSistema();
    }//GEN-LAST:event_btnIngresarActionPerformed
        
    private void jpwClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpwClaveActionPerformed
        ingresarSistema();
    }//GEN-LAST:event_jpwClaveActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        jpwClave.requestFocus();
    }//GEN-LAST:event_txtCedulaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpnCamposLogin;
    private javax.swing.JPanel jpnPrincipal;
    public static javax.swing.JPasswordField jpwClave;
    private javax.swing.JLabel lblCabecera;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblUsuario;
    private static javax.swing.JTextField txtCedula;
    // End of variables declaration//GEN-END:variables
    
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
        String tipo = jComboBox1.getSelectedItem().toString();
        
        if (cedula.isEmpty()) {
            
            this.menuPrincipal.habilitar();
            Utilidades.Sonidos.beep();
             JOptionPane.showMessageDialog(this, " Para DESBLOQUEAR, debe ingresar su cedula/usuario");
             
            txtCedula.requestFocus();
            return;
        }
  
        if (arrayPassword.length <= 0) {
            this.menuPrincipal.habilitar();
            Utilidades.Sonidos.beep();
            jpwClave.requestFocus();
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
            jpwClave.setText("");
            txtCedula.requestFocus();
            
        }
    }
}
