package fitnesstracker.service;

import fitnesstracker.entity.fitapp.FitnessApp;
import fitnesstracker.entity.user.FitUser;
import fitnesstracker.entity.user.FitUserDetails;
import fitnesstracker.entity.user.FitUserRequest;
import fitnesstracker.exception.BadRequestException;
import fitnesstracker.repository.FitUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FitUserService implements UserDetailsService {
    private final FitUserRepository fitUserRepository;
    private final PasswordEncoder passwordEncoder;

    public FitUserService(FitUserRepository fitUserRepository, PasswordEncoder passwordEncoder) {
        this.fitUserRepository = fitUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public FitUser addFitUser(FitUserRequest fitUserRequest) {
        if (fitUserRepository.existsFitUserByEmail(fitUserRequest.email())) {
            throw new BadRequestException("Invalid email");
        }
        return fitUserRepository.save(fitUserRequest.encode(passwordEncoder));
    }

    @Transactional
    public FitnessApp addFitApp(FitnessApp fitnessApp, UserDetails userDetails) {
        FitUser fitUser = getUserByEmail(userDetails.getUsername());
        fitUser.getApplications().add(fitnessApp);
        fitUserRepository.save(fitUser);
        return fitnessApp;
    }

    public FitUser getUserById(Long id) {
        return fitUserRepository.findById(id).orElseThrow(() ->
                new BadRequestException("User with id %d not found".formatted(id)));
    }

    public FitUser getUserByEmail(String email) {
        return fitUserRepository.findByEmail(email).orElseThrow(() ->
                new BadRequestException("User with email %s not found".formatted(email)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new FitUserDetails(getUserByEmail(username));
    }
}
