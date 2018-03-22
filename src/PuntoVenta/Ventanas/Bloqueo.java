package PuntoVenta.Ventanas;

import PuntoVenta.Inicio.MenuPrincipal;
import static PuntoVenta.Ventanas.bloqueo2.pass;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Bloqueo extends javax.swing.JFrame {
    static Object txtNombreUsuario;
    

    public MenuPrincipal menuPrincipal;

    String contrasena = "";
    int vali=0;
    public Bloqueo(MenuPrincipal menuPrincipal) {
        initComponents();
        this.menuPrincipal = menuPrincipal;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/PuntoVenta/Iconos/acerca.png")));
        
       // txtCedula.setText("");
        this.setTitle("Saphiro - Bloqueo de caja");
        ingresarSistema();
       this.setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup14 = new javax.swing.ButtonGroup();
        jpnPrincipal = new javax.swing.JPanel();
        lblCabecera = new javax.swing.JLabel();
        jpnCamposLogin = new javax.swing.JPanel();
        jpwContrasena = new javax.swing.JPasswordField();
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

        jpwContrasena.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jpwContrasena.setForeground(new java.awt.Color(204, 0, 51));
        jpwContrasena.setName("jpwContrasena"); // NOI18N
        jpwContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpwContrasenaActionPerformed(evt);
            }
        });
        jpwContrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpwContrasenaKeyPressed(evt);
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
        txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCedulaKeyPressed(evt);
            }
        });

        jComboBox1.setName("jComboBox1"); // NOI18N

        javax.swing.GroupLayout jpnCamposLoginLayout = new javax.swing.GroupLayout(jpnCamposLogin);
        jpnCamposLogin.setLayout(jpnCamposLoginLayout);
        jpnCamposLoginLayout.setHorizontalGroup(
            jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpwContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCamposLoginLayout.setVerticalGroup(
            jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jpwContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        btnIngresar.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnIngresar.setText("INGRESAR");
        btnIngresar.setContentAreaFilled(true);
        btnIngresar.setName("btnIngresar"); // NOI18N
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngresarMouseClicked(evt);
            }
        });
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        btnIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnIngresarKeyPressed(evt);
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
                .addGap(69, 69, 69)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnCamposLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnPrincipalLayout.setVerticalGroup(
            jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(jpnCamposLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpnPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        getContentPane().add(jpnPrincipal, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(487, 383));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        javax.swing.JOptionPane mensajedeerror = new javax.swing.JOptionPane();
        int g = JOptionPane.showConfirmDialog(this, "Desea salir del sistema ahora", "Saphiro - Salir", JOptionPane.YES_NO_OPTION);

        if (g == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            this.setVisible(true);
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnIngresarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ingresarSistema();
        }
    }//GEN-LAST:event_btnIngresarKeyPressed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
          cerrarbloq();
      //  ingresarSistema();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseClicked
        ingresarSistema();        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresarMouseClicked

    private void txtCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jpwContrasena.requestFocus();
        }
    }//GEN-LAST:event_txtCedulaKeyPressed

    private void jpwContrasenaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpwContrasenaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ingresarSistema();
        }
    }//GEN-LAST:event_jpwContrasenaKeyPressed

    private void jpwContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpwContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpwContrasenaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jpnCamposLogin;
    private javax.swing.JPanel jpnPrincipal;
    public static javax.swing.JPasswordField jpwContrasena;
    private javax.swing.JLabel lblCabecera;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblUsuario;
    private static javax.swing.JTextField txtCedula;
    // End of variables declaration//GEN-END:variables

    /**
     * Método para asignar usuario y clave automatico.
     */
    private void entrarProgramador() {
        String nombre = "inverdata@inverdata.com.ve";
       String password = "1234";
        txtCedula.setText(nombre);
        jpwContrasena.setText(password);
    }

    // KONSTANZA NUEVA FUNCION
    private void entrarProgramador2() {
        txtCedula.setText("1");
        jpwContrasena.setText("1");
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
         if(PuntoVenta.Inicio.MenuPrincipal.btnVentas.isEnabled()){
        this.dispose(); 
        }
        
    }
    public void ingresarSistema() {
        entrarProgramador2();
       // entrarProgramador();
        
          String cedula = txtCedula.getText();
        char[] arrayPassword = jpwContrasena.getPassword();

        if (cedula.isEmpty()) {
            Utilidades.Sonidos.beep();
            txtCedula.requestFocus();
            return;
        }
        if (arrayPassword.length <= 0) {
            Utilidades.Sonidos.beep();
           jpwContrasena.requestFocus();
            return;
        }
        int idEmpleado = menuPrincipal.getOBD().autenticarEmpleado2(cedula, arrayPassword);
             if (idEmpleado != -1) {
            HashMap<String, String> mapEmpleado = menuPrincipal.getOBD().getMapEmpleado(idEmpleado);
            menuPrincipal.setExtendedState(MAXIMIZED_BOTH);
            menuPrincipal.setVisible(true);
            menuPrincipal.setEmpleado(new PuntoVenta.Modelos.ModeloEmpleado(mapEmpleado));
           this.dispose();
      PuntoVenta.Inicio.MenuPrincipal.btnCaja.setEnabled(false);
      PuntoVenta.Inicio.MenuPrincipal.btnVentas.setEnabled(false);
      PuntoVenta.Inicio.MenuPrincipal.btnAyuda.setEnabled(false);
      PuntoVenta.Inicio.MenuPrincipal.btnAcerca.setEnabled(false);
      PuntoVenta.Inicio.MenuPrincipal.btnAdmin.setEnabled(false);
      PuntoVenta.Inicio.MenuPrincipal.btnMovimientos.setEnabled(false);
      PuntoVenta.Inicio.MenuPrincipal.btnProductos.setEnabled(false);
        } else {
            Utilidades.Sonidos.beep();
            txtCedula.setText("");
            jpwContrasena.setText("");
            txtCedula.requestFocus();
            return;
        }
        
        
       /* String cedula = txtCedula.getText();
        char[] arrayPassword = jpwContrasena.getPassword();
        System.out.println(cedula+"  "+arrayPassword);
        if (cedula.isEmpty()) {
            Utilidades.Sonidos.beep();
            txtCedula.requestFocus();
            return;
        }
        if (arrayPassword.length <= 0) {
            Utilidades.Sonidos.beep();
            jpwContrasena.requestFocus();
            return;
        }
        int idEmpleado = menuPrincipal.getOBD().autenticarEmpleado2(cedula, arrayPassword);
              if (idEmpleado != -1) {
            HashMap<String, String> mapEmpleado = menuPrincipal.getOBD().getMapEmpleado(idEmpleado);
            //entrar
            menuPrincipal.setExtendedState(MAXIMIZED_BOTH);
            menuPrincipal.setVisible(true);
            menuPrincipal.setEmpleado(new PuntoVenta.Modelos.ModeloEmpleado(mapEmpleado));
            this.dispose();
             } else {
            Utilidades.Sonidos.beep();
            txtCedula.setText("");
            jpwContrasena.setText("");
            txtCedula.requestFocus();
        */
      
        }

    public void setMaximum(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

  
    
    

