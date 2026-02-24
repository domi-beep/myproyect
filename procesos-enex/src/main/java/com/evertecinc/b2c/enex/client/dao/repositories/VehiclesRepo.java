package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.evertecinc.b2c.enex.client.model.dto2.requests.VehiclesRequestDTO;
import com.evertecinc.b2c.enex.client.model.entities.Vehicles;

public interface VehiclesRepo extends JpaRepository<Vehicles, Long> {

	@Query("""
				SELECT
					v
				FROM
					Vehicles v
				WHERE v.vehicleStatus <> 'E' AND
					(:#{#params.idVehicle} IS NULL OR v.idVehicle = :#{#params.idVehicle}) AND
					(:#{#params.idClient} IS NULL OR v.client.idClient = :#{#params.idClient}) AND
					(:#{#params.idDepartment} IS NULL OR v.department.idDepartment = :#{#params.idDepartment}) AND
					(:#{#params.plate} IS NULL OR v.plate ILIKE :#{#params.plate}) AND
					(:#{#params.nickname} IS NULL OR v.nickname = :#{#params.nickname}) AND
					(:#{#params.vehicleStatus} IS NULL OR v.vehicleStatus = :#{#params.vehicleStatus}) AND
					(:#{#params.documentType} IS NULL OR v.documentType = :#{#params.documentType}) AND
					(:#{#params.controlTotal} IS NULL OR v.controlTotal = :#{#params.controlTotal}) AND
					(:#{#params.vehicleTypeCode} IS NULL OR v.vehicleType.vehicleTypeCode = :#{#params.vehicleTypeCode}) AND
					(:#{#params.productCode} IS NULL OR v.product.productCode = :#{#params.productCode}) AND
					(:#{#params.clientType} IS NULL OR v.client.clientType = :#{#params.clientType})
			""")
	public List<Vehicles> getVehiclesByCriterio(@Param("params") VehiclesRequestDTO params, Pageable pageable);

	@Query("""
				SELECT
					COUNT(*)
				FROM
					Vehicles v
				WHERE v.vehicleStatus <> 'E' AND
					(:#{#params.idVehicle} IS NULL OR v.idVehicle = :#{#params.idVehicle}) AND
					(:#{#params.idClient} IS NULL OR v.client.idClient = :#{#params.idClient}) AND
					(:#{#params.idDepartment} IS NULL OR v.department.idDepartment = :#{#params.idDepartment}) AND
					(:#{#params.plate} IS NULL OR v.plate ILIKE :#{#params.plate}) AND
					(:#{#params.nickname} IS NULL OR v.nickname = :#{#params.nickname}) AND
					(:#{#params.vehicleStatus} IS NULL OR v.vehicleStatus = :#{#params.vehicleStatus}) AND
					(:#{#params.documentType} IS NULL OR v.documentType = :#{#params.documentType}) AND
					(:#{#params.controlTotal} IS NULL OR v.controlTotal = :#{#params.controlTotal}) AND
					(:#{#params.vehicleTypeCode} IS NULL OR v.vehicleType.vehicleTypeCode = :#{#params.vehicleTypeCode}) AND
					(:#{#params.productCode} IS NULL OR v.product.productCode = :#{#params.productCode}) AND
					(:#{#params.clientType} IS NULL OR v.client.clientType = :#{#params.clientType})
			""")
	public Long getCOUNTVehiclesByCriterio(@Param("params") VehiclesRequestDTO params);
	
	public Vehicles getByPlate(String plate);
	
	// Método que busca por ID del departamento y por un conjunto de estados
    public List<Vehicles> findByDepartmentIdDepartmentAndVehicleStatusIn(Long idDepartment, List<String> vehicleStatuses);
    
    public Vehicles findVehiclesByIdVehicle(Long idVehicle);
    

}
