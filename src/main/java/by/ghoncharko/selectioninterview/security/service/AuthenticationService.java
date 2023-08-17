package by.ghoncharko.selectioninterview.security.service;

import by.ghoncharko.selectioninterview.dao.repository.UserRepository;
import by.ghoncharko.selectioninterview.entity.Role;
import by.ghoncharko.selectioninterview.entity.User;
import by.ghoncharko.selectioninterview.security.auth.entity.AuthenticationRequest;
import by.ghoncharko.selectioninterview.security.auth.entity.AuthenticationResponse;
import by.ghoncharko.selectioninterview.security.auth.entity.RegisterRequest;
import by.ghoncharko.selectioninterview.security.error.CannotRegisterUserError;
import by.ghoncharko.selectioninterview.security.error.UserNotFoundError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse registerAsClient(RegisterRequest registerRequest) {
        Optional<User> userOptional = userRepository.findByEmail(registerRequest.getEmail());
        if (userOptional.isPresent()) {
            throw new CannotRegisterUserError("Cannot register user with email " + registerRequest.getEmail() + " because this email is present in database");
        }
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .patronymic(registerRequest.getPatronymic())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.builder()
                        .roleName(by.ghoncharko.selectioninterview.security.auth.entity.Role.CLIENT.name())
                        .id(BigInteger.valueOf(by.ghoncharko.selectioninterview.security.auth.entity.Role.CLIENT.ordinal()))
                        .build())
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    @Transactional(readOnly = true)
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        Optional<User> userOptional = userRepository.findByEmail(authenticationRequest.getEmail());
        if (userOptional.isEmpty()) {
            throw new UserNotFoundError("Cannot authenticate because user not found by email email = " + authenticationRequest.getEmail());
        }
        String token = jwtService.generateToken(userOptional.get());
        return AuthenticationResponse.builder().token(token).build();
    }
}
