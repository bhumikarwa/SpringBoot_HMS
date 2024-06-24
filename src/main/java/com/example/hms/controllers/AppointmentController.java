package com.example.hms.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hms.entities.Appointment;
import com.example.hms.entities.Appointment;
import com.example.hms.entities.Appointment;
import com.example.hms.repos.AppointmentRepo;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
	
	@Autowired
	private AppointmentRepo appointmentRepo;
	
	@PostMapping("/appointments")
	public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
		return  new ResponseEntity<Appointment>(appointmentRepo.save(appointment),HttpStatus.CREATED);
	}
	
	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		return new ResponseEntity<List<Appointment>>(appointmentRepo.findAll(),HttpStatus.OK);
	}
	
	@DeleteMapping("/appointments/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteAppointment(@PathVariable(value="id") Integer id) {
		Appointment appt = appointmentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		appointmentRepo.delete(appt);
		Map<String,Boolean> map = new HashMap<>();
		map.put("Deleted Successfully", Boolean.TRUE);
		return new ResponseEntity<Map<String,Boolean>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable Integer id){
		Appointment appointment = appointmentRepo.findById(id).orElseThrow(() ->new  EntityNotFoundException());
		return new ResponseEntity<Appointment>(appointment,HttpStatus.OK);
	}
	
	@PutMapping("/appointments/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointment) {
		Appointment a = appointmentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		if(appointmentRepo.existsById(a.getId())) {
			a.setName(appointment.getName());
			a.setAge(appointment.getAge());
			a.setNumber(appointment.getNumber());
			a.setSymptoms(appointment.getSymptoms());
			appointmentRepo.save(a);
		}
		return new ResponseEntity<Appointment>(a,HttpStatus.OK);
		
	}
	
	
}