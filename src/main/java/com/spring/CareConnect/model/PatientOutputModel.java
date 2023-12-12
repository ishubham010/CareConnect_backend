package com.spring.CareConnect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientOutputModel {
	private Integer patientId;
	private String patientName;
	private String patientEmail;
	private Integer patientAge;
	private String phoneNumber;
	private String patientAddress;
}
