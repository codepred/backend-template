package com.codepred.cleanrepo.account.dto;

import com.codepred.cleanrepo.account.value_object.UserRole;

import java.time.LocalDateTime;

public record HostAccountDto(int id, LocalDateTime createdDate, String fullName, String email, int completedSurveys, UserRole role) {
}
