package com.sysmap.demo.services.authentication;

import lombok.Data;

@Data
public class AuthenticateResponse {
    public UUID userId;
    public String token;
}
