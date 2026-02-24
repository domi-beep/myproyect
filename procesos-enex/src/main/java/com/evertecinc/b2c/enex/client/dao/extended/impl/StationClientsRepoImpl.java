package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IStationClientsRepo;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationClientsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationsRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StationClientsRepoImpl implements IStationClientsRepo {

	@PersistenceContext
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<StationsDTO> getStationClientsByCriterio(StationClientsRequestDTO params) {
		TypedQuery<StationsDTO> query;

		List<StationsDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
					    s.stationCode 				AS stationCode,
					    s.name 						AS name,
					    s.area.idArea 				AS idArea,
					    s.clientType 				AS clientType,
					    s.ct 						AS controlPass,
					    s.lat 						AS latitud,
					    s.longitud 					AS longitud,
					    a.name	 					AS areaName,
					    r.name 						AS regionName,
					    sc.client.idClient 			AS idClient
					FROM
					    Stations s
					JOIN StationClients sc ON
					    sc.stationCode = s.stationCode AND
						(:idClient IS NULL OR sc.client.idClient = :idClient)
					LEFT JOIN Area a ON
					    a.idArea = s.area.idArea
					    AND a.active = 'A'
					LEFT JOIN Region r ON
					    r.idRegion = a.region.idRegion
					    AND r.active = 'A'
					LEFT JOIN Zone z ON
					    z.idZone = r.zone.idZone
					    AND z.active = 'A'
					where
						(:clientType IS NULL OR s.clientType = :clientType)
					order by name asc
					""";

			query = entityManager.createQuery(sqlQuery, StationsDTO.class).unwrap(Query.class)
					.setTupleTransformer(
							(tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, StationsDTO.class));

			query.setParameter("idClient", params.getIdClient());
			query.setParameter("clientType", params.getClientType());

			response = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StationsDTO> getStationClientsByCriterioPaged(StationClientsRequestDTO params, Pageable pageable) {
		TypedQuery<StationsDTO> query;

		List<StationsDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
					    s.stationCode 				AS stationCode,
					    s.name 						AS name,
					    s.area.idArea 				AS idArea,
					    s.clientType 				AS clientType,
					    s.ct 						AS controlPass,
					    s.lat 						AS latitud,
					    s.longitud 					AS longitud,
					    a.name	 					AS areaName,
					    r.name 						AS regionName,
					    sc.client.idClient 			AS idClient
					FROM
					    Stations s
					JOIN StationClients sc ON
					    sc.stationCode = s.stationCode AND
						(:idClient IS NULL OR sc.client.idClient = :idClient)
					LEFT JOIN Area a ON
					    a.idArea = s.area.idArea
					    AND a.active = 'A'
					LEFT JOIN Region r ON
					    r.idRegion = a.region.idRegion
					    AND r.active = 'A'
					LEFT JOIN Zone z ON
					    z.idZone = r.zone.idZone
					    AND z.active = 'A'
					where
						(:clientType IS NULL OR s.clientType = :clientType)
					order by name asc
					""";

			if(pageable.getSort() != null)
				sqlQuery += Functions.getSortString(pageable.getSort(), StationsDTO.class);
			
			query = entityManager.createQuery(sqlQuery, StationsDTO.class).unwrap(Query.class)
					.setTupleTransformer(
							(tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, StationsDTO.class));

			query.setParameter("idClient", params.getIdClient());
			query.setParameter("clientType", params.getClientType());
			
			query.setFirstResult(pageable.isUnpaged() ? 0 : (int) pageable.getOffset());
			query.setMaxResults(pageable.isUnpaged() ? 10 : pageable.getPageSize());

			response = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getCountStationClientsByCriterioPaged(StationClientsRequestDTO params) {
		TypedQuery<Long> query;

		Long response = null;

		try {
			String sqlQuery = """
					SELECT COUNT(*) AS totalCount
					FROM
					    Stations s
					JOIN StationClients sc ON
					    sc.stationCode = s.stationCode AND
						(:idClient IS NULL OR sc.client.idClient = :idClient)
					LEFT JOIN Area a ON
					    a.idArea = s.area.idArea
					    AND a.active = 'A'
					LEFT JOIN Region r ON
					    r.idRegion = a.region.idRegion
					    AND r.active = 'A'
					LEFT JOIN Zone z ON
					    z.idZone = r.zone.idZone
					    AND z.active = 'A'
					where
						(:clientType IS NULL OR s.clientType = :clientType)
					""";

			query = (TypedQuery<Long>) entityManager.createQuery(sqlQuery);

			query.setParameter("idClient", params.getIdClient());
			query.setParameter("clientType", params.getClientType());
			
			response  = query.getSingleResult();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<StationsDTO> stationsUnique() {
		TypedQuery<StationsDTO> query;

		List<StationsDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
						st.stationCode AS stationCode,
						st.name AS name,
						st.area.idArea AS idArea,
						st.clientType AS clientType,
						st.ct AS controlTotal,
						a.name AS areaName,
						r.name AS regionName,
						st.lat AS latitud,
						st.longitud AS longitud,
						st.codeElectrolinera AS codeElectrolinera
					FROM Stations st
					INNER JOIN Area a ON a.idArea = st.area.idArea
					INNER JOIN Region r ON r.idRegion = a.region.idRegion
					WHERE st.clientType <> "PRV"
					GROUP BY 
						st.stationCode,
						st.name,
						st.area.idArea,
						st.clientType,
						st.ct,
						a.name,
						r.name
							
					""";

			query = entityManager.createQuery(sqlQuery, StationsDTO.class).unwrap(Query.class)
					.setTupleTransformer(
							(tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, StationsDTO.class));


			response = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StationsDTO> getStationClientsTerritoryByCriterio(StationsRequestDTO params) {
		TypedQuery<StationsDTO> query;
		List<StationsDTO> response = null;
		
		try {
			StringBuilder sqlQuery = new StringBuilder("""
					
					SELECT
						S.STATION_CODE stationCode,
						S.NAME name,
						S.ID_AREA idArea,
						S.client_type clientType,
						S.ct as controlPass,
						s.lat AS latitud,
						s.longitud AS longitud
					FROM Stations S
			""") ;
			
			if(params.getContieneEstaciones()) {
				sqlQuery.append("""
						JOIN StationClients SC ON SC.stationCode = S.stationCode
						""");
			}
			
			sqlQuery.append("""
					
					LEFT JOIN Area A ON A.idArea = S.area.idArea
					LEFT JOIN Region R ON R.idRegion = A.region.idRegion
					LEFT JOIN Zone Z ON Z.idZone = R.zone.idZone
					
					WHERE (:areaStatus IS NULL OR A.active = :areaStatus)
					AND (:regionStatus IS NULL OR R.active = :regionStatus)
					AND (:zoneStatus IS NULL OR Z.active = :zoneStatus)
					AND (:clientType IS NULL OR S.clientType = :clientType)
					
					""");
			if(params.getIdRegion() != null) {
				sqlQuery.append("""
						AND A.region.idRegion IN(:listaIdRegion)
						""");
			}
			
			if(params.getIdZone() != null) {
				sqlQuery.append("""
						AND Z.idZone IN(:listaIdZona)
						""");
			}
			
			if(params.getIdZone() != null) {
				sqlQuery.append("""
						AND S.area.idArea IN(:listaIdArea)
						""");
			}
			
			if(params.getContieneEstaciones()) {
				sqlQuery.append("""
						AND (:idClient IS NULL OR SC.id_client = :idClient)
						""");
			}
			
			query = entityManager.createQuery(sqlQuery.toString(), StationsDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, StationsDTO.class));
			
			query.setParameter("idClient", params.getIdClient() == null ? null : params.getIdClient());
			query.setParameter("areaStatus", params.getAreaStatus() == null ? null : params.getAreaStatus());
			query.setParameter("regionStatus", params.getRegionStatus() == null ? null : params.getRegionStatus());
			query.setParameter("zoneStatus", params.getZoneStatus() == null ? null : params.getZoneStatus());
			query.setParameter("clientType", params.getClientType() == null ? null : params.getClientType());
			
			if(!Functions.hasEmptyOrNull(params.getIdRegion())) {
				String strIdRegion = Functions.transformarListaAString(params.getIdRegion());
				query.setParameter("listaIdRegion", strIdRegion);
			}
			
			if(!Functions.hasEmptyOrNull(params.getIdZone())) {
				String strIdZone = Functions.transformarListaAString(params.getIdZone());
				query.setParameter("listaIdZona", strIdZone);
			}
			
			if(!Functions.hasEmptyOrNull(params.getIdArea())) {
				String strIdArea = Functions.transformarListaAString(params.getIdArea());
				query.setParameter("listaIdArea", strIdArea);
			}
			
			
			query.setMaxResults(Integer.MAX_VALUE);
			
			response  = query.getResultList();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		log.debug("response: "+response);
		return response;
	}
	
}
