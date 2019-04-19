package PuntoVenta.Ventanas;

import ClasesExtendidas.Numeros.XBigDecimal;
import PuntoVenta.Inicio.MenuPrincipal;
import ClasesExtendidas.Tablas.ArrayListTableModel;
import ClasesExtendidas.Tablas.EstadoCajaTableModel;
import Utilidades.Globales;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Caja extends javax.swing.JInternalFrame {

    public MenuPrincipal menuPrincipal;

    public String fecha;
    static DecimalFormat nf = new DecimalFormat("###,###,###,###.00");

    private CorteCaja ventanaCorte;
    private CierreCaja ventanaCierre;
    private Cortes ventanaHistorialCortes;
    
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
        DefaultTableModel table = (DefaultTableModel)jtbResultadoBusqueda.getModel();
        table.setRowCount(0);
        
        ArrayList estadoCajaArrayList = menuPrincipal.getOBD().getArrayListEstadoCaja(Integer.parseInt(menuPrincipal.getConfiguracion().getProperty("id_caja")), ""); 
        EstadoCajaTableModel estadoCajaTableModel = new EstadoCajaTableModel(estadoCajaArrayList)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
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
                mostrarCerradas();
            }
        };
        Action actHistorialCortesCaja = new AbstractAction("actionHistorialCortesCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaHistorialCortes();
            }
        };
        Action actImprimirCaja = new AbstractAction("actionImprimirCaja") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    imprimirFacturaCaja();
                } catch (IOException ex) {
                    Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
                }
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
        actHistorialCortesCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
        actImprimirCaja.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));

        btnAbrirCaja.getActionMap().put("actionAbrirCaja", actAbrirCaja);
        btnAbrirCaja.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actAbrirCaja.getValue(Action.ACCELERATOR_KEY), "actionAbrirCaja");

        btnCerrarCaja.getActionMap().put("actionCerrarCaja", actCerrarCaja);
        btnCerrarCaja.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCerrarCaja.getValue(Action.ACCELERATOR_KEY), "actionCerrarCaja");

        btnCorte.getActionMap().put("actionCorteCaja", actCorteCaja);
        btnCorte.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actCorteCaja.getValue(Action.ACCELERATOR_KEY), "actionCorteCaja");

        mostrarCerradas.getActionMap().put("actionFlujoCaja", actFlujoCaja);
        mostrarCerradas.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actFlujoCaja.getValue(Action.ACCELERATOR_KEY), "actionFlujoCaja");
        
        btnCortes.getActionMap().put("actionHistorialCortesCaja", actHistorialCortesCaja);
        btnCortes.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actHistorialCortesCaja.getValue(Action.ACCELERATOR_KEY), "actionHistorialCortesCaja");

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
//                if (jtbResultadoBusqueda.getSelectedRow() > 0) {
//                    btnImprimir.setEnabled(!aFlag);
//                }
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
            System.out.println("montoApertura: " + montoApertura);
            
            int idEstadoCaja = menuPrincipal.getOBD().abrirCaja(menuPrincipal.getModeloCaja().getId(),
                                                                menuPrincipal.getEmpleado().getId(),
                                                                montoApertura);
            
            if(idEstadoCaja >= 0){
                actualizarTabla();
                menuPrincipal.setIdEstadoCaja(idEstadoCaja);
                menuPrincipal.setEstadoCaja(true);
                menuPrincipal.setBotonesMenuPrincipalEnabled(true);
                setBotonesCajaEnabled(true);
//                if (jtbResultadoBusqueda.getSelectedRow() > 0) {
//                    btnImprimir.setEnabled(false);
//                }
            }
        }
    }
    
    public void btnCortesFlag() {
        if(jtbResultadoBusqueda.getSelectedRow() >= 0) {
            btnCortes.setEnabled(true);
        } else {
            btnCortes.setEnabled(false);
        }
    }
    
    public void btnImprimirFlag() {
        if(jtbResultadoBusqueda.getSelectedRow() >= 0 && jtbResultadoBusqueda.getValueAt(jtbResultadoBusqueda.getSelectedRow(), 3).toString().equals("Cerrada")) {
            btnImprimir.setEnabled(true);
        } else {
            btnImprimir.setEnabled(false);
        }
    }
    
    /**
     * Crea un corte de caja.
     */
    public void abrirVentanaCorteCaja() {
        int idCaja = menuPrincipal.getModeloCaja().getId();
        int idEstadoCaja = menuPrincipal.getOBD().getIdEstadoCaja(idCaja);
        List<Integer> ventas = menuPrincipal.getOBD().getListIDVentas(idEstadoCaja);
        
        XBigDecimal[] totalVentas = menuPrincipal.getOBD().getTotalEstadoCaja(menuPrincipal.getIdEstadoCaja(),
                " AND corte_realizado = 0");
        
        if (menuPrincipal.isCajaAbierta()) {
            if (!menuPrincipal.estacerrado(ventanaCorte)) {
                JOptionPane.showMessageDialog(this, "La ventana ya está abierta");
            } else if(ventas.size() <= 0) {
                JOptionPane.showMessageDialog(this, "Aun no has vendido nada");
            } else if(totalVentas[4].compareTo(new BigDecimal(0)) >= 0) {
                JOptionPane.showMessageDialog(this, "Tiene sus cortes al día");
            } else {
                ventanaCorte = new CorteCaja(this);

                Dimension desktopSize = menuPrincipal.panel.getSize();
                Dimension jInternalFrameSize = ventanaCorte.getSize();
                ventanaCorte.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                                         (desktopSize.height - jInternalFrameSize.height) / 2);
                menuPrincipal.panel.add(ventanaCorte);
                this.dispose();
                ventanaCorte.show();
            }
        } else {
            Utilidades.Sonidos.beep();
        }
    }

    public void abrirVentanaHistorialCortes() {
        if(menuPrincipal.estacerrado(ventanaHistorialCortes)) {
            if(jtbResultadoBusqueda.getSelectedRow() >= 0) {
                ventanaHistorialCortes = new Cortes(this, 
                        Integer.parseInt(jtbResultadoBusqueda.getValueAt(jtbResultadoBusqueda.getSelectedRow(), 0).toString().trim())
                );

                Dimension desktopSize = menuPrincipal.panel.getSize();
                Dimension jInternalFrameSize = ventanaHistorialCortes.getSize();
                ventanaHistorialCortes.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                                         (desktopSize.height - jInternalFrameSize.height) / 2);
                menuPrincipal.panel.add(ventanaHistorialCortes);
                this.dispose();
                ventanaHistorialCortes.show();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione algún resultado de la tabla");     
            }
        } else {
            JOptionPane.showMessageDialog(this, "La ventana ya está abierta");
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
            this.dispose();
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
        jPanel4 = new javax.swing.JPanel();
        btnCortes = new javax.swing.JButton();
        btnCorte = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnCerrarCaja = new javax.swing.JButton();
        btnFlujoCaja = new javax.swing.JButton();
        btnAbrirCaja = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbResultadoBusqueda = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        mostrarCerradas = new javax.swing.JCheckBox();

        setClosable(true);

        pnlContenedor.setBackground(new java.awt.Color(32, 182, 155));
        pnlContenedor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(172, 162, 162), null, null));
        //jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ///jPanel1.setBackground(new java.awt.Color(-15589839));

        jPanel4.setBackground(new java.awt.Color(153, 255, 51));
        jPanel4.setOpaque(false);
        //jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        //jPanel4.setBackground(new java.awt.Color(-15589839));

        btnCortes.setBackground(new java.awt.Color(45, 178, 152));
        btnCortes.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCortes.setForeground(new java.awt.Color(255, 255, 255));
        btnCortes.setText("<html><font size=4><center>Cortes<br>F6</center></font></html>");
        btnCortes.setBorder(null);
        btnCortes.setPreferredSize(new java.awt.Dimension(150, 45));
        btnCortes.setEnabled(false);
        btnCortes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCortesActionPerformed(evt);
            }
        });

        btnCorte.setBackground(new java.awt.Color(45, 178, 152));
        btnCorte.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCorte.setForeground(new java.awt.Color(255, 255, 255));
        btnCorte.setText("<html><font size=4><center>Corte<br>F4</center></font></html>");
        btnCorte.setBorder(null);
        btnCorte.setPreferredSize(new java.awt.Dimension(150, 45));
        btnCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorteActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(45, 178, 152));
        btnImprimir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnImprimir.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimir.setText("<html><font size=4><center>Imprimir<br>F11</center></font></html>");
        btnImprimir.setBorder(null);
        btnImprimir.setMaximumSize(new java.awt.Dimension(150, 45));
        btnImprimir.setMinimumSize(new java.awt.Dimension(150, 45));
        btnImprimir.setNextFocusableComponent(txtFiltro);
        btnImprimir.setPreferredSize(new java.awt.Dimension(150, 45));
        btnImprimir.setEnabled(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnCerrarCaja.setBackground(new java.awt.Color(45, 178, 152));
        btnCerrarCaja.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCerrarCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarCaja.setText("<html><font size=4><center>Cerrar<br>F3</center></font></html>");
        btnCerrarCaja.setActionCommand("actionCerrarCaja");
        btnCerrarCaja.setBorder(null);
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
        btnFlujoCaja.setBackground(new java.awt.Color(45, 178, 152));
        btnFlujoCaja.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnFlujoCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnFlujoCaja.setText("<html><font size=4><center>Ver Flujo<br>F5</center></font></html>");
        btnFlujoCaja.setActionCommand("actionFlujoCaja");
        btnFlujoCaja.setBorder(null);
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

        btnAbrirCaja.setBackground(new java.awt.Color(45, 178, 152));
        btnAbrirCaja.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAbrirCaja.setForeground(new java.awt.Color(255, 255, 255));
        btnAbrirCaja.setText("<html><font size=4><center>Abrir<br>F2</center></font></html>");
        btnAbrirCaja.setActionCommand("actionAbrirCaja");
        btnAbrirCaja.setBorder(null);
        btnAbrirCaja.setMaximumSize(new java.awt.Dimension(150, 45));
        btnAbrirCaja.setMinimumSize(new java.awt.Dimension(150, 45));
        btnAbrirCaja.setNextFocusableComponent(btnCerrarCaja);
        btnAbrirCaja.setPreferredSize(new java.awt.Dimension(150, 45));
        btnAbrirCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCajaActionPerformed(evt);
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
        // evento que habilita o deshabilita el boton de imprimir segun la fila seleccionada
        jtbResultadoBusqueda.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                btnImprimirFlag();
                btnCortesFlag();
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PuntoVenta/Iconos/iconos p_v 24x24/fondo-blanco 900x500.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(28, 90, 125));
        jLabel1.setText("<html><font size=4><center>Búsqueda:<br></font></center></html>");

        txtFiltro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(32, 182, 155), 1, true));
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

        mostrarCerradas.setSelected(true);
        mostrarCerradas.setBackground(new java.awt.Color(255, 255, 255));
        mostrarCerradas.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        mostrarCerradas.setText("<html><font size=4><center>Mostrar cerradas</center></font></html>");
        mostrarCerradas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mostrarCerradasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(mostrarCerradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnAbrirCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnFlujoCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCerrarCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCortes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mostrarCerradas)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrirCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFlujoCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrarCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCortes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenedorLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenedorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
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
        try {
            imprimirFacturaCaja();
        } catch (IOException ex) {
            Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        String filtro = txtFiltro.getText();
        TableRowSorter sorter = new TableRowSorter(jtbResultadoBusqueda.getModel());
        List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>(2);
        if(!mostrarCerradas.isSelected()) {
            filters.add(RowFilter.regexFilter("Abierta", 3));
        }
        filters.add(RowFilter.regexFilter(filtro));
        sorter.setRowFilter(RowFilter.andFilter(filters));
        jtbResultadoBusqueda.setRowSorter(sorter);
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void btnCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorteActionPerformed
        abrirVentanaCorteCaja();
    }//GEN-LAST:event_btnCorteActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        focusResultado();
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void btnCortesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCortesActionPerformed
        abrirVentanaHistorialCortes();
    }//GEN-LAST:event_btnCortesActionPerformed

    private void mostrarCerradasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mostrarCerradasItemStateChanged
        mostrarCerradasEvt();
    }//GEN-LAST:event_mostrarCerradasItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAbrirCaja;
    private javax.swing.JButton btnCerrarCaja;
    private javax.swing.JButton btnCorte;
    private javax.swing.JButton btnCortes;
    private javax.swing.JButton btnFlujoCaja;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbResultadoBusqueda;
    private javax.swing.JCheckBox mostrarCerradas;
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables

    protected void setBotonesCajaEnabled(boolean aFlag) {
        btnAbrirCaja.setEnabled(!aFlag);
        btnCerrarCaja.setEnabled(aFlag);
        btnCorte.setEnabled(aFlag);
//        btnFlujoCaja.setEnabled(aFlag);
    }
    
    public void mostrarCerradas() {
        focusResultado();
        mostrarCerradas.setSelected(!mostrarCerradas.isSelected());
    }

