package by.ghoncharko.selectioninterview.repository;

import by.ghoncharko.selectioninterview.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, BigInteger> {
}
