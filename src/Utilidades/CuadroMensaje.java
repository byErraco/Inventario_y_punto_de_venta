/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Programador01
 */
public class CuadroMensaje {

    public static int getMensajeSiNo(Component padre, String mensaje, String titulo) {
        return JOptionPane.showOptionDialog(
                padre,
                mensaje,
                titulo,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, //icono
                new Object[]{"Si", "No"}, //
                "Si");
    }

    public static void getMensajeAdvertencia(Component padre, String mensaje, String titulo) {
        JOptionPane.showInternalMessageDialog(padre, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public static void getMensajeError(Component padre, String mensaje, String titulo) {
        JOptionPane.showInternalMessageDialog(padre, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
}
