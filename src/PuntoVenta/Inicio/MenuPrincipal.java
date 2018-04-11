package PuntoVenta.Inicio;

//import PuntoVenta.Ventanas.bloqueo2;  
import PuntoVenta.Ventanas.Bloqueo; //cambiado por bloqueo2
import Administrador.Ventanas.Admin;
import PuntoVenta.BaseDatos.Empresa;
import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import PuntoVenta.Modelos.ModeloCaja;
import PuntoVenta.Modelos.ModeloEmpleado;
import PuntoVenta.Modelos.ModeloProducto; // añadido
import PuntoVenta.Ventanas.Caja;
import PuntoVenta.Ventanas.Calculadora;
import PuntoVenta.Ventanas.LogIn;
import PuntoVenta.Ventanas.Acerca;
import PuntoVenta.Ventanas.Ayuda;
import PuntoVenta.Ventanas.Factura;
import PuntoVenta.Ventanas.Venta;
import PuntoVenta.Ventanas.CierreCaja;
import PuntoVenta.Ventanas.Productos; //añadido
import PuntoVenta.Ventanas.Movimientos;//añadido
import PuntoVenta.Ventanas.Detalles; //añadido para productos
import PuntoVenta.Ventanas.Compra; //añadido para movimientos
import PuntoVenta.Ventanas.Fabricacion;//añadido para movimientos
import PuntoVenta.Ventanas.Ajuste;//añadido para movimientos
import PuntoVenta.Ventanas.AgregarProducto; //añadido para productos
import PuntoVenta.Ventanas.ModificarProducto; //añadido para productos
import PuntoVenta.fondo;
import Utilidades.KeySaphiro;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
 *
 */
public class MenuPrincipal extends javax.swing.JFrame {

    KeySaphiro ks;

    //VENTANAS
    public Caja caja;
    public Calculadora calc;
    public Venta venta;
    public LogIn login;
    public Admin admin;
    public Bloqueo bloqueo; // modificado de bloqueo2 --> Bloqueo
    public Empresa empresa;
    public CierreCaja cierre;
    public Productos producto; //añadido
    public Movimientos movimiento;   // añadido

    //OTRAS VENTANAS
    //UTILIZAR DESPUES PARA SISTEMA DE CAMBIO DE PANTALLAS
    private Modulo ventanaAbierta;

    //otros
    public fondo panel = new fondo();

    private ModeloEmpleado empleado;
    private boolean cajaAbierta;
    private ModeloCaja modeloCaja;
    private Factura factura;
    private AgregarProducto agregar;
    private ModificarProducto modificar;
    private Detalles detalles;
    private Fabricacion fabricacion;
    private Compra compra;
    private Ajuste ajuste;
    private Acerca acerca = new Acerca();
    private Ayuda ayuda = new Ayuda();
    private int idEstadoCaja;

    private ObjetoBaseDatos obd; //Objeto Base Datos
    private Properties configuracion;
    private boolean logged;
    private Object actProd;

    /**
     * ?
     */
    public MenuPrincipal() {
        initComponents();

        //Obtener archivo de configuracion
        this.configuracion = getConfiguracion("local.conf");
        //Crear objeto base de datos
        try {
            this.obd = crearObjetoBaseDatos();
        } catch (Exception e) {
//            System.err.println("Error al conectar con la base de datos. Verifique su conexión con el servidor: " + configuracion.get("bd_servidor"));
            Utilidades.CuadroMensaje.getMensajeError(panel, "Error al conectar con la base de datos. Verifique su conexión con el servidor: " + configuracion.get("bd_servidor"), "Error de conexión");
        }
        //Asignar modelo caja
        HashMap<String, String> map = this.obd.getMapCaja(Integer.parseInt(this.configuracion.getProperty("id_caja")));
        if (map != null && !map.isEmpty()) {
            this.modeloCaja = new ModeloCaja(map);
        } else {
//            System.err.println("Error: No se cargo la informacion de la caja. Revise la conexion con la base de datos y su archivo de configuración.");
        }

        this.setTitle("Saphiro - " + this.configuracion.getProperty("nombre_equipo"));
        this.cajaAbierta = this.obd.isCajaAbierta(this.modeloCaja.getId());

        this.idEstadoCaja = this.obd.getIdUltimoEstadoCaja(this.modeloCaja.getId());
        crearHotKeys();
        //Si la caja está cerrada, deshabilita los botones F2, F3, F4.
        this.setBotonesMenuPrincipalEnabled(this.cajaAbierta);
            
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);

