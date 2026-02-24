package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IGeoRepo;
import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetAreaByStationsRequestExtendedDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetRegionsByStationsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.GetStationsByCriteriaForCardRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class GeoRepoImpl implements IGeoRepo {

	@PersistenceContext
	private final EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegionDTO> getRegionsByStations(GetRegionsByStationsRequestDTO params) {
		TypedQuery<RegionDTO> query;
		
		List<RegionDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
						r.idRegion 												AS idRegion,
						r.zone.idZone 											AS idZone,
						r.code 													AS code,
						r.name 													AS name,
						r.active 												AS active,
						CASE WHEN max(s.ct) = 1 THEN true ELSE false END		AS controlPass
					FROM Region r
					JOIN Area a ON r.idRegion = a.region.idRegion
					JOIN Stations s ON a.idArea = s.area.idArea and s.clientType = :clientType
					LEFT JOIN StationCardConstraint scc ON scc.stationCode = s.stationCode and scc.card.idCard = :idCard
					WHERE
						a.active = 'A'
						AND r.active = 'A' AND
						(:idCard IS NULL OR (scc.card.idCard IS NULL OR scc.card.idCard != :idCard)) AND
						(:controlPass IS NULL OR s.ct = :controlPass)
						group by idRegion
						order by r.idRegion
			""";

			query = entityManager.createQuery(sqlQuery, RegionDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, RegionDTO.class));

			query.setParameter("idCard", params.getIdCard());
			query.setParameter("clientType", params.getClientType());
			query.setParameter("controlPass", params.isControlPass());
			
			response  = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaDTO> getAreaByStations(GetAreaByStationsRequestExtendedDTO params) {
		TypedQuery<AreaDTO> query;
		
		List<AreaDTO> response = null;

		try {
			String sqlQuery = """
				SELECT DISTINCT
					a.idArea 												as idArea,
					a.region.idRegion 										as idRegion,
					a.code 													as code,
					a.name 													as name,
					a.active 												as active,
					a.city.idCity 											as idCity,
					CASE WHEN max(s.ct) = 1 THEN true ELSE false END 		AS controlPass
				FROM Area a
				join Stations s on a.idArea = s.area.idArea
				left join StationCardConstraint scc on scc.stationCode = s.stationCode and (:idCard IS NULL OR scc.card.idCard = :idCard)
				where a.active = 'A' AND
				(:idRegion IS NULL OR a.region.idRegion = :idRegion) AND
				(:clientType IS NULL OR s.clientType = :clientType) AND
				(:idCard IS NULL OR (scc.card.idCard IS NULL OR scc.card.idCard <> :idCard)) AND
				(:controlPass IS NULL OR s.ct = :controlPass)
				group by idArea
				UNION
				SELECT DISTINCT
					a.idArea 												AS idArea,
					a.region.idRegion 										AS idRegion,
					a.code 													AS code,
					a.name 													AS name,
					a.active 												AS active,
					a.city.idCity 											AS idCity,
					CASE WHEN max(s.ct) = 1 THEN true ELSE false END 		AS controlPass
				FROM Area a
				join Stations s on a.idArea = s.area.idArea and s.clientType = 'PRV'
				join StationClients sc ON sc.stationCode = s.stationCode AND sc.client.idClient = :idClient
				left join StationCardConstraint scc on scc.stationCode = s.stationCode and scc.card.idCard = :idCard
				where a.active = 'A' AND
				(:idRegion IS NULL OR a.region.idRegion = :idRegion) AND
				(:idCard IS NULL OR (scc.card.idCard IS NULL OR scc.card.idCard <> :idCard)) AND
				(:controlPass IS NULL OR s.ct = :controlPass)
				group by idArea
				order by name asc
			""";
			
			query = entityManager.createQuery(sqlQuery, AreaDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, AreaDTO.class));
			
			query.setParameter("idCard", params.getIdCard());
			query.setParameter("idClient", params.getIdClient());
			query.setParameter("idRegion", params.getIdRegion());
			query.setParameter("clientType", params.getClientType());
			query.setParameter("controlPass", params.getControlPass());
			
			response  = query.getResultList();
			
		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StationsDTO> getStationsByCriteria(GetStationsByCriteriaForCardRequestDTO params) {
		TypedQuery<StationsDTO> query;
		
		List<StationsDTO> response = null;
		
		try {
			String sqlQuery = """
				    SELECT DISTINCT
				        s.stationCode 			AS stationCode,
				        s.name 					AS name,
				        s.area.idArea 			AS idArea,
				        s.clientType 			AS clientType,
				        s.ct 					AS controlPass
				    FROM Stations s
				    LEFT JOIN StationCardConstraint scc ON scc.stationCode = s.stationCode AND (:idCard IS NULL OR scc.card.idCard = :idCard)
				    LEFT JOIN Area a ON a.idArea = s.area.idArea AND a.active = 'A'
				    LEFT JOIN Region r ON r.idRegion = a.region.idRegion AND r.active = 'A'
				    LEFT JOIN Zone z ON z.idZone = r.zone.idZone AND z.active = 'A'
				    WHERE
						(:zoneIDsFlagIsNull IS true OR z.idZone IN (:zoneIDs)) AND
						(:regionIDsFlagIsNull IS true OR r.idRegion IN (:regionIDs)) AND
				        (:areaIDsFlagIsNull IS true OR a.idArea IN (:areaIDs)) AND
				        (:clientType IS NULL OR s.clientType = :clientType) AND
				        (:idCard IS NULL OR (scc.card.idCard IS NULL OR scc.card.idCard <> :idCard)) AND
				        (:controlPass IS NULL OR s.ct = :controlPass)
				    UNION
				    SELECT DISTINCT
				        s.stationCode 			AS stationCode,
				        s.name 					AS name,
				        s.area.idArea 			AS idArea,
				        s.clientType 			AS clientType,
				        s.ct 					AS controlPass
				    FROM Stations s
				    JOIN StationClients sc ON sc.stationCode = s.stationCode AND sc.client.idClient = :idClient AND s.clientType = 'PRV'
				    LEFT JOIN StationCardConstraint scc ON scc.stationCode = s.stationCode AND (:idCard IS NULL OR scc.card.idCard = :idCard)
				    LEFT JOIN Area a ON a.idArea = s.area.idArea AND a.active = 'A'
				    LEFT JOIN Region r ON r.idRegion = a.region.idRegion AND r.active = 'A'
				    LEFT JOIN Zone z ON z.idZone = r.zone.idZone AND z.active = 'A'
				    WHERE
				        (:zoneIDsFlagIsNull is true or z.idZone in (:zoneIDs)) AND
				        (:regionIDsFlagIsNull IS true OR r.idRegion IN (:regionIDs)) AND
				        (:areaIDsFlagIsNull IS true OR a.idArea IN (:areaIDs)) AND
				        (:idCard IS NULL OR (scc.card.idCard IS NULL OR scc.card.idCard <> :idCard)) AND
				        (:controlPass IS NULL OR s.ct = :controlPass)
				    ORDER BY name ASC
					""";

			
			query = entityManager.createQuery(sqlQuery, StationsDTO.class)
					.unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, StationsDTO.class));
			
			List<Long> zoneIDs = params.getZoneIDs();
			boolean zoneIDsFlagIsNull = Functions.hasEmptyOrNull(zoneIDs);

			List<Long> regionIDs = params.getRegionIDs();
			boolean regionIDsFlagIsNull = Functions.hasEmptyOrNull(regionIDs);

			List<Long> areaIDs = params.getAreaIDs();
			boolean areaIDsFlagIsNull = Functions.hasEmptyOrNull(areaIDs);
			
			query.setParameter("zoneIDsFlagIsNull", zoneIDsFlagIsNull);
			query.setParameter("zoneIDs", zoneIDs);

			query.setParameter("regionIDsFlagIsNull", regionIDsFlagIsNull);
			query.setParameter("regionIDs", regionIDs);

			query.setParameter("areaIDsFlagIsNull", areaIDsFlagIsNull);
			query.setParameter("areaIDs", areaIDs);

			query.setParameter("clientType", params.getClientType());
			query.setParameter("idCard", params.getIdCard());
			query.setParameter("controlPass", params.getControlPass());
			query.setParameter("idClient", params.getIdClient());			
			response  = query.getResultList();
			
		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
}
