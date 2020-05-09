package com.milton.spboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.milton.spboot.Repositories.CategoriaRepository;
import com.milton.spboot.domain.Categoria;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriarep;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriarep.saveAll(Arrays.asList(cat1, cat2));
	}

}
