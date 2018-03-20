package PuntoVenta.Ventanas;

import ClasesExtendidas.Numeros.XBigDecimal;
import ClasesExtendidas.Tablas.VentaTableModel;
import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import PuntoVenta.Inicio.MenuPrincipal;
import PuntoVenta.Modelos.ModeloCliente;
import PuntoVenta.Modelos.ModeloProducto;

import Administrador.Ventanas.RegistroCliente;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
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

    private DecimalFormat redondeo = new DecimalFormat("0.00");
    public final MenuPrincipal menuPrincipal;
    public String impuesto;
    public RegistroCliente registroCliente;
    public ListaClientes ventanaCliente;
    private boolean clienteAsociadoFactura;
    ModeloProducto productoPorAsociar;

    String ti = "";

    HashMap<String, Integer> productoTabla = new HashMap<>();

    public ListaProductos buscarProducto;
    private int idVenta = -1;
    public Pago totalizarVenta;

    public Venta(MenuPrincipal menuPrincipal) {
        initComponents();
        this.menuPrincipal = menuPrincipal;
        this.setClienteAsociadoFactura(false);

        this.setTitle("Cajero: " + menuPrincipal.getEmpleado().getNombre() + " " + menuPrincipal.getEmpleado().getApellido());
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

        jButton4.setText("Terminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        setClosable(true);
        setResizable(true);

        pnlContenedor.setBackground(new java.awt.Color(32, 182, 155));
        ///jPanel6.setBackground(new java.awt.Color(-15589839));*/
        pnlContenedor.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlContenedorComponentResized(evt);
            }
        });

        pnlInformacionVenta.setBackground(new java.awt.Color(32, 182, 155));
        pnlInformacionVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18), java.awt.Color.white)); // NOI18N
        pnlInformacionVenta.setForeground(new java.awt.Color(255, 255, 255));
        pnlInformacionVenta.setMaximumSize(new java.awt.Dimension(386, 143));
        pnlInformacionVenta.setMinimumSize(new java.awt.Dimension(286, 143));
        ///jPanel4.setBackground(new java.awt.Color(-15589839));

        lblDocumento.setBackground(new java.awt.Color(255, 255, 255));
        lblDocumento.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblDocumento.setForeground(new java.awt.Color(255, 255, 255));
        lblDocumento.setText("CI / RIF:");

        lblNumeroFactura.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblNumeroFactura.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroFactura.setText("Factura:");

        txtNumeroFactura.setEditable(false);
        txtNumeroFactura.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNumeroFactura.setPreferredSize(new java.awt.Dimension(600, 28));

        txtDocumento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtDocumento.setMaximumSize(new java.awt.Dimension(700, 28));
        txtDocumento.setMinimumSize(new java.awt.Dimension(440, 28));
        txtDocumento.setNextFocusableComponent(jtbVenta);
        txtDocumento.setPreferredSize(new java.awt.Dimension(440, 28));
        txtDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyPressed(evt);
            }
        });

        txtNombreCliente.setEditable(false);
        txtNombreCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtNombreCliente.setNextFocusableComponent(txtNumeroFactura);
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        lblNombres.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblNombres.setForeground(new java.awt.Color(255, 255, 255));
        lblNombres.setText("Nombre:");

        cmbTipoDocumento.setToolTipText("");
        cmbTipoDocumento.setName(""); // NOI18N

        javax.swing.GroupLayout pnlInformacionVentaLayout = new javax.swing.GroupLayout(pnlInformacionVenta);
        pnlInformacionVenta.setLayout(pnlInformacionVentaLayout);
        pnlInformacionVentaLayout.setHorizontalGroup(
            pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNumeroFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreCliente)
                    .addGroup(pnlInformacionVentaLayout.createSequentialGroup()
                        .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, Short.MAX_VALUE))
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInformacionVentaLayout.setVerticalGroup(
            pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacionVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoDocumento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDocumento)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombres))
                .addGap(6, 6, 6)
                .addGroup(pnlInformacionVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroFactura))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbTipoDocumento.addItem("V");
        cmbTipoDocumento.addItem("J");
        cmbTipoDocumento.addItem("P");
        cmbTipoDocumento.addItem("E");

        pnlInformacionProducto.setBackground(new java.awt.Color(32, 182, 155));
        pnlInformacionProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18), java.awt.Color.white)); // NOI18N
        //jPanel3.setBackground(new java.awt.Color(-15589839));
        pnlInformacionProducto.setForeground(new java.awt.Color(255, 255, 255));

        lblIdProducto.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblIdProducto.setForeground(new java.awt.Color(255, 255, 255));
        lblIdProducto.setText("Código:");

        txtProductoId.setEditable(false);
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

        lblProductoNombre.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblProductoNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblProductoNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblProductoNombre.setText("Nombre:");

        txtCantidad.setEditable(false);
        txtCantidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCantidad.setPreferredSize(new java.awt.Dimension(103, 25));
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
        });

        lblProductoPrecio.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblProductoPrecio.setForeground(new java.awt.Color(255, 255, 255));
        lblProductoPrecio.setText("Precio:");

        lblProductoPrecioValor.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblProductoPrecioValor.setForeground(new java.awt.Color(255, 255, 255));
        lblProductoPrecioValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblCantidad1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblCantidad1.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidad1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCantidad1.setText("Cantidad:");

        txtProductoNombre.setEditable(false);
        txtProductoNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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

        javax.swing.GroupLayout pnlInformacionProductoLayout = new javax.swing.GroupLayout(pnlInformacionProducto);
        pnlInformacionProducto.setLayout(pnlInformacionProductoLayout);
        pnlInformacionProductoLayout.setHorizontalGroup(
            pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                        .addComponent(lblProductoPrecio)
                        .addGap(18, 18, 18)
                        .addComponent(lblProductoPrecioValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                        .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblProductoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblIdProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblCantidad1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProductoId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtProductoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                                .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(error)
                                .addGap(37, 37, 37))))))
        );
        pnlInformacionProductoLayout.setVerticalGroup(
            pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdProducto)
                    .addComponent(txtProductoId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductoNombre)
                    .addComponent(txtProductoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacionProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidad1)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacionProductoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(error)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnlInformacionProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProductoPrecio, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblProductoPrecioValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        pnlBotones.setBackground(new java.awt.Color(32, 182, 155));

        btnProductos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnProductos.setText("<html><center>Productos<br> F2</center></html>");
        btnProductos.setToolTipText("");
        btnProductos.setActionCommand("actionProductos");
        btnProductos.setMaximumSize(new java.awt.Dimension(200, 35));
        btnProductos.setMinimumSize(new java.awt.Dimension(80, 35));
        btnProductos.setPreferredSize(new java.awt.Dimension(90, 35));
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });

        //totalizar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
        btnTotalizar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTotalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/Tips.png"))); // NOI18N
        btnTotalizar.setText("<html><center>Totalizar<br>F4</center></html>");
        btnTotalizar.setToolTipText("");
        btnTotalizar.setActionCommand("actionTotalizar");
        btnTotalizar.setMaximumSize(new java.awt.Dimension(200, 35));
        btnTotalizar.setMinimumSize(new java.awt.Dimension(80, 35));
        btnTotalizar.setPreferredSize(new java.awt.Dimension(90, 41));
        btnTotalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalizarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/Delete-icon.png"))); // NOI18N
        btnEliminar.setText("<html><center>Eliminar<br>F5</center></html>");
        btnEliminar.setToolTipText("");
        btnEliminar.setActionCommand("actionEliminar");
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

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/cancel.png"))); // NOI18N
        btnCancelar.setText("<html><center>Cancelar<br>F6</center></html>");
        btnCancelar.setToolTipText("");
        btnCancelar.setMaximumSize(new java.awt.Dimension(200, 35));
        btnCancelar.setMinimumSize(new java.awt.Dimension(80, 35));
        btnCancelar.setPreferredSize(new java.awt.Dimension(90, 36));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/update.png"))); // NOI18N
        btnModificar.setText("<html><center>Modificar<br>F7</center></html>");
        btnModificar.setToolTipText("");
        btnModificar.setMaximumSize(new java.awt.Dimension(200, 35));
        btnModificar.setMinimumSize(new java.awt.Dimension(80, 35));
        btnModificar.setPreferredSize(new java.awt.Dimension(90, 35));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEsperar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEsperar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/StandByicon.png"))); // NOI18N
        btnEsperar.setText("<html><center>Esperar<br>F8</center></html>");
        btnEsperar.setToolTipText("");
        btnEsperar.setMaximumSize(new java.awt.Dimension(200, 35));
        btnEsperar.setMinimumSize(new java.awt.Dimension(80, 35));
        btnEsperar.setPreferredSize(new java.awt.Dimension(90, 36));
        btnEsperar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEsperarActionPerformed(evt);
            }
        });

        btnClientes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/Add-Male-User-icon.png"))); // NOI18N
        btnClientes.setText("<html><center>Clientes<br> F3</center></html>");
        btnClientes.setActionCommand("actionClientes");
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
                .addComponent(btnProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTotalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEsperar, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTotalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEsperar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        pnlTotal.setBackground(new java.awt.Color(32, 182, 155));
        pnlTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 18), java.awt.Color.white)); // NOI18N
        ///jPanel5.setBackground(new java.awt.Color(-15589839));

        lblTotal.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 0));
        lblTotal.setText("Total:");

        lblTotalValor.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lblTotalValor.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalValor.setText("0.00");

        lblSubtotal.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtotal.setText("Subtotal:");

        lblImpuesto.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        lblImpuesto.setForeground(new java.awt.Color(255, 255, 255));
        lblImpuesto.setText("Impuesto:");

        lblSubtotalValor.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        lblSubtotalValor.setForeground(new java.awt.Color(255, 255, 255));
        lblSubtotalValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSubtotalValor.setText("0.00");

        lblImpuestoValor.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        lblImpuestoValor.setForeground(new java.awt.Color(255, 255, 255));
        lblImpuestoValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblImpuestoValor.setText("0.00");

        javax.swing.GroupLayout pnlTotalLayout = new javax.swing.GroupLayout(pnlTotal);
        pnlTotal.setLayout(pnlTotalLayout);
        pnlTotalLayout.setHorizontalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTotalLayout.createSequentialGroup()
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(pnlTotalLayout.createSequentialGroup()
                        .addComponent(lblImpuesto)
                        .addGap(18, 18, 18)
                        .addComponent(lblImpuestoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlTotalLayout.createSequentialGroup()
                        .addComponent(lblSubtotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSubtotalValor, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTotalLayout.setVerticalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(pnlInformacionVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlInformacionProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInformacionProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlInformacionVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
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
            char tipo_persona = getCmbTipoDocumento().getSelectedItem().toString().charAt(0);
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

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && productoPorAsociar != null) {
            XBigDecimal cantidad = new XBigDecimal(getTxtCantidad().getText());
            String canticomp = txtCantidad.getText();
            for (int i = 0; i < jtbVenta.getRowCount(); i++) {
                if (jtbVenta.getValueAt(i, 0).toString().equals(txtProductoId.getText())) {
                    canticomp = "" + (Integer.parseInt(canticomp) + Integer.parseInt(jtbVenta.getValueAt(i, 3).toString()));
                }
            }
            double d = cantidad.doubleValue();
            if (cantidad.compareTo(new XBigDecimal(productoPorAsociar.getLimiteVentaPorPersona())) > 0 || !comprobarLimiteMaximo(productoPorAsociar.getCodigoBarra())) {
                JOptionPane.showMessageDialog(null, "El limite de '" + productoPorAsociar.getDescripcion() + "' maximo permitido por persona es de " + productoPorAsociar.getLimiteVentaPorPersona() + "", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (menuPrincipal.getOBD().consultastock(txtProductoId.getText(), canticomp)) {
                menuPrincipal.getOBD().agregarProductoEnVenta(this.getIdVenta(), productoPorAsociar.getCodigoBarra(), d);
//                try {
//                    descontarProducto();
//                } catch (SQLException ex) {
//                    Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
//                }

                actualizarTabla();
                productoTabla.put(productoPorAsociar.getCodigoBarra(), productoPorAsociar.getLimiteVentaPorPersona());
                productoPorAsociar = null;
                limpiarCamposProductoTxt();
            } else {
                JOptionPane.showMessageDialog(null, "La cantidad que se desea vender no esta disponible en el inventario", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_txtCantidadKeyPressed

    public void limpiarVenta() {
        this.setIdVenta(-1);
        limpiarCamposTxt();
        actualizarTabla();
        txtDocumento.setText("");
    }

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

    private void txtProductoNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoNombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            abrirVentanaBuscarProductos();
            String nom = txtProductoNombre.getText().toString();
            PuntoVenta.Ventanas.ListaProductos.txtCampoDescripcion.setText(nom);

        }

    }//GEN-LAST:event_txtProductoNombreKeyPressed

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

    private void txtProductoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoIdActionPerformed

    private void txtProductoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductoNombreActionPerformed

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
        String t = "bloqueo";
        try {
            contra(tr, m, t);

        } catch (SQLException ex) {
            Logger.getLogger(Venta.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnEliminarMouseClicked

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes;
    public static javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEsperar;
    private javax.swing.JButton btnModificar;
    public static javax.swing.JButton btnProductos;
    private javax.swing.JButton btnTotalizar;
    private javax.swing.JComboBox cmbTipoDocumento;
    private javax.swing.JLabel error;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jtbVenta;
    private javax.swing.JLabel lblCantidad1;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblImpuesto;
    private javax.swing.JLabel lblImpuestoValor;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNumeroFactura;
    private javax.swing.JLabel lblProductoNombre;
    private javax.swing.JLabel lblProductoPrecio;
    private javax.swing.JLabel lblProductoPrecioValor;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblSubtotalValor;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalValor;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JPanel pnlInformacionProducto;
    private javax.swing.JPanel pnlInformacionVenta;
    private javax.swing.JPanel pnlTotal;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtDocumento;
    public static javax.swing.JTextField txtNombreCliente;
    public static javax.swing.JTextField txtNumeroFactura;
    public static javax.swing.JTextField txtProductoId;
    public static javax.swing.JTextField txtProductoNombre;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * Metodo para comprobar si un producto agregado en la tabla supera el
     * limite de cantidad maximo establecido; si devuelve FALSO el producto
     * supera el limite establecido, de lo contrario devuelve TRUE.
     *
     * @param serial
     * @return
     */
    private boolean comprobarLimiteMaximo(String serial) {
        XBigDecimal cantExistente = new XBigDecimal(0);
        XBigDecimal cantNueva = new XBigDecimal(getTxtCantidad().getText());
        for (int i = 0; i < jtbVenta.getRowCount(); i++) {
            if (jtbVenta.getValueAt(i, 0).toString().equals(serial)) {
                cantExistente = new XBigDecimal(jtbVenta.getValueAt(i, 3).toString());
                break;
            }
        }
        XBigDecimal cantidadTotal = new XBigDecimal(cantExistente.add(cantNueva).toString());
        if (cantidadTotal.compareTo(new XBigDecimal(productoPorAsociar.getLimiteVentaPorPersona())) > 0) {
            return false;
        } else {
            return true;
        }
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
            JOptionPane.showMessageDialog(this, "Error: La ventana ya esta abierta...");
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
            JOptionPane.showMessageDialog(this, "Error: La ventana ya esta abierta...");
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
            JOptionPane.showMessageDialog(this, "Error: La ventana ya esta abierta...");
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
    public void crearVenta(char tipo_persona, String numero_identificacion_persona) {
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
            this.setIdVenta(menuPrincipal.getOBD().crearVenta(cliente.getId(), menuPrincipal.getIdEstadoCaja()));
            txtNombreCliente.setText(cliente.getNombre() + " " + cliente.getApellido());
            txtNumeroFactura.setText(menuPrincipal.getOBD().numeroFactura(cliente.getId(), menuPrincipal.getIdEstadoCaja()));
            setClienteAsociadoFactura(true);
            setBotonesVentaEnabled(true);

            txtProductoId.setEditable(true);
            getTxtCantidad().setEditable(true);
            txtProductoId.requestFocus();
            actualizarTabla();
        } else {
            this.setClienteAsociadoFactura(false);
            int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Desea registrar a este cliente?", "Este cliente no existe");
            if (seleccion == 0) {
                abrirVentanaRegistroSimpleCliente(tipo_persona, numero_identificacion_persona);
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
        this.txtProductoId.setText("");
        this.txtProductoNombre.setText("");
        this.getTxtCantidad().setText("");
        this.lblProductoPrecio.setVisible(false);
        this.lblProductoPrecioValor.setText("");

        this.txtProductoId.requestFocus();
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
        ArrayList<HashMap<String, String>> listaProductoEnVenta = menuPrincipal.getOBD().getArrayListProductosEnVenta(this.getIdVenta());
        VentaTableModel model = new VentaTableModel(listaProductoEnVenta);
        jtbVenta.setModel(model);
        jtbVenta.setFont(new Font("Arial", Font.BOLD, 16));
        jtbVenta.setBackground(Color.white);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();

        for (int i = 0; i < jtbVenta.getColumnCount(); i++) {
            int width;
            if (i == 0) {
                width = 135;
            } else if (i == 1) {
                width = 400;
            } else if (i == 3) {
                width = 80;
            } else {
                width = 200;
            }

            if (i == 3) {
                dtcr.setHorizontalAlignment(JLabel.CENTER);
                jtbVenta.getColumnModel().getColumn(i).setCellRenderer(dtcr);
            }
            jtbVenta.getColumnModel().getColumn(i).setPreferredWidth(width);
            jtbVenta.getColumnModel().getColumn(i).setResizable(false);
        }

        jtbVenta.getTableHeader().getDefaultRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        jtbVenta.getColumnModel().setColumnSelectionAllowed(false);
        jtbVenta.getTableHeader().setReorderingAllowed(false);

        //Actualizar subtotal, total e Iva.
        if (listaProductoEnVenta != null) {
            double subtotal = 0;
            double total = 0;
            double impuesto = 0;
            for (int i = 0; i < jtbVenta.getRowCount(); i++) {
                impuesto = impuesto + (Double.parseDouble(jtbVenta.getValueAt(i, 4).toString()) * (Double.parseDouble(jtbVenta.getValueAt(i, 3).toString())));
                subtotal = subtotal + (Double.parseDouble(jtbVenta.getValueAt(i, 2).toString()) * (Double.parseDouble(jtbVenta.getValueAt(i, 3).toString())));
                total = total + (Double.parseDouble(jtbVenta.getValueAt(i, 5).toString()) * (Double.parseDouble(jtbVenta.getValueAt(i, 3).toString())));
            }
            lblTotalValor.setText(redondeo.format(total).replace(",", "."));
            lblImpuestoValor.setText(redondeo.format(impuesto).replace(",", "."));
            lblSubtotalValor.setText(redondeo.format(subtotal).replace(",", "."));
//            actualizarLblSubtotal(listaProductoEnVenta);
//            actualizarLblImpuesto(listaProductoEnVenta);
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
            int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Desea eliminar el artículo " + jtbVenta.getModel().getValueAt(numeroRow, 1) + " de la venta?", "Eliminar artículo");

            if (seleccion == 0) {

                String serial = jtbVenta.getValueAt(numeroRow, 0).toString();
                menuPrincipal.getOBD().eliminarProductoEnVenta(this.getIdVenta(), serial);
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
            menuPrincipal.getOBD().setEstadoVenta(this.getIdVenta(), ObjetoBaseDatos.EstadoVenta.Cancelada);
            this.setIdVenta(-1);
            limpiarCamposTxt();
            actualizarTabla();
        }
    }

    /**
     * Método para pausar la venta actual.
     */
    private void pausarVenta() {
        int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Desea pausar la venta actual?", "Pausar venta");
        if (seleccion == 0) {
            menuPrincipal.getOBD().setEstadoVenta(this.getIdVenta(), ObjetoBaseDatos.EstadoVenta.Pausada);
            this.setIdVenta(-1);
            limpiarCamposTxt();
            actualizarTabla();
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
            String cant = JOptionPane.showInputDialog(this, "Ingrese la nueva cantidad de " + jtbVenta.getModel().getValueAt(rowNumber, 1), jtbVenta.getModel().getValueAt(rowNumber, 3));
            if (!cant.isEmpty()) {

                XBigDecimal cantidadNueva = new XBigDecimal(cant);
                XBigDecimal cantidadAnterior = new XBigDecimal(jtbVenta.getModel().getValueAt(rowNumber, 3).toString());
                if (cantidadNueva.compareTo(new XBigDecimal(0)) > 0) {
                    String codigoBarra = jtbVenta.getModel().getValueAt(rowNumber, 0).toString();
                    if (productoTabla.get(codigoBarra) == null) {
                        productoTabla.put(codigoBarra, menuPrincipal.getOBD().getLimiteMaximoProducto(codigoBarra));
                    }
                    if (cantidadNueva.compareTo(new BigDecimal(productoTabla.get(codigoBarra))) > 0) {
                        JOptionPane.showMessageDialog(null, "El limite de '" + jtbVenta.getModel().getValueAt(rowNumber, 1) + "' maximo permitido por persona es de " + productoTabla.get(codigoBarra) + "", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        modificarCantidadProducto(codigoBarra, new XBigDecimal(cantidadNueva.add(cantidadAnterior.negate()).toString()));
                        actualizarTabla();
                    }
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
    private void modificarCantidadProducto(String codigoBarra, XBigDecimal cantidad) {
        menuPrincipal.getOBD().agregarProductoEnVenta(idVenta, codigoBarra, cantidad.doubleValue());
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
        if (!map.isEmpty()) {
            productoPorAsociar = new ModeloProducto(map);
            lblProductoPrecio.setVisible(true);
            lblProductoPrecioValor.setText(map.get("pvp"));
            txtProductoNombre.setText(map.get("descripcion"));
            txtCantidad.setText("1");
            txtCantidad.setSelectionStart(0);
            txtCantidad.setSelectionEnd(txtCantidad.getText().length());
            txtCantidad.requestFocus();
        } else {
            Utilidades.Sonidos.beep();
            limpiarCamposProductoTxt();
            txtProductoId.setSelectionStart(0);
            txtProductoId.setSelectionEnd(txtProductoId.getText().length());
            txtProductoId.requestFocus();
        }
    }

    /**
     * Actualiza el lblSubtotalValor con el precio de todos los productos
     * includos en la venta.
     *
     * @param listaProductoEnVenta
     */
    public void actualizarLblSubtotal(final ArrayList<HashMap<String, String>> listaProductoEnVenta) {
        XBigDecimal montoTotal = new XBigDecimal(0);
        XBigDecimal totalProducto;

        for (HashMap<String, String> producto : listaProductoEnVenta) {
            totalProducto = new XBigDecimal(producto.get("pvp"));
            montoTotal = new XBigDecimal(montoTotal.add(totalProducto).toString());
        }
        this.getLblSubtotalValor().setText(montoTotal.setScale(2, RoundingMode.HALF_EVEN).toString());
    }

    /**
     * Actualiza el lblImpuestoValor con el precio de todos los productos
     * includos en la venta.
     *
     * @param listaProductoEnVenta
     */
    public void actualizarLblImpuesto(final ArrayList<HashMap<String, String>> listaProductoEnVenta) {
        XBigDecimal montoTotal = new XBigDecimal(0);
        XBigDecimal totalProducto;

        for (HashMap<String, String> producto : listaProductoEnVenta) {
            totalProducto = new XBigDecimal(producto.get("impuesto"));
            montoTotal = new XBigDecimal(montoTotal.add(totalProducto).toString());
        }
//        System.out.println("montototal=" + montoTotal);
        this.getLblImpuestoValor().setText(montoTotal.setScale(2, RoundingMode.HALF_EVEN).toString());
    }

    /**
     * Actualiza el lblTotalValor con el precio de todos los productos includos
     * en la venta.
     *
     * @param listaProductoEnVenta
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
