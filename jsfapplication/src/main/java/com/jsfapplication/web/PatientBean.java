package com.jsfapplication.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.service.PatientsManager;



@SessionScoped
@Named("patientBean")
public class PatientBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private Patient patient = new Patient();
	private ListDataModel<Patient> patients = new ListDataModel<Patient>();
	
	@Inject
	private PatientsManager pm;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public void setPatients(ListDataModel<Patient> patients) {
		this.patients = patients;
	}
	
	public ListDataModel<Patient> getPatients() {
		patients.setWrappedData(pm.getAllPatients());
		return patients;
	}
	
	public String addPatient() {
		pm.addPatient(patient);
		patient.setDateOfBirth(null);
		patient.setFirstName("");
		patient.setLastName("");
		patient.setHeight("");
		patient.setPesel("");
		patient.setAddress("");
		patient.setPhoneNumber("");
		patient.setWeight("");
		return "showAll";
		// return null;  // w sytuacji, kiedy chce zostac na obecnym widoku.
	}
	
	
	
	public void validatePeselAndDateOfBirth(ComponentSystemEvent event) {

		UIForm form = (UIForm) event.getComponent();
		UIInput pesel = (UIInput) form.findComponent("pesel");
		UIInput dateOfBirth = (UIInput) form.findComponent("dateOfBirth");

		if (pesel.getValue() != null && dateOfBirth.getValue() != null
				&& pesel.getValue().toString().length() >= 2) {
			String twoDigitsOfPin = pesel.getValue().toString().substring(0, 2);
			Calendar cal = Calendar.getInstance();
			cal.setTime(((Date) dateOfBirth.getValue()));

			String lastDigitsOfDob = ((Integer) cal.get(Calendar.YEAR))
					.toString().substring(2);

			if (!twoDigitsOfPin.equals(lastDigitsOfDob)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(form.getClientId(), new FacesMessage(
						"Pesel doesn't match date of birth"));
				context.renderResponse();
			}
		}
	}
	
	
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
	
	
	public void peselValidator(FacesContext context, UIComponent component, 
			Object value) {
		
		String pesel = (String) value;
		Pattern pattern = Pattern.compile("\\d{11}");
		Matcher match = pattern.matcher(pesel);
		
		if(match.find()) {
			uniquePesel(context, component, value);
			peselControlSum(context, component, value);
		} else {
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
		
		if(sumaKontr != peselek[10]) {
			FacesMessage message = new FacesMessage(
					"There is an error in pesel. Check it once again.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

	
	public void phoneNumberValidator(FacesContext context, UIComponent component,
			Object value) {
		
		String phoneNr = (String) value;
		
		if(phoneNr.isEmpty()) {
			return;
		} else {
			Pattern pattern = Pattern.compile("\\d{9}");
			Matcher match = pattern.matcher(phoneNr);
			
			if(!match.find()) {
				FacesMessage message = new FacesMessage(
						"Phone number should contain 9 digits.");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
		
	}
	

}
