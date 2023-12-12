package com.spring.CareConnect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancellationOutputModel {

	private AppointmentOutputModel appointmentOutputModel;
	private Integer cancellationId;
	private Integer refundAmount;

}
