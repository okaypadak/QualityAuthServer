package dev.padak.backend.controller.test;

import dev.padak.backend.dto.productDTO;
import dev.padak.backend.service.test.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urun")
public class TestController {
 
    @Autowired
    private ProductService service;


    @GetMapping("/hepsi")
    public List<productDTO> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public productDTO getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }

    @PostMapping("/ekle")
    public List<productDTO> add() {
        return service.getProducts();
    }

    @PostMapping("/cikar")
    public List<productDTO> delete() {
        return service.getProducts();
    }

}
