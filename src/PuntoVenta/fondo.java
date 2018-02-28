/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class fondo extends javax.swing.JDesktopPane {

    private final Image IMG;

    public fondo() {
        super();
        IMG = new ImageIcon(getClass().getResource("/PuntoVenta/Iconos/FONDO 2.png")).getImage();
    }

    @Override
    public void paintChildren(Graphics g) {
        g.drawImage(IMG, 0, 0, getWidth(), getHeight(), this);
        super.paintChildren(g);
    }

}
