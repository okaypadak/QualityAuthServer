package dev.padak.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.padak.backend.entity.KullaniciEntity;
import dev.padak.backend.repository.KullaniciRepository;

@Service
public class KullaniciService {

    @Autowired
    private KullaniciRepository kullaniciRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String ekle(KullaniciEntity userInfo) {
        userInfo.setSifre(passwordEncoder.encode(userInfo.getSifre()));
        kullaniciRepository.save(userInfo);
        return "user added to system ";
    }
    
    public Optional<KullaniciEntity> kullaniciBilgileri(String kullaniciAdi) {
    	
    	Optional<KullaniciEntity> kullanici = kullaniciRepository.findByKullaniciAdi(kullaniciAdi);

    	return kullanici;
    	
    	
    }
}
