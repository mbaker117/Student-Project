package com.erabia.student_project.test;

import java.io.IOException;
import java.util.Scanner;

import com.erabia.student_project.Services.StudentService;
import com.erabia.student_project.facade.StudentFacade;
import com.erabia.student_project.facade.impl.StudentFacadeImpl;
import com.erabia.student_project.util.DataSourceUtil;

public class Client {
	private final static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean exit = false;

		StudentService stdService = null;
		do {
			System.out.print("Select you data source (JDBC or CSV):");
			String dataSourceType = scanner.nextLine();
			try {
				stdService = DataSourceUtil.getStudentService(dataSourceType);
				if (stdService == null)
					System.out.println("Unsupported data source");
			} catch (IOException e) {
				System.out.println(e.toString());
			}

		} while (stdService == null);
		StudentFacade stdFacde = new StudentFacadeImpl(stdService);
		while (!exit) {
			printInstruction();
			String choice = scanner.nextLine();
			try {
				switch (choice) {
				case "1":
					stdFacde.addStudent();
					break;
				case "2":
					stdFacde.updateStudent();
					break;
				case "3":
					stdFacde.removeStudent();
					break;
				case "4":
					stdFacde.printStudent();
					break;
				case "5":
					stdFacde.printAllStudent();
					break;
				case "6":
					exit = true;
					break;
				default:
					System.out.println("Incorrect Input!!");

				}
			} catch (Exception ex) {
			System.out.println(ex.toString());
			}
		}

	}

	private static void printInstruction() {
		System.out.println("Select operation\n1. Add student\n2. Update student.\n3. Remove student.\n"
				+ "4. Print student.\n5. Print all students.\n6. Exit.");
	}

}
