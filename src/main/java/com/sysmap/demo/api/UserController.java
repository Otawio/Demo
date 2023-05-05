package com.sysmap.demo.api;
//pacote api é o view do mvc
import com.sysmap.demo.services.user.CreateUserRequest;
import com.sysmap.demo.services.user.FindUserResponse;
import com.sysmap.demo.services.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private IUserService _userService;

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request) {
        if (_jwtService.isValidtoken(getToken(), getUserId()));
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not authenticate");

        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // pode ser criada classe de validação

    @GetMapping
    public ResponseEntity<FindUserResponse> getUser(String email) {
        return ResponseEntity.ok().body(_userService.findUserByEmail(email));
    }

    public String getToken() {
        var jwt = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        return jwt.substring(7);
    }
    public String getUserId() {

    }
}