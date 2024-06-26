package com.socialnetwork.socialnetwork.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class UploadImageService {

    String url = "/home/vitor/github/SocialNetwork/src/main/resources/static/images/users/";

    public String uploadImage(String username, MultipartFile file) throws IOException {

        String newName = username + extesionImage(Objects.requireNonNull(file.getOriginalFilename()));

        byte[] bytes = file.getBytes();
        Path path = Paths.get(url + newName);
        Files.write(path, bytes);

        return "../static/images/users/" + newName;
    }

    private String extesionImage(String nameImage) {
        String[] strings = nameImage.split("\\.");
        return "." + strings[strings.length - 1];
    }
}
