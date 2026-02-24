package com.evertecinc.b2c.enex.schedules;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.evertecinc.b2c.enex.process.exceptions.ProcessException;
import com.evertecinc.b2c.enex.process.service.IProcess;
import com.evertecinc.b2c.enex.process.service.impl.SendUpdateCardGPSOrpakProcess;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT1M")
@Slf4j
public class ScheduleConfig {

    private final SendUpdateCardGPSOrpakProcess SendUpdateCardGPSOrpakProcess;
	
//	private final IProcessService processService;
    private final IProcess changeCardStatusOrpakProcess;
    private final IProcess changeClientOrpakProcess;
    private final IProcess changeDepartmentCardStatusProcess;
    private final IProcess changeDepartmentStatusCondProcess;
    private final IProcess changeDepartmentStatusOrpakProcess;
    private final IProcess changeTypeBalance1LitrosProcess;
    private final IProcess changeTypeBalance1Process;
    private final IProcess changeTypeBalance2LitrosProcess;
    private final IProcess changeTypeBalance2Process;
    private final IProcess createUpdateOTProcess;
    private final IProcess deleteDepartmentFleetOrpakProcess;
    private final IProcess sendCardBalanceOrpakProcess;
    private final IProcess sendCardConstraintOrpakProcess;
    private final IProcess sendClientOrpakProcess;
    private final IProcess sendCustomerBalanceOrpakProcess;
    private final IProcess sendDepartmentBalanceOrpakProcess;
    private final IProcess sendQuantityCardOrpakProcess;
//    private final IProcess sendUpdateCardGPSOrpakProcess;
    private final IProcess vehicleCardOrpakProcess;
    private final IProcess changeFactDepartmentProcess;
    private final IProcess searchDocumentsJDEProcess;
    private final IProcess sendNotificationsDailyProcess;
    private final IProcess sendNotificationsProcess;
    private final IProcess sendSalesNoteToJDEProcess;
    private final IProcess envioMailsProcess;
    
    //keycloak
    private final IProcess keycloakAddOrUpdateUsersProcess;
    private final IProcess keycloakRemoveUsersProcess;
    
    private final IProcess deshabilitarCuentasExpiradasProcess;
    private final IProcess deshabilitarCuentasKeycloakProcess;
    
    //Email bienvenida
    private final IProcess createMailBienvenidaProcess;
    private final IProcess createMailReestablecerClaveProcess;

	private IProcess sendUpdateCardGPSOrpakProcess;
    
    
    

