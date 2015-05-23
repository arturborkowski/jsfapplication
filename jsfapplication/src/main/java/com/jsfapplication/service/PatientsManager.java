package com.jsfapplication.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.jsfapplication.domain.Patient;



@ApplicationScoped
public class PatientsManager {

	
	private List<Patient> db = new ArrayList<Patient>();
	

	public void addPatient(Patient patient) {
		Patient newPatient = new Patient();
		
		newPatient.setFirstName(patient.getFirstName());
		newPatient.setLastName(patient.getLastName());
		newPatient.setPesel(patient.getPesel());
		newPatient.setDateOfBirth(patient.getDateOfBirth());
		newPatient.setAddress(patient.getAddress());
		newPatient.setPhoneNumber(patient.getPhoneNumber());
		newPatient.setWeight(patient.getWeight());
		newPatient.setHeight(patient.getHeight());
		newPatient.setAddingDate(new Date());
		
		db.add(newPatient);
	}
	
	
	public List<Patient> getAllPatients() {
		return db;
	}

}
