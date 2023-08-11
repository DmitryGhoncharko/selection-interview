package by.ghoncharko.selectioninterview.integration.service;

import by.ghoncharko.selectioninterview.dto.QuestionTypeDTO;
import by.ghoncharko.selectioninterview.dto.QuestionTypeDTOWithoutQuestions;
import by.ghoncharko.selectioninterview.service.QuestionTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class JpaHibernateQuestionTypeServiceTest {
    @Autowired
    private QuestionTypeService questionTypeService;

    @Test
    void testFindAllWithoutLazy(){
        List<QuestionTypeDTO> questionTypeDTOList = questionTypeService.findAllWithoutLazy(PageRequest.of(0,50));
        System.out.println(questionTypeDTOList);
    }
    @Test
    void testFindByQuestionTypeIdAllQuestionsWithoutLazy(){
        Optional<QuestionTypeDTO> questionTypeDTOOptional = questionTypeService.findByQuestionTypeIdAllQuestionsWithoutLazy(BigInteger.valueOf(1));
        System.out.println(questionTypeDTOOptional.get());
    }
    @Test
    void testFindByQuestionTypeNameAllQuestionsWithoutLazy(){
       Optional<QuestionTypeDTO> questionTypeDTOOptional = questionTypeService.findByQuestionTypeNameAllQuestionsWithoutLazy("Type 1");
        System.out.println(questionTypeDTOOptional.get());
    }
    @Test
    void testFindByQuestionTypeNameIgnoreCase(){
       Optional<QuestionTypeDTOWithoutQuestions> questionTypeDTOOptional =  questionTypeService.findByQuestionTypeNameIgnoreCase("Type 1");
        System.out.println(questionTypeDTOOptional.get());
    }
}
