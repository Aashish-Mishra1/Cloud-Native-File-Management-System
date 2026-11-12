package com.cloudfiles.server.payload;

import com.cloudfiles.server.utils.Gender;

public record RegistrationRequest(
        String email,
        String name,
        String surname,
        String password,
        Integer age,
        Gender gender
) {}
