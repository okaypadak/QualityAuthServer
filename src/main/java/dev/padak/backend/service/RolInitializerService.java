package dev.padak.backend.service;

import dev.padak.backend.entity.RolEntity;
import dev.padak.backend.repository.RolRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolInitializerService {

    private final RolRepository rolRepository;

    @PostConstruct
    public void initRoles() {
        addRoleIfNotExists("YONETICI");
        addRoleIfNotExists("KULLANICI");
    }

    private void addRoleIfNotExists(String roleName) {
        rolRepository.findByRol(roleName).ifPresentOrElse(
                rol -> {
                    // already exists, log at debug level if needed
                },
                () -> {
                    RolEntity rol = new RolEntity();
                    rol.setRol(roleName);
                    rolRepository.save(rol);
                }
        );
    }
}
