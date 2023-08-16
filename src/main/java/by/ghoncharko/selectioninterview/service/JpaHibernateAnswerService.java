package by.ghoncharko.selectioninterview.service;

import by.ghoncharko.selectioninterview.dao.repository.AnswerRepository;
import by.ghoncharko.selectioninterview.dao.repository.QuestionRepository;
import by.ghoncharko.selectioninterview.dao.repository.QuestionTypeRepository;
import by.ghoncharko.selectioninterview.dto.AnswerDTO;
import by.ghoncharko.selectioninterview.dto.AnswerDTOForCreateOrUpdate;
import by.ghoncharko.selectioninterview.dto.AnswerDTOWithoutQuestion;
import by.ghoncharko.selectioninterview.entity.Answer;
import by.ghoncharko.selectioninterview.entity.Question;
import by.ghoncharko.selectioninterview.entity.QuestionType;
import by.ghoncharko.selectioninterview.error.CannotCreateAnswerError;
import by.ghoncharko.selectioninterview.error.CannotDeleteAnswerError;
import by.ghoncharko.selectioninterview.error.CannotUpdateAnswerError;
import by.ghoncharko.selectioninterview.mapper.AnswerMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaHibernateAnswerService implements AnswerService {
    private static final AnswerMapper answerMapper = AnswerMapper.INSTANCE;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    private final QuestionTypeRepository questionTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTOWithoutQuestion> findAll(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAll(pageable);
        return answerMapper.mapAnswerListToAnswerDtoWithoutQuestionList(answerPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> findAllWithoutLazyQuestions(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAllWithoutLazyQuestions(pageable);
        return answerMapper.mapAnswerListToAnswerDtoList(answerPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTOWithoutQuestion> findAllByDeletedIsTrue(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAllByDeletedIsTrue(pageable);
        return answerMapper.mapAnswerListToAnswerDtoWithoutQuestionList(answerPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTOWithoutQuestion> findAllByDeletedIsFalse(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAllByDeletedIsFalse(pageable);
        return answerMapper.mapAnswerListToAnswerDtoWithoutQuestionList(answerPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> findAllWithoutLazyQuestionsWhereQuestionsDeletedTrue(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAllWithoutLazyQuestionsWhereQuestionsDeletedTrue(pageable);
        return answerMapper.mapAnswerListToAnswerDtoList(answerPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> findAllWithoutLazyQuestionsWhereQuestionsDeletedFalse(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAllWithoutLazyQuestionsWhereQuestionsDeletedFalse(pageable);
        return answerMapper.mapAnswerListToAnswerDtoList(answerPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> findAllWithoutLazyQuestionsWhereAnswersDeletedTrueAndQuestionDeletedFalse(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAllWithoutLazyQuestionsWhereAnswersDeletedTrueAndQuestionDeletedFalse(pageable);
        return answerMapper.mapAnswerListToAnswerDtoList(answerPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> findAllWithoutLazyQuestionsWhereAnswersDeletedTrueAndQuestionDeletedTrue(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAllWithoutLazyQuestionsWhereAnswersDeletedTrueAndQuestionDeletedTrue(pageable);
        return answerMapper.mapAnswerListToAnswerDtoList(answerPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> findAllWithoutLazyQuestionsWhereAnswersDeletedFalseAndQuestionDeletedFalse(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAllWithoutLazyQuestionsWhereAnswersDeletedFalseAndQuestionDeletedFalse(pageable);
        return answerMapper.mapAnswerListToAnswerDtoList(answerPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnswerDTO> findAllWithoutLazyQuestionsWhereAnswersDeletedFalseAndQuestionDeletedTrue(Pageable pageable) {
        Page<Answer> answerPage = answerRepository.findAllWithoutLazyQuestionsWhereAnswersDeletedFalseAndQuestionDeletedTrue(pageable);
        return answerMapper.mapAnswerListToAnswerDtoList(answerPage.getContent());
    }

    @Override
    @Transactional
    public void deleteAnswerById(BigInteger id) {
        Optional<Answer> answerOptional = answerRepository.findById(id);
        if (answerOptional.isEmpty()) {
            throw new CannotDeleteAnswerError("Cannot delete answer by id because answer is not found in database answerId = " + id);
        }
        if (answerOptional.get().isDeleted()) {
            throw new CannotDeleteAnswerError("Cannot delete answer by id because answer already deleted answerId = " + id);
        }
        answerRepository.deleteAnswerById(id);
    }

    @Override
    @Transactional
    public void deleteAnswerByBody(String body) {
        Optional<Answer> answerOptional = answerRepository.findByAnswerBody(body);
        if (answerOptional.isEmpty()) {
            throw new CannotDeleteAnswerError("Cannot delete answer by body because answer is not found in database answerBody = " + body);
        }
        if (answerOptional.get().isDeleted()) {
            throw new CannotDeleteAnswerError("Cannot delete answer by body because answer already deleted answerBody = " + body);
        }
        answerRepository.deleteAnswerByBody(body);
    }

    @Override
    @Transactional
    public AnswerDTOWithoutQuestion create(AnswerDTOForCreateOrUpdate answerDTOForCreateOrUpdate) {
        Answer answer = answerMapper.answerDtoForCreateOrUpdateToAnswer(answerDTOForCreateOrUpdate);
        Optional<Question> questionOptional = questionRepository.findById(answer.getQuestion().getId());
        if (questionOptional.isEmpty()) {
            throw new CannotCreateAnswerError("Cannot create answer because question is not exist questionId = " + answer.getQuestion()
                    .getId());
        }
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findById(answerDTOForCreateOrUpdate.getQuestionDtoForCreateOrUpdate().getQuestionTypeDTO().getId());
        if (questionTypeOptional.isEmpty()) {
            throw new CannotCreateAnswerError("Cannot create answer becase question is not exist questionTypeId = " + answerDTOForCreateOrUpdate.getQuestionDtoForCreateOrUpdate().getQuestionTypeDTO().getId());
        }
        answer.setDateCreated(new Timestamp(new Date().getTime()));
        answer.setLastDateUpdated(new Timestamp(new Date().getTime()));
        Answer answerAfterSave = answerRepository.save(answer);
        return answerMapper.answerToAnswerDtoWithoutQuestion(answerAfterSave);
    }

    @Override
    @Transactional
    public AnswerDTOWithoutQuestion update(AnswerDTOForCreateOrUpdate answerDTOForCreateOrUpdate) {
        Optional<Answer> answerOptional = answerRepository.findById(answerDTOForCreateOrUpdate.getId());
        if (answerOptional.isEmpty()) {
            throw new CannotUpdateAnswerError("Cannot update answer because answer is not present in database answerId = " + answerDTOForCreateOrUpdate.getId());
        }
        Answer answer = answerMapper.answerDtoForCreateOrUpdateToAnswer(answerDTOForCreateOrUpdate);
        answer.setDateCreated(answerOptional.get().getDateCreated());
        answer.setLastDateUpdated(new Timestamp(new Date().getTime()));
        Answer answerAfterSave = answerRepository.save(answer);
        return answerMapper.answerToAnswerDtoWithoutQuestion(answerAfterSave);
    }
}
