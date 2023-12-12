package com.spring.CareConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.CareConnect.entity.Doctor;
import com.spring.CareConnect.exception.DoctorNotFoundException;
import com.spring.CareConnect.model.DoctorInputModel;
import com.spring.CareConnect.model.DoctorOutputModel;
import com.spring.CareConnect.repository.DoctorRepository;
import com.spring.CareConnect.util.MapDoctorRow;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	DoctorRepository doctorRepository;

	public DoctorOutputModel addDoctor(DoctorInputModel doctorInputModel) {
		Doctor doctor = new Doctor();
		doctor.setDoctorName(doctorInputModel.getDoctorName());
		doctor.setSpecification(doctorInputModel.getSpecification());
		doctor.setFees(doctorInputModel.getFees());
		doctor = doctorRepository.save(doctor);
		DoctorOutputModel doctorOutputModel = new MapDoctorRow().mapToDoctorRow(doctor);
		return doctorOutputModel;
	}

	public List<DoctorOutputModel> getAllDoctor() throws DoctorNotFoundException {
		List<Doctor> doctor = doctorRepository.findAll();
		if (doctor == null) {
			throw new DoctorNotFoundException("No Such Doctor Available");
		}
		List<DoctorOutputModel> doctorOutputModels = new ArrayList<>();
		for (Doctor d : doctor) {
			doctorOutputModels.add(new MapDoctorRow().mapToDoctorRow(d));
		}
		return doctorOutputModels;
	}

	public DoctorOutputModel getDoctorById(Integer doctorId) throws DoctorNotFoundException {
		Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
		if (doctor == null) {
			throw new DoctorNotFoundException("No Such Doctor Available for that Id...");
		}
		DoctorOutputModel doctorOutputModel = new MapDoctorRow().mapToDoctorRow(doctor);
		return doctorOutputModel;
	}

	public List<DoctorOutputModel> getDoctorsByArea(String area) throws DoctorNotFoundException {

		List<DoctorOutputModel> doctors = new ArrayList<>();
		List<Doctor> dList = doctorRepository.getDoctorsByArea(area);
		if (dList.isEmpty()) {
			throw new DoctorNotFoundException("No Such Doctor Available for that Area " + area);
		}
		for (Doctor d : dList) {

			DoctorOutputModel doctorOutputModel = new MapDoctorRow().mapToDoctorRow(d);
			doctors.add(doctorOutputModel);
		}
		return doctors;
	}

	public List<DoctorOutputModel> getDoctorsByName(String name) throws DoctorNotFoundException {
		List<DoctorOutputModel> doctors = new ArrayList<>();
		List<Doctor> dList = doctorRepository.findByDoctorNameContaining(name);
		if (dList.isEmpty()) {
			throw new DoctorNotFoundException("No Such Doctor Available for this name " + name);
		}
		for (Doctor d : dList) {

			DoctorOutputModel doctorOutputModel = new MapDoctorRow().mapToDoctorRow(d);
			doctors.add(doctorOutputModel);
		}

		return doctors;
	}

}
