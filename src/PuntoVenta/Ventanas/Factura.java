package PuntoVenta.Ventanas;

import ClasesExtendidas.Tablas.ArrayListTableModel;
import PuntoVenta.Inicio.MenuPrincipal;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Factura extends javax.swing.JInternalFrame {

    public MenuPrincipal menuPrincipal;

    public String fecha;
    static DecimalFormat nf = new DecimalFormat("###,###,###,###.00");

    /**
     *
     * @param menuPrincipal
     */
    public Factura(MenuPrincipal menuPrincipal) {
        initComponents();
        this.menuPrincipal = menuPrincipal;
        this.setTitle("Saphiro - Facturas");
        crearHotKeys();
        LlenarTabla();
    }

    /**
     * Cierra la ventana con dispose().
     */
    private void LlenarTabla() {
        ArrayList ListaFactura;
        String[] columnas = {"Codigo Factura", "Cliente", "Fecha/Hora", "Monto"};
        String[] campos = {"codigo_factura", "nombre", "fecha_hora", "total"};
        ListaFactura = menuPrincipal.getOBD().getArrayListFactura();
        ArrayListTableModel model = new ArrayListTableModel(ListaFactura, columnas, campos);
        getTblResultadoBusqueda().setModel(model);
        getTblResultadoBusqueda().setFont(new Font("Arial", Font.BOLD, 12));
        getTblResultadoBusqueda().getColumnModel().getColumn(0).setPreferredWidth(150);
        getTblResultadoBusqueda().getColumnModel().getColumn(1).setPreferredWidth(190);
        getTblResultadoBusqueda().getColumnModel().getColumn(2).setPreferredWidth(150);
        getTblResultadoBusqueda().getColumnModel().getColumn(3).setPreferredWidth(100);
    }

    public javax.swing.JTable getTblResultadoBusqueda() {
        return jtbResultadoBusqueda;
    }

    public void crearHotKeys() {
        Action actCerrarVentana = new AbstractAction("actionCerrarVentanaCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };

        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F11, ActionEvent.ALT_MASK));

        pnlContenedor.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

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
        jPanel4 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        pnlContenedor.setBackground(new java.awt.Color(32, 182, 155));
        pnlContenedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(172, 162, 162), null, null));
        //jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ///jPanel1.setBackground(new java.awt.Color(-15589839));

        jPanel3.setBackground(new java.awt.Color(32, 182, 155));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Busqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 15), java.awt.Color.white)); // NOI18N
        //jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        //jPanel3.setBackground(new java.awt.Color(-15589839));

        txtFiltro.setNextFocusableComponent(jtbResultadoBusqueda);
        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });
        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFiltroKeyPressed(evt);
            }
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
        jtbResultadoBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbResultadoBusquedaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtbResultadoBusqueda);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(35, 109, 215));
        jPanel4.setOpaque(false);
        //jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        //jPanel4.setBackground(new java.awt.Color(-15589839));

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
                .addGap(251, 251, 251)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(pnlContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        reimprimir();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        String filtro = txtFiltro.getText();
        TableRowSorter sorter = new TableRowSorter(jtbResultadoBusqueda.getModel());
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
        jtbResultadoBusqueda.setRowSorter(sorter);
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing

    }//GEN-LAST:event_formInternalFrameClosing

    private void txtFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyPressed

    }//GEN-LAST:event_txtFiltroKeyPressed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void jtbResultadoBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbResultadoBusquedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F11) {
            reimprimir();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jtbResultadoBusquedaKeyPressed
    private void reimprimir() {
        int row = jtbResultadoBusqueda.getSelectedRow();
        if (row >= 0) {
            String codigoBarra = jtbResultadoBusqueda.getValueAt(row, 0).toString();
            for (int i = 0; i < codigoBarra.length(); i++) {
                if (codigoBarra.charAt(i) != 0) {
                    codigoBarra = codigoBarra.substring(i, codigoBarra.length());
                }
                break;
            }
            List lista = menuPrincipal.getOBD().reimprimirfac(codigoBarra);
            try {
                JasperReport reporte = (JasperReport) JRLoader.loadObject("src/PuntoVenta/ticket.jasper");
                JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
                JasperViewer visor = new JasperViewer(jprint, false);
                visor.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbResultadoBusqueda;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables

}
