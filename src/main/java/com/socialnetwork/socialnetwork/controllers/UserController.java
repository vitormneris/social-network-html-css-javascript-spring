package com.socialnetwork.socialnetwork.controllers;

import com.socialnetwork.socialnetwork.controllers.dto.UserDTO;
import com.socialnetwork.socialnetwork.controllers.mapper.UserMapper;
import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import com.socialnetwork.socialnetwork.services.TokenService;
import com.socialnetwork.socialnetwork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/profile")
    public ResponseEntity<UserEntity> findById(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        Long userId = tokenService.validateTokenClaim(token).asLong();
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @PostMapping
    public ResponseEntity<UserEntity> insert(@RequestPart(value = "user") UserDTO userDTO, @RequestPart(value = "file", required = false) MultipartFile file) {
        UserEntity userEntity = userService.insert(userMapper.userDTOToUserEntity(userDTO), file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}").buildAndExpand(userEntity.getLogin()).toUri();
        return ResponseEntity.created(uri).body(userEntity);
    }
}
