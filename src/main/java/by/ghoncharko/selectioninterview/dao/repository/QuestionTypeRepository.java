package by.ghoncharko.selectioninterview.dao.repository;

import by.ghoncharko.selectioninterview.entity.QuestionType;
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
public interface QuestionTypeRepository extends JpaRepository<QuestionType, BigInteger> {

    Optional<QuestionType> findByQuestionTypeName(String questionTypeName);

    @Query("select qt from QuestionType qt left join fetch qt.questions where qt.questionTypeName = :questionTypeName")
    Optional<QuestionType> findByQuestionTypeNameAllQuestionsWithoutLazy(@Param("questionTypeName") String questionTypeName);

    @Query("select qt from QuestionType qt left join fetch qt.questions where qt.id = :questionTypeId")
    Optional<QuestionType> findByQuestionTypeIdAllQuestionsWithoutLazy(@Param("questionTypeId") BigInteger questionTypeId);

    Page<QuestionType> findAll(Pageable pageable);

    Page<QuestionType> findAllByDeletedIsTrue(Pageable pageable);

    Page<QuestionType> findAllByDeletedIsFalse(Pageable pageable);


    @Query("select qt from QuestionType  qt left join fetch qt.questions where qt.deleted=false ")
    Page<QuestionType> findAllWithoutLazyDeletedQuestionTypeFalse(Pageable pageable);

    @Query("select qt from QuestionType qt left join fetch qt.questions q where qt.deleted = false and q.deleted = false")
    Page<QuestionType> findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionFalse(Pageable pageable);

    @Query("select qt from QuestionType qt left join fetch qt.questions q where qt.deleted = false and q.deleted = true ")
    Page<QuestionType> findAllWithoutLazyDeletedQuestionTypeFalseAndDeletedQuestionTrue(Pageable pageable);

    @Query("select qt from QuestionType  qt left join fetch qt.questions where qt.deleted=true")
    Page<QuestionType> findAllWithoutLazyDeletedQuestionTypeTrue(Pageable pageable);

    @Query("select qt from QuestionType qt left join fetch qt.questions q where qt.deleted = true and q.deleted = false")
    Page<QuestionType> findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsFalse(Pageable pageable);

    @Query("select qt from QuestionType qt left join fetch qt.questions q where qt.deleted = true and q.deleted = true ")
    Page<QuestionType> findAllWithoutLazyDeletedQuestionTypeTrueAndDeletedQuestionsTrue(Pageable pageable);

    @Query("select qt from QuestionType  qt left join fetch qt.questions")
    Page<QuestionType> findAllWithoutLazy(Pageable pageable);
    @Modifying
    @Query("update QuestionType qt set qt.deleted = true  where qt.id=:id")
    void deleteById(@Param("id") BigInteger id);
    @Modifying
    @Query("update QuestionType qt set qt.deleted = true  where qt.questionTypeName=:name")
    void deleteByQuestionTypeName(@Param("name") String name);

    Optional<QuestionType> findById(BigInteger id);

}
