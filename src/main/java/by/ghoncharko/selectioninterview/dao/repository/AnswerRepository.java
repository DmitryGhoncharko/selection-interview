package by.ghoncharko.selectioninterview.dao.repository;

import by.ghoncharko.selectioninterview.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, BigInteger> {
    @Override
    Page<Answer> findAll(Pageable pageable);
    @Query("select ans from Answer ans left join fetch ans.question")
    Page<Answer> findAllWithoutLazyQuestions(Pageable pageable);


}
