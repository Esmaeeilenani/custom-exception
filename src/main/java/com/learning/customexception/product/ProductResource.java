package com.learning.customexception.product;


import com.learning.customexception.exception.ApiException;
import com.learning.customexception.validator.InvalidateIfTrue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {
    private List<Product> products = List.of(
            new Product().setId(1L).setName("Product 1").setDescription("Description 1").setPrice(100.0),
            new Product().setId(2L).setName("Product 2").setDescription("Description 2").setPrice(200.0),
            new Product().setId(3L).setName("Product 3").setDescription("Description 3").setPrice(300.0)
    );

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = products
                .stream()
                .filter(p -> p.getId().longValue() == id.longValue())
                .findFirst()
                .orElse(null);

        product.getDescription();


        InvalidateIfTrue.invalidate(() -> product == null, new ApiException("Product not found").setStatusCode(404));

        InvalidateIfTrue.invalidate(() -> product.getId() == 1
                , new ApiException("Cant access this product").setStatusCode(HttpStatus.FORBIDDEN.value()));

        return product;
    }

    @GetMapping("/findByPrice/{price}")
    public Product getProduct(@PathVariable Double price) {
        Product product = products
                .stream()
                .filter(p -> p.getPrice().equals(price))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));


        //validate if product is less than 300 and throw exception
        InvalidateIfTrue
                .invalidate(() -> product.getPrice() < 300, "Product price is less than 300");




        return product;
    }


}
