package com.jsfapplication.service.searchingFilters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public class FindPatientByDateOfBirth extends FindPatientByDate {



	List<Patient> matches = new ArrayList<Patient>();
	@SuppressWarnings("deprecation")
	Date dob, start = new Date(0, 0, 1), finish = new Date();   // defined default start and ending values
	
	long dobInMillis, startInMillis, finishInMillis;
	
	@Override
	public List<Patient> find(PatientToFind ptf, List<Patient> list) {
		
		matches.clear();
		
		
		if(ptf.getDobStart().length()>0) {
			start = parseFromStringToDate(ptf.getDobStart());
			startInMillis = start.getTime();
		}
		else
			startInMillis = start.getTime();
			
		if(ptf.getDobFinish().length()>0) {
			finish = parseFromStringToDate(ptf.getDobFinish());
			finishInMillis = finish.getTime();
		}
		else
			finishInMillis = finish.getTime();
		
	
		for(Patient p: list) {
			
			dob = p.getDateOfBirth();
			dobInMillis = dob.getTime();
			
			if((dobInMillis<=finishInMillis) && (dobInMillis>=startInMillis)) 				
				matches.add(p);
		}
		
		return matches;
	}

	@Override
	public boolean canDoSearch(PatientToFind ptf) {
		if(ptf.getDobStart().length()>0 || ptf.getDobFinish().length()>0) return true;
		else return false;
	}
	
	

}
