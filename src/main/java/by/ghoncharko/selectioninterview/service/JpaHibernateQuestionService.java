package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dao.repository.QuestionRepository;
import by.ghoncharko.selectioninterview.dao.repository.QuestionTypeRepository;
import by.ghoncharko.selectioninterview.dto.QuestionDTO;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutAnswer;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutAnswersAndType;
import by.ghoncharko.selectioninterview.dto.QuestionDTOWithoutQuestionType;
import by.ghoncharko.selectioninterview.dto.QuestionDtoForCreateOrUpdate;
import by.ghoncharko.selectioninterview.entity.Question;
import by.ghoncharko.selectioninterview.entity.QuestionType;
import by.ghoncharko.selectioninterview.error.CannotCreateQuestionError;
import by.ghoncharko.selectioninterview.error.CannotCreateQuestionTypeError;
import by.ghoncharko.selectioninterview.error.CannotDeleteQuestionError;
import by.ghoncharko.selectioninterview.error.CannotUpdateQuestionError;
import by.ghoncharko.selectioninterview.mapper.QuestionMapper;
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

@RequiredArgsConstructor
@Service
public class JpaHibernateQuestionService implements QuestionService {
    private static final QuestionMapper questionMapper = QuestionMapper.INSTANCE;
    private final QuestionRepository questionRepository;
    private final QuestionTypeRepository questionTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionDTOWithoutAnswersAndType> findByQuestionBodyIgnoreCase(String questionBody) {
        Optional<Question> questionOptional = questionRepository.findByQuestionBodyIgnoreCase(questionBody);
        return questionOptional.map(questionMapper::questionToQuestionDtoWithoutAnswersAndType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTOWithoutAnswersAndType> findAll(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAll(pageable);
        return questionMapper.mapQuestionListToQuestionDtoWithoutAnswersAndTypeList(questionPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTO> findAllWithoutLazyWithAnswersAndQuestionTypes(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAllWithoutLazyWithAnswersAndQuestionTypes(pageable);
        return questionMapper.mapQuestionListToQuestionDtoList(questionPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTOWithoutQuestionType> findAllWithoutLazyWithAnswers(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAllWithoutLazyWithAnswers(pageable);
        return questionMapper.mapQuestionListToQuestionDtoWithoutQuestionTypeList(questionPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTOWithoutAnswer> findAllWithoutLazyWithQuestionTypes(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAllWithoutLazyWithQuestionTypes(pageable);
        return questionMapper.mapQuestionListToQuestionDtoWithoutAnswerList(questionPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionDTOWithoutQuestionType> findByQuestionBodyWithoutLazyAnswers(String body) {
        Optional<Question> questionOptional = questionRepository.findByQuestionBodyWithoutLazyAnswers(body);
        return questionOptional.map(questionMapper::questionToQuestionDtoWithoutType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTOWithoutAnswersAndType> findAllByDeletedIsFalse(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAllByDeletedIsFalse(pageable);
        return questionMapper.mapQuestionListToQuestionDtoWithoutAnswersAndTypeList(questionPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTOWithoutAnswersAndType> findAllByDeletedIsTrue(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAllByDeletedIsTrue(pageable);
        return questionMapper.mapQuestionListToQuestionDtoWithoutAnswersAndTypeList(questionPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTOWithoutQuestionType> findAllWithoutLazyWithAnswersWhereAnswersDeletedFalse(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAllWithoutLazyWithAnswersWhereAnswersDeletedFalse(pageable);
        return questionMapper.mapQuestionListToQuestionDtoWithoutQuestionTypeList(questionPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTOWithoutQuestionType> findAllWithoutLazyWithAnswersWhereAnswersDeletedTrue(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAllWithoutLazyWithAnswersWhereAnswersDeletedTrue(pageable);
        return questionMapper.mapQuestionListToQuestionDtoWithoutQuestionTypeList(questionPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTOWithoutAnswer> findAllWithoutLazyWithQuestionTypesDeletedFalse(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAllWithoutLazyWithQuestionTypesDeletedFalse(pageable);
        return questionMapper.mapQuestionListToQuestionDtoWithoutAnswerList(questionPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTOWithoutAnswer> findAllWithoutLazyWithQuestionTypesDeletedTrue(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAllWithoutLazyWithQuestionTypesDeletedTrue(pageable);
        return questionMapper.mapQuestionListToQuestionDtoWithoutAnswerList(questionPage.getContent());
    }

    @Override
    @Transactional
    public void deleteById(BigInteger id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (questionOptional.isEmpty()) {
            throw new CannotDeleteQuestionError("Cannot delete question by id because question is not present in database questionId = " + id);
        }
        if (questionOptional.get().isDeleted()) {
            throw new CannotDeleteQuestionError("Cannot delete question by id because question already is deleted questionId =" + id);
        }
        questionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByQuestionBody(String body) {
        Optional<Question> questionOptional = questionRepository.findByQuestionBody(body);
        if (questionOptional.isEmpty()) {
            throw new CannotDeleteQuestionError("Cannot delete question by question body because question is not present in database questionBody = " + body);
        }
        if (questionOptional.get().isDeleted()) {
            throw new CannotDeleteQuestionError("Cannot delete question by question body because question already is deleted questionBody = " + body);
        }
        questionRepository.deleteByQuestionBody(body);
    }

    @Override
    @Transactional
    public QuestionDTO create(QuestionDtoForCreateOrUpdate questionDtoForCreateOrUpdate) {
        Question question = questionMapper.questionDtoForCreateOrUpdateToQuestion(questionDtoForCreateOrUpdate);
        question.setDateCreated(new Timestamp(new Date().getTime()));
        question.setLastDateUpdated(new Timestamp(new Date().getTime()));
        Optional<Question> questionOptional = questionRepository.findByQuestionBody(question.getQuestionBody());
        if (questionOptional.isPresent()) {
            throw new CannotCreateQuestionError("Cannot create question because question body is present questioBody = " + question.getQuestionBody());
        }
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeName(question.getQuestionType()
                .getQuestionTypeName());
        if (questionTypeOptional.isEmpty()) {
            QuestionType questionType = QuestionType.builder()
                    .questionTypeName(question.getQuestionType().getQuestionTypeName())
                    .dateCreated(new Timestamp(new Date().getTime()))
                    .lastDateUpdated(new Timestamp(new Date().getTime()))
                    .deleted(question.getQuestionType().isDeleted())
                    .build();
            QuestionType questionTypeAfterSave = questionTypeRepository.save(questionType);
            question.setQuestionType(questionTypeAfterSave);
        } else {
            QuestionType questionType = questionTypeOptional.get();
            question.setQuestionType(questionType);
        }
        Question questionAfterSave = questionRepository.save(question);
        QuestionDTO questionDTO = questionMapper.questionToQuestionDto(questionAfterSave);
        if (questionDTO.getAnswers() == null) {
            questionDTO.setAnswers(new ArrayList<>());
        }
        return questionDTO;
    }

    @Override
    @Transactional
    public QuestionDTO update(QuestionDtoForCreateOrUpdate questionDtoForCreateOrUpdate) {
        Question question = questionMapper.questionDtoForCreateOrUpdateToQuestion(questionDtoForCreateOrUpdate);
        Optional<Question> questionFromDatabaseOptional = questionRepository.findById(question.getId());
        if (questionFromDatabaseOptional.isPresent()) {
            Question questionFromDatabase = questionFromDatabaseOptional.get();
            question.setDateCreated(questionFromDatabase.getDateCreated());
            question.setLastDateUpdated(new Timestamp(new Date().getTime()));
        } else {
            throw new CannotUpdateQuestionError("Cannot update question because is not present in database questionId= " + questionDtoForCreateOrUpdate.getId());
        }
        Optional<Question> questionOptional = questionRepository.findByQuestionBody(question.getQuestionBody());
        if (questionOptional.isPresent() && !questionOptional.get().getId().equals(question.getId())) {
            throw new CannotUpdateQuestionError("Cannot update question because question body is present in database questonBody = " + question.getQuestionBody());
        }
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeName(question.getQuestionType()
                .getQuestionTypeName());
        if (questionTypeOptional.isPresent()) {
            QuestionType questionTypeFromOptional = questionTypeOptional.get();
            question.setQuestionType(questionTypeFromOptional);
        } else {
            QuestionType questionType = new QuestionType().builder()
                    .questionTypeName(question.getQuestionType().getQuestionTypeName())
                    .deleted(question.getQuestionType().isDeleted())
                    .build();
            QuestionType questionTypeAfterSave = questionTypeRepository.save(questionType);
            question.setQuestionType(questionTypeAfterSave);
        }
        Question questionAfterSave = questionRepository.save(question);
        QuestionDTO questionDTO = questionMapper.questionToQuestionDto(questionAfterSave);
        if (questionDTO.getAnswers() == null) {
            questionDTO.setAnswers(new ArrayList<>());
        }
        return questionDTO;
    }
}
