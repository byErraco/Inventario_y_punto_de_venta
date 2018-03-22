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
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author David Chavez
 */
public class Detalles extends javax.swing.JInternalFrame {

    public  MenuPrincipal menuPrincipal;
    public  Productos producto;
    /**
     * Creates new form Detalles
     */
    public Detalles(MenuPrincipal menuPrincipal) {
        this.setTitle("Saphiro - Detalles de producto");
        this.menuPrincipal = menuPrincipal;
        this.producto = producto;
        crearHotKeys();
        actualizarTabla();
        initComponents();
    }

   public void crearHotKeys(){
       
       
   
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
        btnAceptar = new javax.swing.JButton();
        jToolBarDetalle = new javax.swing.JToolBar();
        btnDescripcion = new javax.swing.JButton();
        btnComponente = new javax.swing.JButton();

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
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
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

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/Aceptar3.png"))); // NOI18N
        btnAceptar.setText("<html><font size=4><center>Aceptar<br></center></font></html>");
        btnAceptar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToolBarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBarDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnAceptar.getAccessibleContext().setAccessibleName("<html><font size=4><center>Aceptar<br>XX</center></font></html>");

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

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed
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
    
    
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnComponente;
    private javax.swing.JButton btnDescripcion;
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
