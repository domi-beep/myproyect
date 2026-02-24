package com.evertecinc.b2c.enex.client.model.dto2.jde;

import java.util.ArrayList;
import java.util.List;

public class GridData {
	
	private Titles titles;; 
	private List<Row> rowset = new ArrayList<Row>();
	private Summary summary;
	
	public Titles getTitles() {
		return titles;
	}
	public void setTitles(Titles titles) {
		this.titles = titles;
	}
	public List<Row> getRowset() {
		return rowset;
	}
	public void setRowset(List<Row> rowset) {
		this.rowset = rowset;
	}
	public Summary getSummary() {
		return summary;
	}
	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	
	@Override
	public String toString() {
		return "GridData [titles=" + titles + ", rowset=" + rowset
				+ ", summary=" + summary + "]";
	}

}