    public ScheduleConfig(
//            IProcessService processService,
            @Qualifier("ChangeCardStatusOrpakProcess") IProcess changeCardStatusOrpakProcess,
            @Qualifier("ChangeClientOrpakProcess") IProcess changeClientOrpakProcess,
            @Qualifier("ChangeDepartmentCardStatusProcess") IProcess changeDepartmentCardStatusProcess,
            @Qualifier("ChangeDepartmentStatusCondProcess") IProcess changeDepartmentStatusCondProcess,
            @Qualifier("ChangeDepartmentStatusOrpakProcess") IProcess changeDepartmentStatusOrpakProcess,
            @Qualifier("ChangeTypeBalance1LitrosProcess") IProcess changeTypeBalance1LitrosProcess,
            @Qualifier("ChangeTypeBalance1Process") IProcess changeTypeBalance1Process,
            @Qualifier("ChangeTypeBalance2LitrosProcess") IProcess changeTypeBalance2LitrosProcess,
            @Qualifier("ChangeTypeBalance2Process") IProcess changeTypeBalance2Process,
            @Qualifier("CreateUpdateOTProcess") IProcess createUpdateOTProcess,
            @Qualifier("DeleteDepartmentFleetOrpakProcess") IProcess deleteDepartmentFleetOrpakProcess,
            @Qualifier("SendCardBalanceOrpakProcess") IProcess sendCardBalanceOrpakProcess,
            @Qualifier("SendCardConstraintOrpakProcess") IProcess sendCardConstraintOrpakProcess,
            @Qualifier("SendClientOrpakProcess") IProcess sendClientOrpakProcess,
            @Qualifier("SendCustomerBalanceOrpakProcess") IProcess sendCustomerBalanceOrpakProcess,
            @Qualifier("SendDepartmentBalanceOrpakProcess") IProcess sendDepartmentBalanceOrpakProcess,
            @Qualifier("SendQuantityCardOrpakProcess") IProcess sendQuantityCardOrpakProcess,
            @Qualifier("SendUpdateCardGPSOrpakProcess") IProcess sendUpdateCardGPSOrpakProcess,
            @Qualifier("VehicleCardOrpakProcess") IProcess vehicleCardOrpakProcess,
            @Qualifier("ChangeFactDepartmentProcess") IProcess changeFactDepartmentProcess,
            @Qualifier("SearchDocumentsJDEProcess") IProcess searchDocumentsJDEProcess,
            @Qualifier("SendNotificationsDailyProcess") IProcess sendNotificationsDailyProcess,
            @Qualifier("SendNotificationsProcess") IProcess sendNotificationsProcess,
            @Qualifier("SendSalesNoteToJDEProcess") IProcess sendSalesNoteToJDEProcess,
            @Qualifier("EnvioMailsProcess") IProcess envioMailsProcess,
            @Qualifier("KeycloakAddOrUpdateUsersProcess") IProcess keycloakAddOrUpdateUsersProcess,
            @Qualifier("KeycloakRemoveUsersProcess") IProcess keycloakRemoveUsersProcess,
            @Qualifier("CreateMailBienvenidaProcess") IProcess createMailBienvenidaProcess,
            @Qualifier("DeshabilitarCuentasExpiradasProcess") IProcess deshabilitarCuentasExpiradasProcess,
            @Qualifier("DeshabilitarCuentasKeycloakProcess") IProcess deshabilitarCuentasKeycloakProcess,
            @Qualifier("CreateMailReestablecerClaveProcess") IProcess createMailReestablecerClaveProcess
    , SendUpdateCardGPSOrpakProcess SendUpdateCardGPSOrpakProcess) {
//        this.processService = processService;
        this.changeCardStatusOrpakProcess = changeCardStatusOrpakProcess;
        this.changeClientOrpakProcess = changeClientOrpakProcess;
        this.changeDepartmentCardStatusProcess = changeDepartmentCardStatusProcess;
        this.changeDepartmentStatusCondProcess = changeDepartmentStatusCondProcess;
        this.changeDepartmentStatusOrpakProcess = changeDepartmentStatusOrpakProcess;
        this.changeTypeBalance1LitrosProcess = changeTypeBalance1LitrosProcess;
        this.changeTypeBalance1Process = changeTypeBalance1Process;
        this.changeTypeBalance2LitrosProcess = changeTypeBalance2LitrosProcess;
        this.changeTypeBalance2Process = changeTypeBalance2Process;
        this.createUpdateOTProcess = createUpdateOTProcess;
        this.deleteDepartmentFleetOrpakProcess = deleteDepartmentFleetOrpakProcess;
        this.sendCardBalanceOrpakProcess = sendCardBalanceOrpakProcess;
        this.sendCardConstraintOrpakProcess = sendCardConstraintOrpakProcess;
        this.sendClientOrpakProcess = sendClientOrpakProcess;
        this.sendCustomerBalanceOrpakProcess = sendCustomerBalanceOrpakProcess;
        this.sendDepartmentBalanceOrpakProcess = sendDepartmentBalanceOrpakProcess;
        this.sendQuantityCardOrpakProcess = sendQuantityCardOrpakProcess;
        this.sendUpdateCardGPSOrpakProcess = sendUpdateCardGPSOrpakProcess;
        this.vehicleCardOrpakProcess = vehicleCardOrpakProcess;
        this.changeFactDepartmentProcess = changeFactDepartmentProcess;
        this.searchDocumentsJDEProcess = searchDocumentsJDEProcess;
        this.sendNotificationsDailyProcess = sendNotificationsDailyProcess;
        this.sendNotificationsProcess = sendNotificationsProcess;
        this.sendSalesNoteToJDEProcess = sendSalesNoteToJDEProcess;
        this.envioMailsProcess = envioMailsProcess;
        this.keycloakAddOrUpdateUsersProcess = keycloakAddOrUpdateUsersProcess;
        this.keycloakRemoveUsersProcess = keycloakRemoveUsersProcess;
        this.createMailBienvenidaProcess = createMailBienvenidaProcess;
        this.createMailReestablecerClaveProcess = createMailReestablecerClaveProcess;
        this.SendUpdateCardGPSOrpakProcess = SendUpdateCardGPSOrpakProcess;
        this.deshabilitarCuentasExpiradasProcess = deshabilitarCuentasExpiradasProcess;
        this.deshabilitarCuentasKeycloakProcess = deshabilitarCuentasKeycloakProcess;
    }
	
