package dev.padak.backend.component;

import dev.padak.backend.config.UserDetailsConfig;
import dev.padak.backend.entity.KullaniciEntity;
import dev.padak.backend.repository.KullaniciRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsComponent implements UserDetailsService {

    @Autowired
    private KullaniciRepository repository;


    @Override
    public UserDetails loadUserByUsername(String kullaniciAdi) throws UsernameNotFoundException {
        Optional<KullaniciEntity> userInfo = repository.findByKullaniciAdi(kullaniciAdi);
        return userInfo.map(UserDetailsConfig::new).orElseThrow(() -> new UsernameNotFoundException("user not found " + kullaniciAdi));

    }
}
