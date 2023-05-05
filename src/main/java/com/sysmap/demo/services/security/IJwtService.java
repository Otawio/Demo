package com.sysmap.demo.services.security;

import java.util.UUID;

public interface IJwtService {
    String generateToken(UUID userId);
}
