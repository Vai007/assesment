package com.assesment.sarvika.ServiceImpl;

import com.assesment.sarvika.Entity.Product;
import com.assesment.sarvika.ProductService;
import com.assesment.sarvika.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> getOneById(UUID id) {
        log.info("Request for product by Id: "+id);
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        log.info("Request for findAll product");
        return productRepository.findAll(pageable);
    }

    @Override
    public Product update(Product product) {
        log.info("Request for update product_id: "+product.getId());
        log.debug("Product payload: "+product.toString());
        return productRepository.save(product);
    }

    @Override
    public void deleteByID(UUID id) {
        log.info("Request to delete product_id: "+id);
    }
}
