package com.erabic.StudentDAOExample.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.erabic.StudentDAOExample.bean.Student;
import com.erabic.StudentDAOExample.dao.impl.StudentJDBCDAOImpl;
import com.erabic.StudentDAOExample.exception.StudentDAOException;
import com.erabic.StudentDAOExample.service.StudentService;

public class StudentServiceImpl implements StudentService {
	private static StudentJDBCDAOImpl studentObject = StudentJDBCDAOImpl.getInstance();
	@Override
	public void add(Student std) throws FileNotFoundException, ClassNotFoundException, StudentDAOException, SQLException, IOException {
		studentObject.add(std);
		
	}

	@Override
	public void update(int id, Student std)
			throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		studentObject.update(id, std);

	}

	@Override
	public void delete(int id)
			throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		studentObject.delete(id);

	}

	@Override
	public Optional<Student> get(int id)
			throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException {
		// TODO Auto-generated method stub
		return studentObject.get(id);
	}

	@Override
	public Optional<List<Student>> getAll()
			throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException {
		// TODO Auto-generated method stub
		return studentObject.getAll();
	}

}
