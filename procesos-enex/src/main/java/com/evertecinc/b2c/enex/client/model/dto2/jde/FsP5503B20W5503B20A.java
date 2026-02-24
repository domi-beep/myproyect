package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.util.List;

public class FsP5503B20W5503B20A {

    private String title;
    private Data data;
    private List<Object> errors = null;
    private List<Object> warnings = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

	@Override
	public String toString() {
		return "FsP5903B20W5903B20A [title=" + title + ", data=" + data
				+ ", errors=" + errors + ", warnings=" + warnings + "]";
	}

    
}
