package com.spring.CareConnect.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentInputModel {
	private String description;
	private String appointmentDate;
	private Integer patientId;
	private Integer doctorId;
}
