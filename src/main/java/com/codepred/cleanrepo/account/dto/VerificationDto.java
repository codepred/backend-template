package com.codepred.cleanrepo.account.dto;

import com.codepred.cleanrepo.question.Type;

import java.util.List;

public record VerificationDto(String question, String modelAnswer, String answerContent, Type type, List<String> answers) {
}
