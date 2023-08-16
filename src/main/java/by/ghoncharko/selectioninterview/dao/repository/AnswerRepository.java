package by.ghoncharko.selectioninterview.dao.repository;

import by.ghoncharko.selectioninterview.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, BigInteger> {

    Page<Answer> findAll(Pageable pageable);

    @Query("select ans from Answer ans left join fetch ans.question")
    Page<Answer> findAllWithoutLazyQuestions(Pageable pageable);

    Page<Answer> findAllByDeletedIsTrue(Pageable pageable);

    Page<Answer> findAllByDeletedIsFalse(Pageable pageable);

    @Query("select ans from Answer ans left join fetch ans.question where ans.deleted=true")
    Page<Answer> findAllWithoutLazyQuestionsWhereQuestionsDeletedTrue(Pageable pageable);

    @Query("select ans from Answer ans left join fetch ans.question where ans.deleted=false")
    Page<Answer> findAllWithoutLazyQuestionsWhereQuestionsDeletedFalse(Pageable pageable);

    @Query("select ans from Answer ans left join fetch ans.question where ans.deleted=true and ans.question.deleted = false ")
    Page<Answer> findAllWithoutLazyQuestionsWhereAnswersDeletedTrueAndQuestionDeletedFalse(Pageable pageable);

    @Query("select ans from Answer ans left join fetch ans.question where ans.deleted=true and ans.question.deleted = true ")
    Page<Answer> findAllWithoutLazyQuestionsWhereAnswersDeletedTrueAndQuestionDeletedTrue(Pageable pageable);

    @Query("select ans from Answer ans left join fetch ans.question where ans.deleted=false and ans.question.deleted = false ")
    Page<Answer> findAllWithoutLazyQuestionsWhereAnswersDeletedFalseAndQuestionDeletedFalse(Pageable pageable);

    @Query("select ans from Answer ans left join fetch ans.question where ans.deleted=false and ans.question.deleted = true ")
    Page<Answer> findAllWithoutLazyQuestionsWhereAnswersDeletedFalseAndQuestionDeletedTrue(Pageable pageable);

    @Modifying
    @Query("update Answer  ans set ans.deleted = true where ans.id=:id")
    void deleteAnswerById(@Param("id") BigInteger id);

    @Modifying
    @Query("update Answer  ans set ans.deleted = true where ans.answerBody=:body")
    void deleteAnswerByBody(@Param("body") String body);

    Optional<Answer> findByAnswerBody(String body);
}
