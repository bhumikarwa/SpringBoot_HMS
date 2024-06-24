package com.example.hms.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hms.entities.Patient;


public interface PatientRepo extends JpaRepository<Patient, Integer> {

}
