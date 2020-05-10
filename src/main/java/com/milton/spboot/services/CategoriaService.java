package com.milton.spboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milton.spboot.Repositories.CategoriaRepository;
import com.milton.spboot.domain.Categoria;
import com.milton.spboot.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	

	@Autowired
	private CategoriaRepository repo;
	

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
}
