package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evertecinc.b2c.enex.client.model.dto.ClientDTO;
import com.evertecinc.b2c.enex.client.model.dto.VehicleDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CardSafDTO;
import com.evertecinc.b2c.enex.client.model.dto2.MonitorMaestroTarjetasDTO;
import com.evertecinc.b2c.enex.client.model.dto2.RegionAreaDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteCantidadClientesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteComprasAnualesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteListadoClientesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteListadoVentasDTO;
import com.evertecinc.b2c.enex.client.model.entities.Clients;

public interface ClientRepo extends JpaRepository<Clients, Long> {

	public Clients findByUpiAndClientStatusAndClientTypeIn(String upi, String clientStatus, List<String> clientType);

	public Clients findByUpiAndClientStatusAndClientType(String upi, String clientStatus, String clientType);
	
	public List<Clients> findByIdEjecutivo(Long idEjecutivo);
	public List<Clients> findByIdJefeZona(Long idJefeZona);
	
	public Clients findByIdClient(Long idClient);
	
	@Query("""
			SELECT CASE WHEN SUM(v.controlTotal) > 0 THEN 1 ELSE 0 END
				FROM Vehicles v
				JOIN v.client c
				WHERE c.id = :idClient
				AND v.vehicleStatus <> 'E'
			""")
	public Integer controlPassById(@Param("idClient") Long idClient);
	
	@Query("""
		    SELECT new com.evertecinc.b2c.enex.client.model.dto2.ReporteCantidadClientesDTO(
            TO_CHAR(dateIns, 'MM'),
            COALESCE(a1.cantidad, 0),
            COALESCE(a2.cantidad, 0)
    )
		    FROM Clients c
		    LEFT JOIN (
		        SELECT 
		            TO_CHAR(c1.dateIns, 'MM') AS mes, 
		            COUNT(c1.upi) AS cantidad
		        FROM Clients c1
		        WHERE 
		            EXTRACT(YEAR FROM c1.dateIns) = EXTRACT(YEAR FROM CURRENT_DATE) - 1
		            AND c1.clientStatus <> 'E'
		            AND (:tipoCliente IS NULL OR c1.clientType = :tipoCliente)
		        GROUP BY TO_CHAR(c1.dateIns, 'MM')
		    ) a1 ON a1.mes = TO_CHAR(c.dateIns, 'MM')
		    LEFT JOIN (
		        SELECT 
		            TO_CHAR(c2.dateIns, 'MM') AS mes, 
		            COUNT(c2.upi) AS cantidad
		        FROM Clients c2
		        WHERE 
		            EXTRACT(YEAR FROM c2.dateIns) = EXTRACT(YEAR FROM CURRENT_DATE)
		            AND c2.clientStatus <> 'E'
		            AND (:tipoCliente IS NULL OR c2.clientType = :tipoCliente)
		        GROUP BY TO_CHAR(c2.dateIns, 'MM')
		    ) a2 ON a2.mes = TO_CHAR(c.dateIns, 'MM')
		    WHERE 
		        TO_CHAR(c.dateIns, 'MM') = :fecha
		    GROUP BY 
				TO_CHAR(c.dateIns, 'MM'), COALESCE(a1.cantidad, 0), COALESCE(a2.cantidad, 0)
		""")
		public List<ReporteCantidadClientesDTO> findListCantidadClientes(
		        @Param("tipoCliente") String tipoCliente,
		        @Param("fecha") String fecha,
		        Pageable pageable);
	
	@Query("""
		    SELECT new com.evertecinc.b2c.enex.client.model.dto2.ReporteListadoClientesDTO(
		        TO_CHAR(cl.dateIns, 'DD/MM/YYYY') AS fechaCreacion,
		        cl.legalName AS razonSocial,
		        cl.upi AS rut,
		        COUNT(CASE WHEN cd.cardStatus = 'A' THEN cd.idCard END) AS tarjetasActivas,
		        COUNT(CASE WHEN cd.cardStatus <> 'E' THEN cd.idCard END) AS totalTarjetas,
		        cl.clientType AS tipoCliente
		    )
		    FROM Clients cl
		    LEFT JOIN Card cd ON cd.client.idClient = cl.idClient
		    WHERE cl.clientStatus <> 'E'
		    AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
		    AND (CAST(:fechaIni as text) IS NULL or cl.dateIns >= CAST(CAST(:fechaIni as text) as date))
		    AND (CAST(:fechaFin as text) IS NULL or cl.dateIns <= CAST(CAST(:fechaFin as text) as date))
		    GROUP BY cl.dateIns, cl.legalName, cl.upi, cl.clientType
		""")
		List<ReporteListadoClientesDTO> findListUltimosClientes(
		    @Param("tipoCliente") String tipoCliente,
		    @Param("fechaIni") String fechaIni,
		    @Param("fechaFin") String fechaFin,
		    Pageable pageable
		);

