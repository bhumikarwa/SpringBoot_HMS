package com.example.hms.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hms.entities.Medicine;

public interface MedicineRepo extends JpaRepository<Medicine, Integer> {

}
