package com.evertecinc.b2c.enex.schedules;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleUtils {
	
	@Value("${change.card.status.orpak.schedule.interval}")
	private String changeCardStatusOrpakInterval;

	@Value("${change.client.orpak.schedule.interval}")
	private String changeClientOrpakInterval;

	@Value("${change.department.card.status.schedule.interval}")
	private String changeDepartmentCardStatusInterval;

	@Value("${change.department.status.cond.schedule.interval}")
	private String changeDepartmentStatusCondInterval;

	@Value("${change.department.status.orpak.schedule.interval}")
	private String changeDepartmentStatusOrpakInterval;

	@Value("${change.type.balance1.litros.schedule.interval}")
	private String changeTypeBalance1LitrosInterval;

	@Value("${change.type.balance1.schedule.interval}")
	private String changeTypeBalance1Interval;

	@Value("${change.type.balance2.litros.schedule.interval}")
	private String changeTypeBalance2LitrosInterval;

	@Value("${change.type.balance2.schedule.interval}")
	private String changeTypeBalance2Interval;

	@Value("${create.update.ot.schedule.interval}")
	private String createUpdateOTInterval;

	@Value("${delete.department.fleet.orpak.schedule.interval}")
	private String deleteDepartmentFleetOrpakInterval;

	@Value("${send.card.balance.orpak.schedule.interval}")
	private String sendCardBalanceOrpakInterval;

	@Value("${send.card.constraint.orpak.schedule.interval}")
	private String sendCardConstraintOrpakInterval;

	@Value("${send.client.orpak.schedule.interval}")
	private String sendClientOrpakInterval;

	@Value("${send.customer.balance.orpak.schedule.interval}")
	private String sendCustomerBalanceOrpakInterval;

	@Value("${send.department.balance.orpak.schedule.interval}")
	private String sendDepartmentBalanceOrpakInterval;

	@Value("${send.quantity.card.orpak.schedule.interval}")
	private String sendQuantityCardOrpakInterval;

	@Value("${send.update.card.gps.orpak.schedule.interval}")
	private String sendUpdateCardGPSOrpakInterval;

	@Value("${vehicle.card.orpak.schedule.interval}")
	private String vehicleCardOrpakInterval;

	@Value("${change.fact.department.schedule.interval}")
	private String changeFactDepartmentInterval;

	@Value("${search.documents.jde.schedule.interval}")
	private String searchDocumentsJDEInterval;

	@Value("${send.notifications.daily.schedule.interval}")
	private String sendNotificationsDailyInterval;

	@Value("${send.notifications.schedule.interval}")
	private String sendNotificationsInterval;

	@Value("${send.sales.note.to.jde.schedule.interval}")
	private String sendSalesNoteToJDEInterval;

	@Value("${carga.precio.pizarra.schedule.interval}")
	private String cargaPrecioPizarraInterval;
	
	@Value("${envio.email.schedule.interval}")
	private String envioEmailInterval;

	@Value("${addOrUpdateUsers.keycloak.schedule.interval}")
	private String addOrUpdateUsersInterval;

	@Value("${removeUsers.keycloak.schedule.interval}")
	private String removeUsersInterval;
	
	@Value("${createMailBienvenida.email.schedule.interval}")
	private String createMailBienvenidaInterval;
	
	@Value("${createMailReestablecerClave.email.schedule.interval}")
	private String createMailReestablecerClaveInterval;

    @Bean
    String toChangeCardStatusOrpakInterval() {
	    if (changeCardStatusOrpakInterval != null && !changeCardStatusOrpakInterval.isEmpty()) {
	        String intervalo = changeCardStatusOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toChangeClientOrpakInterval() {
	    if (changeClientOrpakInterval != null && !changeClientOrpakInterval.isEmpty()) {
	        String intervalo = changeClientOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toChangeDepartmentCardStatusInterval() {
	    if (changeDepartmentCardStatusInterval != null && !changeDepartmentCardStatusInterval.isEmpty()) {
	        String intervalo = changeDepartmentCardStatusInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toChangeDepartmentStatusCondInterval() {
	    if (changeDepartmentStatusCondInterval != null && !changeDepartmentStatusCondInterval.isEmpty()) {
	        String intervalo = changeDepartmentStatusCondInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toChangeDepartmentStatusOrpakInterval() {
	    if (changeDepartmentStatusOrpakInterval != null && !changeDepartmentStatusOrpakInterval.isEmpty()) {
	        String intervalo = changeDepartmentStatusOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toChangeTypeBalance1LitrosInterval() {
	    if (changeTypeBalance1LitrosInterval != null && !changeTypeBalance1LitrosInterval.isEmpty()) {
	        String intervalo = changeTypeBalance1LitrosInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toChangeTypeBalance1Interval() {
	    if (changeTypeBalance1Interval != null && !changeTypeBalance1Interval.isEmpty()) {
	        String intervalo = changeTypeBalance1Interval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toChangeTypeBalance2LitrosInterval() {
	    if (changeTypeBalance2LitrosInterval != null && !changeTypeBalance2LitrosInterval.isEmpty()) {
	        String intervalo = changeTypeBalance2LitrosInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toChangeTypeBalance2Interval() {
	    if (changeTypeBalance2Interval != null && !changeTypeBalance2Interval.isEmpty()) {
	        String intervalo = changeTypeBalance2Interval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toCreateUpdateOTInterval() {
	    if (createUpdateOTInterval != null && !createUpdateOTInterval.isEmpty()) {
	        String intervalo = createUpdateOTInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toDeleteDepartmentFleetOrpakInterval() {
	    if (deleteDepartmentFleetOrpakInterval != null && !deleteDepartmentFleetOrpakInterval.isEmpty()) {
	        String intervalo = deleteDepartmentFleetOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendCardBalanceOrpakInterval() {
	    if (sendCardBalanceOrpakInterval != null && !sendCardBalanceOrpakInterval.isEmpty()) {
	        String intervalo = sendCardBalanceOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendCardConstraintOrpakInterval() {
	    if (sendCardConstraintOrpakInterval != null && !sendCardConstraintOrpakInterval.isEmpty()) {
	        String intervalo = sendCardConstraintOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendClientOrpakInterval() {
	    if (sendClientOrpakInterval != null && !sendClientOrpakInterval.isEmpty()) {
	        String intervalo = sendClientOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendCustomerBalanceOrpakInterval() {
	    if (sendCustomerBalanceOrpakInterval != null && !sendCustomerBalanceOrpakInterval.isEmpty()) {
	        String intervalo = sendCustomerBalanceOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendDepartmentBalanceOrpakInterval() {
	    if (sendDepartmentBalanceOrpakInterval != null && !sendDepartmentBalanceOrpakInterval.isEmpty()) {
	        String intervalo = sendDepartmentBalanceOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendQuantityCardOrpakInterval() {
	    if (sendQuantityCardOrpakInterval != null && !sendQuantityCardOrpakInterval.isEmpty()) {
	        String intervalo = sendQuantityCardOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendUpdateCardGPSOrpakInterval() {
	    if (sendUpdateCardGPSOrpakInterval != null && !sendUpdateCardGPSOrpakInterval.isEmpty()) {
	        String intervalo = sendUpdateCardGPSOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toVehicleCardOrpakInterval() {
	    if (vehicleCardOrpakInterval != null && !vehicleCardOrpakInterval.isEmpty()) {
	        String intervalo = vehicleCardOrpakInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toChangeFactDepartmentInterval() {
	    if (changeFactDepartmentInterval != null && !changeFactDepartmentInterval.isEmpty()) {
	        String intervalo = changeFactDepartmentInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSearchDocumentsJDEInterval() {
	    if (searchDocumentsJDEInterval != null && !searchDocumentsJDEInterval.isEmpty()) {
	        String intervalo = searchDocumentsJDEInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendNotificationsDailyInterval() {
	    if (sendNotificationsDailyInterval != null && !sendNotificationsDailyInterval.isEmpty()) {
	        String intervalo = sendNotificationsDailyInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendNotificationsInterval() {
	    if (sendNotificationsInterval != null && !sendNotificationsInterval.isEmpty()) {
	        String intervalo = sendNotificationsInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toSendSalesNoteToJDEInterval() {
	    if (sendSalesNoteToJDEInterval != null && !sendSalesNoteToJDEInterval.isEmpty()) {
	        String intervalo = sendSalesNoteToJDEInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}

    @Bean
    String toCargaPrecioPizarraInterval() {
	    if (cargaPrecioPizarraInterval != null && !cargaPrecioPizarraInterval.isEmpty()) {
	        String intervalo = cargaPrecioPizarraInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}
    
    @Bean
    String toEnvioEmailInterval() {
	    if (envioEmailInterval != null && !envioEmailInterval.isEmpty()) {
	        String intervalo = envioEmailInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}
    
    @Bean
    String toKeycloakAddOrUpdateUsersInterval() {
	    if (addOrUpdateUsersInterval != null && !addOrUpdateUsersInterval.isEmpty()) {
	        String intervalo = addOrUpdateUsersInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}
    
    @Bean
    String toKeycloakRemoveUsersInterval() {
	    if (removeUsersInterval != null && !removeUsersInterval.isEmpty()) {
	        String intervalo = removeUsersInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}
    
    @Bean
    String toCreateMailBienvenida() {
	    if (createMailBienvenidaInterval != null && !createMailBienvenidaInterval.isEmpty()) {
	        String intervalo = createMailBienvenidaInterval;
	        return String.format("0 0/%s * * * *", intervalo); // MINUTOS
	    }
	    return null;
	}
    
    @Bean
    String toCreacionMailReestablecerClave() {
    	if (createMailReestablecerClaveInterval != null && !createMailReestablecerClaveInterval.isEmpty()) {
    		String intervalo = createMailReestablecerClaveInterval;
    		return String.format("0 0/%s * * * *", intervalo); // MINUTOS
    	}
    	return null;
    }
  
}
