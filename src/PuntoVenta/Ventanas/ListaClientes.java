/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;

import ClasesExtendidas.Tablas.ClienteTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author inverdata03
 */
public class ListaClientes extends javax.swing.JInternalFrame {

    private Venta venta;

    /**
     * Creates new form Cliente
     *
     * @param venta
     */
    public ListaClientes(Venta venta) {
        this.venta = venta;
        initComponents();
        this.btnAgregar.setEnabled(!venta.isClienteAsociadoFactura());
        crearHotKeys();
        this.actualizarTabla();
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
        Action actAgregar = new AbstractAction("actionAgregar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        };
        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        actFocusPuntoInteres.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        actAgregar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));

        pnlContenedor.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

        pnlContenedor.getActionMap().put("actionFocusPuntoInteres", actFocusPuntoInteres);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFocusPuntoInteres.getValue(Action.ACTION_COMMAND_KEY), "actionFocusPuntoInteres");

        btnAgregar.getActionMap().put("actionAgregar", actAgregar);
        btnAgregar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAgregar.getValue(Action.ACCELERATOR_KEY), "actionAgregar");

        InternalFrameAdapter listener = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                venta.getTxtDocumento().requestFocus();
            }
        };
        this.addInternalFrameListener(listener);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbListaCliente = new javax.swing.JTable();

        setClosable(true);
        setTitle("Saphiro - Cliente");

        pnlContenedor.setBackground(new java.awt.Color(32, 182, 155));

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregar.setText("<html><center>Agregar<br>F2</center></html>");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSalir.setText("<html><center>Salir<br>Alt + Q</center></html>");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(32, 182, 155));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Buscar cliente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), java.awt.Color.white)); // NOI18N

        txtFiltro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFiltroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        jtbListaCliente.setBackground(new java.awt.Color(240, 240, 240));
        jtbListaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtbListaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbListaClienteKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtbListaCliente);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlContenedorLayout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarCliente();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        String filtro = txtFiltro.getText();
        TableRowSorter sorter = new TableRowSorter(jtbListaCliente.getModel());
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
        jtbListaCliente.setRowSorter(sorter);
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void txtFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jtbListaCliente.getRowCount() > 0) {
                jtbListaCliente.requestFocus();
                jtbListaCliente.setRowSelectionInterval(0, 0);
            }
        }
    }//GEN-LAST:event_txtFiltroKeyPressed

    private void jtbListaClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbListaClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            agregarCliente();
        }
    }//GEN-LAST:event_jtbListaClienteKeyPressed

    private void cerrarVentana() {
        this.dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbListaCliente;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables

    private void actualizarTabla() {
        ArrayList<HashMap<String, String>> lista = venta.menuPrincipal.getOBD().getArrayListClientes();
        ClienteTableModel model = new ClienteTableModel(lista);
        jtbListaCliente.setModel(model);
    }

    private void agregarCliente() {
        int row = jtbListaCliente.getSelectedRow();
        if (row >= 0) {
            venta.getTxtDocumento().setText(jtbListaCliente.getValueAt(row, 0).toString());
            String documento = jtbListaCliente.getValueAt(jtbListaCliente.getSelectedRow(), 0).toString();
            
            // Konstanza: aquí hay un error, el documento tiene el número sin el tipo, no hay '-'
            venta.getCmbTipoDocumento().setSelectedItem(documento.split("-")[0]);
            venta.getTxtDocumento().setText(documento.split("-")[1]);
            this.cerrarVentana();
        }
    }

    private void focusPuntoInteres() {
        if (!this.txtFiltro.hasFocus()) {
            this.txtFiltro.requestFocus();
            this.txtFiltro.setSelectionStart(0);
            this.txtFiltro.setSelectionEnd(this.txtFiltro.getText().length());
        } else {
            txtFiltroKeyPressed(new KeyEvent(this, 0, 0, 0, KeyEvent.VK_ENTER, ' '));
        }
    }

}
