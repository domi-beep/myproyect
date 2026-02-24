package com.evertecinc.b2c.enex.integracion.constants;

import java.math.BigDecimal;

public class ClientConstants {
	
	public static final String CLIENT_STATUS_PENDING="P";
	public static final String CLIENT_STATUS_NEW = "N";
	public static final String CLIENT_STATUS_ACTIVE = "A";
	public static final String CLIENT_STATUS_INACTIVE = "I";
	public static final String CLIENT_STATUS_ELIMINATED = "E";
	
	//Estado "pasive" en orpak
	public static final String CLIENT_STATUS_PASIVE = "P";
	public static final String CLIENT_STATUS_LOCKED = "X";
	public static final String CLIENT_LOCK_LOCKED = "L";
	public static final String CLIENT_LOCK_UNLOCKED = "U";
	
	public static final String CLIENT_SCI = "SCI";
	public static final String CLIENT_SCT = "SCT";
	public static final String CLIENT_SST = "SST";
	public static final String CLIENT_SCS = "SCS";
	public static final String CLIENT_SCE = "SCE";
	public static final String CLIENT_PRE = "PRE";
	public static final String CLIENT_STO = "STO";
    
    //Acciones History
    public static final String CAMBIO_CONFIGURACION="C";
    public static final String CAMBIO_CONFIGURACION_GPS="G";
    public static final String CAMBIO_CONFIGURACION_ENTRADA_GPS="E";
    public static final String CAMBIO_CONFIGURACION_SALIDA_GPS="F";
    public static final String ACTIVACION_GPS="V";
    public static final String DESACTIVACION_GPS="N";
    public static final String PIN_ERROR="P";
    public static final String CAMBIO_ALERTAS="A";
    public static final String CAMBIO_NOMBRE="N";
    public static final String CAMBIO_STATUS="S";
    public static final String CAMBIO_TYPE_BALANCE="T";
    public static final String CREATE_HISTORY="I";
    public static final String DELETE_HISTORY="D";
    public static final String UPDATE_HISTORY="U";
    public static final String CARD_MOVEMENT="Asignaci�n";
    public static final String CAMBIO_DEPARTAMENTO="X";
    
    public static final String MESSAGE_STATUS_PENDING = "P";
    public static final String MESSAGE_STATUS_SENT = "S";
    
    public static final String VEHICLE_REQCARDNUMBER_STATUS_PENDING = "P";
    public static final String VEHICLE_REQCARDNUMBER_STATUS_REPRINT = "R";
    public static final String VEHICLE_REQCARDNUMBER_STATUS_CREATED = "C";
    public static final String VEHICLE_REQCARDNUMBER_INVOICE	    = "I"; // Factura
    
    public static final BigDecimal CARD_DAFAULT_REMAINING_AMOUNT	= BigDecimal.ZERO;
    public static final String CARD_DAFAULT_STATUS					= "P";
    public static final Boolean CARD_DAFAULT_RESTR_DAYS				= Boolean.TRUE;
    public static final Long CARD_DAFAULT_RESTR_HINI				= Long.valueOf(0);
    public static final Long CARD_DAFAULT_RESTR_HEND				= Long.valueOf(24);
    public static final BigDecimal CARD_DAFAULT_RESTR_AMOUNT_MAX	= BigDecimal.valueOf(500000);
    public static final Long CARD_DAFAULT_RESTR_MAX_LOAD			= Long.valueOf(5);
    public static final Long CARD_DAFAULT_RESTR_MAX_QUANTITY		= Long.valueOf(300000);
    public static final BigDecimal CARD_DAFAULT_RESTR_AMOUNT_MAX_CRE	= BigDecimal.valueOf(100);
    public static final Long CARD_DAFAULT_RESTR_MAX_LOAD_CRE			= Long.valueOf(2);
    public static final Long CARD_DAFAULT_RESTR_MAX_QUANTITY_CRE		= Long.valueOf(0);
    public static final Long CARD_DAFAULT_RESTR_MAX_LOAD_SCT			= Long.valueOf(3);
    public static final Long CARD_DAFAULT_RESTR_MAX_QUANTITY_SCT		= Long.valueOf(0);
    public static final BigDecimal CARD_DAFAULT_RESTR_AMOUNT_MAX_SCT	= BigDecimal.valueOf(350);
    public static final Long CARD_DAFAULT_RESTR_MAX_LOAD_SCI			= Long.valueOf(2);
    public static final Long CARD_DAFAULT_RESTR_MAX_QUANTITY_SCI		= Long.valueOf(0);
    public static final BigDecimal CARD_DAFAULT_RESTR_AMOUNT_MAX_SCI	= BigDecimal.valueOf(80000);
    public static final String CARD_STATUS_INACTIVE					= "I";
    public static final String CARD_STATUS_ELIMINADO                = "E";
    public static final String CARD_STATUS_ACTIVE                	= "A";
    public static final String CARD_STATUS_PENDING                	= "P";
    public static final String CARD_STATUS_RENAME                	= "R";
    public static final String CARD_RESTR_TYPE                      = "D";
    public static final Long  CARD_REMAINING_TRX_LOAD               = Long.valueOf(0);
    public static final BigDecimal CARD_REMAINING_PERIOD_AMOUNT     = BigDecimal.ZERO;
    
