package org.ell.billingservice;
import org.ell.billingservice.entities.Bill;
import org.ell.billingservice.entities.ProductItem;
import org.ell.billingservice.feign.CustomerRestClient;
import org.ell.billingservice.feign.ProductItemRestClient;
import org.ell.billingservice.model.Customer;
import org.ell.billingservice.model.Product;
import org.ell.billingservice.repositories.BillRepository;
import org.ell.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;
import java.util.Date;
import java.util.Random;
@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient customerRestClient,
            ProductItemRestClient productItemRestClient
    ){ return args -> {
            Customer customer=customerRestClient.getCustomerById(1L);
            Bill bill=billRepository.save(new Bill(null,new Date(),null,
                    customer.getId(),null));
            PagedModel<Product> productPagedModel=productItemRestClient.pageProduct();
            productPagedModel.forEach(p -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(p.getPrice());
                productItem.setQuantity(1+new Random().nextInt(100));
                productItem.setBill(bill);
                productItem.setProductID(p.getId());
                productItemRepository.save(productItem);
            });


        };
    }

}
