package com.socialnetwork.socialnetwork.controllers.mapper;

import com.socialnetwork.socialnetwork.controllers.dto.PublicationDTO;
import com.socialnetwork.socialnetwork.infrastructure.entities.PublicationEntity;
import org.springframework.stereotype.Component;

@Component
public class PublicationMapper {

    public PublicationEntity publicationDTOToPublicationEntity(PublicationDTO publicationDTO) {
        return PublicationEntity.builder()
                .content(publicationDTO.content())
                .build();
    }

}
