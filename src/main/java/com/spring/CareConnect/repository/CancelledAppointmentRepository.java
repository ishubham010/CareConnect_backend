package com.spring.CareConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.CareConnect.entity.CancelledAppointment;

public interface CancelledAppointmentRepository extends JpaRepository<CancelledAppointment, Integer>{
	
	@Query("select c from CancelledAppointment c where c.appointment.bookingId=?1")
	public CancelledAppointment getCancelDetailsByBookingId(Integer bId);

}
