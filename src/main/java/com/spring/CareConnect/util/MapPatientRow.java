package com.spring.CareConnect.util;

import com.spring.CareConnect.entity.Patient;
import com.spring.CareConnect.model.PatientOutputModel;

public class MapPatientRow {
	public PatientOutputModel mapToPatientRow(Patient patient) {
		PatientOutputModel patientOutputModel = new PatientOutputModel();
		patientOutputModel.setPatientId(patient.getPatientId());
		patientOutputModel.setPatientName(patient.getPatientName());
		patientOutputModel.setPatientEmail(patient.getPatientEmail());
		patientOutputModel.setPatientAge(patient.getPatientAge());
		patientOutputModel.setPhoneNumber(patient.getPhoneNumber());
		patientOutputModel.setPatientAddress(patient.getPatientAddress());
		return patientOutputModel;
	}
}
