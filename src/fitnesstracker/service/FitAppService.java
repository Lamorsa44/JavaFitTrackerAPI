package fitnesstracker.service;

import fitnesstracker.entity.fitapp.FitAppRequest;
import fitnesstracker.entity.fitapp.FitnessApp;
import fitnesstracker.exception.BadRequestException;
import fitnesstracker.exception.UnauthorizedException;
import fitnesstracker.repository.FitAppRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FitAppService {
    private final FitAppRepository fitAppRepository;
    private final FitUserService fitUserService;

    public FitAppService(FitAppRepository fitAppRepository, FitUserService fitUserService) {
        this.fitAppRepository = fitAppRepository;
        this.fitUserService = fitUserService;
    }

    @Transactional
    public FitnessApp registerApp(FitAppRequest fitAppRequest, UserDetails userDetails) {
        if (fitAppRepository.existsByNameIgnoreCase(fitAppRequest.name()))
            throw new BadRequestException("FitApp already exists");

        FitnessApp fitnessApp = new FitnessApp(fitAppRequest);
        fitnessApp = fitAppRepository.save(fitnessApp);
        fitUserService.addFitApp(fitnessApp, userDetails);
        return fitnessApp;
    }

    public FitnessApp getFitApp(UUID apikey) {
        return fitAppRepository.findByApikey(apikey)
                .orElseThrow(() -> new UnauthorizedException("No apikey matched"));
    }
}
