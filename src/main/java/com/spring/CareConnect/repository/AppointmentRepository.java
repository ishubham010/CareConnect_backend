package com.spring.CareConnect.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.CareConnect.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	@Query(value="select count(d) from Appointment d where d.doctor.doctorId = ?1 and d.appointmentDate=?2 and d.bookigStatus='Booked'")
	public Optional<Integer> getDoctorAvailability(Integer doctorId, String date);
    
	public List<Appointment> findAllByPatientPatientId(Integer pId);
}
