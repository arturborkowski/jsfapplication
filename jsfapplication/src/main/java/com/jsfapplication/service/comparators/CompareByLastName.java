package com.jsfapplication.service.comparators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jsfapplication.domain.Patient;

public class CompareByLastName implements Comparator<Patient> {

	@Override
	public int compare(Patient o1, Patient o2) {
		return o1.getLastName().compareToIgnoreCase(o2.getLastName());
	}
	
	public void sortByLastName(List<Patient> list) {
		Collections.sort(list, new CompareByLastName());
	}

}
