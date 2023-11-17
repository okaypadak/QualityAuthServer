package dev.padak.backend.controller;

import dev.padak.backend.dto.user.LoginRequestDTO;
import dev.padak.backend.dto.user.LoginResponseDTO;
import dev.padak.backend.dto.user.UserDTO;
import dev.padak.backend.entity.UserEntity;
import dev.padak.backend.security.JWTAuthenticationFilter;
import dev.padak.backend.security.JWTHelper;
import dev.padak.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JWTHelper helper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    JWTAuthenticationFilter jwtAuthenticationFilter;
    

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        LoginResponseDTO response = LoginResponseDTO.builder().Token(token).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserDTO user) {

        UserEntity entity = modelMapper.map(user, UserEntity.class);

        return userService.add(entity);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
