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
public class AgregarProducto extends javax.swing.JInternalFrame {

public  MenuPrincipal menuPrincipal;
public  Productos producto;
private ObjetoBaseDatos obd;
  
   
   public AgregarProducto(MenuPrincipal menuPrincipal) {
         
        this.setTitle("Saphiro - Agregar producto");
      //  this.menuPrincipal = menuPrincipal;
        this.producto = producto;
       // crearHotKeys();
//        actualizarTabla();
        initComponents();
    }

     

  
 //  public void crearHotKeys(){
       
       
   
   //}
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

        panelPrincipal = new javax.swing.JPanel();
        panelDescripcion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbDescripcion = new javax.swing.JTable();
        panelComponente = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbComponente = new javax.swing.JTable();
        jToolBarDetalles = new javax.swing.JToolBar();
        btnDescripcion = new javax.swing.JButton();
        btnComponente = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();

        setBackground(new java.awt.Color(32, 182, 155));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelPrincipal.setBackground(new java.awt.Color(32, 182, 155));
        panelPrincipal.setLayout(new java.awt.CardLayout());

        panelDescripcion.setBackground(new java.awt.Color(32, 182, 155));
        panelDescripcion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html><font size=4><center>Agregar<br></center></font></html>", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 15), new java.awt.Color(255, 255, 255))); // NOI18N

        jtbDescripcion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html><font size=4><center>Detálles<br></center></font></html>", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDescripcionLayout.setVerticalGroup(
            panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDescripcionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelDescripcion, "card2");

        panelComponente.setBackground(new java.awt.Color(32, 182, 155));
        panelComponente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "<html><font size=4><center>Agregar<br></center></font></html>", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 15), new java.awt.Color(255, 255, 255))); // NOI18N

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
            .addGroup(panelComponenteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        panelComponenteLayout.setVerticalGroup(
            panelComponenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelComponenteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelPrincipal.add(panelComponente, "card3");

        jToolBarDetalles.setBackground(new java.awt.Color(32, 182, 155));
        jToolBarDetalles.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBarDetalles.setRollover(true);

        btnDescripcion.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnDescripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ayuda.png"))); // NOI18N
        btnDescripcion.setText("<html><font size=4><center>Descripción<br>XX</center></font></html>");
        btnDescripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnDescripcion.setFocusable(false);
        btnDescripcion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        jToolBarDetalles.add(btnDescripcion);

        btnComponente.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnComponente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ayuda.png"))); // NOI18N
        btnComponente.setText("<html><font size=4><center>Componente<br>XX</center></font></html>");
        btnComponente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnComponente.setFocusable(false);
        btnComponente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnComponente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnComponente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnComponenteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnComponenteMouseExited(evt);
            }
        });
        btnComponente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComponenteActionPerformed(evt);
            }
        });
        jToolBarDetalles.add(btnComponente);

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAgregar.setText("<html><font size=4><center>Agregar<br>XX</center></font></html>");
        btnAgregar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnAgregar.setFocusable(false);
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAgregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBarDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBarDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnComponenteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComponenteMouseEntered
       setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnComponenteMouseEntered

    private void btnComponenteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComponenteMouseExited
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnComponenteMouseExited

    private void descripcion() {
        CardLayout card = (CardLayout) panelPrincipal.getLayout();
        card.show(panelPrincipal, "panelDescripcion");
       // txtFiltro.requestFocus();
    }
    
    private void componente() {
        CardLayout card = (CardLayout) panelPrincipal.getLayout();
        card.show(panelPrincipal, "panelComponente");
       // txtFiltro.requestFocus();
    }
    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnComponente;
    private javax.swing.JButton btnDescripcion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBarDetalles;
    private javax.swing.JTable jtbComponente;
    private javax.swing.JTable jtbDescripcion;
    private javax.swing.JPanel panelComponente;
    private javax.swing.JPanel panelDescripcion;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables

    void setLocationRelativeTo(Productos aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   /*  public void agregarProducto() {
        if (producto.getId() > 0) {
            int row = jtbAgregar.getSelectedRow();
            if (row >= 0) {
                String codigoBarra = jtbAgregar.getValueAt(row, 0).toString();
                cerrarVentana();
                producto.getCodigoBarra();
            } else {
                Utilidades.Sonidos.beep();
            }
        } else {
            Utilidades.Sonidos.beep();
        } 
*/
    
    
}
