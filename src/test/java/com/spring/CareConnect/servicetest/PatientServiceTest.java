package com.spring.CareConnect.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.CareConnect.entity.Patient;
import com.spring.CareConnect.exception.InvalidRegistrationException;
import com.spring.CareConnect.exception.PatientNotFoundException;
import com.spring.CareConnect.model.PatientInputModel;
import com.spring.CareConnect.model.PatientOutputModel;
import com.spring.CareConnect.repository.PatientRepository;
import com.spring.CareConnect.service.PatientServiceImpl;
import com.spring.CareConnect.validations.PatientValidations;

@ExtendWith(SpringExtension.class)
public class PatientServiceTest {
	@Mock
	PatientRepository patientRepository;
	@Mock
	PatientValidations patientValidations;
	@InjectMocks
	PatientServiceImpl patientService;

	@Test
	public void addPatientTest() throws InvalidRegistrationException {
		PatientInputModel patientInputModel = new PatientInputModel();
		patientInputModel.setPatientName("Shubham");
		patientInputModel.setPatientAge(20);
		patientInputModel.setPassword("ABCD1234");
		patientInputModel.setPatientEmail("shubham@gmail.com");
		patientInputModel.setPatientAddress("Kolkata");
		patientInputModel.setPhoneNumber("9876543210");

		when(patientValidations.checkEmail(patientInputModel.getPatientEmail())).thenReturn(true);
		when(patientValidations.validatePassword(patientInputModel.getPassword())).thenReturn(true);
		when(patientValidations.checkName(patientInputModel.getPatientName())).thenReturn(true);

		Patient patient = new Patient();
		patient.setPatientId(null);
		patient.setPatientName(patientInputModel.getPatientName());
		patient.setPatientAge(patientInputModel.getPatientAge());
		patient.setPatientEmail(patientInputModel.getPatientEmail());
		patient.setPhoneNumber(patientInputModel.getPhoneNumber());
		patient.setPassword(patientInputModel.getPassword());
		patient.setPatientAddress(patientInputModel.getPatientAddress());

		when(patientRepository.save(any(Patient.class))).thenReturn(patient);

		PatientOutputModel result = patientService.addPatient(patientInputModel);
		assertEquals(patient.getPatientId(), result.getPatientId());
		assertEquals(patient.getPatientName(), result.getPatientName());
		assertEquals(patient.getPatientAge(), result.getPatientAge());
		assertEquals(patient.getPatientAddress(), result.getPatientAddress());
		assertEquals(patient.getPatientEmail(), result.getPatientEmail());
		assertEquals(patient.getPhoneNumber(), result.getPhoneNumber());
		verify(patientRepository).save(any(Patient.class));
	}

	@Test
	public void loginPatientTest() throws InvalidRegistrationException {
		PatientInputModel patientInputModel = new PatientInputModel();
		patientInputModel.setPatientName("Shubham");
		patientInputModel.setPatientAge(20);
		patientInputModel.setPassword("ABCD1234");
		patientInputModel.setPatientEmail("shubham@gmail.com");
		patientInputModel.setPatientAddress("Kolkata");
		patientInputModel.setPhoneNumber("9876543210");

		Patient patient = new Patient();
		patient.setPatientId(null);
		patient.setPatientName(patientInputModel.getPatientName());
		patient.setPatientAge(patientInputModel.getPatientAge());
		patient.setPatientEmail(patientInputModel.getPatientEmail());
		patient.setPhoneNumber(patientInputModel.getPhoneNumber());
		patient.setPassword(patientInputModel.getPassword());
		patient.setPatientAddress(patientInputModel.getPatientAddress());

		when(patientValidations.checkEmail(patientInputModel.getPatientEmail())).thenReturn(true);
		when(patientValidations.validatePassword(patientInputModel.getPassword())).thenReturn(true);

		when(patientRepository.findByEmailId("shubham@gmail.com", "ABCD1234")).thenReturn(patient);

		PatientOutputModel result = patientService.loginPatient("shubham@gmail.com", "ABCD1234");

		assertEquals(patient.getPatientId(), result.getPatientId());
		assertEquals(patient.getPatientName(), result.getPatientName());
		assertEquals(patient.getPatientAge(), result.getPatientAge());
		assertEquals(patient.getPatientAddress(), result.getPatientAddress());
		assertEquals(patient.getPatientEmail(), result.getPatientEmail());
		assertEquals(patient.getPhoneNumber(), result.getPhoneNumber());
		verify(patientRepository).findByEmailId("shubham@gmail.com", "ABCD1234");
	}
	
	@Test
    public void loginPatientTest_InvalidCredentials_ThrowsInvalidRegistrationException() {

        when(patientRepository.findByEmailId("shubham@gmail.com", "ABCD1234")).thenReturn(null);

        assertThrows(InvalidRegistrationException.class, () -> patientService.loginPatient("shubham@gmail.com", "ABCD1234"));
    }

	@Test
	public void getPatientByIdTest() throws InvalidRegistrationException, PatientNotFoundException {
		Patient patient = new Patient();
		patient.setPatientId(1);
		patient.setPatientName("Shubham");
		patient.setPatientAge(20);
		patient.setPatientEmail("shubham@gmail.com");
		patient.setPhoneNumber("9876543210");
		patient.setPassword("ABCD1234");
		patient.setPatientAddress("Kolkata");

		when(patientValidations.checkEmail(patient.getPatientEmail())).thenReturn(true);
		when(patientValidations.validatePassword(patient.getPassword())).thenReturn(true);
		when(patientValidations.checkName(patient.getPatientName())).thenReturn(true);

		when(patientRepository.findById(1)).thenReturn(Optional.of(patient));

		PatientOutputModel result = patientService.getPatientById(1);

		assertEquals(1, result.getPatientId());
		assertEquals("Shubham", result.getPatientName());
		assertEquals("shubham@gmail.com", result.getPatientEmail());
		assertEquals("Kolkata", result.getPatientAddress());
		verify(patientRepository).findById(1);
	}
	
	@Test
    public void getPatientByIdTest_NoPatientAvailable_ThrowsPatientNotFoundException() {

        when(patientRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(1));
    }
}
