package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dto.QuestionDTO;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutAnswer;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutAnswersAndType;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutQuestionType;
import by.ghoncharko.selectioninterview.dto.QuestionDtoForCreateOrUpdate;
import by.ghoncharko.selectioninterview.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Optional<QuestionDTOWithoutAnswersAndType> findByQuestionBodyIgnoreCase(String questionBody);

    List<QuestionDTOWithoutAnswersAndType> findAll(Pageable pageable);

    List<QuestionDTO> findAllWithoutLazyWithAnswersAndQuestionTypes(Pageable pageable);

    List<QuestionDTOWithoutQuestionType> findAllWithoutLazyWithAnswers(Pageable pageable);

    List<QuestionDTOWithoutAnswer> findAllWithoutLazyWithQuestionTypes(Pageable pageable);

    Optional<QuestionDTOWithoutQuestionType> findByQuestionBodyWithoutLazyAnswers(String body);

    List<QuestionDTOWithoutAnswersAndType> findAllByDeletedIsFalse(Pageable pageable);

    List<QuestionDTOWithoutAnswersAndType> findAllByDeletedIsTrue(Pageable pageable);

    List<QuestionDTOWithoutQuestionType> findAllWithoutLazyWithAnswersWhereAnswersDeletedFalse(Pageable pageable);

    List<QuestionDTOWithoutQuestionType> findAllWithoutLazyWithAnswersWhereAnswersDeletedTrue(Pageable pageable);

    List<QuestionDTOWithoutAnswer> findAllWithoutLazyWithQuestionTypesDeletedFalse(Pageable pageable);

    List<QuestionDTOWithoutAnswer> findAllWithoutLazyWithQuestionTypesDeletedTrue(Pageable pageable);

    void deleteById(BigInteger id);

    void deleteByQuestionBody(String body);

    QuestionDTO create(QuestionDtoForCreateOrUpdate questionDtoForCreateOrUpdate);

    QuestionDTO update(QuestionDtoForCreateOrUpdate questionDtoForCreateOrUpdate);

}
