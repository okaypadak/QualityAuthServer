package dev.padak.backend.service;

import dev.padak.backend.repository.UserRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRolService {

    private final UserRolRepository userRolRepository;


}
