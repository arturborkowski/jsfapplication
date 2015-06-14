package com.jsfapplication.service.comparators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jsfapplication.domain.Patient;

public class CompareByDateOfAdding implements Comparator<Patient> {

	@Override
	public int compare(Patient o1, Patient o2) {
		long doa1 = o1.getAddingDate().getTime();
		long doa2 = o2.getAddingDate().getTime();
		
		if(doa1<doa2) return -1;
		else if(doa1>doa2) return 1;
		else return 0;
	}
	
	public void sortByDateOfAdding(List<Patient> list) {
		Collections.sort(list, new CompareByDateOfAdding());
	}

}
