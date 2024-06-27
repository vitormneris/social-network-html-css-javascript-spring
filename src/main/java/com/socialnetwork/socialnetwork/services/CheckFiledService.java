package com.socialnetwork.socialnetwork.services;

import com.socialnetwork.socialnetwork.infrastructure.entities.UserEntity;
import com.socialnetwork.socialnetwork.services.exceptions.FieldBlankException;
import com.socialnetwork.socialnetwork.services.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class CheckFiledService {

    public void checkFieldUser(UserEntity userEntity) throws InvalidFormatException, FieldBlankException {
        isNull("username", userEntity.getUsername());
        if (!userEntity.getUsername().matches("^[a-zA-Z0-9 ]+$"))
            throw new InvalidFormatException("username", userEntity.getUsername());

        isNull("e-mail", userEntity.getLogin());
        if (!userEntity.getLogin().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
            throw new InvalidFormatException("e-mail", userEntity.getLogin());

        isNull("password", userEntity.getPassword());
        if (userEntity.getPassword().length() < 8)
            throw new InvalidFormatException("Format not is valid. Password");
    }

    public void checkFieldImage(MultipartFile file) throws InvalidFormatException, FieldBlankException {
        isNull(file);

        String[] strings = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String ext = strings[strings.length - 1];

        if (!(ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("webp")))
            throw new InvalidFormatException("Extension file doesn't valid. " + ext);
    }

    private void isNull(String field, String value) throws FieldBlankException {
        if (value == null || value.isBlank())
            throw new FieldBlankException("The field cannot be blank. " + field);
    }

    private void isNull(MultipartFile value) throws FieldBlankException {
        if (value == null || value.isEmpty())
            throw new FieldBlankException("The field cannot be blank. Image");
    }
}
