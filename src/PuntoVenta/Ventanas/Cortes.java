package PuntoVenta.Ventanas;

import PuntoVenta.Inicio.MenuPrincipal;
import ClasesExtendidas.Tablas.CorteEstadoCajaTableModel;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.TableRowSorter;

public class Cortes extends javax.swing.JInternalFrame {

    public MenuPrincipal menuPrincipal;
    private int idEstadoCaja;
    public String fecha;
    static DecimalFormat nf = new DecimalFormat("###,###,###,###.00");

    /**
     *
     * @param menuPrincipal
     */
    public Cortes(Caja caja, int idEstadoCaja) {
        initComponents();
        btnImprimir.setVisible(true);
        this.idEstadoCaja = idEstadoCaja;
        this.menuPrincipal = caja.menuPrincipal;
        this.setTitle("Saphiro - Gestión historial de cortes para la N°" + idEstadoCaja);

        actualizarTabla();

        crearHotKeys();
    }

    /**
     * Método para actualizar el contenido de la tabla en la ventana caja.
     */
    public void actualizarTabla() {
        int idCaja = menuPrincipal.getModeloCaja().getId();
        int idEstadoCaja = menuPrincipal.getOBD().getIdEstadoCaja(idCaja);
        
        ArrayList cortesCajaArrayList = menuPrincipal.getOBD().getArrayListCortesCaja(idEstadoCaja); 
        CorteEstadoCajaTableModel corteEstadoCajaTableModel = new CorteEstadoCajaTableModel(cortesCajaArrayList);
        jtbResultadoBusqueda.setModel(corteEstadoCajaTableModel);
        
//        setBotonesCajaEnabled(menuPrincipal.isCajaAbierta());

        jtbResultadoBusqueda.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    /**
     * Cierra la ventana con dispose().
     */
    private void cerrarVentana() {
        this.dispose();
    }

    private void crearHotKeys() {
        Action actImprimirCaja = new AbstractAction("actionImprimirCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    imprimirFacturaCaja();
                } catch (IOException ex) {
                    Logger.getLogger(Cortes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
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

        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        actFocusPuntoInteres.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        actImprimirCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));

        btnImprimir.getActionMap().put("actionImprimirCaja", actImprimirCaja);
        btnImprimir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actImprimirCaja.getValue(Action.ACCELERATOR_KEY), "actionImprimirCaja");

        pnlContenedor.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

        pnlContenedor.getActionMap().put("actionFocusPuntoInteres", actFocusPuntoInteres);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFocusPuntoInteres.getValue(Action.ACTION_COMMAND_KEY), "actionFocusPuntoInteres");

        InternalFrameAdapter listener = new InternalFrameAdapter() {
            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                menuPrincipal.getBtnCaja().requestFocus();
            }
        };
        this.addInternalFrameListener(listener);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbResultadoBusqueda = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);

        pnlContenedor.setBackground(new java.awt.Color(32, 182, 155));
        pnlContenedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(172, 162, 162), null, null));
        //jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ///jPanel1.setBackground(new java.awt.Color(-15589839));

        jPanel3.setBackground(new java.awt.Color(32, 182, 155));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 15), java.awt.Color.white)); // NOI18N
        //jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        //jPanel3.setBackground(new java.awt.Color(-15589839));

        txtFiltro.setNextFocusableComponent(jtbResultadoBusqueda);
        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });
        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setAutoscrolls(true);

        jtbResultadoBusqueda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtbResultadoBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtbResultadoBusqueda);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("<html><font size=4><center>Búsqueda:<br></font></center></html>");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(180, 180, 180))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(35, 109, 215));
        jPanel4.setOpaque(false);
        //jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        //jPanel4.setBackground(new java.awt.Color(-15589839));

        btnImprimir.setVisible(false);
        btnImprimir.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnImprimir.setText("<html><font size=4><center>Imprimir<br>F11</center></font></html>");
        btnImprimir.setMaximumSize(new java.awt.Dimension(150, 45));
        btnImprimir.setMinimumSize(new java.awt.Dimension(150, 45));
        btnImprimir.setNextFocusableComponent(txtFiltro);
        btnImprimir.setPreferredSize(new java.awt.Dimension(150, 45));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(pnlContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            imprimirFacturaCaja();
        } catch (IOException ex) {
            Logger.getLogger(Cortes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        String filtro = txtFiltro.getText();
        TableRowSorter sorter = new TableRowSorter(jtbResultadoBusqueda.getModel());
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
        jtbResultadoBusqueda.setRowSorter(sorter);
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        focusResultado();
    }//GEN-LAST:event_txtFiltroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbResultadoBusqueda;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables

    protected void setBotonesCajaEnabled(boolean aFlag) {
        btnImprimir.setEnabled(!aFlag);
    }

    private void imprimirFacturaCaja() throws IOException {
        if (jtbResultadoBusqueda.getSelectedRow() >= 0) {
            String codigoBarra = jtbResultadoBusqueda.getValueAt(jtbResultadoBusqueda.getSelectedRow(), 0).toString();
            for (int i = 0; i < codigoBarra.length(); i++) {
                if (codigoBarra.charAt(i) != 0) {
                    codigoBarra = codigoBarra.substring(i, codigoBarra.length());
                }
                break;
            }
            File reporteFile = menuPrincipal.getOBD().reimprimirReporte(codigoBarra, "reporte_corte", "corte_caja", "id_corte_caja", "reporteCorte");
            Desktop.getDesktop().open(reporteFile);
        }
    }

    /**
     * @return the tblResultadoBusqueda
     */
    public javax.swing.JTable getTblResultadoBusqueda() {
        return jtbResultadoBusqueda;
    }

    /**
     * @return the txtFiltro
     */
    public javax.swing.JTextField getTxtFiltro() {
        return txtFiltro;
    }
    
    /** Konstanza: 
     * Nueva funcion
     * Cambia el focus a 'jtbResultadoBusqueda'
     */
    private void focusResultado() {
        if (jtbResultadoBusqueda.getRowCount() > 0) {
            jtbResultadoBusqueda.requestFocus();
            jtbResultadoBusqueda.setRowSelectionInterval(0, 0);
        }
    }
    
    private void focusPuntoInteres() {
        if (!this.txtFiltro.hasFocus()) {
            this.txtFiltro.requestFocus();
            this.txtFiltro.setSelectionStart(0);
            this.txtFiltro.setSelectionEnd(this.txtFiltro.getText().length());
        } else {
            focusResultado();
        }
    }
}
