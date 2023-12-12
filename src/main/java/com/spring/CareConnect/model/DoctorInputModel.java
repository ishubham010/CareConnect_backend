package com.spring.CareConnect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorInputModel {
	private String doctorName;
	private String specification;
	private Integer fees;
}
