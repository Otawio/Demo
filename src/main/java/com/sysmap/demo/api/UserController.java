package com.sysmap.demo.api;
//pacote api é o view do mvc
import com.sysmap.demo.services.security.IJwtService;
import com.sysmap.demo.services.user.CreateUserRequest;
import com.sysmap.demo.services.user.FindUserResponse;
import com.sysmap.demo.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private IUserService _userService;

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    // pode ser criada classe de validação

    @GetMapping
    public ResponseEntity<FindUserResponse> getUser(String email) {
        return ResponseEntity.ok().body(_userService.findUserByEmail(email));
    }

    @PostMapping("/photo/upload")
    public ResponseEntity uploadPhotoProfile(@RequestParam("photo") MultipartFile photo) {
        try {
            _userService.uploadPhotoProfile(photo);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}