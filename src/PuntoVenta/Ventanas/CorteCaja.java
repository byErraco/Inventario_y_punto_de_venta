/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;

import ClasesExtendidas.Numeros.XBigDecimal;
import ClasesExtendidas.Tablas.ArrayListTableModel;
import ClasesExtendidas.Tablas.TipoPagoTableModel;
import ClasesExtendidas.Tablas.CorteEstadoCajaTableModel;
import PuntoVenta.BaseDatos.Empresa;
import PuntoVenta.Inicio.MenuPrincipal;
import PuntoVenta.Reporte2;
import Utilidades.ValorPagos;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
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
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author inverdata
 */
public class CorteCaja extends javax.swing.JInternalFrame {

    private Caja caja;
    public MenuPrincipal menuPrincipal;

    boolean actualizastes = false;

    /**
     * Creates new form CorteCaja
     *
     * @param caja
     */
    public CorteCaja(Caja caja) {
        initComponents();
        this.caja = caja;
        this.menuPrincipal = caja.menuPrincipal;
        this.setTitle("Saphiro - Corte de caja");
        crearHotKeys();
        Date date = new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
        txtFecha.setText(dia + "/" + mes + "/" + ano);
        txtNumeroCaja.setText(String.valueOf(this.caja.menuPrincipal.getModeloCaja().getId()));
        txtEmpleado.setText(this.caja.menuPrincipal.getEmpleado().getNombre() + " " + this.caja.menuPrincipal.getEmpleado().getApellido());
        
        ArrayList<HashMap<String, String>> listaTiposPago = this.caja.menuPrincipal.getOBD().getArrayListTipoPago();
        for (HashMap<String, String> row : listaTiposPago) {
            cmbTipoPago.addItem(row.get("descripcion_pago"));
        }

        actualizarInformacionCaja();
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
        Action actAceptar = new AbstractAction("actionAceptar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                cortarCaja();
            }
        };

        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        actAceptar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        actFocusPuntoInteres.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

        pnlContenedor.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

