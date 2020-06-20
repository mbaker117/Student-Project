package com.erabia.csvfile.bean;

public class UpdateData {
	private String oldData;
	private String newData;
	
	public UpdateData(String oldData, String newData) {
		this.oldData = oldData;
		this.newData = newData;
	}
	public String getOldData() {
		return oldData;
	}
	public void setOldData(String oldData) {
		this.oldData = oldData;
	}
	public String getNewData() {
		return newData;
	}
	public void setNewData(String newData) {
		this.newData = newData;
	}
	
	

}
