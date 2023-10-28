package dev.padak.backend.controller;

import dev.padak.backend.dto.authenticationRequestDTO;
import dev.padak.backend.dto.authenticationResponseDTO;
import dev.padak.backend.dto.urunDTO;
import dev.padak.backend.entity.KullaniciEntity;
import dev.padak.backend.service.JwtService;
import dev.padak.backend.service.KullaniciService;
import dev.padak.backend.service.UrunService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/urunler")
public class UrunlerController {
 
    @Autowired
    private UrunService service;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/hepsi")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<urunDTO> getAllTheProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public urunDTO getProductById(@PathVariable int id) {
        return service.getProduct(id);
    }

}
