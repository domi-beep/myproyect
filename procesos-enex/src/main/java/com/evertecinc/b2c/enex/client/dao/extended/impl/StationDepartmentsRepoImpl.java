package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IStationDepartmentsRepo;
import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;
import com.evertecinc.b2c.enex.client.model.dto.StationsDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.AreaByStationsDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.RegionsByStationDepartmentRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationClientsRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.StationsByCriteriaForDepartmentRequestDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StationDepartmentsRepoImpl implements IStationDepartmentsRepo {

	@PersistenceContext
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<StationsDTO> getConstraintsByDepartment(StationClientsRequestDTO params) {
		
		if (Functions.hasEmptyOrNull(params.getClientType(), params.getIdDepartment())) {
			log.error("Los parametros clientType, idDepartment no pueden ser nulos o vacios.");
			throw new IllegalArgumentException("Los parametros clientType, idDepartment no pueden ser nulos o vacios.");
		}

		TypedQuery<StationsDTO> query;

		List<StationsDTO> response = null;

		try {
			String sqlQuery = """
					SELECT
					 R.idRegion as idRegion,
					 R.name as regionName,
					 A.idArea as idArea,
					 A.name as areaName,
					 S.stationCode as stationCode,
					 S.name name
							FROM StationDepartmentConstraints SD
							LEFT JOIN Stations S ON SD.stationCode = S.stationCode AND S.clientType = :clientType 
							LEFT JOIN Area A ON A.idArea = S.area.idArea AND A.active = 'A'
							LEFT JOIN Region R ON R.idRegion = A.region.idRegion AND R.active = 'A'
							WHERE SD.idDepartment = :idDepartment
							and S.clientType <> 'PRV'

							UNION

						SELECT
						R.idRegion as idRegion,
					 R.name as regionName,
					 A.idArea as idArea,
					 A.name as areaName,
					 S.stationCode as stationCode,
					 S.name as name
						FROM StationDepartmentConstraints SD
						JOIN StationClients SC ON SC.stationCode = SD.stationCode AND SC.idClient = :idClient
						LEFT JOIN Stations S ON SD.stationCode = S.stationCode
						AND S.clientType ILIKE 'PRV'
						LEFT JOIN Area A ON A.idArea = S.area.idArea AND A.active ILIKE 'A'
						LEFT JOIN Region R ON R.idRegion = A.region.idRegion AND R.active ILIKE 'A'
						WHERE SD.department.idDepartment = :idDepartment

						order by idRegion
										""";

			query = entityManager.createQuery(sqlQuery, StationsDTO.class).unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, StationsDTO.class));

			query.setParameter("idClient", params.getIdClient());
			query.setParameter("clientType", params.getClientType());
			query.setParameter("idDepartment", params.getIdDepartment());

			response = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegionDTO> getRegionsByStationsDepartments(RegionsByStationDepartmentRequestDTO params) {
		TypedQuery<RegionDTO> query;

		List<RegionDTO> response = null;

		try {
			String sqlQuery = """
					SELECT 
					    idRegion as idRegion,
					    idZone as idZone,
					    code as code,
					    name as name,
					    active as active,
					    MAX(controlPass) AS controlPass
					FROM (
					    SELECT
					        r.idRegion AS idRegion,
					        r.zone.idZone AS idZone,
					        r.code AS code,
					        r.name AS name,
					        r.active AS active,
					        MAX(s.ct) AS controlPass
					    FROM Region r
					    JOIN Area a ON r.idRegion = a.region.idRegion
					    JOIN Stations s ON a.idArea = s.area.idArea
					        AND (:clientType IS NULL OR s.clientType ILIKE :clientType) 
					    LEFT JOIN StationDepartmentConstraints scc 
					        ON scc.stationCode = s.stationCode 
					        AND scc.idDepartment = :idDepartment
					    WHERE a.active = 'A'
					        AND r.active = 'A'
					        AND (scc.idDepartment IS NULL OR scc.idDepartment != :idDepartment)
					        AND (:controlPass IS NULL OR s.ct = :controlPass)
					    GROUP BY r.idRegion, r.zone.idZone, r.code, r.name, r.active
					
					    UNION ALL
					
					    SELECT
					        r.idRegion AS idRegion,
					        r.zone.idZone AS idZone,
					        r.code AS code,
					        r.name AS name,
					        r.active AS active,
					        MAX(s.ct) AS controlPass
					    FROM Region r
					    JOIN Area a ON r.idRegion = a.region.idRegion
					    JOIN Stations s ON a.idArea = s.area.idArea
					        AND s.clientType = 'PRV'
					    JOIN StationClients sc 
					        ON sc.stationCode = s.stationCode 
					        AND sc.idClient = :idClient 
					    LEFT JOIN StationDepartmentConstraints scc 
					        ON scc.stationCode = s.stationCode 
					        AND scc.idDepartment = :idDepartment
					    WHERE a.active = 'A'
					        AND r.active = 'A'
					        AND (scc.idDepartment IS NULL OR scc.idDepartment != :idDepartment)
					        AND (:controlPass IS NULL OR s.ct = :controlPass)
					    GROUP BY r.idRegion, r.zone.idZone, r.code, r.name, r.active
					) q
					GROUP BY idRegion, idZone, code, name, active
					ORDER BY idRegion

										""";

			query = entityManager.createQuery(sqlQuery, RegionDTO.class).unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, RegionDTO.class));

			query.setParameter("idClient", params.getIdClient() == null ? null : params.getIdClient());
			query.setParameter("clientType", params.getClientType() == null ? null : params.getClientType());
			query.setParameter("idDepartment", params.getIdDepartment() == null ? null : params.getIdDepartment());
			query.setParameter("controlPass", params.getControlPass() == null ? null : params.getControlPass());

			response = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaDTO> getAreaByStationsDepartments(AreaByStationsDepartmentRequestDTO params) {
		TypedQuery<AreaDTO> query;

		List<AreaDTO> response = null;
		try {
			String sqlQuery = """
					SELECT
					    a.idArea AS idArea,
					    a.region.idRegion AS idRegion,
					    a.code AS code,
					    a.name AS name,
					    a.active AS active,
					    a.city.idCity AS idCity,
					    MAX(s.ct) AS controlPass
					FROM Area a
					JOIN Stations s ON a.idArea = s.area.idArea
					    AND (:tipoCliente IS NULL OR s.clientType = :tipoCliente)
					LEFT JOIN StationDepartmentConstraints scc 
					    ON scc.stationCode = s.stationCode
					    AND (:idDepartment IS NULL OR scc.idDepartment = :idDepartment)
					WHERE a.active = 'A'
					    AND (:listaIdRegiones IS NULL OR a.region.idRegion IN(:listaIdRegiones)) 
					    AND (:idDepartment IS NULL OR (scc.idDepartment IS NULL OR scc.idDepartment != :idDepartment))
					    AND (:controlPass IS NULL OR s.ct = :controlPass)
					GROUP BY a.idArea, a.region.idRegion, a.code, a.name, a.active, a.city.idCity
					
					UNION
					
					SELECT
					    a.idArea AS idArea,
					    a.region.idRegion AS idRegion,
					    a.code AS code,
					    a.name AS name,
					    a.active AS active,
					    a.city.idCity AS idCity,
					    MAX(s.ct) AS controlPass
					FROM Area a
					JOIN Stations s ON a.idArea = s.area.idArea
					    AND s.clientType = 'PRV'
					JOIN StationClients sc ON sc.stationCode = s.stationCode
					    AND sc.idClient = :idClient
					LEFT JOIN StationDepartmentConstraints scc 
					    ON scc.stationCode = s.stationCode
					    AND (:idDepartment IS NULL OR scc.idDepartment = :idDepartment)
					WHERE a.active = 'A'
					    AND (:listaIdRegiones IS NULL OR a.region.idRegion IN(:listaIdRegiones))
					    AND (:idDepartment IS NULL OR (scc.idDepartment IS NULL OR scc.idDepartment != :idDepartment)) 
					    AND (:controlPass IS NULL OR s.ct = :controlPass) 
					GROUP BY a.idArea, a.region.idRegion, a.code, a.name, a.active, a.city.idCity
					
					ORDER BY name ASC
										""";

			query = entityManager.createQuery(sqlQuery, AreaDTO.class).unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, AreaDTO.class));

			query.setParameter("idClient", params.getIdClient() == null ? null : params.getIdClient());
			query.setParameter("idDepartment", params.getIdDepartment() == null ? null : params.getIdDepartment());
			query.setParameter("tipoCliente", params.getClientType() == null ? null : params.getClientType());
			query.setParameter("controlPass", params.getControlPass() == null ? null : params.getControlPass());
			
			if(!Functions.hasEmptyOrNull(params.getRegiones())) {
				String strIdRegion = Functions.transformarListaAString(params.getRegiones());
				query.setParameter("listaIdRegiones", strIdRegion);
			}else {
				query.setParameter("listaIdRegiones", null);
			}
			
			response = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StationsDTO> getStationsByCriteriaForDepartment(StationsByCriteriaForDepartmentRequestDTO params) {
		TypedQuery<StationsDTO> query;

		List<StationsDTO> response = null;
		try {
			String sqlQuery = """
		SELECT
			stationCode as stationCode,
			name as name,
			idArea as idArea,
			clientType as clientType,
			controlPass as controlPass,
			latitud as latitud,
			longitud as longitud
		FROM (
		
		
			SELECT 
				S.stationCode as stationCode,
				S.name as name,
				S.area.idArea as idArea, 
				S.clientType as clientType, 
				S.ct as controlPass, 
				S.lat AS latitud, 
				S.longitud AS longitud
			FROM Stations S
			LEFT JOIN StationDepartmentConstraints SDC ON SDC.stationCode = S.stationCode 
				AND  (:idDepartment IS NULL OR SDC.idDepartment = :idDepartment)
			LEFT JOIN Area A ON A.idArea = S.area.idArea AND A.active = 'A'
			LEFT JOIN Region R ON R.idRegion = A.region.idRegion AND R.active = 'A'
			LEFT JOIN Zone Z ON Z.idZone = R.zone.idZone AND Z.active = 'A'
			WHERE
				(:listaIdZonas IS NULL OR Z.idZone IN(:listaIdZonas))
			AND	(:listaIdRegiones IS NULL OR R.idRegion IN(:listaIdRegiones))
			AND (:listaIdAreas IS NULL OR A.idArea IN(:listaIdAreas))
			AND (:idDepartment IS NULL OR SDC.idDepartment = :idDepartment)
			AND (:tipoCliente IS NULL OR S.clientType = :tipoCliente)
			
			AND (:controlPass IS NULL OR S.ct = :controlPass)
			
			UNION
			
			
			SELECT 
				S.stationCode as stationCode,
				S.name as name,
				S.area.idArea as idArea, 
				S.clientType as clientType, 
				S.ct as controlPass, 
				S.lat AS latitud, 
				S.longitud AS longitud
			FROM Stations S
			JOIN StationClients SC ON SC.stationCode = S.stationCode AND SC.idClient = :idClient
			LEFT JOIN StationDepartmentConstraints SDC ON SDC.stationCode = S.stationCode AND SDC.idDepartment = :idDepartment
			LEFT JOIN Area A ON A.idArea = S.area.idArea AND A.active = 'A'
			LEFT JOIN Region R ON R.idRegion = A.region.idRegion AND R.active = 'A'
			LEFT JOIN Zone Z ON Z.idZone = R.zone.idZone AND Z.active = 'A'
			WHERE
			
				(:listaIdZonas IS NULL OR Z.idZone IN(:listaIdZonas))
			AND	(:listaIdRegiones IS NULL OR R.idRegion IN(:listaIdRegiones))
			AND (:listaIdAreas IS NULL OR A.idArea IN(:listaIdAreas))
			AND S.clientType = 'PRV'
			
			AND (:controlPass IS NULL OR S.ct = :controlPass)
			
		)datos
										""";
			
			//TODO LINEA 297 sin sentido aparente:
			//AND (:idDepartment IS NULL OR SDC.idDepartment IS NULL OR SDC.idDepartment != :idDepartment)
			//TODO LINEA 323 sin sentido aparente:
			//AND (:idDepartment IS NULL OR SDC.idDepartment IS NULL OR SDC.idDepartment != :idDepartment)
			
			query = entityManager.createQuery(sqlQuery, StationsDTO.class).unwrap(Query.class)
					.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, StationsDTO.class));

			query.setParameter("idClient", params.getIdClient() == null ? null : params.getIdClient());
			query.setParameter("idDepartment", params.getIdDepartment() == null ? null : params.getIdDepartment());
			query.setParameter("tipoCliente", params.getClientType() == null ? null : params.getClientType());
			query.setParameter("controlPass", params.getControlPass() == null ? null : params.getControlPass());
			
			if(!Functions.hasEmptyOrNull(params.getZonas())) {
				String zonas = Functions.transformarListaAString(params.getZonas());
				query.setParameter("listaIdZonas", zonas);
			}else {
				query.setParameter("listaIdZonas", null);
			}
			if(!Functions.hasEmptyOrNull(params.getRegiones())) {
				String region = Functions.transformarListaAString(params.getRegiones());
				query.setParameter("listaIdRegiones", region);
			}else {
				query.setParameter("listaIdRegiones", null);
			}
			if(!Functions.hasEmptyOrNull(params.getAreas())) {
				String listaIdAreas = Functions.transformarListaAString(params.getAreas());
				query.setParameter("listaIdAreas", listaIdAreas);
			}else {
				query.setParameter("listaIdAreas", null);
			}
			
			response = query.getResultList();

		} catch (Exception e) {
			log.error("ERROR " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

}