    public static final BigDecimal DEPTO_DAFAULT_RESTR_AMOUNT_MAX	= BigDecimal.valueOf(500000);
    public static final Long DEPTO_DAFAULT_RESTR_MAX_LOAD			= Long.valueOf(5);
    public static final Long DEPTO_DAFAULT_RESTR_MAX_QUANTITY		= Long.valueOf(300000);
    public static final BigDecimal DEPTO_DAFAULT_RESTR_AMOUNT_MAX_CRE	= BigDecimal.valueOf(100);
    public static final Long DEPTO_DAFAULT_RESTR_MAX_LOAD_CRE			= Long.valueOf(2);    
    public static final Long DEPTO_DAFAULT_RESTR_MAX_QUANTITY_CRE		= Long.valueOf(0);
    public static final Long DEPTO_DAFAULT_RESTR_MAX_LOAD_SCT			= Long.valueOf(3);
    public static final Long DEPTO_DAFAULT_RESTR_MAX_QUANTITY_SCT		= Long.valueOf(0);
    public static final BigDecimal DEPTO_DAFAULT_RESTR_AMOUNT_MAX_SCT	= BigDecimal.valueOf(350);
    public static final Long DEPTO_DAFAULT_RESTR_MAX_LOAD_SCI			= Long.valueOf(2);
    public static final Long DEPTO_DAFAULT_RESTR_MAX_QUANTITY_SCI		= Long.valueOf(0);
    public static final BigDecimal DEPTO_DAFAULT_RESTR_AMOUNT_MAX_SCI	= BigDecimal.valueOf(80000);

    
    public static final String VEHICLE_STATUS_ACTIVE				= "A";
    public static final String VEHICLE_STATUS_INACTIVE				= "I";
    public static final String VEHICLE_STATUS_ELIMINATED			= "E";
    public static final String VEHICLE_STATUS_RENAME				= "R";
    
    public static final String DEPARMENT_STATUS_INACTIVE            = "I";
    public static final String DEPARTMENT_STATUS_ACTIVE             = "A";
    
    public static final String DOCUMENT_TYPE_INVOICE				= "I";
    public static final String DOCUMENT_TYPE_TICKET					= "T";
    
    public static final boolean CLIENT_CUSTOMERCODE_INVOICE			= false;
    public static final boolean CLIENT_CUSTOMERCODE_TICKET			= true;
    
    public static final String TYPE_BALANCE_DEPTO                   ="D";
    public static final String TYPE_BALANCE_CARD                    ="C";
    public static final String TYPE_BALANCE_CLIENT                  ="E";
    
    public static final String RESTRICTION_TYPE_DEPTO              ="D";
    public static final String RESTRICTION_TYPE_CARD               ="C";
    
    public static final String CARD_MOVEMENT_CARGA                ="Carga";
    public static final String USUARIO_CARD_MOVEMENT_CARGA        ="Orpak";
    public static final String USUARIO_SISTEMA                    = "Sistema";
    
    public static final String CARDNUMBERMASTER_AVAILABLE		  = "A";
    
    public static final String PRODUCT_STATUS_ACTIVE				= "A";
    
    public static final String SERVICES_GPS_INIT					="0 0 0 0 0";
    
    public static final Integer SERVICES_GPS_CANTIDAD				= 5;
    
    public static final Integer SERVICES_GPS_POSITION_VALID			= 0;
    
    public static final Integer SERVICES_GPS_POSITION_BASIC			= 1;
    
    public static final Integer SERVICES_GPS_POSITION_AFRAUDE		= 2;
    
    public static final Integer SERVICES_GPS_POSITION_CANBUS		= 3;
    
    public static final Integer SERVICES_GPS_POSITION_TELEMD		= 4;
    
    public static final Integer SERVICES_GPS_POSITION_STRING_VALID		= 0;
    
    public static final Integer SERVICES_GPS_POSITION_STRING_BASIC		= 2;
    
    public static final Integer SERVICES_GPS_POSITION_STRING_AFRAUDE	= 4;
    
    public static final Integer SERVICES_GPS_POSITION_STRING_CANBUS		= 6;
    
    public static final Integer SERVICES_GPS_POSITION_STRING_TELEMD		= 8;
    
    public static final String DOCUMENT_SOURCE_SIGN 					="Y";
    
    public static final String DOCUMENT_SOURCE_PPL						="N";
    
    public static final String PRODUCT_CODE_DEFAULT					= "DIESEL";
    
    public static final String TYPE_CONTRALORIA						= "CON";
    
 // Estados a descartar Monitor OP anomalas
 	public static final String STATUS_OP_APROBADO = "A";
 	public static final String STATUS_OP_RECHAZADO = "R";
 	public static final String STATUS_OP_DESCARTADO = "D";
 	public static final String STATUS_OP_PROCESADA = "M";
 	public static final String STATUS_OP_INICIO = "I";
 	public static final String STATUS_OP_INICIO_PREORDEN = "IP";

   
    
}

