package com.hps.orchestraspa.transform;

import org.springframework.stereotype.Service;

import com.hps.orchestraspa.entities.*;
import com.hps.orchestraspa.model.*;

@Service
public class TransformUserServiceImpl implements TransformUserService{
    @Override
    public UserData transform(User user){
        UserData userData = new  UserData();
        userData.setUserId(user.getUserId());
        userData.setUserName(user.getUserName());
        userData.setUserEmail(user.getUserEmail());
        userData.setUserPassword(user.getUserPassword());
        userData.setUserRole(user.getUserRole());
   
        return userData;
    }

    @Override
    public User transform(UserData userData){
        User user = new  User();
        user.setUserId(userData.getUserId());
        user.setUserName(userData.getUserName());
        user.setUserEmail(userData.getUserEmail());
        user.setUserPassword(userData.getUserPassword());
        user.setUserRole(userData.getUserRole());
   
        return user;
    }
}
