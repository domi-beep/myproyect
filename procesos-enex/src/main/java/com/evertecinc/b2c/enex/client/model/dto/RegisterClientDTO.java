package com.evertecinc.b2c.enex.client.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2223951190746845204L;
	
	// Identificación del cliente
	private Long    idClient;
	private String  name;
	private String  upi;
	private String  legalName;
	private String  commercialType;
	private String  contactName;
	private String  contactPhone;
	private String  contactEmail;
	private String  accountJdeClient;
	private String  accountJdeInvoice;
	private String  accountJdeTicket;
	private String  clientStatus;
	private String  clientType;

	// Fechas de creación y actualización
	private LocalDateTime dateins;  // Cambiado de Date a LocalDateTime
	private LocalDateTime dateupd;  // Cambiado de Date a LocalDateTime

	// Direcciones
	private String addressStreetName;
	private String addressStreetNumber;
	private String addressDoorNumber;
	private String addressDisStreetName;
	private String addressDisStreetNumber;
	private String addressDisDoorNumber;
	private Long addressIdArea;
	private Long addressDisIdArea;
	private Long addressDisIdRegion;
	private Long addressIdRegion1;
	private Long addressDisIdCity;
	private Long addressIdCity1;

	// Códigos ORPAK
	private String codeOrpakClient;
	private String codeOrpakInvoice;
	private String codeOrpakTicket;

	// DTOs de área, ciudad, región y ejecutivos (sin cambios en nombres)
	private AreaDTO areaDTO;
	private CityDTO cityDTO;
	private RegionDTO regionDTO;
	private AreaDTO areaFDTO;
	private CityDTO cityFDTO;
	private RegionDTO regionFDTO;
	private EjecutivoDTO ejecutivoDTO;
	private JefeZonaDTO jefeZonaDTO;

	// Información financiera
	private BigDecimal creditLimit;
	private BigDecimal remainingAmount;
	private String locked;
	private String isCredit;
	private String warningStockChannel;
	private BigDecimal warningStock;
	private String warningStockCelular;
	private String warningStockEmail;
	private String warningLockedChannel;
	private String warningLockedEmail;

	// IDs de relaciones jerárquicas
	private Long idJfefeZona;
	private Long idEjecutivo;

	// Facturación y fechas de departamento
	private Boolean factDepartment;
	private LocalDate factDepartmentDate;  // Cambiado de Date a LocalDate

	// Control de acceso y permisos especiales
	private Boolean controlPass;
	private String storelistorpak;

	// Tecnologías de seguimiento y control
	private Boolean adblue;
	private Boolean vGps;
	private Boolean gpsBasic;
	private Boolean antifraude;
	private Boolean cambus;
	private Boolean telemedicion;
	private String gps;

	// Información reglamentaria y de contrato
	private String tipoReglamento;
	private String customerContractCd;

	// Representante legal (sin cambio de nombres)
	private String legalNamePerson;
	private String legal_upi;
	private String legalCivilStatus;
	private String legalProfession;
	private String domicilioRLegal;
	private String telefonoRLegal;
	private String mailRLegal;

	// Segundo representante legal (sin cambio de nombres)
	private String nombreRLegal2;
	private String rutRLegal2;
	private String estadoCivilRLegal2;
	private String profesionRLegal2;
	private String domicilioRLegal2;
	private String telefonoRLegal2;
	private String mailRLegal2;

	// Información de dirección adicional (sin cambio de nombres)
	private String calle1;
	private String numero1;
	private String deptoCasa1;
	private String comuna1;
	private String ciudad1;
	private String region1;
	private String calle2;
	private String deptoCasa2;
	private String numero2;
	private String comuna2;
	private String ciudad2;
	private String region2;

	// Información de contacto
	private String nombreContacto;
	private String apellidoContacto;
	private String cargoContacto;
	private String mailContacto;
	private String telContacto;
	
	public String toStringCodeOrpak() {
		StringBuilder sb = new StringBuilder("{");

		BiConsumer<String, Object> add = (name, value) -> {
		    if (value == null) return;
		    if (value instanceof String && ((String) value).trim().isEmpty()) return;
		    if (sb.length() > 1) sb.append(", ");
		    sb.append(name).append("=").append(value);
		};

		add.accept("codeOrpakClient", codeOrpakClient);
		add.accept("codeOrpakInvoice", codeOrpakInvoice);
		add.accept("codeOrpakTicket", codeOrpakTicket);

		sb.append("}");
		return sb.toString();
	}
}
