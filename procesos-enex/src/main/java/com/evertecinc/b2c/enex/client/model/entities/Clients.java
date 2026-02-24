package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

import com.evertecinc.b2c.enex.client.model.converters.BooleanToSmallintConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clients", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@ToString
public class Clients {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", nullable = false)
    private Long idClient;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "upi", nullable = true)
    private String upi;

    @Column(name = "legal_name", nullable = true)
    private String legalName;

    @Column(name = "commercial_type", nullable = true)
    private String commercialType;

    @Column(name = "contact_name", nullable = true)
    private String contactName;

    @Column(name = "contact_phone", nullable = true)
    private String contactPhone;

    @Column(name = "contact_email", nullable = true)
    private String contactEmail;

    @Column(name = "contact_job", nullable = true)
    private String contactJob;

    @Column(name = "account_jde_client", nullable = true)
    private String accountJdeClient;

    @Column(name = "account_jde_invoice", nullable = true)
    private String accountJdeInvoice;

    @Column(name = "account_jde_ticket", nullable = true)
    private String accountJdeTicket;

    @Column(name = "client_status", nullable = true)
    private String clientStatus;

    @Column(name = "client_type", nullable = true)
    private String clientType;

    @Column(name = "dateins", nullable = true)
    private LocalDateTime dateIns;

    @Column(name = "dateupd", nullable = true)
    private LocalDateTime dateUpd;

    @Column(name = "address_streetname", nullable = true)
    private String addressStreetName;

    @Column(name = "address_streetnumber", nullable = true)
    private String addressStreetNumber;

    @Column(name = "address_doornumber", nullable = true)
    private String addressDoorNumber;

    @Column(name = "address_dis_streetname", nullable = true)
    private String addressDisStreetName;

    @Column(name = "address_dis_streetnumber", nullable = true)
    private String addressDisStreetNumber;

    @Column(name = "address_dis_doornumber", nullable = true)
    private String addressDisDoorNumber;

    @Column(name = "address_dis_id_area", nullable = false)
    private Long addressDisIdArea;

    @Column(name = "address_id_area", nullable = false)
    private Long addressIdArea;

    @Column(name = "address_dis_id_region", nullable = false)
    private Long addressDisIdRegion;

    @Column(name = "address_id_region1", nullable = false)
    private Long addressIdRegion1;

    @Column(name = "address_dis_id_city", nullable = false)
    private Long addressDisIdCity;

    @Column(name = "address_id_city1", nullable = false)
    private Long addressIdCity1;

    @Column(name = "legal_upi", nullable = true)
    private String legalUpi;

    @Column(name = "legal_civil_status", nullable = true)
    private String legalCivilStatus;

    @Column(name = "legal_profession", nullable = true)
    private String legalProfession;

    @Column(name = "legal_address", nullable = true)
    private String legalAddress;

    @Column(name = "legal_phone", nullable = true)
    private String legalPhone;

    @Column(name = "legal_name_person", nullable = true)
    private String legalNamePerson;

    @Column(name = "legal_email", nullable = true)
    private String legalEmail;

    @Column(name = "codeorpakclient", nullable = true)
    private String codeorpakclient;

    @Column(name = "codeorpakticket", nullable = true)
    private String codeorpakticket;

    @Column(name = "codeorpakinvoice", nullable = true)
    private String codeorpakinvoice;

    @Column(name = "legal_upi_copy1", nullable = true)
    private String legalUpiCopy1;

    @Column(name = "legal_civil_status_copy1", nullable = true)
    private String legalCivilStatusCopy1;

    @Column(name = "legal_profession_copy1", nullable = true)
    private String legalProfessionCopy1;

    @Column(name = "legal_address_copy1", nullable = true)
    private String legalAddressCopy1;

    @Column(name = "legal_phone_copy1", nullable = true)
    private String legalPhoneCopy1;

    @Column(name = "legal_name_person_copy1", nullable = true)
    private String legalNamePersonCopy1;

    @Column(name = "legal_email_copy1", nullable = true)
    private String legalEmailCopy1;

    @Column(name = "credit_limit", nullable = true)
    private BigDecimal creditLimit;

    @Column(name = "remaining_amount", nullable = true)
    private BigDecimal remainingAmount;

    @Column(name = "amount_due", nullable = true)
    private BigDecimal amountDue;

    @Column(name = "order_gross_amount", nullable = true)
    private BigDecimal orderGrossAmount;

    @Column(name = "locked", nullable = true)
    private String locked;

    @Column(name = "iscredit", nullable = true)
    private String isCredit;

    @Column(name = "warning_stock_channel", nullable = true)
    private String warningStockChannel;

    @Column(name = "warning_stock", nullable = true)
    private BigDecimal warningStock;

    @Column(name = "warning_stock_celular", nullable = true)
    private String warningStockCelular;

    @Column(name = "warning_stock_email", nullable = true)
    private String warningStockEmail;

    @Column(name = "warning_locked_channel", nullable = true)
    private String warningLockedChannel;

    @Column(name = "warning_locked_email", nullable = true)
    private String warningLockedEmail;

    @Column(name = "id_jefezona", nullable = false)
    private Long idJefeZona;

    @Column(name = "id_ejecutivo", nullable = false)
    private Long idEjecutivo;

    @Column(name = "factdepartment", nullable = true)
	@Convert(converter = BooleanToSmallintConverter.class)
    private Boolean factdepartment;

    @Column(name = "factdepartmentdate", nullable = true)
    private LocalDateTime factdepartmentdate;

    @Column(name = "storelistorpak", nullable = true)
    private String storelistOrpak;

    @Column(name = "adblue", nullable = true)
	@Convert(converter = BooleanToSmallintConverter.class)
    private Boolean adblue;

    @Column(name = "gps", nullable = true)
    private String gps;

    @Column(name = "tiporeglamento", nullable = true)
    private String tiporeglamento;

}
