package Utilidades;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Programador01
 */
public class KeySaphiro extends Cripto {
    String completePassword="key_saphiro$12000$DsiqhzwveUmv$ZdE/RLzIt1bGahOit9mdH3+NCZ4=";
    public KeySaphiro(String completePassword) throws Exception {
        super(completePassword);
    }

    /**
     * Para obtener la MAC del usuario. Escoge la que se esté utilizando en el
     * momento.
     *
     * @return
     */
    private String getMac() {
        StringBuilder sb = new StringBuilder();

        try {
            InetAddress ip = InetAddress.getLocalHost();
//                  System.out.println("Current IP address : " + ip.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
//         System.out.println("Current mac address : " + mac);
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
//                System.out.println(sb.toString());
            }
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Encripta un String utilizando el cipher con el passphrase y lo transforma
     * en Base64.
     *
     * @param str
     * @return
     * @throws Exception
     */
    private String byteToBase64(byte[] phrase) {
        return Base64.encode(phrase);
    }

    /**
     * Válida contra la MAC del dispositivo.
     * @return 
     */
    public boolean validate() {
        return this.validate(getMac());
    }
}
