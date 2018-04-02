/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;

import ClasesExtendidas.Numeros.XBigDecimal;
import ClasesExtendidas.Tablas.PagoTableModel;
import ClasesExtendidas.Tablas.TipoPagoTableModel;
import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import PuntoVenta.Inicio.MenuPrincipal;
import static PuntoVenta.Ventanas.Venta.jtbVenta;
import static PuntoVenta.Ventanas.Venta.txtDocumento;
import static PuntoVenta.Ventanas.Venta.txtNombreCliente;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static PuntoVenta.Ventanas.Venta.txtNumeroFactura;
import Utilidades.ArticuloDescontar;
import Utilidades.ValorPagos;

/**
 *
 * @author jose
 */
public class Pago extends javax.swing.JInternalFrame {

    /**
     * Creates new faclientesturaorm pagarclientesompra
     */
    private final Venta venta;
    public final MenuPrincipal menuPrincipal;
    private final XBigDecimal montoVenta;
    private XBigDecimal montoPagado;
    private DecimalFormat redondeo = new DecimalFormat("0.00");
    public boolean verificador = false, verify = false;

    public Pago(Venta venta) {
        this.venta = venta;
        this.menuPrincipal = venta.menuPrincipal;
        initComponents();
        String tipoma = "";
        if (EF.isSelected()) {
            tipoma = "Efectivo";
        }
        if (TDD.isSelected()) {
            tipoma = "Debito";
        }
        if (TDC.isSelected()) {
            tipoma = "Credito";
        }
        if (CTK.isSelected()) {
            tipoma = "Cesta Tickets";
            cmbBoxBancos.setEnabled(false);
        }

        TipoPagoTableModel model = new TipoPagoTableModel();
        tblTipoPago.setModel(model);
        crearHotKeys();
        montoVenta = new XBigDecimal(venta.getLblTotalValor().getText());
        montoPagado = new XBigDecimal(0);
        lblMontoVentaValor.setText(montoVenta.setScale(2, RoundingMode.HALF_EVEN).toString());
        lblSaldoValor.setText(montoVenta.add(montoPagado.negate()).setScale(2, RoundingMode.HALF_EVEN).toString());
        PagoEfectivo();
    }

