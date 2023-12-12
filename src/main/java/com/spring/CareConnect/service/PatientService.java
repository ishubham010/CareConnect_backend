package com.spring.CareConnect.service;

import org.springframework.stereotype.Service;

import com.spring.CareConnect.exception.InvalidRegistrationException;
import com.spring.CareConnect.exception.PatientNotFoundException;
import com.spring.CareConnect.model.PatientInputModel;
import com.spring.CareConnect.model.PatientOutputModel;

@Service
public interface PatientService {

	public PatientOutputModel addPatient(PatientInputModel patientInputModel) throws InvalidRegistrationException;

	public PatientOutputModel getPatientById(Integer patientId) throws PatientNotFoundException;
	
	public PatientOutputModel loginPatient(String emailId, String password) throws InvalidRegistrationException;
}
