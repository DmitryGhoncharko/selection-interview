package by.ghoncharko.selectioninterview.dao.repository;

import by.ghoncharko.selectioninterview.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, BigInteger> {
    Optional<Token> findByTokenValue(String tokenValue);
}
