package com.jsfapplication.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public class Searcher {

	private HashSet<Patient> results = new HashSet<Patient>();
	private List<IPatientFinder> filters = new ArrayList<IPatientFinder>();
	
	
	
	public List<IPatientFinder> getFilters() {
		return filters;
	}
	public void setFilters(List<IPatientFinder> filters) {
		this.filters = filters;
	}
	
	
	private void checkFilters(PatientToFind ptf) {
		if(ptf.getFirstName()!=null)
			filters.add(new FindPatientByFirstName());
		if(ptf.getLastName()!=null)
			filters.add(new FindPatientByLastName());
		if(ptf.getPesel()!=null)
			filters.add(new FindPatientByPesel());
		
		/*
		 * 
		 * And another filters.
		 * 
		 */
	}
	
	
	public HashSet<Patient> doTheSearch(PatientToFind ptf, List<Patient> list) {
		
		results.clear();
		filters.clear();
		checkFilters(ptf);
		
		for(IPatientFinder pf: filters) {
			results.addAll(pf.find(ptf, list));			
		}
		
		
		return results;
	}
	

}
