package com.cloudfiles.server.service;

import com.cloudfiles.server.payload.RegistrationRequest;

public interface Base64Service {
    String encodeUserData(RegistrationRequest userData);
    RegistrationRequest decodeUserData(String encodedUserData);
}