    private void crearHotKeys() {

        Action actCerrarVentana = new AbstractAction("actionCerrarVentanaCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        };

        Action actEfectivo = new AbstractAction("actionEfectivo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                PagoEfectivo();
            }
        };

        Action actTDD = new AbstractAction("actionTDD") {
            @Override
            public void actionPerformed(ActionEvent e) {
                PagoTDD();
            }
        };

        Action actTDC = new AbstractAction("actionTDC") {
            @Override
            public void actionPerformed(ActionEvent e) {
                PagoTDC();
            }
        };

        Action actCTK = new AbstractAction("actionCTK") {
            @Override
            public void actionPerformed(ActionEvent e) {
                PagoCTK();
            }
        };
        Action actAceptar = new AbstractAction("actionAceptar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                factura();
            }
        };

        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        actAceptar.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));
        actEfectivo.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        actTDD.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        actTDC.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        actCTK.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));

        EF.getActionMap().put("actionEfectivo", actEfectivo);
        EF.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actEfectivo.getValue(Action.ACTION_COMMAND_KEY), "actionEfectivo");
        TDD.getActionMap().put("actionTDD", actTDD);
        TDD.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actTDD.getValue(Action.ACTION_COMMAND_KEY), "actionTDD");
        TDC.getActionMap().put("actionTDC", actTDC);
        TDC.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actTDC.getValue(Action.ACTION_COMMAND_KEY), "actionTDC");
        CTK.getActionMap().put("actionCTK", actCTK);
        CTK.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCTK.getValue(Action.ACTION_COMMAND_KEY), "actionCTK");
        btnSalir.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        btnSalir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

        btnAceptar.getActionMap().put("actionAceptar", actAceptar);
        btnAceptar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAceptar.getValue(Action.ACTION_COMMAND_KEY), "actionAceptar");

        InternalFrameAdapter listener = new InternalFrameAdapter() {
            private Object pago;

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                Pago.txtMonto.requestFocus();
            }
        };

        this.addInternalFrameListener(listener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblMontoVenta = new javax.swing.JLabel();
        lblMontoVentaValor = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblFormaPago = new javax.swing.JLabel();
        lblMontoPagar = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipoPago = new javax.swing.JTable();
        cmbBoxBancos = new javax.swing.JComboBox();
        lblBanco = new javax.swing.JLabel();
        lblCT = new javax.swing.JLabel();
        txtMontoCT = new javax.swing.JTextField();
        txtCantidadCT = new javax.swing.JTextField();
        lblX = new javax.swing.JLabel();
        EF = new javax.swing.JRadioButton();
        TDD = new javax.swing.JRadioButton();
        TDC = new javax.swing.JRadioButton();
        CTK = new javax.swing.JRadioButton();
        apro1 = new javax.swing.JLabel();
        apro2 = new javax.swing.JTextField();
        lblSaldo = new javax.swing.JLabel();
        lblCambio = new javax.swing.JLabel();
        lblCambioValor = new javax.swing.JLabel();
        lblSaldoValor = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(32, 182, 155));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnAceptar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAceptar.setText("<html><center>Aceptar<br>SPACE</center></html>");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSalir.setText("<html><center>Cancelar<br>Alt+Q</center></html>");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblMontoVenta.setFont(new java.awt.Font("Arial", 0, 28)); // NOI18N
        lblMontoVenta.setForeground(new java.awt.Color(255, 255, 255));
        lblMontoVenta.setText("Monto de la venta:");

        lblMontoVentaValor.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        lblMontoVentaValor.setForeground(new java.awt.Color(0, 0, 0));
        lblMontoVentaValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMontoVentaValor.setText("0.00");

        jPanel2.setBackground(new java.awt.Color(32, 182, 155));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        lblFormaPago.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblFormaPago.setForeground(java.awt.Color.white);
        lblFormaPago.setText("Forma de pago:");

        lblMontoPagar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblMontoPagar.setForeground(java.awt.Color.white);
        lblMontoPagar.setText("Monto a pagar:");

        txtMonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMonto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoKeyReleased(evt);
            }
        });

        tblTipoPago.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblTipoPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(tblTipoPago);

        cmbBoxBancos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "BOD", "Provincial", "Venezuela" }));
        cmbBoxBancos.setEnabled(false);
        cmbBoxBancos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBoxBancosItemStateChanged(evt);
            }
        });
        cmbBoxBancos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbBoxBancosMouseClicked(evt);
            }
        });
        cmbBoxBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxBancosActionPerformed(evt);
            }
        });
        cmbBoxBancos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbBoxBancosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbBoxBancosKeyReleased(evt);
            }
        });

        lblBanco.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblBanco.setForeground(java.awt.Color.white);
        lblBanco.setText("Banco:");
        lblBanco.setEnabled(false);

        lblCT.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblCT.setForeground(java.awt.Color.white);
        lblCT.setText("Cesta tickets:");
        lblCT.setEnabled(false);

        txtMontoCT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtMontoCT.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMontoCT.setEnabled(false);
        txtMontoCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoCTActionPerformed(evt);
            }
        });
        txtMontoCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoCTKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoCTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoCTKeyReleased(evt);
            }
        });

        txtCantidadCT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCantidadCT.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCantidadCT.setEnabled(false);
        txtCantidadCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadCTActionPerformed(evt);
            }
        });
        txtCantidadCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadCTKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadCTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadCTKeyReleased(evt);
            }
        });

        lblX.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblX.setForeground(java.awt.Color.white);
        lblX.setText("X");
        lblX.setEnabled(false);

        grupo.add(EF);
        EF.setText("EF");
        EF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EFActionPerformed(evt);
            }
        });

        grupo.add(TDD);
        TDD.setText("TDD");
        TDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TDDActionPerformed(evt);
            }
        });

        grupo.add(TDC);
        TDC.setText("TDC");
        TDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TDCActionPerformed(evt);
            }
        });

        grupo.add(CTK);
        CTK.setText("CTK");
        CTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CTKActionPerformed(evt);
            }
        });

        apro1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        apro1.setForeground(java.awt.Color.white);
        apro1.setText("Aprobacion:");

        apro2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        apro2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apro2KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                apro2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                apro2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblFormaPago)
                                .addGap(166, 166, 166))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblMontoPagar)
                                .addGap(4, 4, 4)
                                .addComponent(txtMonto))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCT)
                            .addComponent(lblBanco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCantidadCT, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblX)
                                .addGap(12, 12, 12)
                                .addComponent(txtMontoCT))
                            .addComponent(cmbBoxBancos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(EF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TDD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TDC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CTK)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(apro1)
                        .addGap(2, 2, 2)
                        .addComponent(apro2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFormaPago)
                            .addComponent(EF)
                            .addComponent(TDD)
                            .addComponent(TDC)
                            .addComponent(CTK))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbBoxBancos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBanco))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidadCT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMontoCT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblX)
                            .addComponent(lblCT)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMontoPagar)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apro1)
                    .addComponent(apro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblSaldo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblSaldo.setForeground(new java.awt.Color(255, 255, 255));
        lblSaldo.setText("Saldo:");
        lblSaldo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblCambio.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblCambio.setForeground(new java.awt.Color(255, 255, 255));
        lblCambio.setText("Cambio:");

        lblCambioValor.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblCambioValor.setForeground(new java.awt.Color(0, 0, 0));
        lblCambioValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCambioValor.setText("0.00");

        lblSaldoValor.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblSaldoValor.setForeground(new java.awt.Color(0, 0, 0));
        lblSaldoValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaldoValor.setText("0.00");

        jButton1.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        jButton1.setText("<html><center>Agregar Pago<br>ENTER</center></html>");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMontoVenta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMontoVentaValor, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCambio)
                                    .addComponent(lblSaldo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblCambioValor, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                                    .addComponent(lblSaldoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(91, 91, 91)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMontoVenta)
                    .addComponent(lblMontoVentaValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSaldoValor)
                            .addComponent(lblSaldo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCambioValor)
                            .addComponent(lblCambio)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void PagoEfectivo() {
        txtMonto.setBackground(Color.white);
        jButton1.setEnabled(false);
        txtMontoCT.setEnabled(true);
        txtCantidadCT.setEnabled(true);
        txtMontoCT.setBackground(Color.GRAY);
        txtCantidadCT.setBackground(Color.GRAY);
        apro1.setVisible(false);
        apro2.setVisible(false);
        apro2.setText("");
        lblMontoPagar.setEnabled(true);
        txtMonto.setEnabled(true);
        txtMonto.setEditable(true);
        EF.setSelected(true);
        txtCantidadCT.setText("");
        txtMontoCT.setText("");
        txtMonto.setText("");
        txtMonto.requestFocus();
        cmbBoxBancos.setEnabled(false);
        txtMontoCT.setEditable(false);
        txtCantidadCT.setEditable(false);
        lblCT.setEnabled(false);
        lblX.setEnabled(false);
        lblBanco.setEnabled(false);
    }

    public void PagoTDD() {
        jButton1.setEnabled(false);
        txtMonto.setBackground(Color.white);
        txtMontoCT.setEnabled(true);
        txtCantidadCT.setEnabled(true);
        txtMontoCT.setBackground(Color.GRAY);
        txtCantidadCT.setBackground(Color.GRAY);
        txtMontoCT.setEditable(false);
        txtCantidadCT.setEditable(false);
        apro1.setVisible(true);
        apro2.setVisible(true);
        apro2.setText("");
        String saldo = lblSaldoValor.getText();
        txtMonto.setText(saldo);
        txtMonto.setEnabled(true);
        lblMontoPagar.setEnabled(true);
        txtCantidadCT.setText("");
        txtMontoCT.setText("");
        lblCT.setEnabled(false);
        lblX.setEnabled(false);
        TDD.setSelected(true);
        lblBanco.setEnabled(true);
        cmbBoxBancos.setEnabled(true);
        apro2.requestFocus();
    }

    public void PagoTDC() {
        PagoTDD();
        TDC.setSelected(true);
    }

    public void PagoCTK() {
        PagoEfectivo();
        txtMonto.setBackground(Color.GRAY);
        txtMonto.setEditable(false);
        txtMontoCT.setEnabled(true);
        txtCantidadCT.setEnabled(true);
        txtMontoCT.setEditable(true);
        txtCantidadCT.setEditable(true);
        lblCT.setEnabled(true);
        lblX.setEnabled(true);
        CTK.setSelected(true);
        lblMontoPagar.setEnabled(false);
        txtCantidadCT.requestFocus();
        txtMontoCT.setBackground(Color.white);
        txtCantidadCT.setBackground(Color.white);
    }

    private void txtMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyPressed
        Object selected2 = cmbBoxBancos.getSelectedItem();
        txtMonto.requestFocus();
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            PagoEfectivo();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            PagoTDD();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            PagoTDC();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            PagoCTK();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton1.doClick();
        }
    }//GEN-LAST:event_txtMontoKeyPressed

    public void factura() {
        List lista = new ArrayList();
        XBigDecimal cantidad = new XBigDecimal(lblMontoVentaValor.getText());
        String factura = txtNumeroFactura.getText();
        String nombre = txtNombreCliente.getText();
        String cedula = txtDocumento.getText();
        String direccion = menuPrincipal.getOBD().direccionCliente(cedula);
        String pagado = "";
        String tipo = "";
        menuPrincipal.empresa = menuPrincipal.getOBD().datosEmpresas();
        int aux = 0;
        double total = 0;
        double totefe = 0;
        double tottdd = 0;
        double tottdc = 0;
        double totctk = 0;
        double pagoconiva = 0;
        double pagosiniva = 0;
        for (int i = 0; i < tblTipoPago.getRowCount(); i++) {
            if (!tipo.equals(tblTipoPago.getValueAt(i, 0).toString())) {
                tipo = tblTipoPago.getValueAt(i, 0).toString();
                total = total + Double.parseDouble(tblTipoPago.getValueAt(i, 1).toString());
                aux++;
            }
            if ("Efectivo".equals(tblTipoPago.getValueAt(i, 0).toString())) {
                totefe = totefe + Double.parseDouble(tblTipoPago.getValueAt(i, 1).toString());
            }
            if ("Debito".equals(tblTipoPago.getValueAt(i, 0).toString())) {
                tottdd = tottdd + Double.parseDouble(tblTipoPago.getValueAt(i, 1).toString());
            }
            if ("Credito".equals(tblTipoPago.getValueAt(i, 0).toString())) {
                tottdc = tottdc + Double.parseDouble(tblTipoPago.getValueAt(i, 1).toString());
            }
            if ("Cestaticket".equals(tblTipoPago.getValueAt(i, 0).toString())) {
                totctk = totctk + Double.parseDouble(tblTipoPago.getValueAt(i, 1).toString());
            }
        }
        for (int i = 0; i < jtbVenta.getRowCount(); i++) {
            if ("0.00".equals(jtbVenta.getValueAt(i, 4).toString())) {
                pagosiniva = pagosiniva + Double.parseDouble(jtbVenta.getValueAt(i, 5).toString());
            } else {
                pagoconiva = pagoconiva + Double.parseDouble(jtbVenta.getValueAt(i, 5).toString());
            }
        }
        List<ValorPagos> vp = new ArrayList();
        if (totefe != 0) {
            vp.add(new ValorPagos("Efectivo", totefe));
        }
        if (tottdd != 0) {
            vp.add(new ValorPagos("Debito", tottdd));
        }
        if (tottdc != 0) {
            vp.add(new ValorPagos("Credito", tottdc));
        }
        if (totctk != 0) {
            vp.add(new ValorPagos("Cestaticket", totctk));
        }
        pagado = "" + redondeo.format(total);
        pagado = pagado.replace(",", ".");
        if (aux > 1) {
            tipo = "Mixto";
        }
        List<ArticuloDescontar> ad = new ArrayList();
        Double cambio = Double.parseDouble(pagado) - Double.parseDouble("" + cantidad);
        String vuelto = redondeo.format(cambio).replace(",", ".");
        for (int i = 0; i < jtbVenta.getRowCount(); i++) {
            ad.add(new ArticuloDescontar(jtbVenta.getValueAt(i, 0).toString(), jtbVenta.getValueAt(i, 3).toString()));
            Double subtotal = pagoconiva - Double.parseDouble(venta.impuesto);
            Double pagouni = Double.parseDouble(jtbVenta.getValueAt(i, 2).toString()) + Double.parseDouble(jtbVenta.getValueAt(i, 4).toString());
            String descrip = "";
            if (jtbVenta.getValueAt(i, 1).toString().length() > 13) {
                for (int k = 0; k < jtbVenta.getValueAt(i, 1).toString().length(); k++) {
                    if (k % 13 == 0 && k != 0) {
                        descrip = descrip + jtbVenta.getValueAt(i, 1).toString().substring(0, k) + "<br/>" + jtbVenta.getValueAt(i, 1).toString().substring(k, jtbVenta.getValueAt(i, 1).toString().length());
                    }
                }
            } else {
                descrip = jtbVenta.getValueAt(i, 1).toString();
            }
//            System.out.println(menuPrincipal.getEmpleado().getNombre() + " " +menuPrincipal.getEmpleado().getApellido());
            if ("0.00".equals(jtbVenta.getValueAt(i, 4).toString())) {
                PuntoVenta.reporte1 nuevo = new PuntoVenta.reporte1(jtbVenta.getValueAt(i, 3).toString(), descrip + " (E)", "" + pagouni.toString(), jtbVenta.getValueAt(i, 5).toString(), cantidad.toString(), nombre, cedula, direccion, factura, pagado, tipo, menuPrincipal.empresa.getTipoEmpresa(), menuPrincipal.empresa.getRif(), menuPrincipal.empresa.getNombre(), menuPrincipal.empresa.getDireccion(), menuPrincipal.empresa.getMoneda(), "" + pagosiniva, "" + subtotal, venta.impuesto, vuelto, menuPrincipal.getEmpleado().getNombre() + " " +menuPrincipal.getEmpleado().getApellido());
                lista.add(nuevo);
            } else {
                PuntoVenta.reporte1 nuevo = new PuntoVenta.reporte1(jtbVenta.getValueAt(i, 3).toString(), descrip, "" + pagouni.toString(), jtbVenta.getValueAt(i, 5).toString(), cantidad.toString(), nombre, cedula, direccion, factura, pagado, tipo, menuPrincipal.empresa.getTipoEmpresa(), menuPrincipal.empresa.getRif(), menuPrincipal.empresa.getNombre(), menuPrincipal.empresa.getDireccion(), menuPrincipal.empresa.getMoneda(), "" + pagosiniva, "" + subtotal, venta.impuesto, vuelto, menuPrincipal.getEmpleado().getNombre() + " " +menuPrincipal.getEmpleado().getApellido());
                lista.add(nuevo);
            }
        }
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/PuntoVenta/ticket.jasper");
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JasperViewer visor = new JasperViewer(jprint, false);
            visor.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        menuPrincipal.getOBD().descontarcantidad(ad);
        menuPrincipal.getOBD().setEstadoVenta(venta.getIdVenta(), ObjetoBaseDatos.EstadoVenta.Finalizada);
        menuPrincipal.getOBD().actualizaiva(venta.getIdVenta(), venta.impuesto, montoVenta, pagoconiva, pagosiniva, pagado, vuelto, menuPrincipal.getEmpleado().getId());
        int id = venta.getIdVenta();
        venta.limpiarVenta();
        menuPrincipal.getOBD().guardarPagos(id, vp);
        PuntoVenta.Inicio.MenuPrincipal.btnCaja.setEnabled(true);
        PuntoVenta.Inicio.MenuPrincipal.btnVentas.setEnabled(true);
        cerrarVentana();
    }

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        factura();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        PuntoVenta.Ventanas.Venta.txtProductoId.requestFocus();
        cerrarVentana();

    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void cmbBoxBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxBancosActionPerformed

    }//GEN-LAST:event_cmbBoxBancosActionPerformed

    private void txtMontoCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoCTActionPerformed

    }//GEN-LAST:event_txtMontoCTActionPerformed

    private void txtMontoCTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoCTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            PagoEfectivo();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            PagoTDD();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            PagoTDC();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            PagoCTK();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton1.doClick();
            txtMontoCT.setText("");
            txtCantidadCT.setText("");
            txtCantidadCT.requestFocus();
        }
    }//GEN-LAST:event_txtMontoCTKeyPressed

    private void txtCantidadCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadCTActionPerformed

    }//GEN-LAST:event_txtCantidadCTActionPerformed

    private void txtCantidadCTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadCTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            PagoEfectivo();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            PagoTDD();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            PagoTDC();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            PagoCTK();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMontoCT.requestFocus();
        }
    }//GEN-LAST:event_txtCantidadCTKeyPressed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed

