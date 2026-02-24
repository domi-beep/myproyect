package com.evertecinc.b2c.enex.client.model.mapper;

import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.entities.Department;

@Component
public class DepartmentMapper {

    public DepartmentDTO toDto(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentDTO dto = new DepartmentDTO();
        dto.setIdDepartment(department.getIdDepartment());
        dto.setIdClient(department.getIdClient());
        dto.setName(department.getName());
        dto.setRestrType(department.getRestrType());
        dto.setRestrL(department.getRestrL());
        dto.setRestrM(department.getRestrM());
        dto.setRestrX(department.getRestrX());
        dto.setRestrJ(department.getRestrJ());
        dto.setRestrV(department.getRestrV());
        dto.setRestrS(department.getRestrS());
        dto.setRestrD(department.getRestrD());
        dto.setRestrHinit(Long.valueOf(department.getRestrHinit()));
        dto.setRestrHend(Long.valueOf(department.getRestrHend()));
        dto.setRestrAmountMax(department.getRestrAmountMax());
        dto.setRestrDailyMaxLoads(Long.valueOf(department.getRestrDailyMaxLoads()));
        dto.setRestrDailyMaxQuantity(Long.valueOf(department.getRestrDailyMaxQuantity()));
        dto.setDepartmentStatus(department.getDepartmentStatus());
        dto.setWarningStockChannel(department.getWarningStockChannel());
        dto.setWarningStock(department.getWarningStock());
        dto.setWarningStockCelular(department.getWarningStockCelular());
        dto.setWarningStockMail(department.getWarningStockMail());
        dto.setWarningLoadChannel(department.getWarningLoadChannel());
        dto.setWarningLoadCelular(department.getWarningLoadCelular());
        dto.setWarningLoadMail(department.getWarningLoadMail());
        dto.setWarningLoadFailChannel(department.getWarningLoadFailChannel());
        dto.setWarningLoadFailCelular(department.getWarningLoadFailCelular());
        dto.setWarningLoadFailEmail(department.getWarningLoadFailEmail());
        dto.setTypeBalance(department.getTypeBalance());
        dto.setCodeOrpakTicket(department.getCodeOrpakTicket());
        dto.setCodeOrpakInvoice(department.getCodeOrpakInvoice());
        dto.setCodeOrpakClient(department.getCodeOrpakClient());
        dto.setGps(department.getGps());
        dto.setIdPadre(department.getIdPadre());
        return dto;
    }

    public Department toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }

        return Department.builder()
                .idDepartment(dto.getIdDepartment())
                .idClient(dto.getIdClient())
                .name(dto.getName())
                .restrType(dto.getRestrType())
                .restrL(dto.getRestrL())
                .restrM(dto.getRestrM())
                .restrX(dto.getRestrX())
                .restrJ(dto.getRestrJ())
                .restrV(dto.getRestrV())
                .restrS(dto.getRestrS())
                .restrD(dto.getRestrD())
                .restrHinit(dto.getRestrHinit().intValue())
                .restrHend(dto.getRestrHend().intValue())
                .restrAmountMax(dto.getRestrAmountMax())
                .restrDailyMaxLoads(dto.getRestrDailyMaxLoads().intValue())
                .restrDailyMaxQuantity(dto.getRestrDailyMaxQuantity().intValue())
                .departmentStatus(dto.getDepartmentStatus())
                .warningStockChannel(dto.getWarningStockChannel())
                .warningStock(dto.getWarningStock())
                .warningStockCelular(dto.getWarningStockCelular())
                .warningStockMail(dto.getWarningStockMail())
                .warningLoadChannel(dto.getWarningLoadChannel())
                .warningLoadCelular(dto.getWarningLoadCelular())
                .warningLoadMail(dto.getWarningLoadMail())
                .warningLoadFailChannel(dto.getWarningLoadFailChannel())
                .warningLoadFailCelular(dto.getWarningLoadFailCelular())
                .warningLoadFailEmail(dto.getWarningLoadFailEmail())
                .typeBalance(dto.getTypeBalance())
                .codeOrpakTicket(dto.getCodeOrpakTicket())
                .codeOrpakInvoice(dto.getCodeOrpakInvoice())
                .codeOrpakClient(dto.getCodeOrpakClient())
                .gps(dto.getGps())
                .idPadre(dto.getIdPadre())
                .build();
    }
}