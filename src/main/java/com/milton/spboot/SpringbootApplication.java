package com.milton.spboot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.milton.spboot.Repositories.CategoriaRepository;
import com.milton.spboot.Repositories.CidadeRepositroy;
import com.milton.spboot.Repositories.ClienteRepository;
import com.milton.spboot.Repositories.EnderecoRepository;
import com.milton.spboot.Repositories.EstadoRepository;
import com.milton.spboot.Repositories.ProdutoRepository;
import com.milton.spboot.domain.Categoria;
import com.milton.spboot.domain.Cidade;
import com.milton.spboot.domain.Cliente;
import com.milton.spboot.domain.Endereco;
import com.milton.spboot.domain.Estado;
import com.milton.spboot.domain.Produto;
import com.milton.spboot.domain.enums.TipoCliente;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRep;
	@Autowired
	private ProdutoRepository produtoRep;
	@Autowired
	private EstadoRepository estadoRep;
	@Autowired
	private CidadeRepositroy cidadeRep;
	@Autowired
	private ClienteRepository clienteRep;
	@Autowired
	private EnderecoRepository enderecoRep;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);
		
		categoriaRep.saveAll(Arrays.asList(cat1, cat2));
		produtoRep.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRep.saveAll(Arrays.asList(est1,est2));
		cidadeRep.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","3637383377",TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("2766323","93838393"));
		
		Endereco e1 = new Endereco(null,"Rua flores","300", "Apto 203", "Jardim","3822643",cli1, c1);
		Endereco e2 = new Endereco(null, "Avaenida Mato", "105", "sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRep.save(cli1);
		enderecoRep.saveAll(Arrays.asList(e1,e2));

	}

}
