package fitnesstracker.service;

import fitnesstracker.entity.data.FitnessData;
import fitnesstracker.entity.data.FitnessRequest;
import fitnesstracker.repository.FitnessDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FitnessService {
    private final FitnessDataRepository fitnessDataRepository;
    private final FitAppService fitAppService;

    public FitnessService(FitnessDataRepository fitnessDataRepository, FitAppService fitAppService) {
        this.fitnessDataRepository = fitnessDataRepository;
        this.fitAppService = fitAppService;
    }

    public FitnessData addFitnessData(FitnessRequest fitnessRequest, UUID apikey) {
        return fitnessDataRepository.save(new FitnessData(fitnessRequest,
                fitAppService.getFitApp(apikey)));
    }

    public List<FitnessData> listFitnessDataWithApiKey(UUID apikey) {
        return fitnessDataRepository.findByApplication(fitAppService.getFitApp(apikey));
    }
}
