package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dao.repository.QuestionTypeRepository;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOWithoutQuestions;
import by.ghoncharko.selectioninterview.entity.QuestionType;
import by.ghoncharko.selectioninterview.error.CannotCreateQuestionTypeError;
import by.ghoncharko.selectioninterview.error.CannotDeleteQuestionTypeError;
import by.ghoncharko.selectioninterview.error.CannotUpdateQuestionTypeError;
import by.ghoncharko.selectioninterview.mapper.QuestionTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaHibernateQuestionTypeService implements QuestionTypeService {
    private static final QuestionTypeMapper questionTypeMapper = QuestionTypeMapper.INSTANCE;
    private final QuestionTypeRepository questionTypeRepository;

    @Override
    @Transactional
    public QuestionTypeDTO create(QuestionTypeDTOForCreateOrUpdate questionTypeDtoForCreateOrUpdate) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeName(questionTypeDtoForCreateOrUpdate.getQuestionTypeName());
        if(questionTypeOptional.isPresent()){
            throw new CannotCreateQuestionTypeError("Cannot create question type error because question type with name " + questionTypeDtoForCreateOrUpdate.getQuestionTypeName() + " is present");
        }
        QuestionType questionType = questionTypeMapper.questionTypeToQuestionTypeDToForCrateOrUpdate(questionTypeDtoForCreateOrUpdate);
        questionType.setDateCreated(new Timestamp(new Date().getTime()));
        questionType.setLastDateUpdated(new Timestamp(new Date().getTime()));
        QuestionType questionTypeAfterSave = questionTypeRepository.save(questionType);
        QuestionTypeDTO questionTypeDTO = questionTypeMapper.questionTypeToQuestionTypeDto(questionTypeAfterSave);
        if (questionTypeDTO.getQuestions() == null) {
            questionTypeDTO.setQuestions(new ArrayList<>());
        }
        return questionTypeDTO;
    }

    @Override
    @Transactional
    public QuestionTypeDTO update(QuestionTypeDTOForCreateOrUpdate questionTypeDtoForCreateOrUpdate) {
        QuestionType questionType = questionTypeMapper.questionTypeToQuestionTypeDToForCrateOrUpdate(questionTypeDtoForCreateOrUpdate);
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findById(questionType.getId());
        if (questionTypeOptional.isPresent()) {
            Optional<QuestionType> questionTypeOptionalByName = questionTypeRepository.findByQuestionTypeName(questionTypeDtoForCreateOrUpdate.getQuestionTypeName());
            if(questionTypeOptionalByName.isPresent() && questionTypeOptionalByName.get().getQuestionTypeName().equals(questionTypeDtoForCreateOrUpdate.getQuestionTypeName()) && !questionTypeOptionalByName.get()
                    .getId()
                    .equals(questionTypeDtoForCreateOrUpdate.getId())){
                throw new CannotUpdateQuestionTypeError("Cannot update question type because question type name is present in database questionTypeName = "  + questionTypeDtoForCreateOrUpdate.getQuestionTypeName());
            }
            QuestionType questionTypeFromOptional = questionTypeOptional.get();
            questionType.setDateCreated(questionTypeFromOptional.getDateCreated());
            questionType.setLastDateUpdated(new Timestamp(new Date().getTime()));
            QuestionType questionTypeAfterSave = questionTypeRepository.save(questionType);
            QuestionTypeDTO questionTypeDTO = questionTypeMapper.questionTypeToQuestionTypeDto(questionTypeAfterSave);
            if (questionTypeDTO.getQuestions() == null) {
                questionTypeDTO.setQuestions(new ArrayList<>());
            }
            return questionTypeDTO;
        }
        throw new CannotUpdateQuestionTypeError("Cannot update question type by id because question type not present in database questionTypeId = " + questionType.getId()
                .toString());
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionTypeDTOWithoutQuestions> findByQuestionTypeNameIgnoreCase(String questionTypeName) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeName(questionTypeName);
        return questionTypeOptional.map(questionTypeMapper::questionTypeToQuestionTypeDtoWithoutQuestions);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionTypeDTO> findByQuestionTypeNameAllQuestionsWithoutLazy(String questionTypeName) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeNameAllQuestionsWithoutLazy(questionTypeName);
        return questionTypeOptional.map(questionTypeMapper::questionTypeToQuestionTypeDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionTypeDTO> findByQuestionTypeIdAllQuestionsWithoutLazy(BigInteger questionTypeId) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeIdAllQuestionsWithoutLazy(questionTypeId);
        return questionTypeOptional.map(questionTypeMapper::questionTypeToQuestionTypeDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTOWithoutQuestions> findAll(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAll(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoWithoutQuestionsList(questionTypePage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTO> findAllWithoutLazy(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllWithoutLazy(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoList(questionTypePage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionTypeDTOWithoutQuestions> findById(BigInteger id) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findById(id);
        return questionTypeOptional.map(questionTypeMapper::questionTypeToQuestionTypeDtoWithoutQuestions);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTOWithoutQuestions> findAllByDeletedIsTrue(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllByDeletedIsTrue(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoWithoutQuestionsList(questionTypePage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTOWithoutQuestions> findAllByDeletedIsFalse(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllByDeletedIsFalse(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoWithoutQuestionsList(questionTypePage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeFalse(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllWithoutLazyDeletedQuestionTypeFalse(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoList(questionTypePage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionFalse(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionFalse(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoList(questionTypePage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionTrue(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionTrue(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoList(questionTypePage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeTrue(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllWithoutLazyDeletedQuestionTypeTrue(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoList(questionTypePage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsFalse(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsFalse(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoList(questionTypePage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionTypeDTO> findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsTrue(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsTrue(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoList(questionTypePage.getContent());
    }

    @Override
    @Transactional
    public void deleteById(BigInteger id) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findById(id);
        if(questionTypeOptional.isEmpty()){
            throw new CannotDeleteQuestionTypeError("Cannot delete question type by id because question type not present in database questionTypeId = " + id);
        }
        if(questionTypeOptional.get().isDeleted()){
            throw new CannotDeleteQuestionTypeError("Cannot delete question type by id because question type already is deleted questionTypeId = " + id);
        }
        questionTypeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByQuestionTypeName(String name) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeName(name);
        if(questionTypeOptional.isEmpty()){
            throw new CannotDeleteQuestionTypeError("Cannot delete question type by name because question type not present in database questionTypeName = " + name);
        }
        if(questionTypeOptional.get().isDeleted()){
            throw new CannotDeleteQuestionTypeError("Cannot delete question type by name because question type already is deleted questionTypeName = " + name);
        }
        questionTypeRepository.deleteByQuestionTypeName(name);
    }
}
