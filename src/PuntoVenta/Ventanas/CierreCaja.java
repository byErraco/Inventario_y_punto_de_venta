/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;

import ClasesExtendidas.Numeros.XBigDecimal;
import PuntoVenta.Inicio.MenuPrincipal;
import PuntoVenta.Reporte3;
import Utilidades.CorteUtilidades;
import Utilidades.GuardarReporte;
import Utilidades.ValorPagos;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

/**
 *
 * @author inverdata
 */
public class CierreCaja extends javax.swing.JInternalFrame {

    private Caja caja;
    public MenuPrincipal menuPrincipal;
    /**
     *
     * Creates new form CierreCaja
     *
     * @param caja
     */
    public CierreCaja(Caja caja) {
        initComponents();
        this.caja = caja;
        this.menuPrincipal = caja.menuPrincipal;
        this.setTitle("Saphiro - Cierre de caja");
        if (menuPrincipal.getOBD().verificarCorteEnPagos()) {
            JOptionPane.showMessageDialog(null, "No se puede realizar el cierre debido a que presenta Cortes pendientes");
            caja.abrirVentanaCorteCaja();
            menuPrincipal.cierre.dispose();
        } else {
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

            actualizarInformacionCaja();
        }
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
                if(btnFinalizar.isEnabled() == true) {
                    try {
                        confirmacion();
                    } catch (IOException ex) {
                        Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
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
    
    private void focusPuntoInteres() {
        if (!txtTotalEfectivoF.hasFocus()) {
            txtTotalEfectivoF.requestFocus();
            CorteUtilidades cu = new CorteUtilidades();
            cu.selectAntesDelPunto(txtTotalEfectivoF);
        } /*else {
            tblResultadoCorte.requestFocus();
            tblResultadoCorte.addColumnSelectionInterval(0, 0);
        }*/
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
        jPanel4 = new javax.swing.JPanel();
        lblTotalVentas = new javax.swing.JLabel();
        txtTotalVentasS = new javax.swing.JTextField();
        lblTotalCortes = new javax.swing.JLabel();
        txtTotalCortesS = new javax.swing.JTextField();
        lblExcedente = new javax.swing.JLabel();
        txtExcedenteS = new javax.swing.JTextField();
        lblRestante = new javax.swing.JLabel();
        txtRestanteS = new javax.swing.JTextField();
        lblTotalEfectivo = new javax.swing.JLabel();
        txtTotalEfectivoS = new javax.swing.JTextField();
        lblTotalDebito = new javax.swing.JLabel();
        txtTotalDebitoS = new javax.swing.JTextField();
        lblTotalCredito = new javax.swing.JLabel();
        txtTotalCreditoS = new javax.swing.JTextField();
        lblTotalCTK = new javax.swing.JLabel();
        txtTotalCestaTicketS = new javax.swing.JTextField();
        lblTotalVentas3 = new javax.swing.JLabel();
        info1 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblEmpleado = new javax.swing.JLabel();
        txtNumeroCaja = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtEmpleado = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        lblTotalEfectivo1 = new javax.swing.JLabel();
        txtTotalEfectivoF = new javax.swing.JTextField();
        lblTotalDebito1 = new javax.swing.JLabel();
        txtTotalDebitoF = new javax.swing.JTextField();
        lblTotalCredito1 = new javax.swing.JLabel();
        txtTotalCreditoF = new javax.swing.JTextField();
        lblTotalCTK1 = new javax.swing.JLabel();
        txtTotalCestaTicketF = new javax.swing.JTextField();
        lblTotalVentas2 = new javax.swing.JLabel();
        lblTotalGlobal = new javax.swing.JLabel();
        txtTotalGlobal = new javax.swing.JTextField();
        info3 = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lblTotalVentas1 = new javax.swing.JLabel();
        txtTotalVentasC = new javax.swing.JTextField();
        lblTotalCortes1 = new javax.swing.JLabel();
        txtTotalCortesC = new javax.swing.JTextField();
        lblExcedente1 = new javax.swing.JLabel();
        txtExcedenteC = new javax.swing.JTextField();
        lblRestante1 = new javax.swing.JLabel();
        txtRestanteC = new javax.swing.JTextField();
        lblTotalEfectivo2 = new javax.swing.JLabel();
        txtTotalEfectivoC = new javax.swing.JTextField();
        lblTotalDebito2 = new javax.swing.JLabel();
        txtTotalDebitoC = new javax.swing.JTextField();
        lblTotalCredito2 = new javax.swing.JLabel();
        txtTotalCreditoC = new javax.swing.JTextField();
        lblTotalCTK2 = new javax.swing.JLabel();
        txtTotalCestaTicketC = new javax.swing.JTextField();
        lblTotalVentas4 = new javax.swing.JLabel();
        info2 = new javax.swing.JLabel();
        lblNumeroCaja = new javax.swing.JLabel();

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
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalVentas.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalVentas.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalVentas.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalVentas.setText("Total Ventas:");

        txtTotalVentasS.setEditable(false);
        txtTotalVentasS.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalVentasS.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalVentasS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalCortes.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalCortes.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalCortes.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalCortes.setText("Total Cortes:");

        txtTotalCortesS.setEditable(false);
        txtTotalCortesS.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCortesS.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalCortesS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblExcedente.setBackground(new java.awt.Color(255, 255, 255));
        lblExcedente.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblExcedente.setForeground(new java.awt.Color(28, 90, 125));
        lblExcedente.setText("Excedente:");

        txtExcedenteS.setEditable(false);
        txtExcedenteS.setBackground(new java.awt.Color(255, 255, 255));
        txtExcedenteS.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtExcedenteS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblRestante.setBackground(new java.awt.Color(255, 255, 255));
        lblRestante.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblRestante.setForeground(new java.awt.Color(28, 90, 125));
        lblRestante.setText("Restante:");

        txtRestanteS.setEditable(false);
        txtRestanteS.setBackground(new java.awt.Color(255, 255, 255));
        txtRestanteS.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtRestanteS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtRestanteS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRestanteSActionPerformed(evt);
            }
        });

        lblTotalEfectivo.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalEfectivo.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalEfectivo.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalEfectivo.setText("Total Efectivo:");

        txtTotalEfectivoS.setEditable(false);
        txtTotalEfectivoS.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalEfectivoS.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalEfectivoS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalDebito.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalDebito.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalDebito.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalDebito.setText("Total Debito:");

        txtTotalDebitoS.setEditable(false);
        txtTotalDebitoS.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalDebitoS.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalDebitoS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalCredito.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalCredito.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalCredito.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalCredito.setText("Total Credito:");

        txtTotalCreditoS.setEditable(false);
        txtTotalCreditoS.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCreditoS.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalCreditoS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalCTK.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalCTK.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalCTK.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalCTK.setText("Total CestaTicket:");

        txtTotalCestaTicketS.setEditable(false);
        txtTotalCestaTicketS.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCestaTicketS.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalCestaTicketS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalVentas3.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalVentas3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTotalVentas3.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalVentas3.setText("Totales en Sistema");

        info1.setBackground(new java.awt.Color(255, 255, 255));
        info1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        info1.setForeground(new java.awt.Color(20, 90, 125));
        info1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/cuadro2-400x400.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTotalCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTotalDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTotalEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTotalVentas))
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTotalCortes)
                                    .addComponent(lblTotalCTK)
                                    .addComponent(lblExcedente, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtExcedenteS)
                                .addComponent(txtTotalCortesS, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtTotalCestaTicketS, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtRestanteS, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                            .addComponent(txtTotalVentasS, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalEfectivoS, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalDebitoS, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalCreditoS, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(lblTotalVentas3)))
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(info1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(lblTotalVentas3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalVentas)
                    .addComponent(txtTotalVentasS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalEfectivo)
                    .addComponent(txtTotalEfectivoS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalDebito)
                    .addComponent(txtTotalDebitoS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCredito)
                    .addComponent(txtTotalCreditoS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCTK)
                    .addComponent(txtTotalCestaTicketS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCortes)
                    .addComponent(txtTotalCortesS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExcedente)
                    .addComponent(txtExcedenteS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRestante)
                    .addComponent(txtRestanteS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(info1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        lblFecha.setBackground(new java.awt.Color(255, 255, 255));
        lblFecha.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(28, 90, 125));
        lblFecha.setText("Fecha:");
        lblFecha.setMaximumSize(new java.awt.Dimension(74, 15));
        lblFecha.setMinimumSize(new java.awt.Dimension(74, 15));
        lblFecha.setPreferredSize(new java.awt.Dimension(74, 15));

        lblEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        lblEmpleado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblEmpleado.setForeground(new java.awt.Color(28, 90, 125));
        lblEmpleado.setText("Empleado:");

        txtNumeroCaja.setEditable(false);
        txtNumeroCaja.setBackground(new java.awt.Color(255, 255, 255));
        txtNumeroCaja.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtNumeroCaja.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        txtFecha.setEditable(false);
        txtFecha.setBackground(new java.awt.Color(255, 255, 255));
        txtFecha.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtFecha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        txtEmpleado.setEditable(false);
        txtEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtEmpleado.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtEmpleado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalEfectivo1.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalEfectivo1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalEfectivo1.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalEfectivo1.setText("Total Efectivo:");

        txtTotalEfectivoF.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalEfectivoF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtTotalEfectivoF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalEfectivoFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalEfectivoFKeyTyped(evt);
            }
        });

        lblTotalDebito1.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalDebito1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalDebito1.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalDebito1.setText("Total Debito:");

        txtTotalDebitoF.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalDebitoF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtTotalDebitoF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalDebitoFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalDebitoFKeyTyped(evt);
            }
        });

        lblTotalCredito1.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalCredito1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalCredito1.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalCredito1.setText("Total Credito:");

        txtTotalCreditoF.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalCreditoF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtTotalCreditoF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalCreditoFKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalCreditoFKeyReleased(evt);
            }
        });

        lblTotalCTK1.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalCTK1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalCTK1.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalCTK1.setText("Total CestaTicket:");

        txtTotalCestaTicketF.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalCestaTicketF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtTotalCestaTicketF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTotalCestaTicketFKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalCestaTicketFKeyReleased(evt);
            }
        });

        lblTotalVentas2.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalVentas2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTotalVentas2.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalVentas2.setText("Total Fisico");

        lblTotalGlobal.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalGlobal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalGlobal.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalGlobal.setText("Total en fisico:");

        txtTotalGlobal.setEditable(false);
        txtTotalGlobal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalGlobal.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalGlobal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtTotalGlobal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalGlobalActionPerformed(evt);
            }
        });

        info3.setBackground(new java.awt.Color(255, 255, 255));
        info3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        info3.setForeground(new java.awt.Color(20, 90, 125));
        info3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/cuadro2-400x400.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblTotalCredito1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalCTK1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalGlobal)
                            .addComponent(lblTotalEfectivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalDebito1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotalCestaTicketF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalEfectivoF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalDebitoF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalCreditoF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(lblTotalVentas2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(info3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(lblTotalVentas2)
                .addGap(62, 62, 62)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalEfectivoF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalEfectivo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTotalDebitoF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalDebito1))
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCredito1)
                    .addComponent(txtTotalCreditoF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCTK1)
                    .addComponent(txtTotalCestaTicketF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTotalGlobal)
                    .addComponent(txtTotalGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(165, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(info3, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(59, Short.MAX_VALUE)))
        );

        btnFinalizar.setBackground(new java.awt.Color(32, 182, 155));
        btnFinalizar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizar.setText("<html><center>Finalizar Cierre<br>F2</center></html>");
        btnFinalizar.setBorder(null);
        btnFinalizar.setEnabled(false);
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(32, 182, 155));
        btnSalir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("<html><center>Salir<br>Alt + Q</center></html>");
        btnSalir.setBorder(null);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblTotalVentas1.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalVentas1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalVentas1.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalVentas1.setText("Total Ventas:");

        txtTotalVentasC.setEditable(false);
        txtTotalVentasC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalVentasC.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalVentasC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalCortes1.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalCortes1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalCortes1.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalCortes1.setText("Total Cortes:");

        txtTotalCortesC.setEditable(false);
        txtTotalCortesC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCortesC.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalCortesC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblExcedente1.setBackground(new java.awt.Color(255, 255, 255));
        lblExcedente1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblExcedente1.setForeground(new java.awt.Color(28, 90, 125));
        lblExcedente1.setText("Excedente:");

        txtExcedenteC.setEditable(false);
        txtExcedenteC.setBackground(new java.awt.Color(255, 255, 255));
        txtExcedenteC.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtExcedenteC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblRestante1.setBackground(new java.awt.Color(255, 255, 255));
        lblRestante1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblRestante1.setForeground(new java.awt.Color(28, 90, 125));
        lblRestante1.setText("Restante:");

        txtRestanteC.setEditable(false);
        txtRestanteC.setBackground(new java.awt.Color(255, 255, 255));
        txtRestanteC.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtRestanteC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
        txtRestanteC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRestanteCActionPerformed(evt);
            }
        });

        lblTotalEfectivo2.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalEfectivo2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalEfectivo2.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalEfectivo2.setText("Total Efectivo:");

        txtTotalEfectivoC.setEditable(false);
        txtTotalEfectivoC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalEfectivoC.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalEfectivoC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalDebito2.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalDebito2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalDebito2.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalDebito2.setText("Total Debito:");

        txtTotalDebitoC.setEditable(false);
        txtTotalDebitoC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalDebitoC.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalDebitoC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalCredito2.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalCredito2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalCredito2.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalCredito2.setText("Total Credito:");

        txtTotalCreditoC.setEditable(false);
        txtTotalCreditoC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCreditoC.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalCreditoC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalCTK2.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalCTK2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTotalCTK2.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalCTK2.setText("Total CestaTicket:");

        txtTotalCestaTicketC.setEditable(false);
        txtTotalCestaTicketC.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalCestaTicketC.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtTotalCestaTicketC.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        lblTotalVentas4.setBackground(new java.awt.Color(255, 255, 255));
        lblTotalVentas4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTotalVentas4.setForeground(new java.awt.Color(28, 90, 125));
        lblTotalVentas4.setText("Totales ingresados por cortes");

        info2.setBackground(new java.awt.Color(255, 255, 255));
        info2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        info2.setForeground(new java.awt.Color(20, 90, 125));
        info2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/cuadro2-400x400.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lblTotalCredito2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblTotalEfectivo2))
                                    .addComponent(lblTotalDebito2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(lblTotalVentas1)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(42, 42, 42)))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTotalEfectivoC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(txtTotalDebitoC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTotalCreditoC, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTotalVentasC)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblRestante1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(txtRestanteC))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblExcedente1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(txtExcedenteC))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblTotalCortes1)
                        .addGap(56, 56, 56)
                        .addComponent(txtTotalCortesC))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblTotalCTK2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalCestaTicketC)))
                .addGap(88, 88, 88))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(lblTotalVentas4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(info2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 47, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(lblTotalVentas4)
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalVentas1)
                    .addComponent(txtTotalVentasC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalEfectivo2)
                    .addComponent(txtTotalEfectivoC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTotalDebito2)
                    .addComponent(txtTotalDebitoC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCredito2)
                    .addComponent(txtTotalCreditoC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCTK2)
                    .addComponent(txtTotalCestaTicketC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalCortes1)
                    .addComponent(txtTotalCortesC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtExcedenteC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExcedente1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRestanteC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRestante1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(info2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        lblNumeroCaja.setBackground(new java.awt.Color(255, 255, 255));
        lblNumeroCaja.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblNumeroCaja.setForeground(new java.awt.Color(28, 90, 125));
        lblNumeroCaja.setText("N° Caja:");
        lblNumeroCaja.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblNumeroCaja.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblNumeroCaja.setMaximumSize(new java.awt.Dimension(74, 15));
        lblNumeroCaja.setMinimumSize(new java.awt.Dimension(74, 15));
        lblNumeroCaja.setPreferredSize(new java.awt.Dimension(74, 15));

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(lblNumeroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumeroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlContenedorLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                                        .addComponent(lblEmpleado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmpleado)
                    .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRestanteSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRestanteSActionPerformed

    }//GEN-LAST:event_txtRestanteSActionPerformed

    private void txtTotalGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalGlobalActionPerformed

    }//GEN-LAST:event_txtTotalGlobalActionPerformed

    private void txtTotalEfectivoFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalEfectivoFKeyTyped
//        if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_PERIOD)) {
//            evt.consume();
//        }
//        if (evt.getKeyChar() == KeyEvent.VK_PERIOD && txtTotalEfectivoF.getText().contains(".")) {
//            evt.consume();
//        }
        keyTypedEvent(evt, txtTotalEfectivoF);
    }//GEN-LAST:event_txtTotalEfectivoFKeyTyped

    private void txtTotalDebitoFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDebitoFKeyTyped
        keyTypedEvent(evt, txtTotalDebitoF);
    }//GEN-LAST:event_txtTotalDebitoFKeyTyped

    private void txtTotalCreditoFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalCreditoFKeyTyped
        keyTypedEvent(evt, txtTotalCreditoF);
    }//GEN-LAST:event_txtTotalCreditoFKeyTyped

    private void txtTotalCestaTicketFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalCestaTicketFKeyTyped
        keyTypedEvent(evt, txtTotalCestaTicketF);
    }//GEN-LAST:event_txtTotalCestaTicketFKeyTyped

    private void txtTotalEfectivoFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalEfectivoFKeyReleased
        totalfisico();
        keyPressedEvent(evt, txtTotalEfectivoF);
    }//GEN-LAST:event_txtTotalEfectivoFKeyReleased

    private void txtTotalDebitoFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalDebitoFKeyReleased
        totalfisico();
        keyPressedEvent(evt, txtTotalDebitoF);
    }//GEN-LAST:event_txtTotalDebitoFKeyReleased

    private void txtTotalCreditoFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalCreditoFKeyReleased
        totalfisico();
        keyPressedEvent(evt, txtTotalCreditoF);
    }//GEN-LAST:event_txtTotalCreditoFKeyReleased

    private void txtTotalCestaTicketFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalCestaTicketFKeyReleased
        totalfisico();
        keyPressedEvent(evt, txtTotalCestaTicketF);
    }//GEN-LAST:event_txtTotalCestaTicketFKeyReleased

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        try {
            confirmacion();
        } catch (IOException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CierreCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void cerrarCaja() throws IOException, SQLException {
        GuardarReporte gr = new GuardarReporte();
                             // montos sistema
        String[][] montos = {{txtTotalEfectivoS.getText(), txtTotalDebitoS.getText(), txtTotalCreditoS.getText(), txtTotalCestaTicketS.getText()},
                             // montos cortes
                             {txtTotalEfectivoC.getText(), txtTotalDebitoC.getText(), txtTotalCreditoC.getText(), txtTotalCestaTicketC.getText()},
                             // montos fisico
                             {txtTotalEfectivoF.getText(), txtTotalDebitoF.getText(), txtTotalCreditoF.getText(), txtTotalCestaTicketF.getText()}};
        
        // guarda en las tablas de la base de datos
        menuPrincipal.getOBD().ActualizarCierreEnEstadoCaja(menuPrincipal.getIdEstadoCaja());
        int idCierreCaja = menuPrincipal.getOBD().crearCierre(txtTotalGlobal.getText(), txtTotalVentasS.getText(), txtTotalVentasC.getText(), 
                menuPrincipal.getIdEstadoCaja(), txtFecha.getText());
        menuPrincipal.getOBD().guardarMontosCierreCaja(idCierreCaja, montos);
        
        String saldo = menuPrincipal.getOBD().montoInicial(menuPrincipal.getIdEstadoCaja());
        Reporte3 rp = new Reporte3(menuPrincipal.getOBD().getDatosParametros(), menuPrincipal.getOBD().getDatosPais(" WHERE activo = TRUE"),
                menuPrincipal.getEmpleado().getNombre() + " " + menuPrincipal.getEmpleado().getApellido(), menuPrincipal.getEmpleado().getCedula(), 
                menuPrincipal.getModeloCaja().getDescripcion(), txtTotalVentasS.getText(), txtTotalGlobal.getText(), txtTotalEfectivoS.getText(), 
                txtTotalDebitoS.getText(), txtTotalCreditoS.getText(), txtTotalCestaTicketS.getText(), txtTotalEfectivoF.getText(), txtTotalDebitoF.getText(), 
                txtTotalCreditoF.getText(), txtTotalCestaTicketF.getText(), saldo);
        List<Reporte3> l3 = new ArrayList();
        l3.add(rp);
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject("src/PuntoVenta/cierre.jasper");
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(l3));
            JasperViewer visor = new JasperViewer(jprint, false);
            gr.GuardarPDF(jprint, "reporteCierre.PDF"); // Guarda el reporte en formato pdf
            visor.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gr.GuardarBaseDatos(idCierreCaja, "reporteCierre.PDF");
//        caja.menuPrincipal.getOBD().setEstadoCaja(
//                caja.menuPrincipal.getIdEstadoCaja(),
//                caja.menuPrincipal.getModeloCaja().getId(),
//                caja.menuPrincipal.getEmpleado().getId(),
//                txtTotalGlobal.getText());

        caja.setBotonesCajaEnabled(false);
        caja.menuPrincipal.setIdEstadoCaja(-1);
        caja.menuPrincipal.setEstadoCaja(false);
        caja.menuPrincipal.setBotonesMenuPrincipalEnabled(false);
        caja.actualizarTabla();
        cerrarVentana();
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtRestanteCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRestanteCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRestanteCActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if(menuPrincipal.estacerrado(caja)) {
            menuPrincipal.abrirVentanaCaja();
        }
    }//GEN-LAST:event_formInternalFrameClosed

    public void totalfisico() {
        double total = 0;
        if (txtTotalEfectivoF.getText() != null && !txtTotalEfectivoF.getText().isEmpty()) {
            total = total + Double.parseDouble(txtTotalEfectivoF.getText());
        }
        if (txtTotalDebitoF.getText() != null && !txtTotalDebitoF.getText().isEmpty()) {
            total = total + Double.parseDouble(txtTotalDebitoF.getText());
        }
        if (txtTotalCreditoF.getText() != null && !txtTotalCreditoF.getText().isEmpty()) {
            total = total + Double.parseDouble(txtTotalCreditoF.getText());
        }
        if (txtTotalCestaTicketF.getText() != null && !txtTotalCestaTicketF.getText().isEmpty()) {
            total = total + Double.parseDouble(txtTotalCestaTicketF.getText());
        }
        XBigDecimal totaltotal = new XBigDecimal("" + total);
        txtTotalGlobal.setText(totaltotal.setScale(2, RoundingMode.HALF_EVEN).toString());
        if (!"0.00".equals(txtTotalGlobal.getText())) {
            btnFinalizar.setEnabled(true);
        } else {
            btnFinalizar.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel info1;
    private javax.swing.JLabel info2;
    private javax.swing.JLabel info3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblExcedente;
    private javax.swing.JLabel lblExcedente1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNumeroCaja;
    private javax.swing.JLabel lblRestante;
    private javax.swing.JLabel lblRestante1;
    private javax.swing.JLabel lblTotalCTK;
    private javax.swing.JLabel lblTotalCTK1;
    private javax.swing.JLabel lblTotalCTK2;
    private javax.swing.JLabel lblTotalCortes;
    private javax.swing.JLabel lblTotalCortes1;
    private javax.swing.JLabel lblTotalCredito;
    private javax.swing.JLabel lblTotalCredito1;
    private javax.swing.JLabel lblTotalCredito2;
    private javax.swing.JLabel lblTotalDebito;
    private javax.swing.JLabel lblTotalDebito1;
    private javax.swing.JLabel lblTotalDebito2;
    private javax.swing.JLabel lblTotalEfectivo;
    private javax.swing.JLabel lblTotalEfectivo1;
    private javax.swing.JLabel lblTotalEfectivo2;
    private javax.swing.JLabel lblTotalGlobal;
    private javax.swing.JLabel lblTotalVentas;
    private javax.swing.JLabel lblTotalVentas1;
    private javax.swing.JLabel lblTotalVentas2;
    private javax.swing.JLabel lblTotalVentas3;
    private javax.swing.JLabel lblTotalVentas4;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtExcedenteC;
    private javax.swing.JTextField txtExcedenteS;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNumeroCaja;
    private javax.swing.JTextField txtRestanteC;
    private javax.swing.JTextField txtRestanteS;
    private javax.swing.JTextField txtTotalCestaTicketC;
    private javax.swing.JTextField txtTotalCestaTicketF;
    private javax.swing.JTextField txtTotalCestaTicketS;
    private javax.swing.JTextField txtTotalCortesC;
    private javax.swing.JTextField txtTotalCortesS;
    private javax.swing.JTextField txtTotalCreditoC;
    private javax.swing.JTextField txtTotalCreditoF;
    private javax.swing.JTextField txtTotalCreditoS;
    private javax.swing.JTextField txtTotalDebitoC;
    private javax.swing.JTextField txtTotalDebitoF;
    private javax.swing.JTextField txtTotalDebitoS;
    private javax.swing.JTextField txtTotalEfectivoC;
    private javax.swing.JTextField txtTotalEfectivoF;
    private javax.swing.JTextField txtTotalEfectivoS;
    private javax.swing.JTextField txtTotalGlobal;
    private javax.swing.JTextField txtTotalVentasC;
    private javax.swing.JTextField txtTotalVentasS;
    // End of variables declaration//GEN-END:variables

    public void keyTypedEvent(KeyEvent evt, JTextField campo) {
        
        if (campo.getText().length() > 20) {
            evt.consume();
        }
        
        CorteUtilidades cu = new CorteUtilidades();
        // no permite introducir letras
        if (evt.getKeyChar() == '.') {
            if(campo.getText().lastIndexOf(".") >= 0)  {
                evt.consume();
                cu.selectDespuesDelPunto(campo);
            }
        } else if(!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
        
        
    }
    
    public void keyPressedEvent(KeyEvent evt, JTextField campo) {
        CorteUtilidades cu = new CorteUtilidades();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(campo.equals(txtTotalEfectivoF)) {
                cu.selectAntesDelPunto(txtTotalDebitoF);
            } else if(campo.equals(txtTotalDebitoF)) {
                cu.selectAntesDelPunto(txtTotalCreditoF);
            } else if(campo.equals(txtTotalCreditoF)) {
                cu.selectAntesDelPunto(txtTotalCestaTicketF);
            } else if(campo.equals(txtTotalCestaTicketF)) {
                cu.selectAntesDelPunto(txtTotalEfectivoF);
            }
        }
    }
    
    
    private void actualizarInformacionCaja() {
//        List<ValorPagos> listpagos = menuPrincipal.getOBD().getTotalPagoCierre(caja.menuPrincipal.getIdEstadoCaja());
//        ValorPagos valorcorte = menuPrincipal.getOBD().getTotalCortesCierre(caja.menuPrincipal.getIdEstadoCaja());
//        XBigDecimal totalCorte = new XBigDecimal(0);
//        XBigDecimal totalEfectivo = new XBigDecimal(0);
//        XBigDecimal totalDebito = new XBigDecimal(0);
//        XBigDecimal totalCredito = new XBigDecimal(0);
//        XBigDecimal totalCestaticket = new XBigDecimal(0);
        CorteUtilidades cu = new CorteUtilidades();
        
        // Total en el sistema
        XBigDecimal[] totalVentasSistema = caja.menuPrincipal.getOBD().getTotalEstadoCaja(caja.menuPrincipal.getIdEstadoCaja(), ";");
        
        txtTotalEfectivoS.setText(totalVentasSistema[0].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
        txtTotalCreditoS.setText(totalVentasSistema[1].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));       
        txtTotalDebitoS.setText(totalVentasSistema[2].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
        txtTotalCestaTicketS.setText(totalVentasSistema[3].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
//        txtTotalCortes.setText(totalVentas[3].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
        txtTotalVentasS.setText(totalVentasSistema[4].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
        
        // Total de la suma de los cortes de caja
        XBigDecimal[] totalVentasCortes = caja.menuPrincipal.getOBD().getTotalCortesCierre(caja.menuPrincipal.getIdEstadoCaja());
        XBigDecimal excedente = caja.menuPrincipal.getOBD().getExcedenteCortesCaja(caja.menuPrincipal.getIdEstadoCaja());
        XBigDecimal restante = caja.menuPrincipal.getOBD().getRestanteCortesCaja(caja.menuPrincipal.getIdEstadoCaja());
        
        txtTotalEfectivoC.setText(totalVentasCortes[0].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
        txtTotalCreditoC.setText(totalVentasCortes[2].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));       
        txtTotalDebitoC.setText(totalVentasCortes[1].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
        txtTotalCestaTicketC.setText(totalVentasCortes[3].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
        
        // Compara el excedente y el restante para aplicar suma y resta        
        if(excedente.compareTo(restante) > 0) {
            txtExcedenteC.setText(excedente.add(restante.negate()).setScale(2, RoundingMode.HALF_EVEN).toString());
            txtRestanteC.setText("0.00");
        } else if(excedente.compareTo(restante) < 0) {
            txtRestanteC.setText(restante.add(excedente.negate()).setScale(2, RoundingMode.HALF_EVEN).toString());
            txtExcedenteC.setText("0.00");
        } else {
            txtExcedenteC.setText("0.00");
            txtRestanteC.setText("0.00");
        }
        
        txtTotalVentasC.setText(totalVentasCortes[4].setScale(2, RoundingMode.HALF_EVEN).toString().replaceAll("-", ""));
        
        
        
        
        double totalventas = 0;
        double totalcortes = 0;
        double resta = 0;
//        for (ValorPagos valor : listpagos) {
//            if (valor.getTipo().equals("Efectivo")) {
//                totalEfectivo = new XBigDecimal(valor.getMonto());
//            }
//            if (valor.getTipo().equals("Debito")) {
//                totalDebito = new XBigDecimal(valor.getMonto());
//            }
//            if (valor.getTipo().equals("Credito")) {
//                totalCredito = new XBigDecimal(valor.getMonto());
//            }
//            if (valor.getTipo().equals("Cestaticket")) {
//                totalCestaticket = new XBigDecimal(valor.getMonto());
//            }
//        }
//        totalCorte = new XBigDecimal(valorcorte.getMonto());
//        XBigDecimal totalVentas = caja.menuPrincipal.getOBD().getTotalEstadoCajaCierre(caja.menuPrincipal.getIdEstadoCaja());
//        totalventas = Double.parseDouble(totalVentas.toString());
//        totalcortes = Double.parseDouble(totalCorte.toString());
//        resta = totalcortes - totalventas;
//        if (resta > 0) {
//            txtRestante.setText("0.00");
//            txtExcedente.setText("" + resta);
//        }
//        if (resta == 0) {
//            txtExcedente.setText("0.00");
//            txtRestante.setText("0.00");
//        }
//        if (resta < 0) {
//            txtExcedente.setText("0.00");
//            txtRestante.setText("" + resta * (-1));
//        }
        txtTotalEfectivoF.setText("0.00");
        txtTotalDebitoF.setText("0.00");
        txtTotalCreditoF.setText("0.00");
        txtTotalCestaTicketF.setText("0.00");
        txtTotalGlobal.setText("0.00");
//        txtTotalVentas.setText(totalVentas.setScale(2, RoundingMode.HALF_EVEN).toString());
//        txtTotalEfectivo.setText(totalEfectivo.setScale(2, RoundingMode.HALF_EVEN).toString());
//        txtTotalDebito.setText(totalDebito.setScale(2, RoundingMode.HALF_EVEN).toString());
//        txtTotalCredito.setText(totalCredito.setScale(2, RoundingMode.HALF_EVEN).toString());
//        txtTotalCestaTicket.setText(totalCestaticket.setScale(2, RoundingMode.HALF_EVEN).toString());
//        txtTotalCortes.setText(totalCorte.setScale(2, RoundingMode.HALF_EVEN).toString());
    }
    
    public void confirmacion() throws IOException, SQLException {
        int g = JOptionPane.showConfirmDialog(this, "¿Desea realizar el cierre de caja?\nNo podrá realizar cambios", 
                "Cierre de caja", JOptionPane.YES_NO_OPTION);
        if (g == JOptionPane.YES_OPTION) {
            cerrarCaja();
        } else if (g == JOptionPane.NO_OPTION) {
            this.setVisible(true);
        }
    }
    
}
