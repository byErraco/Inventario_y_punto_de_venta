package PuntoVenta.Ventanas;

import ClasesExtendidas.Tablas.ArrayListTableModel;
import ClasesExtendidas.Tablas.VentaTableModel;
import PuntoVenta.Inicio.MenuPrincipal;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
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
        btnImprimirFlag();
    }

    /**
     * Cierra la ventana con dispose().
     */
    private void LlenarTabla() {
        ArrayList ListaFactura = menuPrincipal.getOBD().getArrayListFactura();
        VentaTableModel model = new VentaTableModel(ListaFactura);
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
                cerrarVentana();
            }
        };
        
        Action actImprimirCaja = new AbstractAction("actionImprimirCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    reimprimir();
                } catch (IOException ex) {
                    Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PrintException ex) {
                    Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Action actFocusPuntoInteres = new AbstractAction("actionFocusPuntoInteres") {
            @Override
            public void actionPerformed(ActionEvent e) {
                focusPuntoInteres();
            }
        };
        
        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        actFocusPuntoInteres.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        actImprimirCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
        
        pnlContenedor.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

        btnImprimir.getActionMap().put("actionImprimirCaja", actImprimirCaja);
        btnImprimir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actImprimirCaja.getValue(Action.ACCELERATOR_KEY), "actionImprimirCaja");

        pnlContenedor.getActionMap().put("actionFocusPuntoInteres", actFocusPuntoInteres);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFocusPuntoInteres.getValue(Action.ACTION_COMMAND_KEY), "actionFocusPuntoInteres");
        
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

    
    private void cerrarVentana() {
        this.dispose();
    }
    
    /** Konstanza: 
     * Nueva funcion
     * Cambia el focus a 'jtbResultadoBusqueda'
     */
    private void focusResultado() {
        if (jtbResultadoBusqueda.getRowCount() > 0) {
            jtbResultadoBusqueda.requestFocus();
            jtbResultadoBusqueda.setRowSelectionInterval(0, 0);
        }
    }
    
    private void focusPuntoInteres() {
        if (!this.txtFiltro.hasFocus()) {
            this.txtFiltro.requestFocus();
            this.txtFiltro.setSelectionStart(0);
            this.txtFiltro.setSelectionEnd(this.txtFiltro.getText().length());
        } else {
            focusResultado();
        }
    }

    public void btnImprimirFlag() {
        if(jtbResultadoBusqueda.getSelectedRow() >= 0) {
            btnImprimir.setEnabled(true);
        } else {
            btnImprimir.setEnabled(false);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbResultadoBusqueda = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
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

        pnlContenedor.setBackground(new java.awt.Color(32, 182, 155));
        pnlContenedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(172, 162, 162), null, null));
        //jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ///jPanel1.setBackground(new java.awt.Color(-15589839));

        jPanel3.setBackground(new java.awt.Color(32, 182, 155));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(45, 178, 152), 1, true), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(45, 178, 152))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        //jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        //jPanel3.setBackground(new java.awt.Color(-15589839));

        txtFiltro.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtFiltro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
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

        jtbResultadoBusqueda.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
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
        // evento que habilita o deshabilita el boton de imprimir segun la fila seleccionada
        jtbResultadoBusqueda.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                btnImprimirFlag();
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(28, 90, 125));
        jLabel1.setText("<html><font size=4><center>Búsqueda:<br></font></center></html>");

        btnImprimir.setBackground(new java.awt.Color(45, 178, 152));
        btnImprimir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnImprimir.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimir.setText("<html><font size=4><center>Imprimir<br>F11</center></font></html>");
        btnImprimir.setBorder(null);
        btnImprimir.setMaximumSize(new java.awt.Dimension(150, 45));
        btnImprimir.setMinimumSize(new java.awt.Dimension(150, 45));
        btnImprimir.setNextFocusableComponent(txtFiltro);
        btnImprimir.setPreferredSize(new java.awt.Dimension(150, 45));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(32, 182, 155));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/cuadro-700x400.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(310, 310, 310))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(pnlContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            reimprimir();
        } catch (IOException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrintException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            try {
                reimprimir();
            } catch (IOException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PrintException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jtbResultadoBusquedaKeyPressed
    private void reimprimir() throws IOException, PrintException {
        if (jtbResultadoBusqueda.getSelectedRow() >= 0) {
            String codigoBarra = jtbResultadoBusqueda.getValueAt(jtbResultadoBusqueda.getSelectedRow(), 0).toString();
            for (int i = 0; i < codigoBarra.length(); i++) {
                if (codigoBarra.charAt(i) != 0) {
                    codigoBarra = codigoBarra.substring(i, codigoBarra.length());
                }
                break;
            }
            
            /*
             * Parte que enviar la informacion a la impresora
             *   
            */
//            File reporteFile = menuPrincipal.getOBD().reimprimirReporte(codigoBarra, "reporte_venta", "venta", "id_venta", "reporteVenta");
            byte[] reporteBytea = menuPrincipal.getOBD().reimprimirReporte(codigoBarra, "reporte_venta", "venta", "id_venta", "reporteVenta");
            // TODO Auto-generated method stub
            String texto = "Esto es lo que va a la impresora";

            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            DocPrintJob docPrintJob = printService.createPrintJob();
            Doc doc = new SimpleDoc(reporteBytea, flavor, null);
            try {
                    docPrintJob.print(doc, null);
            } catch (PrintException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            
            
//            Desktop.getDesktop().open(reporteFile);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbResultadoBusqueda;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables

}
