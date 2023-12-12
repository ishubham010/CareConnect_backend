package com.spring.CareConnect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.CareConnect.exception.DoctorNotFoundException;
import com.spring.CareConnect.model.DoctorInputModel;
import com.spring.CareConnect.model.DoctorOutputModel;

@Service
public interface DoctorService {
	

	public DoctorOutputModel addDoctor(DoctorInputModel doctorInputModel);

	public List<DoctorOutputModel> getAllDoctor() throws DoctorNotFoundException;
	
	public DoctorOutputModel getDoctorById(Integer doctorId) throws DoctorNotFoundException;

	public List<DoctorOutputModel> getDoctorsByArea(String area) throws DoctorNotFoundException;

	public List<DoctorOutputModel> getDoctorsByName(String name) throws DoctorNotFoundException;

}
