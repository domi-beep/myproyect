package com.evertecinc.b2c.enex.client.dao.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evertecinc.b2c.enex.client.model.dto2.MonitorTarjetasImpresas2DTO;
import com.evertecinc.b2c.enex.client.model.dto2.SafReportDTO;
import com.evertecinc.b2c.enex.saf.model.entities.SafQueue;

import jakarta.transaction.Transactional;

public interface SafQueueRepo extends JpaRepository<SafQueue, Long> {
	
	public SafQueue findByIdElementAndType(String idElement, String type);
	
	public List<SafQueue> findByIdElementAndTypeAndStatus(String idElement, String type, String status);
	
	
	@Query("""
		       SELECT new com.evertecinc.b2c.enex.client.model.dto2.SafReportDTO(
		           s.type, 
		           COUNT(s.idQueue), 
		           SUM(s.numRetries), 
		           MIN(s.dateIns)
		       )
		       FROM SafQueue s
		       WHERE s.status = :status
		       AND (:type IS NULL OR s.type = :type)
		       AND (:idElement IS NULL OR s.idElement = :idElement)
		       GROUP BY s.type
		       """)
		public List<SafReportDTO> getSafPendingReport(
		        @Param("idElement") String idElement, 
		        @Param("type") String type, 
		        @Param("status") String status);
	
	@Query("""
		    SELECT 
		        s.idQueue as idQueue,
		        s.idElement as idElement,
		        s.type as type, 
		        s.status as status,
		        s.dateIns as dateIns,
		        s.dateSend as dateSend,
		        s.numRetries as numRetries,
		        s.data as data
		    FROM SafQueue s
		    WHERE s.status = 'P'
		      AND (:type IS NULL OR s.type = :type)
		      AND (:idElement IS NULL OR s.idElement = :idElement)
		      AND (:data IS NULL OR 
		           (:type = 'SRM' AND s.data LIKE (:data || ';%')) OR 
		           (:type IS NOT NULL AND :type != 'SRM' AND s.data = :data))
		      AND (:task IS NULL OR s.task = :task)
		      AND (:taskisnotnull IS NULL OR s.task IS NOT NULL)
		      AND (:listaTipos IS NULL OR s.type IN(:listaTipos))
		""")
		List<SafQueue> getSafByType(
		    @Param("type") String type, 
		    @Param("idElement") String idElement, 
		    @Param("data") String data, 
		    @Param("task") String task, 
		    @Param("taskisnotnull") Boolean taskisnotnull, 
		    @Param("listaTipos") List<String> listaTipos);
	

