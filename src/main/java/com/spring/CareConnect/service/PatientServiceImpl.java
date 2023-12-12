package com.spring.CareConnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.CareConnect.entity.Patient;
import com.spring.CareConnect.exception.InvalidRegistrationException;
import com.spring.CareConnect.exception.PatientNotFoundException;
import com.spring.CareConnect.model.PatientInputModel;
import com.spring.CareConnect.model.PatientOutputModel;
import com.spring.CareConnect.repository.PatientRepository;
import com.spring.CareConnect.util.MapPatientRow;
import com.spring.CareConnect.validations.PatientValidations;

@Service
public class PatientServiceImpl implements PatientService{
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	PatientValidations patientValidations;

	public PatientOutputModel addPatient(PatientInputModel patientInputModel) throws InvalidRegistrationException {

		if (!patientValidations.checkName(patientInputModel.getPatientName())) {
			throw new InvalidRegistrationException("Invalid Name");
		}
		if (!patientValidations.checkEmail(patientInputModel.getPatientEmail())) {
			throw new InvalidRegistrationException("Invalid Email Id");
		}
		if (!patientValidations.validatePassword(patientInputModel.getPassword())) {
			throw new InvalidRegistrationException("Invalid Password");
		}
		Patient patient1=patientRepository.findByEmailId(patientInputModel.getPatientEmail(), patientInputModel.getPassword());
		if(patient1!=null) {
			throw new InvalidRegistrationException("Email id already exist");
		}
		
		Patient patient = new Patient();
		patient.setPatientName(patientInputModel.getPatientName());
		patient.setPatientEmail(patientInputModel.getPatientEmail());
		patient.setPassword(patientInputModel.getPassword());
		patient.setPatientAddress(patientInputModel.getPatientAddress());
		patient.setPatientAge(patientInputModel.getPatientAge());
		patient.setPhoneNumber(patientInputModel.getPhoneNumber());
		patient = patientRepository.save(patient);
		PatientOutputModel patientOutputModel = new MapPatientRow().mapToPatientRow(patient);
		return patientOutputModel;
	}

	public PatientOutputModel loginPatient(String emailId, String password) throws InvalidRegistrationException {
		Patient patient = patientRepository.findByEmailId(emailId, password);
		if(patient!=null) {
			PatientOutputModel patientOutputModel = new MapPatientRow().mapToPatientRow(patient);
			return patientOutputModel;
		}
		else 
			throw new InvalidRegistrationException("Invalid Email Id or Password");
		
	}
	
	public PatientOutputModel getPatientById(Integer patientId) throws PatientNotFoundException {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			throw new PatientNotFoundException("No Such Patient Available for this Id");
		}
		PatientOutputModel patientOutputModel = new MapPatientRow().mapToPatientRow(patient);
		return patientOutputModel;
	}
}
