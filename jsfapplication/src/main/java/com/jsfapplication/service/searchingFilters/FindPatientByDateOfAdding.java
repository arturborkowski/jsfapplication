package com.jsfapplication.service.searchingFilters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public class FindPatientByDateOfAdding extends FindPatientByDate {


	List<Patient> matches = new ArrayList<Patient>();
	@SuppressWarnings("deprecation")
	Date doa, start = new Date(0, 0, 1), finish = new Date();   // defined default start and ending values
	
	long doaInMillis, startInMillis, finishInMillis;
	
	@Override
	public List<Patient> find(PatientToFind ptf, List<Patient> list) {
		
		matches.clear();
		
		
		if(ptf.getAddingDateStart().length()>0) {
			start = parseFromStringToDate(ptf.getAddingDateStart());
			startInMillis = start.getTime();
		}
		else
			startInMillis = start.getTime();
			
		if(ptf.getAddingDateFinish().length()>0) {
			finish = parseFromStringToDate(ptf.getAddingDateFinish());
			finishInMillis = finish.getTime();
		}
		else
			finishInMillis = finish.getTime();
		
	
		for(Patient p: list) {
			
			doa = p.getAddingDate();
			doaInMillis = doa.getTime();
			
			System.out.println(startInMillis);
			System.out.println(doaInMillis);
			System.out.println(finishInMillis);
			
			if((doaInMillis<=finishInMillis) && (doaInMillis>=startInMillis)) 				
				matches.add(p);
		}
		
		return matches;
	}


	@Override
	public boolean canDoSearch(PatientToFind ptf) {
		if(ptf.getAddingDateStart().length()>0 || ptf.getAddingDateFinish().length()>0) return true;
		else return false;
	}
}
