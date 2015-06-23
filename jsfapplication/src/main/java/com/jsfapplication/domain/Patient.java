package com.jsfapplication.domain;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name = "patient.all", query = "Select p from Patient p")
public class Patient implements Comparable<Patient>{
	
	
	private Long id;	
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
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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
	
	@Temporal(value = TemporalType.DATE)
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


	@Temporal(value = TemporalType.DATE)
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
