package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.User;

public interface IUserRepository {
    public User getUserById(String userId); 
    public User insertUser(User user);
    public boolean checkUserExists(String userId);

    
}
