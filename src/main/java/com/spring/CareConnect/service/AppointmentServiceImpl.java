package com.spring.CareConnect.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.CareConnect.entity.Appointment;
import com.spring.CareConnect.entity.CancelledAppointment;
import com.spring.CareConnect.entity.Doctor;
import com.spring.CareConnect.entity.Patient;
import com.spring.CareConnect.exception.AppointmentNotFoundException;
import com.spring.CareConnect.exception.DoctorNotFoundException;
import com.spring.CareConnect.exception.InvalidAppointmentCancellationException;
import com.spring.CareConnect.exception.PatientNotFoundException;
import com.spring.CareConnect.model.AppointmentInputModel;
import com.spring.CareConnect.model.AppointmentOutputModel;
import com.spring.CareConnect.model.CancellationOutputModel;
import com.spring.CareConnect.repository.AppointmentRepository;
import com.spring.CareConnect.repository.CancelledAppointmentRepository;
import com.spring.CareConnect.repository.DoctorRepository;
import com.spring.CareConnect.repository.PatientRepository;
import com.spring.CareConnect.util.MapAppointmentRow;
import com.spring.CareConnect.util.MapCancellationRow;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	AppointmentRepository appointmentRepository;
	@Autowired
	DoctorService doctorService;
	@Autowired
	PatientService patientService;
	@Autowired
	CancelledAppointmentRepository cancelledAppointmentRepository;
	@Autowired
	DoctorRepository doctorRepository;
	@Autowired
	PatientRepository patientRepository;

	public AppointmentOutputModel addAppointment(AppointmentInputModel appointmentInputModel)
			throws DoctorNotFoundException, PatientNotFoundException {
		Doctor doctor = doctorRepository.findById(appointmentInputModel.getDoctorId()).orElse(null);
//		Doctor doctor = doctorService.getDoctorById(appointmentInputModel.getDoctorId());
//		PatientOutputModel patient1 = patientService.getPatientById(appointmentInputModel.getPatientId());
		Patient patient = patientRepository.findById(appointmentInputModel.getPatientId()).orElse(null);

		if (patient == null)
			throw new PatientNotFoundException("No such Patient Exists");

		Optional<Integer> slots = appointmentRepository.getDoctorAvailability(appointmentInputModel.getDoctorId(),
				appointmentInputModel.getAppointmentDate());
		Integer slots1 = 0;
		if (slots.isPresent())
			slots1 = slots.get();
		if (slots1 >= 5) {
			throw new DoctorNotFoundException("Doctor is not available at this date...");
		}
		Appointment appointment = new Appointment();
		appointment.setBookingAmount(doctor.getFees());
		appointment.setAppointmentDate(appointmentInputModel.getAppointmentDate());
		appointment.setBookigStatus("Booked");
		appointment.setBookingDate(LocalDate.now());
		appointment.setDescription(appointmentInputModel.getDescription());
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment = appointmentRepository.save(appointment);
		AppointmentOutputModel appointmentOutputModel = new MapAppointmentRow().mapToAppointmentOutput(appointment);
		return appointmentOutputModel;

	}

	public CancellationOutputModel cancelAppointmentById(Integer bId)
			throws AppointmentNotFoundException, InvalidAppointmentCancellationException {
//		AppointmentOutputModel appointmentOutputModel = getAppointmentById(bId);
		Appointment appointment = appointmentRepository.findById(bId).orElse(null);
		Integer price = appointment.getBookingAmount();
		LocalDate appointmentDate = LocalDate.parse(appointment.getAppointmentDate());
		LocalDate currDate = LocalDate.now();

		if (appointment.getBookigStatus().equalsIgnoreCase("Cancelled")) {
			throw new InvalidAppointmentCancellationException("Appointment is already cancelled");
		}
		if (currDate.isAfter(appointmentDate)) {
			throw new InvalidAppointmentCancellationException("Current date surpasses appointment date");
		}

		LocalDate oneDayBefore = appointmentDate.minusDays(2);
		LocalDate sevenDaysBefore = appointmentDate.minusDays(7);

		if (currDate.isAfter(oneDayBefore)) {
			price = (int) (0.15 * price);
		} else if ((currDate.isAfter(sevenDaysBefore) && currDate.isBefore(oneDayBefore)) || currDate.isEqual(oneDayBefore)) {
			price = (int) (0.50 * price);
		}

		appointment.setBookigStatus("Cancelled");
		CancelledAppointment cancelledAppointment = new CancelledAppointment();
		cancelledAppointment.setRefundAmount(price);
		cancelledAppointment.setAppointment(appointment);
		cancelledAppointmentRepository.save(cancelledAppointment);

		appointmentRepository.save(appointment);
		CancellationOutputModel cancellationOutputModel = new MapCancellationRow().mapCancellationOutput(cancelledAppointment);
		return cancellationOutputModel;
	}

	public AppointmentOutputModel getAppointmentById(Integer bId) throws AppointmentNotFoundException {
		Appointment appointment = appointmentRepository.findById(bId).orElse(null);
		if (appointment == null) {
			throw new AppointmentNotFoundException("No such appointment available for this Id");
		}

		AppointmentOutputModel appointmentOutputModel = new MapAppointmentRow().mapToAppointmentOutput(appointment);
		return appointmentOutputModel;
	}

	public List<AppointmentOutputModel> getAppointmentByPatientId(Integer pId) throws AppointmentNotFoundException {

		List<Appointment> list = appointmentRepository.findAllByPatientPatientId(pId);
		List<AppointmentOutputModel> oList = new ArrayList<>();
		if (list.isEmpty()) {
			throw new AppointmentNotFoundException("No Such Appointment available for this Id " + pId);
		}
		for (Appointment a : list) {
			AppointmentOutputModel appointmentOutputModel = new MapAppointmentRow().mapToAppointmentOutput(a);
			oList.add(appointmentOutputModel);
		}
		return oList;
	}
	
	public Integer getSlotsOfDoctorPerDate(Integer doctorId, String appointmentDate) {
		return appointmentRepository.getDoctorAvailability(doctorId, appointmentDate).orElse(0);
	}
	
	public CancellationOutputModel getCancelDetailsByBookingId(Integer bId) {
		
		return new MapCancellationRow().mapCancellationOutput(cancelledAppointmentRepository.getCancelDetailsByBookingId(bId));
	}
}
