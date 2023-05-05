package com.sysmap.demo.services.authentication;

import com.sysmap.demo.services.security.IJwtService;
import com.sysmap.demo.services.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserService _userService;
    @Autowired
    private IJwtService _jwtService;
    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        var user = _userService.getUser(request.email);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(request.password)) {
            return null;
        }

        var token = _jwtService.generateToken(user.getId());

        var response = new AuthenticateResponse();

        response.setUserId(user.getId());
        response.setToken(token);

        return response;

    }
}
