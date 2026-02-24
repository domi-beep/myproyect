package com.evertecinc.b2c.enex.client.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.CityDTO;
import com.evertecinc.b2c.enex.client.model.entities.City;
import com.evertecinc.b2c.enex.client.model.entities.Region;

@Component
public class CityMapper {

    // Método para mapear de City a CityDTO
    public CityDTO toDto(City city) {
        if (city == null) {
            return null;
        }

        return new CityDTO(
                city.getIdCity(),
                city.getRegion() != null ? city.getRegion().getIdRegion() : null,
                city.getCode(),
                city.getName(),
                city.getActive()
        );
    }

    // Método para mapear de CityDTO a City
    public City toEntity(CityDTO cityDTO) {
        if (cityDTO == null) {
            return null;
        }

        City city = new City();
        city.setIdCity(cityDTO.getIdCity());
        city.setCode(cityDTO.getCode());
        city.setName(cityDTO.getName());
        city.setActive(cityDTO.getActive());

        // Asignación del ID de Region si está presente
        if (cityDTO.getIdRegion() != null) {
            Region region = new Region();
            region.setIdRegion(cityDTO.getIdRegion());
            city.setRegion(region);
        }

        return city;
    }
}
