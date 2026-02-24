package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.ICardsHistoryRepo;
import com.evertecinc.b2c.enex.client.model.dto2.CardsHistoryDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListCardHistoryJsonRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CardsHistoryRepoImpl implements ICardsHistoryRepo {
	
	@PersistenceContext
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<CardsHistoryDTO> getListCardHistoryByCriteria(ListCardHistoryJsonRequestDTO params, Pageable pageable) {
		TypedQuery<CardsHistoryDTO> query;
		
		List<CardsHistoryDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
							h.idHistory 					AS idHistory,
							h.card.idCard 					AS idCard,
							h.user.idUser 					AS idUser,
							h.username 						AS username,
							h.date 							AS date,
							h.action 						AS action,
							h.actionType 					AS actionType,
							d.name 							AS name,
							d.typeBalance 					AS typeBalance,
							c.cardStatus 					AS cardStatus,
							c.cardnum 						AS cardnum
					from CardsHistory h
					LEFT JOIN Card c on h.card.idCard = c.idCard
					left join VehicleCard vc on vc.card.idCard = c.idCard
					left join Vehicles v on v.idVehicle = vc.vehicle.idVehicle
					left join Departments d on d.idDepartment = v.department.idDepartment
					where
						(:idUser IS NULL OR h.user.idUser = :idUser) AND
						(:idCard IS NULL OR h.card.idCard = :idCard) AND
						(:idVehicle IS NULL OR v.idVehicle = :idVehicle) AND
						(:username IS NULL OR h.username ILIKE :username) AND
						(:action IS NULL OR h.actionType = :action) AND
						(:typeBalance IS NULL OR d.typeBalance = :typeBalance) AND
						(:nombreDepartamento IS NULL OR d.name ILIKE :nombreDepartamento) AND
						(:plate IS NULL OR v.plate ILIKE :plate) AND
						(CAST(:dateFirst as text) IS NULL or h.date >= CAST(CAST(:dateFirst as text) as date)) AND 
						(CAST(:dateEnd as text) IS NULL or h.date <= CAST(CAST(:dateEnd as text) as date))
			""";

			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), CardsHistoryDTO.class);

			query = entityManager.createQuery(sqlQuery, CardsHistoryDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, CardsHistoryDTO.class));
			
			query.setParameter("idUser", params.getIdUser());
			query.setParameter("idCard", params.getIdCard());
			query.setParameter("idVehicle", params.getIdVehicle());
			query.setParameter("username", params.getUsername() == null ? null : "%" + params.getUsername() + "%");
			query.setParameter("action", Functions.hasEmptyOrNull(params.getAction()) ? null : params.getAction());
			query.setParameter("typeBalance", params.getTypeBalance());
			query.setParameter("nombreDepartamento", params.getNameDepartment());
			query.setParameter("plate", params.getPlate());

			query.setParameter("dateFirst", params.getDateFirstLDT());
			query.setParameter("dateEnd", params.getDateEndLDT());

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
	public Long getCOUNTListCardHistoryByCriteria(ListCardHistoryJsonRequestDTO params) {
		TypedQuery<Long> query;
		
		Long response = null;
		
		try {
			String sqlQuery = """
					SELECT COUNT(*) FROM (
					SELECT
							h.idHistory 					AS idHistory,
							h.card.idCard 					AS idCard,
							h.user.idUser 					AS idUser,
							h.username 						AS username,
							h.date 							AS date,
							h.action 						AS action,
							h.actionType 					AS actionType,
							d.name 							AS name,
							d.typeBalance 					AS typeBalance,
							c.cardStatus 					AS cardStatus,
							c.cardnum 						AS cardnum
					from CardsHistory h
					LEFT JOIN Card c on h.card.idCard = c.idCard
					left join VehicleCard vc on vc.card.idCard = c.idCard
					left join Vehicles v on v.idVehicle = vc.vehicle.idVehicle
					left join Departments d on d.idDepartment = v.department.idDepartment
					where
						(:idUser IS NULL OR h.user.idUser = :idUser) AND
						(:idCard IS NULL OR h.card.idCard = :idCard) AND
						(:idVehicle IS NULL OR v.idVehicle = :idVehicle) AND
						(:username IS NULL OR h.username ILIKE :username) AND
						(:action IS NULL OR h.actionType = :action) AND
						(:typeBalance IS NULL OR d.typeBalance = :typeBalance) AND
						(:nombreDepartamento IS NULL OR d.name ILIKE :nombreDepartamento) AND
						(:plate IS NULL OR v.plate ILIKE :plate) AND
						(CAST(:dateFirst as text) IS NULL or h.date >= CAST(CAST(:dateFirst as text) as date)) AND 
						(CAST(:dateEnd as text) IS NULL or h.date <= CAST(CAST(:dateEnd as text) as date))
					) AS query
			""";

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("idUser", params.getIdUser());
			query.setParameter("idCard", params.getIdCard());
			query.setParameter("idVehicle", params.getIdVehicle());
			query.setParameter("username", params.getUsername());
			query.setParameter("action", params.getAction());
			query.setParameter("typeBalance", params.getTypeBalance());
			query.setParameter("nombreDepartamento", params.getNameDepartment());
			query.setParameter("plate", params.getPlate());

			query.setParameter("dateFirst", params.getDateFirstLDT());
			query.setParameter("dateEnd", params.getDateEndLDT());
			
			response  = query.getSingleResult();
			
		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

}
