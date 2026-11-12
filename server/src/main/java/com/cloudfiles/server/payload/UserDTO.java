package com.cloudfiles.server.payload;

import com.cloudfiles.server.model.User;

public record UserDTO(
        User user,
        TokenResponse tokens
) {}
