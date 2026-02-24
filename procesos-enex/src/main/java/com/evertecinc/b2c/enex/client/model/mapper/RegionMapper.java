package com.evertecinc.b2c.enex.client.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.RegionDTO;
import com.evertecinc.b2c.enex.client.model.entities.Region;
import com.evertecinc.b2c.enex.client.model.entities.Zone;

@Component
public class RegionMapper {

    // Método para mapear de Region a RegionDTO
    public RegionDTO toDto(Region region) {
        if (region == null) {
            return null;
        }

        return new RegionDTO(
                region.getIdRegion(),
                region.getZone() != null ? region.getZone().getIdZone() : null,
                region.getCode(),
                region.getName(),
                region.getActive(),
                false // Conversión a Boolean
        );
    }

    // Método para mapear de RegionDTO a Region
    public Region toEntity(RegionDTO regionDTO) {
        if (regionDTO == null) {
            return null;
        }

        Region region = new Region();
        region.setIdRegion(regionDTO.getIdRegion());
        region.setCode(regionDTO.getCode());
        region.setName(regionDTO.getName());
        region.setActive(regionDTO.getActive());

        // Asignación del ID de Zone si está presente
        if (regionDTO.getIdZone() != null) {
            Zone zone = new Zone();
            zone.setIdZone(regionDTO.getIdZone());
            region.setZone(zone);
        }

        return region;
    }
}
