package com.example.hms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String fname;
	private String age;
	private String bloodgroup;
	private String dose;
	private String prescription;
	private String fees;
	private String urgency;

}
