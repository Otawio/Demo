package com.sysmap.demo.services.user;

import com.sysmap.demo.entities.User;

// O pacote services é responsável pelo C(controler) do MVC
public interface IUserService {
    String createUser(CreateUserRequest request);
    FindUserResponse findUserByEmail(String email);
    User getUser(String email);
}
