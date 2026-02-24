package com.evertecinc.b2c.enex.client.model.dto2.jde;

public class RowElement {
	
	private String id;
	private String editable;
	private String value;
	private String internalValue;
	private String title;
	private String dataType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEditable() {
		return editable;
	}
	public void setEditable(String editable) {
		this.editable = editable;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getInternalValue() {
		return internalValue;
	}
	public void setInternalValue(String internalValue) {
		this.internalValue = internalValue;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	@Override
	public String toString() {
		return "RowElement [id=" + id + ", editable=" + editable + ", value="
				+ value + ", internalValue=" + internalValue + ", title="
				+ title + ", dataType=" + dataType + "]";
	}

}
