package dev.padak.backend.controller;

import dev.padak.backend.dto.authenticationRequestDTO;
import dev.padak.backend.dto.authenticationResponseDTO;
import dev.padak.backend.dto.urunDTO;
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
import dev.padak.backend.entity.KullaniciEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kullanici")
public class KullaniciController {
    
    @Autowired
    private KullaniciService kullaniciService;
    
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private ModelMapper modelMapper;
    
    

    @GetMapping("/hosgeldin")
    public String welcome() {
        return "Hoşgeldiniz";
    }

    @PostMapping("/yeni")
    public String addNewUser(@RequestBody authenticationRequestDTO authRequest) {
    	
    
    	KullaniciEntity entity = modelMapper.map(authRequest, KullaniciEntity.class);
    	
        return kullaniciService.ekle(entity);
    }

    @PostMapping("/giris")
    public authenticationResponseDTO authenticateAndGetToken(@RequestBody authenticationRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getKullaniciAdi(), request.getSifre()));
        if (authentication.isAuthenticated()) {
        	
        	authenticationResponseDTO response = new authenticationResponseDTO();
        	response.setToken(jwtService.generateToken(request.getKullaniciAdi()));
        	
      
            return response;
            
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }
}
