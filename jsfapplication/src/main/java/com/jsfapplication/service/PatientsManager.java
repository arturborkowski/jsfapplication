package com.jsfapplication.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;

import com.jsfapplication.domain.Patient;



@ApplicationScoped
public class PatientsManager {

	private static List<Patient> db = new ArrayList<Patient>();
	

	public void addPatient(Patient patient) {
		Patient newPatient = new Patient();
		
		newPatient.setFirstName(patient.getFirstName());
		newPatient.setLastName(patient.getLastName());
		newPatient.setPesel(patient.getPesel());
		newPatient.setDateOfBirth(patient.getDateOfBirth());
		newPatient.setPhoneNumber(patient.getPhoneNumber());
		newPatient.setWeight(patient.getWeight());
		newPatient.setHeight(patient.getHeight());
		
		db.add(newPatient);
	}
	
	
	public List<Patient> getAllPatients() {
		return db;
	}

}
