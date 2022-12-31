package com.learning.customexception.product;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;


}
