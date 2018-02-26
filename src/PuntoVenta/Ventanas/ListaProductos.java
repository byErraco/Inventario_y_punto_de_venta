package PuntoVenta.Ventanas;

import ClasesExtendidas.Tablas.ArrayListTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jose
 */
public class ListaProductos extends JInternalFrame {

    private final Venta venta;

    public ListaProductos(Venta venta) {
        initComponents();
        this.venta = venta;
        crearHotKeys();
        actualizarTabla();
    }


    public void crearHotKeys() {
        Action actCerrarVentana = new AbstractAction("actionCerrarVentanaCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        };
        Action actFocus = new AbstractAction("actionFocus") {
            @Override
            public void actionPerformed(ActionEvent e) {
                focusPuntoInteres();
            }
        };
        Action actAgregar = new AbstractAction("actionAgregar") {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("KEY: Agregar a la venta");
            }
        };

        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        actFocus.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        actAgregar.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));

        pnlContenedor.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

        pnlContenedor.getActionMap().put("actionFocus", actFocus);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFocus.getValue(Action.ACTION_COMMAND_KEY), "actionFocus");

        jtbProductos.getActionMap().put("actionAgregar", actAgregar);
        jtbProductos.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAgregar.getValue(Action.ACCELERATOR_KEY), "actionAgregar");

        InternalFrameListener listener = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                venta.requestFocus();
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
                venta.buscarProducto.getTxtCampoDescripcion().requestFocus();
            }
        };
        this.addInternalFrameListener(listener);
    }

    public void agregarProductoAFactura() {
        if (venta.getIdVenta() > 0) {
            int row = jtbProductos.getSelectedRow();
            if (row >= 0) {
                String codigoBarra = jtbProductos.getValueAt(row, 0).toString();
                cerrarVentana();
                venta.cargarInformacionProducto(codigoBarra);
            } else {
                Utilidades.Sonidos.beep();
            }
        } else {
            Utilidades.Sonidos.beep();
        }

    }

    public void focusPuntoInteres() {
        if (getTxtCampoDescripcion().hasFocus()) {
            jtbProductos.requestFocus();
        } else {
            getTxtCampoDescripcion().requestFocus();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        pnlContenedor = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtCampoDescripcion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProductos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setClosable(true);

        pnlContenedor.setBackground(new java.awt.Color(32, 182, 155));
        pnlContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanel2.setBackground(new java.awt.Color(32, 182, 155));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Buscar producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N

        txtCampoDescripcion.setNextFocusableComponent(jtbProductos);
        txtCampoDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCampoDescripcionActionPerformed(evt);
            }
        });
        txtCampoDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCampoDescripcionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCampoDescripcionKeyReleased(evt);
            }
        });

        jtbProductos.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        /*
        */
        jtbProductos.setAutoscrolls(false);
        jtbProductos.getTableHeader().setResizingAllowed(false);
        jtbProductos.getTableHeader().setReorderingAllowed(false);
        jtbProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtbProductos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(txtCampoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCampoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(32, 182, 155));

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregar.setText("<html><font size=4><center>Agregar<br>F2</font></center></html>");
        btnAgregar.setActionCommand("actionAgregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSalir.setText("<html><font size=4><center>Salir<br>Esc</font></center></html>");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAgregar)
                .addComponent(btnSalir))
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarProductoAFactura();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        cerrarVentana();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtCampoDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCampoDescripcionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jtbProductos.getRowCount() > 0) {
                jtbProductos.requestFocus();
                jtbProductos.setRowSelectionInterval(0, 0);
            }
            
        }
    }//GEN-LAST:event_txtCampoDescripcionKeyPressed

    private void txtCampoDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCampoDescripcionKeyReleased
        
        if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
            String filtro = getTxtCampoDescripcion().getText();
            TableRowSorter sorter = new TableRowSorter(jtbProductos.getModel());
            sorter.setRowFilter(RowFilter.regexFilter(filtro));
            jtbProductos.setRowSorter(sorter);
        }
    }//GEN-LAST:event_txtCampoDescripcionKeyReleased

    private void jtbProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            agregarProductoAFactura();
        }
    }//GEN-LAST:event_jtbProductosKeyPressed

    private void txtCampoDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCampoDescripcionActionPerformed
 String filtro = getTxtCampoDescripcion().getText();
            TableRowSorter sorter = new TableRowSorter(jtbProductos.getModel());
            sorter.setRowFilter(RowFilter.regexFilter(filtro));
            jtbProductos.setRowSorter(sorter);           
  
    }//GEN-LAST:event_txtCampoDescripcionActionPerformed

    private void cerrarVentana() {
        this.dispose();
    }

    private void actualizarTabla() {
        String[] headers = {"Código", "Descripción", "Precio"};
        String[] columnas = {"codigo_barra", "descripcion", "pvp"};
        ArrayList<HashMap<String, String>> listaProductos = venta.menuPrincipal.getOBD().getArrayListProductos();
        ArrayListTableModel model = new ArrayListTableModel(listaProductos, headers, columnas);
        jtbProductos.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jtbProductos;
    private javax.swing.JPanel pnlContenedor;
    public static javax.swing.JTextField txtCampoDescripcion;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the txtCampoDescripcion
     */
    public javax.swing.JTextField getTxtCampoDescripcion() {
    String marmol=txtCampoDescripcion.getText();
        return txtCampoDescripcion;
    }
}
