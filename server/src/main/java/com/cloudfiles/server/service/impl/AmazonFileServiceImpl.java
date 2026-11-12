package com.cloudfiles.server.service.impl;

import com.cloudfiles.server.model.AmazonFile;
import com.cloudfiles.server.model.User;
import com.cloudfiles.server.repository.AmazonFileRepository;
import com.cloudfiles.server.service.AmazonFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AmazonFileServiceImpl implements AmazonFileService {

    private final AmazonFileRepository fileRepository;

    public void save(User user, String name) {
        fileRepository.save(AmazonFile.builder()
                .name(name)
                .user(user)
                .build());
    }

    public void deleteById(Integer id) {
        fileRepository.deleteById(id);
    }
}
