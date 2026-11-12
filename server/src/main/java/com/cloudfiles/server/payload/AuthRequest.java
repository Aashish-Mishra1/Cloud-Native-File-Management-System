package com.cloudfiles.server.payload;

public record AuthRequest(
        String email,
        String password
) {}
