package com.evertecinc.b2c.enex.client.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.JefeZonaDTO;
import com.evertecinc.b2c.enex.client.model.entities.JefeZona;

@Component
public class JefeZonaMapper {

    // Método para mapear de JefeZona a JefeZonaDTO
    public JefeZonaDTO toDto(JefeZona jefeZona) {
        if (jefeZona == null) {
            return null;
        }

        return new JefeZonaDTO(
                jefeZona.getIdJefeZona(),
                jefeZona.getRut(),
                jefeZona.getName(),
                jefeZona.getPhone(),
                jefeZona.getEmail(),
                jefeZona.getStatus()
        );
    }

    // Método para mapear de JefeZonaDTO a JefeZona
    public JefeZona toEntity(JefeZonaDTO jefeZonaDTO) {
        if (jefeZonaDTO == null) {
            return null;
        }

        JefeZona jefeZona = new JefeZona();
        jefeZona.setIdJefeZona(jefeZonaDTO.getIdJefeZona());
        jefeZona.setRut(jefeZonaDTO.getRut());
        jefeZona.setName(jefeZonaDTO.getName());
        jefeZona.setPhone(jefeZonaDTO.getPhone());
        jefeZona.setEmail(jefeZonaDTO.getEmail());
        jefeZona.setStatus(jefeZonaDTO.getStatus());

        return jefeZona;
    }
}
