package com.hps.orchestraspa.service;

import com.hps.orchestraspa.entities.UserData;
import com.hps.orchestraspa.model.User;
import com.hps.orchestraspa.repository.UserDataRepository;
import com.hps.orchestraspa.transform.TransformUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private TransformUserService transformUserService;

    @Override
    public User[] getAll() throws Exception {
        List<User> userModels = new ArrayList<>();
        userDataRepository.findAll().forEach(data -> {
            userModels.add(transformUserService.transform(data));
        });
        return userModels.toArray(new User[0]);
    }

    @Override
    public User get(Integer userId) throws Exception {
        return userDataRepository.findById(userId)
                .map(transformUserService::transform)
                .orElseThrow(() -> new Exception("User not found with ID: " + userId));
    }

    @Override
    public User create(User user) throws Exception {
        UserData data = transformUserService.transform(user);
        data = userDataRepository.save(data);
        return transformUserService.transform(data);
    }

    @Override
    public User update(User user) throws Exception {
        UserData existing = userDataRepository.findById(user.getUserId())
                .orElseThrow(() -> new Exception("User not found"));
        
        existing.setUserName(user.getUserName());
        existing.setUserEmail(user.getUserEmail());
        existing.setUserPassword(user.getUserPassword());
        existing.setUserRole(user.getUserRole());
        
        return transformUserService.transform(userDataRepository.save(existing));
    }

    @Override
    public void delete(Integer userId) throws Exception {
        if (!userDataRepository.existsById(userId)) {
            throw new Exception("Cannot delete. User not found.");
        }
        userDataRepository.deleteById(userId);
    }

    @Override
    public User login(String email, String password) throws Exception {
    // 1. We need a custom query in the Repository (see Step 3)
    UserData userData = userDataRepository.findByUserEmail(email)
            .orElseThrow(() -> new Exception("Invalid Email or Password"));

    // 2. Check if the password matches
    if (!userData.getUserPassword().equals(password)) {
        throw new Exception("Invalid Email or Password");
    }

    // 3. Return the transformed User model
    return transformUserService.transform(userData);
}
}