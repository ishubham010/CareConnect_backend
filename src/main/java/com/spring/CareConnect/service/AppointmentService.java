package com.spring.CareConnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.CareConnect.exception.AppointmentNotFoundException;
import com.spring.CareConnect.exception.DoctorNotFoundException;
import com.spring.CareConnect.exception.InvalidAppointmentCancellationException;
import com.spring.CareConnect.exception.PatientNotFoundException;
import com.spring.CareConnect.model.AppointmentInputModel;
import com.spring.CareConnect.model.AppointmentOutputModel;
import com.spring.CareConnect.model.CancellationOutputModel;

@Service
public interface AppointmentService {

	public AppointmentOutputModel addAppointment(AppointmentInputModel appointmentInputModel)
			throws DoctorNotFoundException, PatientNotFoundException;
	
	public CancellationOutputModel cancelAppointmentById(Integer bId)
			throws AppointmentNotFoundException, InvalidAppointmentCancellationException;

	public AppointmentOutputModel getAppointmentById(Integer bId) throws AppointmentNotFoundException;

	public List<AppointmentOutputModel> getAppointmentByPatientId(Integer pId) throws AppointmentNotFoundException;
	
	public Integer getSlotsOfDoctorPerDate(Integer doctorId, String appointmentDate);
	
	public CancellationOutputModel getCancelDetailsByBookingId(Integer bId);
}