package br.com.rolimvaldeci.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rolimvaldeci.cursomc.domain.Categoria;
import br.com.rolimvaldeci.cursomc.domain.Cidade;
import br.com.rolimvaldeci.cursomc.domain.Cliente;
import br.com.rolimvaldeci.cursomc.domain.Endereco;
import br.com.rolimvaldeci.cursomc.domain.Estado;
import br.com.rolimvaldeci.cursomc.domain.Pagamento;
import br.com.rolimvaldeci.cursomc.domain.PagamentoComBoleto;
import br.com.rolimvaldeci.cursomc.domain.PagamentoComCartao;
import br.com.rolimvaldeci.cursomc.domain.Pedido;
import br.com.rolimvaldeci.cursomc.domain.Produto;
import br.com.rolimvaldeci.cursomc.domain.enums.EstadoPagamento;
import br.com.rolimvaldeci.cursomc.domain.enums.TipoCliente;
import br.com.rolimvaldeci.cursomc.repositories.CategoriaRepository;
import br.com.rolimvaldeci.cursomc.repositories.CidadeRepository;
import br.com.rolimvaldeci.cursomc.repositories.ClienteRepository;
import br.com.rolimvaldeci.cursomc.repositories.EnderecoRepository;
import br.com.rolimvaldeci.cursomc.repositories.EstadoRepository;
import br.com.rolimvaldeci.cursomc.repositories.PagamentoRepository;
import br.com.rolimvaldeci.cursomc.repositories.PedidoRepository;
import br.com.rolimvaldeci.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository catRepo;
	
	@Autowired
	private ProdutoRepository proRepo;
	
	@Autowired
	private CidadeRepository cidRepo;
	
	@Autowired
	private EstadoRepository estRepo;
	
	@Autowired
	private ClienteRepository cliRepo;
	
	@Autowired
	private EnderecoRepository endRepo;
	
	@Autowired
	private PagamentoRepository pagRepo;
	
	@Autowired
	private PedidoRepository pedRepo;
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Apulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		catRepo.saveAll(Arrays.asList(cat1, cat2));
		proRepo.saveAll(Arrays.asList(p1, p2, p3));
		estRepo.saveAll(Arrays.asList(est1, est2));
		cidRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("85956565656", "85989898989"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "21254478", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 803", "Centro", "23254472", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		cliRepo.saveAll(Arrays.asList(cli1));
		endRepo.saveAll(Arrays.asList(e1,e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedRepo.saveAll(Arrays.asList(ped1, ped2));
		pagRepo.saveAll(Arrays.asList(pagto1, pagto2));
		
	}

}











