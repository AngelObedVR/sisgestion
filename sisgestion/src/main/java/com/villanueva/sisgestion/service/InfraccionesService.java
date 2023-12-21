package com.villanueva.sisgestion.service;

import java.util.List;
import com.villanueva.sisgestion.entity.Infracciones;

public interface InfraccionesService {
	public List<Infracciones> findAll();
	public Infracciones findById(int id);
	public Infracciones findByDni(String nombre);
	public Infracciones create(Infracciones obj);
	public Infracciones update(Infracciones obj);
	public int delete(int id);
}
