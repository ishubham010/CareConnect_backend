
package com.spring.CareConnect.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	private String description;
	private Integer bookingAmount;
	private String appointmentDate;
	private LocalDate bookingDate;
	private String bookigStatus;
	
	@ManyToOne
	@JoinColumn (name="pId")
	Patient patient;
	@ManyToOne
	@JoinColumn (name="dId")
	Doctor doctor;
}
