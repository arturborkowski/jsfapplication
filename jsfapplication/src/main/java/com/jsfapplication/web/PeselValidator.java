package com.jsfapplication.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.service.PatientsManager;


@FacesValidator("peselValidator")
public class PeselValidator implements Validator{

	@Inject
	private PatientsManager pm;
	
	public void uniquePesel(FacesContext context, UIComponent component,
			Object value) {

		String pesel = (String) value;

		for (Patient patient : pm.getAllPatients()) {
			if (patient.getPesel().equalsIgnoreCase(pesel)) {
				FacesMessage message = new FacesMessage(
						"Person with this PESEL already exists in database");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}
	
	
	public void peselFormatValidator(FacesContext context, UIComponent component, 
			Object value) {
		
		String pesel = (String) value;
		Pattern pattern = Pattern.compile("\\d{11}");
		Matcher match = pattern.matcher(pesel);
		
		if(!match.find()) {
			FacesMessage message = new FacesMessage(
					"Pesel must contain 11 digits.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		
	}
	
	public void peselControlSum(FacesContext context, UIComponent component,
			Object value) {
		
		String pesel = (String) value;
		int[] peselek = new int[11];
		for(int i = 0; i < 11; i++) {
			peselek[i] = Character.getNumericValue(pesel.charAt(i));
		}
		
		int suma = peselek[0]+3*peselek[1]+7*peselek[2]+9*peselek[3]+peselek[4]
				+3*peselek[5]+7*peselek[6]+9*peselek[7]+peselek[8]+3*peselek[9];
		int sumaKontr = suma%10;
		if(sumaKontr != 0)
			sumaKontr = 10 - sumaKontr;
		else
			sumaKontr = 0;
		
		System.out.println("suma kontr: = " + sumaKontr);
		if(sumaKontr != peselek[10]) {
			FacesMessage message = new FacesMessage(
					"There is an error in pesel. Check it once again.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}


	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		peselFormatValidator(context, component, value);
		peselControlSum(context, component, value);
		uniquePesel(context, component, value);
		
	}

}
