package com.erabia.csvfile.exception;

import com.erabia.csvfile.exception.enums.CSVFileExceptionType;

public class CSVFileException extends Exception {

	private static final long serialVersionUID = 1L;

	private final CSVFileExceptionType type;

	private final String value;

	public CSVFileException(final CSVFileExceptionType type, final String message, final String value) {
		super(message);
		this.type = type;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public CSVFileExceptionType getType() {
		return type;
	}

}
