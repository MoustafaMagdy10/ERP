package com.erp.inventory.service;


import com.erp.inventory.dto.ProductRequestDTO;
import com.erp.inventory.dto.ProductResponseDTO;
import com.erp.inventory.exception.ProductNotFound;
import com.erp.inventory.model.Product;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.erp.inventory.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productReq){

        Product product = modelMapper.map(productReq, Product.class);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return modelMapper.map(productRepository.save(product),ProductResponseDTO.class);

    }

    public ProductResponseDTO getProductById(Long id) {
        return modelMapper.map(productRepository.findById(id).orElseThrow(()->new ProductNotFound(("Product Not Found"))),ProductResponseDTO.class);
    }

    public List<ProductResponseDTO> getAllProducts() {
      return productRepository.findAll().stream().map(product->modelMapper.map(product,ProductResponseDTO.class)).collect(Collectors.toList());
    }

    public ProductResponseDTO getProductByName(String name) {
        return modelMapper.map(productRepository.findByName(name).orElseThrow(()-> new ProductNotFound("Product Not Found")),ProductResponseDTO.class);
    }

    @Transactional
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequest) {
        Product product = productRepository.findByName(productRequest.getName()).orElseThrow(()->new ProductNotFound("Product Not Found"));

        product.setUpdatedAt(LocalDateTime.now());
        return modelMapper.map(productRepository.save(product),ProductResponseDTO.class);
    }


    public void deleteProduct(String name) {
        productRepository.delete(productRepository.findByName(name).orElseThrow(()-> new ProductNotFound("Product Not Found")));

    }
}
