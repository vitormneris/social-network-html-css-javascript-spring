package com.socialnetwork.socialnetwork.controllers;

import com.socialnetwork.socialnetwork.controllers.dto.UserDTO;
import com.socialnetwork.socialnetwork.controllers.mapper.UserMapper;
import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
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

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserEntity> insert(@RequestPart(value = "user") UserDTO userDTO, @RequestPart(value = "file", required = false) MultipartFile file) {
        UserEntity userEntity = userService.insert(userMapper.userDTOToUserEntity(userDTO), file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}").buildAndExpand(userEntity.getLogin()).toUri();
        return ResponseEntity.created(uri).body(userEntity);
    }
}
