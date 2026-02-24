package com.evertecinc.b2c.constants;

public class OrpakWSConstants {

	public static final String CONSTANT_OK = "00";
	public static final String CONSTANT_OK_ORPAK = "0";

	public static final String RETORNO_CERO = "0";
	public static final String RETORNO_UNO = "-1";
	public static final String RETORNO_DOS = "-2";
	public static final String RETORNO_TRES = "-3";
	public static final String RETORNO_CUATRO = "-4";
	public static final String RETORNO_CINCO = "-5";
	public static final String RETORNO_SEIS = "-6";
	public static final String RETORNO_SIETE = "-7";
	public static final String RETORNO_OCHO = "-8";
	public static final String RETORNO_NUEVE = "-9";
	public static final String RETORNO_DIEZ = "-10";

	public static final String RETORNO_TIME_OUT = "-99";
	public static final String RETORNO_DESCONOCIDO = "-98";

	public static final String ORPAK_WS_CREATE_DEPARTMENT_TYPE_CREATE = "1";
	public static final String ORPAK_WS_CREATE_DEPARTMENT_TYPE_UPDATE = "2";
	public static final int ORPAK_WS_CREATE_DEPARTMENT_STATUS_OK = 0;
	public static final int ORPAK_WS_CREATE_DEPARTMENT_STATUS_EXIST = -1;

	public static final String ORPAK_WS_CREATE_DEPARTMENT_OFFLEET_TYPE_CREATE = "1";
	public static final String ORPAK_WS_CREATE_DEPARTMENT_OFFLEET_TYPE_UPDATE = "2";
	public static final String ORPAK_WS_CREATE_DEPARTMENT_OFFLEET_TYPE_DELETE = "3";

	public static final String ORPAK_WS_CREATE_DEPARTMENT_OFFLEET_BALANCETYPE = "1";
	public static final String ORPAK_WS_CREATE_DEPARTMENT_OFFLEET_STOCKTYPE = "1";

	public static final int ORPAK_WS_CREATE_VEHICLE_CARD_TYPE_CREATE = 1;
	public static final int ORPAK_WS_CREATE_VEHICLE_CARD_TYPE_UPDATE = 2;
	public static final int ORPAK_WS_CREATE_VEHICLE_CARD_TYPE_RENAME = 3;
	public static final int ORPAK_WS_CREATE_VEHICLE_DEPTO_UPDATE = 4;
	public static final int ORPAK_WS_CREATE_VEHICLE_CARD_STATUS_OK = 0;
	public static final int ORPAK_WS_CREATE_VEHICLE_CARD_STATUS_EXIST = -4;

	public static final int ORPAK_WS_CHANGE_PIN_OK = 1;

	public static final int ORPAK_WS_CHANGE_CARD_STATUS_OK = 0;

	public static final int ORPAK_WS_CHANGE_DEPARTMENT_STATUS_OK = 0;

	public static final int ORPAK_WS_UPD_CARD_BALANCE_STATUS_OK = 0;
	public static final int ORPAK_WS_UPD_CARD_BALANCE_FUEL_TYPE_ITEM_CODE = 0;

	public static final int ORPAK_WS_CREATE_CLIENT_CONTRACT_TYPE_CREATE = 1;
	public static final int ORPAK_WS_CREATE_CLIENT_CONTRACT_TYPE_UPDATE = 2;
	public static final int ORPAK_WS_CREATE_CLIENT_CONTRACT_STATUS_OK = 0;
	public static final int ORPAK_WS_CREATE_CLIENT_CONTRACT_STATUS_EXIST = -5;

	public static final int ORPAK_WS_CHANGE_BALANCE_CARD_TYPE_CARD = 0;
	public static final int ORPAK_WS_CHANGE_BALANCE_CARD_TYPE_DEPARTMENT = 1;
	public static final int ORPAK_WS_CHANGE_BALANCE_CARD_TYPE_STATUS_OK = 0;

	public static final int ORPAK_WS_GET_DEPARTMENT_BALANCE_STATUS_OK = 0;
	public static final int ORPAK_WS_GET_CARD_BALANCE_STATUS_OK = 0;
	public static final int ORPAK_WS_GET_CARD_BALANCE_STATUS_IN_USE = -4;
	public static final int ORPAK_WS_GET_CUSTOMER_BALANCE_STATUS_OK = 0;

	public static final int ORPAK_WS_CREATE_CONSTRAINT_TYPE_CREATE = 1;
	public static final int ORPAK_WS_CREATE_CONSTRAINT_TYPE_UPDATE = 2;

