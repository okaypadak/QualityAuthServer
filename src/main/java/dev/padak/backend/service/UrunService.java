package dev.padak.backend.service;

import dev.padak.backend.dto.urunDTO;
import dev.padak.backend.entity.KullaniciEntity;
import dev.padak.backend.repository.KullaniciRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UrunService {

    List<urunDTO> urunList = null;

    @PostConstruct
    public void loadProductsFromDB() {
    	urunList = IntStream.rangeClosed(1, 100).mapToObj(i -> urunDTO.builder().id(i)
                        .aciklama("Ürün " + i)
                        .adet(new Random().nextInt(10))
                        .fiyat(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());
    }


    public List<urunDTO> getProducts() {
        return urunList;
    }

    public urunDTO getProduct(int id) {
        return urunList.stream()
                .filter(tek -> tek.getId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("id'li ürün " + id + " bulunamadı"));
    }


}
