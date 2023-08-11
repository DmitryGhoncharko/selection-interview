package by.ghoncharko.selectioninterview.dao.repository;

import by.ghoncharko.selectioninterview.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, BigInteger> {
    Optional<Question> findByQuestionBodyIgnoreCase(String questionBody);

    Page<Question> findAll(Pageable pageable);
    @Query("select qs from Question qs left join fetch qs.answers left join fetch qs.questionType")
    Page<Question> findAllWithoutLazyWithAnswersAndQuestionTypes(Pageable pageable);

    @Query("select qs from Question  qs left join fetch qs.answers")
    Page<Question> findAllWithoutLazyWithAnswers(Pageable pageable);
    @Query("select  qs from Question  qs left join fetch qs.questionType")
    Page<Question> findAllWithoutLazyWithQuestionTypes(Pageable pageable);
    @Query("select qs from Question  qs left join fetch qs.answers where qs.questionBody=:body")
    Optional<Question> findByQuestionBodyWithoutLazyAnswers(@Param("body") String body);
}
