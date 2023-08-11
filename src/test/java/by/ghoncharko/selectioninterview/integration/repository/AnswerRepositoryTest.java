package by.ghoncharko.selectioninterview.integration.repository;

import by.ghoncharko.selectioninterview.entity.Answer;
import by.ghoncharko.selectioninterview.dao.repository.AnswerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class AnswerRepositoryTest {
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void testFindAllWithoutLazyQuestions() {
        Page<Answer> answerPage = answerRepository.findAllWithoutLazyQuestions(Pageable.ofSize(1));
        answerPage.forEach(answer -> {
            Assertions.assertDoesNotThrow(() -> answer.getQuestion().getId());
        });
    }
}
