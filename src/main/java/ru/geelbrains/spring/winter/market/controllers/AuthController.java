package ru.geelbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geelbrains.spring.winter.market.dtos.JwtRequest;
import ru.geelbrains.spring.winter.market.dtos.JwtResponse;
import ru.geelbrains.spring.winter.market.exceptions.AppError;
import ru.geelbrains.spring.winter.market.servicies.UserService;
import ru.geelbrains.spring.winter.market.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {

            Authentication authentication = daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(jwt));

        } catch (BadCredentialsException e) {
                  return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"),
                          HttpStatus.UNAUTHORIZED);
        }
//        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
//        String token = jwtTokenUtil.generateToken(userDetails);
//        return  ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/secured")
    public String helloSecurity() {
        return "Hello";
    }
}
