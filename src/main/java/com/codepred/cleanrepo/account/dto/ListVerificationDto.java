package com.codepred.cleanrepo.account.dto;

import java.util.List;
public record ListVerificationDto(List<VerificationDto> questionList, String surveyName) {
}
