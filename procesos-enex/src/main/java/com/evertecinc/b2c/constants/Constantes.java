package com.evertecinc.b2c.constants;

public class Constantes {

	public static final String STATUS_USUARIO_ACTIVO = "A";
	public static final String STATUS_ELIMINADO = "E";
	
	// Tipo de documento
	public static final Boolean TIPO_DOCUMENTO_FACTURA	= true;
	public static final Boolean TIPO_DOCUMENTO_BOLETA	= false;

	public static final String FILEEXT = ".xlsx";
	
	public static final String CLIENT_SST = "SST";
	public static final String CLIENT_SCT = "SCT";
	
    //Mails en general
    public static final String SYSTEM_PROCESS_MAILS               		= "MAIL";
    public static final String MAILS_STATUS_PENDING	        	  		= "P";
    public static final String MAILS_STATUS_SEND	        	 	 	= "S";

    
    
    public static final String SAF_FORMULARIO_CONTACTANOS		  		= "SFC";

    
	public static final String JDE_TYPE_1 								= "1";
	public static final String JDE_TYPE_2 								= "2";
	public static final String JDE_FILE_PREFIJO 						= "VF";
	public static final String JDE_TIPO_COMPRA_POS 						= "POS";
	public static final String JDE_PAGO_LINEA_TF 						= "Pago en Linea TF";
	public static final String JDE_DOCUMENT_TYPE_INVOICE 				= "I";
	public static final String JDE_DOCUMENT_TYPE_TICKET 				= "T";
    
    
	public static final String ORDER_STATUS_CREATED						="C";
	public static final String ORDER_STATUS_CONFIRMED					="X";
	public static final String ORDER_STATUS_REJECTED					="R";
	public static final String ORDER_STATUS_CANCELLED					="E";
	public static final String ORDER_STATUS_PENDING						="P";
	public static final String ORDER_STATUS_APROBADA_MANUAL				="M";
	public static final String PARAMETER_CORRELATIVO_JDE        		= "correlativo_jde";
	public static final String PARAMETER_IDVENTA                		= "correlativo_idventa";
	
	public static final String CODIGO_RETORNO_BANCO_OK					="0000";
	public static final String CODIGO_RETORNO_BANCO_NOK					="0001";
	
	//Constantes para medios de pago CHILE
	public static final String ORDER_PAY_M_WEBPAY						="T";
	public static final String ORDER_PAY_M_WEBPAY_TEXT					="WebPay";
	
	//Texto de las constantes de status de una orden
	public static final String ORDER_STATUS_CREATED_TEXT				="Creada";
	public static final String ORDER_STATUS_CONFIRMED_TEXT				="Confirmada";
	public static final String ORDER_STATUS_REJECTED_TEXT				="Rechazada";
	public static final String ORDER_STATUS_CANCELLED_TEXT				="Anulada";
	
	public static final String ORDEN_COMPRA								= "OC";
}
