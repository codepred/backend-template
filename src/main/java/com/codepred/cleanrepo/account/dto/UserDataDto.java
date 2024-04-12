package com.codepred.cleanrepo.account.dto;

import com.codepred.cleanrepo.account.value_object.UserRole;

public record UserDataDto(UserRole appUserRole, String email) {
}
