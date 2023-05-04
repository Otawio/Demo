package com.sysmap.demo.services.user;
// O pacote services é responsável pelo C(controler) do MVC
import com.sysmap.demo.data.UserRepository;
import com.sysmap.demo.entities.User;
import com.sysmap.demo.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository _userRepository;

    private IEventService _eventService;

    public String createUser(CreateUserRequest request) {
        var user = new User(request.name, request.email, request.password);

        _userRepository.save(user);

        _eventService.send(user.getId().toString());

        return user.getId().toString();
    }
    public FindUserResponse findUserByEmail(String email) {
       var user = _userRepository.findUserByEmail(email).get();

       var response = new FindUserResponse(user.getId(), user.getName(), user.getEmail());

       return response;
    }
}
