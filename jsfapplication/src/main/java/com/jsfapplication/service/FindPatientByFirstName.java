package com.jsfapplication.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public class FindPatientByFirstName implements IPatientFinder {

	List<Patient> matches = new ArrayList<Patient>();

	@Override
	public List<Patient> find(PatientToFind ptf, List<Patient> list) {
		
		matches.clear();
		
		for(Patient p: list) {
			if(ptf.getFirstName().equals(p.getFirstName())) 
				matches.add(p);
			else {
				System.out.println("Nie ma recordu po imieniu.");
				FacesContext facesContext = FacesContext.getCurrentInstance();	
				FacesMessage message = new FacesMessage("Matches not found.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesContext.addMessage("", message);
				
			}
		}
		
		return matches;
	}

}
