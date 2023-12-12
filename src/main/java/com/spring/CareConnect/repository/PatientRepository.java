package com.spring.CareConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.CareConnect.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	@Query("select p from Patient p where p.patientEmail=?1 and p.password=?2")
	public Patient findByEmailId(String emailId, String password);
}
