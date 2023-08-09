package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOWithoutQuestions;
import by.ghoncharko.selectioninterview.entity.QuestionType;
import by.ghoncharko.selectioninterview.mapper.QuestionTypeMapper;
import by.ghoncharko.selectioninterview.repository.QuestionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaHibernateQuestionTypeService implements QuestionTypeService{
    private static final QuestionTypeMapper questionTypeMapper = QuestionTypeMapper.INSTANCE;
    private final QuestionTypeRepository questionTypeRepository;
    @Override
    @Transactional
    public QuestionTypeDTO create(QuestionTypeDTO questionTypeDTO) {
        QuestionType questionType = questionTypeMapper.questionTypeDtoToQuestionType(questionTypeDTO);
        QuestionType questionTypeAfterSave = questionTypeRepository.save(questionType);
        return questionTypeMapper.questionTypeToQuestionTypeDto(questionTypeAfterSave);
    }

    @Override
    @Transactional
    public QuestionTypeDTO update(QuestionTypeDTO questionTypeDTO) {
        QuestionType questionType = questionTypeMapper.questionTypeDtoToQuestionType(questionTypeDTO);
        QuestionType questionTypeAfterSave = questionTypeRepository.save(questionType);
        return questionTypeMapper.questionTypeToQuestionTypeDto(questionTypeAfterSave);
    }

    @Override
    @Transactional
    public void delete(QuestionTypeDTO questionTypeDTO) {
        QuestionType questionType = questionTypeMapper.questionTypeDtoToQuestionType(questionTypeDTO);
        questionTypeRepository.delete(questionType);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionTypeDTOWithoutQuestions> findByQuestionTypeNameIgnoreCase(String questionTypeName) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeName(questionTypeName);
        if(questionTypeOptional.isPresent()){
            return Optional.of(questionTypeMapper.questionTypeToQuestionTypeDtoWithoutQuestions(questionTypeOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionTypeDTO> findByQuestionTypeNameAllQuestionsWithoutLazy(String questionTypeName) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeNameAllQuestionsWithoutLazy(questionTypeName);
        if(questionTypeOptional.isPresent()){
            return Optional.of(questionTypeMapper.questionTypeToQuestionTypeDto(questionTypeOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionTypeDTO> findByQuestionTypeIdAllQuestionsWithoutLazy(BigInteger questionTypeId) {
       Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeIdAllQuestionsWithoutLazy(questionTypeId);
       if(questionTypeOptional.isPresent()){
           return Optional.of(questionTypeMapper.questionTypeToQuestionTypeDto(questionTypeOptional.get()));
       }
       return Optional.empty();
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
    @Transactional
    public void deleteById(BigInteger id) {
        questionTypeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByQuestionTypeName(String name) {
        questionTypeRepository.deleteByQuestionTypeName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionTypeDTOWithoutQuestions> findById(BigInteger id) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findById(id);
        if(questionTypeOptional.isPresent()){
            return Optional.of(questionTypeMapper.questionTypeToQuestionTypeDtoWithoutQuestions(questionTypeOptional.get()));
        }
        return Optional.empty();
    }
}
