package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IStationRepo;
import com.evertecinc.b2c.enex.client.model.dto2.StationAreaDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListStationJsonRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StationRepoImpl implements IStationRepo {

	@PersistenceContext
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<StationAreaDTO> getStationArea(ListStationJsonRequestDTO params, Pageable pageable) {
		TypedQuery<StationAreaDTO> query;

		List<StationAreaDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
						s.name as nameStation,
						s.stationCode as  codeStation,
						a.name  as  nameArea,
						a.code  as  codeArea,
						r.name  as  nameRegion,
						r.code  as  codeRegion,
						c.name  as  nameCity,
						s.clientType as clientType
					from Stations s 
					inner join Area a on a.idArea = s.area.idArea
					inner join Region r on r.idRegion = a.region.idRegion
					inner join City c on c.idCity = a.city.idCity
					where a.active='A'
						and r.active='A'
						and c.active='A' AND
						(:clientType IS NULL OR s.clientType = :clientType) AND
						(:nameStation IS NULL OR s.name ILIKE :nameStation) AND
						(:nameArea IS NULL OR a.name ILIKE :nameArea) AND
						(:nameRegion IS NULL OR r.name ILIKE :nameRegion) AND
						(:nameCity IS NULL OR c.name ILIKE :nameCity) AND
						(:codeStation IS NULL OR s.stationCode = :codeStation)
					""";


			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), StationAreaDTO.class);

			query = entityManager.createQuery(sqlQuery, StationAreaDTO.class).unwrap(Query.class)
					.setTupleTransformer(
							(tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, StationAreaDTO.class));
			
			query.setParameter("clientType", params.getClientType());
			query.setParameter("nameStation", Functions.iLikeOrNull(params.getNameStation()));
			query.setParameter("nameArea", Functions.iLikeOrNull(params.getNameArea()));
			query.setParameter("nameRegion", Functions.iLikeOrNull(params.getNameRegion()));
			query.setParameter("nameCity", Functions.iLikeOrNull(params.getNameCity()));
			query.setParameter("codeStation", params.getCodeStation());

			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? Integer.MAX_VALUE : pageable.getPageSize());

			response = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getCOUNTStationArea(ListStationJsonRequestDTO params) {
		TypedQuery<Long> query;
		Long response = null;
		
		try {
			String sqlQuery = """
					SELECT COUNT(*) FROM (
					SELECT
						s.name as nameStation,
						s.stationCode as  codeStation,
						a.name  as  nameArea,
						a.code  as  codeArea,
						r.name  as  nameRegion,
						r.code  as  codeRegion,
						c.name  as  nameCity
					from Stations s 
					inner join Area a on a.idArea = s.area.idArea
					inner join Region r on r.idRegion = a.region.idRegion
					inner join City c on c.idCity = a.city.idCity
					where a.active='A'
						and r.active='A'
						and c.active='A' AND
						(:clientType IS NULL OR s.clientType = :clientType) AND
						(:nameStation IS NULL OR s.name ILIKE :nameStation) AND
						(:nameArea IS NULL OR a.name ILIKE :nameArea) AND
						(:nameRegion IS NULL OR r.name ILIKE :nameRegion) AND
						(:nameCity IS NULL OR c.name ILIKE :nameCity) AND
						(:codeStation IS NULL OR s.stationCode = :codeStation)
					) AS query
					""";
			
			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);
			
			query.setParameter("clientType", params.getClientType());
			query.setParameter("nameStation", Functions.iLikeOrNull(params.getNameStation()));
			query.setParameter("nameArea", Functions.iLikeOrNull(params.getNameArea()));
			query.setParameter("nameRegion", Functions.iLikeOrNull(params.getNameRegion()));
			query.setParameter("nameCity", Functions.iLikeOrNull(params.getNameCity()));
			query.setParameter("codeStation", params.getCodeStation());
			
			response  = query.getSingleResult();
			
		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
}
