package com.cloudfiles.server.service;

import com.cloudfiles.server.model.User;

public interface AmazonFileService {
    void save(User user, String name);
    void deleteById(Integer id);
}
