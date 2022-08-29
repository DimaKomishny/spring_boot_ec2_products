package com.dmytro.komyshnyi.ec2.controller;

import com.dmytro.komyshnyi.ec2.dto.ProductDto;
import com.dmytro.komyshnyi.ec2.facade.ProductFacade;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Api("products")
public class ProductController {

    private final ProductFacade productFacade;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('read')")
    public ProductDto getProduct(@PathVariable UUID id) {
        return productFacade.findById(id);
    }

    @ApiOperation(value = "show all products")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('read')")
    public List<ProductDto> getProducts() {
        return productFacade.getAll();
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyAuthority('write')")
    public ResponseEntity<Map<String, UUID>> saveProduct(@Valid @RequestBody ProductDto product) {
        return productFacade.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('write')")
    public void deleteProduct(@PathVariable UUID id) {
        productFacade.delete(id);
    }
}
