package PuntoVenta.Inicio;

//import PuntoVenta.Ventanas.bloqueo2;  
import PuntoVenta.Inicio.ParametrosIniciales1;
import PuntoVenta.Inicio.RegistroAdministrador;
import PuntoVenta.Ventanas.Bloqueo; //cambiado por bloqueo2
import Administrador.Ventanas.Admin;
import PuntoVenta.BaseDatos.Empleado;
import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import PuntoVenta.BaseDatos.Pais;
import PuntoVenta.BaseDatos.Parametros;
import PuntoVenta.Modelos.ModeloCaja;
import PuntoVenta.Modelos.ModeloEmpleado;
import PuntoVenta.Ventanas.Caja;
import PuntoVenta.Ventanas.Calculadora;
import PuntoVenta.Ventanas.LogIn;
import PuntoVenta.Cliente.Cliente;
import PuntoVenta.Ventanas.Acerca;
import PuntoVenta.Ventanas.Ayuda;
import PuntoVenta.Ventanas.Factura;
import PuntoVenta.Ventanas.Venta;
import PuntoVenta.Ventanas.CierreCaja;
import PuntoVenta.Ventanas.ClaveSuperUsuario;
import PuntoVenta.Ventanas.Inventario;
import PuntoVenta.Ventanas.Productos;
import PuntoVenta.fondo;
import Utilidades.KeySaphiro;
import Utilidades.currentLoger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 */
public class MenuPrincipal extends javax.swing.JFrame {

    KeySaphiro ks;

    //VENTANAS
    public ParametrosIniciales1 parametrosIniciales1;
    public RegistroAdministrador registroAdministrador;
    public Caja caja;
    public Calculadora calc;
    public Venta venta;
    public LogIn login;
    public Admin admin;
    public Bloqueo bloqueo; // modificado de bloqueo2 --> Bloqueo
//    public Empresa empresa;
    public Parametros parametros;
    public Pais pais;
    public CierreCaja cierre;
    public Productos productos; //añadido
    public Inventario inventario;   // añadido

    //OTRAS VENTANAS
    //UTILIZAR DESPUES PARA SISTEMA DE CAMBIO DE PANTALLAS
    private Modulo ventanaAbierta;

    //otros
    public fondo panel = new fondo();

    private ModeloEmpleado empleado;
    private boolean cajaAbierta;
    private ModeloCaja modeloCaja;
    private Factura factura;
    private Acerca acerca = new Acerca();
    private Ayuda ayuda = new Ayuda();
    private int idEstadoCaja;
    private boolean primeraVez = true;
    private ObjetoBaseDatos obd; //Objeto Base Datos
    private Properties configuracion;
    private boolean logged;
    private Object actProd;
    public ArrayList<JButton> ListadeBotones = new ArrayList<>();

    private Cliente cliente;
    
