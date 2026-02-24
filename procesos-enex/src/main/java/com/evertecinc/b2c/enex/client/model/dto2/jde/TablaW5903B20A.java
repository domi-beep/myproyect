package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.util.ArrayList;
import java.util.List;

public class TablaW5903B20A {
	
	private String title;
	private List<GridData> data = new ArrayList<GridData>();
	private List<String> errors = new ArrayList<String>();
	private List<String> warnings = new ArrayList<String>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<GridData> getData() {
		return data;
	}
	public void setData(List<GridData> data) {
		this.data = data;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	public List<String> getWarnings() {
		return warnings;
	}
	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
	
	@Override
	public String toString() {
		return "TablaW5903B20A [title=" + title + ", data=" + data
				+ ", errors=" + errors + ", warnings=" + warnings + "]";
	}
	
}
