package com.codepred.cleanrepo.account.dto;

import com.codepred.cleanrepo.account.value_object.UserRole;
import com.codepred.cleanrepo.enums.ResponseStatus;

public record SetPasswordResponse(ResponseStatus code,
                                  String message,
                                  String token,
                                  UserRole role) {
}
