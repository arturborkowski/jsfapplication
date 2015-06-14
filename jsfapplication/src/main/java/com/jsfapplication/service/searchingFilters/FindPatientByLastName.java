package com.jsfapplication.service.searchingFilters;

import java.util.ArrayList;
import java.util.List;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public class FindPatientByLastName implements IPatientFinder {

	
	List<Patient> matches = new ArrayList<Patient>();

	@Override
	public List<Patient> find(PatientToFind ptf, List<Patient> list) {
		
		matches.clear();
		
		for(Patient p: list) {
			
			if(ptf.getLastName().equals(p.getLastName())) 				
				matches.add(p);
		}
		
		return matches;
	}

	
	@Override
	public boolean canDoSearch(PatientToFind ptf) {
		if(ptf.getLastName().length()>0)
			return true;
		else
			return false;
	}

}
