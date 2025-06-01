package com.erp.inventory.controller;

import com.erp.inventory.dto.ProductRequestDTO;
import com.erp.inventory.dto.ProductResponseDTO;
import com.erp.inventory.service.ProductService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create-product")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @GetMapping("/get-product-by-id/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/get-all-products")
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get-product-by-name/{name}")
    public ResponseEntity<ProductResponseDTO>getProductByName(@PathVariable @NotBlank String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @PostMapping("/update-product")
    public ResponseEntity<ProductResponseDTO> UpdateProduct(@RequestBody ProductRequestDTO productRequest) {
        return ResponseEntity.ok(productService.updateProduct(productRequest));
    }

    @DeleteMapping("/delete-product/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)  // 204 No Content is standard for successful DELETE
    public void deleteProduct(@PathVariable @NotBlank String name) {
        productService.deleteProduct(name);
    }
}