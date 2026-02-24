package com.evertecinc.b2c.enex.client.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.AreaDTO;
import com.evertecinc.b2c.enex.client.model.entities.Area;
import com.evertecinc.b2c.enex.client.model.entities.City;
import com.evertecinc.b2c.enex.client.model.entities.Region;

@Component
public class AreaMapper {

    // Método para mapear de Area a AreaDTO
    public AreaDTO toDto(Area area) {
        if (area == null) {
            return null;
        }

        return new AreaDTO(
                area.getIdArea(),
                area.getRegion() != null ? area.getRegion().getIdRegion() : null,
                area.getCode(),
                area.getName(),
                area.getActive(),
                area.getCity() != null ? area.getCity().getIdCity() : null,
                false //                area.getControlPass()
        );
    }

    // Método para mapear de AreaDTO a Area
    public Area toEntity(AreaDTO areaDTO) {
        if (areaDTO == null) {
            return null;
        }

        Area area = new Area();
        area.setIdArea(areaDTO.getIdArea());
        area.setCode(areaDTO.getCode());
        area.setName(areaDTO.getName());
        area.setActive(areaDTO.getActive());
//        area.setControlPass(areaDTO.getControlPass());
        
        // En caso de relaciones complejas, solo asignamos IDs en lugar de entidades completas
        if (areaDTO.getIdRegion() != null) {
            Region region = new Region();
            region.setIdRegion(areaDTO.getIdRegion());
            area.setRegion(region);
        }
        
        if (areaDTO.getIdCity() != null) {
            City city = new City();
            city.setIdCity(areaDTO.getIdCity());
            area.setCity(city);
        }

        return area;
    }
}