package Utilidades;

import java.awt.Toolkit;

public class Sonidos {

    /**
     * Genera un sonido de "beep".
     */
    public static void beep() {
        Toolkit.getDefaultToolkit().beep();//beep en windows
        System.out.println("\007");//beep en linux
    }
}
