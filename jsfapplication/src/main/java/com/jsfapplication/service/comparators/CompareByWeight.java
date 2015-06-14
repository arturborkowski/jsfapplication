package com.jsfapplication.service.comparators;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jsfapplication.domain.Patient;

public class CompareByWeight implements Comparator<Patient>{

	@Override
	public int compare(Patient o1, Patient o2) {
		int weight1 = Integer.parseInt(o1.getWeight());
		long weight2 = Integer.parseInt(o2.getWeight());
		
		if(weight1<weight2) return -1;
		else if(weight1>weight2) return 1;
		else return 0;
	}
	
	public void sortByWeight(List<Patient> list) {
		Collections.sort(list, new CompareByWeight());
	}

}
