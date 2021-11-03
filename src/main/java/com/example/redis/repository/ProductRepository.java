package com.example.redis.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.redis.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProductRepository {

    public static final String HASH_KEY = "Product";
    private RedisTemplate<String, Object> redisTemplate;

    public Product save(Product product) {
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return redisTemplate.opsForHash()
                .values(HASH_KEY)
                .stream().map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return mapToProduct(redisTemplate.opsForHash().get(HASH_KEY, id));
    }

    public Long deleteById(Long... id) {
        return redisTemplate.opsForHash().delete(HASH_KEY, Arrays.stream(id).toArray());
    }

    private Product mapToProduct(Object o) {
        return new ObjectMapper().convertValue(o, Product.class);
    }
}
