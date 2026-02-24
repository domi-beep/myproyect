package com.evertecinc.b2c.enex.client.model.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BooleanToSmallintConverter implements AttributeConverter<Boolean, Short> {
	@Override
	public Short convertToDatabaseColumn(Boolean attribute) {
		return attribute != null ? (attribute ? (short) 1 : (short) 0) : null;
	}

	@Override
	public Boolean convertToEntityAttribute(Short dbData) {
		return dbData != null ? dbData != 0 : null;
	}
}