	@Value("${enex.schedule.enable:true}")

	@Scheduled(cron = "#{@toKeycloakAddOrUpdateUsersInterval}")
	@SchedulerLock(name = "ScheduleConfig_KeycloakAddOrUpdateUsers", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleKeycloakAddOrUpdateUsersProcess() {
	    String time = LocalTime.now().toString();
	    try {
	    	keycloakAddOrUpdateUsersProcess.run();
	    } catch (ProcessException e) {
	        log.error("CargaPrecioPizarra ----- Scheduled task error: " + e.getMessage());
	    }
	    log.info("CargaPrecioPizarra ----- Scheduled task executed: {}", time);
	}
	
	@Scheduled(cron = "#{@toKeycloakRemoveUsersInterval}")
	@SchedulerLock(name = "ScheduleConfig_KeycloakRemoveUsers", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleKeycloakRemoveUsersProcess() {
	    String time = LocalTime.now().toString();
	    try {
	    	keycloakRemoveUsersProcess.run();
	    } catch (ProcessException e) {
	        log.error("CargaPrecioPizarra ----- Scheduled task error: " + e.getMessage());
	    }
	    log.info("CargaPrecioPizarra ----- Scheduled task executed: {}", time);
	}
	
	@Scheduled(cron = "#{@toChangeCardStatusOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeCardStatusOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeCardStatusOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("ChangeCardStatusOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeCardStatusOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("ChangeCardStatusOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeCardStatusOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toChangeClientOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeClientOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeClientOrpak() {
	    
		long startTime = System.currentTimeMillis();
	    log.info("ChangeClientOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeClientOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("ChangeClientOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeClientOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	
	}

	@Scheduled(cron = "#{@toChangeDepartmentCardStatusInterval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeDepartmentCardStatus", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeDepartmentCardStatus() {
	    long startTime = System.currentTimeMillis();
	    log.info("ChangeDepartmentCardStatus ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeDepartmentCardStatusProcess.run();
	    } catch (ProcessException e) {
	        log.error("ChangeDepartmentCardStatus ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeDepartmentCardStatus ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toChangeDepartmentStatusCondInterval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeDepartmentStatusCond", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeDepartmentStatusCond() {
	    long startTime = System.currentTimeMillis();
	    log.info("ChangeDepartmentStatusCond ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeDepartmentStatusCondProcess.run();
	    } catch (ProcessException e) {
	        log.error("ChangeDepartmentStatusCond ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeDepartmentStatusCond ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toChangeDepartmentStatusOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeDepartmentStatusOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeDepartmentStatusOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("ChangeDepartmentStatusOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeDepartmentStatusOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("ChangeDepartmentStatusOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeDepartmentStatusOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toChangeTypeBalance1LitrosInterval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeTypeBalance1Litros", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeTypeBalance1Litros() {
	    long startTime = System.currentTimeMillis();
	    log.info("ChangeTypeBalance1Litros ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeTypeBalance1LitrosProcess.run();
	    } catch (ProcessException e) {
	        log.error("ChangeTypeBalance1Litros ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeTypeBalance1Litros ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toChangeTypeBalance1Interval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeTypeBalance1", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeTypeBalance1() {
	    long startTime = System.currentTimeMillis();
	    log.info("ChangeTypeBalance1 ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeTypeBalance1Process.run();
	    } catch (ProcessException e) {
	        log.error("ChangeTypeBalance1 ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeTypeBalance1 ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toChangeTypeBalance2LitrosInterval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeTypeBalance2Litros", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeTypeBalance2Litros() {
	    long startTime = System.currentTimeMillis();
	    log.info("ChangeTypeBalance2Litros ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeTypeBalance2LitrosProcess.run();
	    } catch (ProcessException e) {
	        log.error("ChangeTypeBalance2Litros ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeTypeBalance2Litros ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toChangeTypeBalance2Interval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeTypeBalance2", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeTypeBalance2() {
	    long startTime = System.currentTimeMillis();
	    log.info("ChangeTypeBalance2 ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeTypeBalance2Process.run();
	    } catch (ProcessException e) {
	        log.error("ChangeTypeBalance2 ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeTypeBalance2 ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toCreateUpdateOTInterval}")
	@SchedulerLock(name = "ScheduleConfig_CreateUpdateOT", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleCreateUpdateOT() {
	    long startTime = System.currentTimeMillis();
	    log.info("CreateUpdateOT ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        createUpdateOTProcess.run();
	    } catch (ProcessException e) {
	        log.error("CreateUpdateOT ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("CreateUpdateOT ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toDeleteDepartmentFleetOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_DeleteDepartmentFleetOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleDeleteDepartmentFleetOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("DeleteDepartmentFleetOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        deleteDepartmentFleetOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("DeleteDepartmentFleetOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("DeleteDepartmentFleetOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendCardBalanceOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendCardBalanceOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendCardBalanceOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendCardBalanceOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        sendCardBalanceOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendCardBalanceOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendCardBalanceOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendCardConstraintOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendCardConstraintOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendCardConstraintOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendCardConstraintOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        sendCardConstraintOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendCardConstraintOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendCardConstraintOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendClientOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendClientOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendClientOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendClientOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        sendClientOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendClientOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendClientOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendCustomerBalanceOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendCustomerBalanceOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendCustomerBalanceOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendCustomerBalanceOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        sendCustomerBalanceOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendCustomerBalanceOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendCustomerBalanceOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendDepartmentBalanceOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendDepartmentBalanceOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendDepartmentBalanceOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendDepartmentBalanceOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        sendDepartmentBalanceOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendDepartmentBalanceOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendDepartmentBalanceOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendQuantityCardOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendQuantityCardOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendQuantityCardOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendQuantityCardOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        sendQuantityCardOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendQuantityCardOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendQuantityCardOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendUpdateCardGPSOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendUpdateCardGPSOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendUpdateCardGPSOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendUpdateCardGPSOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	    	sendUpdateCardGPSOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendUpdateCardGPSOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendUpdateCardGPSOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toVehicleCardOrpakInterval}")
	@SchedulerLock(name = "ScheduleConfig_VehicleCardOrpak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleVehicleCardOrpak() {
	    long startTime = System.currentTimeMillis();
	    log.info("VehicleCardOrpak ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        vehicleCardOrpakProcess.run();
	    } catch (ProcessException e) {
	        log.error("VehicleCardOrpak ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("VehicleCardOrpak ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toChangeFactDepartmentInterval}")
	@SchedulerLock(name = "ScheduleConfig_ChangeFactDepartment", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleChangeFactDepartment() {
	    long startTime = System.currentTimeMillis();
	    log.info("ChangeFactDepartment ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        changeFactDepartmentProcess.run();
	    } catch (ProcessException e) {
	        log.error("ChangeFactDepartment ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("ChangeFactDepartment ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSearchDocumentsJDEInterval}")
	@SchedulerLock(name = "ScheduleConfig_SearchDocumentsJDE", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSearchDocumentsJDE() {
	    long startTime = System.currentTimeMillis();
	    log.info("SearchDocumentsJDE ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        searchDocumentsJDEProcess.run();
	    } catch (ProcessException e) {
	        log.error("SearchDocumentsJDE ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SearchDocumentsJDE ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendNotificationsDailyInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendNotificationsDaily", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendNotificationsDaily() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendNotificationsDaily ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        sendNotificationsDailyProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendNotificationsDaily ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendNotificationsDaily ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendNotificationsInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendNotifications", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendNotifications() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendNotifications ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        sendNotificationsProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendNotifications ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendNotifications ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}

	@Scheduled(cron = "#{@toSendSalesNoteToJDEInterval}")
	@SchedulerLock(name = "ScheduleConfig_SendSalesNoteToJDE", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleSendSalesNoteToJDE() {
	    long startTime = System.currentTimeMillis();
	    log.info("SendSalesNoteToJDE ----- Scheduled task started at: {}", LocalTime.now());
	    try {
	        sendSalesNoteToJDEProcess.run();
	    } catch (ProcessException e) {
	        log.error("SendSalesNoteToJDE ----- Scheduled task error: {}", e.getMessage());
	    }
	    long endTime = System.currentTimeMillis();
	    log.info("SendSalesNoteToJDE ----- Scheduled task executed. Total execution time: {} ms", (endTime - startTime));
	}
	
	@Scheduled(cron = "#{@toEnvioEmailInterval}")
	@SchedulerLock(name = "ScheduleConfig_EnvioMails", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleCargaPrecioPizarra() {
		String time = LocalTime.now().toString();
		try {
			envioMailsProcess.run();
		} catch (ProcessException e) {
			log.error("CargaPrecioPizarra ----- Scheduled task error: " + e.getMessage());
		}
		log.info("CargaPrecioPizarra ----- Scheduled task executed: {}", time);
	}
	
	@Scheduled(cron = "#{@toCreateMailBienvenida}")
	@SchedulerLock(name = "ScheduleConfig_CreacionMailBienvenida", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleCreateMailBienvenida() {
		String time = LocalTime.now().toString();
		try {
			createMailBienvenidaProcess.run();
		} catch (ProcessException e) {
			log.error("CreateMailBienvenida ----- Scheduled task error: " + e.getMessage());
		}
		log.info("CreateMailBienvenida ----- Scheduled task executed: {}", time);
	}

	@Scheduled(cron = "#{@toCreacionMailReestablecerClave}")
	@SchedulerLock(name = "ScheduleConfig_CreacionMailReestablecerClave", lockAtLeastForString = "PT1M", lockAtMostForString = "PT5M")
	public void scheduleCreateMailReestablecerClave() {
	    String time = LocalTime.now().toString();
	    try {
	    	createMailReestablecerClaveProcess.run();
	    } catch (ProcessException e) {
	        log.error("CreacionMailReestablecerClave ----- Scheduled task error: " + e.getMessage());
	    }
	    log.info("CreacionMailReestablecerClave ----- Scheduled task executed: {}", time);
	}
	

	@Scheduled(cron = "0 0 3 * * *", zone = "America/Santiago")
	@SchedulerLock(name = "ScheduleConfig_DeshabilitarUsuarios", lockAtLeastForString = "PT1M", lockAtMostForString = "PT10M")
	public void scheduleDeshabilitarUsuarios() {
		String time = LocalTime.now().toString();
		try {
			deshabilitarCuentasExpiradasProcess.run();
		} catch (ProcessException e) {
			log.error("DeshabilitarCuentasExpiradasProcess ----- Scheduled task error: " + e.getMessage());
		}
		log.info("DeshabilitarCuentasExpiradasProcess ----- Scheduled task executed: {}", time);
	}
	
	@Scheduled(cron = "0 0 4 * * *", zone = "America/Santiago")
	@SchedulerLock(name = "ScheduleConfig_DeshabilitarUsuariosKeycloak", lockAtLeastForString = "PT1M", lockAtMostForString = "PT10M")
	public void scheduleDeshabilitarUsuariosKeycloak() {
		String time = LocalTime.now().toString();
		try {
			deshabilitarCuentasKeycloakProcess.run();
		} catch (ProcessException e) {
			log.error("DeshabilitarCuentasKeycloakProcess ----- Scheduled task error: " + e.getMessage());
		}
		log.info("DeshabilitarCuentasExpiradasProcess ----- Scheduled task executed: {}", time);
	}
    
}
