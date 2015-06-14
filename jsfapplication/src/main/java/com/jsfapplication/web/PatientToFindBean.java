package com.jsfapplication.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.jsfapplication.domain.Patient;
import com.jsfapplication.domain.PatientToFind;
import com.jsfapplication.service.PatientsManager;
import com.jsfapplication.service.comparators.CompareByDateOfAdding;
import com.jsfapplication.service.comparators.CompareByFirstName;
import com.jsfapplication.service.comparators.CompareByHeight;
import com.jsfapplication.service.comparators.CompareByLastName;
import com.jsfapplication.service.comparators.CompareByWeight;
import com.jsfapplication.service.searchingFilters.Searcher;


@SessionScoped
@Named("patientToFindBean")
public class PatientToFindBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private PatientToFind patientToFind = new PatientToFind();
	@Inject
	private PatientsManager pm;
	private List<Patient> matches = new ArrayList<Patient>();
	private ListDataModel<Patient> patients = new ListDataModel<Patient>();
	private Searcher searcher = new Searcher();
	private enum Sorters {fn, ln, pesel, weight, height, doa};
	private static Comparator<Patient> comparator;
	
	
	public PatientToFind getPatientToFind() {
		return patientToFind;
	}
	public void setPatientToFind(PatientToFind patientToFind) {
		this.patientToFind = patientToFind;
	}
	
	public List<Patient> getMatches() {
		return matches;
	}
	public void setMatches(List<Patient> matches) {
		this.matches = matches;
	}
	
	
	public ListDataModel<Patient> getPatients() {
		
		List<Patient> listOfPatients = pm.getAllPatients();
		
		if(comparator!=null)  {
			Collections.sort(matches, comparator);
			Collections.sort(listOfPatients, comparator);
			System.out.println("posortowano");
		}
		else System.out.println("nie posortowano");
		
		if(!matches.isEmpty())
			patients.setWrappedData(matches);
		else 
			patients.setWrappedData(listOfPatients);
		
		return patients;
	}
	
	
	
	public void setPatients(ListDataModel<Patient> patients) {
		this.patients = patients;
	}
	
	
	
	
	
	public String sortTable(ActionEvent actionEvent) {
		
		Map<String, String> params = 
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			
		String param = params.get("sortParam");
		Sorters sorter = Sorters.valueOf(param);
		
		switch(sorter) {
			case fn:
				comparator = new CompareByFirstName();
				break;
			case ln:
				comparator = new CompareByLastName();
				break;
			case weight:
				comparator = new CompareByWeight();
				break;
			case height:
				comparator = new CompareByHeight();
				break;
			case doa:
				comparator = new CompareByDateOfAdding();
				break;
			default:
				break;
		}
		
		
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
			} catch (IOException e) {e.printStackTrace();}
		return null;
	}
	
	
	
	
	public String findMatchingRecords() {

		matches.clear();
		matches.addAll(searcher.doTheSearch(patientToFind, pm.getAllPatients()));
		if(matches.isEmpty()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();	
			FacesMessage message = new FacesMessage("Matches not found.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage("", message);
			patients.setWrappedData(matches);
		}
		
		return "showAll";
	}
	
	
	
	
	
	public void dateFormatValidator(FacesContext context, UIComponent component,
			Object value) {
		
		String date = (String) value;
		
		if(date.isEmpty()) {
			return;
		} else {
			Pattern pattern = Pattern.compile("\\d{2}-\\d{2}-\\d{2}");
			Matcher match = pattern.matcher(date);
			
			if(!match.find()) {
				FacesMessage message = new FacesMessage(
						"Date must be in format dd-MM-yyyy");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
		
	}
	
	

}
