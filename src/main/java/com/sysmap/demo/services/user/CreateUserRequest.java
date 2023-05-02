package com.sysmap.demo.services.user;
// O pacote services é responsável pelo C(controler) do MVC
import lombok.Data;

@Data
public class CreateUserRequest {
    public String name;
    public String email;
    public String password;

}
