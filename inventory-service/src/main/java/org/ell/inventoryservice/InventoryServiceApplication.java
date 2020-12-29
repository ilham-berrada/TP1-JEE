package org.ell.inventoryservice;
import org.ell.inventoryservice.entities.Product;
import org.ell.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
@SpringBootApplication
public class InventoryServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository,
							RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Product.class);
		return args -> {
			productRepository.save(new Product(null,"Ordinateur",5000,3,null));
			productRepository.save(new Product(null,"Smartphone",3000,50,null));
			productRepository.save(new Product(null,"imprimente",8500,10,null));
			productRepository.findAll().forEach(product -> {
				System.out.println(product.toString());
			});

		};
	}
}
