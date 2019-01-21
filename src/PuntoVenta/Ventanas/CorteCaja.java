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
import PuntoVenta.BaseDatos.Pais;
import PuntoVenta.BaseDatos.Parametros;
import PuntoVenta.Inicio.MenuPrincipal;
import PuntoVenta.Reporte2;
import Utilidades.ValorPagos;
import Utilidades.CorteUtilidades;
import Utilidades.GuardarReporte;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
    boolean suprimir = false;
    boolean actualizastes = false;
    int idEstadoCaja;
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
//                confirmacion();
                if(btnAceptar.isEnabled())  try {
                    confirmacion();
                } catch (IOException ex) {
                    Logger.getLogger(CorteCaja.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CorteCaja.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        Action actSuprimir = new AbstractAction("actionSuprimir") {
            @Override
            public void actionPerformed(ActionEvent e) {
                suprimir = !suprimir;
                if(suprimir) {
                    JOptionPane.showMessageDialog(null, "Suprimir activado" , "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Suprimir desactivado" , "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };

        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        actAceptar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        actFocusPuntoInteres.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        actSuprimir.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));

        pnlContenedor.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

        pnlContenedor.getActionMap().put("actionFocusPuntoInteres", actFocusPuntoInteres);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFocusPuntoInteres.getValue(Action.ACTION_COMMAND_KEY), "actionFocusPuntoInteres");

        pnlContenedor.getActionMap().put("actionAceptar", actAceptar);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAceptar.getValue(Action.ACCELERATOR_KEY), "actionAceptar");

        pnlContenedor.getActionMap().put("actionSuprimir", actSuprimir);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actSuprimir.getValue(Action.ACTION_COMMAND_KEY), "actionSuprimir");
        
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
        menuPrincipal.abrirVentanaCaja();
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
        txtEfectivo = new javax.swing.JTextField();
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
        txtCredito = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDebito = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTicket = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotalVentas = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
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
        jLabel1.setText("Efectivo:");

        txtEfectivo.setEditable(false);
        txtEfectivo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtEfectivo.setMaximumSize(new java.awt.Dimension(141, 20));
        txtEfectivo.setMinimumSize(new java.awt.Dimension(141, 20));
        txtEfectivo.setName(""); // NOI18N
        txtEfectivo.setPreferredSize(new java.awt.Dimension(141, 20));

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

        txtCredito.setEditable(false);
        txtCredito.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Crédito:");

        txtDebito.setEditable(false);
        txtDebito.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Débito:");

        txtTicket.setEditable(false);
        txtTicket.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ticket:");

        txtTotalVentas.setEditable(false);
        txtTotalVentas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total ventas:");

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
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotalCortes)
                            .addComponent(txtExcedente)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRestante))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel1)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCredito)
                            .addComponent(txtEfectivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTicket))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDebito))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalVentas)))
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTotalCortes, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtExcedente, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(32, 182, 155));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMonto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtMonto.setText("0.00");
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
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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
        if(btnAceptar.isEnabled())  try {
            confirmacion();
        } catch (IOException ex) {
            Logger.getLogger(CorteCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CorteCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        //actualizarInformacionCaja();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtMontoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && Double.parseDouble(txtMonto.getText()) > 0) {
            CorteUtilidades cu = new CorteUtilidades();
            txtMonto.requestFocus();
            
            // resta y añade color a los campos de pagos            
            if(cmbTipoPago.getSelectedItem().equals("Efectivo")) {
                XBigDecimal efectivo = new XBigDecimal(txtEfectivo.getText());
                asignarValorTipoPago(efectivo, txtEfectivo, "Efectivo");
                cu.colorTexto(txtEfectivo);
            } else if(cmbTipoPago.getSelectedItem().equals("Débito")) {
                XBigDecimal debito = new XBigDecimal(txtDebito.getText());
                asignarValorTipoPago(debito, txtDebito, "Débito");
//                txtDebito.setText(debito.add(valorTxt).setScale(2, RoundingMode.HALF_EVEN).toString());
                cu.colorTexto(txtDebito);
            } else if(cmbTipoPago.getSelectedItem().equals("Crédito")) {
                XBigDecimal credito = new XBigDecimal(txtCredito.getText());
                asignarValorTipoPago(credito, txtCredito, "Crédito");
//                txtCredito.setText(credito.add(valorTxt).setScale(2, RoundingMode.HALF_EVEN).toString());
                cu.colorTexto(txtCredito);
            } else {
                XBigDecimal ticket = new XBigDecimal(txtTicket.getText());
                asignarValorTipoPago(ticket, txtTicket, "CestaTicket");
//                txtTicket.setText(ticket.add(valorTxt).setScale(2, RoundingMode.HALF_EVEN).toString());
                cu.colorTexto(txtTicket);
            }
            
            // obtiene el monto y lo agrega a tblResultadoCorte
            XBigDecimal monto = new XBigDecimal(txtMonto.getText());
            agregarMontoAlCorte(cmbTipoPago.getSelectedItem().toString(), monto);

            // Agrega al txtTotalCortes el monto acumulado por el corte
            XBigDecimal totalVentas = new XBigDecimal(txtTotalVentas.getText());
            double acumuladocortes = 0;
            for (int i = 0; i < tblResultadoCorte.getRowCount(); i++) {
                acumuladocortes = acumuladocortes + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
            }
            XBigDecimal totalCortes = new XBigDecimal("" + acumuladocortes);
            txtTotalCortes.setText(totalCortes.setScale(2, RoundingMode.HALF_EVEN).toString());
            
            // Controla los campos txtExcedente y txtRestante
            XBigDecimal excedente = new XBigDecimal(totalVentas.add(totalCortes.negate()).toString());
            if (excedente.toString().equals("0") || Double.parseDouble(excedente.toString()) < 0) {
                txtRestante.setText("0.00");
                txtExcedente.setText(excedente.setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
                // asignando colores
                cu.colorVerde(txtExcedente);
                cu.colorRojo(txtRestante);
            } else {
                XBigDecimal Restante = new XBigDecimal(totalVentas.add(totalCortes.negate()).toString());
                txtExcedente.setText("0.00");
                txtRestante.setText("-".concat(Restante.setScale(2, RoundingMode.HALF_EVEN).toString()));
                if(Double.parseDouble(txtRestante.getText()) == 0)   txtRestante.setText("0.00");
                // asignando colores
                cu.colorVerde(txtExcedente);
                cu.colorRojo(txtRestante);
            }
            
            // si el acumulado del corte es menor o igual a 0, no permite hacer el corte
            if (Double.parseDouble(txtTotalCortes.getText()) <= 0) {
                btnAceptar.setEnabled(false);
            } else {
                btnAceptar.setEnabled(true);
            }
            
            txtMonto.setText("0.00");
            cu.selectAntesDelPunto(txtMonto);
        }
    }//GEN-LAST:event_txtMontoKeyPressed

    private void cmbTipoPagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoPagoKeyPressed
        CorteUtilidades cu = new CorteUtilidades();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cu.selectAntesDelPunto(txtMonto);
        }
    }//GEN-LAST:event_cmbTipoPagoKeyPressed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            CorteUtilidades cu = new CorteUtilidades();
