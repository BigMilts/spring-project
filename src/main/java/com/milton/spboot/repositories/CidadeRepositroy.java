package com.milton.spboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.milton.spboot.domain.Cidade;

@Repository
public interface CidadeRepositroy extends JpaRepository<Cidade, Integer> {

}