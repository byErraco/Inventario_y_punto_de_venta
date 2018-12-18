package Utilidades;


import java.awt.Color;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leandro
 */
public class CorteUtilidades {
    
    public void colorRojo(JTextField campo) {
        Color red = new Color(193, 39, 45);
        if(Double.parseDouble(campo.getText()) != 0) campo.setForeground(red);
        else                                         campo.setForeground(Color.BLACK);
    }
    
    public void colorVerde(JTextField campo) {
        Color green = new Color(28, 84, 45);
        if(Double.parseDouble(campo.getText()) != 0) campo.setForeground(green);
        else                                         campo.setForeground(Color.BLACK);
    }
    
    public void colorTexto(JTextField campo) {
        // Creando colores 
        Color green = new Color(28, 84, 45);
        Color red = new Color(193, 39, 45);
        // Asignando colores
        if(Double.parseDouble(campo.getText()) > 0)        campo.setForeground(green);
        else if(Double.parseDouble(campo.getText()) < 0)   campo.setForeground(red);
        else                                               campo.setForeground(Color.BLACK);
    }
    
    public void selectAntesDelPunto(JTextField campo) {
        campo.requestFocus();
        campo.setSelectionStart(0);
        int indice = campo.getText().lastIndexOf('.');
        campo.setSelectionEnd(indice);
    }
    
    public void selectDespuesDelPunto(JTextField campo) {
        int indice = campo.getText().lastIndexOf('.');
        campo.setSelectionStart((indice + 1));
        campo.setSelectionEnd(campo.getText().length());
    }
}
