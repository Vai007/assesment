package com.assesment.sarvika.Controller;

import com.assesment.sarvika.Entity.Product;
import com.assesment.sarvika.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@Transactional
@Slf4j
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy){

        log.info("Rest request for findAll product");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> productsPage = productService.findAll(pageable);
        return ResponseEntity.ok(productsPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@RequestParam()UUID id){
        log.info("Rest request for product by Id: "+id);
        Optional<Product> optional = productService.getOneById(id);
        return ResponseEntity.of(optional);
    }

    @PostMapping("")
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product){
        log.info("Rest request for save product");
        product = productService.save(product);
        return ResponseEntity.ok(product);
    }

}
