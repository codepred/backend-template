package com.codepred.cleanrepo.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmittedQuestionRepository extends JpaRepository<SubmittedQuestion, Integer> {
}
