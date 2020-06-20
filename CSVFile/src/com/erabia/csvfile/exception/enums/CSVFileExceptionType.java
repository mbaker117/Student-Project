package com.erabia.csvfile.exception.enums;
public enum CSVFileExceptionType
{

	CSVFILE_NOT_FOUND("csvfile_not_found"), CSVFILE_CAN_READ("csvfile_can't_ be_read"), 
	CSVFILE_CAN_WRITE("csvfile_can't_ be_written"),CSVFILE_EMPTY("csvfile_is_empty");

	private String type;

	private CSVFileExceptionType(final String type)
	{
		this.type = type;
	}


	public String getMessage()
	{
		return type;
	}
}
