package com.codepred.cleanrepo.account.dto;

import com.codepred.cleanrepo.survey.Status;

import java.time.LocalDate;

public record SurveyForSurveyListDto(int id, String name, LocalDate assignedDate, Status status) {
}
