package com.erabic.StudentDAOExample.exception;

import com.erabic.StudentDAOExample.exception.enums.StudentDAOExceptionType;

public class StudentDAOException  extends Exception{
	private final StudentDAOExceptionType type;
	private final String value;
	public StudentDAOException(final StudentDAOExceptionType type, final String message,final String value) {
		super(message);
		this.type = type;
		this.value = value;
	}
	public StudentDAOExceptionType getType() {
		return type;
	}
	public String getValue() {
		return value;
	}
	
	

}
