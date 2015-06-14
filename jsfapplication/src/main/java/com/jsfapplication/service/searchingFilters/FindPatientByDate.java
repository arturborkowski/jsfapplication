package com.jsfapplication.service.searchingFilters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FindPatientByDate implements IPatientFinder {
	
	
	protected Date parseFromStringToDate(String s) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date result = null;
		
		try {
			result = df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

}
