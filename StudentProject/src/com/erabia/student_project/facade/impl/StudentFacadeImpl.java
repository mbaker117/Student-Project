package com.erabia.student_project.facade.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

import com.erabia.csvfile.exception.CSVFileException;
import com.erabia.student_project.Services.StudentService;
import com.erabia.student_project.bean.Student;
import com.erabia.student_project.facade.StudentFacade;
import com.erabic.StudentDAOExample.exception.StudentDAOException;

public class StudentFacadeImpl implements StudentFacade {
	private StudentService stdService;
	private Scanner in;

	public StudentFacadeImpl(StudentService stdService) {
		this.stdService = stdService;
		in = new Scanner(System.in);
	}

	@Override
	public void addStudent() throws ClassNotFoundException, FileNotFoundException, StudentDAOException, SQLException,
			IOException, CSVFileException {
		// TODO Auto-generated method stub
		System.out.println("\n Add Student:");
		System.out.println("Enter first name: ");
		String firstName = in.nextLine();
		System.out.println("Enter last name: ");
		String lastName = in.nextLine();
		double avg = 0;
		try {
			System.out.println("Enter Student avg: ");
			avg = in.nextDouble();
			in.nextLine();
			
		} catch (IllegalFormatException ex) {
			throw ex;

		}
		stdService.add(new Student(firstName, lastName, avg));

	}

	@Override
	public void updateStudent() throws ClassNotFoundException, FileNotFoundException, StudentDAOException, SQLException,
			IOException, CSVFileException {
		// TODO Auto-generated method stub
		System.out.println("\n Update Student:");
		System.out.println("Enter new updated student:");
		System.out.println("Enter first name: ");
		String firstName = in.nextLine();
		System.out.println("Enter last name: ");
		String lastName = in.nextLine();
		double avg = 0;
		int id = -1;
		try {
			System.out.println("Enter Student avg: ");
			avg = in.nextDouble();
		
			System.out.print("Enter id for old student:");
			id = in.nextInt();
			in.nextLine();
		} catch (IllegalFormatException ex) {
			throw ex;

		}
		stdService.update(id, new Student(firstName, lastName, avg));
	}

	@Override
	public void removeStudent() throws ClassNotFoundException, FileNotFoundException, StudentDAOException, SQLException,
			IOException, CSVFileException {
		// TODO Auto-generated method stub
		System.out.println("\n Remove Student:");

		int id = -1;
		try {

			System.out.print("Enter id for student to delete:");
			id = in.nextInt();
			in.hasNextLine();
		} catch (IllegalFormatException ex) {
			throw ex;

		}
		stdService.delete(id);

	}

	@Override
	public void printStudent() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException,
			StudentDAOException, CSVFileException {
		// TODO Auto-generated method stub

		System.out.println("\n Remove Student:");

		int id = -1;
		try {

			System.out.print("Enter id for student to print:");
			id = in.nextInt();
			in.hasNextLine();
		} catch (IllegalFormatException ex) {
			throw ex;

		}
		Student std = stdService.get(id).get();
		System.out.println(std.toString());
	}

	@Override
	public void printAllStudent() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException, StudentDAOException, CSVFileException {
		System.out.println("\n Print Students:");
		List<Student> list = stdService.getAll().get();

		for(Student std:list) {
			System.out.println(std.toString());
			
		}

	}

}
