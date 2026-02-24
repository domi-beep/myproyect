package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evertecinc.b2c.enex.client.model.dto2.requests.ListDeptHistoryJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.entities.DepartmentHistory;

public interface DepartmentHistoryRepo extends JpaRepository<DepartmentHistory, Long> {
	
	@Query("""
			SELECT
				DH
			FROM
				DepartmentHistory DH
			WHERE
				(:#{#params.idDepartment} is null or DH.idDepartment = :#{#params.idDepartment}) AND
				(:#{#params.idUser} is null or DH.idUser = :#{#params.idUser}) AND
				(:#{#params.actionType} is null or DH.actionType ILIKE :#{#params.actionType}) AND
				(:#{#params.idHistory} is null or DH.idHistory = :#{#params.idHistory}) AND
				(:#{#params.username} is null or DH.username ILIKE CONCAT('%', :#{#params.username}, '%')) AND
				(:#{#params.action} is null or DH.action ILIKE :#{#params.action}) AND
				(CAST(:#{#params.dateFirstFormatted} as text) IS NULL or DH.date >= CAST(CAST(:#{#params.dateFirstFormatted} as text) as date)) AND 
				(CAST(:#{#params.dateEndFormatted} as text) IS NULL or DH.date <= CAST(CAST(:#{#params.dateEndFormatted} as text) as date))
		""")
	public List<DepartmentHistory> getHistoryDepartmentcriterio(@Param("params") ListDeptHistoryJsonCtrlRequestDTO params, Pageable pageable);	
	
	@Query("""
			SELECT
				COUNT(*)
			FROM
				DepartmentHistory DH
			WHERE
				(:#{#params.idDepartment} is null or DH.idDepartment = :#{#params.idDepartment}) AND
				(:#{#params.idUser} is null or DH.idUser = :#{#params.idUser}) AND
				(:#{#params.actionType} is null or DH.actionType ILIKE :#{#params.actionType}) AND
				(:#{#params.idHistory} is null or DH.idHistory = :#{#params.idHistory}) AND
				(:#{#params.username} is null or DH.user.username ILIKE :#{#params.username}) AND
				(:#{#params.action} is null or DH.action ILIKE :#{#params.action}) AND
				(CAST(:#{#params.dateFirstFormatted} as text) IS NULL or DH.date >= CAST(CAST(:#{#params.dateFirstFormatted} as text) as date)) AND 
				(CAST(:#{#params.dateEndFormatted} as text) IS NULL or DH.date <= CAST(CAST(:#{#params.dateEndFormatted} as text) as date))
		""")
	public Long countHistoryDepartmentcriterio(@Param("params") ListDeptHistoryJsonCtrlRequestDTO params);	
	

}