	@Query("""
		    SELECT 
		        vr.plate as cardPlate,
		        sq.idQueue as idQueue,
		        sq.idElement as uniqueID,
		        Q3.clientType as clientType,
		        Q3.legalName as companyName,
		        Q3.upi as companyRUT,
		        CASE 
		            WHEN ve.documentType = 'I' THEN 'Factura' 
		            ELSE 'Boleta' 
		        END as documentType,
		        CAST('PTSF' AS STRING) as cardType,
		        p.name as productCode,
		        vt.name as vehicleType,
		        Q3.tiporeglamento as tipoReglamento,
		        Q3.clientStatus as clientStatus,
		        CASE 
		            WHEN veh_re.reqType IN ('SIM', 'N') THEN 'Nueva' 
		            WHEN veh_re.reqType IN ('SRM', 'R') THEN 'Reimpresion' 
		            ELSE 'Nueva' 
		        END as tipoImpresion,
		        MAX(veh_re.address) as direccion,
		        a.name as comuna,
		        ci.name as ciudad,
		        rg.name as region,
		        veh_re.name as contactName,
		        veh_re.phone as contactPhone
		    FROM SafQueue sq
		    LEFT JOIN Vehicles ve ON CAST(ve.idVehicle AS STRING) = sq.idElement
		    LEFT JOIN VehicleReqs veh_re ON veh_re.plate = ve.plate 
		    INNER JOIN (
		        SELECT 
		            MAX(vrq.idReq) as idRequeri, 
		            vrq.reqType AS reqType,  
		            vrq.plate AS plate, 
				    sa.idQueue AS idQueue, 
				    MAX(vrq.address) AS address,
				    v.client.idClient AS idClient,
				    MAX(vrq.zone) AS zone, 
				    MAX(vrq.name) AS name, 
				    MAX(vrq.phone) AS phone
		        FROM VehicleReqs vrq
		        LEFT JOIN Vehicles v ON v.plate = vrq.plate AND v.client.idClient = vrq.client.idClient
		        LEFT JOIN SafQueue sa ON CAST(sa.idElement AS STRING) = CAST(v.idVehicle AS STRING)
		        WHERE sa.type = 'GCN' 
		          AND sa.status = 'P'
		          AND v.vehicleStatus = 'A' 
		          AND sa.data = :param1 
		        GROUP BY vrq.plate, sa.idQueue,vrq.reqType,v.client.idClient
		        ORDER BY idRequeri DESC
		    ) vr ON vr.idRequeri = veh_re.idReq
		    JOIN Clients Q3 ON Q3.idClient = vr.idClient
		    LEFT JOIN Area a ON a.idArea = CAST(vr.zone AS INTEGER)
		    LEFT JOIN City ci ON ci.idCity = a.city.idCity
		    LEFT JOIN Region rg ON rg.idRegion = a.region.idRegion
		    JOIN Products p ON p.productCode = ve.product.productCode
		    JOIN VehicleTypes vt ON ve.vehicleType.vehicleTypeCode = vt.vehicleTypeCode
		    WHERE sq.type = 'GCN' 
		      AND sq.status = 'P' 
		      AND ve.vehicleStatus <> 'E' 
		      AND sq.data = :param1
		      AND Q3.clientStatus <> 'E' 
		      AND Q3.idClient = vr.idClient 
		      AND ve.client.idClient = vr.idClient
		    GROUP BY 
		        vr.plate, sq.idQueue, sq.idElement, Q3.clientType, 
		        Q3.legalName, Q3.upi, ve.documentType, p.name, 
		        vt.name, Q3.tiporeglamento, Q3.clientStatus, 
		        vr.reqType, vr.address, a.name, ci.name, rg.name, Q3.idClient,veh_re.address, veh_re.name, veh_re.phone, veh_re.reqType
		    ORDER BY sq.idQueue ASC
		""")
		List<MonitorTarjetasImpresas2DTO> getDataVehicleReqsImpresion2(@Param("param1") String param1);
	
	@Query("""
		    SELECT 
		        sq.idQueue as idQueue,
		        ce.plate as cardPlate, 
		        ce.tipoTarjeta as clientType, 
		        ce.razonSocial as companyName, 
		        ce.rut as companyRUT, 
		        p.name as productCode,
		        'EXT' as cardType
		    FROM SafQueue sq
		    JOIN CardExtReqs ce ON CAST(ce.idCardExt AS STRING) = CAST(sq.idElement AS STRING)
		    LEFT JOIN Products p ON p.productCode = ce.tipoProducto
		    WHERE sq.type = 'GCN' 
		      AND sq.status = 'P' 
		      AND sq.data = 'EXT'
		""")
		List<MonitorTarjetasImpresas2DTO> getDataVehicleReqsImpresionExt();
	
