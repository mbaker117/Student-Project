package com.erabia.student_project.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.erabia.csvfile.exception.CSVFileException;
import com.erabia.student_project.bean.Student;
import com.erabic.StudentDAOExample.exception.StudentDAOException;



public interface StudentService {
	
	public void add(Student std) throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException, CSVFileException;

	public void update(int id, Student std) throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException, CSVFileException;

	public void delete(int id) throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException, CSVFileException;

	public Optional<Student> get(int id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException, CSVFileException;

	public Optional<List<Student>> getAll() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException, CSVFileException;
	
}
