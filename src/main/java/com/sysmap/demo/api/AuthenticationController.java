package com.sysmap.demo.api;

import com.sysmap.demo.services.authentication.AuthenticateResponse;
import com.sysmap.demo.services.authentication.AuthenticateRequest;
import com.sysmap.demo.services.authentication.IAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService _authenticationService;

    @PostMapping()
    public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request) {
        return ResponseEntity.ok().body(_authenticationService.authenticate(request));
    }
}
