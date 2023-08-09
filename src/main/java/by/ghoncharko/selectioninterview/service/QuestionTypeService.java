package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOWithoutQuestions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface QuestionTypeService {
    QuestionTypeDTO save(QuestionTypeDTO questionTypeDTO);

    void delete(QuestionTypeDTO questionTypeDTO);

    void deleteById(BigInteger id);

    void deleteByQuestionTypeName(String name);

    Optional<QuestionTypeDTOWithoutQuestions> findByQuestionTypeNameIgnoreCase(String questionTypeName);

    Optional<QuestionTypeDTO> findByQuestionTypeNameAllQuestionsWithoutLazy(String questionTypeName);

    Optional<QuestionTypeDTO> findByQuestionTypeIdAllQuestionsWithoutLazy(BigInteger questionTypeId);

    List<QuestionTypeDTOWithoutQuestions> findAll(Pageable pageable);

    List<QuestionTypeDTO> findAllWithoutLazy(Pageable pageable);

    Optional<QuestionTypeDTOWithoutQuestions> findById(BigInteger id);
}
