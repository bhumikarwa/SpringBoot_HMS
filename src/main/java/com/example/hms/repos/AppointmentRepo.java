package com.example.hms.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hms.entities.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {

}
