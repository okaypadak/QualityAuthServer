package dev.padak.backend.controller;

import dev.padak.backend.dto.productDTO;
import dev.padak.backend.service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
 
    @Autowired
    private ProductService service;


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('YONETICI')")
    public List<productDTO> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('KULLANICI')")
    public productDTO getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }

}
