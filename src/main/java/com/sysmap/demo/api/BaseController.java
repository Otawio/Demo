package com.sysmap.demo.api;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sysmap.demo.services.security.IJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    private IJwtService _jwtService;
    public BaseController() {
        _jwtService.isValidToken();
    }
}
