package com.jsfapplication.service.comparators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jsfapplication.domain.Patient;

public class CompareByHeight implements Comparator<Patient>{

	@Override
	public int compare(Patient o1, Patient o2) {
		int height1 = Integer.parseInt(o1.getHeight());
		long height2 = Integer.parseInt(o2.getHeight());
		
		if(height1<height2) return -1;
		else if(height1>height2) return 1;
		else return 0;
	}
	
	public void sortByHeight(List<Patient> list) {
		Collections.sort(list, new CompareByHeight());
	}

}
