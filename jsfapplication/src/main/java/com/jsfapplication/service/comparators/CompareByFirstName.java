package com.jsfapplication.service.comparators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jsfapplication.domain.Patient;

public class CompareByFirstName implements Comparator<Patient> {


	@Override
	public int compare(Patient o1, Patient o2) {
		return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
	}
	
	public void sortByFirstName(List<Patient> list) {
		Collections.sort(list, new CompareByFirstName());
	}

}
