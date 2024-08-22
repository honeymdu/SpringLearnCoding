package com.example.SpringLearnH2db.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringLearnH2db.Entitys.ProductEntity;
import com.example.SpringLearnH2db.Repositories.ProductRepository;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts(){

      //  return productRepository.findByTitleOrderByPrice("parle_Biscuit");
        return productRepository.findByOrderByPrice();

    }

}
