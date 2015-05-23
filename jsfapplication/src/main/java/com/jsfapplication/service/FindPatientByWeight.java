package com.jsfapplication.service;

import java.util.ArrayList;
import java.util.List;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public class FindPatientByWeight implements IPatientFinder {

	List<Patient> matches = new ArrayList<Patient>();
	int weight, start = 0, finish = 500;

	@Override
	public List<Patient> find(PatientToFind ptf, List<Patient> list) {
		
		matches.clear();
			start = Integer.parseInt(ptf.getWeightStart());
			finish = Integer.parseInt(ptf.getWeightFinish());
		
		for(Patient p: list) {
			
			weight = Integer.parseInt(p.getWeight());
			
			if((weight<=finish) && (weight>=start)) 				
				matches.add(p);
			else 
				System.out.println("Nie ma recordu po wadze.");
		}
		
		return matches;
	}
}
