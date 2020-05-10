package com.milton.spboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milton.spboot.domain.Pedido;
import com.milton.spboot.repositories.PedidoRepository;
import com.milton.spboot.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	

	@Autowired
	private PedidoRepository repo;
	

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
