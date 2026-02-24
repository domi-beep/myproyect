package com.evertecinc.b2c.enex.client.constants;

public class PayChileConstants {

		//Constantes de afirmacion 
		public static final String YES ="Y";
		public static final String NO ="N";
		
		//Constantes de tipo de cuotas de webpay
		public static final String SIN_CUOTAS ="VN";
		public static final String NORMALES ="VC";
		public static final String SIN_INTERESES ="SI";
		public static final String CUOTAS_COMERCIO ="CI";
		public static final String DEBITO ="VD";
		
		public static final String SIN_CUOTAS_TEXT ="Sin Cuotas";
		public static final String NORMALES_TEXT ="Cuotas Normales";
		public static final String SIN_INTERESES_TEXT ="Sin Interés";
		public static final String CUOTAS_COMERCIO_TEXT ="Cuotas Comercio";
		public static final String DEBITO_TEXT ="Venta Debito";
		
		public static final String TBK_CHECK_MAC_CORRECT_RESPONSE = "CORRECTO";
		public static final String TBK_CHECK_MAC_INVALID_RESPONSE = "INVALIDO";
		
		
		/**
		 * Banco Chile notification ok
		 */
		public static final String BCHILE_CODRET_OK = "0000";
		public static final String BCHILE_CODRET_NOK = "0001";
		/**
		 * Banco Santander notification ok
		 */
		public static final String BSANTANDER_CODRET_OK = "0000";
		public static final String BSANTANDER_CODRET_NOK = "0001";
		
		/**
		 * Banco estado notification ok
		 */
		public static final String BESTADO_CODRET_OK = "OK";
		public static final String BESTADO_CODRET_NOK = "NOK";
		
		/**
		 * Banco Bci Tbanc notificacion
		 */
		public static final String BBCITBANC_CARGO_APLICADO = "021";
		public static final String BBCITBANC_CARGO_NO_APLICADO = "023";
		
		//Constantes de tipografia medios de pago
		public static final String MEDIO_DE_PAGO_BANCO_ESTADO 		= "E";
		public static final String MEDIO_DE_PAGO_BANCO_CHILE 		= "C";
		public static final String MEDIO_DE_PAGO_BANCO_SANTANDER 	= "S";
		public static final String MEDIO_DE_PAGO_BANCO_BCI 			= "I";
		public static final String MEDIO_DE_PAGO_BANCO_TBANC 		= "B";
		public static final String MEDIO_DE_PAGO_DEPOSITO	 		= "D";
		public static final String MEDIO_DE_PAGO_WEBPAY             = "T";

		//Constantes de nombres de medios de pago
		public static final String NOMBRE_BANCO_ESTADO 		= "Banco Estado";
		public static final String NOMBRE_BANCO_CHILE 		= "Banco de Chile";
		public static final String NOMBRE_BANCO_SANTANDER 	= "Banco Santander";
		public static final String NOMBRE_BANCO_BCI 		= "Banco bci";
		public static final String NOMBRE_BANCO_TBANC 		= "Banco Tbanc";
		public static final String NOMBRE_BANCO_DEPOSITO	= "Depósito";
		
		//Constantes nombres medios de pago para jde
		public static final String NOMBRE_BANCO_ESTADO_JDE = "ESTADO";
		public static final String NOMBRE_BANCO_CHILE_JDE = "CHILE";
		public static final String NOMBRE_BANCO_SANTANDER_JDE = "SANTANDER";
		public static final String NOMBRE_BANCO_BCI_JDE = "BCI";
		public static final String NOMBRE_BANCO_TBANC_JDE = "TBANC";
		public static final String NOMBRE_BANCO_DEPOSITO_JDE = "DEPOSITO";
		public static final String NOMBRE_BANCO_WEBPAY_JDE = "WEBPAY";
		
}
	
