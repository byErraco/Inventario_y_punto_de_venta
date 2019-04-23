package PuntoVenta.Ventanas;

import ClasesExtendidas.Numeros.XBigDecimal;
import ClasesExtendidas.Tablas.ProductoVentaTableModel;
import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import PuntoVenta.Inicio.MenuPrincipal;
import PuntoVenta.Modelos.ModeloCliente;
import PuntoVenta.Modelos.ModeloProducto;

import Administrador.Ventanas.RegistroCliente;
import PuntoVenta.BaseDatos.Pais;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Venta extends javax.swing.JInternalFrame {

    private ProductoVentaTableModel jtbVentaModel;
            
    private DecimalFormat redondeo = new DecimalFormat("0.00");
    public final MenuPrincipal menuPrincipal;
    public String impuesto;
    public RegistroCliente registroCliente;
    public ListaClientes ventanaCliente;
    private boolean clienteAsociadoFactura;
    ModeloProducto productoPorAsociar;

    String ti = "";

    HashMap<String, Double> productoTabla = new HashMap<>();

    public ListaProductos buscarProducto;
    private int idVenta = -1;
    public Pago totalizarVenta;

    public static class PeriodoLimite {
        
        /**
         * Devuelve la descripción de un periodo según su id
         * 
         * @param id_periodo_venta_producto
         * @return String con la descripción del período
         */
        public static String getDescripcion(int id_periodo_venta_producto) {
            switch (id_periodo_venta_producto) {
                case 1:
                    return "Diario";
                case 2:
                    return "Semanal";
            }
            return "";
        }
    }
    
    public Venta(final MenuPrincipal menuPrincipal) {
        initComponents();
        this.menuPrincipal = menuPrincipal;
        this.setClienteAsociadoFactura(false);
//        MenuPrincipal.btnAcerca.setVisible(false);
//        MenuPrincipal.btnAyuda.setVisible(false);
//        MenuPrincipal.btnAdmin.setVisible(false);
//        MenuPrincipal.btnCaja.setVisible(false);
//        MenuPrincipal.btnFacturas.setVisible(false);
//        MenuPrincipal.btnMovimientos.setVisible(false);
//        MenuPrincipal.btnVentas.setVisible(false);
//        MenuPrincipal.btnbloqueo.setVisible(false);
//        MenuPrincipal.btnSalir.setVisible(false);
//        MenuPrincipal.btnCalculadora.setVisible(false);
        
        this.setTitle("Saphiro - Cajero: " + menuPrincipal.getEmpleado().getNombre() + " " + menuPrincipal.getEmpleado().getApellido());
        identificarPais();
        actualizarTabla();
        crearHotKeys();
        setBotonesVentaEnabled(false);
        lblProductoPrecio.setVisible(false);
    }

    private void crearHotKeys() {
        Action actCerrarVentana = new AbstractAction("actionCerrarVentanaCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        };
        Action actFocusPuntoInteres = new AbstractAction("actionFocusPuntoInteres") {
            @Override
            public void actionPerformed(ActionEvent e) {
                focusPuntoInteres();
            }
        };
        Action actProductos = new AbstractAction("actionProductos") {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaBuscarProductos();
            }
        };
        Action actCliente = new AbstractAction("actionClientes") {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaCliente();
            }
        };
        Action actTotalizar = new AbstractAction("actionTotalizar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaTotalizarVenta();
                MenuPrincipal.btnVentas.setEnabled(false);
                MenuPrincipal.btnCaja.setEnabled(false);
            }
        };
        Action actEliminar = new AbstractAction("actionEliminar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component tr = null;
                String m = "Introduce la clave del supervisor:";
                String t = "bloqueo";
                try {
                    contra(tr, m, t);
                } catch (SQLException ex) {
                    Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Action actCancelar = new AbstractAction("actionCancelar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarVenta();
            }
        };
        Action actModificar = new AbstractAction("actionModificar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarCantidadProductoSeleccionado();
            }
        };
        Action actEsperar = new AbstractAction("actionEsperar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausarVenta();
            }
        };
        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        actFocusPuntoInteres.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        actProductos.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        actCliente.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        actTotalizar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        actEliminar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        actCancelar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
        actModificar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
        actEsperar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));

        pnlContenedor.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

        pnlContenedor.getActionMap().put("actionFocusPuntoInteres", actFocusPuntoInteres);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFocusPuntoInteres.getValue(Action.ACTION_COMMAND_KEY), "actionFocusPuntoInteres");

        btnProductos.getActionMap().put("actionProductos", actProductos);
        btnProductos.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actProductos.getValue(Action.ACCELERATOR_KEY), "actionProductos");

        btnClientes.getActionMap().put("actionClientes", actCliente);
        btnClientes.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCliente.getValue(Action.ACCELERATOR_KEY), "actionClientes");

        btnTotalizar.getActionMap().put("actionTotalizar", actTotalizar);
        btnTotalizar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actTotalizar.getValue(Action.ACCELERATOR_KEY), "actionTotalizar");

        btnEliminar.getActionMap().put("actionImprimirCaja", actEliminar);
        btnEliminar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actEliminar.getValue(Action.ACCELERATOR_KEY), "actionImprimirCaja");

        btnCancelar.getActionMap().put("actionCancelar", actCancelar);
        btnCancelar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCancelar.getValue(Action.ACCELERATOR_KEY), "actionCancelar");

        btnModificar.getActionMap().put("actionModificar", actModificar);
        btnModificar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actModificar.getValue(Action.ACCELERATOR_KEY), "actionModificar");

        btnEsperar.getActionMap().put("actionEsperar", actEsperar);
        btnEsperar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actEsperar.getValue(Action.ACCELERATOR_KEY), "actionEsperar");

        InternalFrameAdapter listener = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                menuPrincipal.getBtnCaja().requestFocus();
            }
        };

        this.addInternalFrameListener(listener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        pnlContenedor = new javax.swing.JPanel();
        pnlInformacionVenta = new javax.swing.JPanel();
        lblDocumento = new javax.swing.JLabel();
        lblNumeroFactura = new javax.swing.JLabel();
        txtNumeroFactura = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        lblNombres = new javax.swing.JLabel();
        cmbTipoDocumento = new javax.swing.JComboBox();
        lblFechaFactura = new javax.swing.JLabel();
        lblValorFechaFactura = new javax.swing.JLabel();
        info1 = new javax.swing.JLabel();
        pnlInformacionProducto = new javax.swing.JPanel();
        lblIdProducto = new javax.swing.JLabel();
        txtProductoId = new javax.swing.JTextField();
        lblProductoNombre = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblProductoPrecio = new javax.swing.JLabel();
        lblProductoPrecioValor = new javax.swing.JLabel();
        lblCantidad1 = new javax.swing.JLabel();
        txtProductoNombre = new javax.swing.JTextField();
        error = new javax.swing.JLabel();
        info2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtAlmacen = new javax.swing.JTextField();
        txtLote = new javax.swing.JTextField();
        lblAlmacen = new javax.swing.JLabel();
        lblLote = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbVenta = new javax.swing.JTable();
        pnlBotones = new javax.swing.JPanel();
        btnProductos = new javax.swing.JButton();
        btnTotalizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEsperar = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        pnlTotal = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblTotalValor = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblImpuesto = new javax.swing.JLabel();
        lblSubtotalValor = new javax.swing.JLabel();
        lblImpuestoValor = new javax.swing.JLabel();
        info3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jButton4.setText("Terminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        setClosable(true);
        setResizable(true);

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));
        ///jPanel6.setBackground(new java.awt.Color(-15589839));*/
        pnlContenedor.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlContenedorComponentResized(evt);
            }
        });

        pnlInformacionVenta.setBackground(new java.awt.Color(255, 255, 255));
        pnlInformacionVenta.setForeground(new java.awt.Color(255, 255, 255));
        pnlInformacionVenta.setMaximumSize(new java.awt.Dimension(386, 143));
        pnlInformacionVenta.setMinimumSize(new java.awt.Dimension(286, 143));
        ///jPanel4.setBackground(new java.awt.Color(-15589839));

        lblDocumento.setBackground(new java.awt.Color(255, 255, 255));
        lblDocumento.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblDocumento.setForeground(new java.awt.Color(28, 90, 125));
        lblDocumento.setText("CI / RIF:");

        lblNumeroFactura.setBackground(new java.awt.Color(255, 255, 255));
        lblNumeroFactura.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblNumeroFactura.setForeground(new java.awt.Color(28, 90, 125));
        lblNumeroFactura.setText("Factura:");

        txtNumeroFactura.setEditable(false);
        txtNumeroFactura.setBackground(new java.awt.Color(255, 255, 255));
        txtNumeroFactura.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtNumeroFactura.setPreferredSize(new java.awt.Dimension(600, 28));

        txtDocumento.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtDocumento.setMaximumSize(new java.awt.Dimension(700, 28));
        txtDocumento.setMinimumSize(new java.awt.Dimension(440, 28));
        txtDocumento.setNextFocusableComponent(jtbVenta);
        txtDocumento.setPreferredSize(new java.awt.Dimension(440, 28));
        txtDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyTyped(evt);
            }
        });

        txtNombreCliente.setEditable(false);
        txtNombreCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtNombreCliente.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtNombreCliente.setNextFocusableComponent(txtNumeroFactura);
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        lblNombres.setBackground(new java.awt.Color(255, 255, 255));
        lblNombres.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblNombres.setForeground(new java.awt.Color(28, 90, 125));
        lblNombres.setText("Nombre:");

        cmbTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E" }));
        cmbTipoDocumento.setToolTipText("");
        cmbTipoDocumento.setName(""); // NOI18N

        lblFechaFactura.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblFechaFactura.setForeground(new java.awt.Color(28, 90, 125));
        lblFechaFactura.setText("Fecha de factura:");

        lblValorFechaFactura.setBackground(new java.awt.Color(255, 255, 255));
        lblValorFechaFactura.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N

        info1.setBackground(new java.awt.Color(255, 255, 255));
        info1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        info1.setForeground(new java.awt.Color(20, 90, 125));
        info1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/cuadro2-390x170.png"))); // NOI18N

        javax.swing.GroupLayout pnlInformacionVentaLayout = new javax.swing.GroupLayout(pnlInformacionVenta);
        pnlInformacionVenta.setLayout(pnlInformacionVentaLayout);
        pnlInformacionVentaLayout.setHorizontalGroup(
            pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                        .addComponent(lblNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                        .addComponent(lblFechaFactura)
                        .addGap(18, 18, 18)
                        .addComponent(lblValorFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                        .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombres)
                            .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                                .addComponent(cmbTipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(info1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(11, Short.MAX_VALUE)))
        );
        pnlInformacionVentaLayout.setVerticalGroup(
            pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacionVentaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDocumento)
                    .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbTipoDocumento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombres)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroFactura))
                .addGap(14, 14, 14)
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblValorFechaFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                    .addComponent(info1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 19, Short.MAX_VALUE)))
        );

        pnlInformacionProducto.setBackground(new java.awt.Color(255, 255, 255));
        //jPanel3.setBackground(new java.awt.Color(-15589839));
        pnlInformacionProducto.setForeground(new java.awt.Color(255, 255, 255));

        lblIdProducto.setBackground(new java.awt.Color(255, 255, 255));
        lblIdProducto.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblIdProducto.setForeground(new java.awt.Color(28, 90, 125));
        lblIdProducto.setText("Código:");

        txtProductoId.setEditable(false);
        txtProductoId.setBackground(new java.awt.Color(255, 255, 255));
        txtProductoId.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtProductoId.setNextFocusableComponent(jtbVenta);
        txtProductoId.setPreferredSize(new java.awt.Dimension(103, 25));
        txtProductoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoIdActionPerformed(evt);
            }
        });
        txtProductoId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductoIdKeyPressed(evt);
            }
        });

        lblProductoNombre.setBackground(new java.awt.Color(255, 255, 255));
        lblProductoNombre.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblProductoNombre.setForeground(new java.awt.Color(28, 90, 125));
        lblProductoNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblProductoNombre.setText("Nombre:");

        txtCantidad.setEditable(false);
        txtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidad.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtCantidad.setPreferredSize(new java.awt.Dimension(103, 25));
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        lblProductoPrecio.setBackground(new java.awt.Color(255, 255, 255));
        lblProductoPrecio.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblProductoPrecio.setForeground(new java.awt.Color(28, 90, 125));
        lblProductoPrecio.setText("Precio unitario:");

        lblProductoPrecioValor.setBackground(new java.awt.Color(255, 255, 255));
        lblProductoPrecioValor.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblProductoPrecioValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblCantidad1.setBackground(new java.awt.Color(255, 255, 255));
        lblCantidad1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblCantidad1.setForeground(new java.awt.Color(28, 90, 125));
        lblCantidad1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCantidad1.setText("Cantidad:");

        txtProductoNombre.setEditable(false);
        txtProductoNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtProductoNombre.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtProductoNombre.setPreferredSize(new java.awt.Dimension(103, 25));
        txtProductoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductoNombreActionPerformed(evt);
            }
        });
        txtProductoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductoNombreKeyPressed(evt);
            }
        });

        info2.setBackground(new java.awt.Color(255, 255, 255));
        info2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        info2.setForeground(new java.awt.Color(20, 90, 125));
        info2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/cuadro2-390x170.png"))); // NOI18N

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(28, 90, 125));
        jLabel12.setText("Productos");

        txtAlmacen.setEditable(false);
        txtAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        txtAlmacen.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtAlmacen.setPreferredSize(new java.awt.Dimension(103, 25));
        txtAlmacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlmacenKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAlmacenKeyReleased(evt);
            }
        });

        txtLote.setEditable(false);
        txtLote.setBackground(new java.awt.Color(255, 255, 255));
        txtLote.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtLote.setPreferredSize(new java.awt.Dimension(103, 25));
        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoteKeyReleased(evt);
            }
        });

        lblAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        lblAlmacen.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblAlmacen.setForeground(new java.awt.Color(28, 90, 125));
        lblAlmacen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAlmacen.setText("Almacén:");

        lblLote.setBackground(new java.awt.Color(255, 255, 255));
        lblLote.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblLote.setForeground(new java.awt.Color(28, 90, 125));
        lblLote.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLote.setText("Lote:");

        javax.swing.GroupLayout pnlInformacionProductoLayout = new javax.swing.GroupLayout(pnlInformacionProducto);
        pnlInformacionProducto.setLayout(pnlInformacionProductoLayout);
        pnlInformacionProductoLayout.setHorizontalGroup(
            pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(error)
                            .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblProductoPrecioValor, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                                            .addComponent(lblCantidad1)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                                        .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                                                .addComponent(lblProductoNombre)
                                                .addGap(27, 27, 27))
                                            .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                                                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblIdProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lblAlmacen))
                                                .addGap(18, 18, 18)))
                                        .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtProductoId, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtProductoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblLote)))))
                    .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblProductoPrecio))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                    .addComponent(info2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 28, Short.MAX_VALUE)))
        );
        pnlInformacionProductoLayout.setVerticalGroup(
            pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(0, 35, Short.MAX_VALUE)
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdProducto)
                    .addComponent(txtProductoId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductoNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                        .addComponent(txtAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLote)))
                    .addComponent(lblAlmacen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidad1)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(error))
                    .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProductoPrecioValor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoPrecio))))
                .addGap(46, 46, 46))
            .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(info2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
        );

        jtbVenta.setBackground(new java.awt.Color(204, 204, 204));
        jtbVenta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtbVenta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        /*
        jtbVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        */
        //tab_result.setBackground(new java.awt.Color(-15589839));
        jtbVenta.setShowGrid(true);
        jtbVenta.setShowVerticalLines(true);
        jtbVenta.setGridColor(new java.awt.Color(51, 51, 255));
        jtbVenta.setNextFocusableComponent(txtProductoId);
        jtbVenta.setRowHeight(20);
        jtbVenta.setSelectionBackground(new java.awt.Color(67, 69, 253));
        jScrollPane2.setViewportView(jtbVenta);

        pnlBotones.setBackground(new java.awt.Color(255, 255, 255));

        btnProductos.setBackground(new java.awt.Color(32, 182, 155));
        btnProductos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnProductos.setForeground(new java.awt.Color(255, 255, 255));
        btnProductos.setText("<html><center>Productos<br> F2</center></html>");
        btnProductos.setToolTipText("");
        btnProductos.setActionCommand("actionProductos");
        btnProductos.setBorder(null);
        btnProductos.setMaximumSize(new java.awt.Dimension(200, 35));
        btnProductos.setMinimumSize(new java.awt.Dimension(80, 35));
        btnProductos.setPreferredSize(new java.awt.Dimension(90, 35));
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        //totalizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        btnTotalizar.setBackground(new java.awt.Color(32, 182, 155));
        btnTotalizar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnTotalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnTotalizar.setText("<html><center>Totalizar<br>F4</center></html>");
        btnTotalizar.setToolTipText("");
        btnTotalizar.setActionCommand("actionTotalizar");
        btnTotalizar.setBorder(null);
        btnTotalizar.setMaximumSize(new java.awt.Dimension(200, 35));
        btnTotalizar.setMinimumSize(new java.awt.Dimension(80, 35));
        btnTotalizar.setPreferredSize(new java.awt.Dimension(90, 41));
        btnTotalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalizarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(32, 182, 155));
        btnEliminar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("<html><center>Eliminar<br>F5</center></html>");
        btnEliminar.setToolTipText("");
        btnEliminar.setActionCommand("actionEliminar");
        btnEliminar.setBorder(null);
        btnEliminar.setMaximumSize(new java.awt.Dimension(200, 35));
        btnEliminar.setMinimumSize(new java.awt.Dimension(80, 35));
        btnEliminar.setPreferredSize(new java.awt.Dimension(90, 35));
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        btnEliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEliminarKeyPressed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(32, 182, 155));
        btnCancelar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("<html><center>Cancelar<br>F6</center></html>");
        btnCancelar.setToolTipText("");
        btnCancelar.setBorder(null);
        btnCancelar.setMaximumSize(new java.awt.Dimension(200, 35));
        btnCancelar.setMinimumSize(new java.awt.Dimension(80, 35));
        btnCancelar.setPreferredSize(new java.awt.Dimension(90, 36));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(32, 182, 155));
        btnModificar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("<html><center>Modificar<br>F7</center></html>");
        btnModificar.setToolTipText("");
        btnModificar.setBorder(null);
        btnModificar.setMaximumSize(new java.awt.Dimension(200, 35));
        btnModificar.setMinimumSize(new java.awt.Dimension(80, 35));
        btnModificar.setPreferredSize(new java.awt.Dimension(90, 35));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEsperar.setBackground(new java.awt.Color(32, 182, 155));
        btnEsperar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEsperar.setForeground(new java.awt.Color(255, 255, 255));
        btnEsperar.setText("<html><center>Esperar<br>F8</center></html>");
        btnEsperar.setToolTipText("");
        btnEsperar.setBorder(null);
        btnEsperar.setMaximumSize(new java.awt.Dimension(200, 35));
        btnEsperar.setMinimumSize(new java.awt.Dimension(80, 35));
        btnEsperar.setPreferredSize(new java.awt.Dimension(90, 36));
        btnEsperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsperarActionPerformed(evt);
            }
        });

        btnClientes.setBackground(new java.awt.Color(32, 182, 155));
        btnClientes.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnClientes.setText("<html><center>Clientes<br> F3</center></html>");
        btnClientes.setActionCommand("actionClientes");
        btnClientes.setBorder(null);
        btnClientes.setMaximumSize(new java.awt.Dimension(200, 35));
        btnClientes.setMinimumSize(new java.awt.Dimension(80, 35));
        btnClientes.setPreferredSize(new java.awt.Dimension(90, 35));
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addComponent(btnProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTotalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEsperar, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTotalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEsperar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        pnlTotal.setBackground(new java.awt.Color(255, 255, 255));
        ///jPanel5.setBackground(new java.awt.Color(-15589839));

        lblTotal.setBackground(new java.awt.Color(255, 255, 255));
        lblTotal.setFont(new java.awt.Font("SansSerif", 1, 28)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(28, 90, 125));
        lblTotal.setText("Total:");

        lblTotalValor.setBackground(new java.awt.Color(0, 0, 0));
        lblTotalValor.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        lblTotalValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalValor.setText("0.00");

        lblSubtotal.setBackground(new java.awt.Color(255, 255, 255));
        lblSubtotal.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(28, 90, 125));
        lblSubtotal.setText("Subtotal:");

        lblImpuesto.setBackground(new java.awt.Color(255, 255, 255));
        lblImpuesto.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblImpuesto.setForeground(new java.awt.Color(28, 90, 125));
        lblImpuesto.setText("Impuesto:");

        lblSubtotalValor.setFont(new java.awt.Font("SansSerif", 0, 28)); // NOI18N
        lblSubtotalValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSubtotalValor.setText("0.00");

        lblImpuestoValor.setFont(new java.awt.Font("SansSerif", 0, 28)); // NOI18N
        lblImpuestoValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblImpuestoValor.setText("0.00");

        info3.setBackground(new java.awt.Color(255, 255, 255));
        info3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        info3.setForeground(new java.awt.Color(20, 90, 125));
        info3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/cuadro2-390x170.png"))); // NOI18N

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(28, 90, 125));
        jLabel13.setText("Total");

        javax.swing.GroupLayout pnlTotalLayout = new javax.swing.GroupLayout(pnlTotal);
        pnlTotal.setLayout(pnlTotalLayout);
        pnlTotalLayout.setHorizontalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTotalLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlTotalLayout.createSequentialGroup()
                                .addComponent(lblSubtotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSubtotalValor, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTotalLayout.createSequentialGroup()
                                .addComponent(lblImpuesto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblImpuestoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlTotalLayout.createSequentialGroup()
                                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(lblTotalValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(276, Short.MAX_VALUE))
            .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlTotalLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(info3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(89, Short.MAX_VALUE)))
        );
        pnlTotalLayout.setVerticalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel13)
                .addGap(28, 28, 28)
                .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubtotal)
                    .addComponent(lblSubtotalValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImpuesto)
                    .addComponent(lblImpuestoValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblTotalValor))
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotalLayout.createSequentialGroup()
                    .addContainerGap(41, Short.MAX_VALUE)
                    .addComponent(info3, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(28, 28, 28)))
        );

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(28, 90, 125));
        jLabel11.setText("Ventas");

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(pnlInformacionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addComponent(pnlInformacionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlInformacionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlInformacionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(pnlContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyPressed
        List<Integer> keysPermitidos = Arrays.asList(KeyEvent.VK_F1, KeyEvent.VK_F2, KeyEvent.VK_F3, KeyEvent.VK_F4, KeyEvent.VK_F5, KeyEvent.VK_F6,
                KeyEvent.VK_F7, KeyEvent.VK_F8, KeyEvent.VK_F9, KeyEvent.VK_F10, KeyEvent.VK_F11, KeyEvent.VK_F12);

        if (this.isClienteAsociadoFactura() && evt.getKeyCode() != KeyEvent.VK_ENTER && !keysPermitidos.contains(evt.getKeyCode())) {
            setClienteAsociadoFactura(false);
            setIdVenta(-1);
            limpiarCamposTxt();
            actualizarTabla();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tipo_persona = getCmbTipoDocumento().getSelectedItem().toString();
            String numero_identificacion_persona = getTxtDocumento().getText();
            crearVenta(tipo_persona, numero_identificacion_persona);
        }
    }//GEN-LAST:event_txtDocumentoKeyPressed

    private void btnTotalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalizarActionPerformed
        abrirVentanaTotalizarVenta();
    }//GEN-LAST:event_btnTotalizarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificarCantidadProductoSeleccionado();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        abrirVentanaBuscarProductos();
    }//GEN-LAST:event_btnProductosActionPerformed
    
    /**
     * Devuelve todos los campos a su estado inicial para crear una nueva venta
     */
    public void limpiarVenta() {
        setClienteAsociadoFactura(false);
        idVenta = -1;
        limpiarCamposTxt();
        limpiarCamposProductoTxt();
        txtDocumento.setText("");
        lblValorFechaFactura.setText("");
        txtProductoNombre.setText("");
        lblProductoPrecioValor.setText("");
        actualizarTabla();
        setBotonesVentaEnabled(false);
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelarVenta();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEsperarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEsperarActionPerformed
        pausarVenta();
    }//GEN-LAST:event_btnEsperarActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        abrirVentanaCliente();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void pnlContenedorComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlContenedorComponentResized

    }//GEN-LAST:event_pnlContenedorComponentResized

    private void txtDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyTyped
        char aux = evt.getKeyChar();
        if (!Character.isDigit(aux)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDocumentoKeyTyped

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyTyped
        char aux = evt.getKeyChar();

        if (Character.isDigit(aux)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreClienteKeyTyped

    private void btnEliminarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            Component tr = null;
            String m = "Introduce la clave del supervisor:";
            String t = "bloqueo";
            try {
                contra(tr, m, t);

            } catch (SQLException ex) {
                Logger.getLogger(Venta.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEliminarKeyPressed

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        Component tr = null;
        String m = "Introduce la clave del supervisor:";
        String t = "Bloqueo";
        try {
            contra(tr, m, t);

        } catch (SQLException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnEliminarMouseClicked

    private void txtLoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyReleased
        cargarInformacionPrecioProducto();
    }//GEN-LAST:event_txtLoteKeyReleased

    private void txtAlmacenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlmacenKeyReleased
        cargarInformacionPrecioProducto();
    }//GEN-LAST:event_txtAlmacenKeyReleased

    private void txtAlmacenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlmacenKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAlmacenKeyTyped

    private void txtProductoNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoNombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            abrirVentanaBuscarProductos();
            String nom = txtProductoNombre.getText();
            PuntoVenta.Ventanas.ListaProductos.txtCampoDescripcion.setText(nom);

        }
    }//GEN-LAST:event_txtProductoNombreKeyPressed

    private void txtProductoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoNombreActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed

    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        if (productoPorAsociar != null) {
            int idUnidadInventario = menuPrincipal.getOBD().getIdUnidadInventario(productoPorAsociar.getId(), Integer.parseInt(txtAlmacen.getText()), txtLote.getText());

            if(idUnidadInventario != -1){
                XBigDecimal cantidad = new XBigDecimal(getTxtCantidad().getText());
                double cantidadVenta = Double.parseDouble(txtCantidad.getText());

                for (int i = 0; i < jtbVenta.getRowCount(); i++) {
                    if (jtbVentaModel.getValueAt(i, "Id de unidad de inventario").toString().equals(String.valueOf(idUnidadInventario))) {
                        cantidadVenta += Double.parseDouble(jtbVentaModel.getValueAt(i, "Cantidad").toString());
                    }
                }

                if (!sePuedeVender(productoPorAsociar.getCodigoBarra(), cantidadVenta)) {
                    JOptionPane.showMessageDialog(null, "El limite máximo de '" + productoPorAsociar.getDescripcion() + "' permitido por persona es " + productoPorAsociar.getLimiteVentaPorPersona() + " en un periodo " + PeriodoLimite.getDescripcion(productoPorAsociar.getIdPeriodoLimiteVenta()), "Error", JOptionPane.ERROR_MESSAGE);
                } else if (menuPrincipal.getOBD().consultastock(idVenta, idUnidadInventario, cantidadVenta)) {
                    menuPrincipal.getOBD().agregarProductoEnVenta(idVenta, idUnidadInventario, cantidad.doubleValue());
                    actualizarTabla();
                    productoTabla.put(productoPorAsociar.getCodigoBarra(), productoPorAsociar.getLimiteVentaPorPersona());
                    productoPorAsociar = null;
                    limpiarCamposProductoTxt();
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad que se desea vender no esta disponible en el inventario", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "No se encontró el producto en inventario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtProductoIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Object op = "";
            if (txtProductoId.getText().equals(op)) {
                Buscanom();

                txtProductoNombre.requestFocus();
            } else {
                cargarInformacionProducto(txtProductoId.getText());
            }
            txtCantidad.requestFocus();

        } else if (productoPorAsociar != null) {
            productoPorAsociar = null;
            limpiarCamposProductoTxt();
        }
    }//GEN-LAST:event_txtProductoIdKeyPressed

    private void txtProductoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoIdActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes;
    public static javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEsperar;
    private javax.swing.JButton btnModificar;
    public static javax.swing.JButton btnProductos;
    private javax.swing.JButton btnTotalizar;
    public javax.swing.JComboBox cmbTipoDocumento;
    private javax.swing.JLabel error;
    private javax.swing.JLabel info1;
    private javax.swing.JLabel info2;
    private javax.swing.JLabel info3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jtbVenta;
    private javax.swing.JLabel lblAlmacen;
    private javax.swing.JLabel lblCantidad1;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFechaFactura;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblImpuesto;
    private javax.swing.JLabel lblImpuestoValor;
    private javax.swing.JLabel lblLote;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNumeroFactura;
    private javax.swing.JLabel lblProductoNombre;
    private javax.swing.JLabel lblProductoPrecio;
    private javax.swing.JLabel lblProductoPrecioValor;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblSubtotalValor;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalValor;
    private javax.swing.JLabel lblValorFechaFactura;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlInformacionProducto;
    private javax.swing.JPanel pnlInformacionVenta;
    private javax.swing.JPanel pnlTotal;
    private static javax.swing.JTextField txtAlmacen;
    private javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtDocumento;
    private static javax.swing.JTextField txtLote;
    public static javax.swing.JTextField txtNombreCliente;
    public static javax.swing.JTextField txtNumeroFactura;
    private static javax.swing.JTextField txtProductoId;
    private static javax.swing.JTextField txtProductoNombre;
    // End of variables declaration//GEN-END:variables

    public void identificarPais() {
        //Obteniendo el pais seleccionado
        Pais p = menuPrincipal.getOBD().getDatosPais(" WHERE activo = true");
        
        //asignando identificaicon al combo.
        cmbTipoDocumento.insertItemAt(p.getNacionalidad(), 0);
        cmbTipoDocumento.setSelectedIndex(0);
    }
    
    /**
     *
     * Metodo para comprobar si un producto agregado en la tabla supera el
     * limite de cantidad maximo establecido; si devuelve FALSO el producto
     * supera el limite establecido, de lo contrario devuelve TRUE.
     *
     * @param serial
     * @return
     */
    private boolean sePuedeVender(String serial, double cantidadVenta) {
        // Konstanza: Falta terminar esta función, ya que no se está tomando en cuenta la cantidad vendida en el periodo del producto
        
        if(productoPorAsociar.getLimiteVentaPorPersona() <= 0)  return true;
        if(cantidadVenta > productoPorAsociar.getLimiteVentaPorPersona()) return false;
        
        java.sql.Date ultimaVentaProducto = (java.sql.Date) menuPrincipal.getOBD().getUltimaFechaVentaProducto(cmbTipoDocumento.getSelectedItem().toString().charAt(0), txtDocumento.getText(), serial);
        
        if(ultimaVentaProducto != null){
            LocalDate ultimaVentaProductoLocal = ultimaVentaProducto.toLocalDate();

            LocalDate actual = LocalDate.now();

            Period p = Period.between(ultimaVentaProductoLocal, actual);
            long diasDiferencia = ChronoUnit.DAYS.between(ultimaVentaProductoLocal, actual);

            if(productoPorAsociar.getIdPeriodoLimiteVenta() == 1){
                if(diasDiferencia >= 1) return true;
                // Falta conocer la cantidad que se vendió el último día
            } else if(productoPorAsociar.getIdPeriodoLimiteVenta() == 2){
                if(diasDiferencia >= 7) return true;
                // Falta conocer la cantidad que se vendió la última semana
            }

            return false;
        }
        
        return true;
    }

    /**
     * Metodo para mostrar un mensaje en el que se ingresa una contraseña
     *
     * @param th – JFrame o JDialog
     * @param mensaje
     * @param titulo
     * @return String
     */
    public String contra(Component th, String mensaje, String titulo) throws SQLException {
        String pa = "";
        JPasswordField passwordField = new JPasswordField();
        Object[] obj = {mensaje + ":\n\n", passwordField};
        Object stringArray[] = {"OK"};

        if (JOptionPane.showOptionDialog(th, obj, titulo,
                JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, stringArray, obj) == JOptionPane.OK_OPTION) {
            char[] pass = passwordField.getPassword();
            pa = new String(pass);

//            System.out.println(pa);

            int m = menuPrincipal.getOBD().autsupervisor(pa);
//            System.out.println(m);

            if (m != -1) {
//                System.out.println("validado");

                /*
    Para contar la cantidad del prodcuto en el inventario
                 */
                double numeroRow = jtbVenta.getSelectedRow();
                if (numeroRow < 0) {
                    jtbVenta.setRowSelectionInterval(0, 0);
                    numeroRow = jtbVenta.getSelectedRow();
                }
//        if (numeroRow >= 0) {
//            int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Deséa eliminar el producto?: " + jtbVenta.getModel().getValueAt((int) numeroRow, 1) + " de la venta?", "Eliminar Producto");
//            if (seleccion == 0) {
//                String codigo = jtbVenta.getValueAt((int) numeroRow, 0).toString();
//                double cantidad = Double.parseDouble((String)jtbVenta.getValueAt((int) numeroRow, 3));
//                menuPrincipal.getOBD().contarCantidad(codigo, (double) cantidad);
//            } else {
//                jtbVenta.requestFocus();
//            }
//        }
                eliminarProductoSeleccionadoEnVenta();
            } else {
//                System.out.println(" no validado");
                String g = "Clave Incorrecta";
                String h = "Bloqueo";
                Component tr = null;
                JOptionPane.showOptionDialog(tr, "Clave incorrecta", "Bloqueo", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{"cancelar"}, "cancelar");
            }
        }
        return "";
    }

    /**
     *
     */
    public void abrirVentanaTotalizarVenta() {
        impuesto = lblImpuestoValor.getText();
        if (jtbVenta.getModel().getRowCount() > 0) {
            if (!menuPrincipal.estacerrado(totalizarVenta)) {
                totalizarVenta.dispose();
            }
            totalizarVenta = new Pago(this);
            Dimension desktopSize = menuPrincipal.panel.getSize();
            Dimension jInternalFrameSize = totalizarVenta.getSize();
            totalizarVenta.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            menuPrincipal.panel.add(totalizarVenta);
            totalizarVenta.show();
            PuntoVenta.Ventanas.Pago.EF.setSelected(true);
            PuntoVenta.Ventanas.Pago.txtMonto.requestFocus();
            PuntoVenta.Ventanas.Pago.cmbBoxBancos.setEnabled(false);
            PuntoVenta.Ventanas.Pago.apro1.setVisible(false);
            PuntoVenta.Ventanas.Pago.apro2.setVisible(false);

            //totalizarVenta.requestFocus();
        } else {
            Utilidades.Sonidos.beep();
            this.getTxtDocumento().requestFocus();
        }
    }

    public void Buscanom() {
        String produ = txtProductoId.getText();
        if (produ.equals("")) {
            txtProductoNombre.requestFocus();
        }
    }

    /**
     * Llama al método dispose() del componente.
     */
    private void cerrarVentana() {
        this.dispose();
    }

    /**
     * Abre la ventana de registro simple. Utiliza el char como identificador
     * del comboBox y el String para llenar el campo de cedula.
     *
     * @param tipo_persona
     * @param numero_identificacion_persona
     */
    private void abrirVentanaRegistroSimpleCliente(char tipo_persona, String numero_identificacion_persona) {
      
         
        if (menuPrincipal.estacerrado(registroCliente)) {
            if (numero_identificacion_persona.isEmpty()) {
                registroCliente = new RegistroCliente(this);
            } else {
                registroCliente = new RegistroCliente(this, tipo_persona, numero_identificacion_persona);
            }

            Dimension desktopSize = menuPrincipal.panel.getSize();
            Dimension jInternalFrameSize = registroCliente.getSize();
            registroCliente.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);

            menuPrincipal.panel.add(registroCliente);
            registroCliente.show();
        } else {
            JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.registroCliente.getTitle()+ "\nya esta abierta...");
        }
      }
 
        
    /**
     * Abre la ventana de registro simple.
     */
    private void abrirVentanaRegistroSimpleCliente() {
       
        if (menuPrincipal.estacerrado(registroCliente)) {
            registroCliente = new RegistroCliente(this);
            Dimension desktopSize = menuPrincipal.panel.getSize();
            Dimension jInternalFrameSize = registroCliente.getSize();
            registroCliente.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);

            menuPrincipal.panel.add(registroCliente);
            registroCliente.show();
        } else {
            JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.registroCliente.getTitle()+"\nya esta abierta...");
        }
       }
    
    /**
     * Abre la ventana de ListaClientes.
     */
    private void abrirVentanaCliente() {
        if (menuPrincipal.estacerrado(ventanaCliente)) {
            ventanaCliente = new ListaClientes(this);
            Dimension desktopSize = menuPrincipal.panel.getSize();
            Dimension jInternalFrameSize = ventanaCliente.getSize();
            ventanaCliente.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);

            menuPrincipal.panel.add(ventanaCliente);
            ventanaCliente.show();
        } else {
            JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.ventanaCliente.getTitle()+ "\nya esta abierta...");
        }
    }

    public void abrirVentanaBuscarProductos() {

        if (menuPrincipal.estacerrado(buscarProducto)) {
            buscarProducto = new ListaProductos(this);
            Dimension desktopSize = menuPrincipal.panel.getSize();
            Dimension jInternalFrameSize = buscarProducto.getSize();
            buscarProducto.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
            menuPrincipal.panel.add(buscarProducto);
        } else {
            JOptionPane.showMessageDialog(this, "Error: La ventana\n"+this.buscarProducto.getTitle()+ "\nya esta abierta...");
            buscarProducto.moveToFront();
            if (txtProductoNombre == null) {

            }
            buscarProducto.getTxtCampoDescripcion().requestFocus();
        }
        buscarProducto.show();
    }

    /**
     * Busca un cliente en la base de datos con identificador (nacionalidad o
     * RIF) y un id (cedula o RIF)), en caso de encontrarlo crea un registro de
     * venta a nombre de este usuario y le asigna un estado de "en proceso".
     *
     * @param tipo_persona
     * @param numero_identificacion_persona
     */
    public void crearVenta(String tipo_persona, String numero_identificacion_persona) {
        //El documento no puede ser null o estar vacio ""
        if (numero_identificacion_persona == null || numero_identificacion_persona.isEmpty()) {
            Utilidades.Sonidos.beep();
            this.setClienteAsociadoFactura(false);
            return;
        }
        //La caja no puede estar cerrada.
        if (!menuPrincipal.isCajaAbierta()) {
            Utilidades.Sonidos.beep();
            this.setClienteAsociadoFactura(false);
            int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "La caja está cerrada. ¿Desea abrirla?", "Caja cerrada");
            if (seleccion == 0) {
                this.menuPrincipal.abrirVentanaCaja();
            }
            return;
        }

        HashMap map = menuPrincipal.getOBD().getMapPersona(tipo_persona, numero_identificacion_persona);
        
        if (map != null && !map.isEmpty()) {
            ModeloCliente cliente = new ModeloCliente(map);
            int idVentaNuevo = menuPrincipal.getOBD().crearVenta(cliente.getId(), menuPrincipal.getIdEstadoCaja(), 1);
            this.setIdVenta(idVentaNuevo);
            System.out.println("ID VENTA NUEVO "+idVentaNuevo);
            if(idVentaNuevo > -1){
                txtNombreCliente.setText(cliente.getNombre() + " " + cliente.getApellido());
                HashMap<String, String> venta = menuPrincipal.getOBD().getMapVenta(idVentaNuevo);
                txtNumeroFactura.setText(venta.get("codigo_factura"));
                lblValorFechaFactura.setText(venta.get("fecha_venta"));
                setClienteAsociadoFactura(true);
                setBotonesVentaEnabled(true);
            
                txtProductoId.setEditable(true);
                getTxtCantidad().setEditable(true);
                txtProductoId.requestFocus();
                actualizarTabla();
            }
            else {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al intentar crear la factura", "Error", JOptionPane.ERROR_MESSAGE);
            }
                    
        } else {
            this.setClienteAsociadoFactura(false);
            int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Desea registrar a este cliente?", "Este cliente no existe");
            if (seleccion == 0) {
                abrirVentanaRegistroSimpleCliente();
            }
        }
    }

    public void abrirVentanaBloqueo() {

        totalizarVenta = new Pago(this);
        Dimension desktopSize = menuPrincipal.panel.getSize();
        Dimension jInternalFrameSize = totalizarVenta.getSize();
        totalizarVenta.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        menuPrincipal.panel.add(totalizarVenta);
        totalizarVenta.show();
        totalizarVenta.requestFocus();

    }
    
    /**
     * Todos los campos del formulario se ponen en blanco excepto el documento y
     * se deshabilitan los de txtIdProducto y txtCantidad.
     */
    private void limpiarCamposTxt() {
        this.txtNombreCliente.setText("");
        this.txtNumeroFactura.setText("");
        this.txtProductoId.setText("");
        this.getTxtCantidad().setText("");

        this.txtDocumento.requestFocus();
        this.txtDocumento.setSelectionStart(0);
        this.txtDocumento.setSelectionEnd(this.txtDocumento.getText().length());

        this.txtProductoId.setEditable(false);
        this.getTxtCantidad().setEditable(false);
    }

    /**
     * Método para limpiar los campos txt del panel del producto. No eliminar el
     * id del producto, pero lo selecciona para editarlo de forma inmediata.
     */
    private void limpiarCamposProductoTxt() {
        txtProductoId.setText("");
        txtProductoNombre.setText("");
        txtCantidad.setText("");
        txtAlmacen.setText("");
        txtLote.setText("");
        txtAlmacen.setEnabled(false);
        txtLote.setEnabled(false);
        lblProductoPrecio.setVisible(false);
        lblProductoPrecioValor.setText("");

        txtProductoId.requestFocus();
    }

    public static void reiniciarJTable(javax.swing.JTable jtbVenta) {
        DefaultTableModel modelo = (DefaultTableModel) jtbVenta.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        TableColumnModel modCol = jtbVenta.getColumnModel();
        while (modCol.getColumnCount() > 0) {
            modCol.removeColumn(modCol.getColumn(0));
        }
    }

    /**
     * Actualiza la tabla de productos asociados a la venta y carga en ella la
     * información directa de la base de datos. NOTA: Es problable que si recibe
     * la lista, en vez de hacer un query directo mejore el rendimiento.
     */
    public void actualizarTabla() {
        ArrayList<HashMap<String, String>> listaProductoEnVenta = menuPrincipal.getOBD().getArrayListProductosEnVenta(idVenta);
        jtbVentaModel = new ProductoVentaTableModel(listaProductoEnVenta);
        jtbVenta.setModel(jtbVentaModel);
        jtbVenta.setFont(new Font("Arial", Font.BOLD, 16));
        jtbVenta.setBackground(Color.white);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();

        for (int i = 0; i < jtbVenta.getColumnCount(); i++) {
            int width;
            switch (i) {
                case 0:
                    width = 135;
                    break;
                case 1:
                    width = 400;
                    break;
                case 3:
                    width = 80;
                    dtcr.setHorizontalAlignment(JLabel.CENTER);
                    jtbVenta.getColumnModel().getColumn(i).setCellRenderer(dtcr);
                    break;
                default:
                    width = 200;
                    break;
            }
            jtbVenta.getColumnModel().getColumn(i).setPreferredWidth(width);
            jtbVenta.getColumnModel().getColumn(i).setResizable(false);
        }

        jtbVenta.getTableHeader().getDefaultRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        jtbVenta.getColumnModel().setColumnSelectionAllowed(false);
        jtbVenta.getTableHeader().setReorderingAllowed(false);

        TableColumnModel tcm = jtbVenta.getColumnModel();
        tcm.removeColumn(tcm.getColumn(tcm.getColumnIndex("Id de unidad de inventario")));
        
        //Actualizar subtotal, total e Iva.
        if (listaProductoEnVenta != null) {
            actualizarLblSubtotal();
            actualizarLblImpuesto();
            actualizarLblTotal();
        } else {
            this.lblTotalValor.setText("0.00");
            this.lblSubtotalValor.setText("0.00");
            this.lblImpuestoValor.setText("0.00");
        }

    }

    /**
     * Método para eliminar un producto de la venta. Obtiene el idVenta de esta
     * misma clase.
     *
     */
    public void eliminarProductoSeleccionadoEnVenta() {
        int numeroRow = jtbVenta.getSelectedRow();
        if (numeroRow < 0) {
            jtbVenta.setRowSelectionInterval(0, 0);
            numeroRow = jtbVenta.getSelectedRow();
        }
        if (numeroRow >= 0) {
            int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Desea eliminar el artículo " + jtbVentaModel.getValueAt(numeroRow, "Descripción") + " de la venta?", "Eliminar artículo");
            if (seleccion == 0) {
                int idUnidadInventario = Integer.parseInt(jtbVentaModel.getValueAt(numeroRow, "Id de unidad de inventario").toString());
                menuPrincipal.getOBD().eliminarProductoEnVenta(idVenta, idUnidadInventario);
                actualizarTabla();
            } else {
                jtbVenta.requestFocus();
            }
        }
    }

    /**
     * Método para cancelar la venta actual. Deberia eliminar todos los
     * productos asociados a la venta? o guardarlos como registro?
     */
    private void cancelarVenta() {
        int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Desea cancelar la venta actual?", "Cancelar venta");
        if (seleccion == 0) {
            menuPrincipal.getOBD().setEstadoVenta(idVenta, ObjetoBaseDatos.EstadoVenta.Cancelada);
            limpiarVenta();
        }
    }
       
    /**
     * Método para pausar la venta actual.
     */
    private void pausarVenta() {
        int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Desea pausar la venta actual?", "Pausar venta");
        if (seleccion == 0) {
            menuPrincipal.getOBD().setEstadoVenta(idVenta, ObjetoBaseDatos.EstadoVenta.Pausada);
            limpiarVenta();
        }
    }
    
    /**
     * Abre un JOptionPane para ingresar la nueva cantidad de productos. Si no
     * hay ninguno seleccionado, automáticamente selecciona el primer objeto de
     * la lista.
     */
    private void modificarCantidadProductoSeleccionado() {
        if (jtbVenta.getRowCount() > 0) {
            int rowNumber = jtbVenta.getSelectedRow();
            
            if (rowNumber < 0) {
                jtbVenta.setRowSelectionInterval(0, 0);
                rowNumber = jtbVenta.getSelectedRow();
            }
            
            String cantidadNuevaString = JOptionPane.showInputDialog(this, "Ingrese la nueva cantidad de " + jtbVentaModel.getValueAt(rowNumber, "Descripción"), jtbVentaModel.getValueAt(rowNumber, "Cantidad"));
            
            if (!cantidadNuevaString.isEmpty()) {
                XBigDecimal cantidadNueva = new XBigDecimal(cantidadNuevaString);
                XBigDecimal cantidadAnterior = new XBigDecimal(jtbVentaModel.getValueAt(rowNumber, "Cantidad").toString());
                if (cantidadNueva.compareTo(new XBigDecimal(0)) > 0) {
                    int idUnidadInventario = Integer.parseInt(jtbVentaModel.getValueAt(rowNumber, "Id de unidad de inventario").toString());
                    
                    /*if (productoTabla.get(codigoBarra) == null) {
                        productoTabla.put(codigoBarra, menuPrincipal.getOBD().getLimiteMaximoProducto(codigoBarra));
                        }
                    if (!sePuedeVender(codigoBarra, Double.parseDouble(cantidadNuevaString))) {
                        JOptionPane.showMessageDialog(null, "El limite máximo de '" + productoPorAsociar.getDescripcion() + "' permitido por persona es " + productoPorAsociar.getLimiteVentaPorPersona() + " en un periodo "+PeriodoLimite.getDescripcion(productoPorAsociar.getIdPeriodoLimiteVenta()), "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                     */   
                        modificarCantidadProducto(idUnidadInventario, cantidadNueva, cantidadAnterior);
                        actualizarTabla();
                   // }
                } else {
                    Utilidades.Sonidos.beep();
                }
            }
        } else {
            Utilidades.Sonidos.beep();
        }
    }

    /**
     * Modifica la cantidad de un producto específico dado su serial.
     *
     * @param codigoBarra
     * @param cantidad
     */
    private void modificarCantidadProducto(int idUnidadInventario, XBigDecimal cantidadNueva, XBigDecimal cantidadAnterior) {
        if (menuPrincipal.getOBD().consultastock(idVenta, idUnidadInventario, cantidadNueva.doubleValue())) {
            menuPrincipal.getOBD().agregarProductoEnVenta(idVenta, idUnidadInventario, new XBigDecimal(cantidadNueva.add(cantidadAnterior.negate()).toString()).doubleValue());
        } else {
            JOptionPane.showMessageDialog(null, "La cantidad que se desea vender no está disponible en el inventario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga la informacion de un producto en los txt de la Venta.
     *
     * @param idProducto
     */
    public void cargarInformacionProducto(String idProducto) {
        if (txtProductoId.getText().isEmpty() || !txtProductoId.getText().equalsIgnoreCase(idProducto)) {
            txtProductoId.setText(idProducto);
        }
        HashMap<String, String> map = menuPrincipal.getOBD().getMapProducto(idProducto);
        cargarInformacionProducto(map);
    }
    
    private void cargarInformacionPrecioProducto() {
        try {
            int idUnidadInventario = menuPrincipal.getOBD().getIdUnidadInventario(productoPorAsociar.getId(), Integer.parseInt(txtAlmacen.getText()), txtLote.getText());

            if (idUnidadInventario != -1) {
                cargarInformacionProducto(txtProductoId.getText(), idUnidadInventario);
            } else {
                lblProductoPrecio.setVisible(false);
                lblProductoPrecioValor.setText("");
            }
        } catch (NumberFormatException e) {
            lblProductoPrecio.setVisible(false);
            lblProductoPrecioValor.setText("");
        }
    }
    
    /**
     * Carga la informacion de un producto en los txt de la Venta.
     *
     * @param idProducto
     * @param idUnidadInventario
     */
    public void cargarInformacionProducto(String idProducto, int idUnidadInventario) {
        if (txtProductoId.getText().isEmpty() || !txtProductoId.getText().equalsIgnoreCase(idProducto)) {
            txtProductoId.setText(idProducto);
        }
        HashMap<String, String> map = menuPrincipal.getOBD().getMapProducto(idUnidadInventario);
        cargarInformacionProducto(map);
    }
    
    private void cargarInformacionProducto(HashMap<String, String> map){
        if (!map.isEmpty()) {
            productoPorAsociar = new ModeloProducto(map);
            lblProductoPrecio.setVisible(true);
            lblProductoPrecioValor.setText(map.get("precio_venta_publico"));
            txtProductoNombre.setText(map.get("descripcion_producto"));
            txtCantidad.setText("1");
            txtCantidad.setSelectionStart(0);
            txtCantidad.setSelectionEnd(txtCantidad.getText().length());
            txtCantidad.setEditable(map.get("seguimiento") == null || !map.get("seguimiento").equals("2"));
            txtCantidad.requestFocus();
            txtAlmacen.setText(map.get("almacen_id"));
            txtAlmacen.setEnabled(true);
            txtLote.setEditable(true);
            if (map.get("codigo_lote") != null) {
                txtLote.setText(map.get("codigo_lote"));
                txtLote.setEnabled(true);
                txtLote.setEditable(true);
            }
            else {
                txtLote.setText("");
                txtLote.setEnabled(false);
                txtLote.setEnabled(false);
            }
        } else {
            Utilidades.Sonidos.beep();
            limpiarCamposProductoTxt();
            txtProductoId.setSelectionStart(0);
            txtProductoId.setSelectionEnd(txtProductoId.getText().length());
            txtProductoId.requestFocus();
        }
    }

    /**
     * Actualiza el lblSubtotalValor con la suma de los productos no exentos
     * menos los impuestos de todos los productos incluidos en la venta.
     *
     */
    public void actualizarLblSubtotal() {
        Double subtotal = menuPrincipal.getOBD().getSubtotalVenta(idVenta);
        //Double montoBaseImponible = menuPrincipal.getOBD().getTotalBaseImponibleVenta(idVenta);
        XBigDecimal subtotalDecimal = new XBigDecimal(subtotal.toString());
        //XBigDecimal montoBaseImponibleDecimal = new XBigDecimal(montoBaseImponible.toString());
        this.getLblSubtotalValor().setText(subtotalDecimal.toString());
    }

    /**
     * Actualiza el lblImpuestoValor con el suma del impuesto de todos los productos
     * incluidos en la venta.
     *
     */
    public void actualizarLblImpuesto() {
        Double montoImpuesto = menuPrincipal.getOBD().getTotalImpuestoVenta(idVenta);
        System.out.println("MONTO IMPUESTO: "+montoImpuesto);
        XBigDecimal monto = new XBigDecimal(montoImpuesto.toString());
        this.getLblImpuestoValor().setText(monto.setScale(2, RoundingMode.HALF_EVEN).toString());
    }

    /**
     * Actualiza el lblTotalValor con el precio de todos los productos includos
     * en la venta.
     *
     */
    public void actualizarLblTotal() {
        XBigDecimal montoSubtotal = new XBigDecimal(getLblSubtotalValor().getText());
        XBigDecimal montoImpuesto = new XBigDecimal(getLblImpuestoValor().getText());

        this.getLblTotalValor().setText(montoSubtotal.add(montoImpuesto).setScale(2, RoundingMode.HALF_EVEN).toString());
    }

    /**
     * Activa/desactiva los botones de Totaliza, Eliminar, Cancelar, Modificar y
     * Experar. Tambien desactiva los campos de idProducto y cantidad.
     *
     * @param a Si <code>true</code> activa los campos. Si no, los desactiva.
     */
    public void setBotonesVentaEnabled(final boolean a) {
        btnTotalizar.setEnabled(a);
        btnEliminar.setEnabled(a);
        btnCancelar.setEnabled(a);
        btnModificar.setEnabled(a);
        btnEsperar.setEnabled(a);
        txtProductoId.setEditable(a);
        getTxtCantidad().setEditable(a);
        txtProductoNombre.setEditable(a);
    }

    /**
     * Invoca el requestFocus en un punto de interes para el usuario.
     * txtIdProducto > cmbTipoDocumento > txtDocumento.
     */
    public void focusPuntoInteres() {
        //Si el txtIdProducto está activo, editable y no está en focus
        if (this.txtProductoId.isEnabled() && this.txtProductoId.isEditable() && !this.txtProductoId.hasFocus()) {
            this.txtProductoId.requestFocus();
            this.txtProductoId.setSelectionStart(0);
            this.txtProductoId.setSelectionEnd(this.txtProductoId.getText().length());
        } else if (this.getTxtDocumento().hasFocus()) {
            this.getCmbTipoDocumento().requestFocus();
        } else {
            this.getTxtDocumento().requestFocus();
            this.txtDocumento.setSelectionStart(0);
            this.txtDocumento.setSelectionEnd(this.txtDocumento.getText().length());
        }
    }

    /*
    Metodo para descontar la cantidad del producto
    *
    private void descontarProducto() throws SQLException {
        int idProducto;
        double cantidad;
        String codigo;

        cantidad = Double.valueOf((String) txtCantidad.getText());

        codigo = txtProductoId.getText();
//        System.out.print(codigo + "codigo");
//        System.out.print(cantidad + "cantidad");

        idProducto = menuPrincipal.getOBD().descontarCantidad(codigo, (double) cantidad);

        if (idProducto > 0) {
            HashMap<String, String> mapProducto = menuPrincipal.getOBD().getMapProducto(codigo);
            ModeloProducto producto = new ModeloProducto(mapProducto);
        } else {
            Utilidades.Sonidos.beep();
        }

    }*/

    /**
     * @return the txtIdProducto
     */
    public javax.swing.JTextField getTxtIdProducto() {
        return txtProductoId;
    }

    public static javax.swing.JTextField getTxtProductoNombre() {
        return txtProductoNombre;
    }

    /**
     * @return the txtDocumentoCliente
     */
    public javax.swing.JTextField getTxtDocumento() {
        return txtDocumento;
    }

    /**
     * @return the cmbTipoDocumento
     */
    public javax.swing.JComboBox getCmbTipoDocumento() {
        return cmbTipoDocumento;
    }

    /**
     * @return the lblSubtotalValor
     */
    public javax.swing.JLabel getLblSubtotalValor() {
        return lblSubtotalValor;
    }

    /**
     * @return the lblImpuestoValor
     */
    public javax.swing.JLabel getLblImpuestoValor() {
        return lblImpuestoValor;
    }

    /**
     * @return the lblTotalValor
     */
    public javax.swing.JLabel getLblTotalValor() {
        return lblTotalValor;
    }

    /**
     * @return the idVenta
     */
    public int getIdVenta() {
        return idVenta;
    }

    /**
     * @return the clienteAsociadoFactura
     */
    public boolean isClienteAsociadoFactura() {
        return clienteAsociadoFactura;
    }

    /**
     * @param clienteAsociadoFactura the clienteAsociadoFactura to set
     */
    public void setClienteAsociadoFactura(final boolean clienteAsociadoFactura) {
        btnClientes.setEnabled(!clienteAsociadoFactura);
        cmbTipoDocumento.setEnabled(!clienteAsociadoFactura);
        txtDocumento.setEnabled(!clienteAsociadoFactura);
        txtNombreCliente.setEnabled(!clienteAsociadoFactura);
        txtNumeroFactura.setEnabled(!clienteAsociadoFactura);
        
        this.clienteAsociadoFactura = clienteAsociadoFactura;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * @return the txtCantidad
     */
    public javax.swing.JTextField getTxtCantidad() {
        return txtCantidad;
    }

}
