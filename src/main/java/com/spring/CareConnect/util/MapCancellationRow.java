package com.spring.CareConnect.util;

import com.spring.CareConnect.entity.CancelledAppointment;
import com.spring.CareConnect.model.CancellationOutputModel;

public class MapCancellationRow {
	public CancellationOutputModel mapCancellationOutput(CancelledAppointment cancelledAppointment) {
		CancellationOutputModel cancellationOutputModel = new CancellationOutputModel();
		cancellationOutputModel.setAppointmentOutputModel(new MapAppointmentRow().mapToAppointmentOutput(cancelledAppointment.getAppointment()));
		cancellationOutputModel.setCancellationId(cancelledAppointment.getCancelId());
		cancellationOutputModel.setRefundAmount(cancelledAppointment.getRefundAmount());
		return cancellationOutputModel;
	}
}
