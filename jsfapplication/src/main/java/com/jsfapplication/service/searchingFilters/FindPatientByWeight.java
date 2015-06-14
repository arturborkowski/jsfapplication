package com.jsfapplication.service.searchingFilters;

import java.util.ArrayList;
import java.util.List;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public class FindPatientByWeight implements IPatientFinder {

	List<Patient> matches = new ArrayList<Patient>();
	int weight, start = 0, finish = 500;    // defined default start and ending values

	@Override
	public List<Patient> find(PatientToFind ptf, List<Patient> list) {
		
		matches.clear();
		
		if(ptf.getWeightStart().length()>0)
			start = Integer.parseInt(ptf.getWeightStart());
		if(ptf.getWeightFinish().length()>0)
			finish = Integer.parseInt(ptf.getWeightFinish());
		
		for(Patient p: list) {
			
			weight = Integer.parseInt(p.getWeight());
			
			if((weight<=finish) && (weight>=start)) 				
				matches.add(p);
		}
		
		return matches;
	}

	@Override
	public boolean canDoSearch(PatientToFind ptf) {
		if(ptf.getWeightStart().length()>0 || ptf.getWeightFinish().length()>0) return true;
		else return false;
	}
}
