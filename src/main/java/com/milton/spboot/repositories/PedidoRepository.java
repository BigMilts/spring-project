package com.milton.spboot.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milton.spboot.domain.Cliente;
import com.milton.spboot.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
	@Transactional
	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
}