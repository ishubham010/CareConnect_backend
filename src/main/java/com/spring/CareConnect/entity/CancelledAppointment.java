package com.spring.CareConnect.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class CancelledAppointment {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer cancelId;
		private Integer refundAmount;
		@OneToOne
		@JoinColumn(name="appointmentId")
		private Appointment appointment;
	

}
