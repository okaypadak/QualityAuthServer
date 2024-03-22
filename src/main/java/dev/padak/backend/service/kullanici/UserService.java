package dev.padak.backend.service.kullanici;

import dev.padak.backend.entity.kullanici.UserEntity;
import dev.padak.backend.repository.kullanici.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserEntity userEntity){
        userRepository.save(userEntity);
    }
    public UserEntity findByUsername(String username){
        return userRepository.findByKullaniciAdi(username).orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı"));
    }
}
