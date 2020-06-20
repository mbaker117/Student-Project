package com.erabic.StudentDAOExample.exception.enums;

public enum StudentDAOExceptionType {

	MISSING_CONNECTION("connection can't be established."), EXISTING_STUDENT("student already exists"),
	NO_EXISTING_STUDENT("student not found"),INVALID_AVG("student avg is invalid");

	private String msg;

	private StudentDAOExceptionType(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
