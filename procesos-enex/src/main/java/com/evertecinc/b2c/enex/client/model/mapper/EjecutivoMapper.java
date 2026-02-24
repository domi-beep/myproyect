package com.evertecinc.b2c.enex.client.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.EjecutivoDTO;
import com.evertecinc.b2c.enex.client.model.entities.Ejecutivo;

@Component
public class EjecutivoMapper {

    // Método para mapear de Ejecutivo a EjecutivoDTO
    public EjecutivoDTO toDto(Ejecutivo ejecutivo) {
        if (ejecutivo == null) {
            return null;
        }

        EjecutivoDTO ejecutivoDTO = new EjecutivoDTO();
        ejecutivoDTO.setIdEjecutivo(ejecutivo.getIdEjecutivo());
        ejecutivoDTO.setRut(ejecutivo.getRut() != null ? ejecutivo.getRut() : "");
        ejecutivoDTO.setName(ejecutivo.getName() != null ? ejecutivo.getName() : "");
        ejecutivoDTO.setPhone(ejecutivo.getPhone() != null ? ejecutivo.getPhone() : "");
        ejecutivoDTO.setEmail(ejecutivo.getEmail() != null ? ejecutivo.getEmail() : "");
        ejecutivoDTO.setStatus(ejecutivo.getStatus() != null ? ejecutivo.getStatus() : "");

        return ejecutivoDTO;
    }

    // Método para mapear de EjecutivoDTO a Ejecutivo
    public Ejecutivo toEntity(EjecutivoDTO ejecutivoDTO) {
        if (ejecutivoDTO == null) {
            return null;
        }

        Ejecutivo ejecutivo = new Ejecutivo();
        ejecutivo.setIdEjecutivo(ejecutivoDTO.getIdEjecutivo());
        ejecutivo.setRut(ejecutivoDTO.getRut() != null ? ejecutivoDTO.getRut() : "");
        ejecutivo.setName(ejecutivoDTO.getName() != null ? ejecutivoDTO.getName() : "");
        ejecutivo.setPhone(ejecutivoDTO.getPhone() != null ? ejecutivoDTO.getPhone() : "");
        ejecutivo.setEmail(ejecutivoDTO.getEmail() != null ? ejecutivoDTO.getEmail() : "");
        ejecutivo.setStatus(ejecutivoDTO.getStatus() != null ? ejecutivoDTO.getStatus() : "");

        return ejecutivo;
    }
}
