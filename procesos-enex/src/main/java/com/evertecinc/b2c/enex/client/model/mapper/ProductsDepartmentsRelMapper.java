package com.evertecinc.b2c.enex.client.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.ProductDepartmentDTO;
import com.evertecinc.b2c.enex.client.model.entities.ProductsDepartmentsRel;


@Component
public class ProductsDepartmentsRelMapper {

    // Convierte de entidad a DTO
    public static ProductDepartmentDTO toDto(ProductsDepartmentsRel entity) {
        if (entity == null) {
            return null;
        }
        ProductDepartmentDTO dto = new ProductDepartmentDTO();
        dto.setIdDepartment(entity.getIdDepartment());
        dto.setProductCode(entity.getProductCode());
        dto.setDocumentType(entity.getDocumentType());
        dto.setRemainingAmount(entity.getRemainingAmount());
        return dto;
    }

    // Convierte de DTO a entidad
    public static ProductsDepartmentsRel toEntity(ProductDepartmentDTO dto) {
        if (dto == null) {
            return null;
        }
        ProductsDepartmentsRel entity = new ProductsDepartmentsRel();
        entity.setIdDepartment(dto.getIdDepartment());
        entity.setProductCode(dto.getProductCode());
        entity.setDocumentType(dto.getDocumentType());
        entity.setRemainingAmount(dto.getRemainingAmount());
        return entity;
    }

    // Convierte una lista de entidades a una lista de DTO
    public static List<ProductDepartmentDTO> toDtoList(List<ProductsDepartmentsRel> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(ProductsDepartmentsRelMapper::toDto)
                .collect(Collectors.toList());
    }

    // Convierte una lista de DTO a una lista de entidades
    public static List<ProductsDepartmentsRel> toEntityList(List<ProductDepartmentDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(ProductsDepartmentsRelMapper::toEntity)
                .collect(Collectors.toList());
    }
}
