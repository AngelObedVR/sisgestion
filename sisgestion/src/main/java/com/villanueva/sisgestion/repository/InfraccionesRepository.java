package com.villanueva.sisgestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.villanueva.sisgestion.entity.Infracciones;

@Repository
public interface InfraccionesRepository extends JpaRepository<Infracciones, Integer>{
	//List<Category> findByNameContaining(String name);
	public Infracciones findByDni(String name);
}
