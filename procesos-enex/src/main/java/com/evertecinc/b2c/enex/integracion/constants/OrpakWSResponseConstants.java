package com.evertecinc.b2c.enex.integracion.constants;

public class OrpakWSResponseConstants {
	
//	# RETORNO SAF USC (UPDATE CARD STATUS)
	public static final String 	PROCESO_USC_0 = "OK";
	public static final String 	PROCESO_USC_1 = "Sender or status missing";
	public static final String 	PROCESO_USC_2 = "Card does not exist";
	public static final String 	PROCESO_USC_3 = "Wrong status";
	
//	# RETORNO SAF UCO (UPDATE CLIENT ORPAK)
	public static final String PROCESO_UCO_0 = "OK";
    public static final String PROCESO_UCO_1 = "Operation type missing or wrong";
    public static final String PROCESO_UCO_2 = "Email is not valid";
    public static final String PROCESO_UCO_3 = "Telephone or fax has characters inside";
    public static final String PROCESO_UCO_4 = "Code, name, status, alternativeCode, adress, city, rut, giro, contactname does not exist";
    public static final String PROCESO_UCO_5 = "The user exist when using operation type 1";
    public static final String PROCESO_UCO_6 = "User not exist when operation type 2";

 // # RETORNO SAF USD (UPDATE DEPARTMENT STATUS)
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

    // # RETORNO SAF UCC (UPDATE CARD CONSTRAINT)
    public static final String PROCESO_UCC_0 = "OK";
    public static final String PROCESO_UCC_1 = "Customer does not exist";
    public static final String PROCESO_UCC_2 = "Plate does not exist";
    public static final String PROCESO_UCC_3 = "Time restriction cannot inserted";
    public static final String PROCESO_UCC_4 = "Store List cannot inserted";
    public static final String PROCESO_UCC_5 = "Limit cannot inserted";
    public static final String PROCESO_UCC_6 = "Rule set cannot inserted";
    public static final String PROCESO_UCC_7 = "operation type wrong";

    // # RETORNO SAF UDB (UPDATE DEPARTMENT BALANCE)
    public static final String PROCESO_UDB_0 = "OK";
    public static final String PROCESO_UDB_1 = "Department does not exist";
    public static final String PROCESO_UDB_2 = "Department balance cannot updated";
    public static final String PROCESO_UDB_3 = "TransactionId is exist";
    public static final String PROCESO_UDB_4 = "DepartmentId is missing";
    public static final String PROCESO_UDB_6 = "Fuel type and FuelCode does not match";
    public static final String PROCESO_UDB_7 = "Fuel type wrong or missing";
    public static final String PROCESO_UDB_8 = "Amount cannot go negative";

    // # RETORNO SAF CCN or UCN (CREATE CARD NUMBER, UPDATE CARD NUMBER)
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

    // # RETORNO SAF UUB (UPDATE COSTUMER BALANCE)
    public static final String PROCESO_UUB_0 = "OK";
    public static final String PROCESO_UUB_NEG_1 = "Customer does not exist";
    public static final String PROCESO_UUB_NEG_2 = "Amount must be greather than zero";
    public static final String PROCESO_UUB_NEG_3 = "Transaction does exist.";
    public static final String PROCESO_UUB_NEG_4 = "Remarks cannot empty.";
    public static final String PROCESO_UUB_NEG_5 = "Invalid date";

	// # RETORNO SAF UEB (UPDATE COSTUMER BALANCE)
//    public static final String PROCESO_UUB_0 = "OK";
    public static final String PROCESO_UUB_1 = "Customer does not exist";
    public static final String PROCESO_UUB_2 = "Amount must be greather than zero";
    public static final String PROCESO_UUB_3 = "Transaction does exist.";
    public static final String PROCESO_UUB_4 = "Remarks cannot empty.";
    public static final String PROCESO_UUB_5 = "Invalid date";

}
