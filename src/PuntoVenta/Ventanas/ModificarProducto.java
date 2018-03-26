/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;
import ClasesExtendidas.Tablas.ArrayListTableModel;
import ClasesExtendidas.Tablas.ProductoTableModel;
import PuntoVenta.Modelos.ModeloProducto;
import PuntoVenta.Ventanas.Productos;
import PuntoVenta.Inicio.MenuPrincipal;
import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import java.awt.CardLayout;
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
public class ModificarProducto extends javax.swing.JInternalFrame {

    public  MenuPrincipal menuPrincipal;
    public ModeloProducto producto;
  //  public  Productos producto;
    /**
     * Creates new form Detalles
     */
    public ModificarProducto(MenuPrincipal menuPrincipal) {
        this.setTitle("Saphiro - Modificar producto");
        this.menuPrincipal = menuPrincipal;
       // this.producto = producto;
        crearHotKeys();
        actualizarTabla();
        initComponents();
    }

   public void crearHotKeys(){
      Action actDescripcion = new AbstractAction("actionDescripcion") {
            @Override
            public void actionPerformed(ActionEvent e) {
              descripcion();
            }
        };
        Action actComponente = new AbstractAction("actionComponenete") {
            @Override
            public void actionPerformed(ActionEvent e) {
               componente();
            }
        }; 
       
        
        Action actModificar = new AbstractAction("actionModificar") {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProducto();
            }
        };
        
        actDescripcion.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(0,0));
        actComponente.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(0,0));
        actModificar.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(0,0));
        
        btnDescripcion.getActionMap().put("actionDescripcion", actDescripcion);
        btnDescripcion.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actDescripcion.getValue(Action.ACCELERATOR_KEY), "actionDescripcion");
        
        btnComponente.getActionMap().put("actionComponente", actComponente);
        btnComponente.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actComponente.getValue(Action.ACCELERATOR_KEY), "actionComponente");
        
        btnModificar.getActionMap().put("actionModificar", actModificar);
        btnModificar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actModificar.getValue(Action.ACCELERATOR_KEY), "actionModificar");
     
      
      
      
      InternalFrameAdapter listener = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                menuPrincipal.requestFocus();
            }
        };

        this.addInternalFrameListener(listener);
      
   }
   
   
   //revisar
   public void actualizarTabla(){
        ArrayList<HashMap<String, String>> Descripcion = menuPrincipal.getOBD().getArrayListProductos();
        ProductoTableModel model = new ProductoTableModel(Descripcion);
        jtbDescripcion.setModel(model);
       
         ArrayList<HashMap<String, String>> Componente = menuPrincipal.getOBD().getArrayListProductos();
        ProductoTableModel model2 = new ProductoTableModel(Componente);
        jtbComponente.setModel(model2);
   
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDetalles = new javax.swing.JPanel();
        panelDescripcion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDescripcion = new javax.swing.JTable();
        panelComponente = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbComponente = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        jToolBarDetalle = new javax.swing.JToolBar();
        btnDescripcion = new javax.swing.JButton();
        btnComponente = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(32, 182, 155));
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelDetalles.setBackground(new java.awt.Color(32, 182, 155));
        panelDetalles.setLayout(new java.awt.CardLayout());

        panelDescripcion.setBackground(new java.awt.Color(32, 182, 155));

        jtbDescripcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbDescripcion);

        javax.swing.GroupLayout panelDescripcionLayout = new javax.swing.GroupLayout(panelDescripcion);
        panelDescripcion.setLayout(panelDescripcionLayout);
        panelDescripcionLayout.setHorizontalGroup(
            panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDescripcionLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1)
                .addGap(19, 19, 19))
        );
        panelDescripcionLayout.setVerticalGroup(
            panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelDetalles.add(panelDescripcion, "card2");

        panelComponente.setBackground(new java.awt.Color(32, 182, 155));

        jtbComponente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jtbComponente);

        javax.swing.GroupLayout panelComponenteLayout = new javax.swing.GroupLayout(panelComponente);
        panelComponente.setLayout(panelComponenteLayout);
        panelComponenteLayout.setHorizontalGroup(
            panelComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(panelComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComponenteLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2)
                    .addContainerGap()))
        );
        panelComponenteLayout.setVerticalGroup(
            panelComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(panelComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelComponenteLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2)
                    .addContainerGap()))
        );

        panelDetalles.add(panelComponente, "card3");

        btnModificar.setText("<html><font size=4><center>Modificar<br>XX</center></font></html>");
        btnModificar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jToolBarDetalle.setBackground(new java.awt.Color(32, 182, 155));
        jToolBarDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBarDetalle.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBarDetalle.setRollover(true);

        btnDescripcion.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnDescripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ayuda.png"))); // NOI18N
        btnDescripcion.setText("<html><font size=4><center>Descripción</center></font></html>");
        btnDescripcion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDescripcion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnDescripcion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDescripcionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDescripcionMouseExited(evt);
            }
        });
        btnDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescripcionActionPerformed(evt);
            }
        });
        jToolBarDetalle.add(btnDescripcion);

        btnComponente.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnComponente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ayuda.png"))); // NOI18N
        btnComponente.setText("<html><font size=4><center>Componente<br></center></font></html>");
        btnComponente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnComponente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnComponente.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnComponente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComponenteActionPerformed(evt);
            }
        });
        jToolBarDetalle.add(btnComponente);

        jButton1.setText("<html><font size=4><center>Eliminar<br>XX</center></font></html>");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToolBarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jButton1))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDescripcionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDescripcionMouseEntered
         setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnDescripcionMouseEntered

    private void btnDescripcionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDescripcionMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnDescripcionMouseExited

    private void btnDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescripcionActionPerformed
      descripcion();
    }//GEN-LAST:event_btnDescripcionActionPerformed
  
    private void btnComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComponenteActionPerformed
      componente();
    }//GEN-LAST:event_btnComponenteActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
    modificarProducto();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       EliminarProductos();
    }//GEN-LAST:event_jButton1ActionPerformed
 private void descripcion() {
        CardLayout card = (CardLayout) panelDetalles.getLayout();
        card.show(panelDetalles, "panelDescripcion");
       // txtFiltro.requestFocus();
    }
    
    private void componente() {
        CardLayout card = (CardLayout) panelDetalles.getLayout();
        card.show(panelDetalles, "panelComponente");
       // txtFiltro.requestFocus();
    }
    
    public void  modificarProducto(){
    
    }

    
   
    public void EliminarProductos(){
    
        int numeroRow = jtbDescripcion.getSelectedRow();
        if (numeroRow < 0) {
            jtbDescripcion.setRowSelectionInterval(0, 0);
            numeroRow = jtbDescripcion.getSelectedRow();
        }
        if (numeroRow >= 0) {
            int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Deséa eliminar el producto?: " + jtbDescripcion.getModel().getValueAt(numeroRow, 1) + " del sistema?", "Eliminar archivo");
            if (seleccion == 0) {
                String codigo = jtbDescripcion.getValueAt(numeroRow, 0).toString();
                menuPrincipal.getOBD().eliminarProducto(codigo);
                actualizarTabla();

            } else {
                jtbDescripcion.requestFocus();
            }
        }
    
    }
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComponente;
    private javax.swing.JButton btnDescripcion;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBarDetalle;
    private javax.swing.JTable jtbComponente;
    private javax.swing.JTable jtbDescripcion;
    private javax.swing.JPanel panelComponente;
    private javax.swing.JPanel panelDescripcion;
    private javax.swing.JPanel panelDetalles;
    // End of variables declaration//GEN-END:variables
}
