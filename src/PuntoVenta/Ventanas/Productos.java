/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;
import ClasesExtendidas.Tablas.ArrayListTableModel;
import ClasesExtendidas.Tablas.ClienteTableModel;
import ClasesExtendidas.Tablas.EmpleadosTableModel;
import ClasesExtendidas.Tablas.ProductoTableModel;
import ClasesExtendidas.Tablas.ProductoVentaTableModel;
import PuntoVenta.BaseDatos.ObjetoBaseDatos;
import PuntoVenta.Inicio.MenuPrincipal;
import PuntoVenta.Ventanas.AgregarProducto1;
import PuntoVenta.Ventanas.ModificarProducto1;
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
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author David Chavez
 */

//revisar
public class Productos extends javax.swing.JInternalFrame {
    
 private Modulo ventanaAbierta; 
 public MenuPrincipal menuPrincipal;
 public  AgregarProducto1 agregar;
 public ModificarProducto1 modificar; 
 public ModificarProducto2 modificar2;
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
       // crearHotKeys();
        initComponents();
        actualizarTabla();

    }
    
    
     public boolean estacerrado(Object obj) {
        JInternalFrame[] activos = menuPrincipal.panel.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == obj) {
                cerrado = false;
            }
            i++;
        }
        return cerrado;
    }


 /*   public void crearHotKeys() {
        
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
        actEliminar.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(0,0));    */
    /*    
        btnAgregar.getActionMap().put(Productos.Modulo.AGREGAR.getAction(),actAgregar);
        btnAgregar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAgregar.getValue(Action.ACCELERATOR_KEY), "actionAgregar");
        
        btnModificar.getActionMap().put(Productos.Modulo.MODIFICAR.getAction(),actModificar);
        btnModificar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actModificar.getValue(Action.ACCELERATOR_KEY), "actionModificar");
        
        btnEliminar.getActionMap().put(Productos.Modulo.ELIMINAR.getAction(),actEliminar);
        btnEliminar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actEliminar.getValue(Action.ACCELERATOR_KEY), "actionEliminar");
     
      
      
      
      InternalFrameAdapter listener = new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                menuPrincipal.requestFocus();
            }
        };

        this.addInternalFrameListener(listener);
      
      
    }
       */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtProductos = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbListaProducto = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

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

        jPanel2.setBackground(new java.awt.Color(32, 182, 155));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 302));

        txtProductos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));

        jtbListaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtbListaProducto);

        btnAgregar.setBackground(new java.awt.Color(32, 182, 155));
        btnAgregar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("<html><font size=4><center>Agregar<br>XX</center></font></html>");
        btnAgregar.setBorder(null);
        btnAgregar.setMaximumSize(new java.awt.Dimension(150, 45));
        btnAgregar.setMinimumSize(new java.awt.Dimension(150, 45));
        btnAgregar.setPreferredSize(new java.awt.Dimension(150, 45));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(32, 182, 155));
        btnModificar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("<html><font size=4><center>Modificar<br>XX</center></font></html>");
        btnModificar.setBorder(null);
        btnModificar.setMaximumSize(new java.awt.Dimension(150, 45));
        btnModificar.setMinimumSize(new java.awt.Dimension(150, 45));
        btnModificar.setPreferredSize(new java.awt.Dimension(150, 45));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(32, 182, 155));
        btnEliminar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("<html><font size=4><center>Eliminar<br>XX</center></font></html>");
        btnEliminar.setBorder(null);
        btnEliminar.setMaximumSize(new java.awt.Dimension(150, 45));
        btnEliminar.setMinimumSize(new java.awt.Dimension(150, 45));
        btnEliminar.setPreferredSize(new java.awt.Dimension(150, 45));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(32, 182, 155));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/cuadro-700x400.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(28, 90, 125));
        jLabel1.setText("<html><font size=4><center>Búsqueda:<br></font></center></html>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(22, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(58, 58, 58)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
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
        modificarProductoSeleccionado();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
//        EliminarProductos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void Agregar(){
              
           if(estacerrado(agregar)){
                agregar = new AgregarProducto1(menuPrincipal);
                Dimension desktopSize = menuPrincipal.panel.getSize();
                Dimension jInternalFrameSize = agregar.getSize();
                agregar.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                        (desktopSize.height - jInternalFrameSize.height) / 2);
                 menuPrincipal.panel.add(agregar);
                agregar.show(); 
                 setVentanaAbierta(Modulo.AGREGAR);
           }else{
                JOptionPane.showMessageDialog(this, "La ventana\n"+this.agregar.getTitle()+ "\nse encuentra abierta...");
                  agregar.toFront();
         
           }
    
    }
    
    
    private void abrirVentanaModificacionProductos(String codigo) {
    
//      QUEDA PENDIENTE PARA MANANA TERMINAR EL DE MODIFICAR PRODUCTOS
        modificar2 = new ModificarProducto2(this, codigo);
        Dimension desktopSize = menuPrincipal.panel.getSize();
        Dimension jInternalFrameSize = modificar2.getSize();
        modificar2.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);

        menuPrincipal.panel.add(modificar2);
        modificar2.show();
    
   
    }  
    
    public void modificarProductoSeleccionado() {
        int numeroRow = jtbListaProducto.getSelectedRow();
        if (numeroRow < 0) {
            jtbListaProducto.setRowSelectionInterval(0, 0);
        }
        else {
            String codigo = jtbListaProducto.getValueAt(numeroRow, 1).toString();
            abrirVentanaModificacionProductos(codigo);
        }
    }
     
//    public void EliminarProductos(){
//    
//        int numeroRow = jtbListaProducto.getSelectedRow();
//        if (numeroRow < 0) {
//            jtbListaProducto.setRowSelectionInterval(0, 0);
//            numeroRow = jtbListaProducto.getSelectedRow();
//        }
//        if (numeroRow >= 0) {
//            int seleccion = Utilidades.CuadroMensaje.getMensajeSiNo(this, "¿Deséa eliminar el producto?: " + jtbListaProducto.getModel().getValueAt(numeroRow, 1) + " del sistema?", "Eliminar archivo");
//            if (seleccion == 0) {
//                String codigo = jtbListaProducto.getValueAt(numeroRow, 1).toString();
////                System.out.println("codigo = "+codigo);
//                menuPrincipal.getOBD().eliminarProducto(codigo);
//                actualizarTabla();
//
//            } else {
//                jtbListaProducto.requestFocus();
//            }
//        }
//    
//    }
   
    public void actualizarTabla() {
       ArrayList<HashMap<String, String>> Producto = menuPrincipal.getOBD().getArrayListProductos();
       ProductoTableModel model = new ProductoTableModel(Producto);
       System.out.println("Producto -> "+Producto);
//       System.out.println("setModel(model) -> "+jtbListaProducto.setModel(model));
       jtbListaProducto.setModel(model);
    }
    
    
    
      public Modulo getVentanaAbierta() {
        return ventanaAbierta;
    }

    
    public void setVentanaAbierta(Modulo ventanaAbierta) {
        this.ventanaAbierta = ventanaAbierta;
    }
    
    
    
    public enum Modulo {

        AGREGAR(1, "actionAgregar"),
        MODIFICAR2(2, "actionModificar"),
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbListaProducto;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTextField txtProductos;
    // End of variables declaration//GEN-END:variables

     public javax.swing.JTextField getTextField1() {
    String marmol=txtProductos.getText();
        return txtProductos;
    }
     

     
}