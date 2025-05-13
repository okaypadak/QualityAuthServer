package dev.padak.backend.service.kullanici;

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
        addRoleIfNotExists("USER");
        addRoleIfNotExists("ADMIN");
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
