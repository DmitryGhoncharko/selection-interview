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

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaHibernateQuestionTypeService implements QuestionTypeService{
    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionTypeMapper questionTypeMapper = QuestionTypeMapper.INSTANCE;
    @Override
    public QuestionTypeDTO save(QuestionTypeDTO questionTypeDTO) {
        QuestionType questionType = questionTypeMapper.questionTypeDtoToQuestionType(questionTypeDTO);
        QuestionType questionTypeAfterSave = questionTypeRepository.save(questionType);
        return questionTypeMapper.questionTypeToQuestionTypeDto(questionTypeAfterSave);
    }

    @Override
    public void delete(QuestionTypeDTO questionTypeDTO) {
        QuestionType questionType = questionTypeMapper.questionTypeDtoToQuestionType(questionTypeDTO);
        questionTypeRepository.delete(questionType);
    }

    @Override
    public Optional<QuestionTypeDTOWithoutQuestions> findByQuestionTypeNameIgnoreCase(String questionTypeName) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeName(questionTypeName);
        if(questionTypeOptional.isPresent()){
            return Optional.of(questionTypeMapper.questionTypeToQuestionTypeDtoWithoutQuestions(questionTypeOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<QuestionTypeDTO> findByQuestionTypeNameAllQuestionsWithoutLazy(String questionTypeName) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeNameAllQuestionsWithoutLazy(questionTypeName);
        if(questionTypeOptional.isPresent()){
            return Optional.of(questionTypeMapper.questionTypeToQuestionTypeDto(questionTypeOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<QuestionTypeDTO> findByQuestionTypeIdAllQuestionsWithoutLazy(BigInteger questionTypeId) {
       Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeIdAllQuestionsWithoutLazy(questionTypeId);
       if(questionTypeOptional.isPresent()){
           return Optional.of(questionTypeMapper.questionTypeToQuestionTypeDto(questionTypeOptional.get()));
       }
       return Optional.empty();
    }

    @Override
    public List<QuestionTypeDTOWithoutQuestions> findAll(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAll(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoWithoutQuestionsList(questionTypePage.getContent());
    }

    @Override
    public List<QuestionTypeDTO> findAllWithoutLazy(Pageable pageable) {
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllWithoutLazy(pageable);
        return questionTypeMapper.mapQuestionTypeListToQuestionTypeDtoList(questionTypePage.getContent());
    }

    @Override
    public void deleteById(BigInteger id) {
        questionTypeRepository.deleteById(id);
    }

    @Override
    public void deleteByQuestionTypeName(String name) {
        questionTypeRepository.deleteByQuestionTypeName(name);
    }

    @Override
    public Optional<QuestionTypeDTOWithoutQuestions> findById(BigInteger id) {
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findById(id);
        if(questionTypeOptional.isPresent()){
            return Optional.of(questionTypeMapper.questionTypeToQuestionTypeDtoWithoutQuestions(questionTypeOptional.get()));
        }
        return Optional.empty();
    }
}
