package com.evertecinc.b2c.enex.client.model.dto2.orpak;

import java.util.ArrayList;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CardConstraintRequest {
	
	private int operationtype;
	private String customercode;
	private String plate;
	private ArrayList<TimeRestiction> timeList;
	private ArrayList<Store> storeList;
	private ArrayList<Limit> limit;
	private String itemlist;
	private String storelist;
	
	public ArrayList<Store> getStoreList() {
		return storeList;
	}
	public void setStoreList(ArrayList<Store> storeList) {
		this.storeList = storeList;
	}
	
	public String getStorelist() {
		return storelist;
	}
	public void setStorelist(String storelist) {
		this.storelist = storelist;
	}

}
