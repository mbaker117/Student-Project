package com.erabic.StudentDAOExample.bean;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private double avg;
	
	public Student(String firstName, String lastName, double avg) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.avg = avg;
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", avg=" + avg + "]";
	}
	
	
}
