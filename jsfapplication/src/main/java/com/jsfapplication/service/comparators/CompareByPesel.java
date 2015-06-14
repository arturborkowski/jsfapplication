package com.jsfapplication.service.comparators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jsfapplication.domain.Patient;

public class CompareByPesel implements Comparator<Patient> {

	@Override
	public int compare(Patient o1, Patient o2) {
		int pesel1 = Integer.parseInt(o1.getPesel());
		int pesel2 = Integer.parseInt(o2.getPesel());
		
		if(pesel1<pesel2) return -1;
		else if(pesel1>pesel2) return 1;
		else return 0;
	}
	
	public void sortByPesel(List<Patient> list) {
		Collections.sort(list, new CompareByPesel());
	}

}
