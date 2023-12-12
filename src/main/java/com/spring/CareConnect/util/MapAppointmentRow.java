package com.spring.CareConnect.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import com.spring.CareConnect.entity.Appointment;
import com.spring.CareConnect.model.AppointmentOutputModel;

public class MapAppointmentRow {
	public AppointmentOutputModel mapToAppointmentOutput(Appointment appointment) {
		AppointmentOutputModel appointmentOutputModel = new AppointmentOutputModel();
		appointmentOutputModel.setAppointmentDate(appointment.getAppointmentDate());
		appointmentOutputModel.setBookingId(appointment.getBookingId());
		appointmentOutputModel.setBookigStatus(appointment.getBookigStatus());
		appointmentOutputModel.setBookingAmount(appointment.getBookingAmount());
		appointmentOutputModel.setDescription(appointment.getDescription());
		appointmentOutputModel.setBookingDate(appointment.getBookingDate());
		appointmentOutputModel.setPatientName(appointment.getPatient().getPatientName());
		appointmentOutputModel.setDoctorId(appointment.getDoctor().getDoctorId());
		appointmentOutputModel.setDoctorName(appointment.getDoctor().getDoctorName());
		appointmentOutputModel.setSpecification(appointment.getDoctor().getSpecification());
		appointmentOutputModel.setPatientId(appointment.getPatient().getPatientId());
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(appointment.getAppointmentDate(), format);
		if(LocalDate.now().isAfter(date)) {
			appointmentOutputModel.setCancelStatus(false);
		}
		else {
			appointmentOutputModel.setCancelStatus(true);
		}
		
		return appointmentOutputModel;
	}
}
