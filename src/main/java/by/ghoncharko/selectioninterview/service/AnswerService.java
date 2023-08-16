package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dto.AnswerDTO;
import by.ghoncharko.selectioninterview.dto.AnswerDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.dto.AnswerDTOWithoutQuestion;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;

public interface AnswerService {
    List<AnswerDTOWithoutQuestion> findAll(Pageable pageable);

    List<AnswerDTO> findAllWithoutLazyQuestions(Pageable pageable);

    List<AnswerDTOWithoutQuestion> findAllByDeletedIsTrue(Pageable pageable);

    List<AnswerDTOWithoutQuestion> findAllByDeletedIsFalse(Pageable pageable);

    List<AnswerDTO> findAllWithoutLazyQuestionsWhereQuestionsDeletedTrue(Pageable pageable);

    List<AnswerDTO> findAllWithoutLazyQuestionsWhereQuestionsDeletedFalse(Pageable pageable);

    List<AnswerDTO> findAllWithoutLazyQuestionsWhereAnswersDeletedTrueAndQuestionDeletedFalse(Pageable pageable);

    List<AnswerDTO> findAllWithoutLazyQuestionsWhereAnswersDeletedTrueAndQuestionDeletedTrue(Pageable pageable);

    List<AnswerDTO> findAllWithoutLazyQuestionsWhereAnswersDeletedFalseAndQuestionDeletedFalse(Pageable pageable);

    List<AnswerDTO> findAllWithoutLazyQuestionsWhereAnswersDeletedFalseAndQuestionDeletedTrue(Pageable pageable);

    void deleteAnswerById(BigInteger id);

    void deleteAnswerByBody(String body);

    AnswerDTOWithoutQuestion create(AnswerDTOForCreateOrUpdate answerDTOForCreateOrUpdate);

    AnswerDTOWithoutQuestion update(AnswerDTOForCreateOrUpdate answerDTOForCreateOrUpdate);
}