        /**
         * La licencia está en el archivo de configuración de forma temporal.
         * Debe guardarse en un archivo con otra extension, se recomiendo ".ks"
         * y leerse desde el otro archivo con un InputStream.
         */
        try {
            String licencia = configuracion.getProperty("licencia");

            ks = new KeySaphiro(licencia);
            if (ks.validate()) {
//                System.out.println("MAC aceptada.");
            } else {
//                System.err.println("MAC inválida.");
                //Aqui deberia solicitarse la licencia e intentar validarla. Si no salir del sistema.
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.login = new LogIn(this);
    }

    /**
     * Carga la información del alchivo .conf y lo retorna. El archivo se carga
     * desde la ruta en la que se ejecuta el programa (jar).
     *
     * @param archivo Direccion del archivo a cargar.
     * @return Properties configuracion.
     */
    private Properties getConfiguracion(String archivo) {
        Properties configuracion = new Properties();
        try {
            configuracion.load(new FileInputStream(System.getProperty("user.dir") + File.separator + archivo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuracion;

    }

    /**
     * METODO ANTIGUO. METODO QUE DEVUELVE UN VALOR BOOLEAN PARA SABER SI UN
     * JINTERNALFRAME ESTA ABIERTO O NO. Funciona, pero recorre todos los
     * InternalFrames creados cada vez que se quiere abrir alguno, se deberia
     * buscar una forma más eficiente de detectar si está abierto algun
     * InternalFrame.
     *
     * @param obj
     * @return
     */
    public boolean estacerrado(Object obj) {
        JInternalFrame[] activos = panel.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == obj) {
                cerrado = false;
            }
            i++;
        }
        return cerrado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        btnCaja = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnFacturas = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        btnMovimientos = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnAyuda = new javax.swing.JButton();
        btnAcerca = new javax.swing.JButton();
        btnbloqueo = new javax.swing.JButton();
        btnCalculadora = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jDesktopPane1, java.awt.BorderLayout.NORTH);

        jToolBar1.setBackground(new java.awt.Color(117, 133, 155));
        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jToolBar1.setNextFocusableComponent(btnCaja);

        btnCaja.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. caja.png"))); // NOI18N
        btnCaja.setText("<html><font size=2><center>Caja<br>F1</center></font></html>");
        btnCaja.setActionCommand("actionCaja");
        btnCaja.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCaja.setNextFocusableComponent(btnVentas);
        btnCaja.setSelected(true);
        btnCaja.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCajaMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCajaMouseEntered(evt);
            }
        });
        btnCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCajaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCaja);

        btnVentas.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ventas.png"))); // NOI18N
        btnVentas.setText("<html><font size=2><center>Ventas<br>F2</center></font></html>");
        btnVentas.setActionCommand("actionVentas");
        btnVentas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnVentas.setFocusable(false);
        btnVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas.setRequestFocusEnabled(false);
        btnVentas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentasActionPerformed(evt);
            }
        });
        btnVentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnVentasKeyPressed(evt);
            }
        });
        jToolBar1.add(btnVentas);

        btnFacturas.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnFacturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. factura.png"))); // NOI18N
        btnFacturas.setText("<html><font size=2><center>Facturas<br>F4</center></font></html>");
        btnFacturas.setActionCommand("actionFacturas");
        btnFacturas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnFacturas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFacturas.setNextFocusableComponent(btnCaja);
        btnFacturas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturasActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFacturas);

        btnAdmin.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. admin.png"))); // NOI18N
        btnAdmin.setText("<html><font size=2><center>Admin<br>F6</center></font></html>");
        btnAdmin.setActionCommand("actionCaja");
        btnAdmin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAdmin.setFocusable(false);
        btnAdmin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmin.setNextFocusableComponent(btnVentas);
        btnAdmin.setSelected(true);
        btnAdmin.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnAdmin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdminMouseExited(evt);
            }
        });
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });
        btnAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAdminKeyPressed(evt);
            }
        });
        jToolBar1.add(btnAdmin);

        btnMovimientos.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnMovimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ayuda.png"))); // NOI18N
        btnMovimientos.setText("<html><font size=2><center>Movimientos<br>F8</center></font></html>");
        btnMovimientos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnMovimientos.setFocusable(false);
        btnMovimientos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMovimientos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovimientosActionPerformed(evt);
            }
        });
        btnMovimientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnMovimientosKeyPressed(evt);
            }
        });
        jToolBar1.add(btnMovimientos);

        btnProductos.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ayuda.png"))); // NOI18N
        btnProductos.setText("<html><font size=2><center>Productos<br>F9</center></font></html>");
        btnProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnProductos.setFocusable(false);
        btnProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        btnProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnProductosKeyPressed(evt);
            }
        });
        jToolBar1.add(btnProductos);

        btnAyuda.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ayuda.png"))); // NOI18N
        btnAyuda.setText("<html><font size=2><center>Ayuda<br>F10</center></font></html>");
        btnAyuda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAyuda.setFocusable(false);
        btnAyuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAyuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAyuda);

        btnAcerca.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnAcerca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. acerca.png"))); // NOI18N
        btnAcerca.setText("<html><font size=2><center>Acerca<br>F11</center></font></html>");
        btnAcerca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAcerca.setFocusable(false);
        btnAcerca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAcerca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcercaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAcerca);

        btnbloqueo.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnbloqueo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. bloqueo.png"))); // NOI18N
        btnbloqueo.setText("<html><font size=2><center>Bloqueo<br>F12</center></font></html>");
        btnbloqueo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnbloqueo.setFocusable(false);
        btnbloqueo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnbloqueo.setPreferredSize(new java.awt.Dimension(52, 82));
        btnbloqueo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnbloqueo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbloqueoMouseClicked(evt);
            }
        });
        btnbloqueo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbloqueoActionPerformed(evt);
            }
        });
        btnbloqueo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnbloqueoKeyPressed(evt);
            }
        });
        jToolBar1.add(btnbloqueo);

        btnCalculadora.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnCalculadora.setText("<html><font size=2><center>Calculadora<br>FX</center></font></html>");
        btnCalculadora.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCalculadora.setFocusable(false);
        btnCalculadora.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCalculadora.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCalculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculadoraActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCalculadora);

        btnSalir.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnSalir.setText("<html><font size=4><center>Salir<br></center></font></html>");
        btnSalir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSalir);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.LINE_START);

        pack();
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

    private void btnCalculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculadoraActionPerformed
        abrirCalculadora();
    }//GEN-LAST:event_btnCalculadoraActionPerformed

    private void btnbloqueoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnbloqueoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F12) {
            abrirVentanaBloqueo();
        }
    }//GEN-LAST:event_btnbloqueoKeyPressed

    private void btnbloqueoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbloqueoActionPerformed
        abrirVentanaBloqueo();
    }//GEN-LAST:event_btnbloqueoActionPerformed

    private void btnbloqueoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbloqueoMouseClicked

    }//GEN-LAST:event_btnbloqueoMouseClicked

    private void btnAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcercaActionPerformed
        acerca();
    }//GEN-LAST:event_btnAcercaActionPerformed

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        ayuda();
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            abrirVentanaProd();
        }
    }//GEN-LAST:event_btnProductosKeyPressed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        abrirVentanaProd();
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnMovimientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnMovimientosKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_F8) {
            abrirVentanaMovimientos();
        }

    }//GEN-LAST:event_btnMovimientosKeyPressed

    private void btnMovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovimientosActionPerformed
        abrirVentanaMovimientos();

    }//GEN-LAST:event_btnMovimientosActionPerformed

    private void btnAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAdminKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F6) {
            abrirVentanaAdmin();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdminKeyPressed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        abrirVentanaAdmin();
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdminMouseExited

    private void btnAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdminMouseEntered

    private void btnFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturasActionPerformed
        abrirVentanaFactura();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFacturasActionPerformed

    private void btnVentasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnVentasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            abrirVentanaVentas();
        }
    }//GEN-LAST:event_btnVentasKeyPressed

    private void btnVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentasActionPerformed
        abrirVentanaVentas();
        PuntoVenta.Ventanas.Venta.txtDocumento.requestFocus();
    }//GEN-LAST:event_btnVentasActionPerformed

    private void btnCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCajaActionPerformed
        abrirVentanaCaja();
    }//GEN-LAST:event_btnCajaActionPerformed

    private void btnCajaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnCajaMouseEntered

    private void btnCajaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnCajaMouseExited

    private void ayuda() {
        ayuda.setVisible(true);
        ayuda.setLocationRelativeTo(this);
        ayuda.setTitle("Sistema Saphiro: Ayuda");
    }

    private void acerca() {
        acerca.setVisible(true);
        acerca.setLocationRelativeTo(this);
        acerca.setTitle("Sistema Saphiro: Acerca");
    }

 public  void  habilitar(){
      if(PuntoVenta.Inicio.MenuPrincipal.btnbloqueo.isEnabled()){
        btnCaja.setEnabled(false);
        btnVentas.setEnabled(false);
        btnFacturas.setEnabled(false);
        btnAyuda.setEnabled(false);
        btnAcerca.setEnabled(false);
        btnAdmin.setEnabled(false);
        btnProductos.setEnabled(false);
        btnMovimientos.setEnabled(false);
      }
   }
 
 public  void  deshabilitar(){
       
    //   if(PuntoVenta.Inicio.MenuPrincipal.btnbloqueo.isEnabled()){
        btnCaja.setEnabled(true);
        btnVentas.setEnabled(true);
        btnFacturas.setEnabled(true);
        btnAyuda.setEnabled(true);
        btnAcerca.setEnabled(true);
        btnAdmin.setEnabled(true);
        btnProductos.setEnabled(true);
        btnMovimientos.setEnabled(true);
     //  }
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcerca;
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnCaja;
    private javax.swing.JButton btnCalculadora;
    private javax.swing.JButton btnFacturas;
    private javax.swing.JButton btnMovimientos;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVentas;
    private javax.swing.JButton btnbloqueo;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private void abrirVentanaVentas() {
        if (estacerrado(venta)) {
            venta = new Venta(this);
            Dimension desktopSize = panel.getSize();
            Dimension jInternalFrameSize = venta.getSize();
            venta.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            panel.add(venta);
            venta.setMinimumSize(new Dimension(800, 600));
            try {
                venta.setMaximum(true);
            } catch (PropertyVetoException ex) {
                ex.printStackTrace();
            }
            venta.show();
        } else {
            venta.show();
            venta.getTxtDocumento().requestFocus();
        }
    }

    public void abrirVentanaAdmin() {

        admin = new Admin(this);
        Dimension desktopSize = panel.getSize();
        Dimension jInternalFrameSize = admin.getSize();
        admin.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        panel.add(admin);
        admin.show();
    }

    public void abrirVentanaBloqueo() {

        if (estacerrado(bloqueo)) {
            
            bloqueo = new Bloqueo(this);
            Dimension desktopSize = panel.getSize();
            Dimension jInternalFrameSize = bloqueo.getSize();
            bloqueo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                                (desktopSize.height - jInternalFrameSize.height) / 2);
            panel.add(bloqueo);
            this.habilitar();
            
           // bloqueo.setMaximum(true);
            bloqueo.show();
            setVentanaAbierta(Modulo.BLOQUEO);
            
        } else if (estacerrado(bloqueo)) {
            bloqueo.requestFocus();
            deshabilitar();
        }

    }

    public void abrirVentanaCaja() {
        if (estacerrado(caja)) {
            caja = new Caja(this);
            Dimension desktopSize = panel.getSize();
            Dimension jInternalFrameSize = caja.getSize();
            caja.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            panel.add(caja);
            caja.show();
            setVentanaAbierta(Modulo.CAJA);
        } else {
            caja.requestFocus();
        }
    }

    public void abrirVentanaFactura() {
        factura = new Factura(this);
        Dimension desktopSize = panel.getSize();
        Dimension jInternalFrameSize = factura.getSize();
        factura.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        panel.add(factura);
        factura.show();
    }

    public void abrirCalculadora() {
        if (estacerrado(calc)) {
            calc = new Calculadora(this);
            Dimension desktopSize = panel.getSize();
            Dimension jInternalFrameSize = calc.getSize();
            calc.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            panel.add(calc);
            calc.show();
            setVentanaAbierta(Modulo.CALC);
        } else {
            calc.requestFocus();
        }
    }
    
  
   
    public void abrirVentanaProd(){
      
        if (estacerrado(producto)) {
             producto = new Productos(this);
             Dimension desktopSize = panel.getSize();
        Dimension jInternalFrameSize = producto.getSize();
        producto.setLocation((desktopSize.width -jInternalFrameSize.width)/ 2,
                (desktopSize.height - jInternalFrameSize.height )/ 2);
                panel.add(producto);
                producto.show();
                setVentanaAbierta(Modulo.PRODUCTOS);
        } else {
            JOptionPane.showMessageDialog(this, "La ventana ya esta abierta...");
        }
      
    }
    
    public void abrirVentanaMovimientos(){
         if (estacerrado(movimiento)) {
             movimiento =  new Movimientos(this);
              Dimension desktopSize = panel.getSize();
        Dimension jInternalFrameSize = movimiento.getSize();
        movimiento.setLocation((desktopSize.width -jInternalFrameSize.width)/ 2,
                (desktopSize.height - jInternalFrameSize.height )/ 2);
                panel.add(movimiento);
                movimiento.show();
                setVentanaAbierta(Modulo.MOVIMIENTOS);
        } else {
            JOptionPane.showMessageDialog(this, "La ventana ya esta abierta...");
        }
       
    }

    public void setBotonesMenuPrincipalEnabled(boolean aFlag) {
        btnVentas.setEnabled(aFlag);
        btnFacturas.setEnabled(aFlag);
   }

    /**
     * Método para crear y asignar HotKeys (accesos rápidos). Tiene 4 pasos: -
     * Crear el action que se activara cuando se pulse el boton.
     *
     * - Hacer un putValue(TipoKey, KeyStroke) a cada action.
     *
     * -Asignar el action al actionMap correspondiente de cada action. (método
     * getActionMap().put() en el elemento desde donde se activará el action).
     *
     * - Asignar el action al inputMap correspondiente de cada action. (método
     * getInputMap().put() en el elemento desde donde se activará el action).
     *
     * Para HotKeys dentro de tablas y otros elementos similares, debe asignarse
     * desde el listener keyTyped, keyPressed o keyReleased para que funcione.
     */
    private void crearHotKeys() {
        Action actCaja = new AbstractAction(Modulo.CAJA.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaCaja();
            }
        };
        Action actVentas = new AbstractAction(Modulo.VENTAS.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaVentas();
                PuntoVenta.Ventanas.Venta.txtDocumento.requestFocus();
            }

        };

        Action actFacturas = new AbstractAction(Modulo.FACTURAS.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaFactura();
            }
        };
        Action actUsuario = new AbstractAction(Modulo.USUARIO.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        Action actAdmin = new AbstractAction(Modulo.ADMIN.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAdmin();
            }
        };
        Action actAcerca = new AbstractAction(Modulo.ACERCA.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                acerca();
            }
        };
        Action actAyuda = new AbstractAction(Modulo.AYUDA.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                ayuda();
            }
        };

        Action actbloqueo = new AbstractAction(Modulo.BLOQUEO.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {

                abrirVentanaBloqueo();
            }
        };
        
        //añadido a productos
          Action actProducto = new AbstractAction(Modulo.PRODUCTOS.getAction()){
            @Override
             public void actionPerformed(ActionEvent e){
                 
                    abrirVentanaProd();
                }
            };
            
            //añadido a movimientos
            Action actMovimiento = new AbstractAction (Modulo.MOVIMIENTOS.getAction()){
            
            @Override
             
            public void actionPerformed(ActionEvent e){
            
                abrirVentanaMovimientos();
              }
            
            };
        
        
        
        
        actCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        actVentas.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        actFacturas.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        actUsuario.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        actAdmin.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
        actbloqueo.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
        actAyuda.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
        actAcerca.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
        actProducto.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_F9,0));
        actMovimiento.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_F8,0));
        
        
        getBtnCaja().getActionMap().put(Modulo.CAJA.getAction(), actCaja);
        getBtnCaja().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCaja.getValue(Action.ACCELERATOR_KEY), Modulo.CAJA.getAction());

        btnVentas.getActionMap().put(Modulo.VENTAS.getAction(), actVentas);
        btnVentas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actVentas.getValue(Action.ACCELERATOR_KEY), Modulo.VENTAS.getAction());

        btnFacturas.getActionMap().put(Modulo.FACTURAS.getAction(), actFacturas);
        btnFacturas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFacturas.getValue(Action.ACCELERATOR_KEY), Modulo.FACTURAS.getAction());

        btnAdmin.getActionMap().put(Modulo.ADMIN.getAction(), actAdmin);
        btnAdmin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAdmin.getValue(Action.ACCELERATOR_KEY), Modulo.ADMIN.getAction());

        btnbloqueo.getActionMap().put(Modulo.BLOQUEO.getAction(), actbloqueo);
        btnbloqueo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actbloqueo.getValue(Action.ACCELERATOR_KEY), Modulo.BLOQUEO.getAction());

        btnAyuda.getActionMap().put(Modulo.AYUDA.getAction(), actAyuda);
        btnAyuda.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAyuda.getValue(Action.ACCELERATOR_KEY), Modulo.AYUDA.getAction());

        btnAcerca.getActionMap().put(Modulo.ACERCA.getAction(), actAcerca);
        btnAcerca.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAcerca.getValue(Action.ACCELERATOR_KEY), Modulo.ACERCA.getAction());
        
        btnProductos.getActionMap().put(Modulo.PRODUCTOS.getAction(),actProducto);
        btnProductos.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)actProducto.getValue(Action.ACCELERATOR_KEY),Modulo.PRODUCTOS.getAction());   //  producto
        
        btnMovimientos.getActionMap().put(Modulo.MOVIMIENTOS.getAction(), actMovimiento);
        btnMovimientos.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)actMovimiento.getValue(Action.ACCELERATOR_KEY),Modulo.MOVIMIENTOS.getAction());  // movimiento
    }

    /**
     * @return the btnCaja
     */
    public javax.swing.JButton getBtnCaja() {
        return btnCaja;
    }
    
     public javax.swing.JButton getBtnProducto() {
        return btnProductos;
    }
     
      public javax.swing.JButton getBtnMovimiento() {
        return btnMovimientos;
    }

    /**
     * @return the obd
     */
    public ObjetoBaseDatos getOBD() {
        return obd;
    }

    /**
     * @param obd the obd to set
     */
    public void setOBD(ObjetoBaseDatos obd) {
        this.obd = obd;
    }

    /**
     * @return the configuracion
     */
    public Properties getConfiguracion() {
        return configuracion;
    }

    /**
     * @param configuracion the configuracion to set
     */
    public void setConfiguracion(Properties configuracion) {
        this.configuracion = configuracion;
    }

    /**
     * @return the modeloCaja
     */
    public ModeloCaja getModeloCaja() {
        return modeloCaja;
    }

    /**
     * @return the idEmpleado
     */
    public ModeloEmpleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the idEmpleado to set
     */
    public void setEmpleado(ModeloEmpleado empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the idEstadoCaja
     */
    public int getIdEstadoCaja() {
        return idEstadoCaja;
    }

    /**
     * @param idEstadoCaja the idEstadoCaja to set
     */
    public void setIdEstadoCaja(int idEstadoCaja) {
        this.idEstadoCaja = idEstadoCaja;
    }

    /**
     * Enum de los modulos disponibles
     */
    private enum Modulo {

        CAJA(1, "actionCaja"),
        VENTAS(2, "actionVentas"),
        FACTURAS(3, "actionFacturas"),
        USUARIO(4, "actionUsuarios"),
        AYUDA(11, "actionAyuda"),
        ACERCA(5, "actionAcerca"),
        CALC(10, "actionCalculadora"),
        ADMIN(6, "actionAdmin"),
        PRODUCTOS(8,"actionProducto"),// nueva ventana
        MOVIMIENTOS(9,"actionMovimiento"), // nueva ventana
        BLOQUEO(12, "actionBloqueo");

        private final int id;
        private final String action;

        Modulo(int id, String action) {
            this.id = id;
            this.action = action;
        }

        private int getId() {
            return this.id;
        }

        private String getAction() {
            return this.action;
        }

    }

    /**
     * @return the ventanaAbierta
     */
    public Modulo getVentanaAbierta() {
        return ventanaAbierta;
    }

    /**
     * @param ventanaAbierta the ventanaAbierta to set
     */
    public void setVentanaAbierta(Modulo ventanaAbierta) {
        this.ventanaAbierta = ventanaAbierta;
    }

    /**
     * @return the estadoCaja
     */
    public boolean isCajaAbierta() {
        return cajaAbierta;
    }

    /**
     * @param estadoCaja the estadoCaja to set
     */
    public void setEstadoCaja(boolean estadoCaja) {
        this.cajaAbierta = estadoCaja;
    }

    /**
     * Main del software.
     *
     * @param args
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("nimbusBase", new Color(32, 182, 155));
                UIManager.put("nimbusBlueGrey", new Color(204, 204, 204));
                UIManager.put("control", new Color(204, 204, 204));
//                  UIManager.put("control", new Color(152,155,149)); gris oscurito
//                UIManager.put("control", new Color(204,204,204)); gris clarito
//                UIManager.put("nimbusBase", new Color(163,168,167));
//                UIManager.put("nimbusBase", new Color(51,98,140)); azul original
//                UIManager.put("nimbusBlueGrey", new Color(163,168,167));
//                UIManager.put("nimbusBlueGrey", new Color(51,98,140));
//                UIManager.put("control", new Color(163,168,167));
//                UIManager.put("control", new Color(51,98,140));
                //Aqui se aplica el LookAndFeel - Nimbus.
                try {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                MenuPrincipal menuPrincipal = new MenuPrincipal();
                menuPrincipal.setVisible(false);

            }
        });

    }

    /**
     * Crea el ObjetoBaseDatos obteniendo la información desde el archivo de
     * configuracion. Este objeto es para uso exclusivo de PostgreSQL.
     *
     * @return
     */
    private ObjetoBaseDatos crearObjetoBaseDatos() {
        String bdName = this.configuracion.getProperty("bd_nombre");
        String bdPort = this.configuracion.getProperty("bd_port");
        String bdServer = this.configuracion.getProperty("bd_servidor");
        String bdUrl = "jdbc:postgresql://" + bdServer + ":" + bdPort + "/" + bdName;

        String bdLogin = this.configuracion.getProperty("bd_user");
        String bdPassword = this.configuracion.getProperty("bd_password");
        
        return new ObjetoBaseDatos(bdUrl, bdLogin, bdPassword);
    }
}
