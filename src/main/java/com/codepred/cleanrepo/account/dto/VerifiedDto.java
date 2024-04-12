package com.codepred.cleanrepo.account.dto;

import com.codepred.cleanrepo.question.Type;

public record VerifiedDto(String question, String modelAnswer, String answerContent, boolean isAnswerCorrect, Type type) {
}
