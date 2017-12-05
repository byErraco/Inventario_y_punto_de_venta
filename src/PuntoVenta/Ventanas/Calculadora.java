/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.Ventanas;

import PuntoVenta.Inicio.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author inverdata
 */
public class Calculadora extends javax.swing.JInternalFrame {

    /**
     * Creates new form Calculadora
     */
    MenuPrincipal menu;

    public Calculadora(MenuPrincipal menu) {
        this.menu = menu;
        initComponents();
        this.setTitle("Calculadora");

        Action actBtnIgual = new AbstractAction("btnIgualAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
 
                btnIgualActionPerformed(e);
            }
        };
        
        Action actBtnSumar = new AbstractAction("btnSumarAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSumarActionPerformed(e);
            }
        };
        
        Action actBtnRestar = new AbstractAction("btnRestarAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRestarActionPerformed(e);
            }
        };
        
        Action actBtnMultiplicar = new AbstractAction("btnMultiplicarAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnMultiplicarActionPerformed(e);
            }
        };
        
        Action actBtnDividir = new AbstractAction("btnDividirAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDividirActionPerformed(e);
            }
        };
        
        Action actBtnUno = new AbstractAction("btnUnoAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUnoActionPerformed(e);
            }
        };
        
        Action actBtnDos = new AbstractAction("btnDosAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDosActionPerformed(e);
            }
        };
        
        Action actBtnTres = new AbstractAction("btnTresAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnTresActionPerformed(e);
            }
        };
        
        Action actBtnCuatro = new AbstractAction("btnCuatroAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCuatroActionPerformed(e);
            }
        };
        
        Action actBtnCinco = new AbstractAction("btnCincoAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCincoActionPerformed(e);
            }
        };
        
        Action actBtnSeis = new AbstractAction("btnSeisAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSeisActionPerformed(e);
            }
        };
        
        Action actBtnSiete = new AbstractAction("btnSieteAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSieteActionPerformed(e);
            }
        };
        
        Action actBtnOcho = new AbstractAction("btnOchoAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOchoActionPerformed(e);
            }
        };
        
        Action actBtnNueve = new AbstractAction("btnNueveAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNueveActionPerformed(e);
            }
        };
        
        Action actBtnCero = new AbstractAction("btnCeroAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCeroActionPerformed(e);
            }
        };
        
        Action actBtnPunto = new AbstractAction("btnPuntoAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPuntoActionPerformed(e);
            }
        };
        
        Action actBtnCE = new AbstractAction("btnCEAction") {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCEActionPerformed(e);
            }
        };
        

      
        actBtnIgual.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        jPanel1.getActionMap().put("btnIgualAction", actBtnIgual);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnIgual.getValue(Action.ACTION_COMMAND_KEY), "btnIgualAction");
        
        actBtnSumar.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0));
        jPanel1.getActionMap().put("btnSumarAction", actBtnSumar);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnSumar.getValue(Action.ACTION_COMMAND_KEY), "btnSumarAction");
        
        actBtnRestar.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0));
        jPanel1.getActionMap().put("btnRestarAction", actBtnRestar);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnRestar.getValue(Action.ACTION_COMMAND_KEY), "btnRestarAction");
        
        actBtnMultiplicar.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0));
        jPanel1.getActionMap().put("btnMultiplicarAction", actBtnMultiplicar);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnMultiplicar.getValue(Action.ACTION_COMMAND_KEY), "btnMultiplicarAction");
        
        actBtnDividir.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0));
        jPanel1.getActionMap().put("btnDividirAction", actBtnDividir);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnDividir.getValue(Action.ACTION_COMMAND_KEY), "btnDividirAction");
        
        actBtnUno.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0));
        jPanel1.getActionMap().put("btnUnoAction", actBtnUno);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnUno.getValue(Action.ACTION_COMMAND_KEY), "btnUnoAction");
        
        actBtnDos.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0));
        jPanel1.getActionMap().put("btnDosAction", actBtnDos);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnDos.getValue(Action.ACTION_COMMAND_KEY), "btnDosAction");
        
        actBtnTres.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0));
        jPanel1.getActionMap().put("btnTresAction", actBtnTres);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnTres.getValue(Action.ACTION_COMMAND_KEY), "btnTresAction");
        
        actBtnCuatro.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0));
        jPanel1.getActionMap().put("btnCuatroAction", actBtnCuatro);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnCuatro.getValue(Action.ACTION_COMMAND_KEY), "btnCuatroAction");
        
        actBtnCinco.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0));
        jPanel1.getActionMap().put("btnCincoAction", actBtnCinco);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnCinco.getValue(Action.ACTION_COMMAND_KEY), "btnCincoAction");
        
        actBtnSeis.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0));
        jPanel1.getActionMap().put("btnSeisAction", actBtnSeis);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnSeis.getValue(Action.ACTION_COMMAND_KEY), "btnSeisAction");
        
        actBtnSiete.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0));
        jPanel1.getActionMap().put("btnSieteAction", actBtnSiete);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnSiete.getValue(Action.ACTION_COMMAND_KEY), "btnSieteAction");
        
        actBtnOcho.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0));
        jPanel1.getActionMap().put("btnOchoAction", actBtnOcho);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnOcho.getValue(Action.ACTION_COMMAND_KEY), "btnOchoAction");
        
        actBtnNueve.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0));
        jPanel1.getActionMap().put("btnNueveAction", actBtnNueve);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnNueve.getValue(Action.ACTION_COMMAND_KEY), "btnNueveAction");
        
        actBtnCero.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0));
        jPanel1.getActionMap().put("btnCeroAction", actBtnCero);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnCero.getValue(Action.ACTION_COMMAND_KEY), "btnCeroAction");
        
        actBtnIgual.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        jPanel1.getActionMap().put("btnIgualAction", actBtnIgual);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnIgual.getValue(Action.ACTION_COMMAND_KEY), "btnIgualAction");

        actBtnPunto.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0));
        jPanel1.getActionMap().put("btnPuntoAction", actBtnPunto);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnPunto.getValue(Action.ACTION_COMMAND_KEY), "btnPuntoAction");
        
        actBtnCE.putValue(Action.ACTION_COMMAND_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0));
        jPanel1.getActionMap().put("btnCEAction", actBtnCE);
        jPanel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put((KeyStroke) actBtnCE.getValue(Action.ACTION_COMMAND_KEY), "btnCEAction");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Pantalla = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnCE = new javax.swing.JButton();
        btnDividir = new javax.swing.JButton();
        btnMultiplicar = new javax.swing.JButton();
        btnRestar = new javax.swing.JButton();
        btnSumar = new javax.swing.JButton();
        btnNueve = new javax.swing.JButton();
        btnSeis = new javax.swing.JButton();
        btnCinco = new javax.swing.JButton();
        btnOcho = new javax.swing.JButton();
        btnSiete = new javax.swing.JButton();
        btnCuatro = new javax.swing.JButton();
        btnUno = new javax.swing.JButton();
        btnDos = new javax.swing.JButton();
        btnTres = new javax.swing.JButton();
        btnIgual = new javax.swing.JButton();
        btnPunto = new javax.swing.JButton();
        btnCero = new javax.swing.JButton();

        setClosable(true);

        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1KeyReleased(evt);
            }
        });

        Pantalla.setEditable(false);
        Pantalla.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Pantalla.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Pantalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PantallaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Pantalla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel2KeyReleased(evt);
            }
        });

        btnCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCE.setForeground(java.awt.Color.red);
        btnCE.setText("CE");
        btnCE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCEActionPerformed(evt);
            }
        });

        btnDividir.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnDividir.setText("/");
        btnDividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDividirActionPerformed(evt);
            }
        });

        btnMultiplicar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnMultiplicar.setText("*");
        btnMultiplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiplicarActionPerformed(evt);
            }
        });

        btnRestar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnRestar.setText("-");
        btnRestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestarActionPerformed(evt);
            }
        });

        btnSumar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSumar.setText("+");
        btnSumar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarActionPerformed(evt);
            }
        });

        btnNueve.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnNueve.setText("9");
        btnNueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNueveActionPerformed(evt);
            }
        });

        btnSeis.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSeis.setText("6");
        btnSeis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeisActionPerformed(evt);
            }
        });

        btnCinco.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCinco.setText("5");
        btnCinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCincoActionPerformed(evt);
            }
        });

        btnOcho.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnOcho.setText("8");
        btnOcho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOchoActionPerformed(evt);
            }
        });

        btnSiete.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSiete.setText("7");
        btnSiete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSieteActionPerformed(evt);
            }
        });

        btnCuatro.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCuatro.setText("4");
        btnCuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuatroActionPerformed(evt);
            }
        });

        btnUno.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnUno.setText("1");
        btnUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnoActionPerformed(evt);
            }
        });

        btnDos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnDos.setText("2");
        btnDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDosActionPerformed(evt);
            }
        });

        btnTres.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnTres.setText("3");
        btnTres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTresActionPerformed(evt);
            }
        });

        btnIgual.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnIgual.setText("=");
        btnIgual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIgualActionPerformed(evt);
            }
        });

        btnPunto.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPunto.setText(".");
        btnPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPuntoActionPerformed(evt);
            }
        });

        btnCero.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCero.setText("0");
        btnCero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCeroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCE, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDividir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMultiplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRestar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnUno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTres, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCero, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnSiete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOcho, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNueve, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnCuatro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCinco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSeis, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIgual, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSumar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCE, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDividir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMultiplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSiete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNueve, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOcho, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSeis, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCinco, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCuatro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSumar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTres, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPunto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCero, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnIgual, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCEActionPerformed
        Pantalla.setText(null);
        btnSumar.setEnabled(true);
        btnRestar.setEnabled(true);
        btnMultiplicar.setEnabled(true);
        btnDividir.setEnabled(true);
    }//GEN-LAST:event_btnCEActionPerformed

    private void btnDividirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDividirActionPerformed
        String i = Pantalla.getText();
        Pantalla.setText(i + " " + btnDividir.getText() + " ");
        btnSumar.setEnabled(false);
        btnRestar.setEnabled(false);
        btnMultiplicar.setEnabled(false);
        btnDividir.setEnabled(false);
    }//GEN-LAST:event_btnDividirActionPerformed

    private void btnMultiplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultiplicarActionPerformed
        String i = Pantalla.getText();
        Pantalla.setText(i + " " + btnMultiplicar.getText() + " ");
        btnSumar.setEnabled(false);
        btnRestar.setEnabled(false);
        btnMultiplicar.setEnabled(false);
        btnDividir.setEnabled(false);
    }//GEN-LAST:event_btnMultiplicarActionPerformed

    private void btnRestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestarActionPerformed
        String i = Pantalla.getText();
        Pantalla.setText(i + " " + btnRestar.getText() + " ");
        btnSumar.setEnabled(false);
        btnRestar.setEnabled(false);
        btnMultiplicar.setEnabled(false);
        btnDividir.setEnabled(false);
    }//GEN-LAST:event_btnRestarActionPerformed

    private void btnSieteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSieteActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "7");
    }//GEN-LAST:event_btnSieteActionPerformed

    private void btnOchoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOchoActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "8");
    }//GEN-LAST:event_btnOchoActionPerformed

    private void btnNueveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNueveActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "9");
    }//GEN-LAST:event_btnNueveActionPerformed

    private void btnSumarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSumarActionPerformed
        String i = Pantalla.getText();
        Pantalla.setText(i + " " + btnSumar.getText() + " ");
        btnSumar.setEnabled(false);
        btnRestar.setEnabled(false);
        btnMultiplicar.setEnabled(false);
        btnDividir.setEnabled(false);
    }//GEN-LAST:event_btnSumarActionPerformed

    private void btnCuatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuatroActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "4");
    }//GEN-LAST:event_btnCuatroActionPerformed

    private void btnCincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCincoActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "5");
    }//GEN-LAST:event_btnCincoActionPerformed

    private void btnSeisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeisActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "6");
    }//GEN-LAST:event_btnSeisActionPerformed

    private void btnUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnoActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "1");
    }//GEN-LAST:event_btnUnoActionPerformed

    private void btnDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDosActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "2");
    }//GEN-LAST:event_btnDosActionPerformed

    private void btnTresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTresActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "3");
    }//GEN-LAST:event_btnTresActionPerformed

    private void btnCeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCeroActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + "0");
    }//GEN-LAST:event_btnCeroActionPerformed

    private void btnPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPuntoActionPerformed
        String i = "";
        i = Pantalla.getText();
        Pantalla.setText(i + ".");
    }//GEN-LAST:event_btnPuntoActionPerformed

    private void btnIgualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIgualActionPerformed
        String i = Pantalla.getText();
        if (i.length() < 1) {
            JOptionPane.showMessageDialog(this, "Asegurate de haber ingresado algun dato.", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String[] arre = i.split("\\s+");

            String operador = arre[1];
            switch (operador) {
                case "+": {
                    BigDecimal total = (new BigDecimal(arre[0]).add(new BigDecimal(arre[2]))).setScale(2, RoundingMode.HALF_EVEN);
                    Pantalla.setText(total + "");
                    break;
                }
                case "-": {
                    BigDecimal total = (new BigDecimal(arre[0]).subtract(new BigDecimal(arre[2]))).setScale(2, RoundingMode.HALF_EVEN);
                    Pantalla.setText(total + "");
                    break;
                }
                case "*": {
                    BigDecimal total = (new BigDecimal(arre[0]).multiply(new BigDecimal(arre[2]))).setScale(2, RoundingMode.HALF_EVEN);
                    Pantalla.setText(total + "");
                    break;
                }
                case "/": {
                    BigDecimal total = (new BigDecimal(arre[0]).divide(new BigDecimal(arre[2]))).setScale(2, RoundingMode.HALF_EVEN);
                    Pantalla.setText(total + "");
                    break;
                }
            }
        }
        btnSumar.setEnabled(true);
        btnRestar.setEnabled(true);
        btnMultiplicar.setEnabled(true);
        btnDividir.setEnabled(true);
    }//GEN-LAST:event_btnIgualActionPerformed

    private void jPanel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyReleased

    }//GEN-LAST:event_jPanel1KeyReleased

    private void jPanel2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyReleased

    }//GEN-LAST:event_jPanel2KeyReleased

    private void PantallaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PantallaKeyPressed
   
    }//GEN-LAST:event_PantallaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Pantalla;
    private javax.swing.JButton btnCE;
    private javax.swing.JButton btnCero;
    private javax.swing.JButton btnCinco;
    private javax.swing.JButton btnCuatro;
    private javax.swing.JButton btnDividir;
    private javax.swing.JButton btnDos;
    private javax.swing.JButton btnIgual;
    private javax.swing.JButton btnMultiplicar;
    private javax.swing.JButton btnNueve;
    private javax.swing.JButton btnOcho;
    private javax.swing.JButton btnPunto;
    private javax.swing.JButton btnRestar;
    private javax.swing.JButton btnSeis;
    private javax.swing.JButton btnSiete;
    private javax.swing.JButton btnSumar;
    private javax.swing.JButton btnTres;
    private javax.swing.JButton btnUno;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