        pnlContenedor.getActionMap().put("actionFocusPuntoInteres", actFocusPuntoInteres);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFocusPuntoInteres.getValue(Action.ACTION_COMMAND_KEY), "actionFocusPuntoInteres");

        pnlContenedor.getActionMap().put("actionAceptar", actAceptar);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAceptar.getValue(Action.ACCELERATOR_KEY), "actionAceptar");

        InternalFrameAdapter listener = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                caja.getTxtFiltro().requestFocus();
            }
        };

        this.addInternalFrameListener(listener);

    }

    private void cerrarVentana() {
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        pnlContenedor = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultadoCorte = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        lblTotalValor = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTotalVentas = new javax.swing.JTextField();
        txtNumeroCaja = new javax.swing.JTextField();
        lblNumeroCaja = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lblEmpleado = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCortesAnteriores = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtTotalCortes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtExcedente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRestante = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtMonto = new javax.swing.JTextField();
        lblMonto = new javax.swing.JLabel();
        cmbTipoPago = new javax.swing.JComboBox();
        lblTipoMoneda = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(117, 133, 155));
        setClosable(true);
        setTitle("Saphiro - Corte de Caja");

        pnlContenedor.setBackground(new java.awt.Color(32, 182, 155));

        jPanel1.setBackground(new java.awt.Color(32, 182, 155));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Detalles del corte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), java.awt.Color.white)); // NOI18N

        btnAceptar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAceptar.setText("<html><center>Finalizar Corte<br>F2</center></html>");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tblResultadoCorte);

        lblTotal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("Total:");

        lblTotalValor.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTotalValor.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalValor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalValor.setText("0.00");

        btnSalir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSalir.setText("<html><center>Salir<br>Alt + Q</center></html>");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addComponent(lblTotal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTotalValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblTotalValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel4.setBackground(new java.awt.Color(32, 182, 155));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total ventas:");

        txtTotalVentas.setEditable(false);
        txtTotalVentas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtNumeroCaja.setEditable(false);
        txtNumeroCaja.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblNumeroCaja.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblNumeroCaja.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroCaja.setText("N° Caja:");
        lblNumeroCaja.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblNumeroCaja.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblNumeroCaja.setMaximumSize(new java.awt.Dimension(74, 15));
        lblNumeroCaja.setMinimumSize(new java.awt.Dimension(74, 15));
        lblNumeroCaja.setPreferredSize(new java.awt.Dimension(74, 15));

        lblFecha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("Fecha:");
        lblFecha.setMaximumSize(new java.awt.Dimension(74, 15));
        lblFecha.setMinimumSize(new java.awt.Dimension(74, 15));
        lblFecha.setPreferredSize(new java.awt.Dimension(74, 15));

        txtFecha.setEditable(false);
        txtFecha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblEmpleado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        lblEmpleado.setText("Empleado:");

        txtEmpleado.setEditable(false);
        txtEmpleado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        tblCortesAnteriores.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblCortesAnteriores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane2.setViewportView(tblCortesAnteriores);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total cortes:");

        txtTotalCortes.setEditable(false);
        txtTotalCortes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cortes anteriores:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Excedente");

        txtExcedente.setEditable(false);
        txtExcedente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Restante:");

        txtRestante.setEditable(false);
        txtRestante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtRestante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRestanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                            .addComponent(lblNumeroCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha)
                            .addComponent(txtNumeroCaja, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmpleado, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalVentas)
                            .addComponent(txtTotalCortes)
                            .addComponent(txtExcedente)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(txtRestante))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmpleado))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTotalCortes, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtExcedente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(32, 182, 155));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMonto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMonto.setText("0.00");
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMontoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoKeyReleased(evt);
            }
        });

        lblMonto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(255, 255, 255));
        lblMonto.setText("Monto:");

        cmbTipoPago.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbTipoPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipoPagoKeyPressed(evt);
            }
        });

        lblTipoMoneda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTipoMoneda.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoMoneda.setText("Tipo de pago:");
        lblTipoMoneda.setMaximumSize(new java.awt.Dimension(109, 15));
        lblTipoMoneda.setMinimumSize(new java.awt.Dimension(109, 15));
        lblTipoMoneda.setPreferredSize(new java.awt.Dimension(100, 15));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMonto))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMonto)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        //actualizastes = true;
        cortarCaja();
        //actualizarInformacionCaja();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && Double.parseDouble(txtMonto.getText()) > 0) {
            XBigDecimal monto = new XBigDecimal(txtMonto.getText());
            PromptSupport.setPrompt("0.00", txtMonto);
            agregarMontoAlCorte(cmbTipoPago.getSelectedItem().toString(), monto);
            txtMonto.setText("0.00");
            txtMonto.requestFocus();
            txtMonto.setSelectionStart(0);
            txtMonto.setSelectionEnd(txtMonto.getText().length());
        }
    }//GEN-LAST:event_txtMontoKeyPressed

    private void cmbTipoPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoPagoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtMonto.requestFocus();
            txtMonto.setSelectionStart(0);
            txtMonto.setSelectionEnd(txtMonto.getText().length());
        }
    }//GEN-LAST:event_cmbTipoPagoKeyPressed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtRestanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRestanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRestanteActionPerformed

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            XBigDecimal totalVentas = new XBigDecimal(txtTotalVentas.getText());
            double acumuladocortes = 0;
            for (int i = 0; i < tblResultadoCorte.getRowCount(); i++) {
                acumuladocortes = acumuladocortes + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
            }
            XBigDecimal totalCortes = new XBigDecimal("" + acumuladocortes);
            txtTotalCortes.setText(totalCortes.setScale(2, RoundingMode.HALF_EVEN).toString());

            XBigDecimal excedente = new XBigDecimal(totalVentas.add(totalCortes.negate()).toString());
            if (excedente.toString().equals("0") || Double.parseDouble(excedente.toString()) < 0) {
                txtExcedente.setText(excedente.setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
                txtRestante.setText("0.00");
            } else {
                XBigDecimal Restante = new XBigDecimal(totalVentas.add(totalCortes.negate()).toString());
                txtExcedente.setText("0.00");
                txtRestante.setText(Restante.setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
            }
            if (Double.parseDouble(txtTotalCortes.getText()) <= 0) {
                btnAceptar.setEnabled(false);
            } else {
                btnAceptar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_txtMontoKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbTipoPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblNumeroCaja;
    private javax.swing.JLabel lblTipoMoneda;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalValor;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTable tblCortesAnteriores;
    private javax.swing.JTable tblResultadoCorte;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtExcedente;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNumeroCaja;
    private javax.swing.JTextField txtRestante;
    private javax.swing.JTextField txtTotalCortes;
    private javax.swing.JTextField txtTotalVentas;
    // End of variables declaration//GEN-END:variables

    private void agregarMontoAlCorte(String tipoMoneda, XBigDecimal monto) {
        boolean existe = false;
        String tipoMonedaEnTabla;
        XBigDecimal montoEnTabla;
        XBigDecimal montoTotal;
        TipoPagoTableModel model = (TipoPagoTableModel) tblResultadoCorte.getModel();
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

        montoTotal = new XBigDecimal(0);
        for (int i = 0; i < model.getRowCount(); i++) {
            montoEnTabla = new XBigDecimal(model.getValueAt(i, 1).toString());
            montoTotal = new XBigDecimal(montoTotal.add(montoEnTabla).toString());
        }
        lblTotalValor.setText(montoTotal.setScale(2, RoundingMode.HALF_EVEN).toString());
    }

    /**
     * --------------------- select * from stpv.venta v inner join
     * stpv.estado_caja ec on v.estado_caja_id = ec.id where v.corte_caja is
     * null ------------------------ Invoca el requestFocus en un punto de
     * interes para el usuario. cmbFormaPago -> tblTipoPago.
     */
    private void focusPuntoInteres() {
        if (!cmbTipoPago.hasFocus()) {
            cmbTipoPago.requestFocus();
            cmbTipoPago.showPopup();
        } else {
            tblResultadoCorte.requestFocus();
            tblResultadoCorte.addColumnSelectionInterval(0, 0);
        }
    }

    private void crearCorteCaja() {
        TipoPagoTableModel model = (TipoPagoTableModel) tblResultadoCorte.getModel();
        XBigDecimal montoCorte = new XBigDecimal(lblTotalValor.getText());
        XBigDecimal excedente = new XBigDecimal(txtExcedente.getText());
        XBigDecimal restante = new XBigDecimal(txtRestante.getText());
        int comparacion = montoCorte.compareTo(new XBigDecimal(0));
        if (comparacion < 0) {
            Utilidades.Sonidos.beep();
            focusPuntoInteres();
        } else {
            int idCorteCaja = caja.menuPrincipal.getOBD().crearCorteCaja(montoCorte.doubleValue(), excedente.doubleValue(), restante.doubleValue(), caja.menuPrincipal.getIdEstadoCaja(), caja.menuPrincipal.getEmpleado().getId());
            caja.menuPrincipal.getOBD().ActualizarCorteEnVenta(caja.menuPrincipal.getIdEstadoCaja());
            XBigDecimal montoEnTabla;
            for (int i = 0; i < model.getRowCount(); i++) {
                montoEnTabla = new XBigDecimal(model.getValueAt(i, 1).toString());
                String tipo = model.getValueAt(i, 0).toString();
                caja.menuPrincipal.getOBD().crearDesgloseCaja(idCorteCaja, tipo, montoEnTabla);
            }
        }
    }

    /**
     * Actualiza el lblTotalValor con el precio de todos los productos includos
     * en la venta.
     *
     * @param listaProductoEnVenta
     */
    public void actualizarLblTotal(final ArrayList<HashMap<String, String>> listaProductoEnVenta) {
        XBigDecimal montoTotal = new XBigDecimal(0);
        XBigDecimal pvp;

        for (HashMap<String, String> producto : listaProductoEnVenta) {
            pvp = new XBigDecimal(producto.get("pvp"));
            montoTotal = new XBigDecimal(montoTotal.add(pvp).toString());
        }
    }

    /**
     * Actualiza la informacion de los txt y las tablas en la ventana de corte
     *
     * caja.
     */
    private void actualizarInformacionCaja() {
        XBigDecimal totalVentas = caja.menuPrincipal.getOBD().getTotalEstadoCaja(caja.menuPrincipal.getIdEstadoCaja());
        txtTotalVentas.setText(totalVentas.setScale(2, RoundingMode.HALF_EVEN).toString());
        
        ArrayList<HashMap<String, String>> cortesAnteriores = caja.menuPrincipal.getOBD().getArrayListCortesCaja(caja.menuPrincipal.getIdEstadoCaja());
        CorteEstadoCajaTableModel modelCortesCaja = new CorteEstadoCajaTableModel(cortesAnteriores);
        tblCortesAnteriores.setModel(modelCortesCaja);
        
        TipoPagoTableModel model = new TipoPagoTableModel();
        tblResultadoCorte.setModel(model);
        cerrarVentana();
    }

    private void cortarCaja() {
        List<Reporte2> lista = new ArrayList();
        List<Integer> listaid = new ArrayList();
        List<ValorPagos> listavalor = new ArrayList();
        double gtotal = 0;
        double efectivo = 0;
        double debito = 0;
        double credito = 0;
        double ctk = 0;
        Empresa e = menuPrincipal.getOBD().datosEmpresas();
        XBigDecimal totalVentas = caja.menuPrincipal.getOBD().getTotalEstadoCaja(caja.menuPrincipal.getIdEstadoCaja());
        listaid.addAll(menuPrincipal.getOBD().getListIDVentas(menuPrincipal.getIdEstadoCaja()));
        txtTotalVentas.setText(totalVentas.setScale(2, RoundingMode.HALF_EVEN).toString());
        
        ArrayList<HashMap<String, String>> cortesAnteriores = caja.menuPrincipal.getOBD().getArrayListCortesCaja(caja.menuPrincipal.getIdEstadoCaja());
        CorteEstadoCajaTableModel modelCortesCaja = new CorteEstadoCajaTableModel(cortesAnteriores);
        tblCortesAnteriores.setModel(modelCortesCaja);
        
        crearCorteCaja();
        actualizastes = false;
        for (int i = 0; i < tblResultadoCorte.getRowCount(); i++) {
            if (tblResultadoCorte.getValueAt(i, 0).equals("Efectivo")) {
                efectivo = efectivo + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
            }
            if (tblResultadoCorte.getValueAt(i, 0).equals("Debito")) {
                debito = debito + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
            }
            if (tblResultadoCorte.getValueAt(i, 0).equals("Credito")) {
                credito = credito + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
            }
            if (tblResultadoCorte.getValueAt(i, 0).equals("Cesta Ticket")) {
                ctk = ctk + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
            }
        }
        listavalor.addAll(menuPrincipal.getOBD().montoscorte(listaid));
        
        for (ValorPagos valor : listavalor) {
            gtotal = gtotal + valor.getMontoD();
        }
        
        for (ValorPagos valor : listavalor) {
            Reporte2 pr = new Reporte2(e, menuPrincipal.getEmpleado().getNombre() + " " + menuPrincipal.getEmpleado().getApellido(), menuPrincipal.getEmpleado().getCedula(), menuPrincipal.getModeloCaja().getDescripcion(), valor.getTipo(), valor.getMonto(), "" + gtotal, "" + efectivo, "" + debito, "" + credito, "" + ctk);
            lista.add(pr);
        }
        
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/PuntoVenta/corte.jasper");
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JasperViewer visor = new JasperViewer(jprint, false);
            visor.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TipoPagoTableModel model = new TipoPagoTableModel();
        tblResultadoCorte.setModel(model);
        cerrarVentana();
    }
}
