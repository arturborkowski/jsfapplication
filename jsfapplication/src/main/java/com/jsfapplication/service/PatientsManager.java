package com.jsfapplication.service;


import java.util.List;


import javax.ejb.Stateless;
/*import javax.enterprise.context.ApplicationScoped;*/
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jsfapplication.domain.Patient;


@Stateless	
public class PatientsManager {
	
	@PersistenceContext
	EntityManager em;
	
	public void addPatient(Patient patient) {
		patient.setId(null);
		em.persist(patient);
	}
	
	public void deletePerson(Patient patient) {
		patient = em.find(Patient.class, patient.getId());
		em.remove(patient);
	}
	
	@SuppressWarnings("unchecked")
	public List<Patient> getAllPatients() {
		return em.createNamedQuery("patient.all").getResultList();
	}
	
}

