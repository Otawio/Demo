package com.sysmap.demo.services.authentication;

public interface IAuthenticationService {
    AuthenticateResponse authenticate(AuthenticateRequest request);
}
