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
import PuntoVenta.Ventanas.AgregarProducto;
import PuntoVenta.Ventanas.ModificarProducto;
import PuntoVenta.Modelos.ModeloProducto;
import java.awt.Dimension;
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

//revisar
public class Productos extends javax.swing.JInternalFrame {
 
 public MenuPrincipal menuPrincipal;
 public  AgregarProducto agregar;
 public ModificarProducto modificar;
 public ModeloProducto productos;
 
    /**
     *
     * @param menuPrincipal
     * @param agregar
     * @param modificar
     */
   
    public Productos(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
       // this.agregar = agregar;
      //  this.modificar = modificar;
        this.setTitle("Saphiro - Consúlta de productos");
        crearHotKeys();
        actualizarTabla();
        initComponents();
       
    }

    public void crearHotKeys() {
        
         Action actAgregar = new AbstractAction(Productos.Modulo.AGREGAR.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {
              Agregar();
            }
        };
        Action actModificar = new AbstractAction(Productos.Modulo.MODIFICAR.getAction()) {
            @Override
            public void actionPerformed(ActionEvent e) {
               Modificar();
            }
        }; 
       
        
        Action actEliminar = new AbstractAction(Productos.Modulo.ELIMINAR.getAction()) {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarProductos();
            }
        };
          
        actAgregar.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(0,0));
        actModificar.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(0,0));
        actEliminar.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(0,0));
        
        btnAgregar.getActionMap().put(Productos.Modulo.AGREGAR.getAction(), actAgregar);
        btnAgregar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAgregar.getValue(Action.ACCELERATOR_KEY), "actionAgregar");
        
        btnModificar.getActionMap().put(Productos.Modulo.MODIFICAR.getAction(), actModificar);
        btnModificar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actModificar.getValue(Action.ACCELERATOR_KEY), "actionModificar");
        
        btnEliminar.getActionMap().put(Productos.Modulo.ELIMINAR.getAction(), actAgregar);
        btnEliminar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actEliminar.getValue(Action.ACCELERATOR_KEY), "actionEliminar");
     
      
      
      
      InternalFrameAdapter listener = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                menuPrincipal.requestFocus();
            }
        };

        this.addInternalFrameListener(listener);
      
      
    }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProducto = new javax.swing.JTable();
        txtProductos = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
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

        panelPrincipal.setBackground(new java.awt.Color(32, 182, 155));
        panelPrincipal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(678, 536));

        jPanel1.setBackground(new java.awt.Color(32, 182, 155));
        jPanel1.setMaximumSize(new java.awt.Dimension(327, 327));

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAgregar.setText("<html><font size=4><center>Agregar<br>XX</center></font></html>");
        btnAgregar.setMaximumSize(new java.awt.Dimension(150, 45));
        btnAgregar.setMinimumSize(new java.awt.Dimension(150, 45));
        btnAgregar.setPreferredSize(new java.awt.Dimension(150, 45));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnModificar.setText("<html><font size=4><center>Modificar<br>XX</center></font></html>");
        btnModificar.setMaximumSize(new java.awt.Dimension(150, 45));
        btnModificar.setMinimumSize(new java.awt.Dimension(150, 45));
        btnModificar.setPreferredSize(new java.awt.Dimension(150, 45));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnEliminar.setText("<html><font size=4><center>Eliminar<br>XX</center></font></html>");
        btnEliminar.setMaximumSize(new java.awt.Dimension(150, 45));
        btnEliminar.setMinimumSize(new java.awt.Dimension(150, 45));
        btnEliminar.setPreferredSize(new java.awt.Dimension(150, 45));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(32, 182, 155));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html><font size=4><center>Búsqueda<br></center></font></html>", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 15), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 302));

        jtbProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtbProducto);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(txtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarProductos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void Agregar(){
                agregar = new AgregarProducto(menuPrincipal);
                Dimension desktopSize = menuPrincipal.panel.getSize();
                Dimension jInternalFrameSize = agregar.getSize();
                agregar.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                 menuPrincipal.panel.add(agregar);
                agregar.show(); 
    
    }
    
    
    private void Modificar(){
    
                modificar = new ModificarProducto(menuPrincipal);
                Dimension desktopSize = menuPrincipal.panel.getSize();
                Dimension jInternalFrameSize = modificar.getSize();
                modificar.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                menuPrincipal.panel.add(modificar);
                modificar.show();
    
   
    }  
    
     
    public void EliminarProductos(){
    
        int numeroRow = jtbProducto.getSelectedRow();
        if (numeroRow < 0) {
            jtbProducto.setRowSelectionInterval(0, 0);
            numeroRow = jtbProducto.getSelectedRow();
        }
        if (numeroRow >= 0) {
            int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Deséa eliminar el producto?: " + jtbProducto.getModel().getValueAt(numeroRow, 1) + " del sistema?", "Eliminar archivo");
            if (seleccion == 0) {
                String codigo = jtbProducto.getValueAt(numeroRow, 0).toString();
                menuPrincipal.getOBD().eliminarProducto(codigo);
                actualizarTabla();

            } else {
                jtbProducto.requestFocus();
            }
        }
    
    }
   
// revisar
    private void actualizarTabla() {
       ArrayList<HashMap<String, String>> Producto = menuPrincipal.getOBD().getArrayListProductos();
       ProductoTableModel model = new ProductoTableModel(Producto);
       jtbProducto.setModel(model); 
    }
    
    
    private enum Modulo {

        AGREGAR(1, "actionAgregar"),
        MODIFICAR(2, "actionModificar"),
        ELIMINAR(3, "actionEliminar");

        private final int id;
        private final String action;

        Modulo(int id, String action) {
            this.id = id;
            this.action = action;
        }

        public int getId() {
            return this.id;
        }

        private String getAction() {
            return this.action;
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbProducto;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField txtProductos;
    // End of variables declaration//GEN-END:variables

     public javax.swing.JTextField getTextField1() {
    String marmol=txtProductos.getText();
        return txtProductos;
    }
     

     
}