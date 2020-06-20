package com.erabic.StudentDAOExample.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.erabic.StudentDAOExample.bean.Student;
import com.erabic.StudentDAOExample.dao.StudentDAO;
import com.erabic.StudentDAOExample.exception.StudentDAOException;
import com.erabic.StudentDAOExample.exception.enums.StudentDAOExceptionType;

public class StudentJDBCDAOImpl implements StudentDAO {
	private Connection connection;

	private static class StudentJDBCDAOImplHelper {
		private static final StudentJDBCDAOImpl STUDENT_DAO_OBJECT = new StudentJDBCDAOImpl();
	}

	private StudentJDBCDAOImpl() {
	}

	public static StudentJDBCDAOImpl getInstance() {
		return StudentJDBCDAOImplHelper.STUDENT_DAO_OBJECT;
	}

	private void connectToDatabase() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		Properties prop = new Properties();

		prop.load(new FileInputStream("config.properties"));
		String name = prop.getProperty("name");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String url = prop.getProperty("url");

		Class.forName(name);
		Properties info = new Properties();
		info.put("user", username);
		info.put("password", password);
		connection = DriverManager.getConnection(url, info);

	}

	@Override
	public void add(Student std)
			throws StudentDAOException, FileNotFoundException, SQLException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		if (std == null)
			throw new IllegalArgumentException();
		if (std.getAvg() < 0)
			throw new StudentDAOException(StudentDAOExceptionType.INVALID_AVG,
					StudentDAOExceptionType.INVALID_AVG.getMsg(), null);
		if (connection == null) {
			connectToDatabase();
			if (connection == null)
				throw new StudentDAOException(StudentDAOExceptionType.MISSING_CONNECTION,
						StudentDAOExceptionType.MISSING_CONNECTION.getMsg(), null);
		}
		if (get(std.getId()) != null)
			throw new StudentDAOException(StudentDAOExceptionType.EXISTING_STUDENT,
					StudentDAOExceptionType.EXISTING_STUDENT.getMsg(), null);

		PreparedStatement prStatement = connection
				.prepareStatement("insert into students (FirstName,LastName,Avg) values(?,?,?)");
		prStatement.setString(1, std.getFirstName());
		prStatement.setString(2, std.getLastName());
		prStatement.setDouble(3, std.getAvg());
		prStatement.executeUpdate();
		
	}

	@Override
	public void update(int id, Student std)
			throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		if (std == null)
			throw new IllegalArgumentException();
		if (std.getAvg() < 0)
			throw new StudentDAOException(StudentDAOExceptionType.INVALID_AVG,
					StudentDAOExceptionType.INVALID_AVG.getMsg(), null);
		if (connection == null) {
			connectToDatabase();
			if (connection == null)
				throw new StudentDAOException(StudentDAOExceptionType.MISSING_CONNECTION,
						StudentDAOExceptionType.MISSING_CONNECTION.getMsg(), null);
		}
		if (get(id) == null)
			throw new StudentDAOException(StudentDAOExceptionType.NO_EXISTING_STUDENT,
					StudentDAOExceptionType.NO_EXISTING_STUDENT.getMsg(), null);
		PreparedStatement prStatement = connection
				.prepareStatement("update  students set FirstName=?,LastName=?,Avg=? where Id=?");
		prStatement.setString(1, std.getFirstName());
		prStatement.setString(2, std.getLastName());
		prStatement.setDouble(3, std.getAvg());
		prStatement.setInt(4, id);

		prStatement.executeUpdate();

	}

	@Override
	public void delete(int id)
			throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub

		if (connection == null) {
			connectToDatabase();
			if (connection == null)
				throw new StudentDAOException(StudentDAOExceptionType.MISSING_CONNECTION,
						StudentDAOExceptionType.MISSING_CONNECTION.getMsg(), null);
		}
		if (get(id) == null)
			throw new StudentDAOException(StudentDAOExceptionType.NO_EXISTING_STUDENT,
					StudentDAOExceptionType.NO_EXISTING_STUDENT.getMsg(), null);

		PreparedStatement prStatement = connection.prepareStatement("delete from students where Id=?");

		prStatement.setInt(1, id);

		prStatement.executeUpdate();

	}

	@Override
	public Optional<Student> get(int id)
			throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException {
		// TODO Auto-generated method stub
		if (connection == null) {
			connectToDatabase();
			if (connection == null)
				throw new StudentDAOException(StudentDAOExceptionType.MISSING_CONNECTION,
						StudentDAOExceptionType.MISSING_CONNECTION.getMsg(), null);
		}
		PreparedStatement prStatement = connection.prepareStatement("select * from students where Id=?");

		prStatement.setInt(1, id);

		ResultSet set = prStatement.executeQuery();
		if (set.next()) {
			Student std = new Student(set.getString("FirstName"), set.getString("LastName"), set.getDouble("Avg"));
			std.setId(set.getInt("Id"));
			return Optional.ofNullable(std);

		}else {
			throw new StudentDAOException(StudentDAOExceptionType.NO_EXISTING_STUDENT, StudentDAOExceptionType.NO_EXISTING_STUDENT.getMsg(), null);
		}
	
	}

	@Override
	public Optional<List<Student>> getAll()
			throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException {
		// TODO Auto-generated method stub
		if (connection == null) {
			connectToDatabase();
			if (connection == null)
				throw new StudentDAOException(StudentDAOExceptionType.MISSING_CONNECTION,
						StudentDAOExceptionType.MISSING_CONNECTION.getMsg(), null);
		}
		Statement stmt = connection.createStatement();
		ResultSet set = stmt.executeQuery("select * from students");
		List<Student> students = new LinkedList<>();
		while (set.next()) {
			Student std = new Student(set.getString("FirstName"), set.getString("LastName"), set.getDouble("Avg"));
			std.setId(set.getInt("Id"));
			students.add(std);

		}

		return Optional.ofNullable(students);
	}

}
