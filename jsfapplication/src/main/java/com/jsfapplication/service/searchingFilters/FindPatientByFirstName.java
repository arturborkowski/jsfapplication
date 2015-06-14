package com.jsfapplication.service.searchingFilters;

import java.util.ArrayList;
import java.util.List;



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
		}
		
		return matches;
	}

	
	@Override
	public boolean canDoSearch(PatientToFind ptf) {
		if(ptf.getFirstName().length()>0)
			return true;
		else
			return false;
	}

}
