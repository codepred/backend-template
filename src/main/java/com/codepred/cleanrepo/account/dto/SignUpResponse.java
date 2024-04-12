package com.codepred.cleanrepo.account.dto;

import com.codepred.cleanrepo.enums.ResponseStatus;

public record SignUpResponse (ResponseStatus code,
        String message,
        String token) {
}
