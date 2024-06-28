package com.socialnetwork.socialnetwork.controllers;

import com.socialnetwork.socialnetwork.controllers.dto.PublicationDTO;
import com.socialnetwork.socialnetwork.controllers.mapper.PublicationMapper;
import com.socialnetwork.socialnetwork.infrastructure.entities.PublicationEntity;
import com.socialnetwork.socialnetwork.services.PublicationService;
import com.socialnetwork.socialnetwork.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/publications")
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;
    private final TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<PublicationEntity>> findAll() {
        return ResponseEntity.ok().body(publicationService.findAll());
    }

    @PostMapping
    public ResponseEntity<PublicationEntity> insert(@RequestBody PublicationDTO publicationDTO, @RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");
        Long userId = tokenService.validateTokenClaim(token).asLong();
        PublicationEntity publicationEntity = publicationService.insert(publicationMapper.publicationDTOToPublicationEntity(publicationDTO), userId);
        return ResponseEntity.status(201).body(publicationEntity);
    }
}
