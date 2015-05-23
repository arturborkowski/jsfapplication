package com.jsfapplication.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;
import com.jsfapplication.service.PatientsManager;
import com.jsfapplication.service.Searcher;


@SessionScoped
@Named("patientToFindBean")
public class PatientToFindBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private PatientToFind patientToFind = new PatientToFind();
	@Inject
	private PatientsManager pm;
	private List<Patient> matches = new ArrayList<Patient>();
	private ListDataModel<Patient> patients = new ListDataModel<Patient>();
	private Searcher searcher = new Searcher();
	
	
	public PatientToFind getPatientToFind() {
		return patientToFind;
	}
	public void setPatientToFind(PatientToFind patientToFind) {
		this.patientToFind = patientToFind;
	}
	
	public List<Patient> getMatches() {
		return matches;
	}
	public void setMatches(List<Patient> matches) {
		this.matches = matches;
	}
	public ListDataModel<Patient> getPatients() {
		if(!matches.isEmpty())
			patients.setWrappedData(matches);
		else patients.setWrappedData(pm.getAllPatients());
		return patients;
	}
	public void setPatients(ListDataModel<Patient> patients) {
		this.patients = patients;
	}
	
	
	
	
	
	public String findMatchingRecords() {

		matches.clear();
		matches.addAll(searcher.doTheSearch(patientToFind, pm.getAllPatients()));
		
		return "showAll";
	}
	
	

}
