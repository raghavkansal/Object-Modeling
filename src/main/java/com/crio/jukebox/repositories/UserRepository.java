package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.Map;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository {
    private final Map<String,User> userMap;
    private int autoIncrement;
    
    public UserRepository() {
        this.userMap = new HashMap<>();
        this.autoIncrement = 0;
    }

    @Override
    public User insertUser(User user) {
        autoIncrement++;
        userMap.put(autoIncrement+"",new User(autoIncrement+"",user.getName()));
        return userMap.get(autoIncrement+"");
    }

    @Override
    public User getUserById(String userId) {
        return userMap.get(userId);
    }

    @Override
    public boolean checkUserExists(String userId) {
        return userMap.containsKey(userId);
    }
    
    
}