	@Query("""
		    SELECT new com.evertecinc.b2c.enex.client.model.dto2.ReporteComprasAnualesDTO( 
		        ABS(SUM(ord.totalOrder)) AS monto, 
		        TO_CHAR(ord.payDate, 'MM') AS mes
		    )
		    FROM Orders ord 
		    JOIN OrderItems oi on ord.idOrder = oi.idOrder
		    JOIN Vehicles v on v.idVehicle = oi.idVehicle
		    JOIN Clients cl ON ord.idClient = cl.idClient
		    WHERE EXTRACT(YEAR FROM ord.payDate) = :fecha
		    AND ord.orderStatus = 'X'
		    AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
		    AND (CAST(:fechaIni as text) IS NULL or cl.dateIns >= CAST(CAST(:fechaIni as text) as date))
		    AND (CAST(:fechaFin as text) IS NULL or cl.dateIns <= CAST(CAST(:fechaFin as text) as date))
		    GROUP BY TO_CHAR(ord.payDate, 'MM')
		""")
		List<ReporteComprasAnualesDTO> findMontoPorMes(
		    @Param("fecha") String fecha,
		    @Param("tipoCliente") String tipoCliente,
		    @Param("fechaIni") String fechaIni,
		    @Param("fechaFin") String fechaFin,
		    Pageable pageable
		);
	
	@Query("""
		    SELECT new com.evertecinc.b2c.enex.client.model.dto2.ReporteListadoVentasDTO(
		        EXTRACT(MONTH FROM o.payDate) AS mes, 
		        EXTRACT(YEAR FROM o.payDate) AS anio, 
		        SUM(o.totalOrder) AS monto, 
		        cl.legalName AS razonSocial, 
		        cl.upi AS rut, 
		        oi.productCode AS producto,
		        o.payType AS medioPago, 
		        o.payDate AS fechaPago)
		    FROM Orders o
		    JOIN Clients cl ON cl.idClient = o.idClient
		    JOIN (
		        SELECT idOrder AS orderId, productCode AS productCode
		        FROM OrderItems 
		        GROUP BY idOrder, productCode
		    ) oi ON o.idOrder = oi.orderId
		    WHERE o.orderStatus = 'X'
		    AND (:fechaIni IS NULL OR o.payDate >= CAST(:fechaIni AS date))
		    AND (:fechaFin IS NULL OR o.payDate <= CAST(:fechaFin AS date))    
		    AND (:tipoCliente IS NULL OR cl.clientType = :tipoCliente)
		    GROUP BY 
		        EXTRACT(MONTH FROM o.payDate), 
		        EXTRACT(YEAR FROM o.payDate), 
		        cl.legalName, 
		        cl.upi, 
		        oi.productCode, 
		        o.payType, 
		        o.payDate
		""")
		List<ReporteListadoVentasDTO> getListadoVentas(
		    @Param("fechaIni") String fechaIni, 
		    @Param("fechaFin") String fechaFin, 
		    @Param("tipoCliente") String tipoCliente, 
		    Pageable pageable);



	@Query("""
		    SELECT new com.evertecinc.b2c.enex.client.model.dto.ClientDTO(
		        rcc.idReporte,
		        c.idClient,
		        c.name,
		        c.upi,
		        c.legalName,
		        c.commercialType,
		        c.contactName,
		        c.contactPhone,
		        c.contactEmail,
		        c.contactJob,
		        c.accountJdeClient,
		        c.accountJdeInvoice,
		        c.accountJdeTicket,
		        c.clientStatus,
		        c.clientType,
		        c.dateIns,
		        c.dateUpd,
		        c.addressStreetName,
		        c.addressStreetNumber,
		        c.addressDisStreetName,
		        c.legalUpi,
		        c.legalCivilStatus,
		        c.legalProfession,
		        c.legalAddress,
		        c.legalPhone,
		        c.legalNamePerson)
		    FROM ReporteContraloriaClientes rcc
		    JOIN Clients c ON rcc.client.idClient = c.idClient
		    WHERE rcc.tipo = 'CON'
		    AND (:clientId IS NULL OR rcc.client.idClient = :clientId)
		""")
		List<ClientDTO> getContraloriaClients(
		    @Param("clientId") Long clientId, Pageable pageable);


