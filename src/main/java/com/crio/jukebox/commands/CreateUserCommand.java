package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.UserService;

public class CreateUserCommand implements ICommand{

    private IUserService userService;

    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String name = tokens.get(1);
        User user = userService.addNewUser(name);
        System.out.println(user.getId()+" "+user.getName());
    }
    
}
