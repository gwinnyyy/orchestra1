package com.hps.orchestraspa.service;

import com.hps.orchestraspa.model.User;

public interface UserService {
    User[] getAll() throws Exception;
    User get(Integer userId) throws Exception;
    User create(User user) throws Exception;
    User update(User user) throws Exception;
    void delete(Integer userId) throws Exception;
    User login(String email, String password) throws Exception;
}
