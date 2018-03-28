package PuntoVenta.Ventanas;

import PuntoVenta.Inicio.MenuPrincipal;
import ClasesExtendidas.Tablas.ArrayListTableModel;
import ClasesExtendidas.Tablas.EstadoCajaTableModel;
import Utilidades.Globales;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.TableRowSorter;

public class Caja extends javax.swing.JInternalFrame {

    public MenuPrincipal menuPrincipal;

    public String fecha;
    static DecimalFormat nf = new DecimalFormat("###,###,###,###.00");

    private CorteCaja ventanaCorte;
    private CierreCaja ventanaCierre;

    /**
     *
     * @param menuPrincipal
     */
    public Caja(MenuPrincipal menuPrincipal) {
        initComponents();
        this.menuPrincipal = menuPrincipal;
        this.setTitle("Saphiro - Gestión de la caja N°" + menuPrincipal.getModeloCaja().getId());

        actualizarTabla();

        crearHotKeys();
    }

    /**
     * Método para actualizar el contenido de la tabla en la ventana caja.
     */
    public void actualizarTabla() {
        ArrayList estadoCajaArrayList = menuPrincipal.getOBD().getArrayListEstadoCaja(Integer.parseInt(menuPrincipal.getConfiguracion().getProperty("id_caja"))); 
        EstadoCajaTableModel estadoCajaTableModel = new EstadoCajaTableModel(estadoCajaArrayList);
        jtbResultadoBusqueda.setModel(estadoCajaTableModel);
        
        setBotonesCajaEnabled(menuPrincipal.isCajaAbierta());

        jtbResultadoBusqueda.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    /**
     * Cierra la ventana con dispose().
     */
    private void cerrarVentana() {
        this.dispose();
    }

    private void crearHotKeys() {
        Action actAbrirCaja = new AbstractAction("actionAbrirCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirCaja();
            }
        };
        Action actCerrarCaja = new AbstractAction("actionCerrarCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEstadoCaja(false);
            }
        };
        Action actCorteCaja = new AbstractAction("actionCorteCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaCorteCaja();
            }
        };
        Action actFlujoCaja = new AbstractAction("actionFlujoCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
//                verFlujoCaja();
            }
        };
        Action actImprimirCaja = new AbstractAction("actionImprimirCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirFacturaCaja();
            }
        };
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

        actCerrarVentana.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        actFocusPuntoInteres.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        actAbrirCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        actCerrarCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        actCorteCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        actFlujoCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        actImprimirCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));

        btnAbrirCaja.getActionMap().put("actionAbrirCaja", actAbrirCaja);
        btnAbrirCaja.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAbrirCaja.getValue(Action.ACCELERATOR_KEY), "actionAbrirCaja");

        btnCerrarCaja.getActionMap().put("actionCerrarCaja", actCerrarCaja);
        btnCerrarCaja.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarCaja.getValue(Action.ACCELERATOR_KEY), "actionCerrarCaja");

        btnCorte.getActionMap().put("actionCorteCaja", actCorteCaja);
        btnCorte.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCorteCaja.getValue(Action.ACCELERATOR_KEY), "actionCorteCaja");

        btnFlujoCaja.getActionMap().put("actionFlujoCaja", actFlujoCaja);
        btnFlujoCaja.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFlujoCaja.getValue(Action.ACCELERATOR_KEY), "actionFlujoCaja");

        btnImprimir.getActionMap().put("actionImprimirCaja", actImprimirCaja);
        btnImprimir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actImprimirCaja.getValue(Action.ACCELERATOR_KEY), "actionImprimirCaja");

        pnlContenedor.getActionMap().put("actionCerrarVentanaCaja", actCerrarVentana);
        pnlContenedor.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarVentana.getValue(Action.ACTION_COMMAND_KEY), "actionCerrarVentanaCaja");

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

    /**
     * Metodo para abrir o cerrar cajas.
     *
     * @param aFlag True para abrir una caja, false para cerrarla.
     */
    public void setEstadoCaja(boolean aFlag) {
        if (aFlag && !menuPrincipal.isCajaAbierta()) {
            boolean aux;
            String montoInicial = "";
            do {
                aux = false;
                montoInicial = JOptionPane.showInputDialog(this, "Monto inicial", 0);
                String partes[] = montoInicial.split("");
                String numero = "1234567890";
                for (String parte : partes) {
                    if (!numero.contains(parte)) {
                        aux = true;
                    }
                }
            } while (aux);
            if (montoInicial.matches(Globales.patronCantidad)) {
                int idEstadoCaja = menuPrincipal.getOBD().setEstadoCaja(
                        0,
                        menuPrincipal.getModeloCaja().getId(),
                        menuPrincipal.getEmpleado().getId(),
                        montoInicial);
                actualizarTabla();
                menuPrincipal.setIdEstadoCaja(idEstadoCaja);
                menuPrincipal.setEstadoCaja(aFlag);
                menuPrincipal.setBotonesMenuPrincipalEnabled(aFlag);
                setBotonesCajaEnabled(aFlag);
                if (jtbResultadoBusqueda.getSelectedRow() > 0) {
                    btnImprimir.setEnabled(!aFlag);
                }
            } else {
                Utilidades.Sonidos.beep();
            }
        } else if (!aFlag && menuPrincipal.isCajaAbierta()) {
            jtbResultadoBusqueda.setRowSelectionInterval(0, 0);

            abrirVentanaCierre();
        } else {
            Utilidades.Sonidos.beep();
        }
    }
    
    /**
     * Abre la caja creando un nuevo estado de caja
     */
    public void abrirCaja() {
        String montoApertura;
        
        if(!menuPrincipal.isCajaAbierta()){
            do {
                montoApertura = JOptionPane.showInputDialog(this, "Monto de apertura", 0);
            } while (!montoApertura.matches(Globales.patronCantidad));
            
            int idEstadoCaja = menuPrincipal.getOBD().abrirCaja(menuPrincipal.getModeloCaja().getId(),
                                                                menuPrincipal.getEmpleado().getId(),
                                                                montoApertura);
            
            if(idEstadoCaja >= 0){
                actualizarTabla();
                menuPrincipal.setIdEstadoCaja(idEstadoCaja);
                menuPrincipal.setEstadoCaja(true);
                menuPrincipal.setBotonesMenuPrincipalEnabled(true);
                setBotonesCajaEnabled(true);
                if (jtbResultadoBusqueda.getSelectedRow() > 0) {
                    btnImprimir.setEnabled(false);
                }
            }
        }
    }
    
    /**
     * Crea un corte de caja.
     */
    public void abrirVentanaCorteCaja() {
        if (menuPrincipal.isCajaAbierta()) {
            if (ventanaCorte != null) {
                JOptionPane.showMessageDialog(this, "La ventana ya está abierta");
            } 
            else {
                ventanaCorte = new CorteCaja(this);

                Dimension desktopSize = menuPrincipal.panel.getSize();
                Dimension jInternalFrameSize = ventanaCorte.getSize();
                ventanaCorte.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                                         (desktopSize.height - jInternalFrameSize.height) / 2);
                menuPrincipal.panel.add(ventanaCorte);
                ventanaCorte.show();
            }
        } else {
            Utilidades.Sonidos.beep();
        }
    }

    private void abrirVentanaCierre() {
        if (menuPrincipal.isCajaAbierta() && menuPrincipal.estacerrado(ventanaCierre)) {
            ventanaCierre = new CierreCaja(this);
            Dimension desktopSize = menuPrincipal.panel.getSize();
            Dimension jInternalFrameSize = ventanaCierre.getSize();
            ventanaCierre.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                                (desktopSize.height - jInternalFrameSize.height) / 2);
            menuPrincipal.panel.add(ventanaCierre);
            ventanaCierre.show();
        }
    }

    private void abrirVentanaFlujoCaja() {
        jtbResultadoBusqueda.setRowSelectionInterval(0, 0);
        if (menuPrincipal.isCajaAbierta()) {
            //Cual es la pantalla para ver el flujo de la caja
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenedor = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtFiltro = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbResultadoBusqueda = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnAbrirCaja = new javax.swing.JButton();
        btnCerrarCaja = new javax.swing.JButton();
        btnFlujoCaja = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnCorte = new javax.swing.JButton();

        setClosable(true);
        // revisar funcionamiento de este bloque
      /*  addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
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
        });*/

        pnlContenedor.setBackground(new java.awt.Color(32, 182, 155));
        pnlContenedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(172, 162, 162), null, null));
        //jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ///jPanel1.setBackground(new java.awt.Color(-15589839));

        jPanel3.setBackground(new java.awt.Color(32, 182, 155));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Busqueda", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 15), java.awt.Color.white)); // NOI18N
        //jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        //jPanel3.setBackground(new java.awt.Color(-15589839));

        txtFiltro.setNextFocusableComponent(jtbResultadoBusqueda);
        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });
        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setAutoscrolls(true);

        jtbResultadoBusqueda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtbResultadoBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtbResultadoBusqueda.setNextFocusableComponent(btnAbrirCaja);
        jScrollPane1.setViewportView(jtbResultadoBusqueda);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addComponent(txtFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addGap(189, 189, 189))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(35, 109, 215));
        jPanel4.setOpaque(false);
        //jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        //jPanel4.setBackground(new java.awt.Color(-15589839));

        btnAbrirCaja.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnAbrirCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/Tips.png"))); // NOI18N
        btnAbrirCaja.setText("<html><font size=4><center>Abrir<br>F2</center></font></html>");
        btnAbrirCaja.setActionCommand("actionAbrirCaja");
        btnAbrirCaja.setMaximumSize(new java.awt.Dimension(150, 45));
        btnAbrirCaja.setMinimumSize(new java.awt.Dimension(150, 45));
        btnAbrirCaja.setNextFocusableComponent(btnCerrarCaja);
        btnAbrirCaja.setPreferredSize(new java.awt.Dimension(150, 45));
        btnAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajaActionPerformed(evt);
            }
        });

        btnCerrarCaja.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnCerrarCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/cancel.png"))); // NOI18N
        btnCerrarCaja.setText("<html><font size=4><center>Cerrar<br>F3</center></font></html>");
        btnCerrarCaja.setActionCommand("actionCerrarCaja");
        btnCerrarCaja.setMaximumSize(new java.awt.Dimension(150, 45));
        btnCerrarCaja.setMinimumSize(new java.awt.Dimension(150, 45));
        btnCerrarCaja.setNextFocusableComponent(btnFlujoCaja);
        btnCerrarCaja.setPreferredSize(new java.awt.Dimension(150, 45));
        btnCerrarCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCajaActionPerformed(evt);
            }
        });

        btnFlujoCaja.setVisible(false);
        btnFlujoCaja.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnFlujoCaja.setText("<html><font size=4><center>Ver Flujo<br>F5</center></font></html>");
        btnFlujoCaja.setActionCommand("actionFlujoCaja");
        btnFlujoCaja.setEnabled(false);
        btnFlujoCaja.setMaximumSize(new java.awt.Dimension(150, 45));
        btnFlujoCaja.setMinimumSize(new java.awt.Dimension(150, 45));
        btnFlujoCaja.setNextFocusableComponent(btnImprimir);
        btnFlujoCaja.setPreferredSize(new java.awt.Dimension(150, 45));
        btnFlujoCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFlujoCajaActionPerformed(evt);
            }
        });

        btnImprimir.setVisible(false);
        btnImprimir.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnImprimir.setText("<html><font size=4><center>Imprimir<br>F11</center></font></html>");
        btnImprimir.setMaximumSize(new java.awt.Dimension(150, 45));
        btnImprimir.setMinimumSize(new java.awt.Dimension(150, 45));
        btnImprimir.setNextFocusableComponent(txtFiltro);
        btnImprimir.setPreferredSize(new java.awt.Dimension(150, 45));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnCorte.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        btnCorte.setText("<html><font size=4><center>Corte<br>F4</center></font></html>");
        btnCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAbrirCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnFlujoCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrarCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 111, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCorte, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCorte)
                        .addComponent(btnFlujoCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbrirCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCerrarCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(pnlContenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCajaActionPerformed
        abrirCaja();
    }//GEN-LAST:event_btnAbrirCajaActionPerformed

    private void btnFlujoCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFlujoCajaActionPerformed
//        verFlujoCaja();
    }//GEN-LAST:event_btnFlujoCajaActionPerformed

    private void btnCerrarCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCajaActionPerformed
        setEstadoCaja(false);
    }//GEN-LAST:event_btnCerrarCajaActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        imprimirFacturaCaja();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        String filtro = txtFiltro.getText();
        TableRowSorter sorter = new TableRowSorter(jtbResultadoBusqueda.getModel());
        sorter.setRowFilter(RowFilter.regexFilter(filtro));
        jtbResultadoBusqueda.setRowSorter(sorter);
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void btnCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteActionPerformed
        abrirVentanaCorteCaja();
    }//GEN-LAST:event_btnCorteActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        focusResultado();
    }//GEN-LAST:event_txtFiltroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAbrirCaja;
    private javax.swing.JButton btnCerrarCaja;
    private javax.swing.JButton btnCorte;
    private javax.swing.JButton btnFlujoCaja;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbResultadoBusqueda;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables

    protected void setBotonesCajaEnabled(boolean aFlag) {
        btnAbrirCaja.setEnabled(!aFlag);
        btnCerrarCaja.setEnabled(aFlag);
        btnCorte.setEnabled(aFlag);
        btnImprimir.setEnabled(!aFlag);
//        btnFlujoCaja.setEnabled(aFlag);
    }

    private void imprimirFacturaCaja() {
        if (jtbResultadoBusqueda.getSelectedRow() >= 0) {
            int r = Integer.parseInt(jtbResultadoBusqueda.getValueAt(jtbResultadoBusqueda.getSelectedRow(), 0).toString().trim());
            //btnImprimir.setEnabled(true);

//    ctrl.VerFacturaCaja(conf, r);
        }
    }

    /**
     * @return the tblResultadoBusqueda
     */
    public javax.swing.JTable getTblResultadoBusqueda() {
        return jtbResultadoBusqueda;
    }

    /**
     * @return the txtFiltro
     */
    public javax.swing.JTextField getTxtFiltro() {
        return txtFiltro;
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
}
