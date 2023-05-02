package com.sysmap.demo.services.user;
// O pacote services é responsável pelo C(controler) do MVC
public interface IUserService {
    String createUser(CreateUserRequest request);
    FindUserResponse findUserByEmail(String email);
}