    /**
     * ?
     */
    public MenuPrincipal() {
        initComponents();
        JFrame frame = new JFrame();
//        this.PnlPrincipal.setVisible(false);
//        this.setDefaultCloseOperation(0);
//        Dimension menuPrincipal = Toolkit.getDefaultToolkit().getScreenSize();
//        Dimension jFrameSize = menuPrincipal.getSize();
//        menuPrincipal.setSize(jFrameSize);
        
//        JScrollPane jScrollPane1 = new JScrollPane(panel);
//        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        JPanel jPanel2 = new JPanel(null);
//        jPanel2.add(jScrollPane1);
//        frame.setContentPane(jPanel2);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setVisible(true);
        
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
        this.cajaAbierta = this.obd.isCajaAbierta(this.modeloCaja.getId(), primeraVez);
        primeraVez = false;
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
        validarInicio();
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
    
    //valida el inicio
    public void validarInicio() {
        //Verifica si los parametros han sido creados, de no ser asi, abre la ventana para crearlos.
        int verificarParametros = PuntoVenta.BaseDatos.ObjetoBaseDatos.verificarParametros();
        int verificarUsuarios = PuntoVenta.BaseDatos.ObjetoBaseDatos.verificarUsuarios();
        
        if(verificarParametros > 0 && verificarUsuarios > 0) {
            this.login = new LogIn(this);
        } else if (verificarParametros == 0) {
            this.parametrosIniciales1 = new ParametrosIniciales1(this);
        } else {
            this.registroAdministrador = new RegistroAdministrador(this);
        }
    }
    
    public void crearListas() {
        ListadeBotones.add(btnCaja);
        ListadeBotones.add(btnVentas);
        ListadeBotones.add(btnFacturas);
        ListadeBotones.add(btnAdmin);
        ListadeBotones.add(btnInventario);
        ListadeBotones.add(btnProductos);
        ListadeBotones.add(btnAyuda);
        ListadeBotones.add(btnAcerca);
        ListadeBotones.add(btnBloqueo);
        ListadeBotones.add(btnCalculadora);
        ListadeBotones.add(btnSalir);
    }
    
    public void activarBtn(boolean aux){
        for(int i=0;i<ListadeBotones.size();i++){
            ListadeBotones.get(i).setVisible(aux);
        }
    }
    
    public void ejecutarCerrar(Object auxiliar) {
        int a=0;
        if(a==0){
            try {
                JDialog aux= (JDialog) auxiliar;
                aux.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                       activarBtn(true);
                    }
                });    
            }catch(Exception e){
                a=1;
            }   
        }
        if(a==1){
            try {
                JFrame aux= (JFrame) auxiliar;
                aux.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                       activarBtn(true);
                    }
                });    
            }catch(Exception e){
                a=2;
            }   
        }
        if(a==2){
            try {
                JInternalFrame aux= (JInternalFrame) auxiliar;
                aux.addInternalFrameListener ( new InternalFrameAdapter () {
                    @Override
                    public void internalFrameClosing ( InternalFrameEvent arg0 ) {
                        activarBtn(true);
                    }
                });
            }catch(Exception e){}
        }
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

        jToolBar1 = new javax.swing.JToolBar();
        btnCaja = new javax.swing.JButton();
        btnVentas = new javax.swing.JButton();
        btnFacturas = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnAyuda = new javax.swing.JButton();
        btnAcerca = new javax.swing.JButton();
        btnBloqueo = new javax.swing.JButton();
        btnCalculadora = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setForeground(new java.awt.Color(255, 0, 51));
        jToolBar1.setRollover(true);

        btnCaja.setBackground(new java.awt.Color(255, 255, 255));
        btnCaja.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnCaja.setForeground(new java.awt.Color(28, 90, 125));
        btnCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/16-caja.png"))); // NOI18N
        btnCaja.setText("<html><center>Caja<br>F1</center></html>");
        btnCaja.setActionCommand("actionCaja");
        btnCaja.setBorder(null);
        btnCaja.setFocusable(false);
        btnCaja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCaja.setNextFocusableComponent(btnVentas);
        btnCaja.setPreferredSize(new java.awt.Dimension(130, 80));
        btnCaja.setSelected(true);
        btnCaja.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCaja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCajaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCajaMouseExited(evt);
            }
        });
        btnCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCajaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCaja);

        btnVentas.setBackground(new java.awt.Color(255, 255, 255));
        btnVentas.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnVentas.setForeground(new java.awt.Color(28, 90, 125));
        btnVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/12-ventas.png"))); // NOI18N
        btnVentas.setActionCommand("actionVentas");
        btnVentas.setBorder(null);
        btnVentas.setFocusable(false);
        btnVentas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnVentas.setLabel("<html><center>Ventas<br>F2</center></html>");
        btnVentas.setPreferredSize(new java.awt.Dimension(130, 80));
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

        btnFacturas.setBackground(new java.awt.Color(255, 255, 255));
        btnFacturas.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnFacturas.setForeground(new java.awt.Color(28, 90, 125));
        btnFacturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/11-factura.png"))); // NOI18N
        btnFacturas.setText("<html><center>Facturas<br>F4</center></html>");
        btnFacturas.setActionCommand("actionFacturas");
        btnFacturas.setBorder(null);
        btnFacturas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFacturas.setNextFocusableComponent(btnCaja);
        btnFacturas.setPreferredSize(new java.awt.Dimension(130, 80));
        btnFacturas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturasActionPerformed(evt);
            }
        });
        jToolBar1.add(btnFacturas);

        btnAdmin.setBackground(new java.awt.Color(255, 255, 255));
        btnAdmin.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnAdmin.setForeground(new java.awt.Color(28, 90, 125));
        btnAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/13-admin.png"))); // NOI18N
        btnAdmin.setText("<html><center>Admin<br>F6</center></html>");
        btnAdmin.setActionCommand("actionCaja");
        btnAdmin.setBorder(null);
        btnAdmin.setFocusable(false);
        btnAdmin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmin.setNextFocusableComponent(btnVentas);
        btnAdmin.setPreferredSize(new java.awt.Dimension(130, 80));
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

        btnInventario.setBackground(new java.awt.Color(255, 255, 255));
        btnInventario.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnInventario.setForeground(new java.awt.Color(28, 90, 125));
        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/2-inventario.png"))); // NOI18N
        btnInventario.setText("<html><center>Inventario<br>F8</center></html>");
        btnInventario.setBorder(null);
        btnInventario.setFocusable(false);
        btnInventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInventario.setPreferredSize(new java.awt.Dimension(130, 80));
        btnInventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        btnInventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnInventarioKeyPressed(evt);
            }
        });
        jToolBar1.add(btnInventario);

        btnProductos.setBackground(new java.awt.Color(255, 255, 255));
        btnProductos.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnProductos.setForeground(new java.awt.Color(28, 90, 125));
        btnProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/3-producto.png"))); // NOI18N
        btnProductos.setText("<html><center>Productos<br>F9</center></html>");
        btnProductos.setBorder(null);
        btnProductos.setFocusable(false);
        btnProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProductos.setPreferredSize(new java.awt.Dimension(130, 80));
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

        btnAyuda.setBackground(new java.awt.Color(255, 255, 255));
        btnAyuda.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnAyuda.setForeground(new java.awt.Color(28, 90, 125));
        btnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/15-ayuda.png"))); // NOI18N
        btnAyuda.setText("<html><center>Ayuda<br>F10</center></html>");
        btnAyuda.setBorder(null);
        btnAyuda.setFocusable(false);
        btnAyuda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAyuda.setPreferredSize(new java.awt.Dimension(130, 80));
        btnAyuda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAyuda);

        btnAcerca.setBackground(new java.awt.Color(255, 255, 255));
        btnAcerca.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnAcerca.setForeground(new java.awt.Color(28, 90, 125));
        btnAcerca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/14-acerca.png"))); // NOI18N
        btnAcerca.setText("<html><center>Acerca<br>F11</center></html>");
        btnAcerca.setBorder(null);
        btnAcerca.setFocusable(false);
        btnAcerca.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAcerca.setPreferredSize(new java.awt.Dimension(130, 80));
        btnAcerca.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcercaActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAcerca);

        btnBloqueo.setBackground(new java.awt.Color(255, 255, 255));
        btnBloqueo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnBloqueo.setForeground(new java.awt.Color(28, 90, 125));
        btnBloqueo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/10-bloqueo.png"))); // NOI18N
        btnBloqueo.setText("<html><center>Bloqueo<br>F12</center></html>");
        btnBloqueo.setBorder(null);
        btnBloqueo.setFocusable(false);
        btnBloqueo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBloqueo.setPreferredSize(new java.awt.Dimension(130, 80));
        btnBloqueo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBloqueo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBloqueoMouseClicked(evt);
            }
        });
        btnBloqueo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBloqueoActionPerformed(evt);
            }
        });
        btnBloqueo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBloqueoKeyPressed(evt);
            }
        });
        jToolBar1.add(btnBloqueo);

        btnCalculadora.setBackground(new java.awt.Color(255, 255, 255));
        btnCalculadora.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnCalculadora.setForeground(new java.awt.Color(28, 90, 125));
        btnCalculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/1-calculadora.png"))); // NOI18N
        btnCalculadora.setText("<html><center>Calculadora<br>FX</center></html>");
        btnCalculadora.setBorder(null);
        btnCalculadora.setFocusable(false);
        btnCalculadora.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCalculadora.setPreferredSize(new java.awt.Dimension(130, 80));
        btnCalculadora.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCalculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculadoraActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCalculadora);

        btnSalir.setBackground(new java.awt.Color(255, 255, 255));
        btnSalir.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(28, 90, 125));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/4-salir.png"))); // NOI18N
        btnSalir.setText("<html><center>Salir<br>XX</center></html>");
        btnSalir.setBorder(null);
        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setPreferredSize(new java.awt.Dimension(130, 80));
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSalir);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrarAplicacion();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCalculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculadoraActionPerformed
        abrirCalculadora();
    }//GEN-LAST:event_btnCalculadoraActionPerformed

    private void btnBloqueoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBloqueoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F12) {
            abrirVentanaBloqueo();
        }
    }//GEN-LAST:event_btnBloqueoKeyPressed

    private void btnBloqueoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBloqueoActionPerformed
        abrirVentanaBloqueo();
    }//GEN-LAST:event_btnBloqueoActionPerformed

    private void btnBloqueoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBloqueoMouseClicked

    }//GEN-LAST:event_btnBloqueoMouseClicked

    private void btnAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcercaActionPerformed
        acerca();
    }//GEN-LAST:event_btnAcercaActionPerformed

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        ayuda();
    }//GEN-LAST:event_btnAyudaActionPerformed

    private void btnProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            abrirVentanaProductos();
        }
    }//GEN-LAST:event_btnProductosKeyPressed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        abrirVentanaProductos();
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnInventarioKeyPressed(java.awt.event.KeyEvent evt) {                                         
        if (evt.getKeyCode() == KeyEvent.VK_F8) {
            abrirVentanaInventario();
        }
    }                                        

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        abrirVentanaInventario();
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAdminKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F6) {
            abrirVentanaAdmin();
        }
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
//        if (estacerrado(caja)) {
//            habilitar();
            abrirVentanaCaja();
