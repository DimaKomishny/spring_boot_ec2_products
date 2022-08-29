package com.dmytro.komyshnyi.ec2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private UUID id;
    @NotNull
    @Size(min = 3, max = 255)
    private String name;
    private String description;
    @DecimalMin(value = "0.0", inclusive = false, message = "Price should NOT be less than 0.1")
    @Digits(integer = 10, fraction = 2)
    @NotNull
    private BigDecimal price;
    @NotNull
    private ProducerDto producer;
}
