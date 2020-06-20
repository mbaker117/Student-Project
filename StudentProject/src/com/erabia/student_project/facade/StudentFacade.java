package com.erabia.student_project.facade;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.erabia.csvfile.exception.CSVFileException;
import com.erabic.StudentDAOExample.exception.StudentDAOException;

public interface StudentFacade {
	public void addStudent() throws ClassNotFoundException, FileNotFoundException, StudentDAOException, SQLException, IOException, CSVFileException;
	public void updateStudent() throws ClassNotFoundException, FileNotFoundException, StudentDAOException, SQLException, IOException, CSVFileException;
	public void removeStudent() throws ClassNotFoundException, FileNotFoundException, StudentDAOException, SQLException, IOException, CSVFileException;
	public void printStudent() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException, CSVFileException;
	public void printAllStudent() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException, CSVFileException;

}
