package com.crio.jukebox.services;

import com.crio.jukebox.entities.User;

public interface IUserService {
    //CREATE-USER { Name }
    public User addNewUser(String name);
}
