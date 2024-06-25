package com.socialnetwork.socialnetwork.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadImageService {

    public String uploadImage(MultipartFile file) throws InternalError {
        String url = "/home/vitor/github/SocialNetwork/src/main/resources/static/images/users/";

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(url + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            throw new InternalError("Unable to save image");
        }

        return "/images/users/" + file.getOriginalFilename();
    }
}
