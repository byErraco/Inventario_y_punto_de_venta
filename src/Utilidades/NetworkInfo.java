package Utilidades;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkInfo {

    public static String[] getMacAddresses() {
        try {
            ArrayList<NetworkInterface> nets = Collections.list(NetworkInterface.getNetworkInterfaces());
            int cont = nets.size();
            if (cont == 0) {
                return new String[]{"cc:af:78:0c:fa:45"};
            }

            String[] direcciones = new String[cont];
            cont = 0;

            for (NetworkInterface netint : nets) {
                direcciones[cont] = darFormatoMAC(netint.getHardwareAddress());
                cont++;
            }
            return direcciones;
        } catch (SocketException ex) {
            Logger.getLogger(NetworkInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String darFormatoMAC(byte[] mac) {
        if (mac == null) {
            return "00-00-00-00-00-00";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
        }
        return sb.toString();
    }
}
