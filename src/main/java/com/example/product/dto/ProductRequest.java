package com.example.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
  @NotBlank(message = "Tên không được để trống")
  private String name;

  @NotBlank(message = "Mô tả không được để trống")
  private String description;

  @NotNull(message = "Giá cả không được để trống")
  @Positive(message = "Giá phải lớn hơn 0")
  private Double price;

  @NotNull(message = "Số lượng không được để trống")
  @Min(value = 0, message = "Số lương không được âm")
  private Integer quantity;
}
