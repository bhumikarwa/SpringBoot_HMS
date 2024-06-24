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

import com.example.hms.entities.Medicine;
import com.example.hms.entities.Medicine;
import com.example.hms.repos.MedicineRepo;

import jakarta.persistence.EntityNotFoundException;


@RestController	
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicineController {
	
	@Autowired
	private MedicineRepo medicineRepo;
	
	@PostMapping("/medicines")
	public ResponseEntity<Medicine> saveMedicine(@RequestBody Medicine medicine) {
		return  new ResponseEntity<Medicine>(medicineRepo.save(medicine),HttpStatus.CREATED);
	}
	
	@GetMapping("/medicines")
	public ResponseEntity<List<Medicine>> getAllMedicines() {
		return new ResponseEntity<List<Medicine>>(medicineRepo.findAll(),HttpStatus.OK);
	}

	@DeleteMapping	("/medicines/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteMedicine(@PathVariable(value = "id") Integer id) {
		Medicine medicine = medicineRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		medicineRepo.delete(medicine);
		Map<String,Boolean> map = new HashMap<>();
		map.put("Deleted Successfully", Boolean.TRUE);
		return new ResponseEntity<Map<String,Boolean>>(map, HttpStatus.OK);
	}
	
	@GetMapping("/medicines/{id}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable Integer id){
		Medicine medicine = medicineRepo.findById(id).orElseThrow(() ->new  EntityNotFoundException());
		return new ResponseEntity<Medicine>(medicine,HttpStatus.OK);
	}
	
	@PutMapping("/medicines/{id}")
	public ResponseEntity<Medicine> updateMedicine(@PathVariable Integer id, @RequestBody Medicine medicine) {
		Medicine m = medicineRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		if(medicineRepo.existsById(m.getId())) {
			m.setStock(medicine.getStock());
			m.setDrugname(medicine.getDrugname());
			medicineRepo.save(m);
		}
		return new ResponseEntity<Medicine>(m,HttpStatus.OK);
		
	}
	
}