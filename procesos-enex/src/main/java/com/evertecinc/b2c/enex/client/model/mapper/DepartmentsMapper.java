package com.evertecinc.b2c.enex.client.model.mapper;


import org.springframework.stereotype.Component;

import com.evertecinc.b2c.enex.client.model.dto.DepartmentDTO;
import com.evertecinc.b2c.enex.client.model.entities.Departments;

@Component
public class DepartmentsMapper {

    public DepartmentDTO toDTO(Departments department) {
        if (department == null) {
            return null;
        }

        DepartmentDTO dto = new DepartmentDTO();
        dto.setIdDepartment(department.getIdDepartment());
        dto.setIdClient(department.getClient() != null ? department.getClient().getIdClient() : null);
        dto.setName(department.getName());
        dto.setRestrL(department.getRestrL() != null && department.getRestrL() == 1);
        dto.setRestrM(department.getRestrM() != null && department.getRestrM() == 1);
        dto.setRestrX(department.getRestrX() != null && department.getRestrX() == 1);
        dto.setRestrJ(department.getRestrJ() != null && department.getRestrJ() == 1);
        dto.setRestrV(department.getRestrV() != null && department.getRestrV() == 1);
        dto.setRestrS(department.getRestrS() != null && department.getRestrS() == 1);
        dto.setRestrD(department.getRestrD() != null && department.getRestrD() == 1);
        dto.setRestrHinit(department.getRestrHinit() != null ? department.getRestrHinit().longValue() : null);
        dto.setRestrHend(department.getRestrHend() != null ? department.getRestrHend().longValue() : null);
        dto.setRestrAmountMax(department.getRestrAmountMax());
        dto.setRestrDailyMaxLoads(department.getRestrDailyMaxLoads() != null ? department.getRestrDailyMaxLoads().longValue() : null);
        dto.setDepartmentStatus(department.getDepartmentStatus());
        dto.setWarningStockChannel(department.getWarningStockChannel());
        dto.setWarningStock(department.getWarningStock());
        dto.setWarningStockCelular(department.getWarningStockCelular());
        dto.setWarningStockMail(department.getWarningStockEmail());
        dto.setWarningLoadChannel(department.getWarningLoadChannel());
        dto.setWarningLoadCelular(department.getWarningLoadCelular());
        dto.setWarningLoadMail(department.getWarningLoadEmail());
        dto.setWarningLoadFailChannel(department.getWarningLoadFailChannel());
        dto.setWarningLoadFailCelular(department.getWarningLoadFailCelular());
        dto.setWarningLoadFailEmail(department.getWarningLoadFailEmail());
        dto.setTypeBalance(department.getTypeBalance());
        dto.setCodeOrpakTicket(department.getCodeorpakticket());
        dto.setCodeOrpakInvoice(department.getCodeorpakinvoice());
        dto.setCodeOrpakClient(department.getCodeorpakclient());
        dto.setRestrDailyMaxQuantity(department.getRestrDailyMaxQuantity() != null ? department.getRestrDailyMaxQuantity().longValue() : null);
        dto.setRestrType(department.getRestrType());
        dto.setGps(department.getGps());
        dto.setIdPadre(department.getIdPadre() != null ? department.getIdPadre().longValue() : null);

        return dto;
    }

    public Departments toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }

        Departments department = new Departments();
        department.setIdDepartment(dto.getIdDepartment());
//        department.setClient(dto.getIdClient()); Setear cliente afuera del mapper
        department.setName(dto.getName());
        department.setRestrL(dto.getRestrL() != null && dto.getRestrL() ? 1 : 0);
        department.setRestrM(dto.getRestrM() != null && dto.getRestrM() ? 1 : 0);
        department.setRestrX(dto.getRestrX() != null && dto.getRestrX() ? 1 : 0);
        department.setRestrJ(dto.getRestrJ() != null && dto.getRestrJ() ? 1 : 0);
        department.setRestrV(dto.getRestrV() != null && dto.getRestrV() ? 1 : 0);
        department.setRestrS(dto.getRestrS() != null && dto.getRestrS() ? 1 : 0);
        department.setRestrD(dto.getRestrD() != null && dto.getRestrD() ? 1 : 0);
        department.setRestrHinit(dto.getRestrHinit() != null ? dto.getRestrHinit().intValue() : null);
        department.setRestrHend(dto.getRestrHend() != null ? dto.getRestrHend().intValue() : null);
        department.setRestrAmountMax(dto.getRestrAmountMax());
        department.setRestrDailyMaxLoads(dto.getRestrDailyMaxLoads() != null ? dto.getRestrDailyMaxLoads().intValue() : null);
        department.setDepartmentStatus(dto.getDepartmentStatus());
        department.setWarningStockChannel(dto.getWarningStockChannel());
        department.setWarningStock(dto.getWarningStock());
        department.setWarningStockCelular(dto.getWarningStockCelular());
        department.setWarningStockEmail(dto.getWarningStockMail());
        department.setWarningLoadChannel(dto.getWarningLoadChannel());
        department.setWarningLoadCelular(dto.getWarningLoadCelular());
        department.setWarningLoadEmail(dto.getWarningLoadMail());
        department.setWarningLoadFailChannel(dto.getWarningLoadFailChannel());
        department.setWarningLoadFailCelular(dto.getWarningLoadFailCelular());
        department.setWarningLoadFailEmail(dto.getWarningLoadFailEmail());
        department.setTypeBalance(dto.getTypeBalance());
        department.setCodeorpakticket(dto.getCodeOrpakTicket());
        department.setCodeorpakinvoice(dto.getCodeOrpakInvoice());
        department.setCodeorpakclient(dto.getCodeOrpakClient());
        department.setRestrDailyMaxQuantity(dto.getRestrDailyMaxQuantity() != null ? dto.getRestrDailyMaxQuantity().intValue() : null);
        department.setRestrType(dto.getRestrType());
        department.setGps(dto.getGps());
        department.setIdPadre(dto.getIdPadre() != null ? dto.getIdPadre().intValue() : null);

        return department;
    }
}