// TODO add your handling code here:
    }//GEN-LAST:event_jPanel2KeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        Object selected2 = cmbBoxBancos.getSelectedItem();
        txtMonto.requestFocus();
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            PagoEfectivo();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            PagoTDD();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            PagoTDC();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            PagoCTK();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tipomo = "";
            if (EF.isSelected()) {
                tipomo = "Efectivo";
                cmbBoxBancos.setEnabled(false);
            }
            if (TDD.isSelected()) {
                tipomo = "Debito";

            }
            if (TDC.isSelected()) {
                tipomo = "Credito";
            }
            if (CTK.isSelected()) {
                tipomo = "Cesta Tickets";
                cmbBoxBancos.setEnabled(false);

            }

            String ct = "";
            ct = txtCantidadCT.getText();
            XBigDecimal monto1 = null;
            if (ct != "") {
                XBigDecimal monto = new XBigDecimal(txtMonto.getText());
                monto1 = monto;
            }
            String ap = "p";
            if (evt.getKeyCode() == KeyEvent.VK_ENTER && TDD.isSelected()) {
//                System.out.println("pqqqqqqqqqqqqww");
                apro2.requestFocus();
                String p = lblSaldoValor.getText();

            }
            if (evt.getKeyCode() == KeyEvent.VK_ENTER && TDC.isSelected()) {
//                System.out.println("pqqqqqqqqqqqqww");
                apro2.requestFocus();
                String p = lblSaldoValor.getText();

            }

            if (EF.isSelected() || CTK.isSelected()) {
                agregarMontoAlPago(tipomo, monto1);
                txtMonto.setText("0.00");
                txtMonto.setSelectionStart(0);
                txtMonto.setSelectionEnd(txtMonto.getText().length());
                cmbBoxBancos.enable(false);
                cmbBoxBancos.setSelectedItem("Seleccione");
                txtMonto.requestFocus();
            }

        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER
                && Double.parseDouble(txtMonto.getText()) > Double.parseDouble(lblSaldoValor.getText())) {
            JOptionPane.showMessageDialog(null, "No puede ingresar un pago mayor al saldo para los pagos por punto.");
            txtMonto.setText(lblSaldoValor.getText());

        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER
                && Double.parseDouble(txtMonto.getText()) <= Double.parseDouble(lblSaldoValor.getText())) {
            XBigDecimal monto = new XBigDecimal(txtMonto.getText());
            if (TDD.isSelected() || TDC.isSelected()) {
                txtMonto.setSelectionStart(0);
                txtMonto.setSelectionEnd(txtMonto.getText().length());
            } else {
                txtMonto.setText("0.00");
                txtMonto.setSelectionStart(0);
                txtMonto.setSelectionEnd(txtMonto.getText().length());
                cmbBoxBancos.enable(false);
                cmbBoxBancos.setSelectedItem("Seleccione");
            }
        }
    }//GEN-LAST:event_formKeyPressed


    private void EFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EFActionPerformed
        PagoEfectivo();
    }//GEN-LAST:event_EFActionPerformed

    private void TDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TDDActionPerformed
        PagoTDD();
    }//GEN-LAST:event_TDDActionPerformed

    private void TDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TDCActionPerformed
        PagoTDC();
