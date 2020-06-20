package com.erabia.student_project.Services.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.erabia.csvfile.bean.UpdateRecordData;
import com.erabia.csvfile.exception.CSVFileException;
import com.erabia.csvfile.service.impl.CSVFileServiceImpl;
import com.erabia.student_project.Services.StudentService;
import com.erabia.student_project.bean.Student;
import com.erabic.StudentDAOExample.exception.StudentDAOException;

public class StudentServiceCSVImpl implements StudentService {
	private CSVFileServiceImpl csvObject;
	private String path;
	private String sp;
	private boolean isHeader;

	public StudentServiceCSVImpl() throws FileNotFoundException, IOException {
		csvObject = CSVFileServiceImpl.getInstance();
		Properties prop = new Properties();
		prop.load(new FileInputStream("CSVConfig.properties"));
		path = prop.getProperty("path");
		sp = prop.getProperty("sperator");
		isHeader = prop.getProperty("header").equalsIgnoreCase("yes");
	}

	@Override
	public void add(Student std) throws StudentDAOException, ClassNotFoundException, FileNotFoundException,
			SQLException, IOException, CSVFileException {
		// TODO Auto-generated method stub
		int id = getAll().get().size();
		List<String> stdList = new LinkedList<>();
		stdList.add(String.valueOf((id+1)));
		stdList.add(std.getFirstName());
		stdList.add(std.getLastName());
		stdList.add(String.valueOf(std.getAvg()));
		csvObject.add(path, sp, isHeader, stdList);

	}

	@Override
	public void update(int id, Student std) throws StudentDAOException, ClassNotFoundException, FileNotFoundException,
			SQLException, IOException, CSVFileException {
		HashMap<Integer, UpdateRecordData> map = new HashMap<>();
		List<String> stdList = new LinkedList<>();
	
		stdList.add(String.valueOf(id));
		stdList.add(std.getFirstName());
		stdList.add(std.getLastName());
		stdList.add(String.valueOf(std.getAvg()));
		UpdateRecordData data = new UpdateRecordData(String.valueOf(id), stdList);
		map.put(0, data);
		csvObject.updateRecord(path, sp, isHeader, map);

	}

	@Override
	public void delete(int id) throws StudentDAOException, ClassNotFoundException, FileNotFoundException, SQLException,
			IOException, CSVFileException {
		// TODO Auto-generated method stub
		HashMap<Integer, String> map = new HashMap<>();
		map.put(0, String.valueOf(id));
		csvObject.remove(path, sp, isHeader, map);

	}

	@Override
	public Optional<Student> get(int id) throws ClassNotFoundException, FileNotFoundException, SQLException,
			IOException, StudentDAOException, CSVFileException {
		// TODO Auto-generated method stub
		HashMap<Integer, String> map = new HashMap<>();
		map.put(0, String.valueOf(id));

		Optional<List<List<String>>> list = csvObject.find(path, sp, isHeader, map);
		Iterator<String> it = list.get().get(0).iterator();
		Student std = null;
		if (it.hasNext()) {
			int stdId = Integer.parseInt(it.next());
			String firstName = it.next();
			String lastName = it.next();
			double avg = Double.parseDouble(it.next());
			std = new Student(firstName, lastName, avg);
			std.setId(stdId);

		}
		return Optional.ofNullable(std);
	}

	@Override
	public Optional<List<Student>> getAll() throws ClassNotFoundException, FileNotFoundException, SQLException,
			IOException, StudentDAOException, CSVFileException {
		Optional<List<List<String>>> list = csvObject.getData(path, sp, isHeader);
		Iterator<List<String>> listIt = list.get().iterator();
		List<Student> stdList = new LinkedList<>();
		while (listIt.hasNext()) {
			Iterator<String> it = listIt.next().iterator();
			if (it.hasNext()) {

				int stdId = Integer.parseInt(it.next());
				String firstName = it.next();
				String lastName = it.next();
				double avg = Double.parseDouble(it.next());
				Student std = new Student(firstName, lastName, avg);
				std.setId(stdId);
				stdList.add(std);
			}
		}
		return Optional.ofNullable(stdList);
	}

}
