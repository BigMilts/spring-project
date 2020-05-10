package com.milton.spboot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.milton.spboot.domain.Categoria;
import com.milton.spboot.domain.Cidade;
import com.milton.spboot.domain.Cliente;
import com.milton.spboot.domain.Endereco;
import com.milton.spboot.domain.Estado;
import com.milton.spboot.domain.ItemPedido;
import com.milton.spboot.domain.Pagamento;
import com.milton.spboot.domain.PagamentoComBoleto;
import com.milton.spboot.domain.PagamentoComCartao;
import com.milton.spboot.domain.Pedido;
import com.milton.spboot.domain.Produto;
import com.milton.spboot.domain.enums.EstadoPagamento;
import com.milton.spboot.domain.enums.TipoCliente;
import com.milton.spboot.repositories.CategoriaRepository;
import com.milton.spboot.repositories.CidadeRepositroy;
import com.milton.spboot.repositories.ClienteRepository;
import com.milton.spboot.repositories.EnderecoRepository;
import com.milton.spboot.repositories.EstadoRepository;
import com.milton.spboot.repositories.ItemPedidoRepository;
import com.milton.spboot.repositories.PagamentoRepository;
import com.milton.spboot.repositories.PedidoRepository;
import com.milton.spboot.repositories.ProdutoRepository;

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
	@Autowired
	private PedidoRepository pedidoRep;
	@Autowired
	private PagamentoRepository pagamentoRep;
	@Autowired
	private ItemPedidoRepository itemPedidoRep;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRep.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRep.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00, 1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3, 0.00,2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().add(ip3);
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRep.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}
}
