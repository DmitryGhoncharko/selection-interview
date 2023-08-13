package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOWithoutQuestions;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface QuestionTypeService {
    QuestionTypeDTO create(QuestionTypeDTOForCreateOrUpdate questionTypeDtoForCreateOrUpdate);

    QuestionTypeDTO update(QuestionTypeDTOForCreateOrUpdate questionTypeDtoForCreateOrUpdate);

    Optional<QuestionTypeDTOWithoutQuestions> findByQuestionTypeNameIgnoreCase(String questionTypeName);

    Optional<QuestionTypeDTO> findByQuestionTypeNameAllQuestionsWithoutLazy(String questionTypeName);

    Optional<QuestionTypeDTO> findByQuestionTypeIdAllQuestionsWithoutLazy(BigInteger questionTypeId);

    List<QuestionTypeDTOWithoutQuestions> findAll(Pageable pageable);

    List<QuestionTypeDTO> findAllWithoutLazy(Pageable pageable);

    Optional<QuestionTypeDTOWithoutQuestions> findById(BigInteger id);

    List<QuestionTypeDTOWithoutQuestions> findAllByDeletedIsTrue(Pageable pageable);

    List<QuestionTypeDTOWithoutQuestions> findAllByDeletedIsFalse(Pageable pageable);

    List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeFalse(Pageable pageable);

    List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionFalse(Pageable pageable);

    List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionTrue(Pageable pageable);

    List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeTrue(Pageable pageable);

    List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsFalse(Pageable pageable);

    List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsTrue(Pageable pageable);

    void deleteById(BigInteger id);

    void deleteByQuestionTypeName(String name);
}
