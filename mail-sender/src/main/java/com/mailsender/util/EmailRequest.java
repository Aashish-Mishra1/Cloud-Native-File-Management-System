package com.mailsender.util;

public record EmailRequest(
        String email,
        String link
) {}
