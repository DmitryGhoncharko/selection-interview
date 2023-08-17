package by.ghoncharko.selectioninterview.security.service;

import by.ghoncharko.selectioninterview.dao.repository.TokenRepository;
import by.ghoncharko.selectioninterview.entity.Token;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    @Transactional(readOnly = true)
    public boolean tokenIsPresentInBlackList(String token) {
        return tokenRepository.findByTokenValue(token).isPresent();
    }

    @Transactional
    public void saveTokenInBlackList(String token) {
        Date expireDate = jwtService.extractClaim(token, Claims::getExpiration);
        Token tokenEntity = new Token().builder()
                .tokenValue(token)
                .expireDate(new Timestamp(expireDate.getTime()))
                .build();
        tokenRepository.save(tokenEntity);
    }
}
