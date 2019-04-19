package PuntoVenta.Inicio;

import Utilidades.ruta;
import PuntoVenta.BaseDatos.Pais;
import PuntoVenta.Ventanas.*;
import PuntoVenta.Inicio.MenuPrincipal;
import Utilidades.Verificacion;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ParametrosIniciales1 extends javax.swing.JFrame {
    
    public MenuPrincipal menuPrincipal;
    boolean imagenCargada = false;
    File imagenSeleccionada = new File("");
    
    public ParametrosIniciales1(MenuPrincipal menuPrincipal) {
        initComponents();
   
        this.menuPrincipal = menuPrincipal;

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/PuntoVenta/Iconos/acerca.png")));

      //  entrarProgramador();
        identificarPais();
        this.setVisible(true);

    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup14 = new javax.swing.ButtonGroup();
        panelParametros = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbPais = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMoneda = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblImg = new javax.swing.JLabel();
        btnSeleccionar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Saphiro - Punto de Venta\n"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        panelParametros.setBackground(new java.awt.Color(32, 182, 155));
        panelParametros.setName("panelParametros"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(32, 182, 155));
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(28, 90, 125));
        jLabel2.setText("<html><font size=16><center>Parámetros:<br></font></center></html>");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(28, 90, 125));
        jLabel8.setText("Pais:");
        jLabel8.setName("jLabel8"); // NOI18N

        cmbPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chile", "Colombia", "Ecuador", "Venezuela" }));
        cmbPais.setName("cmbPais"); // NOI18N
        cmbPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaisActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(28, 90, 125));
        jLabel3.setText("Empresa:");
        jLabel3.setName("jLabel3"); // NOI18N

        txtNombre.setToolTipText("");
        txtNombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtNombre.setName("txtNombre"); // NOI18N
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreMouseClicked(evt);
            }
        });
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(28, 90, 125));
        jLabel4.setText("CUIT:");
        jLabel4.setName("jLabel4"); // NOI18N

        txtIdentificacion.setToolTipText("");
        txtIdentificacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtIdentificacion.setName("txtIdentificacion"); // NOI18N
        txtIdentificacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdentificacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdentificacionFocusLost(evt);
            }
        });
        txtIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificacionKeyTyped(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(28, 90, 125));
        jLabel5.setText("Dirección de Sede:");
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        txtDireccion.setAutoscrolls(false);
        txtDireccion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtDireccion.setName("txtDireccion"); // NOI18N
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDireccionKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtDireccion);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(28, 90, 125));
        jLabel6.setText("Teléfono:");
        jLabel6.setName("jLabel6"); // NOI18N

        txtTelefono.setToolTipText("");
        txtTelefono.setName("txtTelefono"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(28, 90, 125));
        jLabel7.setText("Moneda:");
        jLabel7.setName("jLabel7"); // NOI18N

        txtMoneda.setEditable(false);
        txtMoneda.setName("txtMoneda"); // NOI18N
        txtMoneda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMonedaKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMonedaKeyReleased(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(28, 90, 125));
        jLabel9.setText("Logo:");
        jLabel9.setName("jLabel9"); // NOI18N

        lblImg.setBackground(new java.awt.Color(255, 255, 255));
        lblImg.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        lblImg.setName("lblImg"); // NOI18N

        btnSeleccionar.setBackground(new java.awt.Color(32, 182, 155));
        btnSeleccionar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("Seleccionar archivo");
        btnSeleccionar.setBorder(null);
        btnSeleccionar.setName("btnSeleccionar"); // NOI18N
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnRegistrar.setBackground(new java.awt.Color(32, 182, 155));
        btnRegistrar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.setBorder(null);
        btnRegistrar.setName("btnRegistrar"); // NOI18N
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        salir.setBackground(new java.awt.Color(32, 182, 155));
        salir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("Salir");
        salir.setBorder(null);
        salir.setName("salir"); // NOI18N
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/fondo-blanco-690x720.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMoneda)
                                        .addComponent(txtTelefono))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cmbPais, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(217, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPais, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel9)))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelParametrosLayout = new javax.swing.GroupLayout(panelParametros);
        panelParametros.setLayout(panelParametrosLayout);
        panelParametrosLayout.setHorizontalGroup(
            panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelParametrosLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        panelParametrosLayout.setVerticalGroup(
            panelParametrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelParametrosLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(panelParametros, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(815, 823));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
       
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        HashMap<String, String> map;
        map = new HashMap();
        
        String[] claveFila = {"pais" ,"nombre", "identificacion", "direccion", "telefono", "moneda"};
        String[] valorFila = {(String)cmbPais.getSelectedItem() ,txtNombre.getText(), txtIdentificacion.getText(), 
                              txtDireccion.getText(), txtTelefono.getText(), txtMoneda.getText()};
        
        //insertando Strings en el map
        for(int i = 0; i < claveFila.length; i++) {
            map.put(claveFila[i], valorFila[i]);
        }
        
        
        if (validaciones(valorFila[0], valorFila[1], valorFila[2], valorFila[3], valorFila[4]) == false) {
        } else {
            int g = JOptionPane.showConfirmDialog(this, "Esta seguro?\nLuego no podra realizar cambios", "Saphiro - Parametros", JOptionPane.YES_NO_OPTION);
            
            if (g == JOptionPane.YES_OPTION) {
                guardarImagen(imagenSeleccionada);  //Guarda la imagen en una carpeta del programa
                guardarRutaImagen(map);  //Inserta en el map el nombre de la imagen para guardarlo en la base de datos
                menuPrincipal.getOBD().crearParametros(map);
                menuPrincipal.getOBD().activarPais((String)cmbPais.getSelectedItem());
//                menuPrincipal.setExtendedState(MAXIMIZED_BOTH);
                menuPrincipal.validarInicio();
                this.dispose();
                
            } else if (g == JOptionPane.NO_OPTION) {
                this.setVisible(true);
            }
        }
        

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        //        if (!Character.isAlphabetic(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_SPACE)) {
            //            evt.consume();
            //        }
        //        if (txtNombre.getText().length() > 20) {
            //            evt.consume();
            //        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
//        txtNombre.setBackground(Color.white);
    }//GEN-LAST:event_txtNombreKeyPressed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        //        habilitar();        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtIdentificacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyTyped

    }//GEN-LAST:event_txtIdentificacionKeyTyped

    private void txtIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacionKeyReleased
        //        habilitar();        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char validar = evt.getKeyChar();
        if(Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        //        habilitar();        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyTyped
        if (!txtDireccion.getText().isEmpty() && txtDireccion.getText().length() % 38 == 0) {
            txtDireccion.setText(txtDireccion.getText() + "\n");
        }
    }//GEN-LAST:event_txtDireccionKeyTyped

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        //        habilitar();        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtMonedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonedaKeyTyped
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMonedaKeyTyped

    private void txtMonedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMonedaKeyReleased
        //        habilitar();        // TODO add your handling code here:
    }//GEN-LAST:event_txtMonedaKeyReleased

    private void cmbPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaisActionPerformed
        // TODO add your handling code here:
        identificarPais();
    }//GEN-LAST:event_cmbPaisActionPerformed

    private void txtNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseClicked

    }//GEN-LAST:event_txtNombreMouseClicked

    private void txtIdentificacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdentificacionFocusGained
        switch(txtIdentificacion.getText()) {
            //Venezuela
            case "J-XXXXXXXX-X": txtIdentificacion.setText("");
            break;
            //Colombia
            case "XXXXXXXX-X": txtIdentificacion.setText("");
            break;
            //Chile
            case "XXXXXXXXX-X": txtIdentificacion.setText("");
            break;
            //Ecuador
            case "XXXXXXXXXXXXX": txtIdentificacion.setText("");
            break;
        }
    }//GEN-LAST:event_txtIdentificacionFocusGained

    private void txtIdentificacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdentificacionFocusLost
        if(txtIdentificacion.getText().trim().equals(""))   setEjemplo((String)cmbPais.getSelectedItem());
    }//GEN-LAST:event_txtIdentificacionFocusLost

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        seleccionarImagen();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        int g = JOptionPane.showConfirmDialog(this, "Desea salir del sistema ahora", "Saphiro - Salir", JOptionPane.YES_NO_OPTION);

        if (g == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (g == JOptionPane.NO_OPTION) {
            
            this.setVisible(true);
        }
    }//GEN-LAST:event_salirActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        int g = JOptionPane.showConfirmDialog(this, "Desea salir del sistema ahora", "Saphiro - Salir", JOptionPane.YES_NO_OPTION);

        if (g == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (g == JOptionPane.NO_OPTION) {
            
            this.setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.ButtonGroup buttonGroup14;
    private javax.swing.JComboBox<String> cmbPais;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImg;
    private javax.swing.JPanel panelParametros;
    private javax.swing.JButton salir;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtMoneda;
    public static javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
    
    //Muestra la imagen seleccionada y valida que sea una imagen
    public void seleccionarImagen() {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Buscar imagen");
        File archivo;
        
        if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivo = new File(fc.getSelectedFile().toString());

            if(archivo.getName().toUpperCase().endsWith("PNG") || archivo.getName().toUpperCase().endsWith("JPG") 
                    || archivo.getName().toUpperCase().endsWith("JPEG")) {
                rsscalelabel.RSScaleLabel.setScaleLabel(lblImg, fc.getSelectedFile().toString());
                imagenCargada = true;
                imagenSeleccionada = archivo;
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de archivo inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    //Guarda la imagen seleccionada
    public void guardarImagen(File imagen) {
        BufferedImage image = null;
        ruta r = new ruta();
        
        try {
            image = ImageIO.read(imagen);
            if(imagen.getName().toUpperCase().endsWith("JPG"))           ImageIO.write(image, "jpg", new File(r.rutaImagen + "logo.jpg"));
            else if(imagen.getName().toUpperCase().endsWith("PNG"))      ImageIO.write(image, "png", new File(r.rutaImagen + "logo.png"));
            else if(imagen.getName().toUpperCase().endsWith("JPEG"))     ImageIO.write(image, "jpeg", new File(r.rutaImagen + "logo.jpeg"));
        } catch (Exception e) {
        }
    }
    
    //guarda el nombre de la imagen en el map para pasarlo a la base de datos
    public void guardarRutaImagen(HashMap<String, String> map) {
        map.put("logo", verificarImagenGuardada());
    }
    
    //entra en la carpeta donde se guarda la imagen y verifica que exista;
    public String verificarImagenGuardada() {
        ruta r = new ruta();
        File ruta = new File(r.rutaImagen);        
        String[] archivos = ruta.list();
        return archivos[0];
    }
    
    //identifica el pais segun el item seleccionado en el combobox para traerse la moneda y el tipo de documentacion de dicho pais
    public void identificarPais() {
        //Obteniendo el pais seleccionado
        String nombrePais = (String)cmbPais.getSelectedItem();
        Pais p = menuPrincipal.getOBD().getDatosPais(" WHERE nombre_pais = '"+nombrePais+"'");
        
        //asignando moneda, tipo de identificacion y ejemplo para la identificacion.
        txtMoneda.setText(p.getSimbolo() + " - " + p.getMoneda());
        jLabel4.setText(p.getIdentificacion() + ":");
        setEjemplo(nombrePais);
    }
    
    //coloca el ejemplo
    public void setEjemplo(String nombrePais) {
        switch(nombrePais) {
            case "Venezuela": txtIdentificacion.setText("J-XXXXXXXX-X");
            break;
            case "Colombia": txtIdentificacion.setText("XXXXXXXX-X");
            break;
            case "Chile": txtIdentificacion.setText("XXXXXXXXX-X");
            break;
            case "Ecuador": txtIdentificacion.setText("XXXXXXXXXXXXX");
            break;
        }
    }
    
    //Valida todos los campos
    public boolean validaciones(String pais ,String nombre, String identificacion, String direccion, String telefono) {
        
        boolean estanVacios = false;
        boolean identificacionValidada = false;
        
        //validando campos vacios
        if(nombre.trim().isEmpty() || identificacion.trim().isEmpty() || direccion.trim().isEmpty() || telefono.trim().isEmpty() || imagenCargada == false) {
            estanVacios = true;
        }
        
        //validando identificacion
        switch(pais) {
            case "Venezuela": if(identificacion.equals(Verificacion.isRif(identificacion)))  identificacionValidada = true;
            break;
            case "Colombia": if(identificacion.equals(Verificacion.isNit(identificacion)))   identificacionValidada = true;
            break;
            case "Chile": if(identificacion.equals(Verificacion.isRut(identificacion)))      identificacionValidada = true;
            break;
            case "Ecuador": if(identificacion.equals(Verificacion.isRuc(identificacion)))    identificacionValidada = true;
            break;
        }

        //enviando mensajes
        if(estanVacios == true) {
            JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(identificacionValidada == false) {
            JOptionPane.showMessageDialog(null, "La identififacion debe ser valida", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return !(estanVacios == true || identificacionValidada == false);
    }
}