package com.jsfapplication.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.service.PatientsManager;



@SessionScoped
@Named("patientBean")
public class PatientBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private Patient patient = new Patient();
	private ListDataModel<Patient> patients = new ListDataModel<Patient>();
	private PatientsManager pm = new PatientsManager();

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void setPatients(ListDataModel<Patient> patients) {
		this.patients = patients;
	}
	
	public ListDataModel<Patient> getPatients() {
		patients.setWrappedData(pm.getAllPatients());
		return patients;
	}
	
	public String addPatient() {
		pm.addPatient(patient);
		patient.setDateOfBirth("");
		patient.setFirstName("");
		patient.setLastName("");
		patient.setHeight("");
		patient.setPesel("");
		patient.setPhoneNumber("");
		patient.setWeight("");
		return "showAll";
	}

}
