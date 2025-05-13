package dev.padak.backend.service.kullanici;

import dev.padak.backend.entity.UserEntity;
import dev.padak.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByKullaniciAdi(username).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı"));
    }

    public boolean existsByKullaniciAdi(String kullaniciAdi) {
        return userRepository.findByKullaniciAdi(kullaniciAdi).isPresent();
    }

    public boolean existsByEposta(String eposta) {
        return userRepository.findByEposta(eposta).isPresent();
    }
}
