package com.cloudfiles.server.service;

import com.cloudfiles.server.model.User;
import com.cloudfiles.server.payload.RegistrationRequest;
import com.cloudfiles.server.payload.UserDTO;

import java.util.List;

public interface UserService {
    void sendMail(RegistrationRequest request);
    User getUser(Integer id);
    UserDTO save(String data);
    void deleteById(Integer id);
    List<User> findAll();
}
