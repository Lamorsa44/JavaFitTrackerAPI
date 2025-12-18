package fitnesstracker.entity.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;

@Validated
public record FitUserRequest(@NotBlank @Email String email, @NotBlank String password) {
    public FitUserRequest {
        email = email.toLowerCase();
    }

    public FitUser encode(PasswordEncoder passwordEncoder) {
        return new FitUser(new FitUserRequest(email, passwordEncoder.encode(password))) ;
    }
}