//    private void imprimirFacturaCaja() {
//        if (jtbResultadoBusqueda.getSelectedRow() >= 0) {
//            int r = Integer.parseInt(jtbResultadoBusqueda.getValueAt(jtbResultadoBusqueda.getSelectedRow(), 0).toString().trim());
//            btnImprimir.setEnabled(true);
//
////    ctrl.VerFacturaCaja(conf, r);
//
//
//        }
//    }
    
    public void mostrarCerradasEvt() {
        if(!mostrarCerradas.isSelected()) {
            TableRowSorter sorter = new TableRowSorter(jtbResultadoBusqueda.getModel());
            sorter.setRowFilter(RowFilter.regexFilter("Abierta", 3));
            jtbResultadoBusqueda.setRowSorter(sorter);
        } else {
            TableRowSorter sorter = new TableRowSorter(jtbResultadoBusqueda.getModel());
            sorter.setRowFilter(RowFilter.regexFilter(""));
            jtbResultadoBusqueda.setRowSorter(sorter);
        }
    }
    
    private void imprimirFacturaCaja() throws IOException {
        if (jtbResultadoBusqueda.getSelectedRow() >= 0) {
            String codigoBarra = jtbResultadoBusqueda.getValueAt(jtbResultadoBusqueda.getSelectedRow(), 0).toString();
            for (int i = 0; i < codigoBarra.length(); i++) {
                if (codigoBarra.charAt(i) != 0) {
                    codigoBarra = codigoBarra.substring(i, codigoBarra.length());
                }
                break;
            }

            byte[] reporteBytea = menuPrincipal.getOBD().reimprimirReporte(codigoBarra, "reporte_cierre", "cierre_caja", "id_estado_caja", "reporteCierre");
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
