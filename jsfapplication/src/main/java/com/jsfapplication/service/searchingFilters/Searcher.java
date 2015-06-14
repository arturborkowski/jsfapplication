package com.jsfapplication.service.searchingFilters;

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
	
	
	private void addFilters(PatientToFind ptf) {
		
			filters.add(new FindPatientByFirstName());
			filters.add(new FindPatientByLastName());
			filters.add(new FindPatientByPesel());		
			filters.add(new FindPatientByWeight());		
			filters.add(new FindPatientByHeight());		
			filters.add(new FindPatientByDateOfBirth());	
			filters.add(new FindPatientByDateOfAdding());
	}
	
	
	public HashSet<Patient> doTheSearch(PatientToFind ptf, List<Patient> list) {
		
		results.clear();
		filters.clear();
		addFilters(ptf);
		
		for(IPatientFinder pf: filters) {
			if(pf.canDoSearch(ptf))
				results.addAll(pf.find(ptf, list));			
		}
		
		
		return results;
	}
	

}
