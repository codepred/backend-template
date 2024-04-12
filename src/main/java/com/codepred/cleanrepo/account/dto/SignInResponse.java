package com.codepred.cleanrepo.account.dto;

import com.codepred.cleanrepo.account.value_object.UserRole;
import com.codepred.cleanrepo.enums.ResponseStatus;
public record SignInResponse (ResponseStatus code,
        String message,
        String token,
        UserRole appUserRole) {
}
