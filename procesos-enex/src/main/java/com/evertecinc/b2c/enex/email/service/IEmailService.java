package com.evertecinc.b2c.enex.email.service;

import java.util.List;

import org.springframework.mail.MailException;

import com.evertecinc.b2c.enex.email.model.dto.AlertMailDTO;
import com.evertecinc.b2c.enex.email.model.dto.SendMessageDTO;

public interface IEmailService {

	void sendMessage(List<String> to, List<String> cc, List<String> bcc, String from, String replyTo, String subject,
			String text, List<String> attachmentsPaths, boolean isHtml) throws MailException;

	void sendMessage(SendMessageDTO emailDTO) throws MailException;

	void sendAlertClient(AlertMailDTO alert) throws MailException;

}