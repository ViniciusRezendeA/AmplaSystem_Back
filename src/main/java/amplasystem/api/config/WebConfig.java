package amplasystem.api.config;

// ! import java.util.ArrayList; | Não utilizado

// ! import org.springframework.beans.factory.annotation.Autowired; | Não utilizado
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// ! import amplasystem.api.models.Cliente; | Não utilizado
// ! import amplasystem.api.models.Vendedor; | Não utilizado\

// ? Trocar isso
// ! import amplasystem.api.repositories.ClienteRepository; | Não utilizado
// ! import amplasystem.api.repositories.FinanceiroRepository; | Não utilizado
// ! import amplasystem.api.repositories.IndustriaRepository; | Não utilizado
// ! import amplasystem.api.repositories.OrdemDeCompraRepository; | Não utilizado
// ! import amplasystem.api.repositories.PedidoFaturadoRepository; | Não utilizado
// ! import amplasystem.api.repositories.VendedorRepository; | Não utilizado
// ? Por isso
// : import amplasystem.api.repositories.*;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebMvc
@Component
public class WebConfig implements WebMvcConfigurer, CommandLineRunner {

    /*
    > Não utilizado 
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FinanceiroRepository financeiroRepository;

    @Autowired
    private IndustriaRepository industriaRepository;

    @Autowired
    private OrdemDeCompraRepository ordemDeCompraRepository;

    @Autowired
    private PedidoFaturadoRepository pedidoFaturadoRepository;

    @Autowired
    private VendedorRepository vendedorRepository;
    */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
    }
    @Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
    @Override
    public void run(String... args) {
        //Vendedor v1 = new Vendedor(null, "vendedor1@gmail.com", "senha", "Pedro Henrique", "Gerente", new ArrayList<>());
        //Cliente c1 = new Cliente(null, "60270975000161", "31988888888", "Belo Horizonte", "Rua dos bobos n0", "Empresa 1", v1, new ArrayList<>());
    }
}