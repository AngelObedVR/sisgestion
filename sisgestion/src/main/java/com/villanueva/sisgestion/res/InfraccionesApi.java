package com.villanueva.sisgestion.res;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.villanueva.sisgestion.entity.Infracciones;
import com.villanueva.sisgestion.service.InfraccionesService;

@RestController
@RequestMapping("/api/infraccions")
public class InfraccionesApi {
	@Autowired
	private InfraccionesService service;
	
	@GetMapping()
	public ResponseEntity<List<Infracciones>> getAll(){
		List<Infracciones> infraccion= service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(infraccion);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Infracciones> getById(@PathVariable("id") int id) {
		Infracciones infraccion = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(infraccion);
	}
	
	@PostMapping
	public ResponseEntity<Infracciones> create(@RequestBody Infracciones infraccion) {
		Infracciones infraccionDb=service.create(infraccion);
		return ResponseEntity.status(HttpStatus.CREATED).body(infraccionDb);
	}
	
	@PutMapping
	public ResponseEntity<Infracciones> update(@RequestBody Infracciones infraccion) {
		System.out.println(infraccion.getId());
		Infracciones infraccionDb=service.update(infraccion);
		return ResponseEntity.status(HttpStatus.OK).body(infraccionDb);
	}
	
	

	
	@DeleteMapping(value="/{id}")
	public int delete(@PathVariable("id") int id){
		return service.delete(id);
	}
}
