package com.evertecinc.b2c.enex.client.dao.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evertecinc.b2c.enex.client.model.dto2.requests.DoAgregarUsuarioJsonCtrlRequestDTO;
import com.evertecinc.b2c.enex.client.model.dto2.requests.ListUsuariosBORequestDTO;
import com.evertecinc.b2c.enex.client.model.entities.Users;

public interface UsersRepo extends JpaRepository<Users, Long> {
	
	public Users findUserByRutAndRoleAndStatus(String rut, Integer role, String status);

	public Users findUserByRutAndStatus(String rut, String status);
	
	public Users findByIdUser(Long idUser);
	
	@Query("""
			select
				u
			from 
				Users u
			where
				(:#{#params.name} is null or u.name Ilike :#{#params.name}) and
				(:#{#params.firstName} is null or u.firstName Ilike :#{#params.firstName}) and
				(:#{#params.lastName} is null or u.lastName Ilike :#{#params.lastName}) and
				(:#{#params.mail} is null or u.email = :#{#params.mail}) and
				(:#{#params.rut} is null or u.rut = :#{#params.rut})
		""")
	public Users findUserByCriterio(@Param("params") DoAgregarUsuarioJsonCtrlRequestDTO params);
	
	@Query("""
	        select
	            u
	        from 
	            Users u
	        where
	            (:#{#params.name} is null or u.name ILIKE CONCAT('%', :#{#params.name}, '%')) and
	            (:#{#params.firstName} is null or u.firstName ILIKE CONCAT('%', :#{#params.firstName}, '%')) and
	            (:#{#params.lastName} is null or u.lastName ILIKE CONCAT('%', :#{#params.lastName}, '%')) and
	            (:#{#params.mail} is null or u.email ILIKE CONCAT('%', :#{#params.mail}, '%')) and
	            (:#{#params.rut} is null or u.rut ILIKE CONCAT('%', :#{#params.rut}, '%')) and
	            (:#{#params.status} is null or u.status = :#{#params.status}) and
	            (:#{#params.role} is null or u.role = :#{#params.role}) and
	            u.role not in (0, 1, 2)
	    """)
	
	public List<Users> findListUsersByCriterio(@Param("params") ListUsuariosBORequestDTO params, Pageable pageable);
	
	@Query("""
			select
				COUNT(*)
			from 
				Users u
			where
	            (:#{#params.name} is null or u.name ILIKE CONCAT('%', :#{#params.name}, '%')) and
	            (:#{#params.firstName} is null or u.firstName ILIKE CONCAT('%', :#{#params.firstName}, '%')) and
	            (:#{#params.lastName} is null or u.lastName ILIKE CONCAT('%', :#{#params.lastName}, '%')) and
	            (:#{#params.mail} is null or u.email ILIKE CONCAT('%', :#{#params.mail}, '%')) and
	            (:#{#params.rut} is null or u.rut ILIKE CONCAT('%', :#{#params.rut}, '%')) and
	            (:#{#params.status} is null or u.status = :#{#params.status}) and
	            (:#{#params.role} is null or u.role = :#{#params.role}) and
	            u.role not in (0, 1, 2)
		""")
	
	public Long countListUsersByCriterio(@Param("params") ListUsuariosBORequestDTO params);
	
	public Users findUsersByIdUser(Long idUser);

	public List<Users> findUsersByStatusAndRoleNotIn(String status, List<Long> role);

	public List<Users> findUsersByStatusAndRoleIn(String status, List<Long> role);

	public List<Users> findUsersByStatusAndStatuskcAndRoleNotInAndUsernameNotNullAndEmailNotNull(String status,
			String statuskc, List<Long> role);

	public List<Users> findUsersByStatusAndStatuskcAndUsernameNotNullAndEmailNotNull(String status, String statuskc);
	



	@Query("""
			    select u
			    from Users u
			    where u.status = 'A' and ( 
			    u.dateLastLogin is null
			       or u.dateLastLogin < :fechaHoraMaximoInactividad or
			       u.fechaCreacion = null)
			""")
	public List<Users> findInactiveOrNeverLogged(@Param("fechaHoraMaximoInactividad") LocalDateTime fechaHoraMaximoInactividad);


	

}
