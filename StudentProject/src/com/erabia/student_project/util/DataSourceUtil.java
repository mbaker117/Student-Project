package com.erabia.student_project.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.erabia.student_project.Services.StudentService;
import com.erabia.student_project.Services.impl.StudentServiceCSVImpl;
import com.erabia.student_project.Services.impl.StudentServiceJDBCImpl;

public class DataSourceUtil {
	public static StudentService getStudentService(String type) throws FileNotFoundException, IOException {
		if(type==null)
			throw new IllegalArgumentException("type is null");
		if(type.equalsIgnoreCase("CSV"))
			return new StudentServiceCSVImpl();
		else if(type.equalsIgnoreCase("JDBC"))
			return new StudentServiceJDBCImpl();
	
		return null;
	}

}
