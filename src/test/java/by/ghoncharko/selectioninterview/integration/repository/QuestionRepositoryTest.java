package by.ghoncharko.selectioninterview.integration.repository;

import by.ghoncharko.selectioninterview.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;


}
