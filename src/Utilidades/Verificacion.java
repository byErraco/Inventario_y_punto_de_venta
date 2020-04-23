/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
/**
 *
 * @author leandro
 */
public class Verificacion {
    //CAMPO CI VALIDACIONES-------------------------------------------------------------------------------
    
    public static String isAlfanumeric (String cadena){
        if (cadena.matches("[A-Z0-9]+")){
            return cadena;
        } else {
            return ("Cédula incorrecta");
        }
    }
    
    public static String isNumeric2 (String cadena) {
        if (cadena.matches("(([0-9]+{1,3})([-]+{1})([0-9]+)([-]+{1})([0-9]+{1}))")){
            return cadena;
        } else {
            return ("Cédula incorrecta");
        }
    }
    
    public static String isNumeric1 (String cadena) {
        if (cadena.matches("(([0-9]+)([-]+{1})([0-9]+{1}))")){
            return cadena;
        } else {
            return ("error");
        }
    }
    
    public static String isNumeric3 (String cadena) {
        if (cadena.matches("(([0-9]+)([-]+{1})([A-Z]+{1}))")){
            return cadena;
        } else {
            return ("error");
        }
    }
    
    public static String isNumeric4 (String cadena) {
        if (cadena.matches("(([0-9]+{1,4})([-]+{1})([0-9]+{1,4})([-]+{1})([0-9]+{1,5}))")){
            return cadena;
        } else {
            return ("Cédula incorrecta");
        }
    }
    
    public static String isNumeric5 (String cadena) {
        if (cadena.matches("(([0-9]+{1})([-]+{1})([0-9]+{1,3})([-]+{1})([0-9]+))")){
            return cadena;
        } else {
            return ("Cédula incorrecta");
        }
    }
     
    public static boolean isNumeric6(String str) {
        NumberFormat formater = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formater.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    //  SOLO STRING---------------------------------------------------------------------------------------------------
    public static String isString (String cadena){
        if (cadena.matches ("[A-Z]+")){
          return cadena;
        } else {
            return ("Error");
        }
    }
    // EMAIL------------------------------------------------------------------------------------------
    public static boolean isEmail(String cadena) {
        return cadena.matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }
    
    // VALIDAR IDENTIFICACION TRIBUTARIA DE PAISES-----------------------------------------------------------
    
    //Venezuela
    public static String isRif(String cadena) {
        if (cadena.matches("^[JG]-[0-9]{8}-[0-9]{1}$")) {
            return cadena;
        }else {
            return ("Identificacion incorrecta");
        }
    }
    
    //Colombia
    public static String isNit(String cadena) {
        if (cadena.matches("^[0-9]{8,9}[-][0-9]{1}$")) {
            return cadena;
        }else {
            return ("Identificacion incorrecta");
        }
    }
    
    //Chile
    public static String isRut(String cadena) {
        if (cadena.matches("^[0-9]{9}-[0-9]{1}$")) {
            return cadena;
        }else {
            return ("Identificacion incorrecta");
        }
    }
    
    //Ecuador
    public static String isRuc(String cadena) {
        if (cadena.matches("^[0-9]{13}")) {
            return cadena;
        }else {
            return ("Identificacion incorrecta");
        }
    }
    
    //VALIDAR CEDULAS DE IDENTIDAD---------------------------------------------------
    public static void validarIdentificaciones(String idci, KeyEvent evt, JTextField cedulat) {
        //Bolivia
        char c = evt.getKeyChar();
        if (idci.equals("BO") || idci.equals("VE") || idci.equals("PY")  || idci.equals("CO") || idci.equals("CR") || idci.equals("CU") || idci.equals("AR") || idci.equals("GT")){
            if (!Character.isDigit(c)|| c == KeyEvent.VK_SPACE || cedulat.getText().length()>=50)
            {
                evt.consume();
            }
        }
        //Chile
        if (idci.equals("CHL")){
            if(c =='-' && cedulat.getText().toUpperCase().contains("-") || c==KeyEvent.VK_SPACE || cedulat.getText().length()>=50){
                evt.consume();
            }
            if( cedulat.getText().toUpperCase().equals("") && c =='-'){
                evt.consume();
            }
        }  
        //Mexico
        if (idci.equals("MX")){
            if(c==KeyEvent.VK_SPACE || cedulat.getText().toUpperCase().length()>=50 || !Character.isAlphabetic(c) && !Character.isDigit(c)){
                evt.consume();
            }
        }
        //Nicaragua
        if (idci.equals("NI")){
            try {
                MaskFormatter formatter = new MaskFormatter("###-######-AAAA");
                formatter.setPlaceholderCharacter('_');
            } catch (ParseException ex) {}
        }
        //Honduras
        if (idci.equals("HN") || idci.equals("PA") || idci.equals("DO")){
           if (c == KeyEvent.VK_SPACE || (!Character.isDigit(c) && c != '-') || cedulat.getText().length() >=50) {
               evt.consume();
           }
        }
        //Ecuador
        if (idci.equals("EC") || idci.equals("SV") || idci.equals("PE") || idci.equals("UY") ){
            if ((!Character.isDigit(c) && c != '-') || c == KeyEvent.VK_SPACE || cedulat.getText().length() >=50){
                evt.consume();
            }
            if(c =='-' && cedulat.getText().toUpperCase().contains("-")){
                evt.consume();
            }
            if( cedulat.getText().toUpperCase().equals("") && c =='-'){
                evt.consume();
            }
        }
    }
    //VALIDAR CON EVENTOS-----------------------------------------------------------------------------
    public static void isDigit(KeyEvent evt) {
        char validar = evt.getKeyChar();
        if(Character.isLetter(validar)) {
//            getToolkit().beep();
            evt.consume();
        }
    }
    
    public static void isAlphabetic(KeyEvent evt) {
        char validar = evt.getKeyChar();
        if(Character.isLetter(validar)) {
//            getToolkit().beep();
            evt.consume();
        }
    }
    
    public static void nombre(KeyEvent evt, JTextField txtNombre) {
        if ( (txtNombre.getText().length() > 40) || !Character.isAlphabetic(evt.getKeyChar()) ) {
            evt.consume();
        }
    }
    
    public static void telefono(KeyEvent evt, JTextField txtTelefono) {
        if ((txtTelefono.getText().length() > 40) || ((!Character.isDigit(evt.getKeyChar()) && (evt.getKeyChar() != '-') && (evt.getKeyChar() != '+')))) {
            evt.consume();
        }
    }
}
