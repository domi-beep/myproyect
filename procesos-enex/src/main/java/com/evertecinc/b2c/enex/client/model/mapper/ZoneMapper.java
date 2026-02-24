package com.evertecinc.b2c.enex.client.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.ZoneDTO;
import com.evertecinc.b2c.enex.client.model.entities.Zone;

@Component
public class ZoneMapper {

    // Método para mapear de Zone a ZoneDTO
    public ZoneDTO toDto(Zone zone) {
        if (zone == null) {
            return null;
        }

        return new ZoneDTO(
                zone.getIdZone(),
                zone.getName(),
                zone.getActive()
        );
    }

    // Método para mapear de ZoneDTO a Zone
    public Zone toEntity(ZoneDTO zoneDTO) {
        if (zoneDTO == null) {
            return null;
        }

        Zone zone = new Zone();
        zone.setIdZone(zoneDTO.getIdZone());
        zone.setName(zoneDTO.getName());
        zone.setActive(zoneDTO.getActive());

        return zone;
    }
}
