package dev.padak.backend.service.kullanici;

import dev.padak.backend.repository.kullanici.UserRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRolService {

    private final UserRolRepository userRolRepository;


}
