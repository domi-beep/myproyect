package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evertecinc.b2c.enex.client.model.dto.CardDTO;
import com.evertecinc.b2c.enex.client.model.dto.MonitorCardDTO;
import com.evertecinc.b2c.enex.client.model.entities.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {

	Cards findByIdCard(Long idCard);
	
	@Query("SELECT c FROM Cards c WHERE c.idClient = :idElement")
    List<Cards> findByDepartment(Long idElement);
	
	@Query(value = """
	        SELECT 
	            C.id_card AS idCard,
	            D.id_department AS idDepartment,
	            D.name AS nameDepto,
	            C.restriction_type AS restrictionType,
	            D.type_balance AS typeBalance,
	            C.cardnum AS numTarjeta,
	            C.remaining_amount AS saldoTarjeta,
	            V.id_vehicle AS idVehicle,
	            V.plate AS patente,
	            V.nickname AS username,
	            V.document_type AS tipoDocumento,
	            V.product_code AS codigoProducto,
	            C.card_status AS estadoTarjeta,
	            C.reqcard_status AS reqCardStatus
	        FROM vehicles V
	        JOIN departments D ON D.id_department = V.id_department 
	        JOIN vehicle_card VC ON VC.id_vehicle = V.id_vehicle
	        JOIN cards C ON C.id_card = VC.id_card 
	            AND C.card_status <> 'E' 
	            AND C.card_status <> 'P'
	        WHERE D.department_status = 'A'
	          AND V.vehicle_status = 'A'
	          AND D.id_department = :idDepartment
	        """, nativeQuery = true)
	List<MonitorCardDTO> getCardVehicleByDepartment(@Param("idDepartment") Long idDepartment);

	
	@Query(value = """
	        SELECT 
	            C.id_card AS idCard,
	            C.id_client AS idClient,
	            C.cardnum AS cardnum,
	            C.remaining_amount AS remainingAmount,
	            C.crtdate AS crtdate,
	            C.upddate AS upddate,
	            C.card_status AS cardStatus,
	            C.restr_L AS restrL,
	            C.restr_M AS restrM,
	            C.restr_X AS restrX,
	            C.restr_J AS restrJ,
	            C.restr_V AS restrV,
	            C.restr_S AS restrS,
	            C.restr_D AS restrD,
	            C.restr_hini AS restrHini,
	            C.restr_hend AS restrHend,
	            C.restr_amount_max AS restrAmountMax,
	            C.restr_daily_max_loads AS restrDailyMaxLoads,
	            C.restr_daily_max_quantity AS restrDailyMaxQuantity,
	            C.restriction_type AS restrictionType,
	            C.version AS version,
	            C.reqcard_status AS reqcardStatus,
	            C.reqcard_reprint AS reqCardReprint,
	            C.ct_position AS ctPosition
	        FROM vehicles V
	        JOIN vehicle_card VC ON VC.id_vehicle = V.id_vehicle
	        JOIN cards C ON C.id_card = VC.id_card 
	        WHERE V.id_vehicle = :idVehicle
	        AND C.card_status <> 'E'
	        AND (:posicion IS NULL AND C.ct_position IS NULL OR :posicion IS NOT NULL AND C.ct_position = :posicion)
	        """, nativeQuery = true)
	List<CardDTO> getDeviceByVehicle(Long idVehicle, String posicion);
	
	@Query("""
		    SELECT c
		    FROM Cards c
		    JOIN VehicleCard vc ON vc.card.idCard = c.idCard
		    JOIN Vehicles v ON vc.vehicle.idVehicle = v.idVehicle
		    WHERE v.idVehicle = :idVehicle
		      AND c.cardStatus <> 'E'
		""")
		List<Cards> findCardsByVehicle(@Param("idVehicle") Long idVehicle);


}
