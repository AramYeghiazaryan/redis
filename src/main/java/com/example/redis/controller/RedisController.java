package com.example.redis.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.entity.Product;
import com.example.redis.repository.ProductRepository;

import lombok.AllArgsConstructor;

@RequestMapping("/redis")
@RestController
@AllArgsConstructor
public class RedisController {

    ProductRepository productRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product get(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @DeleteMapping
    public Long delete(@RequestParam Long id) {
        return productRepository.deleteById(id);
    }

}

