package com.socialnetwork.socialnetwork.controllers.dto;

import org.springframework.web.multipart.MultipartFile;

public record UserDTO(String username,
                      String email,
                      String password) {
}
