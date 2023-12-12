package com.spring.CareConnect.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentOutputModel {
	private Integer bookingId;
	private String patientName;
	private String description;
	private Integer bookingAmount;
	private String appointmentDate;
	private LocalDate bookingDate;
	private String bookigStatus;
	private boolean cancelStatus;
	private Integer doctorId;
	private String doctorName;
	private String specification;
	private Integer patientId;
	
}
