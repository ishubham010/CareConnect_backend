package com.spring.CareConnect.controller;

import java.util.List;

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

import com.spring.CareConnect.exception.DoctorNotFoundException;
import com.spring.CareConnect.model.DoctorInputModel;
import com.spring.CareConnect.model.DoctorOutputModel;
import com.spring.CareConnect.service.DoctorService;

@RestController
@CrossOrigin("*")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	private Logger logger = LoggerFactory.getLogger(DoctorController.class);

//	@PostMapping("/adddoctor")
//	public DoctorOutputModel addDoctor(@RequestBody DoctorInputModel doctorInputModel) {
//		logger.info("addDoctor() started");
//		DoctorOutputModel doctor = doctorService.addDoctor(doctorInputModel);
//		logger.info("Doctor={}", doctor);
//		logger.info("addDoctor() closed");
//		return doctor;
//	}

	@GetMapping("/getalldoctor")
	public ResponseEntity<List<DoctorOutputModel>> getAllDoctor() throws DoctorNotFoundException {
		logger.info("getAllDoctor() started");
		List<DoctorOutputModel> doctor = doctorService.getAllDoctor();
		logger.info("Doctor={}", doctor);
		logger.info("getAllDoctor() closed");
		return new ResponseEntity<List<DoctorOutputModel>>(doctor, HttpStatus.OK);
	}

	@GetMapping("/getdoctorbyid/{doctorId}")
	public ResponseEntity<DoctorOutputModel> getDoctorById(@PathVariable("doctorId") Integer doctorId)
			throws DoctorNotFoundException {

		logger.info("getDoctorById() started");
		DoctorOutputModel doctor = doctorService.getDoctorById(doctorId);
		logger.info("Doctor={}", doctor);
		logger.info("getDoctorById() closed");
		return new ResponseEntity<DoctorOutputModel>(doctor, HttpStatus.OK);
	}

	@GetMapping("/getdoctorsbyarea/{area}")
	public ResponseEntity<List<DoctorOutputModel>> getDoctorsByArea(@PathVariable("area") String area)
			throws DoctorNotFoundException {
		logger.info("getDoctorsByArea() started");
		List<DoctorOutputModel> doctors = doctorService.getDoctorsByArea(area);
		logger.info("Doctor={}", doctors);
		logger.info("getDoctorsByArea() closed");
		return new ResponseEntity<List<DoctorOutputModel>>(doctors, HttpStatus.OK);
	}

	@GetMapping("/getdoctorsbyname/{name}")
	public ResponseEntity<List<DoctorOutputModel>> getDoctorsByName(@PathVariable("name") String name)
			throws DoctorNotFoundException {
		logger.info("getDoctorsByArea() started");
		List<DoctorOutputModel> doctors = doctorService.getDoctorsByName(name);
		logger.info("Doctor={}", doctors);
		logger.info("getDoctorsByArea() closed");
		return new ResponseEntity<List<DoctorOutputModel>>(doctors, HttpStatus.OK);
	}

}
