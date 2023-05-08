package com.sysmap.demo.services.user;

import com.sysmap.demo.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

// O pacote services é responsável pelo C(controler) do MVC
public interface IUserService {
    String createUser(CreateUserRequest request);
    FindUserResponse findUserByEmail(String email);
    User getUser(String email);
    User getUserById(UUID uuid);
    void uploadPhotoProfile(MultipartFile photo) throws Exception;
}
