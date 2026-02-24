package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.ICardUseRepo;
import com.evertecinc.b2c.enex.client.model.dto2.CardUseDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.CardUseTrxRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CardUseRepoImpl implements ICardUseRepo{
	
	@PersistenceContext
	private final EntityManager entityManager;
	

	
	@SuppressWarnings("unchecked")
	@Override
	public List<CardUseDTO> getListCardUseTrx(CardUseTrxRequestDTO params, Pageable pageable) {
		
		TypedQuery<CardUseDTO> query;
		
		List<CardUseDTO> response = null;
		
		try {
			String sqlQuery = """
				SELECT distinct
				cu.idCardUse				AS idCardUse,
				cu.station						AS station,
				s.name 						AS nombreEstacion,
				cu.stationRut					AS stationRut,
				cu.trx							AS trx,
				cu.datetrx						AS dateTrx,
				cu.clientId					AS clientId,
				cu.clientRut 					AS clientRut,
				cu.deptId						AS deptId,
				d.name 						AS deptName,
				cu.cardNumber					AS cardNumber,
				cu.plate						AS plate,
				cu.productCode					AS productCode,
				cu.productName					AS productName,
				cu.unitPrice					AS unitPrice,
				cu.quantity					AS quantity,
				cu.totalPrice					AS totalPrice,
				cu.returnCode					AS returnCode,
				cu.driverRut					AS driverRut,
				cu.odometer					AS odometer,
				cu.amountBalance				AS amountBalance,
				cu.remainingLoads				AS remainingLoads,
				cu.periodType					AS periodType,
				cu.remainingAmount				AS remainingAmount,
				cu.documentNumber				AS documentNumber,
				cu.ct						AS controlTotal,
				cu.dp						AS datapass,
				ci.name 					AS nombreCiudad,
				s.lat 						AS latitud,
				s.longitud					AS longitud,
				re.name 					AS nombreRegion
				FROM CardUse cu JOIN Clients c on cu.clientId in (c.codeorpakclient,c.codeorpakinvoice,c.codeorpakticket)
				LEFT JOIN Stations s on cu.station = s.stationCode
				LEFT JOIN Departments d on cu.deptId in (d.codeorpakclient,d.codeorpakinvoice,d.codeorpakticket) 
				left join Area a on a.idArea = s.area.idArea
				left join City ci on ci.idCity = a.city.idCity
				left join Region re on re.idRegion = ci.region.idRegion
					WHERE
					(cu.returnCode = '0') AND
					(:rutCliente IS NULL OR c.upi = :rutCliente)
					AND
				 
	                (cu.datetrx between :fechaInicio AND :fechaFin) 
	             AND
				(:plate IS NULL OR cu.plate = :plate)

			""" + Functions.getSortString(pageable.getSort(), CardUseDTO.class);
			 
		
			query = entityManager.createQuery(sqlQuery, CardUseDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, CardUseDTO.class));
					
					query.setParameter("rutCliente", params.getRutCliente());
					
					// Definir fechaInicio y fechaFin
			        LocalDateTime fechaInicio = null;
			        LocalDateTime fechaFin = null;
			        if (params.getFecha() != null ) {
			            try {
			                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			                
			                fechaInicio = params.getFecha();
			                fechaFin = params.getFecha().plusDays(1);
			                log.info("fechaInicio " + fechaInicio);
			                log.info("fechaFin " + fechaFin);
			            } catch (DateTimeParseException e) {
			                log.error("Error al parsear la fecha: " + e.getMessage());
			            }
			        }

			        query.setParameter("fechaInicio", fechaInicio);
			        query.setParameter("fechaFin", fechaFin);
			        
			        
					//query.setParameter("fecha", params.getFecha());
					query.setParameter("plate", null);


//					query.setFirstResult((int) pageable.getOffset());
//					query.setMaxResults(pageable.getPageSize());
					
					response  = query.getResultList();

				} catch (Exception e) {
					log.error("ERROR " + e.getMessage());
					e.printStackTrace();
				}
		
		return response;
	}
	
	
	@Override
	public Long getTotalLoadTrxByDay() {
		
		try {
			String sqlQuery = """
		            SELECT COUNT(id_card_use) 
		            FROM card_use 
		            WHERE DATE(datetrx) = CURRENT_DATE;
		        """;

			 return ((Number) entityManager.createNativeQuery(sqlQuery).getSingleResult()).longValue();
		
		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
			return 0L; // Retornar 0 en caso de error
		}
		
	}

}
