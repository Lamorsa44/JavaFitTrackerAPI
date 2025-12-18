package fitnesstracker.entity.user;

import fitnesstracker.entity.fitapp.FitnessApp;

import java.util.List;

public record FitUserResponse(Long id, String email, List<FitnessApp> applications) {
    public FitUserResponse(FitUser fitUser) {
        this(fitUser.getId(), fitUser.getEmail(), fitUser.getApplications().reversed());
    }
}
