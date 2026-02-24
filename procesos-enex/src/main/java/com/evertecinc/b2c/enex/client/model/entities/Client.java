package com.evertecinc.b2c.enex.client.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long idClient;

    @Column(name = "name")
    private String name;

    @Column(name = "upi")
    private String upi;

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "commercial_type")
    private String commercialType;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_job")
    private String contactJob;

    @Column(name = "account_jde_client")
    private String accountJdeClient;

    @Column(name = "account_jde_invoice")
    private String accountJdeInvoice;

    @Column(name = "account_jde_ticket")
    private String accountJdeTicket;

    @Column(name = "client_status")
    private String clientStatus;

    @Column(name = "client_type")
    private String clientType;

    @Column(name = "date_ins")
    private LocalDateTime dateIns;

    @Column(name = "date_upd")
    private LocalDateTime dateUpd;

    @Column(name = "address_streetname")
    private String addressStreetname;

    @Column(name = "address_streetnumber")
    private String addressStreetnumber;

    @Column(name = "address_doornumber")
    private String addressDoornumber;

    @Column(name = "address_dis_streetname")
    private String addressDisStreetname;

    @Column(name = "address_dis_streetnumber")
    private String addressDisStreetnumber;

    @Column(name = "address_dis_doornumber")
    private String addressDisDoornumber;

    @Column(name = "address_id_area")
    private Long addressIdArea;

    @Column(name = "address_id_region1")
    private Long addressIdRegion1;

    @Column(name = "address_id_city1")
    private Long addressIdCity1;

    @Column(name = "address_dis_id_area")
    private Long addressDisIdArea;

    @Column(name = "address_dis_id_region")
    private Long addressDisIdRegion;

    @Column(name = "address_dis_id_city")
    private Long addressDisIdCity;

    @Column(name = "legal_upi")
    private String legalUpi;

    @Column(name = "legal_civil_status")
    private String legalCivilStatus;

    @Column(name = "legal_profession")
    private String legalProfession;

    @Column(name = "legal_address")
    private String legalAddress;

    @Column(name = "legal_phone")
    private String legalPhone;

    @Column(name = "legal_name_person")
    private String legalNamePerson;

    @Column(name = "legal_email")
    private String legalEmail;

    @Column(name = "code_or_pak_client")
    private String codeOrpakClient;

    @Column(name = "code_or_pak_ticket")
    private String codeOrpakTicket;

    @Column(name = "code_or_pak_invoice")
    private String codeOrpakInvoice;

    @Column(name = "credit_limit", precision = 38, scale = 2)
    private BigDecimal creditLimit;

    @Column(name = "remaining_amount", precision = 38, scale = 2)
    private BigDecimal remainingAmount;

    @Column(name = "amount_due", precision = 38, scale = 2)
    private BigDecimal amountDue;

    @Column(name = "locked")
    private String locked;

    @Column(name = "iscredit")
    private String isCredit;

    @Column(name = "warning_stock", precision = 38, scale = 2)
    private BigDecimal warningStock;

    @Column(name = "warning_stock_channel")
    private String warningStockChannel;

    @Column(name = "warning_stock_celular")
    private String warningStockCelular;

    @Column(name = "warning_stock_email")
    private String warningStockEmail;

    @Column(name = "warning_locked_channel")
    private String warningLockedChannel;

    @Column(name = "warning_locked_email")
    private String warningLockedEmail;

    @Column(name = "fact_department")
    private Long factDepartment;

    @Column(name = "factdepartment")
    private Long factDepartmentOld;

    @Column(name = "fact_department_date")
    private LocalDateTime factDepartmentDate;

    @Column(name = "storelistorpak")
    private String storeListOrPak;

    @Column(name = "tipo_reglamento")
    private String tipoReglamento;

    @Column(name = "gps")
    private String gps;

//    @Column(name = "apellido_contacto")
//    private String apellidoContacto;

//    @Column(name = "nombre_contacto")
//    private String nombreContacto;

    @Column(name = "adblue")
    private Long adblue;

    @Column(name = "ad_blue")
    private Long adBlue;

    // Relaciones con claves externas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_dis_id_area", referencedColumnName = "id_area", insertable = false, updatable = false)
    private Area addressDisArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_dis_id_city", referencedColumnName = "id_city", insertable = false, updatable = false)
    private City addressDisCity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_dis_id_region", referencedColumnName = "id_region", insertable = false, updatable = false)
    private Region addressDisRegion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jefe_zona", referencedColumnName = "id_jefezona", insertable = false, updatable = false)
    private JefeZona jefeZona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ejecutivo", referencedColumnName = "id_ejecutivo", insertable = false, updatable = false)
    private Ejecutivo ejecutivo;
}