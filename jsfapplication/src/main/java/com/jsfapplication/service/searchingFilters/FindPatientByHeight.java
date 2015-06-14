package com.jsfapplication.service.searchingFilters;

import java.util.ArrayList;
import java.util.List;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public class FindPatientByHeight implements IPatientFinder {

	List<Patient> matches = new ArrayList<Patient>();
	int height, start = 0, finish = 300;   // defined default start and ending values

	@Override
	public List<Patient> find(PatientToFind ptf, List<Patient> list) {
		
		matches.clear();
		
		if(ptf.getHeightStart().length()>0)
			start = Integer.parseInt(ptf.getHeightStart());
		if(ptf.getHeightFinish().length()>0)
			finish = Integer.parseInt(ptf.getHeightFinish());
		
		for(Patient p: list) {
			
			height = Integer.parseInt(p.getHeight());
			
			if((height<=finish) && (height>=start)) 				
				matches.add(p);
		}
		
		return matches;
	}

	
	@Override
	public boolean canDoSearch(PatientToFind ptf) {
		if(ptf.getHeightStart().length()>0 || ptf.getHeightFinish().length()>0) return true;
		else return false;
	}

}
