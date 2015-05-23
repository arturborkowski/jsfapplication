package com.jsfapplication.service;

import java.util.List;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;

public interface IPatientFinder {

	public List<Patient> find(PatientToFind ptf, List<Patient> list);
	
}
