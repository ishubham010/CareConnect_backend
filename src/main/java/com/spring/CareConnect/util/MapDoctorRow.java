package com.spring.CareConnect.util;

//import java.util.List;

import com.spring.CareConnect.entity.Doctor;
import com.spring.CareConnect.model.DoctorOutputModel;

public class MapDoctorRow {
	public DoctorOutputModel mapToDoctorRow(Doctor doctor) {
		DoctorOutputModel doctorOutputModel = new DoctorOutputModel();
		doctorOutputModel.setDoctorId(doctor.getDoctorId());
		doctorOutputModel.setDoctorName(doctor.getDoctorName());
		doctorOutputModel.setFees(doctor.getFees());
		doctorOutputModel.setSpecification(doctor.getSpecification());
		return doctorOutputModel;
	}
	
}
