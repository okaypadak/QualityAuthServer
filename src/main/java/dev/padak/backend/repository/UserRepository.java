package dev.padak.backend.repository;

import dev.padak.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByKullaniciAdi(String username);

    Optional<UserEntity> findByEposta(String eposta);

    boolean existsByKullaniciAdi(String kullaniciAdi);

    boolean existsByEposta(String eposta);
}