	public static final int ORPAK_WS_CARD_CONSTRAINT_STATUS_OK = 0;
	public static final int ORPAK_WS_CARD_CONSTRAINT_STATUS_EXIST = -7;

	public static final String ORPAK_WS_CARD_CONSTRAINT_NULL_STATIONS = "0000";

	public static final String ORPAK_WS_DEPARTMENT_BALANCE_AMOUNT = "0";
	public static final String ORPAK_WS_DEPARTMENT_BALANCE_QUANTITY = "1";
	
	//
//  // # RETORNO SAF UCC (UPDATE CARD CONSTRAINT)
	public static final String PROCESO_UCC_0 = "OK";
	public static final String PROCESO_UCC_1 = "Customer does not exist";
	public static final String PROCESO_UCC_2 = "Plate does not exist";
	public static final String PROCESO_UCC_3 = "Time restriction cannot inserted";
	public static final String PROCESO_UCC_4 = "Store List cannot inserted";
	public static final String PROCESO_UCC_5 = "Limit cannot inserted";
	public static final String PROCESO_UCC_6 = "Rule set cannot inserted";
	public static final String PROCESO_UCC_7 = "operation type wrong";
	
	
    public static final String PROCESO_USD_0 = "OK";
    public static final String PROCESO_USD_1 = "Customer does not exist";
    public static final String PROCESO_USD_2 = "Department does not exist";
    public static final String PROCESO_USD_3 = "Status missing";
    public static final String PROCESO_USD_4 = "Department code missing";

    
    // # RETORNO SAF UCB (UPDATE CARD BALANCE)
	public static final String PROCESO_UCB_0 = "OK";
	public static final String PROCESO_UCB_1 = "Customer does not exist";
	public static final String PROCESO_UCB_2 = "Card balance cannot updated";
	public static final String PROCESO_UCB_3 = "Card type must be debit amount card";
	public static final String PROCESO_UCB_4 = "Transaction already existing";
	public static final String PROCESO_UCB_5 = "Card number does not exist";
	public static final String PROCESO_UCB_6 = "Fuel Code does not exist";
	public static final String PROCESO_UCB_7 = "If fuel type different from 1 and 0";


//
//  // # RETORNO SAF UDB (UPDATE DEPARTMENT BALANCE)
	public static final String PROCESO_UDB_0 = "OK";
	public static final String PROCESO_UDB_1 = "Department does not exist";
	public static final String PROCESO_UDB_2 = "Department balance cannot updated";
	public static final String PROCESO_UDB_3 = "TransactionId is exist";
	public static final String PROCESO_UDB_4 = "DepartmentId is missing";
	public static final String PROCESO_UDB_6 = "Fuel type and FuelCode does not match";
	public static final String PROCESO_UDB_7 = "Fuel type wrong or missing";
	public static final String PROCESO_UDB_8 = "Amount cannot go negative";
//
//  // # RETORNO SAF CCN or UCN (CREATE CARD NUMBER, UPDATE CARD NUMBER)
	public static final String PROCESO_CCN_0 = "OK";
	public static final String PROCESO_CCN_1 = "Customer does not exist";
	public static final String PROCESO_CCN_2 = "Department does not exist";
	public static final String PROCESO_CCN_3 = "Card does not exist";
	public static final String PROCESO_CCN_4 = "Card is in use";
	public static final String PROCESO_CCN_5 = "Card type is not valid";
	public static final String PROCESO_CCN_6 = "Rule set does not exist";
	public static final String PROCESO_CCN_7 = "Operation type is missing or wrong";
	public static final String PROCESO_CCN_8 = "Vehicle does not exist (in case of update)";
	public static final String PROCESO_CCN_9 = "Plate already exist";
	public static final String PROCESO_CCN_10 = "Balance amount <> 0";
//
//  // # RETORNO SAF UUB (UPDATE COSTUMER BALANCE)
	public static final String PROCESO_UUB_0 = "OK";
	public static final String PROCESO_UUB_NEG_1 = "Customer does not exist";
	public static final String PROCESO_UUB_NEG_2 = "Amount must be greather than zero";
	public static final String PROCESO_UUB_NEG_3 = "Transaction does exist.";
	public static final String PROCESO_UUB_NEG_4 = "Remarks cannot empty.";
	public static final String PROCESO_UUB_NEG_5 = "Invalid date";

}