// TODO add your handling code here:
    }//GEN-LAST:event_TDCActionPerformed

    private void CTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CTKActionPerformed
        PagoCTK();
        // TODO add your handling code here:
    }//GEN-LAST:event_CTKActionPerformed

    private void cmbBoxBancosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBoxBancosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBoxBancosKeyReleased

    private void cmbBoxBancosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBoxBancosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMonto.requestFocus();
        }
    }//GEN-LAST:event_cmbBoxBancosKeyPressed

    private void apro2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apro2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            PagoEfectivo();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            PagoTDD();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            PagoTDC();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F4) {
            PagoCTK();
        }

        String q = apro2.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String tipomo = "";

            if (TDD.isSelected()) {
                tipomo = "Debito";

            }
            if (TDC.isSelected()) {
                tipomo = "Credito";
            }

            if (!"".equals(txtMonto.getText()) && cmbBoxBancos.getSelectedIndex() != 0 && !"".equals(apro2.getText())) {
                jButton1.doClick();
                txtMonto.setText("0.00");
                apro2.setText("");
                apro2.requestFocus();
            }
        }


    }//GEN-LAST:event_apro2KeyPressed

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        if (txtMonto.getText().startsWith(".")) {
            txtMonto.setText("0.");
        }
        if (txtMonto.getText().length() > 18) {
            evt.consume();
        }
        if (txtMonto.getText().contains(".") && (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }
        if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        crearPago();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
        if ("".equals(txtMonto.getText())) {
            jButton1.setEnabled(false);
        } else if (TDD.isSelected() || TDC.isSelected() ? !"".equals(apro2.getText()) && (Double.parseDouble(lblSaldoValor.getText()) > 0) && cmbBoxBancos.getSelectedIndex() != 0 : (Double.parseDouble(lblSaldoValor.getText()) > 0)) {
            jButton1.setEnabled(true);
        }

    }//GEN-LAST:event_txtMontoKeyReleased

    private void txtMontoCTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoCTKeyReleased
        if (("".equals(txtMontoCT.getText()) && "".equals(txtCantidadCT.getText()))) {
            jButton1.setEnabled(false);
        } else if (Double.parseDouble(lblSaldoValor.getText()) > 0) {
            double monto = Double.parseDouble(txtMontoCT.getText()) * Double.parseDouble(txtCantidadCT.getText());
            txtMonto.setText("" + monto);
            if (Double.parseDouble(txtMonto.getText()) > 0) {
                jButton1.setEnabled(true);
            }
        }
    }//GEN-LAST:event_txtMontoCTKeyReleased

    private void txtMontoCTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoCTKeyTyped
        if (txtMonto.getText().startsWith(".")) {
            txtMonto.setText("0.");
        }
        if (txtMonto.getText().length() > 10) {
            evt.consume();
        }
        if (txtMonto.getText().contains(".") && (evt.getKeyChar() == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }
        if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_PERIOD)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtMontoCTKeyTyped

    private void txtCantidadCTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadCTKeyReleased

    }//GEN-LAST:event_txtCantidadCTKeyReleased

    private void apro2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apro2KeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
        if (apro2.getText().length() > 18) {
            evt.consume();
        }
    }//GEN-LAST:event_apro2KeyTyped

    private void txtCantidadCTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadCTKeyTyped

        if (txtMonto.getText().length() > 18) {
            evt.consume();
        }

        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadCTKeyTyped

    private void apro2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apro2KeyReleased

        if (Double.parseDouble(lblSaldoValor.getText()) > 0) {
            if ((!"".equals(apro2.getText()) && cmbBoxBancos.getSelectedIndex() > 0)) {
                jButton1.setEnabled(true);
            } else {
                jButton1.setEnabled(false);
            }
        }
    }//GEN-LAST:event_apro2KeyReleased

    private void cmbBoxBancosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbBoxBancosMouseClicked
    }//GEN-LAST:event_cmbBoxBancosMouseClicked

    private void cmbBoxBancosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBoxBancosItemStateChanged
        if (cmbBoxBancos.getSelectedIndex() < 1) {
            jButton1.setEnabled(false);
        } else if (Double.parseDouble(lblSaldoValor.getText()) > 0) {
            if ((!"".equals(apro2.getText()) && cmbBoxBancos.getSelectedIndex() > 0)) {
                jButton1.setEnabled(true);
            }
        }
    }//GEN-LAST:event_cmbBoxBancosItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton CTK;
    public static javax.swing.JRadioButton EF;
    private javax.swing.JRadioButton TDC;
    private javax.swing.JRadioButton TDD;
    public static javax.swing.JLabel apro1;
    public static javax.swing.JTextField apro2;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSalir;
    public static javax.swing.JComboBox cmbBoxBancos;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblCT;
    private javax.swing.JLabel lblCambio;
    private javax.swing.JLabel lblCambioValor;
    private javax.swing.JLabel lblFormaPago;
    private javax.swing.JLabel lblMontoPagar;
    private javax.swing.JLabel lblMontoVenta;
    private javax.swing.JLabel lblMontoVentaValor;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblSaldoValor;
    private javax.swing.JLabel lblX;
    private javax.swing.JTable tblTipoPago;
    private javax.swing.JTextField txtCantidadCT;
    public static javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtMontoCT;
    // End of variables declaration//GEN-END:variables

    /**
     * Llama al método dispose() del componente.
     */
    private void cerrarVentana() {
        this.dispose();
    }

    /**
     * Agrega un monto y un tipoMoneda a la tabla de pago.
     *
     * @param tipoMoneda
     * @param monto
     */
    private void agregarMontoAlPago(String tipoMoneda, XBigDecimal monto) {
        boolean existe = false;
        XBigDecimal saldo = new XBigDecimal(montoVenta.add(montoPagado.negate()).toString());
        if (saldo.compareTo(new BigDecimal(0)) <= 0) {
            return;
        }
        String tipoMonedaEnTabla;
        XBigDecimal montoEnTabla;
        XBigDecimal cambio;
        TipoPagoTableModel model = (TipoPagoTableModel) tblTipoPago.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            tipoMonedaEnTabla = model.getValueAt(i, 0).toString();
            if (tipoMonedaEnTabla.equalsIgnoreCase(tipoMoneda)) {
                montoEnTabla = new XBigDecimal(model.getValueAt(i, 1).toString());
                model.setValueAt(monto.add(montoEnTabla).setScale(2, RoundingMode.HALF_EVEN), i, 1);
                existe = true;
            }
        }
        if (!existe) {
            model.addRow(new String[]{tipoMoneda, monto.setScale(2, RoundingMode.HALF_EVEN).toString()});
        }
        montoPagado = new XBigDecimal(0);
        for (int i = 0; i < model.getRowCount(); i++) {
            montoEnTabla = new XBigDecimal(model.getValueAt(i, 1).toString());
            montoPagado = new XBigDecimal(montoPagado.add(montoEnTabla).toString());
        }

        lblSaldoValor.setText(montoVenta.add(montoPagado.negate()).setScale(2, RoundingMode.HALF_EVEN).toString());
        cambio = new XBigDecimal(montoPagado.add(montoVenta.negate()).toString());
        saldo = new XBigDecimal(montoVenta.add(montoPagado.negate()).toString());
        if (saldo.compareTo(new BigDecimal(0)) < 0) {
            saldo = new XBigDecimal(0);
        }
        if (cambio.compareTo(new BigDecimal(0)) < 0) {
            cambio = new XBigDecimal(0);
        }
        lblSaldoValor.setText(saldo.setScale(2, RoundingMode.HALF_EVEN).toString());
        lblCambioValor.setText(cambio.setScale(2, RoundingMode.HALF_EVEN).toString());
    }
    ArrayList<HashMap<String, String>> lista = new ArrayList();

    /**
     * Método para crear un pago y asociarlo a una venta.
     */
    private void crearPago() {
        XBigDecimal cambio;
        String TipoPago = "";
        XBigDecimal saldo = new XBigDecimal(lblSaldoValor.getText());
        XBigDecimal monto = new XBigDecimal(0);
        if (!"".equals(txtMonto.getText()) && txtMonto.getText() != null) {
            monto = new XBigDecimal(txtMonto.getText());
        } else {
            JOptionPane.showMessageDialog(null, "INSERTE MONTO", "Error", JOptionPane.ERROR_MESSAGE);
        }
        HashMap<String, String> hm = new HashMap<String, String>();
        if (EF.isSelected()) {
            TipoPago = "Efectivo";
            txtMonto.requestFocus();
        } else if (TDD.isSelected()) {
            TipoPago = "Debito";
            txtMonto.requestFocus();
        } else if (TDC.isSelected()) {
            TipoPago = "Credito";
            txtMonto.requestFocus();
        } else {
            TipoPago = "Cestaticket";
            txtCantidadCT.requestFocus();
        }
        txtCantidadCT.setText("");
        txtMontoCT.setText("");
        hm.put("tipo", TipoPago);
        hm.put("monto", txtMonto.getText());
        lista.add(hm);
        PagoTableModel model = new PagoTableModel(lista);
        tblTipoPago.setModel(model);
        txtMonto.setText("");
        double result = Double.parseDouble(saldo.toString()) - Double.parseDouble(monto.toString());
        lblSaldoValor.setText("" + redondeo.format(result));
        lblSaldoValor.setText(lblSaldoValor.getText().replace(",", "."));
        jButton1.setEnabled(false);
        apro2.setText("");

        if (Double.parseDouble(lblSaldoValor.getText()) <= Double.parseDouble(lblCambioValor.getText())) {
            lblCambioValor.setText("" + redondeo.format(-1 * (Double.parseDouble(lblSaldoValor.getText()))));
            if (lblCambioValor.getText().startsWith("-")) {
                lblCambioValor.setText(lblCambioValor.getText().substring(1, lblCambioValor.getText().length()));
            }
            lblCambioValor.setText(lblCambioValor.getText().replace(",", "."));
            lblSaldoValor.setText("0.00");
            btnAceptar.setEnabled(true);

        }
    }
}
