/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author Programador01
 */
public class Cripto {

    private final byte[] validKey;
    private final int iterations;
    private final byte[] salt;
    private final boolean modified;

    public Cripto(String completePassword) {
        String[] parametros = completePassword.split("\\$");
        modified = !parametros[0].equals("key_saphiro");
        iterations = Integer.parseInt(parametros[1]);
        salt = parametros[2].getBytes();
        validKey = parametros[3].getBytes();
    }

    /**
     * Valida el key.
     *
     * @param attemptedPassword
     * @return
     */
    public boolean validate(final String attemptedPassword) {
        byte[] b64;
        try {
            PBEKeySpec keySpec = new PBEKeySpec(attemptedPassword.toCharArray(), salt, iterations, 20 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] f = skf.generateSecret(keySpec).getEncoded();
            b64 = Base64.encode(f).getBytes();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            return false;
        }
        return Arrays.equals(b64, validKey);

    }

    public byte[] getSalt() {
        return this.salt;
    }

    public byte[] getValidKey() {
        return this.validKey;
    }

    public int getIterations() {
        return this.iterations;
    }

    public boolean isModified() {
        return this.modified;
    }

    public enum Encriptacion {

        MD5("MD5"),
        SHA1("SHA-1");

        String algoritmo;

        private Encriptacion(String algoritmo) {
            this.algoritmo = algoritmo;
        }

        @Override
        public String toString() {
            return this.algoritmo;
        }

    }
}
