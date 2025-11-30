package com.assesment.sarvika;

import com.assesment.sarvika.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Product save(Product product);

    Optional<Product> getOneById(UUID id);

    Page<Product> findAll(Pageable pageable);

    Product update(Product product);

    void deleteByID(UUID id);

}
