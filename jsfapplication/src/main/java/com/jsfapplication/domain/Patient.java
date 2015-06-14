package com.jsfapplication.domain;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


public class Patient implements Comparable<Patient>{
	
	@Size(min = 2, max = 50)
	private String firstName = "";
	private String lastName = "";
	private String pesel = "";
	@Past
	private Date dateOfBirth = new Date();
	private String address;
	private String phoneNumber = "";
	private String weight = "";
	private String height = "";
	private Date addingDate = new Date();
	
	

	public Patient() {}
	
	
	
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

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getDateOfBirthString() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String date = df.format(dateOfBirth);
		return date;
	}
	
	public Date getAddingDate() {
		return addingDate;
	}



	public void setAddingDate(Date addingDate) {
		this.addingDate = addingDate;
	}
	
	public String getAddingDateString() {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String date = df.format(addingDate);
		return date;
	}



	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}



	@Override
	public int compareTo(Patient o) {
		String thisFullName = this.lastName+" "+this.firstName;
		String otherFullName = o.getLastName()+" "+o.getFirstName();
		return thisFullName.compareToIgnoreCase(otherFullName);
	}



}
