package dev.padak.backend.repository;

import dev.padak.backend.entity.KullaniciEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

public interface KullaniciRepository extends JpaRepository<KullaniciEntity, Integer> {
    
	Optional<KullaniciEntity> findByKullaniciAdi(String kullaniciAdi);
    
}