//            // asignando focus
//            XBigDecimal totalVentas = new XBigDecimal(txtTotalVentas.getText());
//            double acumuladocortes = 0;
//            for (int i = 0; i < tblResultadoCorte.getRowCount(); i++) {
//                acumuladocortes = acumuladocortes + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
//            }
//            XBigDecimal totalCortes = new XBigDecimal("" + acumuladocortes);
//            txtTotalCortes.setText(totalCortes.setScale(2, RoundingMode.HALF_EVEN).toString());
//
//            
//            XBigDecimal valorTxt = new XBigDecimal(txtMonto.getText());
//            txtMonto.setText("0.00");
//            cu.selectAntesDelPunto(txtMonto);
//
//            // asigna y resta el monto a los campos de pagos
//            if(cmbTipoPago.getSelectedItem().equals("Efectivo")) {
//                XBigDecimal efectivo = new XBigDecimal(txtEfectivo.getText());
//                txtEfectivo.setText(efectivo.add(valorTxt).setScale(2, RoundingMode.HALF_EVEN).toString());
//                cu.colorTexto(txtEfectivo);
//            } else if(cmbTipoPago.getSelectedItem().equals("Débito")) {
//                XBigDecimal debito = new XBigDecimal(txtDebito.getText());
//                txtDebito.setText(debito.add(valorTxt).setScale(2, RoundingMode.HALF_EVEN).toString());
//                cu.colorTexto(txtDebito);
//            } else if(cmbTipoPago.getSelectedItem().equals("Crédito")) {
//                XBigDecimal credito = new XBigDecimal(txtCredito.getText());
//                txtCredito.setText(credito.add(valorTxt).setScale(2, RoundingMode.HALF_EVEN).toString());
//                cu.colorTexto(txtCredito);
//            } else {
//                XBigDecimal ticket = new XBigDecimal(txtTicket.getText());
//                txtTicket.setText(ticket.add(valorTxt).setScale(2, RoundingMode.HALF_EVEN).toString());
//                cu.colorTexto(txtTicket);
//            }
//            
//
//            XBigDecimal excedente = new XBigDecimal(totalVentas.add(totalCortes.negate()).toString());
//            if (excedente.toString().equals("0") || Double.parseDouble(excedente.toString()) < 0) {
//                txtRestante.setText("0.00");
//                txtExcedente.setText(excedente.setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
//                cu.colorTexto(txtExcedente);
//            } else {
//                XBigDecimal Restante = new XBigDecimal(totalVentas.add(totalCortes).toString());
//                txtExcedente.setText("0.00");
//                txtRestante.setText(Restante.setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
//                cu.colorTexto(txtRestante);
//            }
//            if (Double.parseDouble(txtTotalCortes.getText()) <= 0) {
//                btnAceptar.setEnabled(false);
//            } else {
//                btnAceptar.setEnabled(true);
//            }
//        }
    }//GEN-LAST:event_txtMontoKeyReleased

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        CorteUtilidades cu = new CorteUtilidades();
        // no permite introducir letras
        if (evt.getKeyChar() == '.') {
            if(txtMonto.getText().lastIndexOf(".") >= 0)  {
                evt.consume();
                cu.selectDespuesDelPunto(txtMonto);
            }
        } else if(!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
        
    }//GEN-LAST:event_txtMontoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cmbTipoPago;
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
    private javax.swing.JTextField txtCredito;
    private javax.swing.JTextField txtDebito;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtExcedente;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtNumeroCaja;
    private javax.swing.JTextField txtRestante;
    private javax.swing.JTextField txtTicket;
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
                if(suprimir == false)  model.setValueAt(monto.add(montoEnTabla).setScale(2, RoundingMode.HALF_EVEN), i, 1);
                else {
                    BigDecimal montoNuevo = monto.negate().add(montoEnTabla).setScale(2, RoundingMode.HALF_EVEN);
                    model.setValueAt(montoNuevo, i, 1);
                    if(montoNuevo.compareTo(new BigDecimal(0)) <= 0)   model.removeRow(i);
                }
                
                existe = true;
            }
        }
        if (!existe) {
            if(suprimir)    JOptionPane.showMessageDialog(null, "No queda mas por restar de este tipo de pago", "Error", JOptionPane.INFORMATION_MESSAGE);
            else            model.addRow(new String[]{tipoMoneda, monto.setScale(2, RoundingMode.HALF_EVEN).toString()});
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

    private int crearCorteCaja() {
        TipoPagoTableModel model = (TipoPagoTableModel) tblResultadoCorte.getModel();
        XBigDecimal montoCorte = new XBigDecimal(lblTotalValor.getText());
        XBigDecimal excedente = new XBigDecimal(txtExcedente.getText());
        XBigDecimal restante = new XBigDecimal(txtRestante.getText().replace("-",""));
        int comparacion = montoCorte.compareTo(new XBigDecimal(0));
        int idCorteCaja = -1;
        if (comparacion < 0) {
            Utilidades.Sonidos.beep();
            focusPuntoInteres();
        } else {
            idCorteCaja = caja.menuPrincipal.getOBD().crearCorteCaja(montoCorte.doubleValue(), excedente.doubleValue(), restante.doubleValue(), caja.menuPrincipal.getIdEstadoCaja(), caja.menuPrincipal.getEmpleado().getId());
            XBigDecimal montoEnTabla;
            for (int i = 0; i < model.getRowCount(); i++) {
                montoEnTabla = new XBigDecimal(model.getValueAt(i, 1).toString());
                String tipo = model.getValueAt(i, 0).toString();
                caja.menuPrincipal.getOBD().crearDesgloseCaja(idCorteCaja, tipo, montoEnTabla);
            }
        }
        return idCorteCaja;
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
        CorteUtilidades cu = new CorteUtilidades();
        
        XBigDecimal[] totalVentas = caja.menuPrincipal.getOBD().getTotalEstadoCaja(caja.menuPrincipal.getIdEstadoCaja(),
                " AND corte_realizado = 0");
        //------ Asignando totales por tipo de pago ------
        txtEfectivo.setText(totalVentas[0].setScale(2, RoundingMode.HALF_EVEN).toString());
        cu.colorTexto(txtEfectivo);
   
        txtCredito.setText(totalVentas[1].setScale(2, RoundingMode.HALF_EVEN).toString());
        cu.colorTexto(txtCredito);
        
        txtDebito.setText(totalVentas[2].setScale(2, RoundingMode.HALF_EVEN).toString());
        cu.colorTexto(txtDebito);
        
        txtTicket.setText(totalVentas[3].setScale(2, RoundingMode.HALF_EVEN).toString());
        cu.colorTexto(txtTicket);
        
        txtTotalVentas.setText(totalVentas[4].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
        
        ArrayList<HashMap<String, String>> cortesAnteriores = caja.menuPrincipal.getOBD().getArrayListCortesCaja(caja.menuPrincipal.getIdEstadoCaja());
        CorteEstadoCajaTableModel modelCortesCaja = new CorteEstadoCajaTableModel(cortesAnteriores);
        tblCortesAnteriores.setModel(modelCortesCaja);
        
        TipoPagoTableModel model = new TipoPagoTableModel();
        tblResultadoCorte.setModel(model);
        
//        cerrarVentana();
    }

    private void cortarCaja() throws IOException, SQLException {
        List<Reporte2> lista = new ArrayList();
//        List<Integer> listaid = new ArrayList();
        List<ValorPagos> listavalor = new ArrayList();
        int idCorteCaja;
        double gtotalU = 0;
        double efectivo = 0;
        double debito = 0;
        double credito = 0;
        double ctk = 0;
        GuardarReporte gr = new GuardarReporte();
        Parametros para = menuPrincipal.getOBD().getDatosParametros();
        Pais p = menuPrincipal.getOBD().getDatosPais(" WHERE activo = true");
        XBigDecimal[] totalVentas = caja.menuPrincipal.getOBD().getTotalEstadoCaja(caja.menuPrincipal.getIdEstadoCaja(),
                " AND corte_realizado = 0");
//        listaid.addAll(menuPrincipal.getOBD().getListIDVentas(menuPrincipal.getIdEstadoCaja()));
        
        ArrayList<HashMap<String, String>> cortesAnteriores = caja.menuPrincipal.getOBD().getArrayListCortesCaja(caja.menuPrincipal.getIdEstadoCaja());
        CorteEstadoCajaTableModel modelCortesCaja = new CorteEstadoCajaTableModel(cortesAnteriores);
        tblCortesAnteriores.setModel(modelCortesCaja);
        
        idCorteCaja = crearCorteCaja();
        actualizastes = false;
        
//        for (int i = 0; i < tblResultadoCorte.getRowCount(); i++) {
//            if (tblResultadoCorte.getValueAt(i, 0).equals("Efectivo")) {
//                efectivo = efectivo + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
//            }
//            if (tblResultadoCorte.getValueAt(i, 0).equals("Debito")) {
//                debito = debito + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
//            }
//            if (tblResultadoCorte.getValueAt(i, 0).equals("Credito")) {
//                credito = credito + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
//            }
//            if (tblResultadoCorte.getValueAt(i, 0).equals("CestaTicket")) {
//                ctk = ctk + Double.parseDouble(tblResultadoCorte.getValueAt(i, 1).toString());
//            }
//        }
        // Anteriormente aquí se enviaba "listaid" que era un conjunto de los id de las ventas
        // ahora se envía el id del corte que se acaba de crear
        listavalor.addAll(menuPrincipal.getOBD().montoscorte(idCorteCaja));
        
        for (ValorPagos valor : listavalor) {
            gtotalU = gtotalU + valor.getMontoD();
        }
        
        // Asignando los montos que trae el sistema por defecto
        efectivo = Double.parseDouble(totalVentas[0].setScale(2, RoundingMode.HALF_EVEN).toString().replace("-", ""));
        credito = Double.parseDouble(totalVentas[1].setScale(2, RoundingMode.HALF_EVEN).toString().replace("-", ""));
        debito = Double.parseDouble(totalVentas[2].setScale(2, RoundingMode.HALF_EVEN).toString().replace("-", ""));
        ctk = Double.parseDouble(totalVentas[3].setScale(2, RoundingMode.HALF_EVEN).toString().replace("-", ""));
        String gtotalS = totalVentas[4].setScale(2, RoundingMode.HALF_EVEN).toString().replace("-", "");
        
        // Asignando los montos que ingresa el cajero en el reporte
        for (ValorPagos valor : listavalor) {
            
            String tipoPago = "";
            switch(valor.getTipo()) {
                case 1: tipoPago = "Efectivo"; break;
                case 2: tipoPago = "Débito"; break;
                case 3: tipoPago = "Crédito"; break;
                case 4: tipoPago = "CestaTicket"; break;
            }
            
            Reporte2 pr = new Reporte2(para, p, menuPrincipal.getEmpleado().getNombre() + " " + menuPrincipal.getEmpleado().getApellido(), 
                    menuPrincipal.getEmpleado().getCedula(), menuPrincipal.getModeloCaja().getDescripcion(), tipoPago, valor.getMonto(), 
                    gtotalS, "" + gtotalU, "" + efectivo, "" + debito, "" + credito, "" + ctk);
            lista.add(pr);
        }
        
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/PuntoVenta/corte.jasper");
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JasperViewer visor = new JasperViewer(jprint, false);
            gr.GuardarPDF(jprint, "reporteCorte.PDF"); // Guarda el reporte en formato pdf
            visor.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gr.GuardarBaseDatos(idCorteCaja, "reporteCorte.PDF");
        TipoPagoTableModel model = new TipoPagoTableModel();
        tblResultadoCorte.setModel(model);
        cerrarVentana();
    }
    
    public void asignarValorTipoPago(XBigDecimal cantidad, JTextField campo, String tipoPago) {
        TipoPagoTableModel model = (TipoPagoTableModel) tblResultadoCorte.getModel();
        XBigDecimal valorTxt = new XBigDecimal(txtMonto.getText());
        // verifica si ingresa o saca dinero del corte
        if(suprimir == false) campo.setText(cantidad.add(valorTxt).setScale(2, RoundingMode.HALF_EVEN).toString());
        else {
            for(int i = 0; i < model.getRowCount(); i++) {
                XBigDecimal tblMonto = new XBigDecimal(model.getValueAt(i, 1).toString());
                if(model.getValueAt(i, 0).toString().equals(tipoPago) && tblMonto.compareTo(valorTxt) > 0) {
                    campo.setText(cantidad.add(valorTxt.negate()).setScale(2, RoundingMode.HALF_EVEN).toString());
                } else if (model.getValueAt(i, 0).toString().equals(tipoPago) && tblMonto.compareTo(valorTxt) <= 0) {
                    campo.setText(cantidad.add(tblMonto.negate()).setScale(2, RoundingMode.HALF_EVEN).toString());
                }
            }
        }
    }
    
    public void confirmacion() throws IOException, SQLException {
        int g = JOptionPane.showConfirmDialog(this, "¿Desea realizar el corte de caja?\nNo podrá realizar cambios", 
                "Corte de caja", JOptionPane.YES_NO_OPTION);
        if (g == JOptionPane.YES_OPTION) {
            cortarCaja();
        } else if (g == JOptionPane.NO_OPTION) {
            this.setVisible(true);
        }
    }
}
