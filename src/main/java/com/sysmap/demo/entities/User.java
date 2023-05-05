package com.sysmap.demo.entities;
// Os pacotes DATA e o ENTITIES farão o M(model) do MVC
import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String photoUri;

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    protected void setId(){
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }
}
