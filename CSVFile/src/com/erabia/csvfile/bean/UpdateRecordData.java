package com.erabia.csvfile.bean;

import java.util.List;

public class UpdateRecordData {
	private String oldKeyWord;
	private List<String> newRecord;
	
	public String getOldKeyWord() {
		return oldKeyWord;
	}
	public void setOldKeyWord(String oldKeyWord) {
		this.oldKeyWord = oldKeyWord;
	}
	public List<String> getNewRecord() {
		return newRecord;
	}
	public void setNewRecord(List<String> newRecord) {
		this.newRecord = newRecord;
	}
	public UpdateRecordData(String oldKeyWord, List<String> newRecord) {
		
		this.oldKeyWord = oldKeyWord;
		this.newRecord = newRecord;
	}
	
	
	

}
