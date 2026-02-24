package com.evertecinc.b2c.enex.saf.constants;

public class SafConstants {
	
		// Creacion de archivo para nota de venta y pago de JDE
		public static final String SAF_SEND_NOTA_VENTA_JDE 				= "NVS";
		// Envia actualizacion de estado para un departamento
		public static final String SAF_UPDATE_DEPARTMENT_STATUS 		= "USD";
		// Envia actualizacion de estado para un departamento
		public static final String SAF_UPDATE_DEPARTMENTCARD_STATUS 	= "USDC";
		// Envia actualizacion de estado para una tarjeta
		public static final String SAF_UPDATE_CARD_STATUS 				= "USC";
		// Envia actualizacion de saldo de una tarjeta
		public static final String SAF_UPDATE_CARD_BALANCE 				= "UCB";
		// Envia actualizacion de saldo de un departamento
		public static final String SAF_UPDATE_DEPARTMENT_BALANCE 		= "UDB";
		// Envia actualizacion de saldo de un cliente
		public static final String SAF_UPDATE_CUSTOMER_BALANCE 			= "UEB";
		// Envia cambio de estado para eliminar una tarjeta
		public static final String SAF_DELETE_CARD 						= "DC";
		// Envia cambio de estado para eliminar un departamento
		public static final String SAF_DELETE_DEPARTMENT		 		= "DD";
		// Envia cambio de estado para eliminar un departamento
		public static final String SAF_DELETE_DEPARTMENT_FLEET		 	= "DDF";
		// Envia creacion de client a Orpak
		public static final String SAF_SEND_CLIENT_ORPAK		 		= "SCO";
		// Envia actualizacion de client a Orpak
		public static final String SAF_UPDATE_CLIENT_ORPAK		 		= "UCO";
		// Envio de correo por carga de transaccion en estacion de servicio
		public static final String SAF_SEND_MAIL_LOAD_TRX		 		= "SML";
		// Envio de correo por bloqueo desde listas negras
		public static final String SAF_SEND_MAIL_CLIENT_LOCK	 		= "SMC";
		// Listado para personalizacion de la tarjeta
		public static final String SAF_GET_CARD_NUMBER			 		= "GCN";
		// Envia creacion de vehicle/card
		public static final String SAF_CREATE_CARD_NUMBER			 	= "CCN";	
		// Envia actualizacion de vehicle/card
		public static final String SAF_UPDATE_CARD_NUMBER			 	= "UCN";
		// Envia actualizacion por renombre de patente de vehicle/card
		public static final String SAF_RENAME_CARD_NUMBER			 	= "RCN";
		// Envia actualizacion de un card Constraint
		public static final String SAF_UPDATE_CARD_CONSTRAINT		 	= "UCC";
		// Envia actualizaciones de estado del cliente
		public static final String SAF_CHANGE_CUSTOMER_STATUS           = "CCS";
		// Envia cambio de limite de credito
		public static final String SAF_CHANGE_CUSTOMER_CREDITLIMIT      = "UCL";
		//Solicitud de impresion
		public static final String SAF_PRINT_REQUEST					= "SIM";
		//Solicitud de reimpresion
		public static final String SAF_REPRINT_REQUEST					= "SRM";
		//Solicitud de producto AdBlue 
		public static final String SAF_ADBLUE_REQUEST					= "SAD";
		//Solicitud Validacion GPS
		public static final String SAF_VGPS_REQUEST						= "VGR";
		//Solicitud GPS Basico
		public static final String SAF_GPSBASIC_REQUEST					= "GBR";
		//Solicitud Regla antifraude
		public static final String SAF_AFRAUDE_REQUEST					= "AFR";
		//Solicitud Cambus WS GPS
		public static final String SAF_CAMBUS_REQUEST					= "CAR";
		//Solicitud Telemedicion
		public static final String SAF_TELEMEDICION_REQUEST				= "TMR";
		
		
		//Solicitud de impresion de dispositivo
		public static final String SAF_DISPOSITIVO_IMPRESION			= "SDI";
		//Solicitud de reimpresion de dispositivo
		public static final String SAF_DISPOSITIVO_REIMPRESION			= "SDR";
		//Solicitud de creacion de vehiculo-dispositivo
		public static final String SAF_CREATE_DEVICE_NUMBER				= "CDN";
		//Solicitud de actualizacion de vehiculo-dispositivo
		public static final String SAF_UPDATE_DEVICE_NUMBER				= "UDN";
		
		public static final String SAF_STATUS_PENDING					= "P";
		public static final String SAF_STATUS_SENDED				 	= "S";
		public static final String SAF_STATUS_DELETED				 	= "X";
		public static final String SAF_STATUS_REMOVE				 	= "M";
		public static final String SAF_STATUS_REJECTED				 	= "R";
		
		public static final String SAF_CONDITIONAL_1                    = "UTB1";
		public static final String SAF_CONDITIONAL_2                    = "UTB2";
		public static final String SAF_CONDITIONAL_3                    = "UTB3";
		public static final String SAF_CONDITIONAL_4                    = "UTB4";
		
		public static final String SAF_CONDITIONAL_LT_1                    = "UTBL1";
		public static final String SAF_CONDITIONAL_LT_2                    = "UTBL2";
		public static final String SAF_CONDITIONAL_LT_3                    = "UTBL3";
		public static final String SAF_CONDITIONAL_LT_4                    = "UTBL4";
		
		public static final String SAF_ORDEN_COMPRA_PENDING				= "OCP";
		//Solicitud para modificar litros de la tarjeta en Orpak
		public static final String SAF_LOAD_QUANTITY_CARD				= "LQC";
		
		public static final String SAF_UPDATE_GPS						= "GPS";

		public static final String SAF_SEND_MAIL_BIENVENIDA_KEYCLOAK = "SMBK"; // Send Mail Bienvenida Keycloak
		public static final String SAF_SEND_MAIL_REESTABLECER_PASSWORD_KEYCLOAK = "SMRPK"; // Send Mail Reestablecer Password Keycloak
}
