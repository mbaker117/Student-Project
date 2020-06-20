package com.erabic.StudentDAOExample.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.erabic.StudentDAOExample.bean.Student;
import com.erabic.StudentDAOExample.exception.StudentDAOException;

public interface StudentService {

	public void add(Student std) throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	public void update(int id, Student std) throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	public void delete(int id) throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException;

	public Optional<Student> get(int id) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException;

	public Optional<List<Student>> getAll() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException;
	
	
}
