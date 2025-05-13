package dev.padak.backend.service;

import dev.padak.backend.entity.RolEntity;
import dev.padak.backend.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    public Optional<RolEntity> gonder(String ad) {
        return rolRepository.findByRol(ad);
    }

    
}