	@Modifying
	@Query("""
	    UPDATE SafQueue sq 
	    SET numRetries = sq.numRetries + 1, 
	        status = :status, 
	        dateSend = CURRENT_TIMESTAMP
	    WHERE sq.idElement = :idElement 
	      AND sq.type = :type 
	      AND sq.status = 'P'
	""")
	void updateSafStatusByElement(
	    @Param("status") String status, 
	    @Param("idElement") Long idElement, 
	    @Param("type") String type
	);
	
//	@Query("""
//		    SELECT 
//		        q.idQueue,
//		        q3.upi rut,
//		        q3.legalName razonSocial,
//		        q2.plate patente,
//		        q3.clientType tipoCliente,
//		        q.dateins fechaIngreso,
//		        q.type tipoSaf,
//		        q2.idVehicle,
//		        c.version,
//		        CONCAT(u.name, ' ', u.firstName) usuarioAut,
//		        vr.dateout dateOut,
//		        q.data,
//		        c.cardnum,
//		        q3.contactPhone,
//		        q.status estado
//		    
//		    FROM SafQueue q
//		    JOIN Vehicles q2 ON q2.idVehicle = q.idElement
//		    JOIN Clients q3 ON q3.idClient = q2.client.idClient
//		    LEFT JOIN Card c ON c.idCard = (
//		        SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(qu.data, ';', 1), ';', 1) 
//		        FROM SafQueue qu WHERE qu.idQueue = q.idQueue
//		    ) AND c.cardStatus <> 'E' AND c.cardStatus <> 'P'
//		    LEFT JOIN VehicleReqs vr ON vr.idReq = (
//		        SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(qu.data, ';', -2), ';', 1) 
//		        FROM SafQueue qu WHERE qu.idQueue = q.idQueue
//		    )
//		    LEFT JOIN Users u ON u.idUser = vr.userAut.idUserAut
//		    WHERE (:tipoSaf IS NULL AND (q.type = 'SIM' OR q.type = 'SRM') OR q.type = :tipoSaf)
//		      AND (:rut IS NULL OR q3.upi LIKE CONCAT('%', :rut, '%'))
//		      AND (:patente IS NULL OR q2.plate LIKE CONCAT('%', :patente, '%'))
//		      AND (:tipoCliente IS NULL OR q3.clientType LIKE CONCAT('%', :tipoCliente, '%'))
//		      AND (:estado IS NULL OR q.status = :estado)
//		      AND (:task IS NULL OR q.task = :task)
//		      AND (:fechaInicio IS NULL OR :fechaFinal IS NULL OR DATE(q.dateins) BETWEEN :fechaInicio AND :fechaFinal)
//		    ORDER BY CASE WHEN :resultadoOrdenado = true THEN :orderBy END,
//		             CASE WHEN :ordenAscendente = true THEN ASC END,
//		             CASE WHEN :ordenAscendente = false THEN DESC END
//		""")
//		List<MonitorImpresionDTO> getListPendingPrintSaf(
//		    @Param("tipoSaf") String tipoSaf,
//		    @Param("rut") String rut,
//		    @Param("patente") String patente,
//		    @Param("tipoCliente") String tipoCliente,
//		    @Param("estado") String estado,
//		    @Param("task") Long task,
//		    @Param("fechaInicio") LocalDateTime fechaInicio,
//		    @Param("fechaFinal") LocalDateTime fechaFinal,
//		    @Param("resultadoOrdenado") Boolean resultadoOrdenado,
//		    @Param("orderBy") String orderBy,
//		    @Param("ordenAscendente") Boolean ordenAscendente
//		);


	@Modifying
    @Transactional
    @Query("UPDATE SafQueue s " +
           "SET s.idElement = :idElement, " +
           "    s.type = :type, " +
           "    s.status = :status, " +
           "    s.dateIns = :dateIns, " +
           "    s.dateSend = :dateSend, " +
           "    s.numRetries = :numRetries, " +
           "    s.data = :data, " +
           "    s.task = :task " +
           "WHERE s.idQueue = :idQueue")
    int updateSafQueue(
        @Param("idQueue") Long idQueue,
        @Param("idElement") String idElement,
        @Param("type") String type,
        @Param("status") String status,
        @Param("dateIns") LocalDateTime dateIns,
        @Param("dateSend") LocalDateTime dateSend,
        @Param("numRetries") Long numRetries,
        @Param("data") String data,
        @Param("task") Long task
    );



}
