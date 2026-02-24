package com.evertecinc.b2c.enex.client.dao.extended.impl;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.classes.utils.db.DbUtils;
import com.evertecinc.b2c.enex.client.dao.extended.IContentsRepo;
import com.evertecinc.b2c.enex.client.model.dto2.ImagenDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ImagenCriterioDTO;
import com.evertecinc.b2c.enex.utils.functions.Functions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContentsRepoImpl implements IContentsRepo {

	@PersistenceContext
	private final EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenDTO> getImagenesBannerByCriterio(ImagenCriterioDTO params,
			Pageable paging) {
		TypedQuery<ImagenDTO> query;
		List<ImagenDTO> response = null;
		
		try {
			String sqlQuery = new String("""
					SELECT
						i.idImagen as idImagen,
						i.nombre as nombre,
						i.estado as estado,
						i.orden as orden,
						i.fechaInicio as fechaInicio,
						i.fechaFin as fechaFin,
						i.nuevaVentana as nuevaVentana,
						i.url as url,
						i.imagenWeb as imagenWeb,
						i.imagenMobile as imagenMobile,
						i.reloj as reloj
					FROM Imagenes i 
					join ImagenesBanner ib on i.idImagen = ib.id.idImagen
					WHERE
						AND i.estado <>	'E' AND
						(:idImagen IS NULL OR i.idImagen = :idImagen) AND
						(:idBanner IS NULL OR ib.id.idbanner = :idBanner) AND
						(:estado IS NULL OR i.estado = :estado) AND
						(:idVigente = false OR (i.fechaInicio <= CURRENT_TIMESTAMP AND CURRENT_TIMESTAMP <= i.fechaFin))
			""") ;
			
			if(paging.getSort() != null)
				sqlQuery += Functions.getSortString(paging.getSort(), ImagenDTO.class);
			
			query = entityManager.createQuery(sqlQuery.toString(), ImagenDTO.class)
			.unwrap(Query.class)
			.setTupleTransformer((tuple, aliases) -> DbUtils.objFromFields(tuple, aliases, ImagenDTO.class));
			
			query.setParameter("idImagen", params.getIdImagen() == null ? null : params.getIdImagen());
			query.setParameter("idBanner", params.getIdBanner() == null ? null : params.getIdBanner());
			query.setParameter("estado", params.getEstado() == null ? null : params.getEstado());
			query.setParameter("idVigente", params.getIsVigente() == true  ? true : false);
			

			query.setFirstResult(paging.isUnpaged() ? 0 : (int) paging.getOffset());
			query.setMaxResults(paging.isUnpaged() ? Integer.MAX_VALUE : paging.getPageSize());
			
			response  = query.getResultList();
			
		}catch(Exception ex) {
			log.error("ERROR " + ex.getMessage());
			ex.printStackTrace();
		}
		
		log.debug("response: "+response);
		return response;
	}
	
	

}
