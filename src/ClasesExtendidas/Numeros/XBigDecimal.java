/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesExtendidas.Numeros;

import java.math.BigDecimal;

/**
 *
 * @author gustavo
 */
public class XBigDecimal extends BigDecimal {

    /**
     * Inicializa el BigDecimal utilizando un String.
     *
     * @param valor
     */
    public XBigDecimal(String valor) {
        super(valor);
    }

    /**
     * Inicializa el BigDecimal utilizando un int.
     *
     * @param valor
     */
    public XBigDecimal(int valor) {
        super(valor);
    }
    
    /**
     * Inicializa el BigDecimal utilizando un double.
     *
     * @param valor
     */
    public XBigDecimal(Double valor) {
        super(valor);
    }

}
