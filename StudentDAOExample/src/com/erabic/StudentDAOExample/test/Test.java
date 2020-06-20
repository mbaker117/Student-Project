package com.erabic.StudentDAOExample.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.erabic.StudentDAOExample.bean.Student;
import com.erabic.StudentDAOExample.dao.impl.StudentJDBCDAOImpl;
import com.erabic.StudentDAOExample.exception.StudentDAOException;



public class Test {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException, StudentDAOException, IOException {
		// TODO Auto-generated method stub
		StudentJDBCDAOImpl s = StudentJDBCDAOImpl.getInstance();
		Student s1= new Student("Mohammed","Baker",82.0);
		s.delete(6);
		s.delete(5);
		
		
		
		List<Student> stds = s.getAll().get();
		for(Student ss: stds)
			System.out.println(ss.toString());
		

	}

}
