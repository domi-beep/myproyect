package com.evertecinc.b2c.enex.client.model.converters;

import jakarta.persistence.AttributeConverter;

public class StringToLongConverter implements AttributeConverter<String, Long> {
	@Override
	public Long convertToDatabaseColumn(String attribute) {
//		return attribute != null ? Integer.parseInt(attribute) : null;
		return attribute != null ? Long.valueOf(attribute) : null;
	}

	@Override
	public String convertToEntityAttribute(Long dbData) {
		return dbData != null ? String.valueOf(dbData) : null;
	}
}
