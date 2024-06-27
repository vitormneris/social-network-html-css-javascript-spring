package com.socialnetwork.socialnetwork.controllers;

import com.socialnetwork.socialnetwork.controllers.dto.PublicationDTO;
import com.socialnetwork.socialnetwork.controllers.mapper.PublicationMapper;
import com.socialnetwork.socialnetwork.infrastructure.entities.PublicationEntity;
import com.socialnetwork.socialnetwork.services.PublicationService;
import com.socialnetwork.socialnetwork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/publications")
@RequiredArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;

    @GetMapping
    public ResponseEntity<List<PublicationEntity>> findAll() {
        return ResponseEntity.ok().body(publicationService.findAll());
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity<PublicationEntity> insert(@RequestBody PublicationDTO publicationDTO, @PathVariable Long userId) {
        PublicationEntity publicationEntity = publicationService.insert(publicationMapper.publicationDTOToPublicationEntity(publicationDTO), userId);
        return ResponseEntity.status(201).body(publicationEntity);
    }
}
