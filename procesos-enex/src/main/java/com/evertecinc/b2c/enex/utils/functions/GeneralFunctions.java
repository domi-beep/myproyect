package com.evertecinc.b2c.enex.utils.functions;

import java.util.Base64;

public class GeneralFunctions {
	
	private static final Base64.Encoder encoder = Base64.getEncoder();
	private static final Base64.Decoder decoder = Base64.getDecoder();
	public static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
    public static final int BASE = ALPHABET.length();
    
    /**
     * Rellena un número con ceros a la izquierda hasta alcanzar el largo especificado.
     *
     * @param numero El número a formatear.
     * @param largo  El largo total esperado de la cadena resultante.
     * @return Una cadena que representa el número rellenado con ceros.
     * @throws IllegalArgumentException Si el número tiene más dígitos que el largo especificado.
     */

    public static String nCeros(int numero, int largo) {
        String numeroStr = String.valueOf(numero);
        if (numeroStr.length() > largo) {
            throw new IllegalArgumentException("El número " + numero + " excede el largo especificado de " + largo);
        }
        return String.format("%0" + largo + "d", numero);
    }

}
