package by.ghoncharko.selectioninterview.repository;

import by.ghoncharko.selectioninterview.entity.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, BigInteger> {

    Optional<QuestionType> findByQuestionTypeNameIgnoreCase(String questionTypeName);
    @Query("select qt from QuestionType qt left join fetch qt.questions where qt.questionTypeName = :questionTypeName")
    Optional<QuestionType> findByQuestionTypeNameAllQuestionsWithoutLazy(@Param("questionTypeName") String questionTypeName);

    @Query("select qt from QuestionType qt left join fetch qt.questions where qt.id = :questionTypeId")
    Optional<QuestionType> findByQuestionTypeIdAllQuestionsWithoutLazy(@Param("questionTypeId") BigInteger questionTypeId);

    Page<QuestionType> findAll(Pageable pageable);
    @Query("select qt from QuestionType  qt left join fetch qt.questions")
    Page<QuestionType> findAllWithoutLazy(Pageable pageable);

}
