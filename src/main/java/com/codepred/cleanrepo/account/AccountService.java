package com.codepred.cleanrepo.account;

import com.codepred.cleanrepo.account.dto.ResultAnswerDto;
import com.codepred.cleanrepo.account.dto.ResultDto;
import com.codepred.cleanrepo.account.dto.ResultQuestionDto;
import com.codepred.cleanrepo.account.value_object.Username;
import com.codepred.cleanrepo.answer.Answer;
import com.codepred.cleanrepo.auth.JwtService;
import com.codepred.cleanrepo.auth.exception.InvalidJwtException;
import com.codepred.cleanrepo.auth.value_object.Jwt;
import com.codepred.cleanrepo.question.Question;
import com.codepred.cleanrepo.question.SubmittedQuestion;
import com.codepred.cleanrepo.question.Type;
import com.codepred.cleanrepo.survey.SubmittedSurvey;
import com.codepred.cleanrepo.survey.SurveyWithChosenQuestions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final JwtService jwtService;
    private final AccountRepository accountRepository;
    Account getAccountFromToken(String token) {
        Jwt jwt = new Jwt(token.split(" ")[1]);
        Optional<String> email = jwtService.extractUserEmailFromJwt(jwt);
        if(email.isEmpty()) {
            throw new InvalidJwtException();
        }
        Optional<Account> account = accountRepository.findByEmail_Email(email.get());
        if(account.isEmpty()) {
            throw new InvalidJwtException();
        }
        return account.get();
    }
    Optional<Username> getOptionalName(Account account) {
        return Optional.ofNullable(account.getName());
    }

    ResultDto getResults(SurveyWithChosenQuestions surveyWithChosenQuestions) {
        List<ResultQuestionDto> resultQuestionDtos = new ArrayList<>();
        int allAnswersCounter = 0;
        float correctAnswersCounter = 0.0f;
        for(Question question : surveyWithChosenQuestions.getQuestionList()) {
            List<ResultAnswerDto> resultAnswerDtos = new ArrayList<>();
            for(SubmittedSurvey submittedSurvey : surveyWithChosenQuestions.getSubmittedSurveys()) {
                for(SubmittedQuestion submittedQuestion : submittedSurvey.getQuestionList()) {
                    if(question.getQuestion().equals(submittedQuestion.getQuestion())) {
                        if (question.getType().equals(Type.ABCD)) {
                            System.out.println(submittedQuestion.getSelectedAnswerId() + " hehe " + question.getCorrectAnswerId());
                            String content = "";
                            for (Answer answer : submittedQuestion.getAnswers()) {
                                if (answer.getId() == submittedQuestion.getSelectedAnswerId()) {
                                    content = answer.getContent();
                                    break;
                                }
                            }
                            String correctContent = "";
                            for(Answer answer : question.getAnswers()) {
                                if(answer.getId() == question.getCorrectAnswerId()) {
                                    correctContent = answer.getContent();
                                    break;
                                }
                            }
                            if (correctContent.equals(content) || submittedQuestion.isCorrect()) {
                                correctAnswersCounter++;
                            }
                            resultAnswerDtos.add(new ResultAnswerDto(
                                    getOptionalName(submittedSurvey.getSubmitter()).map(Username::getUsername).orElse(null),
                                    content));
                        } else if (question.getType().equals(Type.OPEN)) {
                            if (submittedQuestion.getAnswerContent().equals(question.getModelAnswer()) || submittedQuestion.isCorrect()) {
                                correctAnswersCounter++;
                            }
                            resultAnswerDtos.add(new ResultAnswerDto(
                                    getOptionalName(submittedSurvey.getSubmitter()).map(Username::getUsername).orElse(null),
                                    submittedQuestion.getAnswerContent()));
                        }
                        allAnswersCounter++;
                        break;
                    }
                }
            }
            if(question.getType().equals(Type.ABCD)) {
                String content = "";
                for(Answer answer : question.getAnswers()) {
                    if(answer.getId() == question.getCorrectAnswerId()) {
                        content = answer.getContent();
                        break;
                    }
                }
                resultQuestionDtos.add(new ResultQuestionDto(question.getQuestion(), content, resultAnswerDtos));
            }
            else if(question.getType().equals(Type.OPEN)) {
                resultQuestionDtos.add(new ResultQuestionDto(question.getQuestion(), question.getModelAnswer(), resultAnswerDtos));
            }
        }
        float score = Math.round(correctAnswersCounter*100/allAnswersCounter);
        return new ResultDto((int) score, resultQuestionDtos);
    }
}
