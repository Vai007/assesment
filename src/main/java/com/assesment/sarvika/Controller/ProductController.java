package com.assesment.sarvika.Controller;

import com.assesment.sarvika.Entity.Product;
import com.assesment.sarvika.ProductService;
import com.assesment.sarvika.error.InvalidRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    @Operation(summary = "Get all products", description = "Fetch paginated and sorted list of products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List loaded successfully")
    })
    public ResponseEntity<Page<Product>> getAllProducts(
            @Parameter(description = "Page number (0-indexed)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of items per page")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Sort field")
            @RequestParam(defaultValue = "id") String sortBy){

        log.info("Rest request for findAll product");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> productsPage = productService.findAll(pageable);
        return ResponseEntity.ok(productsPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Fetch product using its UUID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "400", description = "Invalid UUID format")
    })
    public ResponseEntity<Product> getProductById(@PathVariable UUID id){
        log.info("Rest request for product by Id: "+id);
        Optional<Product> optional = productService.getOneById(id);
        return ResponseEntity.of(optional);
    }

    @PostMapping("")
    @Operation(summary = "Create a new product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Invalid product data")
    })
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product){
        log.info("Rest request for save product");
        if(product.getId() != null){
            throw new InvalidRequest("Id should not available in body");
        }
        product = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "400", description = "Invalid data or ID mismatch"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Product> updateProduct(
            @PathVariable UUID id,
            @RequestBody @Valid Product product){
        log.info("Rest request for update product");
        if(product.getId() == null){
            throw new InvalidRequest("Id is not available in body");
        }
        if(!product.getId().equals(id)){
            throw new InvalidRequest("Id mismatch");
        }
        product = productService.update(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid product id")
    })
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id){
        productService.deleteByID(id);
        return ResponseEntity.ok("Deleted");
    }

}
