/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;
import ClasesExtendidas.Tablas.ArrayListTableModel;
import ClasesExtendidas.Tablas.ProductoTableModel;
import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import PuntoVenta.Inicio.MenuPrincipal;
import PuntoVenta.Ventanas.Detalles;
import PuntoVenta.Modelos.ModeloProducto;
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
 * @author David Chavez
 */
public class Productos extends javax.swing.JInternalFrame {
 private final ModeloProducto productos;
 private final MenuPrincipal menuPrincipal;
   
    public Productos(MenuPrincipal menuPrincipal , ModeloProducto producto) {
        initComponents();
        this.menuPrincipal = menuPrincipal;
        this.setTitle("Saphiro - Adinistracion de Productos");
        this.productos = producto;
        crearHotKeys();
        actualizarTabla();
    
    }


    public Productos(MenuPrincipal aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public void crearHotKeys() {
        
        Action actAgregar = new AbstractAction("actionAgregar") {
            @Override
            public void actionPerformed(ActionEvent e) {
//               agregarProducto();
            }
        };
        Action actModificar = new AbstractAction("actionModificar") {
            @Override
            public void actionPerformed(ActionEvent e) {
               modificarProducto();
            }
        };
       
        
        Action actEliminar = new AbstractAction("actionEliminar") {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarProductos();
            }
        };
    }

    public void agregarProducto() {
        if (productos.getId() > 0) {
            int row = jtbProductos.getSelectedRow();
            if (row >= 0) {
                String codigoBarra = jtbProductos.getValueAt(row, 0).toString();
                cerrarVentana();
                productos.getCodigoBarra();
            } else {
                Utilidades.Sonidos.beep();
            }
        } else {
            Utilidades.Sonidos.beep();
        }

    }
    public void modificarProducto(){
    
    
    
    }
    
    
    public void EliminarProductos(){
    
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProductos = new javax.swing.JTable();
        txtProductos = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnVer = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 204, 102));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnAgregar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnAgregar.setText("<html><font size=2><center>Agregar</center></font></html> ");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnModificar.setText("<html><font size=2><center>Modificar</center></font></html> ");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnEliminar.setText("<html><font size=2><center>Eliminar</center></font></html> ");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jtbProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbProductos);
        if (jtbProductos.getColumnModel().getColumnCount() > 0) {
            jtbProductos.getColumnModel().getColumn(0).setResizable(false);
            jtbProductos.getColumnModel().getColumn(1).setResizable(false);
            jtbProductos.getColumnModel().getColumn(2).setResizable(false);
            jtbProductos.getColumnModel().getColumn(3).setResizable(false);
        }

        txtProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProductosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductosKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel1.setText("BUSQUEDA");

        btnVer.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnVer.setText("<html><font size=2><center>Ver</center></font></html> ");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addComponent(txtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarProducto();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
       Detalles det = new Detalles();
       det.setVisible(true);
       
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificarProducto();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarProductos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jtbProductos.getRowCount() > 0) {
                jtbProductos.requestFocus();
                jtbProductos.setRowSelectionInterval(0, 0);
            }
        }
    }//GEN-LAST:event_txtProductosKeyPressed

    private void txtProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductosKeyReleased
       String filtro = txtProductos.getText();
        TableRowSorter sorter = new TableRowSorter(jtbProductos.getModel());
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
        jtbProductos.setRowSorter(sorter);
    }//GEN-LAST:event_txtProductosKeyReleased

    
    
     private void cerrarVentana() {
        this.dispose();
    }
// revisar
    private void actualizarTabla() {
        String[] headers = {"Código", "Descripción","Cantidad","Tipo", "Precio"};
        String[] columnas = {"codigo_barra", "descripcion_producto","cantidad_disponible","producto_pre_fabricado", "pvp"};
        ArrayList<HashMap<String, String>> Producto = menuPrincipal.getOBD().getArrayListProductos();
        ArrayListTableModel model = new ArrayListTableModel(Producto, headers, columnas);
        jtbProductos.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbProductos;
    private javax.swing.JTextField txtProductos;
    // End of variables declaration//GEN-END:variables

     public javax.swing.JTextField getTextField1() {
    String marmol=txtProductos.getText();
        return txtProductos;
    }
     

     
}