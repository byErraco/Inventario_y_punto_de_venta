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
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author David Chavez
 */
public class ModificarProducto extends javax.swing.JInternalFrame {
public final MenuPrincipal menuPrincipal;
private final Productos prod;
private ObjetoBaseDatos obd;
   
public ModificarProducto(Productos producto, MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        this.prod = producto;
        this.setTitle("Saphiro - Modificar Producto");
        initComponents();
        crearHotKeys();
        actualizarTabla();
    }

  /*  public ModificarProducto(Productos producto, MenuPrincipal menuPrincipal) {
        initComponents();
        this.menuPrincipal = menuPrincipal;
        this.prod = producto;
        this.setTitle("Saphiro - Modificar Producto");
        crearHotKeys();
        actualizarTabla();
    }*/

   

   
    
   public void crearHotKeys(){
   
   }
   public void actualizarTabla(){
        ArrayList<HashMap<String, String>> Producto = menuPrincipal.getOBD().getArrayListProductos();
        ProductoTableModel model = new ProductoTableModel(Producto);
        jtbComponentes.setModel(model);
   
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelDeltalle = new javax.swing.JPanel();
        panelComponentes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbComponentes = new javax.swing.JTable();
        panelDescripcion = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbDescripcion = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        btnDescripcion = new javax.swing.JButton();
        btnComponente = new javax.swing.JButton();

        setBackground(new java.awt.Color(32, 182, 155));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel1.setText("DETALLES");

        panelDeltalle.setBackground(new java.awt.Color(32, 182, 155));
        panelDeltalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelDeltalle.setLayout(new java.awt.CardLayout());

        panelComponentes.setBackground(new java.awt.Color(32, 182, 155));

        jtbComponentes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtbComponentes);

        javax.swing.GroupLayout panelComponentesLayout = new javax.swing.GroupLayout(panelComponentes);
        panelComponentes.setLayout(panelComponentesLayout);
        panelComponentesLayout.setHorizontalGroup(
            panelComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComponentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelComponentesLayout.setVerticalGroup(
            panelComponentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComponentesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDeltalle.add(panelComponentes, "card2");

        panelDescripcion.setBackground(new java.awt.Color(32, 182, 155));

        jtbDescripcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "1", "2", "3", "4"
            }
        ));
        jScrollPane2.setViewportView(jtbDescripcion);

        javax.swing.GroupLayout panelDescripcionLayout = new javax.swing.GroupLayout(panelDescripcion);
        panelDescripcion.setLayout(panelDescripcionLayout);
        panelDescripcionLayout.setHorizontalGroup(
            panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
            .addGroup(panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDescripcionLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelDescripcionLayout.setVerticalGroup(
            panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
            .addGroup(panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelDescripcionLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panelDeltalle.add(panelDescripcion, "card3");

        btnModificar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnModificar.setText("<html><font size=2><center>Modificar</center></font></html>");
        btnModificar.setBorder(null);

        jToolBar1.setBackground(new java.awt.Color(32, 182, 155));
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);
        jToolBar1.setMaximumSize(new java.awt.Dimension(2147483647, 251));
        jToolBar1.setMinimumSize(new java.awt.Dimension(63, 251));

        btnDescripcion.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnDescripcion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ayuda.png"))); // NOI18N
        btnDescripcion.setText("<html><font size=2><center>Descripcion</center></font></html>");
        btnDescripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
        jToolBar1.add(btnDescripcion);

        btnComponente.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        btnComponente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/1. ayuda.png"))); // NOI18N
        btnComponente.setText("<html><font size=2><center>Componente</center></font></html>");
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
        jToolBar1.add(btnComponente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(panelDeltalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172)))
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(248, 248, 248))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelDeltalle, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescripcionActionPerformed
        
    }//GEN-LAST:event_btnDescripcionActionPerformed

    private void btnComponenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComponenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComponenteActionPerformed

    private void btnDescripcionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDescripcionMouseExited
      setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnDescripcionMouseExited

    private void btnDescripcionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDescripcionMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnDescripcionMouseEntered

    private void btnComponenteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComponenteMouseEntered
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnComponenteMouseEntered

    private void btnComponenteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComponenteMouseExited
       setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnComponenteMouseExited

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComponente;
    private javax.swing.JButton btnDescripcion;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable jtbComponentes;
    private javax.swing.JTable jtbDescripcion;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelDeltalle;
    private javax.swing.JPanel panelDescripcion;
    // End of variables declaration//GEN-END:variables

}
