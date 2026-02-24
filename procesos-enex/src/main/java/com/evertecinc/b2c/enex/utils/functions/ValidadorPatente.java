package com.evertecinc.b2c.enex.utils.functions;

import java.util.regex.Pattern;

public class ValidadorPatente {

	private static final String PATENTES_REGEX = String.join("|",
			// Formato 1985
			"^[^ÑQ]{2}[0-9]{4}$",

			// Formato 2007
			"^[^AEIOUMÑNQ]{4}[0-9]{2}$",

			// Regla 1
			"^[^ÑQO]{3}[0-9]{3}$",

			// Regla 2
			"^[^AEIOUMÑNQ]{2}-[0-9]{4}$",

			// Regla 3
			"^[^AEIOUMÑNQ]{4}-[0-9]{2}$",

			// Regla 4
			"^[^ÑQO]{4}-[0-9]{4}$",

			// Regla 5
			"^[0-9]{4}$",

			// Regla 6
			"^\\*[0-9]{4}\\*$");

	private static final Pattern PATRON_PATENTES = Pattern.compile(PATENTES_REGEX);

	public static boolean validarPatente(String patente) {
		if (patente == null || patente.isEmpty()) {
			return false;
		}
		return PATRON_PATENTES.matcher(patente.toUpperCase()).matches();
	}
}
