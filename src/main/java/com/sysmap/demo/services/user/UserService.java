package com.sysmap.demo.services.user;
// O pacote services é responsável pelo C(controler) do MVC
import com.sysmap.demo.data.IUserRepository;
import com.sysmap.demo.entities.User;
import com.sysmap.demo.services.fileUpload.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository _I_userRepository;
    @Autowired
    private PasswordEncoder _passwordEncoder;
    @Autowired
    private IFileUploadService _fileUploadService;


    public String createUser(CreateUserRequest request) {
        var user = new User(request.name, request.email);

        if (!_I_userRepository.findUserByEmail(request.email).isEmpty()) {
            return null;
            //throw new Exception("Usuário já existe");
        }

        var hash = _passwordEncoder.encode(request.password);

        user.setPassword(hash);

        _I_userRepository.save(user);

        //_eventService.send(user);

        return user.getId().toString();
    }
    public FindUserResponse findUserByEmail(String email) {
       var user = _I_userRepository.findUserByEmail(email).get();

       var response = new FindUserResponse(user.getId(), user.getName(), user.getEmail(), user.getPhotoUri());

       return response;
    }

    public User getUser(String email) {
        return _I_userRepository.findUserByEmail(email).get();
    }

    public User getUserById(UUID id) { return _I_userRepository.findUserById(id).get(); }

    public void uploadPhotoProfile(MultipartFile photo) throws Exception {
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        var photoUri = "";

        try {
            var fileName = user.getId() + "." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);

            photoUri = _fileUploadService.upload(photo, fileName);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        user.setPhotoUri(photoUri);
        _I_userRepository.save(user);

    }
}
