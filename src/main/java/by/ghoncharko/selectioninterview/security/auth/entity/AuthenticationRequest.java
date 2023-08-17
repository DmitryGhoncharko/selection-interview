package by.ghoncharko.selectioninterview.security.auth.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @NotEmpty(message = "email does not be empty")
    @Size(max = 2000,message = "max email size 2000")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$", message = "must be email")
    @NotNull(message = "email does not be empty")
    private String email;
    @NotEmpty(message = "password does not be empty")
    @NotNull(message = "password does not be empty")
    @Size(max = 5000, message = "max password size 5000")
    private String password;
}
