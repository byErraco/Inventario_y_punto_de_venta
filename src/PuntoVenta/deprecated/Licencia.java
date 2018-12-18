/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PuntoVenta.deprecated;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class Licencia {

    String ID = "";
    String DESCRIPCION = "";
    String MAC = "";
    boolean archivo = false;

    private Image logo = null;
    private static final byte[] CIFRAR_KEY = keyAbyte();
    private static final String CIFRAR_KEY_STRING = "isb2.0";
    private static final String CIFRAR_FORMATO_SALIDA = "UTF8";
    private static final String CIFRAR_ALGORITHM = "Blowfish";
    public static final String BARRA = System.getProperty("file.separator");
    public static final String DIR_APP = System.getProperty("user.dir");
    private static final String CIFRAR_NOMBRE_ARCHIVO = "key_biometrico";

    public static boolean existeArchivo(String ruta) {
        return new File(ruta).exists();
    }

    public String getId() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public boolean isArchivo() {
        return archivo;
    }

    public void setArchivo(boolean archivo) {
        this.archivo = archivo;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    private static byte[] keyAbyte() {
        try {
            return CIFRAR_KEY_STRING.getBytes(CIFRAR_FORMATO_SALIDA);
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    public static byte[] toByteArray(String hexStr) {
        byte bArray[] = new byte[hexStr.length() / 2];
        for (int i = 0; i < (hexStr.length() / 2); i++) {
            byte firstNibble = Byte.parseByte(hexStr.substring(2 * i, 2 * i + 1), 16);
            byte secondNibble = Byte.parseByte(hexStr.substring(2 * i + 1, 2 * i + 2), 16);
            int finalByte = (secondNibble) | (firstNibble << 4);
            bArray[i] = (byte) finalByte;
        }
        return bArray;
    }

    public static String getHexEncriptado(String texto) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(CIFRAR_KEY, CIFRAR_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIFRAR_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] pText = texto.getBytes(CIFRAR_FORMATO_SALIDA);
            byte[] encrypted = cipher.doFinal(pText);
            return toHexString(encrypted);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String toHexString(byte[] b) {

        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static char[] hexChar = {
        '0', '1', '2', '3',
        '4', '5', '6', '7',
        '8', '9', 'A', 'B',
        'C', 'D', 'E', 'F'
    };

    public void getDatosLicencia() {
        String archivoLogo = DIR_APP + BARRA + getHexEncriptado(CIFRAR_NOMBRE_ARCHIVO);
        String nombreArchivo = DIR_APP + BARRA + getHexEncriptado(CIFRAR_NOMBRE_ARCHIVO) + ".dat";

        if ((existeArchivo(archivoLogo)) && (existeArchivo(nombreArchivo))) {
            try {
                SecretKeySpec skeySpec = new SecretKeySpec(CIFRAR_KEY, CIFRAR_ALGORITHM);
                Cipher cipher = Cipher.getInstance(CIFRAR_ALGORITHM);
                DataInputStream file = new DataInputStream(new FileInputStream(nombreArchivo));
                String hexString = file.readUTF();
                cipher.init(Cipher.DECRYPT_MODE, skeySpec);
                byte[] decrypted = cipher.doFinal(toByteArray(hexString));
                String[] datosArchivo = (new String(decrypted, CIFRAR_FORMATO_SALIDA)).split(";;");
                if (datosArchivo.length == 3) {

                    String id1 = datosArchivo[0];
                    this.setID(id1);
                    String Descripcion1 = datosArchivo[1];
                    this.setDESCRIPCION(Descripcion1);
                    String mac = "00-19-7E-A0-9E-1C";//datosArchivo[2];
                    //GUSTAVO

                    this.setMAC(mac);
                    this.setArchivo(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
//                System.out.print("Error leyendo contenido de archivo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se puede ubicar la licencia:\n" + nombreArchivo, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