	@Query("""
		    SELECT 
		        v.idVehicle AS idVehicle, 
		        v.plate AS plateNumber, 
		        vt.name AS vehicleType, 
		        c.legalName AS legalName, 
		        c.upi AS upi, 
		        p.name AS productName, 
		        v.documentType AS documentType,
		        CASE 
		            WHEN c.clientType <> 'PRE' THEN c.accountJdeClient
		            WHEN v.documentType = 'I' THEN c.accountJdeInvoice
		            ELSE c.accountJdeTicket 
		        END AS cuentaC,
		        c.clientType AS clientType,
		        c.tiporeglamento AS tipoReglamento, 
		        c.clientStatus AS clientStatus
		    FROM Vehicles v
		    JOIN VehicleTypes vt ON v.vehicleType.vehicleTypeCode = vt.vehicleTypeCode
		    JOIN Clients c ON c.idClient = v.client.idClient
		    JOIN Products p ON p.productCode = v.product.productCode
		    WHERE v.idVehicle = :idVehicle
		""")
		CardSafDTO getCardInformation(@Param("idVehicle") Long idVehicle);
	
	@Query("""
		    SELECT 
		        v.idVehicle, 
		        v.plate, 
		        v.vehicleStatus
		    
		    FROM Vehicles v
		    WHERE v.plate = :plate
		      AND v.vehicleStatus IN :listaEstados
		      AND v.idVehicle = :idVehicle
		""")
		List<VehicleDTO> getVehiclesByPlateAndStatus(
		    @Param("plate") String plate, 
		    @Param("listaEstados") List<String> listaEstados, 
		    @Param("idVehicle") Long idVehicle
		);
	
	@Query("""
		    SELECT 
		        r.name as regionNombre, 
		        r.idRegion regionId, 
		        a.idArea areaId, 
		        a.name areaNombre, 
		        c.idCity cityId, 
		        c.name ciudadNombre
		    
		    FROM Region r
		    LEFT JOIN Area a ON a.region.idRegion = r.idRegion
		    LEFT JOIN City c ON c.idCity = a.city.idCity
		    WHERE (:idArea IS NULL OR a.idArea = :idArea)
		      AND (:nameArea IS NULL OR a.name ILIKE CONCAT('%', :nameArea, '%'))
		      AND (:idRegion IS NULL OR r.idRegion = :idRegion)
		      AND (:nameRegion IS NULL OR r.name ILIKE CONCAT('%', :nameRegion, '%'))
		""")
		RegionAreaDTO getRegionByComRegion(
		    @Param("idArea") Long idArea,
		    @Param("nameArea") String nameArea,
		    @Param("idRegion") Long idRegion,
		    @Param("nameRegion") String nameRegion
		);
	
	@Query("""
		    SELECT 
		        cm.idMifare, 
		        cm.status estado, 
		        cm.dateUse fechaUso, 
		        cm.vehicle.idVehicle, 
		        v.plate patente, 
		        cm.cardNumber
		    
		    FROM CardnumberMaster cm
		    LEFT JOIN Vehicles v ON cm.vehicle.idVehicle = v.idVehicle
		    WHERE (:estado IS NULL OR cm.status = :estado)
		      AND (:preambulo IS NULL OR cm.cardNumber LIKE CONCAT(:preambulo, '%'))
		      AND (:mifare IS NULL OR cm.idMifare = :mifare)
		      AND (:idVehicle IS NULL OR cm.vehicle.idVehicle = :idVehicle)

		""")
		List<MonitorMaestroTarjetasDTO> getListCardMaster(
		    @Param("estado") String estado,
		    @Param("preambulo") String preambulo,
		    @Param("mifare") String mifare,
		    @Param("idVehicle") Long idVehicle
		);

	@Query("""
		    SELECT COUNT(cm) 
		    FROM CardMovement cm 
		    WHERE cm.trx = :trx
		""")
		Long getCountTrxCardMovement(@Param("trx") String trx);

}
