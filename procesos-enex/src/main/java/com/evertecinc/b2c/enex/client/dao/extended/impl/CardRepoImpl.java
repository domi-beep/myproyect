package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.ICardRepo;
import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CardMovementDTO;
import com.evertecinc.b2c.enex.client.model.dto2.CardMovementDepartmentAllDTO;
import com.evertecinc.b2c.enex.client.model.dto2.ReporteCargasClientesDTO;
import com.evertecinc.b2c.enex.client.model.dto2.StationConstraintDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetCardsByDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListCardMovementRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListCardRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.MovimientoTarjetaRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ReporteCargasClientesRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CardRepoImpl implements ICardRepo {

	@PersistenceContext
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<CardMovementDepartmentAllDTO> getListCardMovementDepartmentAll(MovimientoTarjetaRequestDTO params, Pageable pageable) {
		TypedQuery<CardMovementDepartmentAllDTO> query;
		
		List<CardMovementDepartmentAllDTO> response = null;

		try {
			String sqlQuery = """
				select
					d.idDepartment 														AS idDepartment,
					d.name																AS nameDepartment,
					cm.idMov															AS idMov,
					c.idCard															AS idCard,
					cm.movement															AS movement,
					cm.dateIn															AS dateIn,
					cm.plate															AS plate,
					CASE WHEN cm.monto IS NOT NULL THEN cm.monto ELSE 0 END				AS monto,
					cm.username															AS username,
					s.name 																AS stationName,
					s.stationCode 														AS stationCode,
					cm.payType															AS payType,
					cm.productCode														AS productCode,
					v.nickname															AS nickname,
					c.version															AS version,
					aa.name 															AS comuna,
					CASE WHEN cm.quantity IS NOT NULL THEN cm.quantity ELSE 0 END		AS quantity,
					cm.driverRut														AS driverRut,
					CASE WHEN cm.odometer IS NOT NULL THEN cm.odometer ELSE '0' END		AS odometer,
					cm.documentNumber													AS documentNumber,
					CASE WHEN cm.performance IS NOT NULL THEN cm.performance ELSE 0 END	AS performance,
					cm.dp 																AS dataPass,
					cm.ct 																AS controlPass,
					cl.clientType 														AS clientType,
					v.documentType 														AS documentType,
					cm.idRef															AS idRef,
					cm.boardPrice														AS boardPrice,
					cm.unitPrice														AS unitPrice
				FROM
				Departments d
				JOIN Vehicles v ON v.department.idDepartment = d.idDepartment AND v.vehicleStatus != 'E'
				JOIN VehicleCard vc ON vc.vehicle.idVehicle = v.idVehicle
				JOIN CardMovement cm ON cm.card.idCard = vc.card.idCard
				JOIN Card c ON c.idCard = cm.card.idCard
				LEFT JOIN Stations s ON s.stationCode = cm.stationCode
				LEFT JOIN Area aa ON aa.idArea = s.area.idArea
				LEFT JOIN Clients cl ON cl.idClient = d.client.idClient
				WHERE
					(c.cardStatus <> 'R' AND v.vehicleStatus <> 'R') AND
					(:idMov IS NULL OR cm.idMov = :idMov) AND
					(:idDepartment IS NULL OR d.idDepartment = :idDepartment) AND
					(:idClient IS NULL OR cl.idClient = :idClient) AND
					(:nameDepartment IS NULL OR d.name = :nameDepartment) AND
					(:movement IS NULL OR cm.movement = :movement) AND
					(:plate IS NULL OR cm.plate = :plate) AND
					(:username IS NULL OR cm.username = :username) AND
					(:nickname IS NULL OR v.nickname = :nickname) AND
					(:driverRut IS NULL OR cm.driverRut = :driverRut) AND
					(:idCard IS NULL OR c.idCard = :idCard) AND
					(:idVehicle IS NULL OR v.idVehicle = :idVehicle) AND
					(:controlPass IS NULL OR cm.ct = :controlPass) AND
					(:dataPass IS NULL OR cm.dp = :dataPass) AND
					(:clientType IS NULL OR s.clientType = :clientType) AND
					(CAST(:fechaIn as text) IS NULL or cm.dateIn >= CAST(CAST(:fechaIn as text) as date)) AND 
					(CAST(:fechaEnd as text) IS NULL or cm.dateIn <= CAST(CAST(:fechaEnd as text) as date)) AND
					(:upi IS NULL OR cl.upi = :upi)
			""";
			
			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), MonitorCardDTO.class);

			query = entityManager.createQuery(sqlQuery, CardMovementDepartmentAllDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, CardMovementDepartmentAllDTO.class));
			
			query.setParameter("idMov", params.getIdMov());
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("nameDepartment", params.getNameDepartment());
			query.setParameter("movement", params.getMovement());

			query.setParameter("fechaIn", params.getDateInFormatted());
			query.setParameter("fechaEnd", params.getDateEndFormatted());

			query.setParameter("plate", params.getPlate());
			query.setParameter("username", params.getUsername());
			query.setParameter("nickname", params.getNickname());
			query.setParameter("driverRut", params.getRutDriver());
			query.setParameter("idCard", params.getIdCard());
			query.setParameter("idVehicle", params.getIdVehicle());
			query.setParameter("controlPass", params.getControlPass());
			query.setParameter("dataPass", params.getDataPass());
			query.setParameter("clientType", params.getClientType());
			query.setParameter("upi", params.getUpi());

			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? 10 : pageable.getPageSize());
			
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Long countListCardMovementDepartmentAll(MovimientoTarjetaRequestDTO params) {
		
		TypedQuery<Long> query;
		
		Long response = null;

		try {
			String sqlQuery = """
				SELECT COUNT(*)
				FROM
				Departments d
				JOIN Vehicles v ON v.department.idDepartment = d.idDepartment AND v.vehicleStatus != 'E'
				JOIN VehicleCard vc ON vc.vehicle.idVehicle = v.idVehicle
				JOIN CardMovement cm ON cm.card.idCard = vc.card.idCard
				JOIN Card c ON c.idCard = cm.card.idCard
				LEFT JOIN Stations s ON s.stationCode = cm.stationCode
				LEFT JOIN Area aa ON aa.idArea = s.area.idArea
				LEFT JOIN Clients cl ON cl.idClient = d.client.idClient
				WHERE
					(c.cardStatus <> 'R' AND v.vehicleStatus <> 'R') AND
					(:idMov IS NULL OR cm.idMov = :idMov) AND
					(:idDepartment IS NULL OR d.idDepartment = :idDepartment) AND
					(:idClient IS NULL OR cl.idClient = :idClient) AND
					(:nameDepartment IS NULL OR d.name = :nameDepartment) AND
					(:movement IS NULL OR cm.movement = :movement) AND
					(:plate IS NULL OR cm.plate = :plate) AND
					(:username IS NULL OR cm.username = :username) AND
					(:nickname IS NULL OR v.nickname = :nickname) AND
					(:driverRut IS NULL OR cm.driverRut = :driverRut) AND
					(:idCard IS NULL OR c.idCard = :idCard) AND
					(:idVehicle IS NULL OR v.idVehicle = :idVehicle) AND
					(:controlPass IS NULL OR cm.ct = :controlPass) AND
					(:dataPass IS NULL OR cm.dp = :dataPass) AND
					(:clientType IS NULL OR s.clientType = :clientType) AND
					(CAST(:fechaIn as text) IS NULL or cm.dateIn >= CAST(CAST(:fechaIn as text) as date)) AND 
					(CAST(:fechaEnd as text) IS NULL or cm.dateIn <= CAST(CAST(:fechaEnd as text) as date)) AND
					(:upi IS NULL OR cl.upi = :upi)
			""";

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("idMov", params.getIdMov());
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("nameDepartment", params.getNameDepartment());
			query.setParameter("movement", params.getMovement());

			// TODO YCORTES implementar fechas between
//					(:dateIni IS NULL OR cm.dateIn between :dateIni AND :dateEnd) AND
			query.setParameter("fechaIn", params.getDateInFormatted());
			query.setParameter("fechaEnd", params.getDateEndFormatted());

			query.setParameter("plate", params.getPlate());
			query.setParameter("username", params.getUsername());
			query.setParameter("nickname", params.getNickname());
			query.setParameter("driverRut", params.getRutDriver());
			query.setParameter("idCard", params.getIdCard());
			query.setParameter("idVehicle", params.getIdVehicle());
			query.setParameter("controlPass", params.getControlPass());
			query.setParameter("dataPass", params.getDataPass());
			query.setParameter("clientType", params.getClientType());
			query.setParameter("upi", params.getUpi());

			response  = query.getSingleResult();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorCardDTO> getListCardsByCriterio(ListCardRequestDTO params, Pageable pageable) {
		TypedQuery<MonitorCardDTO> query;
		
		List<MonitorCardDTO> response = null;

		// YCORTES Revisar con Karen los null generados al hacer uniones de tablas en especial el reqcardReprint
//					CASE WHEN c.reqcardReprint IS NULL THEN 0 ELSE c.reqcardReprint END				AS reqCardReprint,
//					c.reqcardReprint				AS reqCardReprint,
		try {
			String sqlQuery = """
				select
					c.idCard 						AS idCard,
					d.idDepartment					AS idDepartment,
					v.client.idClient				AS idClient,
					d.name 							AS nameDepto,
					c.restrictionType				AS restrictionType,
					c.restrType						AS restrType,
					d.typeBalance					AS typeBalance,
					c.cardnum						AS cardnum,
					c.remainingAmount				AS remainingAmount,
					v.idVehicle						AS idVehicle,
					v.plate							AS plate,
					v.nickname						AS nickname,
					v.documentType					AS documentType,
					p.productCode					AS productCode,
					p.name 							AS productName,
					c.cardStatus					AS cardStatus,
					vt.name 						AS vehicleTypeName,
					c.reqcardStatus					AS reqCardStatus,
					d.departmentStatus				AS departmentStatus,
					c.remainingPeriodAmount			AS remainingPeriodAmount,
					c.remainingTrxLoad				AS remainingTrxLoad,
					(c.reqcardReprint IS NOT NULL AND c.reqcardReprint = 1)				AS reqCardReprint,
					c.version						AS version,
					v.controlTotal					AS controlTotal,
					v.vehicleStatus					AS vehicleStatus,
					c.ctPosition 					AS ctPosition,
					case when v.datapass is null then 0 else v.datapass end as datapass,
					SUM(case when cm.quantity is null then 0 else cm.quantity end) as litros,
					c.restrL						AS restrL,
					c.restrM						AS restrM,
					c.restrX						AS restrX,
					c.restrJ						AS restrJ,
					c.restrV						AS restrV,
					c.restrS						AS restrS,
					c.restrD						AS restrD,
					c.restrHini						AS restrHini,
					c.restrHend						AS restrHend,
					c.restrAmountMax				AS restrAmountMax,
					c.restrDailyMaxLoads			AS restrDailyMaxLoads,
					c.restrDailyMaxQuantity			AS restrDailyMaxQuantity,
					(case
						when (
						select
							COUNT(*)
						from
							SafQueue sq
						where
							sq.status = 'P'
							and sq.type = 'SRM'
							and sq.idElement = cast(v.idVehicle as text)
							and sq.data ilike CONCAT(cast(c.idCard as text),
							';%')) > 0 then true
						else false
					end) as hasReprintSaf,
					v.datachip						AS datachip,
					v.warningStockChannel			AS warningStockChannel,
					v.warningLoadChannel			AS warningLoadChannel,
					v.warningStock					AS warningStock,
					v.warningStockEmail				AS warningStockEmail,
					v.warningLoadEmail				AS warningLoadEmail
				FROM Vehicles v
				JOIN Departments d ON d.idDepartment = v.department.idDepartment
				JOIN Products p ON p.productCode = v.product.productCode
				JOIN VehicleTypes vt ON vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
				LEFT JOIN VehicleCard vc ON vc.vehicle.idVehicle = v.idVehicle
				LEFT JOIN Card c ON c.idCard = vc.card.idCard
				LEFT JOIN CardMovement cm ON c.idCard = cm.card.idCard
				WHERE
					(v.vehicleStatus <> 'E') AND
					(:vehicleTypeCode IS NULL OR vt.vehicleTypeCode = :vehicleTypeCode) AND
					(:estadoTarjeta IS NULL OR c.cardStatus = :estadoTarjeta) AND
					(:plate IS NULL OR v.plate ILIKE :plate) AND
					(:nickname IS NULL OR v.nickname ILIKE :nickname) AND
					(:documentType IS NULL OR v.documentType ILIKE :documentType) AND
					(:idClient IS NULL OR (CASE WHEN v.client.idClient IS NOT NULL THEN v.client.idClient = :idClient END)) AND
					(:cardnum IS NULL OR (CASE WHEN c.cardnum IS NOT NULL THEN c.cardnum LIKE :cardnum END)) AND
					(:sinTarjetas IS NULL OR (CASE WHEN :sinTarjetas IS NOT NULL THEN c.cardnum IS NULL END)) AND
					(:quitarPendientes IS NULL OR c.cardStatus <> 'P')
				GROUP BY
					c.idCard, 
					v.idVehicle,
					d.idDepartment, 
					p.productCode,
					vt.name
				HAVING
					(:idCard IS NULL OR (CASE WHEN c.idCard IS NOT NULL THEN c.idCard = :idCard END)) AND
					(:idVehicle IS NULL OR v.idVehicle = :idVehicle) AND
					(:idDepartment IS NULL OR d.idDepartment = :idDepartment) AND
					(:productCode IS NULL OR p.productCode ILIKE :productCode)
			""";

			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), MonitorCardDTO.class);

//					******* SIN SENTIDO APARENTE ******
//					(:frontOffice IS NULL OR (CASE WHEN :frontOffice IS NOT NULL THEN (C.CARD_STATUS <> 'R' AND V.vehicle_status <> 'R') END)
//					<if test="estadoTarjeta == null"> 
//						AND IFNULL(C.CARD_STATUS,'I') <![CDATA[<>]]> 'E'
//					</if>
//					<if test="estadoTarjeta != null">
//						AND C.CARD_STATUS <![CDATA[<>]]> 'E'
//						AND COALESCE(C.CARD_STATUS,0) = COALESCE((CASE WHEN C.CARDNUM IS NOT NULL THEN #{estadoTarjeta} ELSE C.CARD_STATUS END),0)
//					</if>
//					******* SIN SENTIDO APARENTE FIN ******

			query = entityManager.createQuery(sqlQuery, MonitorCardDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorCardDTO.class));

			query.setParameter("estadoTarjeta", params.getCardStatus());
			query.setParameter("vehicleTypeCode", params.getVehicleTypeCode());
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("idVehicle", params.getIdVehicle());
			query.setParameter("plate", params.getPlate() != null ? "%" + params.getPlate() + "%" : params.getPlate());
			query.setParameter("nickname", params.getNickname() != null ? "%" + params.getNickname() + "%" : params.getNickname());
			query.setParameter("productCode", params.getProductCode() != null ? "%" + params.getProductCode() + "%" : params.getProductCode());
			query.setParameter("documentType", params.getDocumentType() != null ? "%" + params.getDocumentType() + "%" : params.getDocumentType());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("cardnum", params.getCardnum());
			query.setParameter("idCard", params.getIdCard());
			query.setParameter("sinTarjetas", params.getSinTarjetas());
//			query.setParameter("frontOffice", params.getFrontOffice());
			query.setParameter("quitarPendientes", params.getQuitarPendientes());
			
			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());
			
			log.info("PRUEBAAA " + query.getResultList());
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getCOUNTListCardsByCriterio(ListCardRequestDTO params) {
		TypedQuery<Long> query;
		
		Long response = null;
		try {
			String sqlQuery = """
					SELECT COUNT(*) FROM (
				select
					c.idCard 						AS idCard,
					d.idDepartment					AS idDepartment,
					v.client.idClient				AS idClient,
					d.name 							AS nameDepto,
					c.restrictionType				AS restrictionType,
					d.typeBalance					AS typeBalance,
					c.cardnum						AS cardnum,
					c.remainingAmount				AS remainingAmount,
					v.idVehicle						AS idVehicle,
					v.plate							AS plate,
					v.nickname						AS nickname,
					v.documentType					AS documentType,
					p.productCode					AS productCode,
					p.name 							AS productName,
					c.cardStatus					AS cardStatus,
					vt.name 						AS vehicleTypeName,
					c.reqcardStatus					AS reqCardStatus,
					d.departmentStatus				AS departmentStatus,
					c.remainingPeriodAmount			AS remainingPeriodAmount,
					c.remainingTrxLoad				AS remainingTrxLoad,
					(c.reqcardReprint IS NOT NULL AND c.reqcardReprint = 1)				AS reqCardReprint,
					c.version						AS version,
					c.restrDailyMaxQuantity			AS restrDailyMaxQuantity,
					v.controlTotal					AS controlTotal,
					v.vehicleStatus					AS vehicleStatus,
					c.ctPosition 					AS ctPosition,
					case when v.datapass is null then 0 else v.datapass end as datapass,
					SUM(case when cm.quantity is null then 0 else cm.quantity end) as litros
				FROM Vehicles v
				JOIN Departments d ON d.idDepartment = v.department.idDepartment
				JOIN Products p ON p.productCode = v.product.productCode
				JOIN VehicleTypes vt ON vt.vehicleTypeCode = v.vehicleType.vehicleTypeCode
				LEFT JOIN VehicleCard vc ON vc.vehicle.idVehicle = v.idVehicle
				LEFT JOIN Card c ON c.idCard = vc.card.idCard
				LEFT JOIN CardMovement cm ON c.idCard = cm.card.idCard
				WHERE
					(v.vehicleStatus <> 'E') AND
					(:vehicleTypeCode IS NULL OR vt.vehicleTypeCode = :vehicleTypeCode) AND
					(:estadoTarjeta IS NULL OR c.cardStatus = :estadoTarjeta) AND
					(:idDepartment IS NULL OR d.idDepartment = :idDepartment) AND
					(:idVehicle IS NULL OR v.idVehicle = :idVehicle) AND
					(:plate IS NULL OR v.plate ILIKE :plate) AND
					(:nickname IS NULL OR v.nickname ILIKE :nickname) AND
					(:productCode IS NULL OR p.productCode ILIKE :productCode) AND
					(:documentType IS NULL OR v.documentType ILIKE :documentType) AND
					(:idClient IS NULL OR (CASE WHEN v.client.idClient IS NOT NULL THEN v.client.idClient = :idClient END)) AND
					(:cardnum IS NULL OR (CASE WHEN c.cardnum IS NOT NULL THEN c.cardnum LIKE :cardnum END)) AND
					(:idCard IS NULL OR (CASE WHEN c.idCard IS NOT NULL THEN c.idCard = :idCard END)) AND
					(:sinTarjetas IS NULL OR (CASE WHEN :sinTarjetas IS NOT NULL THEN c.cardnum IS NULL END)) AND
					(:quitarPendientes IS NULL OR c.cardStatus <> 'P')
				GROUP BY
					c.idCard, 
					v.idVehicle,
					d.idDepartment, 
					p.productCode,
					vt.name
					) as query
			""";

//					******* SIN SENTIDO APARENTE ******
//					(:frontOffice IS NULL OR (CASE WHEN :frontOffice IS NOT NULL THEN (C.CARD_STATUS <> 'R' AND V.vehicle_status <> 'R') END)
//					<if test="estadoTarjeta == null"> 
//						AND IFNULL(C.CARD_STATUS,'I') <![CDATA[<>]]> 'E'
//					</if>
//					<if test="estadoTarjeta != null">
//						AND C.CARD_STATUS <![CDATA[<>]]> 'E'
//						AND COALESCE(C.CARD_STATUS,0) = COALESCE((CASE WHEN C.CARDNUM IS NOT NULL THEN #{estadoTarjeta} ELSE C.CARD_STATUS END),0)
//					</if>
//					******* SIN SENTIDO APARENTE FIN ******

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);

//			query.setParameter("estadoTarjeta", params.getEstadoTarjeta());
			query.setParameter("estadoTarjeta", params.getCardStatus());
			query.setParameter("vehicleTypeCode", params.getVehicleTypeCode());
			query.setParameter("idDepartment", params.getIdDepartment());
			query.setParameter("idVehicle", params.getIdVehicle());
			query.setParameter("plate", params.getPlate() != null ? "%" + params.getPlate() + "%" : params.getPlate());
			query.setParameter("nickname", params.getNickname() != null ? "%" + params.getNickname() + "%" : params.getNickname());
			query.setParameter("productCode", params.getProductCode() != null ? "%" + params.getProductCode() + "%" : params.getProductCode());
			query.setParameter("documentType", params.getDocumentType() != null ? "%" + params.getDocumentType() + "%" : params.getDocumentType());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("cardnum", params.getCardnum());
			query.setParameter("idCard", params.getIdCard());
			query.setParameter("sinTarjetas", params.getSinTarjetas());
//			query.setParameter("frontOffice", params.getFrontOffice());
			query.setParameter("quitarPendientes", params.getQuitarPendientes());
			
			response  = query.getSingleResult();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StationConstraintDTO> getConstraintsByCard(Long idCard, String clientType, Long idClient) {

		TypedQuery<StationConstraintDTO> query;
		
		List<StationConstraintDTO> response = null;
		try {
			String sqlQuery = """
					SELECT 
						R.idRegion 					AS idRegion,
						R.name 						AS region,
						A.idArea 					AS idArea,
						A.name 						AS area,
						S.stationCode 				AS stationCode,
						S.name 						AS station 
					FROM StationCardConstraint SC
					LEFT JOIN Stations S ON SC.stationCode = S.stationCode
					LEFT JOIN Area A ON A.idArea = S.area.idArea AND A.active = 'A'
					LEFT JOIN Region R ON R.idRegion = A.region.idRegion AND R.active = 'A'
					WHERE SC.card.idCard = :idCard
					and S.clientType = :clientType
					union
					SELECT
						R.idRegion 					AS idRegion,
						R.name 						AS region,
						A.idArea 					AS idArea,
						A.name 						AS area,
						S.stationCode 				AS stationCode,
						S.name 						AS station 
					FROM StationCardConstraint SC
					join StationClients scl on scl.stationCode = SC.stationCode and scl.client.idClient = :idClient
					LEFT JOIN Stations S ON SC.stationCode = S.stationCode 
					LEFT JOIN Area A ON A.idArea = S.area.idArea AND A.active = 'A'
					LEFT JOIN Region R ON R.idRegion = A.region.idRegion AND R.active = 'A'
					WHERE SC.card.idCard = :idCard
					and S.clientType = 'PRV'
					order by idRegion
			""";

			query = entityManager.createQuery(sqlQuery, StationConstraintDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, StationConstraintDTO.class));

			query.setParameter("idCard", idCard);
			query.setParameter("clientType", clientType);
			query.setParameter("idClient", idClient);
			
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CardMovementDTO> getListCardMovement(ListCardMovementRequestDTO params, Pageable pageable) {
		TypedQuery<CardMovementDTO> query;
		
		List<CardMovementDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
						cu.idCardUse as idCardUse,
						s.name as stationName,
						cu.station as stationCode,
						ar.name as area,
						rg.name as region,
						cu.trx as trx,
						cu.datetrx as datetrx,
						cu.clientId as clientId,
						cu.clientRut as clientRut,
						cu.deptId as deptId,
						cu.cardNumber as cardNumber,
						cu.plate as plate,
						cu.productCode as productCode,
						cu.productName as productName,
						cu.unitPrice as unitPrice,
						cu.quantity as quantity,
						cu.totalPrice as totalPrice,
						cu.returnCode as returnCode,
						cu.driverRut as driverRut,
						cu.odometer as odometer,
						cu.amountBalance as amountBalance,
						cu.remainingLoads as remainingLoads,
						cu.periodType as periodType,
						cu.remainingAmount as remainingAmount,
						cu.documentNumber as documentNumber,
						COALESCE(cm.performance, 0) AS performance,
						COALESCE(v.controlTotal, 0) AS ct,
						COALESCE(v.datapass, 0) AS dataPass,
						COALESCE(s.ct, 0) AS ctStation,
						COALESCE(cu.ct, 0) AS tipoCarga,
						COALESCE(cu.dp, 0) AS tipoOdometro,
						cl.clientType as clientType,
						v.documentType as documentType,
						cl.legalName as legalName,
						cm.boardPrice as boardPrice,
						v.nickname as nickName
						from CardUse cu
						left join (SELECT 
						    stationCode as stationCode, 
						    name as name, 
						    area.idArea as idArea, 
						    CASE WHEN SUM(ct) > 0 THEN 1 ELSE 0 END AS ct 
							FROM 
							    Stations 
							GROUP BY 
							    stationCode, name, area.idArea) s on cu.station = s.stationCode
			LEFT JOIN Area ar ON ar.idArea = s.idArea
			LEFT JOIN Region rg ON rg.idRegion = ar.region.idRegion
			LEFT JOIN Clients cl ON cu.clientRut = cl.upi AND 
			(	
				cu.clientId = cl.codeorpakinvoice OR 
				cu.clientId = cl.codeorpakticket OR 
				cu.clientId = cl.codeorpakclient	
			)
            left join Card c on cu.cardNumber = c.cardnum
            left join VehicleCard vc on c.idCard = vc.card.idCard
            left join Vehicles v on vc.vehicle.idVehicle = v.idVehicle
            left join CardMovement cm on cm.trx = cu.trx
            
            WHERE
					(CAST(:fechaIn as text) IS NULL or cu.datetrx >= CAST(CAST(:fechaIn as text) as date)) AND 
					(CAST(:fechaEnd as text) IS NULL or cu.datetrx <= CAST(CAST(:fechaEnd as text) as date)) AND
					(:codeStation IS NULL or cu.station ILIKE :codeStation) AND
					(:upi IS NULL or cu.clientRut ILIKE :upi) AND
					(:plate IS NULL or cu.plate ILIKE :plate) AND
					(:tarjeta IS NULL or cu.cardNumber ILIKE :tarjeta) AND
					(:rutChofer IS NULL or cu.driverRut ILIKE :rutChofer) AND
					(:tipoCliente IS NULL or cl.clientType ILIKE :tipoCliente) AND
					(:razonSocial IS NULL or cl.legalName ILIKE :razonSocial) AND
					(:tipoCarga IS NULL or cu.ct = :tipoCarga) AND
					(:fallidas IS NULL or
						(:fallidas = 0 and cu.returnCode = '0') or
						(:fallidas = 1 and cu.returnCode <> '0')
					)
					 
			""";
			
			

			
			
			 
			
			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), CardMovementDTO.class);

			query = entityManager.createQuery(sqlQuery, CardMovementDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, CardMovementDTO.class));
			
			query.setParameter("fechaIn", params.getDateInFormatted());
			query.setParameter("fechaEnd", params.getDateEndFormatted());
			query.setParameter("codeStation", params.getCodeStation() == null ? null : params.getCodeStation());
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("plate", params.getPlate() == null ? null : "%"+params.getPlate()+"%");
			query.setParameter("tarjeta", params.getTarjeta() == null ? null : "%"+params.getTarjeta()+"%");
			query.setParameter("rutChofer", params.getRutChofer() == null ? null : "%"+params.getRutChofer()+"%");
			query.setParameter("tipoCliente", params.getTipoCliente() == null ? null : "%"+params.getTipoCliente()+"%");
			query.setParameter("razonSocial", params.getRazonSocial() == null ? null : "%"+params.getRazonSocial()+"%");
			query.setParameter("tipoCarga", params.getTipoCarga() == null ? null : params.getTipoCarga());
			query.setParameter("fallidas", params.getFallidas() == null ? null : params.getFallidas());
			
			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());
			
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Long countListCardMovement(ListCardMovementRequestDTO params, Pageable pageable) {
		
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = """
					SELECT COUNT(*)
						from CardUse cu
						left join (SELECT 
						    stationCode as stationCode, 
						    name as name, 
						    area.idArea as idArea, 
						    CASE WHEN SUM(ct) > 0 THEN 1 ELSE 0 END AS ct 
							FROM 
							    Stations 
							GROUP BY 
							    stationCode, name, area.idArea) s on cu.station = s.stationCode
			LEFT JOIN Area ar ON ar.idArea = s.idArea
			LEFT JOIN Region rg ON rg.idRegion = ar.region.idRegion
			LEFT JOIN Clients cl ON cu.clientRut = cl.upi AND 
			(	
				cu.clientId = cl.codeorpakinvoice OR 
				cu.clientId = cl.codeorpakticket OR 
				cu.clientId = cl.codeorpakclient	
			)
            left join Card c on cu.cardNumber = c.cardnum
            left join VehicleCard vc on c.idCard = vc.card.idCard
            left join Vehicles v on vc.vehicle.idVehicle = v.idVehicle
            left join CardMovement cm on cm.trx = cu.trx
            
            WHERE
					(CAST(:fechaIn as text) IS NULL or cu.datetrx >= CAST(CAST(:fechaIn as text) as date)) AND 
					(CAST(:fechaEnd as text) IS NULL or cu.datetrx <= CAST(CAST(:fechaEnd as text) as date)) AND
					(:codeStation IS NULL or cu.station ILIKE :codeStation) AND
					(:upi IS NULL or cu.clientRut ILIKE :upi) AND
					(:plate IS NULL or cu.plate ILIKE :plate) AND
					(:tarjeta IS NULL or cu.cardNumber ILIKE :tarjeta) AND
					(:rutChofer IS NULL or cu.driverRut ILIKE :rutChofer) AND
					(:tipoCliente IS NULL or cl.clientType ILIKE :tipoCliente) AND
					(:razonSocial IS NULL or cl.legalName ILIKE :razonSocial) AND
					(:tipoCarga IS NULL or cu.ct = :tipoCarga) AND
					(:fallidas IS NULL or
						(:fallidas = 0 and cu.returnCode = '0') or
						(:fallidas = 1 and cu.returnCode <> '0')
					)
					 
			""";
			
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("fechaIn", params.getDateInFormatted());
			query.setParameter("fechaEnd", params.getDateEndFormatted());
			query.setParameter("codeStation", params.getCodeStation() == null ? null : params.getCodeStation());
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("plate", params.getPlate() == null ? null : "%"+params.getPlate()+"%");
			query.setParameter("tarjeta", params.getTarjeta() == null ? null : "%"+params.getTarjeta()+"%");
			query.setParameter("rutChofer", params.getRutChofer() == null ? null : "%"+params.getRutChofer()+"%");
			query.setParameter("tipoCliente", params.getTipoCliente() == null ? null : "%"+params.getTipoCliente()+"%");
			query.setParameter("razonSocial", params.getRazonSocial() == null ? null : "%"+params.getRazonSocial()+"%");
			query.setParameter("tipoCarga", params.getTipoCarga() == null ? null : params.getTipoCarga());
			query.setParameter("fallidas", params.getFallidas() == null ? null : params.getFallidas());
			
			response  = query.getSingleResult();
			
		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		log.debug("response: "+response);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteCargasClientesDTO> getListadoCargasClientes(ReporteCargasClientesRequestDTO params, Pageable pageable) {
		TypedQuery<ReporteCargasClientesDTO> query;
		
		List<ReporteCargasClientesDTO> response = null;

		try {
			String sqlQuery = """
			SELECT 
			    cd.client.idClient AS idClient, 
			    c.upi AS upi, 
			    c.legalName AS legalName, 
			    c.clientType AS clientType, 
			    SUM(cm.quantity) AS litrosAcumulados, 
			    ABS(SUM(cm.monto)) AS montoTotalAcumulado,
			    SUM(CASE WHEN cd.cardStatus = 'A' THEN 1 ELSE 0 END) AS tarjetasActivas,
			    SUM(CASE WHEN cd.cardStatus <> 'E' THEN 1 ELSE 0 END) AS totalTarjetas,
			    MAX(cm.dateIn) AS fechaUltimaCarga
			FROM 
			    CardMovement cm
			LEFT JOIN 
			    Card cd ON cm.card.idCard = cd.idCard
			LEFT JOIN 
			    Clients c ON c.idClient = cd.client.idClient
			WHERE 
			    (:movement IS NULL OR cm.movement = :movement)
			    AND (CAST(:fechaIn as text) IS NULL or cm.dateIn >= CAST(CAST(:fechaIn as text) as date))
				AND (CAST(:fechaEnd as text) IS NULL or cm.dateIn <= CAST(CAST(:fechaEnd as text) as date))
			    AND (:upi IS NULL OR c.upi ILIKE :upi)
			    AND (:legalName IS NULL OR c.legalName ILIKE :legalName)
			    AND (:clientType IS NULL OR c.clientType ILIKE :clientType)
			GROUP BY cd.client.idClient, c.upi, c.legalName, c.clientType
			""";
			
			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), ReporteCargasClientesDTO.class);

			query = entityManager.createQuery(sqlQuery, ReporteCargasClientesDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ReporteCargasClientesDTO.class));
			
			query.setParameter("fechaIn", params.getDateInFormatted());
			query.setParameter("fechaEnd", params.getDateEndFormatted());
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
			query.setParameter("clientType", params.getClientType() == null ? null : "%"+params.getClientType()+"%");
			query.setParameter("movement", params.getMovement() == null ? null : "%"+params.getMovement()+"%");
			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());
			
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Long countListadoCargasClientes(ReporteCargasClientesRequestDTO params, Pageable pageable) {
		TypedQuery<Long> query;
		Long response = null;

		try {
			String sqlQuery = """
			SELECT COUNT(*) FROM(
					SELECT 
			    cd.client.idClient AS idClient, 
			    c.upi AS upi, 
			    c.legalName AS legalName, 
			    c.clientType AS clientType, 
			    SUM(cm.quantity) AS litrosAcumulados, 
			    ABS(SUM(cm.monto)) AS montoTotalAcumulado,
			    SUM(CASE WHEN cd.cardStatus = 'A' THEN 1 ELSE 0 END) AS tarjetasActivas,
			    SUM(CASE WHEN cd.cardStatus <> 'E' THEN 1 ELSE 0 END) AS totalTarjetas,
			    MAX(cm.dateIn) AS fechaUltimaCarga
			FROM 
			    CardMovement cm
			LEFT JOIN 
			    Card cd ON cm.card.idCard = cd.idCard
			LEFT JOIN 
			    Clients c ON c.idClient = cd.client.idClient
			WHERE 
			    (:movement IS NULL OR cm.movement = :movement)
			    AND (CAST(:fechaIn as text) IS NULL or cm.dateIn >= CAST(CAST(:fechaIn as text) as date))
				AND (CAST(:fechaEnd as text) IS NULL or cm.dateIn <= CAST(CAST(:fechaEnd as text) as date))
			    AND (:upi IS NULL OR c.upi ILIKE :upi)
			    AND (:legalName IS NULL OR c.legalName ILIKE :legalName)
			    AND (:clientType IS NULL OR c.clientType ILIKE :clientType)
			GROUP BY cd.client.idClient, c.upi, c.legalName, c.clientType
			) datos
			""";
			
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("fechaIn", params.getDateInFormatted());
			query.setParameter("fechaEnd", params.getDateEndFormatted());
			query.setParameter("upi", params.getUpi() == null ? null : "%"+params.getUpi()+"%");
			query.setParameter("legalName", params.getLegalName() == null ? null : "%"+params.getLegalName()+"%");
			query.setParameter("clientType", params.getClientType() == null ? null : "%"+params.getClientType()+"%");
			query.setParameter("movement", params.getMovement() == null ? null : "%"+params.getMovement()+"%");
			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());
			
			response  = query.getSingleResult();
			
		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		log.debug("response: "+response);
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorCardDTO> getCardVehicleByDepartment(Long idDepartment) {
		TypedQuery<MonitorCardDTO> query;
		
		List<MonitorCardDTO> response = null;

		try {
			String sqlQuery = """
				SELECT 
					C.idCard 				as idCard,
					D.idDepartment 			as idDepartment,
					D.name 					as nameDepto,
					C.restrictionType 		as restrictionType,
					D.typeBalance 			as typeBalance,
					C.cardnum 				as cardnum,
					C.remainingAmount 		as remainingAmount,
					V.idVehicle 			as idVehicle,
					V.plate 				as plate,
					V.nickname 				as nickname,
					V.documentType 			as documentType,
					V.product.productCode 	as productCode,
					C.cardStatus 			as cardStatus,
					C.reqcardStatus 		as reqCardStatus
				FROM
				Vehicles V
					JOIN Departments D ON D.idDepartment = V.department.idDepartment 
					JOIN VehicleCard VC ON VC.vehicle.idVehicle = V.idVehicle
					JOIN Card C ON C.idCard = VC.card.idCard and C.cardStatus <> 'E' and C.cardStatus <> 'P'
				WHERE
					D.departmentStatus = 'A'
					AND V.vehicleStatus = 'A'
					AND D.idDepartment = :idDepartment
			""";


			query = entityManager.createQuery(sqlQuery, MonitorCardDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, MonitorCardDTO.class));

			query.setParameter("idDepartment", idDepartment);
			
			query.setMaxResults(Integer.MAX_VALUE);
			
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CardDTO> getCardsByDepartment(GetCardsByDepartmentRequestDTO params, Pageable pageable) {
		
		if(Functions.hasEmptyOrNull(pageable))
			pageable = PageRequest.of(0, Integer.MAX_VALUE);

		TypedQuery<CardDTO> query;
		
		List<CardDTO> response = null;

		try {
			String sqlQuery = """
						SELECT 
							c.idCard 							AS idCard,
							c.client.idClient 							AS idClient,
							c.cardnum 							AS cardNum,
							c.remainingAmount 					AS remainingAmount,
							c.crtdate 							AS crtDate,
							c.upddate 							AS updDate,
							c.cardStatus 						AS cardStatus,
							c.restrType 						AS restrType,
							c.restrL 							AS restrL,
							c.restrM 							AS restrM,
							c.restrX 							AS restrX,
							c.restrJ 							AS restrJ,
							c.restrV 							AS restrV,
							c.restrS 							AS restrS,
							c.restrD 							AS restrD,
							c.restrHini 						AS restrHini,
							c.restrHend 						AS restrHend,
							c.restrAmountMax 					AS restrAmountMax,
							c.restrDailyMaxLoads 				AS restrDailyMaxLoads,
							c.restrDailyMaxQuantity 			AS restrDailyMaxQuantity,
							c.restrictionType 					AS restrictionType,
							c.version 							AS version
						FROM
						Vehicles v
						JOIN Departments d ON d.idDepartment = v.department.idDepartment 
						JOIN VehicleCard vc ON vc.vehicle.idVehicle = v.idVehicle
						JOIN Card c ON c.idCard = vc.card.idCard and c.cardStatus <> 'E'
						WHERE
						v.vehicleStatus = 'A'
					    AND (
					        :actDepto IS NULL OR 
					        (:actDepto = true AND d.departmentStatus IN ('A', 'I')) OR 
					        (:actDepto = false AND d.departmentStatus = 'A'))
					    AND d.idDepartment = :idDepto
					""";

			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), CardDTO.class);

			query = entityManager.createQuery(sqlQuery, CardDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, CardDTO.class));

			query.setParameter("idDepto", params.getIdDepartment());
			query.setParameter("actDepto", params.getActDepto() != null ? params.getActDepto().booleanValue() : null);

			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());

			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public void updateCardByDepartment(Long idDepartment) {
		try {
			String sqlQuery = """
					UPDATE Card SET cardStatus = 'E', upddate = :now
					WHERE idCard IN (
					    SELECT vc.card.idCard
					    FROM Vehicles v
					    JOIN Departments d ON d.idDepartment = v.department.idDepartment
					    JOIN VehicleCard vc ON vc.vehicle.idVehicle = v.idVehicle
					    WHERE v.vehicleStatus = 'A'
					    AND d.idDepartment = :idDepartment
					)
					""";

			// Crear la consulta
			jakarta.persistence.Query query = entityManager.createNativeQuery(sqlQuery);
			query.setParameter("now", LocalDateTime.now());
			query.setParameter("idDepartment", idDepartment);

			// Ejecutar la actualización
			int updatedCount = query.executeUpdate();
			log.info("Updated {} cards for department ID {}", updatedCount, idDepartment);

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage(), e);
		}
	}

}
