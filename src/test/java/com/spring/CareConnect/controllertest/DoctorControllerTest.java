package com.spring.CareConnect.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.spring.CareConnect.controller.DoctorController;
import com.spring.CareConnect.model.DoctorOutputModel;
import com.spring.CareConnect.service.DoctorServiceImpl;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {
	@Mock
	private DoctorControllerTest doctorControllerTest;
	@MockBean
	private DoctorServiceImpl doctorServiceImpl;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllDoctors() throws Exception {
		DoctorOutputModel doctor1 = new DoctorOutputModel();
		doctor1.setDoctorId(1);
		doctor1.setDoctorName("Dr. Ayush");
		doctor1.setFees(1000);
		doctor1.setSpecification("Ortho");
		
		DoctorOutputModel doctor2 = new DoctorOutputModel();
		doctor2.setDoctorId(2);
		doctor2.setDoctorName("Dr. Ashish");
		doctor2.setFees(1500);
		doctor2.setSpecification("Cardiologist");
		
		List<DoctorOutputModel> mockDoctor = new ArrayList<>();
		mockDoctor.add(doctor1);
		mockDoctor.add(doctor2);
		
		when(doctorServiceImpl.getAllDoctor()).thenReturn(mockDoctor);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/getalldoctor").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void getDoctorByIdTest() throws Exception {
		DoctorOutputModel doctor = new DoctorOutputModel();
		doctor.setDoctorId(1);
		doctor.setDoctorName("Dr. Ayush");
		doctor.setFees(1000);
		doctor.setSpecification("Ortho");

		when(doctorServiceImpl.getDoctorById(1)).thenReturn(doctor);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/getdoctorbyid/{doctorId}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json("{\"doctorId\":1}"));
	}

	@Test
	public void getDoctorsByAreaTest() throws Exception {
		DoctorOutputModel doctor1 = new DoctorOutputModel();
		doctor1.setDoctorId(1);
		doctor1.setDoctorName("Dr. Ayush");
		doctor1.setFees(1000);
		doctor1.setSpecification("Ortho");

		DoctorOutputModel doctor2 = new DoctorOutputModel();
		doctor2.setDoctorId(2);
		doctor2.setDoctorName("Dr. Ashish");
		doctor2.setFees(1500);
		doctor2.setSpecification("Cardiologist");

		List<DoctorOutputModel> mockDoctor = new ArrayList<>();
		mockDoctor.add(doctor1);
		mockDoctor.add(doctor2);

		when(doctorServiceImpl.getDoctorsByArea("Ortho")).thenReturn(mockDoctor);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/getdoctorsbyarea/{area}", "Ortho").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].specification").value("Ortho"));
	}

	@Test
	public void getDoctorsByNameTest() throws Exception {
		DoctorOutputModel doctor1 = new DoctorOutputModel();
		doctor1.setDoctorId(1);
		doctor1.setDoctorName("Dr. Ayush");
		doctor1.setFees(1000);
		doctor1.setSpecification("Ortho");

		DoctorOutputModel doctor2 = new DoctorOutputModel();
		doctor2.setDoctorId(2);
		doctor2.setDoctorName("Dr. Ashish");
		doctor2.setFees(1500);
		doctor2.setSpecification("Cardiologist");

		List<DoctorOutputModel> mockDoctor = new ArrayList<>();
		mockDoctor.add(doctor1);
		mockDoctor.add(doctor2);

		when(doctorServiceImpl.getDoctorsByName("Dr. Ayush")).thenReturn(mockDoctor);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/getdoctorsbyname/{name}", "Dr. Ayush").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].doctorName").value("Dr. Ayush"));
	}
}
