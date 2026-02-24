package com.evertecinc.b2c.enex.email.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageDTO {
	
	public List<String> to;
	public List<String> cc;
	public List<String> bcc;
	public String from;
	public String replyTo;
	public String subject;
	public String text;
	public List<String> attachmentsPaths;
	public boolean isHtml;


}