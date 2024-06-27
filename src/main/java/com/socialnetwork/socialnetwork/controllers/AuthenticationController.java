package com.socialnetwork.socialnetwork.controllers;

import com.socialnetwork.socialnetwork.controllers.dto.AuthenticateDTO;
import com.socialnetwork.socialnetwork.controllers.dto.response.TokenResponseDTO;
import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import com.socialnetwork.socialnetwork.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthenticateDTO data) {
        var usernamePassword =  new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok().body(new TokenResponseDTO(token));
    }
}
