package com.spring.CareConnect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.CareConnect.exception.InvalidRegistrationException;
import com.spring.CareConnect.exception.PatientNotFoundException;
import com.spring.CareConnect.model.PatientInputModel;
import com.spring.CareConnect.model.PatientOutputModel;
import com.spring.CareConnect.service.PatientService;

@RestController
@CrossOrigin("*")
public class PatientController {
	@Autowired
	private PatientService patientService;
	private Logger logger = LoggerFactory.getLogger(PatientController.class);

	@PostMapping("/addpatient")
	public ResponseEntity<PatientOutputModel> addPatient(@RequestBody PatientInputModel patientInputModel) throws InvalidRegistrationException {
		logger.info("addPatient() started");
		PatientOutputModel patient = patientService.addPatient(patientInputModel);
		logger.info("Patient={}", patient);
		logger.info("addPatient() closed");
		return new ResponseEntity<PatientOutputModel>(patient,HttpStatus.OK);
	}

	@GetMapping("/getpatientbyid/{patientid}")
	public ResponseEntity<PatientOutputModel> getPatientById(@PathVariable("patientid") Integer patientId)
			throws PatientNotFoundException {
		logger.info("getDoctorById() started");
		PatientOutputModel patient = patientService.getPatientById(patientId);
		logger.info("Patient={}", patient);
		logger.info("getDoctorById() closed");
		return new ResponseEntity<PatientOutputModel>(patient,HttpStatus.OK);
	}
	
	@GetMapping("/loginpatient/{emailid}/{password}")
	public ResponseEntity<PatientOutputModel> loginPatient(@PathVariable("emailid") String emailId, @PathVariable("password") String password) throws InvalidRegistrationException {
		logger.info("loginInfo() started");
		PatientOutputModel outputModel = patientService.loginPatient(emailId, password);
		logger.info("loginInfo={}",outputModel);
		logger.info("loginInfo() ended");
		return new ResponseEntity<PatientOutputModel>(outputModel, HttpStatus.OK);
	}
}
