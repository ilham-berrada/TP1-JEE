package org.ell.customer;

import org.ell.customer.entities.Customer;
import org.ell.customer.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration) {
        restConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            customerRepository.save(new Customer(null, "ilham", "ilham.berrada.ib@gmail.com"));
            customerRepository.save(new Customer(null, "Berrada", "berrada0305@gmail.com"));
            customerRepository.save(new Customer(null, "saloua", "saloua@gmail.com"));
            customerRepository.save(new Customer(null, "elly", "Elly@gmail.com"));
            customerRepository.findAll().forEach(
                    customer -> {
                        System.out.println(customer.toString());
                    });
        };
    }
}
