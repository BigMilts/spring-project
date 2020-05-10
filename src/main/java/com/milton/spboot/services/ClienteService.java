package com.milton.spboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milton.spboot.Repositories.ClienteRepository;
import com.milton.spboot.domain.Cliente;
import com.milton.spboot.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	

	@Autowired
	private ClienteRepository repo;
	

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}