//            PuntoVenta.Inicio.MenuPrincipal.btnVentas.setEnabled(false);
            
//        } else if (estacerrado(caja)) {
//            deshabilitar();
//        }
    }//GEN-LAST:event_btnCajaActionPerformed

    private void btnCajaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaMouseEntered
//        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnCajaMouseEntered

    private void btnCajaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCajaMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnCajaMouseExited

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrarAplicacion();
    }//GEN-LAST:event_formWindowClosing

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
    //  if(PuntoVenta.Inicio.MenuPrincipal.btnbloqueo.isEnabled()){
        btnCaja.setEnabled(false);
        btnVentas.setEnabled(false);
        btnFacturas.setEnabled(false);
        btnAyuda.setEnabled(false);
        btnAcerca.setEnabled(false);
        btnAdmin.setEnabled(false);        
        btnProductos.setEnabled(false);
        btnInventario.setEnabled(false);
     // }
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
        btnInventario.setEnabled(true);
     //  }
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnAcerca;
    public static javax.swing.JButton btnAdmin;
    public static javax.swing.JButton btnAyuda;
    public static javax.swing.JButton btnBloqueo;
    public static javax.swing.JButton btnCaja;
    private javax.swing.JButton btnCalculadora;
    public static javax.swing.JButton btnFacturas;
    public static javax.swing.JButton btnInventario;
    public static javax.swing.JButton btnProductos;
    private javax.swing.JButton btnSalir;
    public static javax.swing.JButton btnVentas;
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
            JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.venta.getTitle()+ "\nya esta abierta...");
        }
    }

    public void abrirVentanaAdmin() {
        currentLoger c = new currentLoger();
        Empleado emple = c.getDatosEmpleadoLogueado();
        
//        System.out.println("abrirVentanaAdmin se ha llamado");
        
        if(estacerrado(admin)) {
            if (emple.getCargo_id().equals("1")) {
                admin = new Admin(this);
                Dimension desktopSize = panel.getSize();
                Dimension jInternalFrameSize = admin.getSize();
                admin.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                panel.add(admin);
                admin.show();
            } else {
                ClaveSuperUsuario csu = new ClaveSuperUsuario(this, true, this);
                csu.setVisible(true);
                if(csu.autenticarClave(true)) {
                    admin = new Admin(this);
                    Dimension desktopSize = panel.getSize();
                    Dimension jInternalFrameSize = admin.getSize();
                    admin.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                    panel.add(admin);
                    admin.show();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.admin.getTitle()+ "\nya esta abierta...");
        }
    }

    public void abrirVentanaBloqueo() {

        if (estacerrado(bloqueo)) {
            habilitar();
            bloqueo = new Bloqueo(this);
            Dimension desktopSize = panel.getSize();
            Dimension jInternalFrameSize = bloqueo.getSize();
            bloqueo.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                                (desktopSize.height - jInternalFrameSize.height) / 2);
            panel.add(bloqueo);
           
            
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
            JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.caja.getTitle()+ "\nya esta abierta...");
        }
    }

    public void abrirVentanaFactura() {
        if (estacerrado(factura)){
            factura = new Factura(this);
            Dimension desktopSize = panel.getSize();
            Dimension jInternalFrameSize = factura.getSize();
            factura.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            panel.add(factura);
            factura.show();
        } else{
            JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.factura.getTitle()+ "\nya esta abierta...");
        }
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
            JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.calc.getTitle()+ "\nya esta abierta...");
            calc.moveToFront();
        }
    }
    
  
   
    public void abrirVentanaProductos(){
      
        if (estacerrado(productos)) {
            productos = new Productos(this);
            Dimension desktopSize = panel.getSize();
            Dimension jInternalFrameSize = productos.getSize();
            productos.setLocation((desktopSize.width -jInternalFrameSize.width)/ 2,
                (desktopSize.height - jInternalFrameSize.height )/ 2);
                panel.add(productos);
                productos.show();
                setVentanaAbierta(Modulo.PRODUCTOS);
        } else {
           JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.productos.getTitle()+ "\nya esta abierta...");
           productos.moveToFront();
        }
      
    }
    
    public void abrirVentanaInventario(){
         if (estacerrado(inventario)) {
            inventario =  new Inventario(this);
            Dimension desktopSize = panel.getSize();
            Dimension jInternalFrameSize = inventario.getSize();
            inventario.setLocation((desktopSize.width -jInternalFrameSize.width)/ 2,
                (desktopSize.height - jInternalFrameSize.height )/ 2);
                panel.add(inventario);
            inventario.show();
            setVentanaAbierta(Modulo.INVENTARIO);
        } else {
           JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.inventario.getTitle()+ "\nya esta abierta...");
           inventario.moveToFront();
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
                 
                    abrirVentanaProductos();
                }
            };
            
            //añadido a movimientos
            Action actInventario = new AbstractAction (Modulo.INVENTARIO.getAction()){
            
            @Override
             
            public void actionPerformed(ActionEvent e){
            
                abrirVentanaInventario();
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
        actInventario.putValue(Action.ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_F8,0));

        
        getBtnCaja().getActionMap().put(Modulo.CAJA.getAction(), actCaja);
        getBtnCaja().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCaja.getValue(Action.ACCELERATOR_KEY), Modulo.CAJA.getAction());
        
        btnVentas.getActionMap().put(Modulo.VENTAS.getAction(), actVentas);
        btnVentas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actVentas.getValue(Action.ACCELERATOR_KEY), Modulo.VENTAS.getAction());

        btnFacturas.getActionMap().put(Modulo.FACTURAS.getAction(), actFacturas);
        btnFacturas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFacturas.getValue(Action.ACCELERATOR_KEY), Modulo.FACTURAS.getAction());

        btnAdmin.getActionMap().put(Modulo.ADMIN.getAction(), actAdmin);
        btnAdmin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAdmin.getValue(Action.ACCELERATOR_KEY), Modulo.ADMIN.getAction());

        btnBloqueo.getActionMap().put(Modulo.BLOQUEO.getAction(), actbloqueo);
        btnBloqueo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actbloqueo.getValue(Action.ACCELERATOR_KEY), Modulo.BLOQUEO.getAction());

        btnAyuda.getActionMap().put(Modulo.AYUDA.getAction(), actAyuda);
        btnAyuda.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAyuda.getValue(Action.ACCELERATOR_KEY), Modulo.AYUDA.getAction());

        btnAcerca.getActionMap().put(Modulo.ACERCA.getAction(), actAcerca);
        btnAcerca.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAcerca.getValue(Action.ACCELERATOR_KEY), Modulo.ACERCA.getAction());
        
        btnProductos.getActionMap().put(Modulo.PRODUCTOS.getAction(),actProducto);
        btnProductos.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)actProducto.getValue(Action.ACCELERATOR_KEY),Modulo.PRODUCTOS.getAction());   //  producto
        
        btnInventario.getActionMap().put(Modulo.INVENTARIO.getAction(), actInventario);
        btnInventario.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke)actInventario.getValue(Action.ACCELERATOR_KEY),Modulo.INVENTARIO.getAction());  // movimiento
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
     
      public javax.swing.JButton getBtnInventario() {
        return btnInventario;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void sincronizar() {
        String servidor = configuracion.getProperty("servidor_api");
        String apiKey = configuracion.getProperty("api_key");
        
        if(servidor != null && apiKey != null){
            cliente = new Cliente(obd, servidor, apiKey);
            cliente.sincronizar();
        }
        else {
            JOptionPane.showMessageDialog(null, "No se han establecido las variables de configuración del sistema de inventario", "Error de configuración", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarAplicacion() {
        javax.swing.JOptionPane mensajedeerror = new javax.swing.JOptionPane();
        int g = JOptionPane.showConfirmDialog(this, "¿Desea salir del sistema ahora?\nSe realizará una sincronización antes de cerrar la aplicación.", "Saphiro - Salir", JOptionPane.YES_NO_OPTION);

        if (g == JOptionPane.YES_OPTION) {
            cliente.sincronizar();
            System.exit(0);
        } else if (g == JOptionPane.NO_OPTION) {
            this.setVisible(true);
        }
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
        INVENTARIO(9,"actionInventario"), // nueva ventana
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
