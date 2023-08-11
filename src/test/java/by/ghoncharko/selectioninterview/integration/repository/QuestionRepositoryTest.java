package by.ghoncharko.selectioninterview.integration.repository;

import by.ghoncharko.selectioninterview.entity.Question;
import by.ghoncharko.selectioninterview.dao.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testFindAllWithoutLazyWithAnswersAndQuestionTypes(){
      Page<Question> questionPage = questionRepository.findAllWithoutLazyWithAnswersAndQuestionTypes(Pageable.ofSize(1));
      questionPage.forEach(question -> {
          Assertions.assertDoesNotThrow(()->question.getAnswers().size());
          Assertions.assertDoesNotThrow(()->question.getQuestionType().getId());
      });
    }
    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testFindAllWithoutLazyWithAnswers(){
        Page<Question> questionPage = questionRepository.findAllWithoutLazyWithAnswers(Pageable.ofSize(1));
        questionPage.forEach(question -> {
            Assertions.assertDoesNotThrow(()->question.getAnswers().size());
        });
    }
    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testFindAllWithoutLazyWithQuestionTypes(){
        Page<Question> questionPage = questionRepository.findAllWithoutLazyWithQuestionTypes(Pageable.ofSize(1));
        questionPage.forEach(question -> {
            Assertions.assertDoesNotThrow(()->question.getQuestionType().getId());
        });
    }
    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void testFindByQuestionBodyWithoutLazyAnswers(){
        Optional<Question> questionOptional = questionRepository.findByQuestionBodyWithoutLazyAnswers("Question 1");
        Question question = questionOptional.orElseThrow(RuntimeException::new);
        Assertions.assertDoesNotThrow(()->question.getAnswers().size());
    }
}
