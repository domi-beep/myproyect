package com.evertecinc.b2c.constants;

public class OrderConstants {

	public static final String ORDER_STATUS_CREATED				="C";
	public static final String ORDER_STATUS_CONFIRMED			="X";
	public static final String ORDER_STATUS_REJECTED			="R";
	public static final String ORDER_STATUS_CANCELLED			="E";
	public static final String ORDER_STATUS_PENDING				="P";
	public static final String ORDER_STATUS_APROBADA_MANUAL		="M";
	public static final String PARAMETER_CORRELATIVO_JDE        = "correlativo_jde";
	public static final String PARAMETER_IDVENTA                = "correlativo_idventa";
	
	public static final String CODIGO_RETORNO_BANCO_OK			="0000";
	public static final String CODIGO_RETORNO_BANCO_NOK			="0001";
	
	//Constantes para medios de pago CHILE
	public static final String ORDER_PAY_M_WEBPAY				="T";
	public static final String ORDER_PAY_M_WEBPAY_TEXT			="WebPay";
	
	//Texto de las constantes de status de una orden
	public static final String ORDER_STATUS_CREATED_TEXT			="Creada";
	public static final String ORDER_STATUS_CONFIRMED_TEXT			="Confirmada";
	public static final String ORDER_STATUS_REJECTED_TEXT			="Rechazada";
	public static final String ORDER_STATUS_CANCELLED_TEXT			="Anulada";
	
	public static final String ORDEN_COMPRA							= "OC";
	
	public static final String EMAIL_ORDER_TO_USER = "OTU";
	
	
}
