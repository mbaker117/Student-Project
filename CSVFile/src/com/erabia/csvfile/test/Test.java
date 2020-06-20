package com.erabia.csvfile.test;

import com.erabia.csvfile.exception.CSVFileException;
import com.erabia.csvfile.service.CSVFileService;
import com.erabia.csvfile.service.impl.CSVFileServiceImpl;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CSVFileService service = CSVFileServiceImpl.getInstance();
		
		try {
			//service.getData(null, null, false);
			service.findAll("SASAsa", "S", false, null);
		} catch (CSVFileException e) {
			switch (e.getType())
			{
			case CSVFILE_CAN_READ:
				break;
			case CSVFILE_CAN_WRITE:
				break;
			case CSVFILE_EMPTY:
				break;
			case CSVFILE_NOT_FOUND:
				break;
			default:
				System.out.println("Catch");
			
			}
		}
		

	}

}
