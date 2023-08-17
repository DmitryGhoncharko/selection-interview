package by.ghoncharko.selectioninterview.security.controller;

import by.ghoncharko.selectioninterview.security.auth.entity.AuthenticationRequest;
import by.ghoncharko.selectioninterview.security.auth.entity.AuthenticationResponse;
import by.ghoncharko.selectioninterview.security.auth.entity.RegisterRequest;
import by.ghoncharko.selectioninterview.security.service.AuthenticationService;
import by.ghoncharko.selectioninterview.security.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.registerAsClient(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        final String authHeader = httpServletRequest.getHeader("Authorization");
        final String  jwt = authHeader.substring(7);
        tokenService.saveTokenInBlackList(jwt);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }
}
