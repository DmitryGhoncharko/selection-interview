package by.ghoncharko.selectioninterview.integration.repository;

import by.ghoncharko.selectioninterview.entity.QuestionType;
import by.ghoncharko.selectioninterview.repository.QuestionTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class QuestionTypeRepositoryTest {
    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testFindByQuestionTypeNameAllQuestionsWithoutLazy(){
       Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeNameAllQuestionsWithoutLazy("Question 1");
       questionTypeOptional.ifPresent(questionType->{
           Assertions.assertDoesNotThrow(()-> questionType.getQuestions().size());
       });
    }
    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testFindByQuestionTypeIdAllQuestionsWithoutLazy(){
        Optional<QuestionType> questionTypeOptional = questionTypeRepository.findByQuestionTypeIdAllQuestionsWithoutLazy(BigInteger.valueOf(1));
        questionTypeOptional.ifPresent(questionType->{
            Assertions.assertDoesNotThrow(()-> questionType.getQuestions().size()); ;
        });
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testFindAllWithoutLazy(){
        Page<QuestionType> questionTypePage = questionTypeRepository.findAllWithoutLazy(Pageable.ofSize(1));
        questionTypePage.getContent().forEach(questionType->{
            Assertions.assertDoesNotThrow(()->questionType.getQuestions().size());
        });
    }
}
