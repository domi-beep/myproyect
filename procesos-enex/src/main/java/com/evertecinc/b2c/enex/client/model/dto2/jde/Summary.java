package com.evertecinc.b2c.enex.client.model.dto2.jde;

public class Summary {
	
	public int records;
	public boolean moreRecords;
	
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public boolean isMoreRecords() {
		return moreRecords;
	}
	public void setMoreRecords(boolean moreRecords) {
		this.moreRecords = moreRecords;
	}
	
	@Override
	public String toString() {
		return "Summary [records=" + records + ", moreRecords=" + moreRecords
				+ "]";
	}

}
