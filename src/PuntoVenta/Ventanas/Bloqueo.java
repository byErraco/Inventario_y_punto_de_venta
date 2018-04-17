package PuntoVenta.Ventanas;

import PuntoVenta.Inicio.MenuPrincipal;
import static PuntoVenta.Inicio.MenuPrincipal.btnAcerca;
import static PuntoVenta.Inicio.MenuPrincipal.btnAdmin;
import static PuntoVenta.Inicio.MenuPrincipal.btnAyuda;
import static PuntoVenta.Inicio.MenuPrincipal.btnCaja;
import static PuntoVenta.Inicio.MenuPrincipal.btnFacturas;
import static PuntoVenta.Inicio.MenuPrincipal.btnMovimientos;
import static PuntoVenta.Inicio.MenuPrincipal.btnProductos;
import static PuntoVenta.Inicio.MenuPrincipal.btnVentas;
import static PuntoVenta.Ventanas.LogIn.jpwClave;
//import static PuntoVenta.Ventanas.bloqueo2.pass;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Bloqueo extends javax.swing.JFrame {
   // static Object txtNombreUsuario;
    

    public MenuPrincipal menuPrincipal;

  //  String contrasena = "";
 //   int i =0;
    
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
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCedula))
                    .addComponent(jpwContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCamposLoginLayout.setVerticalGroup(
            jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCamposLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(txtCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCamposLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpwContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
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
                .addGap(60, 60, 60)
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
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
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

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
         // cerrarbloq();
        ingresarSistema();
       // this.menuPrincipal.habilitar();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void jpwContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpwContrasenaActionPerformed
        ingresarSistema();
    }//GEN-LAST:event_jpwContrasenaActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
         jpwContrasena.requestFocus();
    }//GEN-LAST:event_txtCedulaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnIngresar;
    public static javax.swing.JButton btnSalir;
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
        String ced ="1";
        String pass = "1";
        txtCedula.setText(ced);
        jpwContrasena.setText(pass);
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
       // entrarProgramador2();
        
       // entrarProgramador();
        
       
       // REVISAR YA QUE AL INGRESAR UN USUARIO "0" SIN CONTRASEÑA, DESBLOQUEA LA VENTANA.......
          String cedula = txtCedula.getText();
          char[] arrayPassword = jpwContrasena.getPassword();
           int idEmpleado = menuPrincipal.getOBD().autenticarEmpleado2(cedula, arrayPassword);
      
          
          
          
         
            
          if (idEmpleado != -1) {
            HashMap<String, String> mapEmpleado = menuPrincipal.getOBD().getMapEmpleado(idEmpleado);
            menuPrincipal.setExtendedState(MAXIMIZED_BOTH);
            menuPrincipal.setVisible(true);
            menuPrincipal.setEmpleado(new PuntoVenta.Modelos.ModeloEmpleado(mapEmpleado));
            this.menuPrincipal.deshabilitar();
            cerrarbloq();
            
            } else { 
               /*  if (cedula.isEmpty() && arrayPassword.toString().isEmpty()) {
                Utilidades.Sonidos.beep();
                 JOptionPane.showMessageDialog(this, "Ingresar CEDULA y PASWORD para acceder al sistema");
                txtCedula.setText("");
                jpwContrasena.setText("");
                txtCedula.requestFocus();
                //jpwContrasena.requestFocus();
                this.menuPrincipal.habilitar();

                         } //if dentro del else
                  */
                  if (!cedula.equals(idEmpleado)) {

                        Utilidades.Sonidos.beep();
                        JOptionPane.showMessageDialog(this, "Ingrese cedula");
                        txtCedula.setText("");
                       txtCedula.requestFocus();
                       this.menuPrincipal.habilitar();
                      return;

                    } else{
                      
                  }
                  
                  
                 if(!arrayPassword.toString().equalsIgnoreCase(cedula)){
                        Utilidades.Sonidos.beep();
                        JOptionPane.showMessageDialog(this, "Ingrese password");
                        jpwContrasena.setText("");
                       jpwContrasena.requestFocus();
                       this.menuPrincipal.habilitar();
                    //  return;
                      
                          }
                   
                 } // else
        return;     
        
      
        }// if principal
    
    
    
    /* else if (txtCedula.getText().equals(cedula.isEmpty())) {

                        Utilidades.Sonidos.beep();
                        JOptionPane.showMessageDialog(this, "Ingrese cedula");
                       txtCedula.requestFocus();
                       this.menuPrincipal.habilitar();

                    } else{
                        Utilidades.Sonidos.beep();
                        JOptionPane.showMessageDialog(this, "Ingrese password");
                       jpwContrasena.requestFocus();
                       this.menuPrincipal.habilitar();

                 } // ii-else_if-else   */
    
 /* public  void  habilitar(){
        btnCaja.setEnabled(false);
        btnVentas.setEnabled(false);
        btnFacturas.setEnabled(false);
        btnAyuda.setEnabled(false);
        btnAcerca.setEnabled(false);
        btnAdmin.setEnabled(false);
        btnProductos.setEnabled(false);
        btnMovimientos.setEnabled(false);
   
   }
    public  void  deshabilitar(){
       
       if(PuntoVenta.Inicio.MenuPrincipal.btnbloqueo.isEnabled()){
        btnCaja.setEnabled(true);
        btnVentas.setEnabled(true);
        btnFacturas.setEnabled(true);
        btnAyuda.setEnabled(true);
        btnAcerca.setEnabled(true);
        btnAdmin.setEnabled(true);
        btnProductos.setEnabled(true);
        btnMovimientos.setEnabled(true);
       }
   }*/
        /*  if (cedula.isEmpty() && arrayPassword.length <= 0) {
            Utilidades.Sonidos.beep();
             JOptionPane.showMessageDialog(this, "Ingresar CEDULA y PASWORD para acceder al sistema");
            txtCedula.requestFocus();
            jpwContrasena.requestFocus();
            this.menuPrincipal.habilitar();
            return;
        }
       if (arrayPassword.length <= 0) {
            Utilidades.Sonidos.beep();
             JOptionPane.showMessageDialog(this, "Ingrese password");
           jpwContrasena.requestFocus();
           this.menuPrincipal.habilitar();
            return;
        }*/     
             
        
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
    public void setMaximum(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    }

  
    
    

