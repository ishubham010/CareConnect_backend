package com.spring.CareConnect.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.spring.CareConnect.model.ErrorResponse;

@ControllerAdvice
public class MyControllerAdvice {
	@ExceptionHandler(value=DoctorNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDoctorNotFoundException(DoctorNotFoundException d,WebRequest we){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErroMessage(d.getMessage());
		errorResponse.setErrorCode(404);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=PatientNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlePatientNotFoundException(PatientNotFoundException p,WebRequest we){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErroMessage(p.getMessage());
		errorResponse.setErrorCode(404);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=AppointmentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAppointmentNotFoundException(AppointmentNotFoundException p,WebRequest we){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErroMessage(p.getMessage());
		errorResponse.setErrorCode(404);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=InvalidAppointmentCancellationException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAppointmentCancellationException(InvalidAppointmentCancellationException p,WebRequest we){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErroMessage(p.getMessage());
		errorResponse.setErrorCode(404);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=InvalidRegistrationException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRegistrationException(InvalidRegistrationException p,WebRequest we){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErroMessage(p.getMessage());
		errorResponse.setErrorCode(404);
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
