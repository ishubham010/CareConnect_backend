package com.spring.CareConnect.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.CareConnect.controller.PatientController;
import com.spring.CareConnect.model.PatientInputModel;
import com.spring.CareConnect.model.PatientOutputModel;
import com.spring.CareConnect.repository.PatientRepository;
import com.spring.CareConnect.service.PatientServiceImpl;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {
	@Mock
	PatientController patientController;
	@MockBean
	PatientServiceImpl patientService;
	@Mock
	PatientRepository patientRepository;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void addPatientTest() throws Exception {
		PatientInputModel patientInputModel = new PatientInputModel();
		patientInputModel.setPatientName("Shubham");
		patientInputModel.setPatientEmail("shubham@gmail.com");
		patientInputModel.setPatientAge(20);
		patientInputModel.setPassword("ABCD1234");
		patientInputModel.setPatientAddress("Kolkata");
		patientInputModel.setPhoneNumber("9876543210");
		
		PatientOutputModel expectedOutput = new PatientOutputModel();
		expectedOutput.setPatientId(1);
		expectedOutput.setPatientName("Shubham");
		expectedOutput.setPatientAddress("Kolkata");
		expectedOutput.setPatientAge(20);
		expectedOutput.setPhoneNumber("9876543210");
		expectedOutput.setPatientEmail("shubham@gmail.com");
		
		when(patientService.addPatient(patientInputModel)).thenReturn(expectedOutput);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/addpatient").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(expectedOutput))).andExpect(status().isOk());
	}
	
	@Test
	public void getPatientByIdTest() throws Exception {
		PatientOutputModel patient = new PatientOutputModel();
		patient.setPatientId(1);
		patient.setPatientName("Shubham");
		patient.setPatientEmail("shubham@gmail.com");
		patient.setPatientAddress("Kolkata");
		
		when(patientService.getPatientById(1)).thenReturn(patient);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/getpatientbyid/{patientid}",1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json("{\"patientId\":1}"));
	}
	
	@Test
	public void loginPatientTest() throws JsonProcessingException, Exception {
		PatientInputModel patientInputModel = new PatientInputModel();
		patientInputModel.setPatientName("Shubham");
		patientInputModel.setPatientEmail("shubham@gmail.com");
		patientInputModel.setPatientAge(20);
		patientInputModel.setPassword("ABCD1234");
		patientInputModel.setPatientAddress("Kolkata");
		patientInputModel.setPhoneNumber("9876543210");
		
		PatientOutputModel expectedOutput = new PatientOutputModel();
		expectedOutput.setPatientId(1);
		expectedOutput.setPatientName("Shubham");
		expectedOutput.setPatientAddress("Kolkata");
		expectedOutput.setPatientAge(20);
		expectedOutput.setPhoneNumber("9876543210");
		expectedOutput.setPatientEmail("shubham@gmail.com");
		
		when(patientService.addPatient(patientInputModel)).thenReturn(expectedOutput);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/loginpatient/{emailid}/{password}", "shubham@gmail.com", "ABCD1234").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(expectedOutput))).andExpect(status().isOk());
	}
	
}
