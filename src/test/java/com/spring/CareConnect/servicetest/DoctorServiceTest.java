package com.spring.CareConnect.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.CareConnect.entity.Doctor;
import com.spring.CareConnect.exception.DoctorNotFoundException;
import com.spring.CareConnect.model.DoctorInputModel;
import com.spring.CareConnect.model.DoctorOutputModel;
import com.spring.CareConnect.repository.DoctorRepository;
import com.spring.CareConnect.service.DoctorServiceImpl;

@ExtendWith(SpringExtension.class)
public class DoctorServiceTest {
	@InjectMocks
	DoctorServiceImpl doctorServiceImpl;
	@Mock
	DoctorRepository doctorRepository;

	// AddDoctorTest
	@Test
	public void addDoctorTest() {
		DoctorInputModel doctorInputModel = new DoctorInputModel();
		doctorInputModel.setDoctorName(" Dr. Ayush");
		doctorInputModel.setFees(1000);
		doctorInputModel.setSpecification("Ortho");

		Doctor doctor = new Doctor();
		doctor.setDoctorId(null);
		doctor.setDoctorName(doctorInputModel.getDoctorName());
		doctor.setFees(doctorInputModel.getFees());
		doctor.setSpecification(doctorInputModel.getSpecification());

		when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

		DoctorOutputModel result = doctorServiceImpl.addDoctor(doctorInputModel);
		assertEquals(doctor.getDoctorId(), result.getDoctorId());
		assertEquals(doctor.getDoctorName(), result.getDoctorName());
		assertEquals(doctor.getFees(), result.getFees());
		assertEquals(doctor.getSpecification(), result.getSpecification());
		verify(doctorRepository).save(any(Doctor.class));

	}

	// GetAllDoctorsTest
	@Test
	public void getAllDoctorsTest() throws DoctorNotFoundException {
		Doctor doctor1 = new Doctor();
		doctor1.setDoctorId(1);
		doctor1.setDoctorName("Dr. Ayush");
		doctor1.setFees(1000);
		doctor1.setSpecification("Ortho");

		Doctor doctor2 = new Doctor();
		doctor2.setDoctorId(2);
		doctor2.setDoctorName("Dr. Ashish");
		doctor2.setFees(1500);
		doctor2.setSpecification("Cardiologist");

		List<Doctor> mockDoctor = new ArrayList<>();
		mockDoctor.add(doctor1);
		mockDoctor.add(doctor2);

		when(doctorRepository.findAll()).thenReturn(mockDoctor);

		List<DoctorOutputModel> result = doctorServiceImpl.getAllDoctor();
	}

	@Test
    public void getAllDoctorsTest_NoDoctorsAvailable_ThrowsDoctorNotFoundException() {
        when(doctorRepository.findAll()).thenReturn(null);
        
        assertThrows(DoctorNotFoundException.class, () -> doctorServiceImpl.getAllDoctor());
    }

	// GetDoctorByIdTest
	@Test
	public void getDoctorByIdTest() throws DoctorNotFoundException {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(1);
		doctor.setDoctorName("Dr. Ayush");
		doctor.setFees(1000);
		doctor.setSpecification("Ortho");

		when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));

		DoctorOutputModel result = doctorServiceImpl.getDoctorById(1);

		assertEquals(1, result.getDoctorId());
		assertEquals("Dr. Ayush", result.getDoctorName());
		assertEquals(1000, result.getFees());
		assertEquals("Ortho", result.getSpecification());
		verify(doctorRepository).findById(1);
	}

	@Test
    public void getDoctorByIdTest_NoDoctorsAvailable_ThrowsDoctorNotFoundException() {
        when(doctorRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThrows(DoctorNotFoundException.class, () -> doctorServiceImpl.getDoctorById(1));
    }

	// GetDoctorsByAreaTest
	@Test
	public void getDoctorsByAreaTest() throws DoctorNotFoundException {
		Doctor doctor1 = new Doctor();
		doctor1.setDoctorId(1);
		doctor1.setDoctorName("Dr. Ayush");
		doctor1.setFees(1000);
		doctor1.setSpecification("Ortho");

		Doctor doctor2 = new Doctor();
		doctor2.setDoctorId(2);
		doctor2.setDoctorName("Dr. Ashish");
		doctor2.setFees(1500);
		doctor2.setSpecification("Cardiologist");

		List<Doctor> mockDoctor = new ArrayList<>();
		mockDoctor.add(doctor1);
		mockDoctor.add(doctor2);

		when(doctorRepository.getDoctorsByArea("Ortho")).thenReturn(mockDoctor);

		List<DoctorOutputModel> result = doctorServiceImpl.getDoctorsByArea("Ortho");
	}

	@Test
	public void getDoctorsByAreaTest_NoDoctorsAvailable_ThrowsDoctorNotFoundException() {
		String area = "Nonexistent Area";

		when(doctorRepository.getDoctorsByArea(area)).thenReturn(new ArrayList<>());

		assertThrows(DoctorNotFoundException.class, () -> doctorServiceImpl.getDoctorsByArea(area));
	}

	// GetDoctorsByNameTest
	@Test
	public void getDoctorsByNameTest() throws DoctorNotFoundException {
		Doctor doctor1 = new Doctor();
		doctor1.setDoctorId(1);
		doctor1.setDoctorName("Dr. Ayush");
		doctor1.setFees(1000);
		doctor1.setSpecification("Ortho");

		Doctor doctor2 = new Doctor();
		doctor2.setDoctorId(2);
		doctor2.setDoctorName("Dr. Ashish");
		doctor2.setFees(1500);
		doctor2.setSpecification("Cardiologist");

		List<Doctor> mockDoctor = new ArrayList<>();
		mockDoctor.add(doctor1);
		mockDoctor.add(doctor2);

		when(doctorRepository.findByDoctorNameContaining("Dr. Ayush")).thenReturn(mockDoctor);

		List<DoctorOutputModel> result = doctorServiceImpl.getDoctorsByName("Dr. Ayush");
	}

	@Test
	public void getDoctorsByNameTest_NoDoctorsAvailable_ThrowsDoctorNotFoundException() {
		String name = "Nonexistent Doctor";

		when(doctorRepository.findByDoctorNameContaining(name)).thenReturn(new ArrayList<>());

		assertThrows(DoctorNotFoundException.class, () -> doctorServiceImpl.getDoctorsByName(name));
	}
}
