package com.villanueva.sisgestion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.villanueva.sisgestion.entity.Infracciones;
import com.villanueva.sisgestion.repository.InfraccionesRepository;
import com.villanueva.sisgestion.service.InfraccionesService;

@Service
public class InfraccionesServiceImpl implements InfraccionesService{
	@Autowired
	private InfraccionesRepository repo;

	@Override
	@Transactional(readOnly = true)
	public List<Infracciones> findAll() {
		try {
			return repo.findAll();
		}catch(Exception e) {
			//log.error(e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Infracciones findById(int id) {
		try {
			return repo.findById(id).orElse(null);
		}catch(Exception e) {
			//log.error(e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Infracciones findByDni(String name) {
		try {
			return repo.findByDni(name);
		}catch(Exception e) {
			//log.error(e.getMessage());
			return null;
		}
	}
	//@Override
	//@Transactional(readOnly = true)
	//public List<Infracciones> findByNameContaining(String nombre) {
	//	try {
	//		return repo.findByNameContaining(nombre);
	//	} catch (Exception e) {
	//		return null;
	//	}
	//}

	@Override
	@Transactional
	public Infracciones create(Infracciones obj) {
		try {
			return repo.save(obj);
		}catch(Exception e) {
			//log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Infracciones update(Infracciones obj) {
		try {
			Infracciones cat = repo.findById(obj.getId()).orElseThrow();
			if(cat==null) {
				return null;
			}
			cat.setDni(obj.getDni());
			cat.setFecha(obj.getFecha());
			cat.setPlaca(obj.getPlaca());
			cat.setInfraccion(obj.getInfraccion());
			cat.setDescripcion(obj.getDescripcion());
			repo.save(cat);
			return repo.save(cat);
		}catch(Exception e) {
			//log.error(e.getMessage());
			return null;
		}
	}

	@Override
	public int delete(int id) {
		try {
			Infracciones categoriaDb=repo.findById(id).orElse(null);
			if(categoriaDb==null) {
				return 0;
			}else {
				repo.delete(categoriaDb);
				return 1;
			}
		}catch(Exception e) {
			//log.error(e.getMessage());
			return 0;
		}
	}
}
