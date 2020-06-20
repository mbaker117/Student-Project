package com.erabia.student_project.Services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.erabia.student_project.Services.StudentService;
import com.erabia.student_project.bean.Student;
import com.erabic.StudentDAOExample.dao.impl.StudentJDBCDAOImpl;
import com.erabic.StudentDAOExample.exception.StudentDAOException;
import com.erabic.StudentDAOExample.exception.enums.StudentDAOExceptionType;

public class StudentServiceJDBCImpl implements StudentService {
	private StudentJDBCDAOImpl jdbcObject;

	public StudentServiceJDBCImpl() {
		jdbcObject = StudentJDBCDAOImpl.getInstance();

	}

	@Override
	public void add(Student std)
			throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		com.erabic.StudentDAOExample.bean.Student student = new com.erabic.StudentDAOExample.bean.Student(
				std.getFirstName(), std.getLastName(), std.getAvg());
		jdbcObject.add(student);

	}

	@Override
	public void update(int id, Student std)
			throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		com.erabic.StudentDAOExample.bean.Student student = new com.erabic.StudentDAOExample.bean.Student(
				std.getFirstName(), std.getLastName(), std.getAvg());
		student.setId(std.getId());
		jdbcObject.update(id, student);
	}

	@Override
	public void delete(int id)
			throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		jdbcObject.delete(id);

	}

	@Override
	public Optional<Student> get(int id)
			throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException {
		// TODO Auto-generated method stub

		Optional<com.erabic.StudentDAOExample.bean.Student> student = jdbcObject.get(id);
		if (student.isPresent()) {
			Student std = new Student(student.get().getFirstName(), student.get().getLastName(),
					student.get().getAvg());
			std.setId(student.get().getId());
			return Optional.ofNullable(std);
		} else {
			throw new StudentDAOException(StudentDAOExceptionType.NO_EXISTING_STUDENT,
					StudentDAOExceptionType.NO_EXISTING_STUDENT.getMsg(), null);
		}
	}

	@Override
	public Optional<List<Student>> getAll()
			throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException {
		List<Student> students = new LinkedList<>();
		List<com.erabic.StudentDAOExample.bean.Student> daoStudents = jdbcObject.getAll().get();
		for (com.erabic.StudentDAOExample.bean.Student std : daoStudents) {
			Student newStd = new Student(std.getFirstName(), std.getLastName(), std.getAvg());
			newStd.setId(std.getId());
			students.add(newStd);
		}
		return Optional.ofNullable(students);
	}

}
