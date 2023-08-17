package by.ghoncharko.selectioninterview.security.service;

import by.ghoncharko.selectioninterview.dao.repository.UserRepository;
import by.ghoncharko.selectioninterview.entity.User;
import by.ghoncharko.selectioninterview.security.auth.entity.AuthenticationRequest;
import by.ghoncharko.selectioninterview.security.auth.entity.AuthenticationResponse;
import by.ghoncharko.selectioninterview.security.auth.entity.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager  authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .patronymic(registerRequest.getPatronymic())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        Optional<User>  userOptional = userRepository.findByEmail(authenticationRequest.getEmail());
        if(userOptional.isEmpty()){

        }
        String token = jwtService.generateToken(userOptional.get());
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
