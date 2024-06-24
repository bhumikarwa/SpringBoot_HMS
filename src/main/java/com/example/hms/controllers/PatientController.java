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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hms.entities.Patient;
import com.example.hms.repos.PatientRepo;

import jakarta.persistence.EntityNotFoundException;

@RestController	
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
	
	@Autowired
	private PatientRepo patientRepo;
	
	@PostMapping("/patients")
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
		return new ResponseEntity<Patient>(patientRepo.save(patient),HttpStatus.CREATED);
	}
	
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getAllPatients() {
		return new ResponseEntity<List<Patient>>(patientRepo.findAll(),HttpStatus.OK);
	}
	
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable(value = "id") Integer id) {
		Patient patient = patientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		patientRepo.delete(patient);
		Map<String,Boolean> map = new HashMap<>();
		map.put("Deleted Successfully", Boolean.TRUE);
		return new ResponseEntity<Map<String,Boolean>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Integer id){
		Patient patient = patientRepo.findById(id).orElseThrow(() ->new  EntityNotFoundException());
		return new ResponseEntity<Patient>(patient,HttpStatus.OK);
	}
	
	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patient) {
		Patient p = patientRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		if(patientRepo.existsById(p.getId())) {
			p.setFname(patient.getFname());
			p.setAge(patient.getAge());
			p.setBloodgroup(patient.getBloodgroup());
			p.setDose(patient.getDose());
			p.setPrescription(patient.getPrescription());
			p.setUrgency(patient.getUrgency());
			
			patientRepo.save(p);
		}
		return new ResponseEntity<Patient>(p ,HttpStatus.OK);
		
	}

}
