package com.jsfapplication.service.searchingFilters;

import java.util.List;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public interface IPatientFinder {

	public boolean canDoSearch(PatientToFind ptf);
	public List<Patient> find(PatientToFind ptf, List<Patient> list);
	
}
