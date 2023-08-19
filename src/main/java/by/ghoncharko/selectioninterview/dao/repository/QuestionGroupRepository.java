package by.ghoncharko.selectioninterview.dao.repository;

import by.ghoncharko.selectioninterview.entity.QuestionGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Optional;

public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, BigInteger> {
    Page<QuestionGroup> findByDeletedFalse(Pageable pageable);

    Page<QuestionGroup> findByDeletedTrue(Pageable pageable);

    Page<QuestionGroup> findAll(Pageable pageable);

    @Override
    Optional<QuestionGroup> findById(BigInteger integer);

    Optional<QuestionGroup> findByGroupName(String name);

    @Modifying
    @Query("update QuestionGroup qg set qg.deleted = true  where qg.id=:id")
    void deleteById(@Param("id") BigInteger id);
    @Modifying
    @Query("update QuestionGroup qg set qg.deleted = true  where qg.groupName=:name")
    void deleteByQuestionGroupName(@Param("name") String name);
}
