package com.spring.CareConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.CareConnect.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	@Query("select d from Doctor d where d.specification=?1")
	List<Doctor> getDoctorsByArea(String area);

	List<Doctor> findByDoctorNameContaining(String name);

}
