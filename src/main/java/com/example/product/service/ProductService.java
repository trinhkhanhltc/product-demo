package com.example.product.service;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.entity.Product;
import com.example.product.exception.ResourceNotFoundException;
import com.example.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll()
        .stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
  }

  public ProductResponse getById(Long id) {
    return toResponse(
        productRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found")));
  }

  public ProductResponse create(ProductRequest request) {
    var product = Product.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .quantity(request.getQuantity())
        .build();
    return toResponse(productRepository.save(product));
  }

  public ProductResponse update(Long id, ProductRequest request) {
    var product =
        productRepository
            .findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found"));
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());
    product.setQuantity(request.getQuantity());
    return toResponse(productRepository.save(product));
  }

  public void delete(Long id) {
    if (!productRepository.existsById(id)) {
      throw new ResourceNotFoundException("Product with id " + id + " not found");
    }

    productRepository.deleteById(id);
  }

  private ProductResponse toResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .quantity(product.getQuantity())
        .build();
  }
}
