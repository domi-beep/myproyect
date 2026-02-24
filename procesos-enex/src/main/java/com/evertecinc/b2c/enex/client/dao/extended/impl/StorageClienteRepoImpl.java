package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IStorageCliente;
import com.evertecinc.b2c.enex.client.model.dto2.ContratoStorageDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class StorageClienteRepoImpl implements IStorageCliente {

	@PersistenceContext
	private final EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<ContratoStorageDTO> getListStorageCliente(Long idClient) {
		TypedQuery<ContratoStorageDTO> query;
		List<ContratoStorageDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT 
						sc.idStoCliente 			as idStoCliente,
						sc.client.idClient 				as idClient,
						sc.fecha	 				as fechaPersoneria,
						sc.fecha	 				as fechaPersoneriaString,
						sc.notaria 					as notariaPersoneria,
						sc.notario 					as notarioPersoneria,
						sc.dateIns 					as fechaIns,
						sc.dateUpd 					as fechaUpd,
						sc.usrIns 					as usuarioIns,
						sc.usrUpd 					as usuarioUpd,
						scs.id.stationCode				as codigoEstacion,
						scs.litros 					as metrosCubicos,
						st.name 					as nombreEstacionFile,
						aST.name 					as nombreAreaFile,
						rST.name					as nombreRegionFile,
						cl.legalAddress			as direccionCliente,
						cl.addressStreetName 		as nombreCalleCliente,
						cl.addressStreetNumber 	as numeroCalleCliente,
						aCL.name					as nombreAreaCliente,
						rCL.name					as nombreRegionCliente
					FROM StorageClient sc
					INNER JOIN StorageClientStation scs on scs.client.idClient =  sc.client.idClient
					INNER JOIN Clients cl on cl.idClient = sc.client.idClient
					INNER JOIN Stations st on st.stationCode = scs.id.stationCode
					INNER JOIN Area aST on aST.idArea = st.area.idArea
					INNER JOIN Region rST on rST.idRegion = aST.region.idRegion
					INNER JOIN Area aCL on aCL.idArea = cl.addressDisIdArea
					INNER JOIN Region rCL on rCL.idRegion = cl.addressIdRegion1
					WHERE 
					(:idClient IS NULL OR sc.client.idClient = :idClient)
					group by 
					sc.idStoCliente,
					sc.client.idClient,
					sc.fecha,
					sc.notaria,
					sc.notario,
					sc.dateIns,
					sc.dateUpd,
					sc.usrIns,
					sc.usrUpd,
					scs.id.stationCode,
					scs.litros,
					st.name,
					aST.name,
					rST.name,
					cl.legalAddress,
					cl.addressStreetName,
					cl.addressStreetNumber,
					aCL.name,
					rCL.name
					
			""") ;
			
			query = entityManager.createQuery(sqlQuery.toString(), ContratoStorageDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ContratoStorageDTO.class));
			
			query.setParameter("idClient", idClient == null ? null : idClient);

			response  = query.getResultList();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		log.debug("response: "+response);
		return response;
	}
	
}
