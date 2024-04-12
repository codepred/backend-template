package com.codepred.cleanrepo.account.dto;

import java.util.List;

public record VerificationResultDto(List<VerifiedDto> questionList, String name) {
}
