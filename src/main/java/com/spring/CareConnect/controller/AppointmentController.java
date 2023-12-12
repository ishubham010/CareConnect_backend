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
import com.spring.CareConnect.exception.AppointmentNotFoundException;
import com.spring.CareConnect.exception.DoctorNotFoundException;
import com.spring.CareConnect.exception.InvalidAppointmentCancellationException;
import com.spring.CareConnect.exception.PatientNotFoundException;
import com.spring.CareConnect.model.AppointmentInputModel;
import com.spring.CareConnect.model.AppointmentOutputModel;
import com.spring.CareConnect.model.CancellationOutputModel;
import com.spring.CareConnect.service.AppointmentService;

@RestController
@CrossOrigin("*")
public class AppointmentController {
	private Logger logger=LoggerFactory.getLogger(AppointmentController.class);
	@Autowired
	AppointmentService appointmentService;
	
	@PostMapping("/addappointment")
	public ResponseEntity<AppointmentOutputModel> addAppointment (@RequestBody AppointmentInputModel appointmentInputModel) throws DoctorNotFoundException, PatientNotFoundException {
		logger.info("addAppointment() started");
		AppointmentOutputModel appointment = appointmentService.addAppointment(appointmentInputModel);
		logger.info("Appointment={}",appointment);
		logger.info("addAppointment() closed");
		return new ResponseEntity<AppointmentOutputModel>(appointment,HttpStatus.OK);
	}
	
	@PostMapping("/cancelappointmentbyid/{bid}")
	public ResponseEntity<CancellationOutputModel> cancelAppointmentById(@PathVariable("bid") Integer bId) throws AppointmentNotFoundException, InvalidAppointmentCancellationException {
		logger.info("cancelAppointmentById() started");
		CancellationOutputModel cancelled = appointmentService.cancelAppointmentById(bId);
		logger.info("Cancelled={}",cancelled);
		logger.info("cancelAppointmentById() closed");
		return new ResponseEntity<CancellationOutputModel>(cancelled,HttpStatus.OK);
	}
	
	@GetMapping("/getappointmentbyid/{bid}")
	public ResponseEntity<AppointmentOutputModel> getAppointmentById(@PathVariable("bid") Integer bId) throws AppointmentNotFoundException {
		logger.info("getAppointmentById() started");
	    AppointmentOutputModel appbyid = appointmentService.getAppointmentById(bId);
		logger.info("Doctor={}",appbyid);
		logger.info("getDoctorById() closed");
		return new ResponseEntity<AppointmentOutputModel>(appbyid,HttpStatus.OK);
	}
	
	@GetMapping("/getappointmentbypatientid/{pid}")
	public ResponseEntity<List<AppointmentOutputModel>> getAppointmentByPatientId(@PathVariable("pid") Integer pId) throws AppointmentNotFoundException {
		logger.info("getAppointmentByPatientId() started");
		List<AppointmentOutputModel> appbyids = appointmentService.getAppointmentByPatientId(pId);
		logger.info("Doctor={}",appbyids);
		logger.info("getAppointmentByPatientId() closed");
		return new ResponseEntity<List<AppointmentOutputModel>>(appbyids,HttpStatus.OK);
	}
	
	@GetMapping("/getslotsOfDoctorPerDate/{did}/{appdate}")
	public ResponseEntity<Integer> getSlotsOfDoctorPerDate(@PathVariable("did") Integer dId, @PathVariable("appdate") String appDate){
		logger.info("getAppointmentByPatientId() started");
		Integer slots = appointmentService.getSlotsOfDoctorPerDate(dId, appDate);
		logger.info("Doctor={}", slots);
		logger.info("getAppointmentByPatientId() closed");
		return new ResponseEntity<Integer>(slots,HttpStatus.OK);
	}
	
	@GetMapping("/getcanceldetailsbybookingid/{bid}")
	public ResponseEntity<CancellationOutputModel> getCancelDetailsByBookingId(@PathVariable("bid") Integer bId) {
		logger.info("getCancelDetailsByBookingId() started");
		CancellationOutputModel cancelled = appointmentService.getCancelDetailsByBookingId(bId);
		logger.info("Cancelled={}",cancelled);
		logger.info("getCancelDetailsByBookingId() closed");
		return new ResponseEntity<CancellationOutputModel>(cancelled, HttpStatus.OK);
	}
}
